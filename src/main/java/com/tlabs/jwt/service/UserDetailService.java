package com.tlabs.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tlabs.jwt.model.User;
import com.tlabs.jwt.model.UserDetail;
import com.tlabs.jwt.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserDetail userDetail = userRepository.findByUserName(userName);
		if(userDetail == null){
			throw new UsernameNotFoundException("User Not Found!!");
		}
		System.out.println("--------------> Retrieved User " + userDetail);
		User user = new User(userDetail);
		return user;
	}
	
	public void addUser(UserDetail userDetail){
		UserDetail user = userRepository.save(userDetail);
		System.out.println("-----------------> User Saved:: " + user);
	}
	
	
	

}
