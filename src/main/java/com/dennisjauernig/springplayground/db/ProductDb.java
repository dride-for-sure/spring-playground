package com.dennisjauernig.springplayground.db;

import com.dennisjauernig.springplayground.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDb {

 List<Product> productDb = new ArrayList<>();

 public List<Product> list () {
	return this.productDb;
 }

 public Product add (Product product) {
	this.productDb.add( product );
	return product;
 }
}