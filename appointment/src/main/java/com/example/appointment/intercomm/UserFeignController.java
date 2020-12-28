package com.example.appointment.intercomm;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.appointment.model.vo.UserProfileVo;

@FeignClient("user-service")
public interface UserFeignController {
	
	@GetMapping(value="/userservice/viewProfile")
	public UserProfileVo viewProfile(@RequestParam String emailId);

}
