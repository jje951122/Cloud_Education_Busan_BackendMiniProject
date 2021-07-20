package com.project.nmt.repository;

import com.project.nmt.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("select s.name from Stock s")
    List<String> findAllStockName();
}
