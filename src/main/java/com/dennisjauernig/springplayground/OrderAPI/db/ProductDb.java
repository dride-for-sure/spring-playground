package com.dennisjauernig.springplayground.OrderAPI.db;

import com.dennisjauernig.springplayground.OrderAPI.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductDb {

 List<Product> productDb = new ArrayList<>( List.of(
				 new Product( "1", "Schuhe" ),
				 new Product( "2", "Hosen" )
 ) );

 public List<Product> list () {
	return this.productDb;
 }

 public Product add (Product product) {
	this.productDb.add( product );
	return product;
 }

 public Optional<List<String>> validate (ArrayList<String> productIds) {
	List<String> idsInvalid = productIds.stream()
					.filter( id -> this.productDb.stream().noneMatch( product -> product.getId().equals( id ) ) )
					.collect( Collectors.toList() );
	return idsInvalid.isEmpty() ? Optional.empty() : Optional.of( idsInvalid );
 }

 public boolean delete (String id) {
	List<Product> filteredDb = this.productDb.stream()
					.filter( product -> !product.getId().equals( id ) )
					.collect( Collectors.toList() );
	if ( filteredDb.size() == this.productDb.size() ) {
	 return false;
	}
	this.productDb = filteredDb;
	return true;
 }
}