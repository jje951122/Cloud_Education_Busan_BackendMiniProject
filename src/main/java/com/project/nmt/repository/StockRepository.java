package com.project.nmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nmt.model.StockInfo;

public interface StockRepository extends JpaRepository<StockInfo, Long>{
	
}
