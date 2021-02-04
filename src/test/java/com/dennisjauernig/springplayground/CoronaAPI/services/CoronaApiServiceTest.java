package com.dennisjauernig.springplayground.CoronaAPI.services;

import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaCountryStatusData;
import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaProvinceStatusData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoronaApiServiceTest {

 @Test
 @DisplayName ("Get by country valid")
 void getByCountryValidRequestTester () {
	RestTemplate restTemplate = mock( RestTemplate.class );
	CoronaApiService coronaApiService = new CoronaApiService( restTemplate );

	String url = "https://api.covid19api.com/country/germany/status/confirmed?from=2021-01-28T00:00:00Z&to=2021-02-04T00:00:00Z";
	CoronaCountryStatusData[] mockedData = {
					new CoronaCountryStatusData( "germany", "100", "2021-01-28T00:00:00Z" ),
					new CoronaCountryStatusData( "germany", "200", "2021-01-28T00:00:00Z" )
	};

	when( restTemplate.getForEntity( url, CoronaCountryStatusData[].class ) ).thenReturn( new ResponseEntity<>( mockedData, HttpStatus.OK ) );
	Optional<List<CoronaCountryStatusData>> actual = coronaApiService.getByCountry( "germany" );

	List<CoronaCountryStatusData> expected = new ArrayList<>( List.of(
					new CoronaCountryStatusData( "germany", "100", "2021-01-28T00:00:00Z" ),
					new CoronaCountryStatusData( "germany", "200", "2021-01-28T00:00:00Z" )
	) );
	assertThat( expected, equalTo( actual.get() ) );
 }

 @Test
 @DisplayName ("Get by country invalid")
 void getByCountryInvalidRequestTester () {
	RestTemplate restTemplate = mock( RestTemplate.class );
	CoronaApiService coronaApiService = new CoronaApiService( restTemplate );

	String url = "https://api.covid19api.com/country/germany/status/confirmed?from=2021-01-28T00:00:00Z&to=2021-02-04T00:00:00Z";
	CoronaCountryStatusData[] mockedData = {
					new CoronaCountryStatusData( "germany", "100", "2021-01-28T00:00:00Z" ),
					new CoronaCountryStatusData( "germany", "200", "2021-01-28T00:00:00Z" )
	};

	when( restTemplate.getForEntity( url, CoronaCountryStatusData[].class ) ).thenReturn( new ResponseEntity<>( mockedData, HttpStatus.BAD_REQUEST ) );
	Optional<List<CoronaCountryStatusData>> actual = coronaApiService.getByCountry( "germany" );

	assertThat( Optional.empty(), equalTo( actual ) );
 }

 @Test
 @DisplayName ("Get by country and province valid")
 void getByCountryAndProvinceValidRequestTester () {
	RestTemplate restTemplate = mock( RestTemplate.class );
	CoronaApiService coronaApiService = new CoronaApiService( restTemplate );

	String url = "https://api.covid19api.com/live/country/germany/status/confirmed?from=2021-01-28T00:00:00Z&to=2021-02" +
					"-04T00:00:00Z";
	CoronaProvinceStatusData[] mockedData = {
					new CoronaProvinceStatusData( "germany", "hamburg", "100", "2021-01-28T00:00:00Z" ),
					new CoronaProvinceStatusData( "germany", "berlin", "200", "2021-01-28T00:00:00Z" )
	};

	when( restTemplate.getForEntity( url, CoronaProvinceStatusData[].class ) ).thenReturn( new ResponseEntity<>( mockedData, HttpStatus.OK ) );
	Optional<List<CoronaProvinceStatusData>> actual = coronaApiService.getByCountryAndProvince( "germany", "berlin" );

	List<CoronaProvinceStatusData> expected = new ArrayList<>( List.of(
					new CoronaProvinceStatusData( "germany", "berlin", "200", "2021-01-28T00:00:00Z" )
	) );
	assertThat( expected, equalTo( actual.get() ) );
 }

 @Test
 @DisplayName ("Get by country and province invalid")
 void getByCountryAndProvinceInvalidRequestTester () {
	RestTemplate restTemplate = mock( RestTemplate.class );
	CoronaApiService coronaApiService = new CoronaApiService( restTemplate );

	String url = "https://api.covid19api.com/live/country/germany/status/confirmed?from=2021-01-28T00:00:00Z&to=2021-02" +
					"-04T00:00:00Z";
	CoronaProvinceStatusData[] mockedData = {
					new CoronaProvinceStatusData( "germany", "hamburg", "100", "2021-01-28T00:00:00Z" ),
					new CoronaProvinceStatusData( "germany", "berlin", "200", "2021-01-28T00:00:00Z" )
	};

	when( restTemplate.getForEntity( url, CoronaProvinceStatusData[].class ) ).thenReturn( new ResponseEntity<>( mockedData, HttpStatus.BAD_REQUEST ) );
	Optional<List<CoronaProvinceStatusData>> actual = coronaApiService.getByCountryAndProvince( "germany", "berlin" );

	assertThat( Optional.empty(), equalTo( actual ) );
 }

 @Test
 @DisplayName ("Get by country and province empty matches")
 void getByCountryAndProvinceEmptyMatches () {
	RestTemplate restTemplate = mock( RestTemplate.class );
	CoronaApiService coronaApiService = new CoronaApiService( restTemplate );

	String url = "https://api.covid19api.com/live/country/germany/status/confirmed?from=2021-01-28T00:00:00Z&to=2021-02" +
					"-04T00:00:00Z";
	CoronaProvinceStatusData[] mockedData = {
					new CoronaProvinceStatusData( "germany", "hamburg", "100", "2021-01-28T00:00:00Z" ),
					new CoronaProvinceStatusData( "germany", "hamburg", "200", "2021-01-28T00:00:00Z" )
	};

	when( restTemplate.getForEntity( url, CoronaProvinceStatusData[].class ) ).thenReturn( new ResponseEntity<>( mockedData, HttpStatus.OK ) );
	Optional<List<CoronaProvinceStatusData>> actual = coronaApiService.getByCountryAndProvince( "germany", "berlin" );

	List<CoronaProvinceStatusData> expected = new ArrayList<>();
	assertThat( expected, equalTo( actual.get() ) );
 }
}
