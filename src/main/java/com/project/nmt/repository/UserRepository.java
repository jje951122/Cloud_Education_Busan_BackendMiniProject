package com.project.nmt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.nmt.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserId(String userId);
	
	@Modifying
	@Transactional
	@Query("update User u SET u.budget=:budget WHERE u.id=:userId")
	int updateBudget(int budget, Long userId);//품목 사고팔떄 유저의 자산을 변경

}
