package com.loginsystembackend.loginsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loginsystembackend.loginsystem.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findFirstByName(String name);
}