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
@RequestMapping ("student")
public class Controller {

 private final HashMap<String, Student> students = new HashMap<>();

 StudentsService studentsService;

 public Controller () {
	this.studentsService = new StudentsService();
 }

 @GetMapping ("{id}")
 public Student getStudent (@PathVariable String id) {
	Optional<Student> student = this.studentsService.get( id );
	if ( student.isPresent() ) {
	 return student.get();
	}
	throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Student with " + id + " is not " +
					"available" );
 }

 @GetMapping ("geschlecht")
 public List<Student> getStudents () {
	return this.studentsService.list();
 }
}
