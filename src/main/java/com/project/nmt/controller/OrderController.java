package com.project.nmt.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.nmt.model.Order;
import com.project.nmt.model.OrderLog;
import com.project.nmt.model.Stock;
import com.project.nmt.model.StockInfo;
import com.project.nmt.model.User;
import com.project.nmt.repository.OrderLogRepository;
import com.project.nmt.repository.OrderRepository;
import com.project.nmt.repository.StockInfoRepository;
import com.project.nmt.repository.UserRepository;
import com.project.nmt.repository.stockRepository;
import com.project.nmt.service.OrderService;

@Controller
@Transactional
public class OrderController {
	private static final int HashMap = 0;
	private static final int Long = 0;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderService orderService;
	@Autowired
	UserRepository userrepository;
	@Autowired
	OrderLogRepository orderlogRepository;
	@Autowired
	StockInfoRepository sir;
	@Autowired
	stockRepository sr;
	
	
	@ResponseBody
	@PostMapping("/order")
	public Boolean orderProduct(HttpSession session, @RequestParam int flag, @RequestParam("cnt") int cnt) {//flag 0매수 1매도
		Map<String, Object> res;
		User user=(User) session.getAttribute("id");
		Long num=(Long) session.getAttribute("stockId");
		System.out.println(num);
		Stock nowStock = sr.findByid(num);
		System.out.println(nowStock);
		
//		Long StockId= (Long) session.getAttribute("stockId");
		LocalDateTime now=LocalDateTime.now();
		LocalDate today=null;
		if (now.getHour() < 15) {// 2시인가 3시에 갱신되기떄문에 이전 시간에는 전날 가격 유지
			today = LocalDate.now().minusDays(1);
			
		} else {
			today = LocalDate.now();
		}
		StockInfo nowStockInfo = sir.findAllByStockAndInfoDate(nowStock, today);
		System.out.println(nowStockInfo);
		
		Long totalPrice=(long) (nowStockInfo.getPrice() *cnt);
		List<Order> list=orderRepository.findAllByUserAndStock(user, nowStock);
		if(flag==0) {//구매
			if(user.getBudget()<totalPrice) {
				return false;
			}
			
			res =orderService.buy(user, nowStockInfo, list, totalPrice, cnt );
//			int updatePrice=(int) res.get("afterAvgPrice");//구입/판매 후 평균가격
//			orderRepository.updateOrderPrice(user, nowStock, updatePrice);
			//매수 한것에 대해 보유내역에 저장
			userrepository.updateBudget((int)res.get("budget"), user.getId());
			Order order=(Order) res.get("order");
			orderRepository.save(order);
		}
		else {//판매
			
			if(list.size()<cnt) {
				return false;
			}
			
			res=orderService.sell(user, nowStockInfo, list, totalPrice, cnt);
			Map<Long, Integer> map = new HashMap<>();
			map=(Map<Long, Integer>) res.get("updateOrderInfo");//팔았을때 삭제하거나 갱신해야하는 품목 
			
	        for(Long key:map.keySet()){
	            int count=map.get(key);
	            if(count==0) {//0이면 가지고 있는 품목을 다 파는 것
	            	orderRepository.deleteOrderById(key);
	            }
	            else {//남아있는 개수->
	            	orderRepository.updateQuantityById(key, count);
	            }
	        }
	        
	        int beforePrice=(int) res.get("beforePrice");//구입/판메 전 평균가격
			int updatePrice=(int) res.get("afterPrice");//구입/판매 후 평균가격
			if(beforePrice!=updatePrice)
				orderRepository.updateOrderPrice(user, nowStock, updatePrice);
			else{
				System.out.println("가격변동 없음");
			}
			
			//판것에 대해 log를 남시고 남은 해당 품목의 가격 최신화
			OrderLog orderLog=new OrderLog();
			orderLog.setSoldDate(LocalDate.now());
			orderLog.setSoldPrice(nowStockInfo.getPrice());
			orderLog.setBoughtPrice(beforePrice);
			orderLog.setSoldQuantity(cnt);
			orderLog.setUser(user);
			orderLog.setStock(nowStock);
			
			orderlogRepository.save(orderLog);
			orderRepository.updateOrderPrice(user, nowStock, updatePrice);
		}
		
		return true;
	}
}
