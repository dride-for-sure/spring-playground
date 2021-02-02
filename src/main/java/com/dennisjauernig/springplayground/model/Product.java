package com.dennisjauernig.springplayground.model;

import java.util.Objects;

public class Product {

 private final String id;
 private final String name;

 public Product (String id, String name) {
	this.id = id;
	this.name = name;
 }

 public String getId () {
	return id;
 }

 public String getName () {
	return name;
 }

 @Override
 public String toString () {
	return "Product{" +
					"id='" + id + '\'' +
					", name='" + name + '\'' +
					'}';
 }

 @Override
 public boolean equals (Object o) {
	if ( this == o ) return true;
	if ( o == null || getClass() != o.getClass() ) return false;
	Product product = (Product) o;
	return Objects.equals( getId(), product.getId() ) && Objects.equals( getName(), product.getName() );
 }

 @Override
 public int hashCode () {
	return Objects.hash( getId(), getName() );
 }
}
