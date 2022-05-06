package com.example.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping(value = "/doctor")
	public String addDoctor(DoctorInfoVO model)
	{
		return adminService.addNewDoctor(model);
	}
	
	@DeleteMapping(value = "/doctor")
	public void deleteDoctor(@RequestHeader String emailId)
	{
		adminService.removeDoctor(emailId);
	}
	
	@GetMapping(value = "/search-doctor")
	public DoctorInfoVO searchDoctor(@RequestBody String emailId)
	{
		return adminService.searchDoctorByEmail(emailId);
	}

	@GetMapping(value = "/doctors")
	public List<DoctorInfoVO> getAllDoctor()
	{
		return adminService.getAllDoctor();
	}
}
