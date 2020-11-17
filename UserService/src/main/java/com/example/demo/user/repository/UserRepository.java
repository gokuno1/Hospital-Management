package com.example.demo.user.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.model.UserInfo;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Integer>{
	
	@Query(value="select * from userdetails where email_id= ?1",nativeQuery = true)
	public UserInfo findByEmail(String email);
	
	@Query(value="select * from userdetails where mobile_no= ?1",nativeQuery = true)
	public UserInfo findByMobile(double mobileNo);
	
	@Query(value="select * from userdetails limit ?1,?2",nativeQuery = true)
	public List<UserInfo> findUserByRownum(long start, long noOfRows);
	
	
	
	

}
