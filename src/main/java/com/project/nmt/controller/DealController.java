package com.project.nmt.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.nmt.dto.ChartDto;
import com.project.nmt.model.Stock;
import com.project.nmt.model.StockInfo;
import com.project.nmt.repository.StockInfoRepository;
import com.project.nmt.repository.stockRepository;
import com.project.nmt.service.ChartService;

@Controller
public class DealController {
	@Autowired
	stockRepository sr;
	@Autowired
	StockInfoRepository sir;
	@Autowired
	ChartService chartService;

	@GetMapping("/nowProduct")
	public String nowProduct(@RequestParam("num") Long num, Model model) {
		Stock stock = sr.findByid(num);
		model.addAttribute("name", stock.getKeyword());
		model.addAttribute("id", num);

		// 당일 가격, 하루전과의 변동률
		LocalTime now = LocalTime.now();
		LocalDate today;
		LocalDate yesterday;
		String opt = "";
		if (now.getHour() < 15) {// 2시인가 3시에 갱신되기떄문에 이전 시간에는 전날 가격 유지
			today = LocalDate.now().minusDays(1);
			yesterday = today.minusDays(1);
			opt = "금일 가격 갱신전입니다.";
		} else {
			today = LocalDate.now();
			yesterday = today.minusDays(1);
		}
		model.addAttribute("opt", opt);

		Long todayPrice = sir.findAllByStockAndInfoDate(stock, today);
		model.addAttribute("todayPrice", todayPrice);

		Long yesterdayPrice = sir.findAllByStockAndInfoDate(stock, today);
		double diff= ((double) ((todayPrice - yesterdayPrice) / (double) yesterdayPrice));
		String temp="";
		if(diff>=0)
			temp="+";
			
		String diffPer = String.format(temp+"%.2f",diff*100);
		model.addAttribute("diffPer", diffPer);

		return "nowProduct";
	}

	@PostMapping("/chart")
	@ResponseBody
	public ResponseEntity<JSONObject> ChartInit(@RequestParam("num") Long num) {
		Stock stock = sr.findByid(num);
		// 차트정보를 json데이터로 전달
		return chartService.json_get(stock);
	}

}
