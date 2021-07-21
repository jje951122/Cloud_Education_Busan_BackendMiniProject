package com.project.nmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nmt.model.OrderLog;

public interface OrderLogRepository extends JpaRepository<OrderLog, Long>{

}
