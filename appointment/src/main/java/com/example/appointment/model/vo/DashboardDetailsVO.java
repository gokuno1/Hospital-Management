package com.example.appointment.model.vo;

public class DashboardDetailsVO {
	
	private int pendingAppointments;
	private int todayAppointment;
	private int appointmentForWeek;
	
	public int getPendingAppointments() {
		return pendingAppointments;
	}
	public void setPendingAppointments(int pendingAppointments) {
		this.pendingAppointments = pendingAppointments;
	}
	public int getTodayAppointment() {
		return todayAppointment;
	}
	public void setTodayAppointment(int todayAppointment) {
		this.todayAppointment = todayAppointment;
	}
	public int getAppointmentForWeek() {
		return appointmentForWeek;
	}
	public void setAppointmentForWeek(int appointmentForWeek) {
		this.appointmentForWeek = appointmentForWeek;
	}
	
	

}
