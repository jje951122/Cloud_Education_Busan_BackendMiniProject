package com.project.nmt.service;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.project.nmt.model.Stock;


public interface ChartService {
	public ResponseEntity<JSONObject> json_get(Stock stock);
	
}
