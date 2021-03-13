package com.example.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dashboard.modelVo.DashboardData;
import com.example.dashboard.modelVo.DashboardDetailsVO;
import com.example.dashboard.service.DashboardServiceImpl;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private DashboardServiceImpl dashboardServiceImpl; 
	
	@GetMapping(value = "/getAppointmentOverview")
	public DashboardDetailsVO getAppointmentOverview(@RequestHeader DashboardData info)
	{
		DashboardDetailsVO dashboard = dashboardServiceImpl.appointmentOverview(info);
		return dashboard;
	}

}
