package com.project.nmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nmt.model.StockInfo;

public interface stockRepository extends JpaRepository<StockInfo, Long> {

}
