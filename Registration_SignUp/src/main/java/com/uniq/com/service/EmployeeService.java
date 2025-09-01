package com.uniq.com.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uniq.com.entity.Employee;
import com.uniq.com.repo.EmpRepository;

@Service
public class EmployeeService implements UserDetailsService{
	
	@Autowired
	private EmpRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//fetch
		Employee obj = repo.findByEmail(email).get();
		//data return
		
		UserDetails u = new User(obj.getEmail(), obj.getPwd(), Collections.emptyList());
		return u;
	}

	
}
