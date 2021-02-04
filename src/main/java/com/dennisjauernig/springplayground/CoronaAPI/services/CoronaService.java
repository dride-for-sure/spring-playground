package com.dennisjauernig.springplayground.CoronaAPI.services;

import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaActiveCases;
import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaCountryStatusData;
import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaProvinceStatusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Service
public class CoronaService {

 private final CoronaApiService coronaApiService;

 @Autowired
 public CoronaService (CoronaApiService coronaApiService) {
	this.coronaApiService = coronaApiService;
 }

 public CoronaActiveCases getByCountryAverage (String country) {
	Optional<List<CoronaCountryStatusData>> list = this.coronaApiService.getByCountry( country );
	if ( list.isPresent() ) {
	 return new CoronaActiveCases( country, "", this.calcAverageCasesByCountry( list.get() ) );
	} else {
	 throw new ResponseStatusException( HttpStatus.NOT_FOUND );
	}
 }

 public CoronaActiveCases getByProvinceAverage (String country, String province) {
	Optional<List<CoronaProvinceStatusData>> list = this.coronaApiService.getByCountryAndProvince( country, province );
	if ( list.isPresent() ) {
	 return new CoronaActiveCases( country, province, this.calcAverageCasesByProvince( list.get() ) );
	} else {
	 throw new ResponseStatusException( HttpStatus.NOT_FOUND );
	}
 }

 public List<String> getHomeSchooling (String country, String province) {
	Optional<List<CoronaProvinceStatusData>> list = this.coronaApiService.getByCountryAndProvince( country, province );
	if ( list.isPresent() ) {
	 return List.of( country, province, String.valueOf( this.calcHomeSchooling( list.get() ) ) );
	} else {
	 throw new ResponseStatusException( HttpStatus.NOT_FOUND );
	}
 }

 private boolean calcHomeSchooling (List<CoronaProvinceStatusData> list) {
	int average = this.calcAverageCasesByProvince( list );
	return average > 100;
 }

 private int calcAverageCasesByCountry (List<CoronaCountryStatusData> list) {
	int average;
	if ( list.size() < 2 ) {
	 average = 0;
	} else {
	 int diff = parseInt( list.get( list.size() - 1 ).getCases() ) - parseInt( list.get( 0 ).getCases() );
	 average = diff / ( list.size() - 1 );
	}
	return average;
 }

 private int calcAverageCasesByProvince (List<CoronaProvinceStatusData> list) {
	int average;
	if ( list.size() < 2 ) {
	 average = 0;
	} else {
	 int diff = parseInt( list.get( list.size() - 1 ).getConfirmed() ) - parseInt( list.get( 0 ).getConfirmed() );
	 average = diff / ( list.size() - 1 );
	}
	return average;
 }
}
