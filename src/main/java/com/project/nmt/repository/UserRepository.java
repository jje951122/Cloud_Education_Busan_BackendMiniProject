package com.project.nmt.repository;

import com.project.nmt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,Long> {
} 