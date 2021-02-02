package com.dennisjauernig.springplayground.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Order {

 private UUID id = UUID.randomUUID();
 private ArrayList<String> productIds = new ArrayList<>();

 public Order (UUID id, ArrayList<String> productIds) {
	this.id = id;
	this.productIds = productIds;
 }

 public Order () {
 }

 public UUID getId () {
	return this.id;
 }

 public void setId (UUID id) {
	this.id = id;
 }

 public ArrayList<String> getProductIds () {
	return this.productIds;
 }

 public void setProductIds (ArrayList<String> productIds) {
	this.productIds = productIds;
 }

 @Override
 public String toString () {
	return "Order{" +
					"id=" + this.id +
					", productIds=" + this.productIds +
					'}';
 }

 @Override
 public boolean equals (Object o) {
	if ( this == o ) return true;
	if ( o == null || this.getClass() != o.getClass() ) return false;
	Order order = (Order) o;
	return Objects.equals( this.getId(), order.getId() ) && Objects.equals( this.getProductIds(), order.getProductIds() );
 }

 @Override
 public int hashCode () {
	return Objects.hash( this.getId(), this.getProductIds() );
 }
}

