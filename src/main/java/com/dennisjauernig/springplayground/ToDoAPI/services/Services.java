package com.dennisjauernig.springplayground.ToDoAPI.services;

import com.dennisjauernig.springplayground.ToDoAPI.db.Db;
import com.dennisjauernig.springplayground.ToDoAPI.model.ToDo;
import com.dennisjauernig.springplayground.ToDoAPI.model.ToDoWithoutId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class Services {

 public Db db;

 @Autowired
 public Services (Db db) {
	this.db = db;
 }

 public List<ToDo> get () {
	return this.db.get();
 }

 public Optional<ToDo> create (ToDoWithoutId toDoWithoutId) {
	UUID uuid = UUID.randomUUID();
	ToDo toDo = new ToDo( uuid.toString(), toDoWithoutId.getDesc(), toDoWithoutId.getStatus() );
	return this.db.create( toDo );
 }

 public Optional<ToDo> update (String id, ToDo todo) {
	return this.db.update( id, todo );
 }

 public Optional<ToDo> delete (String id) {
	return this.db.delete( id );
 }
}
