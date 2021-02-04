package com.dennisjauernig.springplayground.OrderAPI.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
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
