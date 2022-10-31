package com.example.demo.user.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.user.model.Department;
import com.example.demo.user.model.DepartmentDetails;
import com.example.demo.user.model.DoctorInfo;
import com.example.demo.user.model.SpringSession;
import com.example.demo.user.model.UserInfo;
import com.example.demo.user.modelVo.DoctorInfoVO;
import com.example.demo.user.modelVo.MyUserDetails;
import com.example.demo.user.modelVo.UserDto;
import com.example.demo.user.modelVo.UserInfoVO;
import com.example.demo.user.modelVo.UserLoginResponse;
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
	public UserLoginResponse authenticateUser(UserInfoVO user,HttpServletRequest request,HttpServletResponse httpServletResponse) {
		
		UserLoginResponse response = new UserLoginResponse();
		boolean login = false;
		UserInfo authUser = userRepository.findByEmailId(user.getEmailId());
		if(authUser!=null) {
			login = validateUser(authUser, user);
		}
		if(login) {
			String sessionId = request.getSession().getId();
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.getUserType()));
			MyUserDetails myUserDetails = new MyUserDetails(new UserDto(user.getMobileNo(), null), authorities);
			Collection<? extends Session> usersSessions = this.sessions
					.findByPrincipalName(myUserDetails.getUsername()).values();
			if (!usersSessions.isEmpty()) {
				usersSessions.forEach(sessionIda -> {
					this.sessions.deleteById(sessionIda.getId());
				});
			}
			SecurityContextHolder.getContext().setAuthentication(
					new UsernamePasswordAuthenticationToken(myUserDetails, null, authorities));
			
			response.setMobileNo(authUser.getMobileNo());
			response.setEmailId(authUser.getEmailId());
			response.setUserType(authUser.getUserType());
			response.setBirthDate(authUser.getBirthDate());
			response.setPatientName(authUser.getPatientName());
			response.setSessionId(sessionId);
			response.setAddress(authUser.getAddress());
		}
		
		return response;
	}

	private boolean validateUser(UserInfo authUser, UserInfoVO user) {
		String inputPassword = encrypt.decryptCipherText(user.getPassword());
		String userPassword = encrypt.decryptCipherText(authUser.getPassword());
		if(null!=inputPassword && inputPassword.equals(userPassword)) {
			return true;
		}
		return false;		
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
	public UserInfoVO viewProfile(String emailId) {
		// TODO Auto-generated method stub
		UserInfoVO userDetails = new UserInfoVO();
		UserInfo userProfile = userRepository.findByEmailId(emailId);
		userDetails.setAddress(userProfile.getAddress());
		userDetails.setBirthDate(userProfile.getBirthDate());
		userDetails.setEmailId(userProfile.getEmailId());
		userDetails.setGender(userProfile.getGender());
		userDetails.setMobileNo(userProfile.getMobileNo());
		userDetails.setPassword(userProfile.getPassword());
		userDetails.setPatientId(userProfile.getPatientId());
		userDetails.setPatientName(userProfile.getPatientName());
		userDetails.setUserType(userProfile.getUserType());
		return userDetails;
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

	@Override
	public List<DoctorInfo> getAllDoctors() {
		// TODO Auto-generated method stub
		List<DoctorInfo> getAll = (List<DoctorInfo>) doctorRepository.findAll();
		return getAll;
	}

	@Override
	public DoctorInfoVO searchDoctorByEmail(String emailId) {
		// TODO Auto-generated method stub
		DoctorInfoVO doctorDetails = new DoctorInfoVO();
		DoctorInfo user = doctorRepository.findByEmail(emailId);
		doctorDetails.setAddress(user.getAddress());
		doctorDetails.setBirthDate(user.getBirthDate());
		doctorDetails.setChargePerVisit(user.getChargePerVisit());
		doctorDetails.setDeptName("waiting to develop logic");
		doctorDetails.setDoctorId(user.getDoctorId());
		doctorDetails.setDoctorName(user.getDoctorName());
		doctorDetails.setEmailId(emailId);
		doctorDetails.setGender(user.getGender());
		doctorDetails.setMobileNo(user.getMobileNo());
		doctorDetails.setSpecialization(user.getSpecialization());
		doctorDetails.setUserId(user.getUserId());
		doctorDetails.setWorkExperience(user.getWorkExperience());
		return doctorDetails;
	}

	@Override
	public String deleteDoctorByEmail(String emailId) {
		// TODO Auto-generated method stub
		doctorRepository.deleteByEmail(emailId);
		return "User deleted Successfully";
	}

	
}





















