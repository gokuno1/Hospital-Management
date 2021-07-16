package com.example.demo.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.user.model.DoctorInfo;
import com.example.demo.user.model.SpringSession;
import com.example.demo.user.model.UserInfo;
import com.example.demo.user.modelVo.DoctorInfoVO;
import com.example.demo.user.modelVo.UserInfoVO;
import com.example.demo.user.modelVo.UserLoginResponse;

public interface UserService {

	public UserLoginResponse authenticateUser(UserInfoVO user,HttpServletRequest request,HttpServletResponse httpServletResponse);
	public String addNewUser(UserInfoVO userinfo);
	public UserInfoVO viewProfile(String emailId);
	public String forgotPassword(String emailId, String password);
	public String addNewDoctor(DoctorInfoVO doctorinfo);
	public List<DoctorInfo> getAllDoctors();
	public DoctorInfoVO searchDoctorByEmail(String emailId);
	public String deleteDoctorByEmail(String emailId);
	
}
