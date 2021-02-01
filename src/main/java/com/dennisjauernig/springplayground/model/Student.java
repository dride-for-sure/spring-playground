package com.dennisjauernig.springplayground.model;

public class Student {
 private final String name;
 private final String id;

 public Student(String id, String name){
  this.id = id;
  this.name = name;
 }

 public String getId () {
  return id;
 }

 public String getName () {
  return name;
 }
}
