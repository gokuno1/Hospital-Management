package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaController {

	@GetMapping(value = "/profile")
	public String method1(@RequestHeader String name)
	{
		return "This is Get Mapping with same endpoint "+name;
	}
	
	@PostMapping(value = "/profile")
	public String method2(@RequestParam String name)
	{
		return "This is Post Mapping with same endpoint "+name;
	}

}
