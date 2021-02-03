package com.dennisjauernig.springplayground.services;

import com.dennisjauernig.springplayground.db.ProductDb;
import com.dennisjauernig.springplayground.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

 private final ProductDb productDb;

 @Autowired
 public ProductService (ProductDb productDb) {
	this.productDb = productDb;
 }

 public List<Product> list (Optional<String> search) {
	if ( search.isEmpty() ) {
	 return this.productDb.list();
	}
	return this.productDb.list().stream()
					.filter( product -> product.getName().matches( "(?i).*" + search.get() + ".*" ) )
					.collect( Collectors.toList() );
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

 public void delete (String id) {
	boolean status = this.productDb.delete( id );
	if ( !status ) {
	 throw new ResponseStatusException( HttpStatus.BAD_REQUEST, id + " is not available" );
	}
 }
}