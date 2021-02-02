package com.dennisjauernig.springplayground.services;

import com.dennisjauernig.springplayground.db.ProductDb;
import com.dennisjauernig.springplayground.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

 private final ProductDb productDb;

 public ProductService (ProductDb productDb) {
	this.productDb = productDb;
 }

 public List<Product> list () {
	return this.productDb.list();
 }

 public Product add (Product product) {
	return this.productDb.add( product );
 }
}