package com.example.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.admin.intercomm.UserFeignController;
import com.example.admin.modelVo.DoctorInfoVO;

@Service
public class AdminServiceImpl implements AdminService{
	
//	@Autowired
//	private AdminRepository adminRepository;

	@Autowired
	private UserFeignController userFeignController;

	@Override
	public String removeDoctor(String contactNo) {
		// TODO Auto-generated method stub
		String data = userFeignController.deleteDoctor(contactNo);
		return data;
	}
	
	@Override
	public String addNewDoctor(DoctorInfoVO adminModel) {
		// TODO Auto-generated method stub
		String response = userFeignController.addNewDoctor(adminModel);
		return response;
	}

	public DoctorInfoVO searchDoctorByEmail(String emailId)
	{
		DoctorInfoVO getDoc = userFeignController.searchDoctor(emailId);
		return getDoc;
	}
	
	public List<DoctorInfoVO> getAllDoctor()
	{
		List<DoctorInfoVO> getAll = userFeignController.getAllDoctors();
		return getAll;
	}
}
