package com.dennisjauernig.springplayground.model;

import java.util.Objects;

public class Product {

 private String id;
 private String name;

 public Product (String id, String name) {
	this.id = id;
	this.name = name;
 }

 public Product () {
 }

 public String getId () {
	return this.id;
 }

 public void setId (String id) {
	this.id = id;
 }

 public String getName () {
	return this.name;
 }

 public void setName (String name) {
	this.name = name;
 }

 @Override
 public String toString () {
	return "Product{" +
					"id='" + this.id + '\'' +
					", name='" + this.name + '\'' +
					'}';
 }

 @Override
 public boolean equals (Object o) {
	if ( this == o ) return true;
	if ( o == null || this.getClass() != o.getClass() ) return false;
	Product product = (Product) o;
	return Objects.equals( this.id, product.id ) && Objects.equals( this.name, product.name );
 }

 @Override
 public int hashCode () {
	return Objects.hash( this.id, this.name );
 }
}
