package com.example.demo.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserLoginResponse {
	
	private String email;
	private String sessionId;
//	private int user_id;
	private String name;
	@JsonIgnore
	private String status;
	
	public UserLoginResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/*
	 * public int getUser_id() { return user_id; }
	 * 
	 * public void setUser_id(int user_id) { this.user_id = user_id; }
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	 
	

}
