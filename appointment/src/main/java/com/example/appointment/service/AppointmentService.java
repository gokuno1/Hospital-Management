package com.example.appointment.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.appointment.model.AppointmentModel;
import com.example.appointment.model.AppointmentModelVo;
import com.example.appointment.model.vo.PrescriptionVO;
import com.example.appointment.model.vo.UserPrescriptionDetails;

public interface AppointmentService {

	public AppointmentModel bookAppointment(AppointmentModelVo bookingDetails);
	public AppointmentModel updateAppointment(int appointmentId, String status, String prescription, Boolean followUp);
	public List<AppointmentModel> getPendingAppointments(String doctorEmail);
	public List<AppointmentModel> getTodaysAppointment(String doctorEmail);
	public List<AppointmentModel> getPreviousAppointmentsForPatient(String doctorEmail, String patientEmail, int pageNo);
	public String getPrescriptionPdf(UserPrescriptionDetails details, HttpServletResponse response);
	
}

