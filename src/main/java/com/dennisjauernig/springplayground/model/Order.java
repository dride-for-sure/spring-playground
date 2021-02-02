package com.dennisjauernig.springplayground.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;

@Data
public class Order {

 private UUID id = UUID.randomUUID();
 private ArrayList<String> productIds = new ArrayList<>();

 public Order (UUID id, ArrayList<String> productIds) {
	this.id = id;
	this.productIds = productIds;
 }

 public Order () {
 }

}

