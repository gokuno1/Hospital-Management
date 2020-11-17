package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.model.UserInfo;
import com.example.demo.user.model.UserLoginResponse;
import com.example.demo.user.model.UserProfile;
import com.example.demo.user.service.UserServiceImpl;
import com.example.demo.utils.ResponseUtils;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceImpl service;
	
	@PostMapping(value="/authenticateUser")
	public ResponseEntity<UserLoginResponse> authenticateUser(@RequestBody UserInfo user/* , HttpServletRequest request, HttpServletResponse response */)
	{
		ResponseEntity<UserLoginResponse> resp = null;
		UserLoginResponse res = service.authenticateUser(user/* , request, response */);
		//return res;
		
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
	public ResponseEntity<UserInfo> addNewUser(@RequestBody UserInfo user)
	{
		ResponseEntity<UserInfo> resp = null;
		UserInfo res = service.addNewUser(user);
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
	
	
	@GetMapping(value="/viewProfile")
	public UserProfile viewProfile(@RequestParam String emailId)
	{
	//	ResponseEntity<UserProfile> resp = null;
		UserProfile res = service.viewProfile(emailId);
		/*
		 * if(res==null) { resp=ResponseUtils.getBadRequestResponse(res); return resp; }
		 * else { resp=ResponseUtils.getOKResponse(res); return resp; }
		 */
		return res;
	}
	
	@PutMapping(value="/updateProfile")
	public void updateProfile()
	{
		service.scheduledUpdate();
	}
	
}
