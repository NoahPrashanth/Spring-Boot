package com.july.boot.CRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.july.boot.CRUD.entity.Employee;
import com.july.boot.CRUD.repository.EmployeeRepository;
import com.july.boot.CRUD.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService service; //jpa
	
//	@Autowired
//	private EmployeeRepository repo;
	
	
//	@Value("${spring.noah}")
//	private String v;
//	
//	@GetMapping("/")
//	public String home() {
//		return this.v;
//	}

	@PostMapping("/saveemp")
	public ResponseEntity<Employee> saveEmp(@Valid @RequestBody Employee obj) {
		service.saveEmployee(obj);
		return new ResponseEntity<Employee>(obj, HttpStatus.CREATED);
	}
		
	@GetMapping("/get")
	public ResponseEntity<List<Employee>> getAll() {
		List<Employee> list = service.getAllEmployee();
		//return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/getemp/{id}")
	public Employee get(@PathVariable Integer id) {
		Employee obj = service.getParticular(id);
		return obj;
	}
	
	
	
	@PutMapping("/update/{id}")
	public void updateEmp(@PathVariable Integer id, @RequestBody Employee obj) {
		service.update(id, obj);
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public void deleteEmp(@PathVariable Integer id) {
		service.deleteEmp(id);
	}
	
	//FindByMethods
	@GetMapping("getemp/name/{name}")
	public List<Employee> findEmpname(@PathVariable String name){
		return service.findName(name);
	}

	
	
	
	//http://localhost:8080/pageAndSort?pn=0&ps=5
	
	
//	@GetMapping("/pageAndSort")
//	public ResponseEntity<Page<Employee>> getEmpPS(@RequestParam(name = "pn") Integer pn
//												 , @RequestParam(name = "ps") Integer ps) {
//		
//		Sort s = Sort.by("name", "age").descending();
//		
//		
//		//2 - 1 = 1
//		//1- 100
//		PageRequest of = PageRequest.of(pn, ps, s);
//		
//		
//		Page<Employee> pages = repo.findAll(of);
//		//100 - 96
//		//95 - 91
//		//90 - 86
//		//85 - 81
//		//0
//		
//		return ResponseEntity.ok(pages);
//	}
	
	
	
	
	
	
	
	
	
	
	
}
