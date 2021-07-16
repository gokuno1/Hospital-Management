package com.udemy.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/hello")
	public String testMethod()
	{
		return "Hello to spring security";
	}
	
	@GetMapping("/mello")
	public String testMethod1()
	{
		return "Hello to spring security1";
	}
	@GetMapping("/jello")
	public String testMethod2()
	{
		return "Hello to spring security2";
	}
	@GetMapping("/yello")
	public String testMethod3()
	{
		return "Hello to spring security3";
	}
}
