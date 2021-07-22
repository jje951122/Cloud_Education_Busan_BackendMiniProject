package com.project.nmt.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.nmt.dto.OrderDto;
import com.project.nmt.model.Order;
import com.project.nmt.model.User;
import com.project.nmt.repository.OrderRepository;
import com.project.nmt.repository.StockInfoRepository;
import com.project.nmt.repository.stockRepository;
import com.project.nmt.repository.UserRepository;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Controller
public class BudgetController {
	
	
	private final UserRepository userRepository;
	private final OrderRepository orderRepository; 
	private final StockInfoRepository stockInfoRepository;
	private final stockRepository stockRepository;

	
	@GetMapping("/index/{id}")
	public String budget(@PathVariable("id") Long id, Model model) {
		User user = userRepository.findById(id).get();
		List<Order> orderList = orderRepository.findAllByUser(user);
	
		
		model.addAttribute("nowUser", user.getName());
		model.addAttribute("nowUserBudget", user.getBudget());
		
		model.addAttribute("nowOrderlist", orderList);
		
		if(orderList==null) {
			return "index";
		}
		
		LocalTime now=LocalTime.now();
		LocalDate today=LocalDate.now();
		if (now.getHour() < 15) {// 2시인가 3시에 갱신되기떄문에 이전 시간에는 전날 가격 유지
			today = LocalDate.now().minusDays(1);
		}
		
		List<OrderDto> list=new ArrayList<>();
		int total= 0;
		for(int i=0;i<orderList.size();i++) {
			OrderDto temp=new OrderDto();
			Order order=orderList.get(i);
			temp.setBoughtPrice(order.getPrice());
			temp.setQuantity(order.getQuantity());
			temp.setNowPrice(stockInfoRepository.findAllByStockAndInfoDate(order.getStock(), today).getPrice());
			temp.setTitle(order.getStock().getKeyword());
			double diff= ((double) ((temp.getNowPrice() - temp.getBoughtPrice()) / (double) temp.getBoughtPrice()));
			String s="";
			if(diff>=0)
				s="+";	
			temp.setPercent(String.format(s+"%.2f",diff*100));
			temp.setStockId(stockRepository.findStockByKeyword(temp.getTitle()).getId());
			list.add(temp);
			total+=order.getPrice()*order.getQuantity();
		}
		model.addAttribute("orderList", list);
		model.addAttribute("nowUserTotalBudget", total);
		

		return "index";
	}
	
	
	
}