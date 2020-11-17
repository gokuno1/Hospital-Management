package com.example.demo.user.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.model.UserProfile;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Integer>{

	@Transactional
	@Modifying
	@Query(value="update userprofile set email_id=?2, first_name=?3, last_name=?4, mobile_no=?5 where user_id=?1",nativeQuery=true)
	public void updateUserProfile(int userId, String emailId, String firstName, String lastName, double mobileNo);
}
