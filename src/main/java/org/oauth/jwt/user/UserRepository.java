package org.oauth.jwt.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(nativeQuery = true, value = "select * from user where email= :email")
	public User findByEmail(@Param("email") String email);
		 
}
