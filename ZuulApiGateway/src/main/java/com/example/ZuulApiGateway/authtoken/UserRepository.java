package com.example.ZuulApiGateway.authtoken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ZuulApiGateway.entity.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer>{

	@Query(value = "select * from USER_DETAILS where EMAIL_ID=?1", nativeQuery = true)
	public UserInfo findUserByEmailId(String userName);
}
