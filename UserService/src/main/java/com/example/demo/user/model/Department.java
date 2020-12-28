package com.example.demo.user.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DeaprtmentDetails")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DEPARTMENT_NO")
	private int departmentNo;
	
	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "USER_ID")
	private UserInfo doctorId;
	
	public int getDepartmentNo() {
		return departmentNo;
	}
	public void setDepartmentNo(int departmentNo) {
		this.departmentNo = departmentNo;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public UserInfo getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(UserInfo doctorId) {
		this.doctorId = doctorId;
	}

}
