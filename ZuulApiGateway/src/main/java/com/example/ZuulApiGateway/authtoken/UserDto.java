package com.example.ZuulApiGateway.authtoken;

import java.io.Serializable;
import java.security.Principal;

public class UserDto implements Principal,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public UserDto(String userName,String password){
		this.username=userName;
		this.Password=password;
	}
	private String username;
	private String Password;
	
	
	public String getPassword() {
		return Password;
	}
 
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		Password = password;
	}

	@Override
	public String getName() {
		return this.getUsername();
	}

}
