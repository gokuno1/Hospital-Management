package com.hospital.security.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.security.gateway.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);
	
}
