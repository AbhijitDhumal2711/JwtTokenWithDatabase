package com.becoder.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.becoder.dto.UserRequest;

import com.becoder.model.UserDtls;
import com.becoder.service.UserService;

import ch.qos.logback.classic.Logger;
import io.jsonwebtoken.security.InvalidKeyException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

	   
	@Autowired
	private UserService userService;

//	@GetMapping("/test")
//	public ResponseEntity<?> getDetails(HttpServletRequest request) {
////		String id = request.getSession().getId();
//		return new ResponseEntity<>("Hello ,Welcome to Becoder Dashboard", HttpStatus.OK);
//	}

	@GetMapping("/test")
	public String getDetails() {
//		String id = request.getSession().getId();
		return "Abhijit Dhumal";
	}
	
	
	@GetMapping("/user")
	public ResponseEntity<?> getUserDetails(HttpServletRequest request) {
		return new ResponseEntity<>(userService.getUserDtls(), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserRequest userReqeust) {
		String token = userService.login(userReqeust);
		if (token == null) {
			return new ResponseEntity<>("invalid credentials", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public UserDtls saveUser(@RequestBody UserDtls details) {
		
		return userService.saveUser(details);
	}

}
