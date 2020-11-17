package com.example.demo.user.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.user.model.UserInfo;
import com.example.demo.user.model.UserLoginResponse;
import com.example.demo.user.model.UserProfile;
import com.example.demo.user.model.UserScheduler;
import com.example.demo.user.repository.SchedulerCOunt;
import com.example.demo.user.repository.UserProfileRepository;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.utils.BasicEncryption;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BasicEncryption encrypt;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserProfileRepository profileRepository;
	
	@Autowired 
	private SchedulerCOunt usercount;
	
	/*
	 * @Autowired private UserInfo userinfo;
	 * 
	 * @Autowired private EntityManager entityManager;
	 * 
	 * @Autowired(required = true) private SchedulerCOunt usercount;
	 */
	
	@Override
	public UserInfo addNewUser(UserInfo userinfo) {
		// TODO Auto-generated method stub
		UserInfo data = new UserInfo();
		if(userinfo != null)
		{
			data.setEmail_id(userinfo.getEmail_id());
			data.setFirst_Name(userinfo.getFirst_Name());
			data.setLast_Name(userinfo.getLast_Name());
			data.setMobile_no(userinfo.getMobile_no());
			String pwd = encrypt.encryptPlainText(userinfo.getPassword());
			data.setPassword(pwd);
			repository.save(data);
			return data;
		}
		return null;
	}

	@Override
	public UserLoginResponse login(UserInfo userinfo/* ,HttpServletRequest request, HttpServletResponse response */) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public UserLoginResponse authenticateUser(
			UserInfo userinfo/* , HttpServletRequest request, HttpServletResponse response */) {
		// TODO Auto-generated method stub
		System.out.println(userinfo);
		UserLoginResponse userResponse = new UserLoginResponse();
		UserInfo user= repository.findByEmail(userinfo.getEmail_id());
		System.out.println(user);
		if(userinfo.getEmail_id().equals(user.getEmail_id()) && userinfo.getPassword().equals(encrypt.decryptCipherText(user.getPassword())))
		{
			String session="12348575";
			userResponse.setEmail(userinfo.getEmail_id());
			userResponse.setName(userinfo.getFirst_Name());
			userResponse.setSessionId(session);
			userResponse.setStatus(HttpStatus.OK.name());
			System.out.println(userResponse);
			return userResponse;
		}
		return userResponse;
	}

	@Override
	public UserProfile viewProfile(String emailId) {
		// TODO Auto-generated method stub
		UserProfile profile = new UserProfile();
		UserInfo user = repository.findByEmail(emailId);
		if(user!=null)
		{
			profile.setFirst_Name(user.getFirst_Name());
			profile.setLast_Name(user.getLast_Name());
			profile.setEmail_id(user.getEmail_id());
			profile.setMobile_no(user.getMobile_no());
			profile.setUser_id(user.getUser_id());
			System.out.println(profile);
			return profile;
		}
		
		return null;
	}

	@Override
	public UserInfo updateUserbyEmail(String email, UserInfo user) {
		// TODO Auto-generated method stub
		UserInfo oldData = repository.findByEmail(email);
		user.setUser_id(oldData.getUser_id());
		UserInfo newData = repository.save(user);
		return newData;
	}

	@Override
	public UserInfo updateUserbyMobile(double mobileNo, UserInfo user) {
		// TODO Auto-generated method stub
		UserInfo oldData = repository.findByMobile(mobileNo);
		user.setUser_id(oldData.getUser_id());
		UserInfo newData = repository.save(user);
		return newData;
	}

	@Override
	public UserInfo deleteUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserInfo> forgotPassword(UserLoginResponse userCredentials) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void scheduledUpdate() {
		// TODO Auto-generated method stub
	  UserScheduler counter = usercount.userCounter();
	  long start=1;
	  long end =3;
	  long count = counter.getCounter();
	  
	  if(count!=0)
	  {
		  List<UserInfo> users = new ArrayList<UserInfo>(); 
		  do
		  {
			  users = repository.findUserByRownum(start, end);
			  start=end+1;
			  System.out.println("%%%%%%%%%%%%"+start+"%%%%%%%%%%");
			  count=count-end;
			  System.out.println("%%%%%%%%%%%%"+count+"%%%%%%%%%%");
		  }while(count>=0);
		  
		  users.stream().forEach(userDetails->{
			  
			  profileRepository.updateUserProfile(userDetails.getUser_id(), 
					  userDetails.getEmail_id(), userDetails.getFirst_Name(),
					  userDetails.getLast_Name(), userDetails.getMobile_no());
		  });
		  
	  }
		
	}

	/*
	 * @Override public List<UserProfile> updateProfileDetails(String emailId,
	 * String fName, String lName, double mobileNo, int userId) {
	 */
		/*
		 * // TODO Auto-generated method stub String fquery="update userprofile set";
		 * StringBuffer rquery = new StringBuffer(); String compQuery = new String();
		 * 
		 * Map<String,String> map = new HashMap<>();
		 * 
		 * String bquery="where user_id="+":userId"; map.put("userId",
		 * String.valueOf(userId));
		 * 
		 * if(emailId!=null) { compQuery = "email_id="+":emailId";
		 * rquery.append(compQuery); map.put("emailId", String.valueOf(emailId)); }
		 * if(fName!=null) { compQuery = "first_name="+":fName";
		 * rquery.append(compQuery); map.put("fName", String.valueOf(fName)); }
		 * if(lName!=null) { compQuery = "last_name="+":lName";
		 * rquery.append(compQuery); map.put("lName", String.valueOf(lName)); }
		 * if(mobileNo!=0) { compQuery = "mobile_no="+":mobileNo";
		 * rquery.append(compQuery); map.put("mobileNo", String.valueOf(mobileNo)); }
		 * 
		 * Query query = entityManager.createNativeQuery(fquery + rquery + bquery);
		 * map.entrySet().stream().forEach(mapobj->query.setParameter(mapobj.getKey(),
		 * mapobj.getValue()));
		 * 
		 * List<UserProfile> result = query.getResultList(); return result;
		 */
//	}

/*
 * @Override public void scheduledUpdate(UserInfo userinfo) { // TODO
 * Auto-generated method stub
 */		/*
		 * long start = 1; //if big data long end = 1000; long count = 0; long mode= 0;
		 * UserScheduler counter = usercount.userCounter(); count =
		 * counter.getCounter(); if(count!=0) { mode=count%1000; if(mode!=0) { count =
		 * count + 1; } for(int i=0;i<count/1000;i++) { List<UserInfo> users=
		 * repository. } }
		 */
		
//	}

	
}





















