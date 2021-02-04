package com.dennisjauernig.springplayground.CoronaAPI.services;

import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaCountryStatusData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
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
	TimeService timeService = mock( TimeService.class );
	CoronaApiService coronaApiService = new CoronaApiService( restTemplate, timeService );

	String url = "https://api.covid19api.com/country/germany?from=2021-01-01T00:00:00Z&to=2021-01-08T00:00:00Z";
	CoronaCountryStatusData[] mockedData = {
					new CoronaCountryStatusData( "germany", "", "100", "2021-01-28T00:00:00Z" ),
					new CoronaCountryStatusData( "germany", "", "200", "2021-01-28T00:00:00Z" )
	};

	LocalDate date = LocalDate.of( 2021, 1, 8 );
	when( timeService.getLocalTime() ).thenReturn( date );

	when( restTemplate.getForEntity( url, CoronaCountryStatusData[].class ) ).thenReturn( new ResponseEntity<>( mockedData, HttpStatus.OK ) );
	Optional<List<CoronaCountryStatusData>> actual = coronaApiService.get( "germany" );

	List<CoronaCountryStatusData> expected = new ArrayList<>( List.of(
					new CoronaCountryStatusData( "germany", "", "100", "2021-01-28T00:00:00Z" ),
					new CoronaCountryStatusData( "germany", "", "200", "2021-01-28T00:00:00Z" )
	) );
	assertThat( expected, equalTo( actual.get() ) );
 }

 @Test
 @DisplayName ("Get by country invalid")
 void getByCountryInvalidRequestTester () {
	RestTemplate restTemplate = mock( RestTemplate.class );
	TimeService timeService = mock( TimeService.class );
	CoronaApiService coronaApiService = new CoronaApiService( restTemplate, timeService );

	String url = "https://api.covid19api.com/country/germany?from=2021-01-01T00:00:00Z&to=2021-01-08T00:00:00Z";
	CoronaCountryStatusData[] mockedData = {
					new CoronaCountryStatusData( "germany", "", "100", "2021-01-28T00:00:00Z" ),
					new CoronaCountryStatusData( "germany", "", "200", "2021-01-28T00:00:00Z" )
	};

	LocalDate date = LocalDate.of( 2021, 1, 8 );
	when( timeService.getLocalTime() ).thenReturn( date );

	when( restTemplate.getForEntity( url, CoronaCountryStatusData[].class ) ).thenReturn( new ResponseEntity<>( mockedData, HttpStatus.BAD_REQUEST ) );
	Optional<List<CoronaCountryStatusData>> actual = coronaApiService.get( "germany" );

	assertThat( Optional.empty(), equalTo( actual ) );
 }

 @Test
 @DisplayName ("Get by country and province valid")
 void getByCountryAndProvinceValidRequestTester () {
	RestTemplate restTemplate = mock( RestTemplate.class );
	TimeService timeService = mock( TimeService.class );
	CoronaApiService coronaApiService = new CoronaApiService( restTemplate, timeService );

	String url = "https://api.covid19api.com/live/country/germany/status/confirmed?from=2021-01-01T00:00:00Z&to=2021-01" +
					"-08T00:00:00Z";
	CoronaCountryStatusData[] mockedData = {
					new CoronaCountryStatusData( "germany", "hamburg", "100", "2021-01-28T00:00:00Z" ),
					new CoronaCountryStatusData( "germany", "berlin", "200", "2021-01-28T00:00:00Z" )
	};

	LocalDate date = LocalDate.of( 2021, 1, 8 );
	when( timeService.getLocalTime() ).thenReturn( date );

	when( restTemplate.getForEntity( url, CoronaCountryStatusData[].class ) ).thenReturn( new ResponseEntity<>( mockedData, HttpStatus.OK ) );
	Optional<List<CoronaCountryStatusData>> actual = coronaApiService.get( "germany", "berlin" );

	List<CoronaCountryStatusData> expected = new ArrayList<>( List.of(
					new CoronaCountryStatusData( "germany", "berlin", "200", "2021-01-28T00:00:00Z" )
	) );
	assertThat( expected, equalTo( actual.get() ) );
 }

 @Test
 @DisplayName ("Get by country and province invalid")
 void getByCountryAndProvinceInvalidRequestTester () {
	RestTemplate restTemplate = mock( RestTemplate.class );
	TimeService timeService = mock( TimeService.class );
	CoronaApiService coronaApiService = new CoronaApiService( restTemplate, timeService );

	String url = "https://api.covid19api.com/live/country/germany/status/confirmed?from=2021-01-01T00:00:00Z&to=2021-01" +
					"-08T00:00:00Z";
	CoronaCountryStatusData[] mockedData = {
					new CoronaCountryStatusData( "germany", "hamburg", "100", "2021-01-28T00:00:00Z" ),
					new CoronaCountryStatusData( "germany", "berlin", "200", "2021-01-28T00:00:00Z" )
	};

	LocalDate date = LocalDate.of( 2021, 1, 8 );
	when( timeService.getLocalTime() ).thenReturn( date );

	when( restTemplate.getForEntity( url, CoronaCountryStatusData[].class ) ).thenReturn( new ResponseEntity<>( mockedData, HttpStatus.BAD_REQUEST ) );
	Optional<List<CoronaCountryStatusData>> actual = coronaApiService.get( "germany", "berlin" );

	assertThat( Optional.empty(), equalTo( actual ) );
 }

 @Test
 @DisplayName ("Get by country and province empty matches")
 void getByCountryAndProvinceEmptyMatches () {
	RestTemplate restTemplate = mock( RestTemplate.class );
	TimeService timeService = mock( TimeService.class );
	CoronaApiService coronaApiService = new CoronaApiService( restTemplate, timeService );

	String url = "https://api.covid19api.com/live/country/germany/status/confirmed?from=2021-01-01T00:00:00Z&to=2021-01" +
					"-08T00:00:00Z";
	CoronaCountryStatusData[] mockedData = {
					new CoronaCountryStatusData( "germany", "hamburg", "100", "2021-01-28T00:00:00Z" ),
					new CoronaCountryStatusData( "germany", "hamburg", "200", "2021-01-28T00:00:00Z" )
	};
	LocalDate date = LocalDate.of( 2021, 1, 8 );
	when( timeService.getLocalTime() ).thenReturn( date );

	when( restTemplate.getForEntity( url, CoronaCountryStatusData[].class ) ).thenReturn( new ResponseEntity<>( mockedData, HttpStatus.OK ) );
	Optional<List<CoronaCountryStatusData>> actual = coronaApiService.get( "germany", "berlin" );

	assertThat( Optional.empty(), equalTo( actual ) );
 }
}
