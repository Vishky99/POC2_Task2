package com.poc2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="projects")
@Data
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long projid;
	
	private String projname;
	
	private String duration;
	
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

}
