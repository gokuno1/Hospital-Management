package com.example.demo.user.model;

import java.io.Serializable;
import java.security.Principal;

public class UserDTO implements Principal, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	
	
	
	public UserDTO(String email) {
		super();
		this.email = email;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}

}
