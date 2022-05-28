package com.hospital.security.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hospital.security.gateway.filter.AuthorizationFilter;
import com.hospital.security.gateway.filter.CustomAuthFilter;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	// remove deprecated code and try latest supported code
	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
//		http.csrf().disable().authorizeRequests().antMatchers("/gateway/**").permitAll().anyRequest().authenticated();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
		http.authorizeRequests().antMatchers("/gateway/refresh?**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/gateway/**").hasAnyAuthority("ROLE_USER"); //only user role can access
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/gateway/**").hasAnyAuthority("ROLE_USER"); //only user role can access
		http.authorizeRequests().anyRequest().authenticated(); // after authentication
		http.addFilter(new CustomAuthFilter(authManagerBean()));// this class verifies email and password, generates access and refresh token and return. 
		http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);// this class checks if token is valid and authorizes the token.
	}
	
	@Bean
	public AuthenticationManager authManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}

}
