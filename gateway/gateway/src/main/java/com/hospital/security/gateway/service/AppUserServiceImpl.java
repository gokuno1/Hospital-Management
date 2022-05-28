package com.hospital.security.gateway.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital.security.gateway.model.AppUser;
import com.hospital.security.gateway.model.Role;
import com.hospital.security.gateway.repository.RoleRepository;
import com.hospital.security.gateway.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppUserService, UserDetailsService{
	
	private final UserRepository userRepository;
	
	private final RoleRepository roleRepository;
	
	private final PasswordEncoder passwordEncoder;

	@Override
	public AppUser saveUser(AppUser user) {
		log.info("saving user");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("saving role");
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String emailId, String roleName) {
		AppUser user = userRepository.findByEmailId(emailId);
		Role role = roleRepository.findByName(roleName);
		user.getRoles().add(role);
	}

	@Override
	public AppUser getUser(String emailId) {
		return userRepository.findByEmailId(emailId);
	}

	@Override
	public List<AppUser> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepository.findByEmailId(username);
		if(user==null) {
			log.error("User name not found");
			throw new UsernameNotFoundException("User name not found");
		}else {
			log.info("User name found");	
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role->		
			authorities.add(new SimpleGrantedAuthority(role.getName()))
		);
		return new User(user.getEmailId(), user.getPassword(), authorities);
	}

}
