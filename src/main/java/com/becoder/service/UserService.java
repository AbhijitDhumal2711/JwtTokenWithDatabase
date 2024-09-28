package com.becoder.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.becoder.model.User;
import com.becoder.repository.UserRepos;

@Service
public class UserService {

	@Autowired
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	
	private UserRepos userRepos;
	
	public List<User> getAllUsers(){
		
		return userRepos.findAll();
	}
	
	public User createUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepos.save(user);
	}
	
	
	public User getOneUser(int id) {
		
		return userRepos.findById(id).get();
	}
}
