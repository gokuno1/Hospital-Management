package com.example.demo.user.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;

import com.example.demo.user.model.Department;
import com.example.demo.user.model.DepartmentDetails;
import com.example.demo.user.model.DoctorInfo;
import com.example.demo.user.model.SpringSession;
import com.example.demo.user.model.UserInfo;
import com.example.demo.user.modelVo.DoctorInfoVO;
import com.example.demo.user.modelVo.UserInfoVO;
import com.example.demo.user.repository.DepartmentDetailsRepository;
import com.example.demo.user.repository.DoctorRepository;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.utils.BasicEncryption;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	FindByIndexNameSessionRepository<? extends Session> sessions;
	
	@Autowired
	private BasicEncryption encrypt;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private DepartmentDetailsRepository departmentDetailsRepository;
	
	//@Autowired
//	private SessionRepository sessionRepository;
	
	/*
	 * @Autowired private UserInfo userinfo;
	 * 
	 * @Autowired private EntityManager entityManager;
	 * 
	 * @Autowired(required = true) private SchedulerCOunt usercount;
	 */
	@Override
	public String forgotPassword(String emailId, String password) {
		// TODO Auto-generated method stub
		UserInfo user = userRepository.findByEmailId(emailId);
		if(encrypt.decryptCipherText(user.getPassword()).equals(password))
		{
			return "Password should not be same as old password";
		}
		else
		{
			user.setPassword(encrypt.encryptPlainText(password));
			userRepository.save(user);
			return user.getPassword();
		}
	}

	@Override
	public SpringSession authenticateUser(UserInfoVO user,HttpServletRequest request,HttpServletResponse httpServletResponse) {
		// TODO Auto-generated method stub
	//	SpringSessionVO sessionmanagement = new SpringSessionVO();
		SpringSession session = new SpringSession();
		UserInfo authUser = userRepository.findByEmailId(user.getEmailId());
		if(authUser.getEmailId().equals(user.getEmailId()) && user.getPassword().equals(encrypt.decryptCipherText(authUser.getPassword())))
		{
			String sessionId = request.getSession().getId();
			System.out.println(sessionId);
			//session.setPrincipalName(user.getEmailId());
			//sessionRepository.save(session);
			return session;
		}
		
		return session;
		
	}

	@Override
	public String addNewUser(UserInfoVO userinfo) {
		// TODO Auto-generated method stub
		UserInfo user = new UserInfo();
		user = userRepository.findByEmailId(userinfo.getEmailId());
		if(user!=null)
		{
			//add basevo class for response message
			return "User already present, try to login instead";
		}
		else
		{
			UserInfo adduser = new UserInfo();
			adduser.setPatientName(userinfo.getPatientName());
			adduser.setAddress(userinfo.getAddress());
			adduser.setBirthDate(userinfo.getBirthDate());
			adduser.setEmailId(userinfo.getEmailId());
			adduser.setGender(userinfo.getGender());
			adduser.setMobileNo(userinfo.getMobileNo());
			adduser.setPassword(encrypt.encryptPlainText(userinfo.getPassword()));
			adduser.setUserType(userinfo.getUserType());
			userRepository.save(adduser);
			return "User added Successfully";
		}
	}

	@Override
	public UserInfo viewProfile(String emailId) {
		// TODO Auto-generated method stub
		UserInfo userProfile = userRepository.findByEmailId(emailId);
		
		return userProfile;
	}

	@Override
	public String addNewDoctor(DoctorInfoVO doctorinfo) {
		// TODO Auto-generated method stub
		DoctorInfo doctor = doctorRepository.findByEmail(doctorinfo.getEmailId());
		if(doctor!=null)
		{
			return "Email Id already present.!!!!";
		}
		else
		{
			DoctorInfo adduser = new DoctorInfo();
			adduser.setAddress(doctorinfo.getAddress());
			adduser.setBirthDate(doctorinfo.getBirthDate());
			adduser.setChargePerVisit(doctorinfo.getChargePerVisit());
			adduser.setDoctorName(doctorinfo.getDoctorName());
			adduser.setEmailId(doctorinfo.getEmailId());
			adduser.setGender(doctorinfo.getGender());
			adduser.setMobileNo(doctorinfo.getMobileNo());
			adduser.setPassword(null);
			adduser.setSpecialization(doctorinfo.getSpecialization());
			adduser.setWorkExperience(doctorinfo.getWorkExperience());
			doctorRepository.save(adduser);
			Department dept = new Department();
			DepartmentDetails details = departmentDetailsRepository.findByName(doctorinfo.getDeptName());
			dept.setDeptId(details.getDeptId());
			dept.setDoctorId(adduser.getDoctorId());
			
			return "User added Successfully";
		}
	}

	
}





















