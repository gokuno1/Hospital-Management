package com.example.appointment.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointment.model.AppointmentModel;
import com.example.appointment.model.AppointmentModelVo;
import com.example.appointment.model.vo.DashboardDetailsVO;
import com.example.appointment.model.vo.UserPrescriptionDetails;
import com.example.appointment.service.AppointmentServiceImpl;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	AppointmentServiceImpl appointmentService;
	
	@PostMapping(value = " ")
	public AppointmentModel bookAppointment(@RequestBody AppointmentModelVo appointment)
	{
		return appointmentService.bookAppointment(appointment);
	}
	
	@PostMapping(value = "/update")
	public AppointmentModel updateAppointment(@RequestHeader int appointmentId, 
			@RequestParam(required = false) Optional<String> status, 
			@RequestParam(required = false) Optional<String> prescription,
			@RequestParam(required = false) Optional<Boolean> followUp)
	{
	   return appointmentService.updateAppointment(appointmentId, status.orElse(null), prescription.orElse(null), followUp.orElse(false));
	}
	
	@GetMapping(value = "/todays")
	public List<AppointmentModel> getAllAppointments(@RequestHeader String doctorName)
	{
		return appointmentService.getTodaysAppointment(doctorName);
	}
	
	@GetMapping(value = "/previous")
	public List<AppointmentModel> getAllAppointmentBypatient(@RequestHeader String patientEmail, @RequestHeader String doctorEmail, @RequestHeader int pageNo)
	{
		return appointmentService.getPreviousAppointmentsForPatient(doctorEmail, patientEmail, pageNo);
	}
	
	@GetMapping(value = "/pending")
	public List<AppointmentModel> getAllPendingAppointments(@RequestHeader String doctorName)
	{
		return appointmentService.getPendingAppointments(doctorName);
	}
	
	@PostMapping(value = "/download-prescription")
	public String getPrescriptionPdf(@RequestBody UserPrescriptionDetails details, HttpServletResponse response) {
		
		return appointmentService.getPrescriptionPdf(details, response);
		
	}
	
	@GetMapping(value = "/overview")
	public DashboardDetailsVO getAppointmentOverview(@RequestHeader String doctorEmail)
	{
		return appointmentService.appointmentDetails(doctorEmail);
	}
}
