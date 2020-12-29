package com.example.demo.user.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DOCTOR_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DOCTOR_ID")
	private int doctorId;
	
	@Column(name = "DOCTOR_NAME")
	private String doctorName;
	
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "BIRTHDATE")
	private Date birthDate;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "SPECIALIZATION")
	private String specialization;
	
	@Column(name = "CHARGES_PER_VISIT")
	private double chargePerVisit;
	
	@Column(name = "WORK_EXPERIENCE")
	private int workExperience;
	
	@OneToOne(mappedBy = "doctorId")
	private Department userId;

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public double getChargePerVisit() {
		return chargePerVisit;
	}

	public void setChargePerVisit(double chargePerVisit) {
		this.chargePerVisit = chargePerVisit;
	}

	public int getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(int workExperience) {
		this.workExperience = workExperience;
	}

	public Department getUserId() {
		return userId;
	}

	public void setUserId(Department userId) {
		this.userId = userId;
	}
	
	

}
