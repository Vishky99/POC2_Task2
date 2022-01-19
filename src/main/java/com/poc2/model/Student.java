package com.poc2.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="students")
@Data
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String mob;
	
	private String email;
	
	private String project;
	
	private String roles;
	
	@OneToMany(mappedBy = "student" ,cascade = CascadeType.ALL)
	private Set<Project> projects;

}
