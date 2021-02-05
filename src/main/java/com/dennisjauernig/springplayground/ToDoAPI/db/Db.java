package com.dennisjauernig.springplayground.ToDoAPI.db;

import com.dennisjauernig.springplayground.ToDoAPI.model.ToDo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class Db {

 private final Map<UUID, ToDo> db;

 public Db () {
	this.db = new HashMap<>();
 }

 public Optional<List<ToDo>> get () {
	return Optional.of( new ArrayList<>( this.db.values() ) );
 }

 public Optional<ToDo> create (ToDo todo) {
	UUID uuid = UUID.fromString( todo.getId() );
	if ( this.db.containsKey( uuid ) ) {
	 return Optional.empty();
	}
	this.db.put( UUID.fromString( todo.getId() ), todo );
	return Optional.of( this.db.get( UUID.fromString( todo.getId() ) ) );
 }

 public Optional<ToDo> update (String id, ToDo todo) {
	UUID uuid = UUID.fromString( id );
	if ( this.db.containsKey( uuid ) ) {
	 this.db.put( uuid, todo );
	 return Optional.of( this.db.get( uuid ) );
	}
	return Optional.empty();
 }

 public Optional<ToDo> delete (String id) {
	UUID uuid = UUID.fromString( id );
	if ( this.db.containsKey( uuid ) ) {
	 ToDo response = this.db.get( uuid );
	 this.db.remove( UUID.fromString( id ) );
	 return Optional.of( response );
	}
	return Optional.empty();
 }
}
