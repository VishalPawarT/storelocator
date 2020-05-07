package com.pin.sms.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pin.sms.rest.bean.LoginRequest;
import com.pin.sms.rest.bean.LoginResponse;
import com.pin.sms.rest.db.model.AppUser;
import com.pin.sms.rest.db.repository.UserRepository;
import com.pin.sms.rest.response.ApiResponseBuilderUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/")
public class LoginController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, LoginResponse loginResponse) {
		AppUser appUser = userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
		log.info("Request got for login {} ", loginRequest);
		if(appUser != null) {
			loginResponse.setAuthenticated(Boolean.valueOf(true));
			loginResponse.setMessage("Login Successfully.");
			loginResponse.setUserId(appUser.getUserId());
			loginResponse.setActive(appUser.isActive());
			loginResponse.setUsername(appUser.getUsername());
			log.info("Valid login request for {} ", loginRequest.getUsername());
			return ApiResponseBuilderUtil.toResponseEntity(loginResponse, HttpStatus.valueOf(200));
		} else {
			loginResponse.setAuthenticated(Boolean.valueOf(false));
			loginResponse.setActive(false);
			loginResponse.setMessage("Invalid Credentials");
			loginResponse.setUsername(loginRequest.getUsername());
			log.info("Invalid login request for {} ", loginRequest.getUsername());
			return ApiResponseBuilderUtil.toResponseEntity(loginResponse, HttpStatus.valueOf(403));
		}
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<LoginResponse> signup(@RequestBody LoginRequest loginRequest, LoginResponse loginResponse) {
		log.info("Request got for signup {} ", loginRequest);
		
		AppUser appUser = new AppUser();
		appUser.setUsername(loginRequest.getUsername());
		appUser.setPassword(loginRequest.getPassword());
		appUser.setActive(false);
		appUser.setToken("Test");
		
		try {
			userRepository.save(appUser);
		} catch(DataIntegrityViolationException e) {
			log.info("Duplicate key error got request {} ", loginRequest, e);
			loginResponse.setActive(Boolean.valueOf(false));
			loginResponse.setAuthenticated(Boolean.valueOf(false));
			loginResponse.setMessage("User profile is already created.");
			return ApiResponseBuilderUtil.toResponseEntity(loginResponse, HttpStatus.valueOf(403));
		}
		
		loginResponse.setActive(Boolean.valueOf(false));
		loginResponse.setAuthenticated(Boolean.valueOf(true));
		loginResponse.setUsername(appUser.getUsername());
		loginResponse.setMessage("User Profile created successfully.");
		loginResponse.setUserId(appUser.getUserId());
		
		return ApiResponseBuilderUtil.toResponseEntity(loginResponse, HttpStatus.valueOf(200));
	}
	
	@GetMapping("/submit/otp/{userId}/{otp}")
	public ResponseEntity<String> signup(@PathVariable("otp") String otp, @PathVariable("userId") Long userId) {
		AppUser appUser = userRepository.findByUserIdAndToken(userId, otp);
		if(appUser != null) {
			appUser.setActive(true);
			userRepository.save(appUser);
			return ApiResponseBuilderUtil.toResponseEntity("success", HttpStatus.valueOf(200));
		} else {
			return ApiResponseBuilderUtil.toResponseEntity("fail", HttpStatus.valueOf(403));
		}
	}
}
