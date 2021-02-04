package com.dennisjauernig.springplayground.CoronaAPI.services;

import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaCountryStatusData;
import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaProvinceStatusData;
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

 public CoronaApiService (RestTemplate restTemplate) {
	this.restTemplate = restTemplate;
 }

 public Optional<List<CoronaCountryStatusData>> getByCountry (String country) {

	LocalDate toDate = LocalDate.now();
	LocalDate fromDate = toDate.minusDays( 7 );

	String url = "https://api.covid19api.com/country/" + country + "/status/confirmed?from=" + fromDate.toString() + "T00:00:00Z" +
					"&to=" + toDate.toString() + "T00:00:00Z";

	ResponseEntity<CoronaCountryStatusData[]> response = this.restTemplate.getForEntity( url,
					CoronaCountryStatusData[].class );

	if ( response.getStatusCode().equals( HttpStatus.OK ) ) {
	 List<CoronaCountryStatusData> list = Arrays.asList( response.getBody() );
	 return Optional.of( list );
	}
	return Optional.empty();
 }

 public Optional<List<CoronaProvinceStatusData>> getByCountryAndProvince (String country, String province) {

	LocalDate toDate = LocalDate.now();
	LocalDate fromDate = toDate.minusDays( 7 );

	String url = "https://api.covid19api.com/live/country/" + country + "/status/confirmed?from=" + fromDate.toString() + "T00:00:00Z" +
					"&to=" + toDate.toString() + "T00:00:00Z";

	ResponseEntity<CoronaProvinceStatusData[]> response = this.restTemplate.getForEntity( url,
					CoronaProvinceStatusData[].class );

	if ( response.getStatusCode().equals( HttpStatus.OK ) ) {
	 List<CoronaProvinceStatusData> list = Arrays.asList( response.getBody() );
	 return Optional.of( list.stream().filter( el -> el.getProvince().toLowerCase().equals( province ) ).collect( Collectors.toList() ) );
	}
	return Optional.empty();
 }
}

