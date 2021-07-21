package com.project.nmt.service;

import com.project.nmt.model.Stock;
import com.project.nmt.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepository stockRepository;

    public List<String> getStockList() {
        return stockRepository.findAllStockKeyword();
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
    
}
