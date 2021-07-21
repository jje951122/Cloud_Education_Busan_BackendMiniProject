package com.project.nmt.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.nmt.model.Order;
import com.project.nmt.model.StockInfo;
import com.project.nmt.model.User;
import com.project.nmt.repository.OrderRepository;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.BatchAllocator.ForTotal;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public Map<String, Object> buy(User user, StockInfo stockInfo, List<Order> list, Long totalPrice, int cnt) {
		Map<String, Object> map = new HashMap<>();
		int updateBudget=(int) (user.getBudget()-totalPrice);
		
		int totalCnt=0;
		int orderTotalPrice=0;
		List<Long> orderIdList=new ArrayList<Long>();
		for(int i=0;i<list.size();i++) {
			Order order=list.get(i);
			orderIdList.add(order.getId());
			int nowOrderCnt=order.getQuantity();
			totalCnt+=nowOrderCnt;
			orderTotalPrice+=order.getPrice()*nowOrderCnt;
		}
		for(int i=0;i<orderIdList.size();i++) {
			orderRepository.deleteById(orderIdList.get(i));
		}
		
		
		int afterAvgPrice=(int) ((orderTotalPrice+totalPrice)/(totalCnt+cnt));
		map.put("afterAvgPrice", afterAvgPrice);
		
		
		
		Order order = new Order();
		order.setBoughtDate(LocalDate.now());
		order.setPrice(afterAvgPrice);
		order.setQuantity(cnt+totalCnt);
		order.setStock(stockInfo.getStock());
		order.setUser(user);
		map.put("budget", updateBudget);
		map.put("order", order);
		
		return map;
	}

	@Override
	public Map<String, Object> sell(User user, StockInfo stockInfo, List<Order> list, Long totalPrice, int cnt) {
		Map<String, Object> map = new HashMap<>();
		int updateBudget=(int) (user.getBudget()+totalPrice);
		Map<Long, Integer> updateOrderInfo = new HashMap<>();//order.id, 바껴야하는 order.quantity값 -> 이걸 넘겨줘서 update해줄 예정 /0이면 삭제 
		
		int orderTotalPrice=0;
		int totalCnt=0;
		int count=cnt;
		for(int i=0;i<list.size();i++) {
			Order order=list.get(i);
			int nowOrderCnt=order.getQuantity();
			totalCnt+=nowOrderCnt;
			orderTotalPrice+=order.getPrice()*nowOrderCnt;
			
			if(count==0)continue;
			else if(nowOrderCnt<=cnt) {
				updateOrderInfo.put(order.getId(), 0);
				count-=nowOrderCnt;
			}
			else {
				updateOrderInfo.put(order.getId(), nowOrderCnt-cnt);
				count=0;
			}
		}
		int beforeAvgPrice=(int) orderTotalPrice/totalCnt;
		map.put("beforePrice", beforeAvgPrice);
		int afterAvgPrice=(int) ((orderTotalPrice-totalPrice)/(totalCnt-cnt));
		map.put("updateOrderInfo", updateOrderInfo);
		
		map.put("afterPrice", afterAvgPrice);
		System.out.println(map);
		return map;

	}

}
