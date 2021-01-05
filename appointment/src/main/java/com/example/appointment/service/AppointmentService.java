package com.example.appointment.service;

import java.util.List;

import com.example.appointment.model.AppointmentModel;
import com.example.appointment.model.AppointmentModelVo;

public interface AppointmentService {

	public AppointmentModel bookAppointment(AppointmentModelVo bookingDetails);
	public AppointmentModel updateAppointment(int appointmentId, String status, String prescription, Boolean followUp);
	public List<AppointmentModel> getPendingAppointments(String doctorEmail);
	public List<AppointmentModel> getTodaysAppointment(String doctorEmail);
	public List<AppointmentModel> getPreviousAppointmentsForPatient(String doctorEmail, String patientEmail, int pageNo);
	
}

