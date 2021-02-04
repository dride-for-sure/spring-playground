package com.dennisjauernig.springplayground.CoronaAPI.controller;

import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaActiveCases;
import com.dennisjauernig.springplayground.CoronaAPI.services.CoronaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping ("api/corona")
public class CoronaController {

 private final CoronaService coronaService;

 @Autowired
 public CoronaController (CoronaService coronaService) {
	this.coronaService = coronaService;
 }

 @GetMapping ("/average/{country}")
 public ResponseEntity<CoronaActiveCases> getAverageBy (@PathVariable String country) {
	Optional<CoronaActiveCases> response = this.coronaService.getAverageBy( country );
	return response.isEmpty() ? ResponseEntity.badRequest().build() : ResponseEntity.ok( response.get() );
 }

 @GetMapping ("average/{country}/{province}")
 public ResponseEntity<CoronaActiveCases> getAverageBy (@PathVariable String country,
																												@PathVariable String province) {
	Optional<CoronaActiveCases> response = this.coronaService.getAverageBy( country, province );
	return response.isEmpty() ? ResponseEntity.badRequest().build() : ResponseEntity.ok( response.get() );
 }

 @GetMapping ("homeschooling/{country}/{province}")
 public ResponseEntity<String> getHomeSchooling (@PathVariable String country,
																								 @PathVariable String province) {
	Optional<String> response = this.coronaService.getHomeSchooling( country, province );
	return response.isEmpty()
				 ? ResponseEntity.badRequest().build()
				 : ResponseEntity.status( HttpStatus.OK ).contentType( MediaType.APPLICATION_JSON ).body( response.get() );
 }

}