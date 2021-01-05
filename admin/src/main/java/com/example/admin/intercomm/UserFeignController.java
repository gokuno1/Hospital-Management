package com.example.admin.intercomm;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.admin.modelVo.DoctorInfoVO;


@FeignClient("user-service")
public interface UserFeignController {
	
	@PostMapping(value="/addNewDoctor")
	public String addNewDoctor(@RequestBody DoctorInfoVO doctor);
	
	@GetMapping(value = "/getAllDoctors")
	public List<DoctorInfoVO> getAllDoctors();
	
	@GetMapping(value = "/searchDoctor")
	public DoctorInfoVO searchDoctor(@RequestBody String emailId);
	
	@DeleteMapping(value = "/deleteDoctor")
	public String deleteDoctor(@RequestBody String emailId);

}
