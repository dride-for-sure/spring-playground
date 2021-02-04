package com.dennisjauernig.springplayground.OrderAPI.model;

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

 private UUID id;
 private ArrayList<String> productIds;

 public Order (UUID id, ArrayList<String> productIds) {
	this.id = id;
	this.productIds = productIds;
 }

 public Order () {
 }

}