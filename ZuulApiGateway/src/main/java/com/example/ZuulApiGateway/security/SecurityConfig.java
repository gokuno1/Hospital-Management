package com.example.ZuulApiGateway.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
				.antMatchers("/users/*").hasRole("ADMIN")
				.antMatchers("/mello").hasAuthority("USER")
				.antMatchers("/jello").permitAll()
		.and()							
		.formLogin().and()
		.httpBasic();
	}
	
}
