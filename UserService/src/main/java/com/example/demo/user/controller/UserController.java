package com.example.demo.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.model.DoctorInfo;
import com.example.demo.user.model.SpringSession;
import com.example.demo.user.model.UserInfo;
import com.example.demo.user.modelVo.DoctorInfoVO;
import com.example.demo.user.modelVo.UserInfoVO;
import com.example.demo.user.modelVo.UserLoginResponse;
import com.example.demo.user.service.UserServiceImpl;
import com.example.demo.user.utils.ResponseUtils;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserServiceImpl service;
	
	@PostMapping(value="/authenticateUser")
	public ResponseEntity<UserLoginResponse> authenticateUser(@RequestBody UserInfoVO user, HttpServletRequest request, HttpServletResponse response)
	{
		ResponseEntity<UserLoginResponse> resp = null;
		UserLoginResponse res = service.authenticateUser(user, request, response);	
		  if(res!=null)
		  { 
			  resp=ResponseUtils.getOKResponse(res); 
			  return resp; 
		  } 
		  else 
		  {
		  resp=ResponseUtils.getUnAuthorizedResponse(res); 
		  return resp; 
		  }
		 
		
	}
	
	@PostMapping(value="/addNewUser")
	public ResponseEntity<String> addNewUser(@RequestBody UserInfoVO user)
	{
		ResponseEntity<String> resp = null;
		String res = service.addNewUser(user);
		if(res!=null)
		{
			resp=ResponseUtils.getBadRequestResponse(res);
			return resp;
		}
		else
		{
			resp=ResponseUtils.getOKResponse(res);
			return resp;
		}
		
	}
	
	@GetMapping(value = "/getProfileDetails")
	public ResponseEntity<UserInfoVO> getProfileDetails(@RequestParam String emailId)
	{
		ResponseEntity<UserInfoVO> resp = null;
		UserInfoVO userdetails = service.viewProfile(emailId);
		resp = ResponseUtils.getOKResponse(userdetails);
		return resp;
	}
	
	@PostMapping(value = "/forgotPassword")
	public ResponseEntity<String> forgotPassword(@RequestHeader String emailId, @RequestHeader String password)
	{
		ResponseEntity<String> resp = null;
		String responsePassword = service.forgotPassword(emailId, password);
		resp = ResponseUtils.getOKResponse(responsePassword);
		return resp;
	}
	
	@PostMapping(value="/addNewDoctor")
	public ResponseEntity<String> addNewDoctor(@RequestBody DoctorInfoVO doctor)
	{
		ResponseEntity<String> resp = null;
		String res = service.addNewDoctor(doctor);
		if(res!=null)
		{
			resp=ResponseUtils.getBadRequestResponse(res);
			return resp;
		}
		else
		{
			resp=ResponseUtils.getOKResponse(res);
			return resp;
		}
		
	}
	
	@GetMapping(value = "/getAllDoctors")
	public ResponseEntity<List<DoctorInfo>> getAllDoctors()
	{
		ResponseEntity<List<DoctorInfo>> resp = null;
		List<DoctorInfo> userdetails = service.getAllDoctors();
		resp = ResponseUtils.getOKResponse(userdetails);
		return resp;
	}
	
	@GetMapping(value = "/searchDoctor")
	public ResponseEntity<DoctorInfoVO> searchDoctor(@RequestBody String emailId)
	{
		ResponseEntity<DoctorInfoVO> resp = null;
		DoctorInfoVO userdetails = service.searchDoctorByEmail(emailId);
		resp = ResponseUtils.getOKResponse(userdetails);
		return resp;
	}
	
	@DeleteMapping(value = "/deleteDoctor")
	public ResponseEntity<String> deleteDoctor(@RequestBody String emailId)
	{
		ResponseEntity<String> resp = null;
		String userdetails = service.deleteDoctorByEmail(emailId);
		resp = ResponseUtils.getOKResponse(userdetails);
		return resp;
	}
}
