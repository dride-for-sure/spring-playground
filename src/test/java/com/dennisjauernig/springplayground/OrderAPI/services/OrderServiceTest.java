package com.dennisjauernig.springplayground.OrderAPI.services;

import com.dennisjauernig.springplayground.OrderAPI.db.OrderDb;
import com.dennisjauernig.springplayground.OrderAPI.db.ProductDb;
import com.dennisjauernig.springplayground.OrderAPI.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

 @Test
 @DisplayName ("List all orders")
 void list () {
	OrderDb orderDb = mock( OrderDb.class );
	ProductDb productDb = mock( ProductDb.class );
	OrderService orderService = new OrderService( orderDb, productDb );

	UUID uuid = UUID.randomUUID();
	List<Order> mockedReturnedList = new ArrayList<>( List.of( new Order( uuid,
					new ArrayList<>( List.of( "1",
									"2" ) ) ) ) );
	when( orderDb.list() ).thenReturn( mockedReturnedList );
	List<Order> returnedList = orderService.list();

	List<Order> expectedList = new ArrayList<>( List.of( new Order( uuid, new ArrayList<>( List.of( "1",
					"2" ) ) ) ) );

	assertThat( returnedList, equalTo( expectedList ) );
 }

 @Test
 @DisplayName ("Get valid order")
 void getValid () {
	OrderDb orderDb = mock( OrderDb.class );
	ProductDb productDb = mock( ProductDb.class );
	OrderService orderService = new OrderService( orderDb, productDb );

	UUID uuid = UUID.randomUUID();
	List<Order> returnedOrder = new ArrayList<>( List.of( new Order( uuid, new ArrayList<>( List.of(
					"1", "2" ) ) ) ) );
	List<Order> expectedOrder = new ArrayList<>( List.of( new Order( uuid, new ArrayList<>( List.of(
					"1", "2" ) ) ) ) );

	when( orderDb.list() ).thenReturn( returnedOrder );
	List<Order> getOrder = orderService.get( uuid );

	assertThat( getOrder, equalTo( expectedOrder ) );
 }

 @Test
 @DisplayName ("Get invalid order")
 void getInvalid () {
	OrderDb orderDb = mock( OrderDb.class );
	ProductDb productDb = mock( ProductDb.class );
	OrderService orderService = new OrderService( orderDb, productDb );

	UUID uuid = UUID.randomUUID();
	List<Order> returnedOrder = new ArrayList<>( List.of( new Order( uuid, new ArrayList<>( List.of(
					"1", "2" ) ) ) ) );

	when( orderDb.list() ).thenReturn( returnedOrder );
	List<Order> getOrder = orderService.get( UUID.randomUUID() );

	assertThat( getOrder, equalTo( new ArrayList<>() ) );
 }

 @Test
 @DisplayName ("Add valid order")
 void addValid () {
	OrderDb orderDb = mock( OrderDb.class );
	ProductDb productDb = mock( ProductDb.class );
	OrderService orderService = new OrderService( orderDb, productDb );

	UUID uuid = UUID.randomUUID();
	Order newOrder = new Order( uuid, new ArrayList<>( List.of( "1", "2" ) ) );

	when( productDb.validate( newOrder.getProductIds() ) ).thenReturn( Optional.empty() );
	when( orderDb.add( newOrder ) ).thenReturn( newOrder );

	Order returnedOrder = orderService.add( newOrder );
	Order expectedOrder = new Order( uuid, new ArrayList<>( List.of( "1", "2" ) ) );

	assertThat( returnedOrder, equalTo( expectedOrder ) );
 }

 @Test
 @DisplayName ("Add invalid order")
 void addInvalid () {
	OrderDb orderDb = mock( OrderDb.class );
	ProductDb productDb = mock( ProductDb.class );
	OrderService orderService = new OrderService( orderDb, productDb );

	UUID uuid = UUID.randomUUID();
	Order newOrder = new Order( uuid, new ArrayList<>( List.of( "4", "5" ) ) );

	when( productDb.validate( newOrder.getProductIds() ) ).thenReturn( Optional.of( new ArrayList<>( List.of( "4", "5" ) ) ) );

	assertThrows( ResponseStatusException.class, () -> orderService.add( newOrder ) );
 }


 @Test
 @DisplayName ("Delete invalid order")
 void deleteValid () {
	OrderDb orderDb = mock( OrderDb.class );
	ProductDb productDb = mock( ProductDb.class );
	OrderService orderService = new OrderService( orderDb, productDb );

	UUID uuid = UUID.randomUUID();
	when( orderDb.delete( uuid ) ).thenReturn( false );
	assertThrows( ResponseStatusException.class, () -> orderService.delete( uuid ) );
 }
}
