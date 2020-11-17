package com.example.admin.service;

import com.example.admin.model.AdminModel;

public interface AdminService {
	
	public AdminModel addNewDoctor(AdminModel adminModel);
	public String removeDoctor(String contactNo);

}
