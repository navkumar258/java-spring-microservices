package com.example.account.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.account.config.AppProperties;
import com.example.account.model.User;
import com.example.account.service.PublisherService;
import com.example.account.service.UserService;

@RestController
@RequestMapping("/api")
public class MainController {

	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	private final UserService userService;

	private final PublisherService publisherService;

	private final AppProperties appProperties;

	public MainController(UserService userService, PublisherService publisherService, AppProperties appProperties) {
		this.userService = userService;
		this.publisherService = publisherService;
		this.appProperties = appProperties;
	}

	@PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		log.info("Create user request - " + user.toString());

		Map<String, Object> response = new HashMap<>();

		if (userService.existsByEmail(user.getEmail())) {
			response.put("status", "error");
			response.put("message", "user email already exists, please change and try again");
		} else if (userService.existsByMobile(user.getMobile())) {
			response.put("status", "error");
			response.put("message", "user mobile already exists, please change and try again");
		} else {
			user.setEmailVerified(false);
			User savedUser = userService.saveUser(user);

			publisherService.publishUserCreateEvent(appProperties.getUserCreationRoutingKey(), savedUser);

			response.put("status", "success");
			response.put("message", "User created successfully!!!");
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(path = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUserDetails(@PathVariable(name = "id", required = true) long userId) {
		Map<String, Object> response = new HashMap<>();

		Optional<User> userInDB = userService.findById(userId);

		if (userInDB.isPresent()) {
			response.put("status", "success");
			response.put("message", userInDB);
		} else {
			response.put("status", "error");
			response.put("message", "Requested user could not be found!!!");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
