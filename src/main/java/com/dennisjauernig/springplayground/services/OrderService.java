package com.dennisjauernig.springplayground.services;

import com.dennisjauernig.springplayground.db.OrderDb;
import com.dennisjauernig.springplayground.db.ProductDb;
import com.dennisjauernig.springplayground.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

 private final OrderDb orderDb;
 private final ProductDb productDb;

 @Autowired
 public OrderService (OrderDb orderDb, ProductDb productDb) {
	this.orderDb = orderDb;
	this.productDb = productDb;
 }

 public List<Order> list () {
	return this.orderDb.list();
 }

 public List<Order> get (UUID id) {
	List<Order> orders = this.orderDb.list();
	return orders.stream()
					.filter( order -> order.getId().equals( id ) )
					.collect( Collectors.toList() );
 }

 public Order add (Order order) {
	Optional<List<String>> idsInvalid = this.productDb.validate( order.getProductIds() );
	if ( idsInvalid.isEmpty() ) {
	 return this.orderDb.add( order );
	} else {
	 throw new ResponseStatusException( HttpStatus.NOT_ACCEPTABLE, idsInvalid.get() + " are no " +
					 "valid products" );
	}
 }

 public void delete (UUID id) {
	boolean status = this.orderDb.delete( id );
	if ( !status ) {
	 throw new ResponseStatusException( HttpStatus.BAD_REQUEST, id + "is not available" );
	}
 }
}
