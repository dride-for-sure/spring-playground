package com.dennisjauernig.springplayground.OrderAPI.controller;

import com.dennisjauernig.springplayground.OrderAPI.model.Product;
import com.dennisjauernig.springplayground.OrderAPI.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("api/products")
public class ProductController {

 private final ProductService productService;

 @Autowired
 public ProductController (ProductService productService) {
	this.productService = productService;
 }

 @GetMapping
 public List<Product> list (@RequestParam Optional<String> search) {
	return this.productService.list( search );
 }

 @GetMapping ("{id}")
 public List<Product> get (@PathVariable String id) {
	return this.productService.get( id );
 }

 @DeleteMapping ("{id}")
 public void delete (@PathVariable String id) {
	this.productService.delete( id );
 }

 @PostMapping
 public Product add (@RequestBody Product product) {
	return this.productService.add( product );
 }
}