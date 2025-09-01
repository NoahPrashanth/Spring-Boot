package com.uniq.com.config.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		
		Employee emp = repo.findByEmail(email).get();
		UserDetails u1 = new User(emp.getEmail(), emp.getPwd(),  Collections.emptyList());
		return u1;
	}

}
