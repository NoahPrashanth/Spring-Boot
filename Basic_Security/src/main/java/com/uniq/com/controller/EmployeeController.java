package com.uniq.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	@GetMapping("/home")
	public String welcome() {
		return "Welcome back";
	}
	
	@GetMapping("/dashboard")
	public String dashbrd() {
		return "Statistics";
	}
	
	
	//to make public
	@GetMapping("/loging")
	public String lgn() {
		return "Login Page";
	}

}
