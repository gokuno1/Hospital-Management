package com.example.demo.user.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userprofile")
public class UserProfile {
	
	
	
	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	private int user_id;
	
	
	@OneToOne(mappedBy = "user_id",fetch = FetchType.EAGER)
	@JoinColumn(name= "user_id")
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getEmail_id() {
		return email_id;
	}
	
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	
	public String getFirst_Name() {
		return first_Name;
	}
	
	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}
	
	public String getLast_Name() {
		return last_Name;
	}
	
	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}
	
	public double getMobile_no() {
		return mobile_no;
	}
	
	public void setMobile_no(double mobile_no) {
		this.mobile_no = mobile_no;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	private String email_id;
	private String first_Name;
	private String last_Name;
	private double mobile_no;
	private int age;
	private String birthDate;
	

}
