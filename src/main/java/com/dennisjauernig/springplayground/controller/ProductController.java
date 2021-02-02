package com.dennisjauernig.springplayground.controller;

import com.dennisjauernig.springplayground.model.Product;
import com.dennisjauernig.springplayground.services.ProductService;
import org.apache.catalina.startup.ListenerCreateRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("api/products")
public class ProductController {

 private final ProductService productService;

 @Autowired
 public ProductController (ProductService productService) {
	this.productService = productService;
 }

 @GetMapping
 public List<Product> list () {
	return this.productService.list();
 }

 @GetMapping ("{id}")
 public List<Product> get (String id) {
	return this.productService.get( id );
 }

 @PutMapping
 public Product add (@RequestBody Product product) {
	return this.productService.add( product );
 }
}