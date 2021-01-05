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
	
	@PostMapping(value = "/addDoctor")
	public String addDoctor(DoctorInfoVO model)
	{
		String admin = adminService.addNewDoctor(model);
		return admin;
	}
	
	@DeleteMapping(value = "/deleteDoctor")
	public void deleteDoctor(@RequestHeader String emailId)
	{
		adminService.removeDoctor(emailId);
	}
	
	@GetMapping(value = "/searchDoctor")
	public DoctorInfoVO searchDoctor(@RequestBody String emailId)
	{
	//	ResponseEntity<DoctorInfo> resp = null;
		DoctorInfoVO userdetails = adminService.searchDoctorByEmail(emailId);
	//	resp = ResponseUtils.getOKResponse(userdetails);
		return userdetails;
	}

	@GetMapping(value = "/getAllDoctor")
	public List<DoctorInfoVO> getAllDoctor()
	{
		List<DoctorInfoVO> getAll = adminService.getAllDoctor();
		return getAll;
	}
}
