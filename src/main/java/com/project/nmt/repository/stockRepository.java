package com.project.nmt.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nmt.model.Stock;
import com.project.nmt.model.StockInfo;

public interface stockRepository extends JpaRepository<Stock, Long> {

	Stock findByid(Long num);

}
