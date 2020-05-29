package com.tlabs.jwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtController.class);
	
	@GetMapping("hello")
	public String hello(){
		LOGGER.info("HIt HIt");
		return "Hello World!!";
	}

}
