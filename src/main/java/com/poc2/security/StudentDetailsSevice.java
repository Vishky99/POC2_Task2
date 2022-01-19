package com.poc2.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poc2.model.Student;
import com.poc2.repository.StudentsRepository;

@Service
public class StudentDetailsSevice implements UserDetailsService{

	@Autowired
	StudentsRepository studentRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Student> student = studentRepository.findByEmail(username);
		return student.map(StudentDetails::new).get();
	}

}
