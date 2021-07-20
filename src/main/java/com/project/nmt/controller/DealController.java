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
import com.project.nmt.repository.stockRepository;

@Controller
public class DealController {
	@Autowired stockRepository sr;
	@Autowired StockInfoRepository sir;
	
	

	@ResponseBody
	@GetMapping("/chart")
	public List<StockInfo> chart(@RequestParam("num") Long num, Model model) {
		Stock stock=sr.findByid(num);
		model.addAttribute("list",sir.findAllByStock(stock));
//		model.addAllAttributes(, "list");


		
		return sir.findAllByStock(stock);
	}
}
