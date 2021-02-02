package com.dennisjauernig.springplayground.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Order {

 private UUID id = UUID.randomUUID();
 private ArrayList<String> productIds = new ArrayList<>();

 public Order () {
 }

 public Order (UUID id, ArrayList<String> productIds) {
	this.id = id;
	this.productIds = productIds;
 }

 public UUID getId () {
	return id;
 }

 public ArrayList<String> getProductIds () {
	return productIds;
 }

 @Override
 public String toString () {
	return "Order{" +
					"id='" + id + '\'' +
					", productIds=" + productIds +
					'}';
 }

 @Override
 public boolean equals (Object o) {
	if ( this == o ) return true;
	if ( o == null || getClass() != o.getClass() ) return false;
	Order order = (Order) o;
	return Objects.equals( getId(), order.getId() ) && Objects.equals( getProductIds(), order.getProductIds() );
 }

 @Override
 public int hashCode () {
	return Objects.hash( getId(), getProductIds() );
 }
}
