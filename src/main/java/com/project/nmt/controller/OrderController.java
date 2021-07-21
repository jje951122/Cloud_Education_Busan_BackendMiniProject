package com.project.nmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

	
	@PostMapping("/order")
	public String orderProduct(@RequestParam("cnt") int cnt) {
	return null;
	}
}
