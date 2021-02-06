package com.example.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dashboard.controller.AppointmentFeignController;
import com.example.dashboard.modelVo.DashboardData;
import com.example.dashboard.modelVo.DashboardDetailsVO;


@Service
public class DashboardServiceImpl implements DashboardService{
	
	@Autowired
	private AppointmentFeignController appointmentFeignController ;

	@Override
	public DashboardDetailsVO appointmentOverview(DashboardData details) {
		// TODO Auto-generated method stub
		DashboardDetailsVO dashboard = appointmentFeignController.getAppointmentOverview(details.getDoctorEmail());
		return dashboard;
	}

}
