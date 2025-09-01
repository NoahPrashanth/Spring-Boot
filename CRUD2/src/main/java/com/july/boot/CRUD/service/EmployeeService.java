package com.july.boot.CRUD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.july.boot.CRUD.entity.Employee;
import com.july.boot.CRUD.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repo;	
	
	public void saveEmployee(Employee obj) {
		repo.save(obj); //insert query 
	}
	
	
	public List<Employee> getAllEmployee() {
		List<Employee> list = repo.findAll();   //select * from Employee
		return list;
	}
	
	
	public Employee getParticular(Integer id) {
		Employee obj = repo.findById(id).get();
		return obj;		
	}
	
	
	public void update(Integer id, Employee obj) {
		//update
		//first retive the particular employee based on id
		Employee emp = repo.findById(id).get();//27
		//update
		emp.setAge(obj.getAge());
		//save
		repo.save(emp);
	}
	
	public void deleteEmp(Integer id) {
		repo.deleteById(id);
	}
	
	
	
	public List<Employee> findName(String name){
		return repo.findByName(name);
	}

}
