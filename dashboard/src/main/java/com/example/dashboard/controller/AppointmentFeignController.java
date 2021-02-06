package com.example.dashboard.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.dashboard.modelVo.DashboardDetailsVO;

@FeignClient("appointment-service")
public interface AppointmentFeignController {
	
	@GetMapping(value = "/getAppointmentOverview")
	public DashboardDetailsVO getAppointmentOverview(@RequestHeader String doctorEmail);

}
