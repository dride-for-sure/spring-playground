package com.dennisjauernig.springplayground.ToDoAPI.controller;

import com.dennisjauernig.springplayground.ToDoAPI.db.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

 @LocalServerPort
 private int port;

 @Autowired
 private RestTemplate restTemplate;

 @Autowired
 private Db db;

 // TODO: MOCK RANDOM
 /*
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
 */

}
