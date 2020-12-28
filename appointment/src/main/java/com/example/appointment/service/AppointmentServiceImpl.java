package com.example.appointment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appointment.intercomm.UserFeignController;
import com.example.appointment.model.AppointmentModel;
import com.example.appointment.model.AppointmentModelVo;
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
		details.setAppointmentDate(bookingDetails.getAppointmentDate());
		details.setDisease(bookingDetails.getDisease());
		details.setDoctorEmail(bookingDetails.getDoctorEmail());
		details.setFollowUp(false);
		details.setPatientEmail(bookingDetails.getPatientEmail());
		details.setPrescription(null);
		details.setStatus("PENDING");
		appointmentRepository.save(details);
		return details;
	}

	@Override
	public AppointmentModel updateAppointment(int appointmentId, String status, String prescription, Boolean followUp) {
		// TODO Auto-generated method stub
		//AppointmentModel appointmentDetails = appointmentRepository.findByPatientAndDoctorEmail(patientEmail, doctorEmail);
		AppointmentModel appointmentDetails = appointmentRepository.findByAppointmentId(appointmentId);
		if(followUp!=null)
		{
			appointmentDetails.setFollowUp(true);
		}
		if(status!=null)
		{
			appointmentDetails.setStatus(status);
		}
		if(prescription!=null)
		{
			appointmentDetails.setPrescription(prescription);
		}
		
		appointmentRepository.save(appointmentDetails);
		return appointmentDetails;
	}

	@Override
	public List<AppointmentModel> getPendingAppointments(String doctorEmail) {
		// TODO Auto-generated method stub
		List<AppointmentModel> appointments = appointmentRepository.getAllPendingAppointments(doctorEmail);
		return appointments;
	}

	@Override
	public List<AppointmentModel> getTodaysAppointment(String doctorEmail) {
		// TODO Auto-generated method stub
		List<AppointmentModel> appointments = appointmentRepository.getAllTodaysAppointments(doctorEmail);
		return appointments;
	}

	@Override
	public List<AppointmentModel> getPreviousAppointmentsForPatient(String doctorEmail, String patientEmail) {
		// TODO Auto-generated method stub
		List<AppointmentModel> appointments = appointmentRepository.getAllPreviousAppointments(doctorEmail, patientEmail);
		return appointments;
	}

	

}
