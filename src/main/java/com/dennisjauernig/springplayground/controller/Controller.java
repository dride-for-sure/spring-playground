package com.dennisjauernig.springplayground.controller;

import com.dennisjauernig.springplayground.model.Student;
import com.dennisjauernig.springplayground.services.StudentsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("students")
public class Controller {

 private final HashMap<String, Student> students = new HashMap<>();

 private final StudentsService studentsService;

 public Controller (StudentsService studentsService) {
	this.studentsService = studentsService; // DI
 }

 @GetMapping ("{id}")
 public Student getStudent (@PathVariable String id) {
	Optional<Student> student = this.studentsService.get( id );
	if ( student.isPresent() ) {
	 return student.get();
	}
	throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Student with " + id + " is not " + "available"
	);
 }

 @GetMapping
 public List<Student> getStudents (@RequestParam Optional<String> search) {
	return this.studentsService.list( search.orElse( "" ) );
 }

 @PutMapping
 public Student addStudent (@RequestBody Student student) {
	studentsService.add( student );
	return student;
 }
}
