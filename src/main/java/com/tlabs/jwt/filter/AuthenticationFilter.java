package com.tlabs.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tlabs.jwt.service.MyUserDetailService;
import com.tlabs.jwt.util.JwtUtil;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	@Autowired
	private MyUserDetailService myUserDetailService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		LOGGER.info("Inside Authentication Filter Before UserNamePassword Authentication");
		
		String header = request.getHeader("Authorization");
		String token = "";
		
		if((header != null && !header.isEmpty()) && header.startsWith("Bearer ")){
			
			token = header.substring(7);
			String tokenUserName = jwtUtil.extractUserName(token);
			
			final UserDetails userDetails = myUserDetailService.loadUserByUsername(tokenUserName);
			
			if(jwtUtil.validateToken(token)){
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));				
			}
			
			
		}
	
		filterChain.doFilter(request,response);
	}

}
