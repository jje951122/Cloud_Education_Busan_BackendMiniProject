package com.project.nmt.controller;

import com.project.nmt.model.Stock;
import com.project.nmt.model.StockInfo;
import com.project.nmt.repository.StockInfoRepository;
import com.project.nmt.repository.StockRepository;
import com.project.nmt.service.ChartService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class DealController {//품목 차트, 품목 거래를 위한 창을 띄움
	@Autowired
	StockRepository stockRepository;
	@Autowired
	StockInfoRepository stockInfoRepository;
	@Autowired
	ChartService chartService;

	@GetMapping("/nowProduct")
	public String nowProduct(@RequestParam("num") Long num, Model model, HttpSession session) {
		Stock stock = stockRepository.findById(num).get();
		
		model.addAttribute("name", stock.getKeyword());
		model.addAttribute("stockId", num);
		session.setAttribute("stockId", stock.getId());
		
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
		
		//현재가격과 전날가격과의 변동사항
		StockInfo nowStockInfo = stockInfoRepository.findAllByStockAndInfoDate(stock, today);
		Long todayPrice = (long) nowStockInfo.getPrice();
		model.addAttribute("todayPrice", todayPrice);

		Long yesterdayPrice = (long) stockInfoRepository.findAllByStockAndInfoDate(stock, today).getPrice();
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
	public ResponseEntity<JSONObject> ChartInit(@RequestParam("num") Long num) {//차트를 띄우기 위한 데이터 요청
		Stock stock = stockRepository.findById(num).get();
		// 차트정보를 json데이터로 전달
		return chartService.json_get(stock);
	}
}
