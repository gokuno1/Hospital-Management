package com.hospital.security.gateway.service;

import java.util.List;

import com.hospital.security.gateway.model.AppUser;
import com.hospital.security.gateway.model.Role;

public interface AppUserService {

	AppUser saveUser(AppUser user);
	Role saveRole(Role role);
	void addRoleToUser(String emailId, String roleName);
	AppUser getUser(String emailId);
	List<AppUser> getUsers();
}

