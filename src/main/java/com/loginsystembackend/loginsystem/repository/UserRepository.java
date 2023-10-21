package com.loginsystembackend.loginsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loginsystembackend.loginsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsernameOrEmail(String username,String email);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
	
	

}
