package com.example.appointment.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointment.model.AppointmentModel;
import com.example.appointment.model.AppointmentModelVo;
import com.example.appointment.model.vo.DashboardDetailsVO;
import com.example.appointment.model.vo.PrescriptionVO;
import com.example.appointment.model.vo.UserPrescriptionDetails;
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
	
	@PostMapping(value = "/updateAppointment")
	public AppointmentModel updateAppointment(@RequestHeader int appointmentId, 
			@RequestParam(required = false) Optional<String> status, 
			@RequestParam(required = false) Optional<String> prescription,
			@RequestParam(required = false) Optional<Boolean> followUp)
	{
		AppointmentModel details = appointmentService.updateAppointment(appointmentId, status.orElse(null), prescription.orElse(null), followUp.orElse(false));
		return details;
	}
	
	@GetMapping(value = "/getAllTodaysAppointment")
	public List<AppointmentModel> getAllAppointments(@RequestHeader String doctorName)
	{
		List<AppointmentModel> list = appointmentService.getTodaysAppointment(doctorName);
		return list;
	}
	
	@GetMapping(value = "/getPreviousAppointmentOfPatient")
	public List<AppointmentModel> getAllAppointmentBypatient(@RequestHeader String patientEmail, @RequestHeader String doctorEmail, @RequestHeader int pageNo)
	{
		List<AppointmentModel> list = appointmentService.getPreviousAppointmentsForPatient(doctorEmail, patientEmail, pageNo);
		return list;
	}
	
	@GetMapping(value = "/getAllPendingAppointment")
	public List<AppointmentModel> getAllPendingAppointments(@RequestHeader String doctorName)
	{
		List<AppointmentModel> list = appointmentService.getPendingAppointments(doctorName);
		return list;
	}
	
	@PostMapping(value = "/downloadPrescription")
	public String getPrescriptionPdf(@RequestBody UserPrescriptionDetails details, HttpServletResponse response) {
		
		String data = appointmentService.getPrescriptionPdf(details, response);
		return data;
		
	}
	
	@GetMapping(value = "/getAppointmentOverview")
	public DashboardDetailsVO getAppointmentOverview(@RequestHeader String doctorEmail)
	{
		DashboardDetailsVO details = appointmentService.appointmentDetails(doctorEmail);
		return details;
	}
}
