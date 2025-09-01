package com.july.boot.CRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.july.boot.CRUD.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer>{

}
