package com.example.appointment.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.appointment.model.AppointmentModel;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentModel, Integer>{

	@Query(value = "select * from appointment_management.appointmentdetails where appointment_id=?1", nativeQuery = true)
	public AppointmentModel findByDoctorName(int appointmentId);
	
	@Query(value = "select * from appointment_management.appointmentdetails where patient_email=?1 and appointment_status='ACCEPTED'", nativeQuery = true)
	public List<AppointmentModel> findByPatientEmail(String patientEmail);
	
	@Query(value = "select * from appointment_management.appointmentdetails where appointment_id=?1", nativeQuery = true)
	public AppointmentModel findByAppointmentId(int id);
	
	@Query(value = "select * from appointment_management.appointmentdetails where doctor_email=?1", nativeQuery = true)
    public List<AppointmentModel> findByDoctorEmail(String doctorEmail);
	
	@Query(value = "select * from appointment_management.appointmentdetails where doctor_email=?1 and appointment_status='PENDING'", nativeQuery = true)
	public List<AppointmentModel> getAllPendingAppointments(String doctorEmail);
	
	@Query(value = "select * from appointment_management.appointmentdetails where doctor_email=?1 and date=CURDATE()", nativeQuery = true)
	public List<AppointmentModel> getAllTodaysAppointments(String doctorEmail);
	
	@Query(value = "select * from appointment_management.appointmentdetails where doctor_email=?1 and patient_email=?2 and appointment_status='COMPLETED'",countQuery="select count(*) from appointment_management.appointmentdetails where doctor_email=?1 and patient_email=?2 and appointment_status='COMPLETED'", nativeQuery = true)
	public Page<AppointmentModel> getAllPreviousAppointments(String doctorEmail, String patientEmail, Pageable page);
	
//	@Query(value = "select distinct CO_ID, rownum, ORDER_PLACED, ORDER_STATUS, SHIP_TO_ID, SHIP_TO_NAME, SAP_ITEM_ID, REC_PLANT_DESC, CUST_ORG_ID, CUST_NAME, PGI_NO, CARRIER_VEH_NO, 
	//SCHEDULE_NO, DI_NO, SAP_NUM, TRANSPORTER_NAME, CHALLAN_NUMBER, INVOICE_DATE, INVOICE_NO, DI_DATE, DRIVER_MOB_NO, SHIP_FROM_ID, SHIP_FROM_NAME, ORIG_REQ_QTY, DELIVERY_TERMS, DELIVERY_TERM_CODE, 
	//DELIVERY_PERIOD, DELIVERY_PERIOD_CODE, DELIVERY_DATE, PRODUCT_STATE, SHIP_TO_ADDR1, SHIP_TO_ADDR2, SHIP_TO_ADDR3, TRUCK_TYPE, SPECIAL_INSTRUCTION, RETAILER1_QTY, RETAILER2_QTY, RETAILER3_QTY, 
	//RETAILER4_QTY, ORIG_REQ_DATE, RETAILER_CODE, RETAILER2, RETAILER3, RETAILER4, MOBILE, GPS_STATUS, ETA, DRIVER_NAME, DRIVER_MOBILE_NO, ORDER_QTY, TRUCK_ASSIGNED_DATE, SOURCE_ALOCATED_DATE, DISPATCHED_DATE, 
	//DELIVERY_MOB_NO, ORDER_ACKNOWLEDGED, DELIVERY_NAME, PIN_CODE, DISPATCH_QTY, ENTITY,RET1_NAME,RET2_NAME,RET3_NAME,RET4_NAME,PRODUCT_ALIAS,INCO_TERM from {h-schema}vw_order_detail_status_7_days WHERE CUST_ORG_ID in ?1 and ORDER_STATUS = 'PENDING ACKNOWLEDEMENT' ", 
	//countQuery="select count(*) from {h-schema}vw_order_detail_status_7_days WHERE CUST_ORG_ID in ?1 and ORDER_STATUS = 'PENDING ACKNOWLEDEMENT'", nativeQuery = true)
//	public Page<OrderDispatched> findAllPendingAckOrderByCustomerId(List<String> userId,Pageable page);

}
