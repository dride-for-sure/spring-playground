package com.dennisjauernig.springplayground.model;

import lombok.Data;

@Data
public class Product {

 private String id;
 private String name;

 public Product (String id, String name) {
	this.id = id;
	this.name = name;
 }

 public Product () {
 }

}
