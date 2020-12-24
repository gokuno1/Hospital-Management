package com.example.demo.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.user.model.SpringSession;
import com.example.demo.user.model.UserInfo;
import com.example.demo.user.modelVo.UserInfoVO;

public interface UserService {

	public SpringSession authenticateUser(UserInfoVO user,HttpServletRequest request,HttpServletResponse httpServletResponse);
	public String addNewUser(UserInfoVO userinfo);
	public UserInfo viewProfile(String emailId);
	public String forgotPassword(String emailId, String password);
	
	
}
