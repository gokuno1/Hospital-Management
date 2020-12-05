package com.example.appointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointment.model.AppointmentModel;
import com.example.appointment.model.AppointmentModelVo;
import com.example.appointment.service.AppointmentServiceImpl;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	AppointmentServiceImpl appointmentService;
	
	@PostMapping(value = "/bookAppointment")
	public AppointmentModel bookAppointment(@RequestBody AppointmentModelVo appointment)
	{
		AppointmentModel details = appointmentService.bookAppointment(appointment);
		return details;
	}

	@PostMapping(value = "/acceptRejectAppointment")
	public AppointmentModel acceptRejectAppointment(@RequestBody AppointmentModel appointment)
	{
		AppointmentModel details = appointmentService.acceptRejectAppointment(appointment);
		return details;
	}
	
	@PostMapping(value = "/cancelOrRescheduleAppointment")
	public String cancelRescheduleAppointment(@RequestBody AppointmentModel appointment)
	{
		String details = appointmentService.cancelAppointment(appointment);
		return details;
	}
	
	@GetMapping(value = "/getAllAppointments")
	public List<AppointmentModel> getAllAppointments(@RequestHeader String doctorName)
	{
		List<AppointmentModel> list = appointmentService.getAllAppointmentsByDoctorName(doctorName);
		return list;
	}
	
	@GetMapping(value = "/getAllAppointmentBypatient")
	public List<AppointmentModel> getAllAppointmentBypatient(@RequestHeader String patientEmail, @RequestHeader String mobile)
	{
		List<AppointmentModel> list = appointmentService.getAppointmentsByPatient(patientEmail, mobile);
		return list;
	}
}
