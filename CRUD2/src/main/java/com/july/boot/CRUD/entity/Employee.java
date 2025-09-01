package com.july.boot.CRUD.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "emp_db")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Name should be entered")
	@Size(min = 3, message = "Enter valid name")
	@Column(name = "ename")
	private String name;
	
	@NotNull(message = "Enter Age")
	@Min(value = 18, message = "Age should be greater than 18")
	@Column(name = "eage")
	private Integer age;
	
	@Email(message = "Enter valid email")
	private String email;
	
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn
	private UAN pf;	
	

	@ManyToOne(fetch = FetchType.LAZY) //Eager
	@JoinColumn(name = "dept_id")
	private Department dept;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "ep_rel", //tbl_name
			joinColumns = @JoinColumn(name = "e_id"), //P_FK
			inverseJoinColumns = @JoinColumn(name = "p_id") //S_FK
			)
	private Set<Project> project;
	
	
	
	
	
	
	
}

