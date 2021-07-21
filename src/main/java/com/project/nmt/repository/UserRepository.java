package com.project.nmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nmt.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserId(String userId);
	

}
