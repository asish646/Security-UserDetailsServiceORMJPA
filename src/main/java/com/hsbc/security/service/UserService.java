package com.hsbc.security.service;

import java.util.Optional;

import com.hsbc.security.entity.User;

public interface UserService {

	public Integer saveUser(User user);
	
	public User findByUserEmail(String email);
}
