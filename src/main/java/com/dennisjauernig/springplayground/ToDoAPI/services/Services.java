package com.dennisjauernig.springplayground.ToDoAPI.services;

import com.dennisjauernig.springplayground.ToDoAPI.db.Db;
import com.dennisjauernig.springplayground.ToDoAPI.model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Services {

 public Db db;

 @Autowired
 public Services (Db db) {
	this.db = db;
 }

 public Optional<List<ToDo>> get () {
	return this.db.get();
 }

 public Optional<ToDo> create (ToDo todo) {
	return this.db.create( todo );
 }

 public Optional<ToDo> update (String id, ToDo todo) {
	return this.db.update( id, todo );
 }

 public Optional<ToDo> delete (String id) {
	return this.db.delete( id );
 }
}
