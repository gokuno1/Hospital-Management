package com.example.demo.user.service;

import java.util.List;

import com.example.demo.user.model.UserInfo;
import com.example.demo.user.model.UserLoginResponse;
import com.example.demo.user.model.UserProfile;
import com.example.demo.user.modelVo.AppointmentModelVo;

public interface UserService2 {
	
	public UserInfo addNewUser(UserInfo userinfo);

	public UserLoginResponse login(UserInfo userinfo/* ,HttpServletRequest request, HttpServletResponse response */);

	public UserLoginResponse authenticateUser(UserInfo userinfo/* , HttpServletRequest request, HttpServletResponse response */);
	
	public UserProfile viewProfile(String emailId);
	
	//public UserProfile getByMobileno(double mobileno);
	
	public UserInfo updateUserbyEmail(String email, UserInfo user);
	
	public UserInfo updateUserbyMobile(double mobileNo, UserInfo user);
	
	public List<AppointmentModelVo> getCurrentAppointments(String emailId, String mobileNo);

}
