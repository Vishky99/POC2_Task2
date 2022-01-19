package com.poc2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc2.model.Student;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long>{

	Optional<Student> findByEmail(String username);

}
