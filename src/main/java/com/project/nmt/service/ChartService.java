package com.project.nmt.service;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;

import com.project.nmt.model.Stock;


public interface ChartService {
	ResponseEntity<JSONObject> json_get(Stock stock);


}
