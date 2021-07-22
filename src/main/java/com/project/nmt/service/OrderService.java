package com.project.nmt.service;


import com.project.nmt.model.Order;
import com.project.nmt.model.Stock;
import com.project.nmt.model.StockInfo;
import com.project.nmt.model.User;

public interface OrderService {
	void buy(User user, StockInfo stockInfo, Order order, Long totalPrice, int cnt);
	void sell(User user, StockInfo stockInfo, Stock nowStock, Order order, Long totalPrice, int cnt);
}
