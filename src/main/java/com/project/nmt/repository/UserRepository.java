package com.project.nmt.repository;

import com.project.nmt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUserId(String userId);

	boolean existsByUserId(String userId);

	boolean existsByEmail(String email);
}
