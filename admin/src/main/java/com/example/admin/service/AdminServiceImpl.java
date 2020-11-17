package com.example.admin.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.admin.model.AdminModel;
import com.example.admin.repository.AdminRepository;

public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminRepository adminRepository;

	@Override
	public AdminModel addNewDoctor(AdminModel adminModel) {
		// TODO Auto-generated method stub
		AdminModel model = new AdminModel();
		model.setClinicAddress(adminModel.getClinicAddress());
		model.setDoctorAge(adminModel.getDoctorAge());
		model.setDoctorContact(adminModel.getDoctorContact());
		model.setDoctorEmail(adminModel.getDoctorEmail());
		model.setDoctorName(adminModel.getDoctorName());
		model.setDoctorSpeciality(adminModel.getDoctorSpeciality());
		model.setExperience(adminModel.getDoctorAge());
		adminRepository.save(model);
		return model;
	}

	@Override
	public String removeDoctor(String contactNo) {
		// TODO Auto-generated method stub
		adminRepository.deleteBycontactName(contactNo);
		return null;
	}

}
