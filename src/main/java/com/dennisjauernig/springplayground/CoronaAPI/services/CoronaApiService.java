package com.dennisjauernig.springplayground.CoronaAPI.services;

import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaCountryStatusData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoronaApiService {

 private final RestTemplate restTemplate;
 private final Time time;

 public CoronaApiService (RestTemplate restTemplate, Time time) {

	this.restTemplate = restTemplate;
	this.time = time;
 }

 public Optional<List<CoronaCountryStatusData>> get (String country) {
	ResponseEntity<CoronaCountryStatusData[]> response = this.getResponseEntity( country, false );

	if ( response.getStatusCode().equals( HttpStatus.OK ) && response.hasBody() ) {
	 List<CoronaCountryStatusData> list = Arrays.asList( response.getBody() );
	 return Optional.of( list );
	}
	return Optional.empty();
 }

 public Optional<List<CoronaCountryStatusData>> get (String country, String province) {
	ResponseEntity<CoronaCountryStatusData[]> response = this.getResponseEntity( country, true );

	if ( response.getStatusCode().equals( HttpStatus.OK ) && response.hasBody() ) {
	 List<CoronaCountryStatusData> list = Arrays.asList( response.getBody() );
	 List<CoronaCountryStatusData> filtered =
					 list.stream().filter( el -> el.getProvince().toLowerCase().equals( province ) ).collect( Collectors.toList() );
	 return filtered.size() == 0 ? Optional.empty() : Optional.of( filtered );
	}
	return Optional.empty();
 }

 private ResponseEntity<CoronaCountryStatusData[]> getResponseEntity (String country, boolean live) {
	LocalDate toDate = this.time.getLocalTime();
	LocalDate fromDate = toDate.minusDays( 7 );

	String url = "https://api.covid19api.com/";
	if ( live ) {
	 url += "live/country/" + country + "/status/confirmed";
	} else {
	 url += "country/" + country;
	}
	url += "?from=" + fromDate.toString() + "T00:00:00Z&to=" + toDate.toString() + "T00:00:00Z";

	return this.restTemplate.getForEntity( url, CoronaCountryStatusData[].class );
 }
}

