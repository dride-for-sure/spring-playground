package com.dennisjauernig.springplayground.OrderAPI.db;

import com.dennisjauernig.springplayground.OrderAPI.model.Order;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;

public class OrderDbTest {

 @Test
 @DisplayName ("Add and return order")
 void addAndList () {
	OrderDb orderDb = new OrderDb();
	UUID uuid = UUID.randomUUID();
	orderDb.add( new Order( uuid, new ArrayList<>( List.of( "1", "2" ) ) ) );
	List<Order> actual = orderDb.list();
	Order expectedOrder = new Order( uuid, new ArrayList<>( List.of( "1", "2" ) ) );
	assertThat( actual, hasItem( expectedOrder ) );
 }

 @Test
 @DisplayName ("Delete valid order")
 void deleteValid () {
	OrderDb orderDb = new OrderDb();
	UUID uuid = UUID.randomUUID();
	orderDb.add( new Order( uuid, new ArrayList<>( List.of( "1", "2" ) ) ) );
	orderDb.delete( uuid );
	List<Order> actual = orderDb.list();
	Order expectedOrder = new Order( uuid, new ArrayList<>( List.of( "1", "2" ) ) );
	assertThat( actual, Matchers.not( containsInAnyOrder( expectedOrder ) ) );
 }

 @Test
 @DisplayName ("Delete invalid order")
 void deleteInvalid () {
	OrderDb orderDb = new OrderDb();
	UUID uuid = UUID.randomUUID();
	orderDb.add( new Order( uuid, new ArrayList<>( List.of( "1", "2" ) ) ) );
	boolean status = orderDb.delete( UUID.randomUUID() );
	assertThat( status, Matchers.is( false ) );
 }
}
