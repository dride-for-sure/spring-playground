package com.dennisjauernig.springplayground.OrderAPI.controller;

import com.dennisjauernig.springplayground.OrderAPI.db.OrderDb;
import com.dennisjauernig.springplayground.OrderAPI.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerIntegrationTest {

 @LocalServerPort
 private int port;

 @Autowired
 private TestRestTemplate restTemplate;

 @Autowired
 private OrderDb orderDb;

 @Test
 @DisplayName ("Add valid order")
 void addValid () {
	String url = "http://localhost:" + this.port + "/api/orders";
	UUID uuid = UUID.randomUUID();
	Order validOrder = new Order( uuid, new ArrayList<String>( List.of( "1", "2" ) ) );

	ResponseEntity<Order> response = this.restTemplate.postForEntity( url, validOrder, Order.class );

	assertThat( response.getStatusCode(), is( HttpStatus.OK ) );
	assertThat( response.getBody(), equalTo( new Order( uuid,
					new ArrayList<String>( List.of( "1", "2" ) ) ) ) );
	assertTrue( this.orderDb.list().contains( new Order( uuid, new ArrayList<String>( List.of( "1", "2" ) ) ) ) );
 }

 @Test
 @DisplayName ("Add invalid order")
 void addInvalid () {
	String url = "http://localhost:" + this.port + "/api/orders";
	UUID uuid = UUID.randomUUID();
	Order validOrder = new Order( uuid, new ArrayList<String>( List.of( "4", "5" ) ) );

	ResponseEntity<Order> response = this.restTemplate.postForEntity( url, validOrder, Order.class );

	assertThat( response.getStatusCode(), is( HttpStatus.NOT_ACCEPTABLE ) );
	assertFalse( this.orderDb.list().contains( new Order( uuid,
					new ArrayList<String>( List.of( "4", "5" ) ) ) ) );
 }

 /*
 @Test
 @DisplayName( "Delete valid order" )

 @Test
 @DisplayName( "Delete invalid order" )

 @Test
 @DisplayName( "Get valid order" )

 @Test
 @DisplayName("Ged invalid order")

 @Test
 @DisplayName("List all orders")
 */
}
