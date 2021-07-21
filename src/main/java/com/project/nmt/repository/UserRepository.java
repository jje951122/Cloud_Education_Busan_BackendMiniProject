package com.project.nmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nmt.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUserId(String userId);
	

}
