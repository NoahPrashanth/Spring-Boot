package com.uniq.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uniq.com.entity.Employee;
import com.uniq.com.repo.EmpRepository;

@RestController
public class EmpController {
	
	@Autowired
	private EmpRepository repo;
	
	@Autowired
	private AuthenticationManager am;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping("/register")
	public ResponseEntity<String> save(@RequestBody Employee req){
		String pwd = req.getPwd();
		String encodedPWD = encoder.encode(pwd);
		req.setPwd(encodedPWD);
		repo.save(req);
		return new ResponseEntity<String>("Saved Sucessfully", HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> logn(@RequestBody Employee e){
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(e.getEmail(), e.getPwd());
		for(int i=0 ; i<5 ; i++) {
			System.out.println(encoder.encode(e.getPwd()));
		}
		try {
			Authentication auth = am.authenticate(token);
			if(auth.isAuthenticated()) {
				return ResponseEntity.ok("Logged in....");
			}
		}
		catch(AuthenticationException ee) {
			System.out.println("Hanlded");
		}
		
		return new ResponseEntity<String>("Wrong Credentials", HttpStatus.BAD_REQUEST);
		
	}
	

}
