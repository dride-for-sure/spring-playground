package com.dennisjauernig.springplayground.services;

import com.dennisjauernig.springplayground.db.StudentDb;
import com.dennisjauernig.springplayground.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentsService {

 private final StudentDb studentDb;

 public StudentsService (StudentDb studentDb) {
	this.studentDb = studentDb;
 }

 public List<Student> list (String search) {
	List<Student> students = this.studentDb.get();
	if ( !search.isEmpty() ) {
	 return students.stream()
					 .filter( student -> student.getName().equalsIgnoreCase( search ) )
					 .collect( Collectors.toList() );
	}
	return students;
 }

 public Optional<List<Student>> get (String id) {
	List<Student> students = this.studentDb.get();
	List<Student> matches = students.stream()
					.filter( student -> student.getId().equals( id ) )
					.collect( Collectors.toList() );
	return matches.isEmpty() ? Optional.empty() : Optional.of( matches );
 }

 public Student add (Student student) {
	return this.studentDb.add( student );
 }
}
