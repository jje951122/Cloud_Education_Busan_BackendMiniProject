package com.project.nmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.nmt.model.Stock;
import com.project.nmt.model.StockInfo;

public interface StockRepository extends JpaRepository<Stock, Long> {
	List<StockInfo> findAllByid(Long id);
}
