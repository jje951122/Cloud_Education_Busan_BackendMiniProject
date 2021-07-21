package com.project.nmt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.nmt.model.Order;
import com.project.nmt.model.User;
import com.project.nmt.repository.OrderRepository;
import com.project.nmt.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class BudgetController {
	
	private final UserRepository userRepository;
	private final OrderRepository orderRepository; 
	

	
	@GetMapping("/index/{id}")
	public String budget(@PathVariable("id") Long id, Model model) {
		User user = userRepository.findById(id).get();
		List<Order> orderList = orderRepository.findAllByUser(user);
		
		model.addAttribute("user", user);
		model.addAttribute("orderlist", orderList);
		
		
		return "index";
	}
	
	
	
}