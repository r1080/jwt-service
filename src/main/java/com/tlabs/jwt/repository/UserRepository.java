package com.tlabs.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlabs.jwt.model.UserDetail;

public interface UserRepository extends JpaRepository<UserDetail,Integer> {
	
	public UserDetail findByUserName(String userName);
	
}
