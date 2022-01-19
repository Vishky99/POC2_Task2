package com.poc2.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc2.exception.NotFoundException;
import com.poc2.model.Project;
import com.poc2.repository.ProjectsRepository;
import com.poc2.repository.StudentsRepository;

@RestController
public class ProjectController {
	
	@Autowired
	private ProjectsRepository projectRepository;
	
	@Autowired
	private StudentsRepository studentRepository;
	
	@GetMapping("/students/{id}/projects")
	public Optional<Project> getProjectByStudentId(@PathVariable Long id){
		if(!studentRepository.existsById(id)) {
			throw new NotFoundException("Student not found!");
		}
		
		return projectRepository.findById(id);
	}
	
	@PostMapping("/students/{id}/projects")
	public Project addProject(@PathVariable Long id, @RequestBody Project project) {
		return studentRepository.findById(id)
				.map(student -> {
					project.setStudent(student);
					return projectRepository.save(project);
				}).orElseThrow(() -> new NotFoundException("Student not found!"));
	}


}
