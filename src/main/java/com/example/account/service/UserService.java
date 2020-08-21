package com.example.account.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.account.model.User;
import com.example.account.repository.UserRepository;
import com.github.javaparser.ast.stmt.BreakStmt;

@Service
public class UserService {

	private final static Logger log = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Optional<User> findById(long id) {
		log.info("Get user details for id - " + id);
		return userRepository.findById(id);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public boolean existsByMobile(long mobile) {
		return userRepository.existsByMobile(mobile);
	}
	
	/*
	 * public static void main(String[] args) { BreakStmt breakStmt = new
	 * BreakStmt(); System.out.println(breakStmt.getLabel()); }
	 */
}
