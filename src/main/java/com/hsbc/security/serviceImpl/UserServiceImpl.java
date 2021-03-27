package com.hsbc.security.serviceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hsbc.security.entity.User;
import com.hsbc.security.repository.UserRepository;
import com.hsbc.security.service.UserService;																																																																																									
import com.hsbc.security.util.EncodePassword;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	private UserRepository repository;

	@Autowired
	private EncodePassword encode;

	@Override
	public Integer saveUser(User user) {

		user.setUserPassword(encode.encodePasswordBeforeSave(user.getUserPassword()));
		User savedUser = repository.save(user);
		return savedUser.getUserId();
	}
	
	@Override
	public User findByUserEmail(String email) {
		
		return repository.findByUserEmail(email).get();
	}

	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		
		Optional<User> userOptional = repository.findByUserEmail(user);
		
		if(userOptional.isEmpty()) {
			throw new UsernameNotFoundException("Not a valid User");
		}else {
			User userRtvd = userOptional.get();
			Set<SimpleGrantedAuthority> sga = new HashSet<SimpleGrantedAuthority>();
			for(String role : userRtvd.getAuthorities()) {
				sga.add(new SimpleGrantedAuthority(role));
			}
			return new org.springframework.security.core.userdetails.User(user, userRtvd.getUserPassword()
					,sga );
//			userRtvd.getAuthorities().stream().map(x->new SimpleGrantedAuthority(x)).collect(Collectors.toSet())
		}
		
	}

}
