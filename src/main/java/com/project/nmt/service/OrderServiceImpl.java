package com.project.nmt.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.nmt.model.Order;
import com.project.nmt.model.OrderLog;
import com.project.nmt.model.Stock;
import com.project.nmt.model.StockInfo;
import com.project.nmt.model.User;
import com.project.nmt.repository.OrderLogRepository;
import com.project.nmt.repository.OrderRepository;
import com.project.nmt.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	OrderLogRepository orderLogRepository;

	@Override
	public void buy(User user, StockInfo stockInfo, Order order, Long totalPrice, int cnt) {
		// 매수한 금액만큼 계좌에서 차감
		int updateBudget = (int) (user.getBudget() - totalPrice);
		System.out.println("test");
		userRepository.updateBudget(updateBudget, user.getId());
		int afterAvgPrice=(int) (totalPrice/cnt);
		int totalCnt=cnt;
		
		if (order != null) {
			totalCnt = order.getQuantity();
			int orderTotalPrice = order.getPrice() * totalCnt;
			// 매수후 평가금액
			afterAvgPrice = (int) ((orderTotalPrice + totalPrice) / (totalCnt + cnt));

			// 기존의 order 삭제
			orderRepository.deleteById(order.getId());
		}

		// 이전 주문내역과 현재 매수로 갱신한 새로운 order삽입
		Order newOrder = new Order();
		newOrder.setBoughtDate(LocalDate.now());
		newOrder.setPrice(afterAvgPrice);
		newOrder.setQuantity(cnt + totalCnt);
		newOrder.setStock(stockInfo.getStock());
		newOrder.setUser(user);
		orderRepository.save(newOrder);
	}

	@Override
	public void sell(User user, StockInfo stockInfo, Stock nowStock, Order order, Long totalPrice, int cnt) {
		if (order.getQuantity() == cnt) {// 판매 개수와 보유 개수가 같으면 삭제
			orderRepository.deleteOrderById(order.getId());
		} else {// 보유개수가 더 많으면 차감
			orderRepository.updateQuantityById(order.getId(), order.getQuantity() - cnt);
		}

		// 현재 가격 기준 판매한 금액만큼 계좌에 합산
		userRepository.updateBudget(stockInfo.getPrice() * cnt+user.getBudget(), user.getId());

		// 판것에 대해 log
		OrderLog orderLog = new OrderLog();
		orderLog.setSoldDate(LocalDate.now());
		orderLog.setSoldPrice(stockInfo.getPrice());
		orderLog.setBoughtPrice(order.getPrice());
		orderLog.setSoldQuantity(cnt);
		orderLog.setUser(user);
		orderLog.setStock(nowStock);
		orderLogRepository.save(orderLog);
	}

}
