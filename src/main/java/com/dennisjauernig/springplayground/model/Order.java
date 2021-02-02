package com.dennisjauernig.springplayground.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
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

