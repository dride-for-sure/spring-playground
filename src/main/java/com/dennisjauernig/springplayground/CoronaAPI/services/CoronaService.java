package com.dennisjauernig.springplayground.CoronaAPI.services;

import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaActiveCases;
import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaCountryStatusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

 public Optional<CoronaActiveCases> getAverageBy (String country) {
	Optional<List<CoronaCountryStatusData>> list = this.coronaApiService.get( country );
	return list.map( coronaCountryStatusData -> new CoronaActiveCases( country, "", this.calcAverageCases( coronaCountryStatusData ) ) );
 }

 public Optional<CoronaActiveCases> getAverageBy (String country, String province) {
	Optional<List<CoronaCountryStatusData>> list = this.coronaApiService.get( country, province );
	return list.map( coronaCountryStatusData -> new CoronaActiveCases( country, province,
					this.calcAverageCases( coronaCountryStatusData ) ) );
 }

 public Optional<String> getHomeSchooling (String country, String province) {
	Optional<List<CoronaCountryStatusData>> list = this.coronaApiService.get( country, province );
	return list.map( coronaCountryStatusData -> "{ " +
					"\"country\": \"" + country + "\"," +
					" \"province\": \"" + province + "\"," +
					" \"homeschooling\": \"" + this.hasHomeSchooling( coronaCountryStatusData ) +
					"\"}" );
 }

 private boolean hasHomeSchooling (List<CoronaCountryStatusData> list) {
	int average = this.calcAverageCases( list );
	return average > 100;
 }

 private int calcAverageCases (List<CoronaCountryStatusData> list) {
	if ( list.size() < 2 ) {
	 return 0;
	} else {
	 int diff = parseInt( list.get( list.size() - 1 ).getConfirmed() ) - parseInt( list.get( 0 ).getConfirmed() );
	 return diff / ( list.size() - 1 );
	}
 }
}
