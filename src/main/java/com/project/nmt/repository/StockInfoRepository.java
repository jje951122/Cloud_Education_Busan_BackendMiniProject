package com.project.nmt.repository;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.nmt.dto.ChartDto;
import com.project.nmt.model.Stock;
import com.project.nmt.model.StockInfo;

public interface StockInfoRepository extends JpaRepository<StockInfo, Long> {
	List<StockInfo> findAllByStock(Stock stock);



	@Query("select s.price from StockInfo s where s.stock=?1 AND s.infoDate=?2")
	Long findAllByStockAndInfoDate(Stock stock,LocalDate today);

}
