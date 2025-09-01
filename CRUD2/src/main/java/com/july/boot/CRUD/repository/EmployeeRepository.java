package com.july.boot.CRUD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.july.boot.CRUD.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	public List<Employee> findByName(String name);
	
	/*public List<Employee> findByAgeGreaterThanEqual(Integer age);
	
	
//	@Query(value = "select department, count(*) from emp_db group by department having count(*) > :c", nativeQuery = true)
//	public List<Employee> retriveAllEmp(@Param("c") Integer count);	
	
	
	@Query(value = "select id, name, max(salary) from emp_db where department in (:dept1, :dept2)", nativeQuery = true)
	public List<Employee> findMaxInAnyTwoDept(@Param("dept1") String d1, @Param("dept2") String d2);
	
	
	
	
	@Query(value = "select * from emp_db", nativeQuery = true)
	public List<Employee> getAll();
	
	@Query(value = "select e.id, e.name from Employee e")
	public List<Employee> getAllJPQL();
	
	
	
	
	
	
	@Query(value = "select department, count(*) from emp_db group by department having count(*) > :c", nativeQuery = true)
	public List<Employee> retriveAllEmp(@Param("c") Integer count);
	
	
	
//	@Query("select e.department, count(e) from Employee e group by e.department having count(e) > :a")
//	public List<Employee> retriveAllEmpJPQL(@Param("a") Integer co);
	
	
	
	
//	@Query("select * from employee where name = :n")
//	public Employee retr(@Param("n") String name);
	
	
	*/
	
	
	
	
}
