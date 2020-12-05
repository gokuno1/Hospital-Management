package com.example.appointment.service;

import java.util.List;

import com.example.appointment.model.AppointmentModel;
import com.example.appointment.model.AppointmentModelVo;

public interface AppointmentService {

	public AppointmentModel bookAppointment(AppointmentModelVo bookingDetails);
	public AppointmentModel acceptRejectAppointment(AppointmentModel bookingDetails);
	public String cancelAppointment(AppointmentModel bookingDetails);
	public List<AppointmentModel> getAllAppointmentsByDoctorName(String doctorName);
	public List<AppointmentModel> getAppointmentsByPatient(String patientEmail, String patientMobile);
	
}

