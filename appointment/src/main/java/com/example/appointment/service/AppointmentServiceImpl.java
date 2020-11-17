package com.example.appointment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appointment.intercomm.UserFeignController;
import com.example.appointment.model.AppointmentModel;
import com.example.appointment.model.AppointmentModelVo;
import com.example.appointment.model.vo.UserProfileVo;
import com.example.appointment.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
	AppointmentRepository  appointmentRepository; 
	
	@Autowired
	UserFeignController userFeignController;

	@Override
	public AppointmentModel bookAppointment(AppointmentModelVo bookingDetails) {
		// TODO Auto-generated method stub
		AppointmentModel details = new AppointmentModel();
		UserProfileVo user = userFeignController.viewProfile(bookingDetails.getPatientEmail());
		details.setAppointmentDate(bookingDetails.getAppointmentDate());
		details.setDoctorName(bookingDetails.getDoctorName());
		details.setPatientName(user.getFirst_Name()+" "+user.getLast_Name());
		details.setPatientAge(user.getAge());
		details.setPatientBirthDate(user.getBirthDate());
		details.setPatientEmail(bookingDetails.getPatientEmail());
		details.setStatus("PENDING");
		appointmentRepository.save(details);
		return details;
	}

	@Override
	public AppointmentModel acceptRejectAppointment(AppointmentModel bookingDetails) {
		// TODO Auto-generated method stub
		AppointmentModel accept = appointmentRepository.findByDoctorName(bookingDetails.getDoctorName());
		if(bookingDetails.getStatus().equalsIgnoreCase("ACCEPTED"))
		{
			accept.setStatus("ACCEPTED");
		}
		else
		{
			accept.setStatus("REJECTED");
		}
		
		appointmentRepository.save(accept);
		
		return accept;
	}

	@Override
	public String cancelAppointment(AppointmentModel bookingDetails) {
		AppointmentModel accept = appointmentRepository.findByDoctorName(bookingDetails.getAppointmentId());
		if(bookingDetails.getStatus().equalsIgnoreCase("ACCEPTED"))
		{
			accept.setStatus("PENDING");
			accept.setAppointmentDate(bookingDetails.getAppointmentDate());
			appointmentRepository.save(accept);
			return "Your appointment has been rescheduled.";
		}
		else 
		{
			accept.setStatus("CANCELLED");
			appointmentRepository.save(accept);
			return "Appointment has been cancelled";
		}	
	}

	@Override
	public List<AppointmentModel> getAllAppointmentsByDoctorName(String doctorName) {
		// TODO Auto-generated method stub
		List<AppointmentModel> allAppointments = new ArrayList<>();
		AppointmentModel accept = appointmentRepository.findByDoctorName(doctorName);
		allAppointments.add(accept);
		return allAppointments;
	}
	
	

}
