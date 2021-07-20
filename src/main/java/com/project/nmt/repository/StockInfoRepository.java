package com.project.nmt.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nmt.model.Stock;
import com.project.nmt.model.StockInfo;

public interface StockInfoRepository extends JpaRepository<StockInfo, Long> {
	List<StockInfo> findAllByStock(Stock stock);

	List<StockInfo> findAllBystock(Optional<Stock> stock);

}
