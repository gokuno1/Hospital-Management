package com.example.demo.user.modelVo;

import java.io.Serializable;
import java.security.Principal;

public class UserDto  implements Principal, Serializable {

//	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
//	private static final long serialVersionUID = 9057092956206429816L;

	public UserDto(String username, String password) {
		this.username = username;
		this.Password = password;
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
		// TODO Auto-generated method stub
		return this.getUsername();
	}

}
