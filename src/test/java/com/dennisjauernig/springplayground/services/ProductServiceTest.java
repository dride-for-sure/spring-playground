package com.dennisjauernig.springplayground.services;

import com.dennisjauernig.springplayground.db.ProductDb;
import com.dennisjauernig.springplayground.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

 @Test
 @DisplayName ("List without search param")
 void list () {
	ProductDb productDb = mock( ProductDb.class );
	ProductService productService = new ProductService( productDb );

	List<Product> returnedList = new ArrayList<>( List.of( new Product( "1", "Hose" ) ) );
	when( productDb.list() ).thenReturn( returnedList );

	List<Product> expectedList = new ArrayList<>( List.of( new Product( "1", "Hose" ) ) );
	assertThat( productService.list( Optional.empty() ), equalTo( expectedList ) );
 }

 @Test
 @DisplayName ("List with valid search param case correct")
 void listValidSearchParamCaseCorrect () {
	ProductDb productDb = mock( ProductDb.class );
	ProductService productService = new ProductService( productDb );

	List<Product> returnedList = new ArrayList<>( List.of( new Product( "1", "Hose" ) ) );
	when( productDb.list() ).thenReturn( returnedList );

	List<Product> expectedList = new ArrayList<>( List.of( new Product( "1", "Hose" ) ) );
	assertThat( productService.list( Optional.of( "os" ) ), equalTo( expectedList ) );
 }

 @Test
 @DisplayName ("List with valid search param case incorrect")
 void listValidSearchParamCaseIncorrect () {
	ProductDb productDb = mock( ProductDb.class );
	ProductService productService = new ProductService( productDb );

	List<Product> returnedList = new ArrayList<>( List.of( new Product( "1", "Hose" ) ) );
	when( productDb.list() ).thenReturn( returnedList );

	List<Product> expectedList = new ArrayList<>( List.of( new Product( "1", "Hose" ) ) );
	assertThat( productService.list( Optional.of( "Os" ) ), equalTo( expectedList ) );
 }

 @Test
 @DisplayName ("List with invalid search param")
 void listInvalidSearchParam () {
	ProductDb productDb = mock( ProductDb.class );
	ProductService productService = new ProductService( productDb );

	List<Product> returnedList = new ArrayList<>( List.of( new Product( "1", "Hose" ) ) );
	when( productDb.list() ).thenReturn( returnedList );

	assertThat( productService.list( Optional.of( "xy" ) ), equalTo( new ArrayList<>() ) );
 }

 @Test
 @DisplayName ("Get valid id")
 void getValidId () {
	ProductDb productDb = mock( ProductDb.class );
	ProductService productService = new ProductService( productDb );

	List<Product> returnedList = new ArrayList<>( List.of( new Product( "1", "Hose" ) ) );
	when( productDb.list() ).thenReturn( returnedList );

	List<Product> expectedList = new ArrayList<>( List.of( new Product( "1", "Hose" ) ) );
	assertThat( productService.get( "1" ), equalTo( expectedList ) );
 }

 @Test
 @DisplayName ("Get invalid id")
 void getInvalidId () {
	ProductDb productDb = mock( ProductDb.class );
	ProductService productService = new ProductService( productDb );

	List<Product> returnedList = new ArrayList<>( List.of( new Product( "1", "Hose" ) ) );
	when( productDb.list() ).thenReturn( returnedList );

	assertThat( productService.get( "2" ), equalTo( new ArrayList<>() ) );
 }

 @Test
 @DisplayName ("Add product")
 void addProduct () {
	ProductDb productDb = mock( ProductDb.class );
	ProductService productService = new ProductService( productDb );

	Product newProduct = new Product( "1", "Hose" );
	Product returnedProduct = new Product( "1", "Hose" );
	when( productDb.add( newProduct ) ).thenReturn( returnedProduct );

	Product expected = new Product( "1", "Hose" );
	assertThat( productService.add( newProduct ), equalTo( expected ) );
 }

 @Test
 @DisplayName ("Delete invalid id)")
 void deleteValidId () {
	ProductDb productDb = mock( ProductDb.class );
	ProductService productService = new ProductService( productDb );
	
	when( productDb.delete( "1" ) ).thenReturn( true );
	assertThrows( ResponseStatusException.class, () -> productService.delete( "X" ) );
 }
}
