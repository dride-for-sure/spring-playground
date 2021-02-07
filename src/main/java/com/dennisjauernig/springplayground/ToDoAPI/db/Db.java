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

 public List<ToDo> get () {
	return new ArrayList<>( this.db.values() );
 }

 public Optional<ToDo> create (ToDo toDo) {
	if ( this.db.containsKey( UUID.fromString( toDo.getId() ) ) ) {
	 return Optional.empty();
	}
	this.db.put( UUID.fromString( toDo.getId() ), toDo );
	return Optional.of( toDo );
 }

 // Update: return todo + update notification
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
