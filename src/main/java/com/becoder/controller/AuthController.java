package com.becoder.controller;

import java.util.List;

import org.slf4j.Logger;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.becoder.config.JwtHelper;
import com.becoder.dto.JwtResponse;
import com.becoder.model.JwtRequest;
import com.becoder.model.User;
import com.becoder.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	
	@Autowired
	private UserService userService;
	 
	   @Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private AuthenticationManager manager;


	    @Autowired
	    private JwtHelper helper;

	    private Logger logger = LoggerFactory.getLogger(AuthController.class);


	    @PostMapping("/login")
	    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

	        this.doAuthenticate(request.getEmail(), request.getPassword());


	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        String token = this.helper.generateToken(userDetails);

	        JwtResponse response = new JwtResponse();
	        response.setJwtToken(token);
	        response.setUsername(userDetails.getUsername());
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	    private void doAuthenticate(String email, String password) {

	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
	        try {
	            manager.authenticate(authentication);


	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException(" Invalid Username or Password  !!");
	        }

	    }

	    @ExceptionHandler(BadCredentialsException.class)
	    public String exceptionHandler() {
	        return "Credentials Invalid !!";
	    }
	    
	   
	    @PostMapping("/create")
	    public User createUser(@RequestBody User user) {
	   
	    	return userService.createUser(user);
	    	
	    }
	    
	    @GetMapping("/users")
	    public ResponseEntity<List<User>> getAllUsers() {
	        List<User> users = userService.getAllUsers();
	        return new ResponseEntity<>(users, HttpStatus.OK);
	    }
	    
	    @GetMapping("/users/{id}")
	    public User getUser(@PathVariable("id") int id) {
	    	
	    	return userService.getOneUser(id);
	    }

}
