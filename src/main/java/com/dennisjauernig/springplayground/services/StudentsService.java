package com.dennisjauernig.springplayground.services;

import com.dennisjauernig.springplayground.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentsService {
 private final HashMap<String, Student> students = new HashMap<>();

 public StudentsService () {
	Student student1 = new Student( "1", "Karl" );
	Student student2 = new Student( "2", "Heinz" );
	students.put( student1.getId(), student1 );
	students.put( student2.getId(), student2 );
 }

 public List<Student> list (String search) {
	List<Student> students = new ArrayList<>( this.students.values() );
	if ( !search.isEmpty() ) {
	 List<Student> filteredStudents =
					 students.stream()
									 .filter( student -> student.getName().equals( search ) )
									 .collect( Collectors.toList() );
	 return filteredStudents;
	}
	return students;
 }

 public Optional<Student> get (String id) {
	Student student = this.students.get( id );
	return student == null ? Optional.empty() : Optional.of( student );
 }

 public void add (Student student) {
	this.students.put( student.getId(), student );
 }
}
