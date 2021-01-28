package com.example.appointment.model.vo;

import java.util.List;

public class UserPrescriptionDetails {
	
	private String patientName;
	private String doctorName;
	private int patientAge;
	private List<PrescriptionVO> prescriptionList;
	
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public List<PrescriptionVO> getPrescriptionList() {
		return prescriptionList;
	}
	public void setPrescriptionList(List<PrescriptionVO> prescriptionList) {
		this.prescriptionList = prescriptionList;
	}
	
	
	
	

}
