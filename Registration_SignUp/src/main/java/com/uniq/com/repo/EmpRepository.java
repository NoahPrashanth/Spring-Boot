package com.uniq.com.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniq.com.entity.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer>{
	Optional<Employee> findByEmail(String email);
}
