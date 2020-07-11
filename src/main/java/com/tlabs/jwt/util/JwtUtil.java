package com.tlabs.jwt.util;

import java.time.Instant;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	public String generateToken(UserDetails userDetails) {
		Claims claims = Jwts.claims();
		String token = createToken(claims, userDetails.getUsername());
		return token;
	}

	private String createToken(Claims claims, String username) {
		Instant instant = Instant.now();
		claims.put("setBy", "Raghav1080");
		final String token = Jwts.builder().setSubject(username).setExpiration(Date.from(instant.plusSeconds(60 * 60)))
				.setIssuedAt(Date.from(instant)).addClaims(claims).signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
		return token;
	}

	public String extractUserName(String jwt) {
		Claims claims = decodeJWT(jwt);
		return claims.getSubject();
	}
	
	public boolean validateToken(String jwt){
		Claims claims = decodeJWT(jwt);
		if(claims.get("setBy").equals("Raghav1080") && isExpired(claims.getExpiration())){
			
		}
		return false;
	}

	private boolean isExpired(Date expiration) {
		return expiration.compareTo(new Date()) < 0;
	}

	public Claims decodeJWT(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
		return claims;
	}
	
}
