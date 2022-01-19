package com.poc2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc2.jwt.JwtRequest;
import com.poc2.jwt.JwtResponse;
import com.poc2.jwt.JwtTokenUtil;
import com.poc2.model.Student;
import com.poc2.repository.StudentsRepository;
import com.poc2.security.StudentDetailsSevice;

@RestController
@CrossOrigin
public class StudentController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private StudentsRepository studentRepository;
	
	@Autowired
	private StudentDetailsSevice studentDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtToken;
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	@GetMapping("/students/{id}")
	public Optional<Student> getStudentById(@PathVariable Long id) {
		return studentRepository.findById(id);
	}
	
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		final UserDetails userDetails = studentDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtToken.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
}
