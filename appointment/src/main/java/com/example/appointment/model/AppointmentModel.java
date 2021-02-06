package com.example.appointment.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appointmentdetails")
public class AppointmentModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_id")
	private int appointmentId;
	
	@Column(name = "doctor_email")
	private String doctorEmail;
	
	@Column(name = "patient_email")
	private String patientEmail;
	
	@Column(name = "appointment_date")
	private Date appointmentDate;
	
	@Column(name = "booking_date")
	private Date bookingDate;
	
	@Column(name = "appointment_status")
	private String status;
	
	@Column(name = "disease")
	private String disease;
	
	@Column(name = "prescription")
	private String prescription;
	
	@Column(name = "isFollowUp")
	private Boolean followUp;
	
	public AppointmentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getDoctorEmail() {
		return doctorEmail;
	}
	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}
	public String getPatientEmail() {
		return patientEmail;
	}
	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
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
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	

	
		

}
