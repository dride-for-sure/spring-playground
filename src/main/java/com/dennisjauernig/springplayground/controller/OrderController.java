package com.dennisjauernig.springplayground.controller;

import com.dennisjauernig.springplayground.model.Order;
import com.dennisjauernig.springplayground.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("api/orders")
public class OrderController {

 private final OrderService orderService;

 @Autowired
 public OrderController (OrderService orderService) {
	this.orderService = orderService;
 }

 @GetMapping
 public List<Order> list () {
	return this.orderService.list();
 }

 @GetMapping ("{id}")
 public List<Order> get (@PathVariable String id) {
	return this.orderService.get( UUID.fromString( id ) );
 }

 @DeleteMapping ("{id}")
 public void delete (@PathVariable String id) {
	this.orderService.delete( UUID.fromString( id ) );
 }

 @PostMapping
 public Order add (@RequestBody Order order) {
	return this.orderService.add( order );
 }
}
