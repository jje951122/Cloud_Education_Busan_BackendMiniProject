package com.project.nmt.service;

import com.project.nmt.model.Stock;
import com.project.nmt.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepository stockRepository;

    public List<String> getStockKeywordList() {
        return stockRepository.findAllStockKeyword();
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public List<Stock> getStocksByPage(int page, int size) {
        int count = (int) stockRepository.count();
        int fromIdx = page * size;
        int toIdx = (page + 1) * size;

        if (toIdx > count) {
            toIdx = count;
        }

        return stockRepository.findAll(Sort.by("keyword"))
                .subList(fromIdx, toIdx);
    }

    public int getStockSize(int size) {
        return (int) stockRepository.count() / size;
    }

    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElseGet(Stock::new);
    }

    public Stock getStockByName(String name) {
        return stockRepository.findByName(name);
    }
}
