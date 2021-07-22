package com.project.nmt.controller;

import com.project.nmt.model.Order;
import com.project.nmt.model.Stock;
import com.project.nmt.model.StockInfo;
import com.project.nmt.model.User;
import com.project.nmt.repository.OrderRepository;
import com.project.nmt.repository.StockInfoRepository;
import com.project.nmt.repository.StockRepository;
import com.project.nmt.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
@Controller
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final StockInfoRepository stockInfoRepository;
    private final StockRepository stockRepository;

    @ResponseBody
    @PostMapping("/order")
    public Boolean orderProduct(HttpSession session, @RequestParam int flag, @RequestParam("cnt") int cnt) {//flag 0매수 1매도
        User user = (User) session.getAttribute("user");
        Long num = (Long) session.getAttribute("stockId");
        System.out.println(num);
        Stock nowStock = stockRepository.findById(num).orElseGet(Stock::new);
        System.out.println(nowStock.getId());


        LocalDateTime now = LocalDateTime.now();
        LocalDate today;
        if (now.getHour() < 15) {// 3시에 갱신되기떄문에 이전 시간에는 전날 가격 유지
            today = LocalDate.now().minusDays(1);
        } else {
            today = LocalDate.now();
        }
        //품목에 대한 현재 가격
        StockInfo nowStockInfo = stockInfoRepository.findAllByStockAndInfoDate(nowStock, today);


        //현재 거래 금액
        Long totalPrice = ((long) nowStockInfo.getPrice() * cnt);
        //보유 품목에 대한 현황
        Order order = orderRepository.findByUserAndStock(user, nowStock);

        if (flag == 0) {//구매
            if (user.getBudget() < totalPrice) {
                return false;
            }
            orderService.buy(user, nowStockInfo, order, totalPrice, cnt);
        } else {//판매
            if (order == null || order.getQuantity() < cnt) {//가진것이 없거나 수량이 모자라면
                return false;
            }
            orderService.sell(user, nowStockInfo, nowStock, order, totalPrice, cnt);
        }

        return true;
    }
}
