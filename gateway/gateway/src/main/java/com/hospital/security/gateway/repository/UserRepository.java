package com.hospital.security.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.security.gateway.model.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Long>{

	AppUser findByEmailId(String username);
	
}
