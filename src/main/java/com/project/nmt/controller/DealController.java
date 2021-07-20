package com.project.nmt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.nmt.model.Stock;
import com.project.nmt.model.StockInfo;
import com.project.nmt.repository.StockInfoRepository;
import com.project.nmt.repository.StockRepository;

@Controller
public class DealController {
	@Autowired StockRepository sr;
	@Autowired StockInfoRepository sir;
	
	


	@GetMapping("/chart")
	public String chart(@RequestParam("num") Long num, Model model) {
		System.out.println(num);

		Optional<Stock> stock=sr.findById(num);
		model.addAllAttributes(sir.findAllBystock(stock));
//		List<StockInfo> list=sir.findAllBystock(stock);
		StockInfo stock2;
		
		return "chart";
	}
}
