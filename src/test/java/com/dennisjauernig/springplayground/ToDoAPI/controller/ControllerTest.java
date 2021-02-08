package com.dennisjauernig.springplayground.ToDoAPI.controller;

import com.dennisjauernig.springplayground.ToDoAPI.db.Db;
import com.dennisjauernig.springplayground.ToDoAPI.model.ToDo;
import com.dennisjauernig.springplayground.ToDoAPI.model.ToDoWithoutId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

 @LocalServerPort
 private int port;

 @Autowired
 private RestTemplate restTemplate;

 @Autowired
 private Db db;

 @Test
 @DisplayName ("Post -> containsInAnyOrder")
 void postAndList () {
	UUID uuid1 = UUID.randomUUID();

	// Mockbean

	String url = "http://localhost:" + this.port + "/api/todo";
	ResponseEntity<ToDo> response = this.restTemplate.postForEntity(
					url,
					new ToDoWithoutId( "Foobar", "DONE" ),
					ToDo.class );

	List<ToDo> actual = this.db.get();

	assertThat( response.getStatusCode(), is( HttpStatus.OK ) );
	assertThat( response.getBody(), equalTo( new ToDo( uuid1.toString(), "Foobar", "DONE" ) ) );
	assertThat( actual, containsInAnyOrder( new ToDo( uuid1.toString(), "Foobar", "DONE" ) ) );
 }

 @Test
 @DisplayName ("Post existing -> Exception")
 void postExisting () {
	UUID uuid1 = UUID.randomUUID();
	String url = "http://localhost:" + this.port + "/api/todo";

	ResponseEntity<ToDo> response1 = this.restTemplate.postForEntity(
					url,
					new ToDo( uuid1.toString(), "Foobar", "DONE" ),
					ToDo.class );

	assertThat( response1.getStatusCode(), is( HttpStatus.OK ) );
	assertThrows( HttpClientErrorException.class, () -> this.restTemplate.postForEntity(
					url,
					new ToDo( uuid1.toString(), "Foobar", "DONE" ),
					ToDo.class ) );
 }
}
