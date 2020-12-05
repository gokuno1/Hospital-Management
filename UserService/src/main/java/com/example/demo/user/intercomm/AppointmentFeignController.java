package com.example.demo.user.intercomm;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.demo.user.modelVo.AppointmentModelVo;

@FeignClient(value = "appointment-service")
public interface AppointmentFeignController {
	
	@GetMapping(value = "/appointment/getAllAppointmentBypatient")
	public List<AppointmentModelVo> getAllAppointmentBypatient(@RequestHeader String patientEmail, @RequestHeader String mobile);

}
