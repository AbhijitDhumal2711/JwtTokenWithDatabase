package com.becoder.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.becoder.dto.UserRequest;
import com.becoder.model.UserDtls;

public interface UserService {
	public String login(UserRequest request);
	public List<UserDtls> getUserDtls();
	
	public UserDtls saveUser(UserDtls details);
		
}
