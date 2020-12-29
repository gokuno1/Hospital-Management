package com.example.demo.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.model.DoctorInfo;

@Repository
public interface DoctorRepository extends CrudRepository<DoctorInfo, Integer> {
	
	@Query(value = "SELECT * FROM DOCTOR_DETAILS WHERE EMAIL_ID=?1", nativeQuery = true)
	public DoctorInfo findByEmail(String doctorEmail);
	
	@Query(value = "SELECT * FROM DOCTOR_DETAILS WHERE MOBILE_NO=?1", nativeQuery = true)
	public DoctorInfo findByMobileNo(String mobileNo);
	
}
