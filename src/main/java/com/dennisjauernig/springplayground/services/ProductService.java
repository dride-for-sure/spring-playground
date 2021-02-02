package com.dennisjauernig.springplayground.services;

import com.dennisjauernig.springplayground.db.ProductDb;
import com.dennisjauernig.springplayground.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

 private final ProductDb productDb;

 @Autowired
 public ProductService (ProductDb productDb) {
	this.productDb = productDb;
 }

 public List<Product> list () {
	return this.productDb.list();
 }

 public List<Product> get (String id) {
	List<Product> products = this.productDb.list();
	return products.stream()
					.filter( product -> product.getId().equals( id ) )
					.collect( Collectors.toList() );
 }

 public Product add (Product product) {
	return this.productDb.add( product );
 }
}