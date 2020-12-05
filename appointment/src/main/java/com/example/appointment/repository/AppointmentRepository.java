package com.example.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.appointment.model.AppointmentModel;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentModel, Integer>{

	@Query(value = "select * from appointmentservice.appointmentdetails where doctor_name=?1", nativeQuery = true)
	public AppointmentModel findByDoctorName(String doctorName);
	
	@Query(value = "select * from appointmentservice.appointmentdetails where appointment_id=?1", nativeQuery = true)
	public AppointmentModel findByDoctorName(int appointmentId);
	
	@Query(value = "select * from appointmentservice.appointmentdetails where patient_email=?1 and appointment_status='ACCEPTED'", nativeQuery = true)
	public List<AppointmentModel> findByPatientEmail(String patientEmail);
	
	@Query(value = "select * from appointmentservice.appointmentdetails where patient_mobile=?1 and appointment_status='ACCEPTED'", nativeQuery = true)
	public List<AppointmentModel> findByPatientMobile(String patientMobile);
	
	@Query(value = "select * from appointmentservice.appointmentdetails where doctor_email=?1", nativeQuery = true)
    public List<AppointmentModel> findByDoctorEmail(String doctorEmail);
	
	@Query(value = "select * from appointmentservice.appointmentdetails where doctor_mobile=?1", nativeQuery = true)
	public List<AppointmentModel> findByDoctorMobile(String doctorMobile);
}
