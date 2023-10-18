package com.loginsystembackend.loginsystem.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loginsystembackend.loginsystem.entity.User;
import com.loginsystembackend.loginsystem.repository.UserRepository;

@Service
public class UserDetails implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(username,username);
		if(user==null) {
			throw new UsernameNotFoundException("User not exists");
		}
		Set<GrantedAuthority> authorities = user.getRoles().stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet()); 
													
				return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
	}
	}
	
	


