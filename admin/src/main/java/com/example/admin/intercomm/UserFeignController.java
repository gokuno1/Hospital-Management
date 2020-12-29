package com.example.admin.intercomm;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.admin.modelVo.DoctorInfoVO;


@FeignClient("user-service")
public interface UserFeignController {
	
	@PostMapping(value="/addNewDoctor")
	public String addNewDoctor(@RequestBody DoctorInfoVO doctor);

}
