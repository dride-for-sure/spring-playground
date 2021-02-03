package com.dennisjauernig.springplayground.db;

import com.dennisjauernig.springplayground.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class OrderDb {

 private List<Order> orderDb = new ArrayList<>();

 public List<Order> list () {
	return this.orderDb;
 }

 public Order add (Order order) {
	this.orderDb.add( order );
	return order;
 }

 public boolean delete (UUID id) {
	List<Order> filteredDb = this.orderDb.stream()
					.filter( order -> !order.getId().equals( id ) )
					.collect( Collectors.toList() );
	if ( filteredDb.size() == this.orderDb.size() ) {
	 return false;
	}
	this.orderDb = filteredDb;
	return true;
 }
}
