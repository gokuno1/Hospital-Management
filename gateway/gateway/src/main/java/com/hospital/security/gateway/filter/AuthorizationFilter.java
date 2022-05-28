package com.hospital.security.gateway.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getServletPath().equals("/login") || request.getServletPath().equals("/gateway/refresh-token")) {
			filterChain.doFilter(request, response);
		}else {
			String authHeader = request.getHeader("Authorization");
			if(authHeader!=null && authHeader.startsWith("Bearer ")) {
				try {
					String token = authHeader.substring("Bearer ".length());
					Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
					JWTVerifier verifier = JWT.require(algorithm).build();
					DecodedJWT decodedToken = verifier.verify(token);
					String username = decodedToken.getSubject();
					String[] roles = decodedToken.getClaim("roles").asArray(String.class);
					Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
					Arrays.stream(roles).forEach(role->
						authorities.add(new SimpleGrantedAuthority(role))
					);
					UsernamePasswordAuthenticationToken authenticationToken = 
							new UsernamePasswordAuthenticationToken(username, null,authorities);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					filterChain.doFilter(request, response);
				} catch (IllegalArgumentException | JWTVerificationException e) {
					log.error("Error logging in",e.getMessage());
					response.setHeader("Error", e.getMessage());
					response.sendError(HttpServletResponse.SC_FORBIDDEN);
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				} 				
			}else {
				filterChain.doFilter(request, response);
			}
		}
		
	}
	
}
