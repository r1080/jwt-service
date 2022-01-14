package org.oauth.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
//@EnableEurekaClient
public class OAuthJwtApp {
	
	public static void main(String[] args) {
		SpringApplication.run(OAuthJwtApp.class,args);		
	}
	

}
