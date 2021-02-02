package com.dennisjauernig.springplayground.db;

import com.dennisjauernig.springplayground.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentDb {

 private final ArrayList<Student> students = new ArrayList<>();

 public List<Student> get () {
	return this.students;
 }

 public Student add (Student student) {
	this.students.add( student );
	return student;
 }

}
