package com.dennisjauernig.springplayground.controller;

import com.dennisjauernig.springplayground.model.Product;
import com.dennisjauernig.springplayground.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("api/products")
public class ProductController {

 private final ProductService productService;

 public ProductController (ProductService productService) {
	this.productService = productService;
 }

 @GetMapping ("list")
 public List<Product> list () {
	return this.productService.list();
 }

 @PutMapping
 public Product add (Product product) {
	return this.productService.add( product );
 }

}