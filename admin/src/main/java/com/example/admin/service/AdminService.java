package com.example.admin.service;

import com.example.admin.modelVo.DoctorInfoVO;

public interface AdminService {
	
	public String addNewDoctor(DoctorInfoVO adminModel);
	public String removeDoctor(String contactNo);

}
