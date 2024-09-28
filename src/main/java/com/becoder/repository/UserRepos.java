package com.becoder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.becoder.model.User;

@Repository
public interface UserRepos extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);
	
}
