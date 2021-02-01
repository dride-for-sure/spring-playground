package com.dennisjauernig.springplayground.controller;

import com.dennisjauernig.springplayground.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping
public class Controller {

 @GetMapping("/student")
 public Student getStudent (@RequestParam(value = "id", defaultValue = "1") String id) {
  HashMap<String, Student> students = new HashMap<>();
  Student student1 = new Student( "1", "Karl" );
  Student student2 = new Student( "2", "Heinz" );
  students.put( student1.getId(), student1 );
  students.put( student2.getId(), student2 );
  return students.get(id);
 }

 @GetMapping("/students")
 public List<Student> getStudents () {
  HashMap<String, Student> students = new HashMap<>();
  Student student1 = new Student( "1", "Karl" );
  Student student2 = new Student( "2", "Heinz" );
  students.put( student1.getId(), student1 );
  students.put( student2.getId(), student2 );
  return new ArrayList<Student>(students.values());
 }

}
