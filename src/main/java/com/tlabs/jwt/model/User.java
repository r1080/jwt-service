package com.tlabs.jwt.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
	
	private UserDetail userDetail;
	
	public User() {
		
	}
	
	public User(UserDetail userDetail){
		this.userDetail = userDetail;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1393910377224336185L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String roles = userDetail.getRole();
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(String role : roles.split(",")){
			authorities.add(new SimpleGrantedAuthority(role));
		}
    	return authorities;
	}

	@Override
	public String getPassword() {
		return userDetail.getPassword();
	}

	@Override
	public String getUsername() {
		return userDetail.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
