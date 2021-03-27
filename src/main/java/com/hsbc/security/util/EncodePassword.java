package com.hsbc.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncodePassword {

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public String encodePasswordBeforeSave(String password) {
		
		String encoded = encoder.encode(password);
		return encoded;
	}
}
