package com.example.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.admin.intercomm.UserFeignController;
import com.example.admin.model.AdminModel;
import com.example.admin.modelVo.DoctorInfoVO;
import com.example.admin.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private UserFeignController userFeignController;

	@Override
	public String removeDoctor(String contactNo) {
		// TODO Auto-generated method stub
		adminRepository.deleteBycontactName(contactNo);
		return null;
	}



	@Override
	public String addNewDoctor(DoctorInfoVO adminModel) {
		// TODO Auto-generated method stub
		String response = userFeignController.addNewDoctor(adminModel);
		return response;
	}

}
