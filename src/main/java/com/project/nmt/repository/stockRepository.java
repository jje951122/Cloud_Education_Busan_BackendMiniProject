package com.project.nmt.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nmt.model.Stock;

public interface stockRepository extends JpaRepository<Stock, Long> {

	Stock findByid(Long num);


}