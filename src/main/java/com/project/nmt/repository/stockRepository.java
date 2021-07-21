package com.project.nmt.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.nmt.model.Stock;

public interface stockRepository extends JpaRepository<Stock, Long> {
	@Query("select s from Stock s where s.id=?1")
	Stock findByid(Long num);


}

