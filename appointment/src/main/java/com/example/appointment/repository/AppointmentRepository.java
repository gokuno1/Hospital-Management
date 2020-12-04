package com.example.appointment.repository;

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
}