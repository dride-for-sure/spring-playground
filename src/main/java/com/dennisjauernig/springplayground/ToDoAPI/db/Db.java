package com.dennisjauernig.springplayground.ToDoAPI.db;

import com.dennisjauernig.springplayground.ToDoAPI.model.ToDo;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class Db {

 private final Map<UUID, ToDo> db = new HashMap<>();

 public Optional<List<ToDo>> list () {
	return Optional.of( this.db.values().stream().collect( Collectors.toList() ) );
 }

 public Optional<ToDo> create (ToDo todo) {
	this.db.put( todo.getUuid(), todo );
	return Optional.of( this.db.get( todo.getUuid() ) );
 }

 public Optional<ToDo> update (String id, ToDo todo) {
	UUID uuid = UUID.fromString( id );
	this.db.put( uuid, todo );
	return Optional.of( this.db.get( uuid ) );
 }

 public void delete (String id) {
	this.db.remove( id );
 }
}
