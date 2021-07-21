package com.project.nmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nmt.model.Order;
import com.project.nmt.model.User;

public interface OrderRepository extends JpaRepository<Order, Long>{
	List<Order> findAllByUser(User user);
}
