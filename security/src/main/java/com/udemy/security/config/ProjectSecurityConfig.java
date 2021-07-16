package com.udemy.security.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//	    http.authorizeRequests((requests) -> requests.antMatchers("/hello").authenticated());
//	    http.authorizeRequests((requests) -> requests.antMatchers("/mello").permitAll());
//	    http.authorizeRequests((requests) -> requests.antMatchers("/jello").permitAll());
	    
//		http.authorizeRequests().antMatchers("/hello").authenticated()
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
//				.antMatchers("/hello").hasAuthority("WRITE")
//				.antMatchers("/mello").hasAuthority("READ")
//				.antMatchers("/jello").permitAll()
				.antMatchers("/hello").hasRole("ADMIN")
				.antMatchers("/mello").hasAuthority("USER")
				.antMatchers("/jello").permitAll()
		.and()							
		.formLogin().and()
		.httpBasic();
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication().withUser("admin").password("12345").authorities("admin").and().withUser("user").password("12345").authorities("read").and().passwordEncoder(NoOpPasswordEncoder.getInstance());
//	}
	
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		InMemoryUserDetailsManager userDetails = new InMemoryUserDetailsManager();
//		UserDetails user = User.withUsername("admin").password("12345").authorities("admin").build();
//		UserDetails user1 = User.withUsername("user").password("12345").authorities("read").build();
//		userDetails.createUser(user);
//		userDetails.createUser(user1);
//		auth.userDetailsService(userDetails); 
//			
//	}
//	
//	@Bean
//	public UserDetailsService userDetailsService(DataSource dataSource) {
//		return new JdbcUserDetailsManager(dataSource);
//	}	
//	
	@Bean
	public PasswordEncoder passwordEcoder()
	{
		return new BCryptPasswordEncoder();//1O ROUNDS BCRYPT
	}
	
	
	
	
	
	
	
	
	
	
}
