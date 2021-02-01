package com.dennisjauernig.springplayground.services;

import com.dennisjauernig.springplayground.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {
 private final HashMap<String, Student> students = new HashMap<>();

 public StudentsService() {
	Student student1 = new Student( "1", "Karl" );
	Student student2 = new Student( "2", "Heinz" );
	students.put( student1.getId(), student1 );
	students.put( student2.getId(), student2 );
 }

 public List<Student> list() {
  return new ArrayList<>(this.students.values());
 }

 public Optional<Student> get(String id) {
  Student student = this.students.get( id );
  return student == null ? Optional.empty() : Optional.of( student );
 }
}
