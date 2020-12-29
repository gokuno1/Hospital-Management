package com.example.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.modelVo.DoctorInfoVO;
import com.example.admin.service.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminServiceImpl adminService;
	
	@PostMapping(value = "/addDoctor")
	public String addDoctor(DoctorInfoVO model)
	{
		String admin = adminService.addNewDoctor(model);
		return admin;
	}
	
	@DeleteMapping(value = "/deleteDoctor")
	public void deleteDoctor(@RequestHeader String contactNo)
	{
		adminService.removeDoctor(contactNo);
	}

}
