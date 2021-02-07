package com.dennisjauernig.springplayground.ToDoAPI.services;

import com.dennisjauernig.springplayground.ToDoAPI.db.Db;
import com.dennisjauernig.springplayground.ToDoAPI.model.ToDo;
import com.dennisjauernig.springplayground.ToDoAPI.model.ToDoWithoutId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicesTest {

 @Test
 @DisplayName ("List -> hasItem")
 void list () {
	Db db = mock( Db.class );
	Services services = new Services( db );
	UUID uuid1 = UUID.randomUUID();

	when( db.get() ).thenReturn( new ArrayList<ToDo>( List.of(
					new ToDo( uuid1.toString(), "FOOBAR", "DONE" )
	) ) );

	List<ToDo> actual = services.get();

	assertThat( actual, hasItem( new ToDo( uuid1.toString(), "FOOBAR", "DONE" ) ) );
 }

 @Test
 @DisplayName ("Empty List -> Optional.empty")
 void listEmpty () {
	Db db = mock( Db.class );
	Services services = new Services( db );

	when( db.get() ).thenReturn( new ArrayList<ToDo>() );

	List<ToDo> actual = services.get();

	assertThat( actual, equalTo( new ArrayList<ToDo>() ) );
 }

 @Test
 @DisplayName ("Create -> equalTo")
 void create () {
	Db db = mock( Db.class );
	Services services = new Services( db );
	UUID uuid1 = UUID.randomUUID();

	try ( MockedStatic<UUID> uuidMockedStatic = Mockito.mockStatic( UUID.class ) ) {
	 uuidMockedStatic.when( () -> UUID.randomUUID() ).thenReturn( uuid1 );

	 when( db.create( new ToDo( uuid1.toString(), "FOOBAR", "DONE" ) ) )
					 .thenReturn( Optional.of( new ToDo( uuid1.toString(), "FOOBAR", "DONE" )
					 ) );

	 Optional<ToDo> actual = services.create( new ToDoWithoutId( "FOOBAR", "DONE" ) );

	 assertThat( actual.get(), equalTo( new ToDo( uuid1.toString(), "FOOBAR", "DONE" ) ) );

	}
 }

 @Test
 @DisplayName ("Update valid -> equalTo")
 void updateValid () {
	Db db = mock( Db.class );
	Services services = new Services( db );
	UUID uuid1 = UUID.randomUUID();

	when( db.update( uuid1.toString(), new ToDo( uuid1.toString(), "FOOBAR", "DONE" ) ) )
					.thenReturn( Optional.of( new ToDo( uuid1.toString(), "FOOBAR", "DONE" )
					) );

	Optional<ToDo> actual = services.update( uuid1.toString(),
					new ToDo( uuid1.toString(), "FOOBAR", "DONE" ) );

	assertThat( actual.get(), equalTo( new ToDo( uuid1.toString(), "FOOBAR", "DONE" ) ) );
 }

 @Test
 @DisplayName ("Update invalid -> Optional.empty")
 void updateInvalid () {
	Db db = mock( Db.class );
	Services services = new Services( db );
	UUID uuid1 = UUID.randomUUID();
	UUID uuid2 = UUID.randomUUID();

	when( db.update( uuid1.toString(), new ToDo( uuid2.toString(), "FOOBAR", "DONE" ) ) )
					.thenReturn( Optional.empty() );

	Optional<ToDo> actual = services.update( uuid2.toString(),
					new ToDo( uuid1.toString(), "FOOBAR", "DONE" ) );

	assertThat( actual, equalTo( Optional.empty() ) );
 }

 @Test
 @DisplayName ("Delete Valid -> equalTo")
 void deleteValid () {
	Db db = mock( Db.class );
	Services services = new Services( db );
	UUID uuid1 = UUID.randomUUID();

	when( db.delete( uuid1.toString() ) )
					.thenReturn( Optional.of( new ToDo( uuid1.toString(), "FOOBAR", "DONE" ) ) );

	Optional<ToDo> actual = services.delete( uuid1.toString() );

	assertThat( actual.get(), equalTo( new ToDo( uuid1.toString(), "FOOBAR", "DONE" ) ) );
 }

 @Test
 @DisplayName ("Delete Invalid -> Optional.empty")
 void deleteInvalid () {
	Db db = mock( Db.class );
	Services services = new Services( db );
	UUID uuid1 = UUID.randomUUID();

	when( db.delete( uuid1.toString() ) ).thenReturn( Optional.empty() );

	Optional<ToDo> actual = services.delete( uuid1.toString() );

	assertThat( actual, equalTo( Optional.empty() ) );
 }
}
