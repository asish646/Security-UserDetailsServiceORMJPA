package com.hsbc.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hsbc.security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUserEmail(String userEmail);
}
