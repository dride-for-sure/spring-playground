package com.dennisjauernig.springplayground.db;

import com.dennisjauernig.springplayground.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductDbTest {

 @Test
 @DisplayName ("Add and return product")
 void addAndList () {
	ProductDb productDb = new ProductDb();
	productDb.add( new Product( "3", "Shirts" ) );
	List<Product> actual = productDb.list();
	Product expected = new Product( "3", "Shirts" );
	assertThat( actual, hasItem( expected ) );
 }

 @Test
 @DisplayName ("Delete valid product")
 void deleteValid () {
	ProductDb productDb = new ProductDb();
	productDb.add( new Product( "3", "Shirts" ) );
	productDb.delete( "3" );
	List<Product> actual = productDb.list();
	Product expected = new Product( "3", "Shirts" );
	assertThat( actual, not( hasItem( expected ) ) );
 }

 @Test
 @DisplayName ("Delete invalid product")
 void deleteInvalid () {
	ProductDb productDb = new ProductDb();
	productDb.add( new Product( "3", "Shirts" ) );
	boolean status = productDb.delete( "4" );
	assertThat( status, is( false ) );
 }

 @Test
 @DisplayName ("Validate valid+invalid product ids")
 void validateMixedIds () {
	ProductDb productDb = new ProductDb();
	ArrayList<String> productIds = new ArrayList<>( List.of( "1", "3" ) );
	Optional<List<String>> invalidIds = productDb.validate( productIds );
	assertThat( invalidIds.get(), hasItem( "3" ) );
 }

 @Test
 @DisplayName ("Validate valid product ids")
 void validateValidIds () {
	ProductDb productDb = new ProductDb();
	ArrayList<String> productIds = new ArrayList<>( List.of( "1", "2" ) );
	Optional<List<String>> invalidIds = productDb.validate( productIds );
	assertTrue( invalidIds.isEmpty() );
 }

 @Test
 @DisplayName ("Validate invalid product ids")
 void validateInvalidIds () {
	ProductDb productDb = new ProductDb();
	ArrayList<String> productIds = new ArrayList<>( List.of( "4", "5" ) );
	Optional<List<String>> invalidIds = productDb.validate( productIds );
	assertThat( invalidIds.get(), containsInAnyOrder( "4", "5" ) );
 }

 @Test
 @DisplayName ("Validate empty product ids")
 void validateEmptyIds () {
	ProductDb productDb = new ProductDb();
	ArrayList<String> productIds = new ArrayList<>( List.of( "" ) );
	Optional<List<String>> invalidIds = productDb.validate( productIds );
	assertThat( invalidIds.get(), containsInAnyOrder( "" ) );
 }
}