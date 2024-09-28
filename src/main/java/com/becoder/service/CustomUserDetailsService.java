package com.becoder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.becoder.model.User;
import com.becoder.repository.UserRepos;
@Service
public class CustomUserDetailsService implements UserDetailsService{

	
	@Autowired
	private UserRepos repos;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	 User user=repos.findByEmail(username).orElseThrow(()->new RuntimeException("User Not Found"));
		
		
		return user;
	}

}
