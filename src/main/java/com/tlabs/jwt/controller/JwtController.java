package com.tlabs.jwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tlabs.jwt.model.AuthenticateRequest;
import com.tlabs.jwt.model.AuthenticateResponse;
import com.tlabs.jwt.model.UserDetail;
import com.tlabs.jwt.service.UserDetailService;
import com.tlabs.jwt.util.JwtUtil;

@RestController
public class JwtController {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/hello")
	public String hello() {
		LOGGER.info("Hello World get Request Hit");
		return "Hello World!!";
	}
	
	
	@PostMapping("/user")
	public ResponseEntity<?> addUser(@RequestBody UserDetail userDetail){
		
		LOGGER.info("Input User " + userDetail);
		
		userDetailService.addUser(userDetail);
		
		return ResponseEntity.ok("User INput");
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticateRequest authenticateRequest) {

		LOGGER.info("------> Authenticating Credentials and Creating Token");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticateRequest.getUserName(), authenticateRequest.getPassword()));

		} catch (BadCredentialsException ex) {
			LOGGER.error("Invalid User Name Or Password ", ex);
			return new ResponseEntity<>("Invalid User Name Or Password", HttpStatus.UNAUTHORIZED);
		}
		LOGGER.info("After Authentication");
		UserDetails userDetails = userDetailService.loadUserByUsername(authenticateRequest.getUserName());
		String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticateResponse(jwt));
	}

}
