package com.example.appointment.model;

import java.sql.Date;

public class AppointmentModelVo {
	
	private String patientEmail;
	private Date appointmentDate;
    private String doctorEmail;
	private String disease;
	private String prescription;
	private Boolean followUp;
	
	public String getPatientEmail() {
		return patientEmail;
	}
	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}
	public String getDoctorEmail() {
		return doctorEmail;
	}
	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public Boolean getFollowUp() {
		return followUp;
	}
	public void setFollowUp(Boolean followUp) {
		this.followUp = followUp;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	

}
