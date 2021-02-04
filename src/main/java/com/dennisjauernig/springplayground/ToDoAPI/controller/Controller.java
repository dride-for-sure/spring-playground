package com.dennisjauernig.springplayground.ToDoAPI.controller;

import com.dennisjauernig.springplayground.ToDoAPI.model.ToDo;
import com.dennisjauernig.springplayground.ToDoAPI.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("api/todo")
public class Controller {

 private Services services;

 @Autowired
 public Controller (Services services) {
	this.services = services;
 }

 @GetMapping
 public ResponseEntity<List<ToDo>> get () {
	Optional<List<ToDo>> response = this.services.get();
	return response.isEmpty() ? ResponseEntity.badRequest().build() : ResponseEntity.ok( response.get() );
 }

 @PostMapping
 public ResponseEntity<ToDo> post (@RequestBody ToDo todo) {
	Optional<ToDo> response = this.services.create( todo );
	return response.isEmpty() ? ResponseEntity.badRequest().build() : ResponseEntity.ok( response.get() );
 }

 @PutMapping ("{id}")
 public ResponseEntity<ToDo> put (@PathVariable String id, @RequestBody ToDo todo) {
	Optional<ToDo> response = this.services.update( id, todo );
	return response.isEmpty() ? ResponseEntity.badRequest().build() : ResponseEntity.ok( response.get() );
 }

 @DeleteMapping ("{id}")
 public void delete (@PathVariable String id) {
	this.services.delete( id );
 }
}
