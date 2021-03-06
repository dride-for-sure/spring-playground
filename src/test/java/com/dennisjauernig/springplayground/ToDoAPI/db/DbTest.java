package com.dennisjauernig.springplayground.ToDoAPI.db;

import com.dennisjauernig.springplayground.ToDoAPI.model.ToDo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DbTest {

 @Test
 @DisplayName ("Create valid -> containsInAnyOrder")
 void addValid () {
	Db db = new Db();
	UUID uuid1 = UUID.randomUUID();
	UUID uuid2 = UUID.randomUUID();
	db.create( new ToDo( uuid1.toString(), "FooBar", "OPEN" ) );
	db.create( new ToDo( uuid2.toString(), "FooBar", "DONE" ) );

	List<ToDo> actual = db.get();

	ToDo todo1 = new ToDo( uuid1.toString(), "FooBar", "OPEN" );
	ToDo todo2 = new ToDo( uuid2.toString(), "FooBar", "DONE" );

	assertThat( actual, containsInAnyOrder( todo1, todo2 ) );
 }

 @Test
 @DisplayName ("Create existing -> Optional.empty")
 void addExisting () {
	Db db = new Db();
	UUID uuid1 = UUID.randomUUID();
	UUID uuid2 = UUID.randomUUID();
	db.create( new ToDo( uuid1.toString(), "FooBar", "OPEN" ) );

	Optional<ToDo> response = db.create( new ToDo( uuid1.toString(), "FooBar", "DONE" ) );
	List<ToDo> actual = db.get();

	ToDo todo1 = new ToDo( uuid1.toString(), "FooBar", "OPEN" );
	ToDo todo2 = new ToDo( uuid2.toString(), "FooBar", "DONE" );

	assertThat( response, equalTo( Optional.empty() ) );
	assertEquals( actual.size(), 1 );
 }

 @Test
 @DisplayName ("Empty List-> Empty List")
 void listEmpty () {
	Db db = new Db();
	List<ToDo> actual = db.get();
	assertThat( actual, equalTo( new ArrayList<>() ) );
 }

 @Test
 @DisplayName ("Update valid -> hasItem")
 void updateValid () {
	Db db = new Db();
	UUID uuid1 = UUID.randomUUID();
	db.create( new ToDo( uuid1.toString(), "FooBar", "OPEN" ) );

	Optional<ToDo> response = db.update( uuid1.toString(), new ToDo( uuid1.toString(), "FooBar",
					"OPEN" ) );
	List<ToDo> actual = db.get();

	assertThat( response.get(), equalTo( new ToDo( uuid1.toString(), "FooBar", "OPEN" ) ) );
	assertThat( actual, hasItem( new ToDo( uuid1.toString(), "FooBar", "OPEN" ) ) );
 }

 @Test
 @DisplayName ("Update invalid -> Optional.empty")
 void updateInvalid () {
	Db db = new Db();
	UUID uuid1 = UUID.randomUUID();
	UUID uuid2 = UUID.randomUUID();
	db.create( new ToDo( uuid1.toString(), "FooBar", "OPEN" ) );

	Optional<ToDo> response = db.update( uuid2.toString(), new ToDo( uuid2.toString(), "FooBar",
					"OPEN" ) );
	List<ToDo> actual = db.get();

	assertThat( response, equalTo( Optional.empty() ) );
	assertThat( actual, not( hasItem( new ToDo( uuid2.toString(), "FooBar", "OPEN" ) ) ) );
 }

 @Test
 @DisplayName ("Delete valid -> not(hasItem)")
 void deleteValid () {
	Db db = new Db();
	UUID uuid1 = UUID.randomUUID();
	UUID uuid2 = UUID.randomUUID();
	db.create( new ToDo( uuid1.toString(), "FooBar", "OPEN" ) );
	db.create( new ToDo( uuid2.toString(), "FooBar", "OPEN" ) );

	Optional<ToDo> response = db.delete( uuid1.toString() );
	List<ToDo> actual = db.get();

	assertThat( response.get(), equalTo( new ToDo( uuid1.toString(), "FooBar", "OPEN" ) ) );
	assertThat( actual, not( hasItem( new ToDo( uuid1.toString(), "FooBar", "OPEN" ) ) ) );
 }

 @Test
 @DisplayName ("Delete valid -> list is empty -> empty list")
 void deleteValidAndListIsEmpty () {
	Db db = new Db();
	UUID uuid1 = UUID.randomUUID();
	db.create( new ToDo( uuid1.toString(), "FooBar", "OPEN" ) );

	Optional<ToDo> response = db.delete( uuid1.toString() );
	List<ToDo> actual = db.get();

	assertThat( response.get(), equalTo( new ToDo( uuid1.toString(), "FooBar", "OPEN" ) ) );
	assertThat( actual, equalTo( new ArrayList<>() ) );
 }

 @Test
 @DisplayName ("Delete invalid -> Optional.empty")
 void deleteInvalid () {
	Db db = new Db();
	UUID uuid1 = UUID.randomUUID();
	UUID uuid2 = UUID.randomUUID();
	db.create( new ToDo( uuid1.toString(), "FooBar", "OPEN" ) );

	Optional<ToDo> response = db.delete( uuid2.toString() );
	List<ToDo> actual = db.get();

	assertThat( response, equalTo( Optional.empty() ) );
	assertThat( actual, hasItem( new ToDo( uuid1.toString(), "FooBar", "OPEN" ) ) );
 }
}
