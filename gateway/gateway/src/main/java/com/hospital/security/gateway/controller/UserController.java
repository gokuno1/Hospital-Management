package com.hospital.security.gateway.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.security.gateway.model.AppUser;
import com.hospital.security.gateway.model.Role;
import com.hospital.security.gateway.service.AppUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gateway")
@Slf4j
public class UserController {
	
	//keep secret in secure storage.
	
	private final AppUserService appUserService;
	
	@GetMapping("/users")
	public ResponseEntity<List<AppUser>> getUser(){
		return ResponseEntity.ok().body(appUserService.getUsers());
	}
	
	@PostMapping("/users/save")
	public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
		return ResponseEntity.created(uri).body(appUserService.saveUser(user));
	}
	
	@PostMapping("/users/role")
	public ResponseEntity<Role> saveUserRole(@RequestBody Role user){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/gateway/users/role").toUriString());
		return ResponseEntity.created(uri).body(appUserService.saveRole(user));
	}
	
	@PostMapping("/role")
	public ResponseEntity<?> addUserRole(@RequestBody RoleToUserData user){
		appUserService.addRoleToUser(user.getEmailId(), user.getRoleName());
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/refresh-token")
	public void getRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String authHeader = request.getHeader("Authorization");
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			try {
				String requestRefreshToken = authHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedToken = verifier.verify(requestRefreshToken);
				String username = decodedToken.getSubject();
				AppUser user = appUserService.getUser(username);
				String accessToken = JWT.create().withSubject(user.getEmailId())
						.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
						.withIssuer(request.getRequestURI())
						.withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
						.sign(algorithm);
				Map<String, String> token = new HashMap<>();
				token.put("accessToken", accessToken);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), token);
			} catch (IllegalArgumentException | JWTVerificationException e) {
				log.error("Error logging in",e.getMessage());
				response.setHeader("Error", e.getMessage());
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			} 				
		}else {
			throw new RuntimeException("Refresh token is missing");
		}
	}
	
}

