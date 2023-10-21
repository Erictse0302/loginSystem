package com.loginsystembackend.loginsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.loginsystembackend.loginsystem.entity.Role;
import com.loginsystembackend.loginsystem.repository.RoleRepository;

@SpringBootApplication
public class LoginsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginsystemApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(RoleRepository roleRepository) {
		return (args)-> {
			Role role = new Role();
			role.setName("ROLE_ADMIN");
			roleRepository.save(role);
		};
		
	}
}
