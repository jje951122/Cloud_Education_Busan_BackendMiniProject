package com.project.nmt.service;

import com.project.nmt.model.*;
import com.project.nmt.repository.OrderLogRepository;
import com.project.nmt.repository.OrderRepository;
import com.project.nmt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final OrderLogRepository orderLogRepository;

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

	public void sell(User user, StockInfo stockInfo, Stock nowStock, Order order, Long totalPrice, int cnt) {
		if (order.getQuantity() == cnt) {// 판매 개수와 보유 개수가 같으면 삭제
			orderRepository.deleteOrderById(order.getId());
		} else {// 보유개수가 더 많으면 차감
			orderRepository.updateQuantityById(order.getId(), order.getQuantity() - cnt);
		}

		// 현재 가격 기준 판매한 금액만큼 계좌에 합산
		userRepository.updateBudget(stockInfo.getPrice() * cnt+user.getBudget(), user.getId());

		// 판것에 대해 log
		OrderLog orderLog = OrderLog.builder()
				.soldDate(LocalDate.now())
				.soldPrice(stockInfo.getPrice())
				.boughtPrice(order.getPrice())
				.soldQuantity(cnt)
				.user(user)
				.stock(nowStock)
				.build();

		orderLogRepository.save(orderLog);
	}

}
