package com.loginsystembackend.loginsystem.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginsystembackend.loginsystem.Dto.LoginDto;
import com.loginsystembackend.loginsystem.Dto.SignUpDto;
import com.loginsystembackend.loginsystem.entity.Role;
import com.loginsystembackend.loginsystem.entity.User;
import com.loginsystembackend.loginsystem.repository.RoleRepository;
import com.loginsystembackend.loginsystem.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class LoginController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<>("User Login successfully",HttpStatus.OK);
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
		if(userRepository.existsByUsername(signUpDto.getUsername())) {
			return new ResponseEntity<>("User is alreadly exists",HttpStatus.BAD_REQUEST);
		}
		
		if(userRepository.existsByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<>("Email is alreadly exists",HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setName(signUpDto.getName());
		user.setUsername(signUpDto.getUsername());
		
		user.setEmail(signUpDto.getEmail());
		
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		
		Role roles = roleRepository.findByName("ROLE_ADMIN").get();	
		
		user.setRoles(Collections.singleton(roles));
		
		userRepository.save(user);
		
		return new ResponseEntity<> ("User is Registered Sucessfully",HttpStatus.OK);
		
		
		
	}
	

}
