package com.project.nmt.service;

import java.util.List;
import java.util.Map;

import com.project.nmt.model.Order;
import com.project.nmt.model.StockInfo;
import com.project.nmt.model.User;

public interface OrderService {
	public Map<String, Object> buy(User user, StockInfo stockInfo, List<Order> list, Long totalPrice, int cnt);
	public Map<String, Object> sell(User user, StockInfo stockInfo, List<Order> list, Long totalPrice,int cnt);
}
