package com.dennisjauernig.springplayground.CoronaAPI.services;

import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaActiveCases;
import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaCountryStatusData;
import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaProvinceStatusData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoronaServiceTest {

 @Test
 @DisplayName ("Get by valid country")
 void getByCountryValidTester () {
	CoronaApiService coronaApiService = mock( CoronaApiService.class );
	CoronaService coronaService = new CoronaService( coronaApiService );

	List<CoronaCountryStatusData> responseList = new ArrayList<>( List.of(
					new CoronaCountryStatusData( "germany", "100", "date1" ),
					new CoronaCountryStatusData( "germany", "200", "date2" )
	) );
	when( coronaApiService.getByCountry( "germany" ) ).thenReturn( Optional.of( responseList ) );

	CoronaActiveCases actual = coronaService.getByCountryAverage( "germany" );
	CoronaActiveCases expected = new CoronaActiveCases( "germany", "", 100 );

	assertThat( expected, equalTo( actual ) );
 }

 @Test
 @DisplayName ("Get invalid country")
 void getByCountryInvalidTester () {
	CoronaApiService coronaApiService = mock( CoronaApiService.class );
	CoronaService coronaService = new CoronaService( coronaApiService );

	when( coronaApiService.getByCountry( "xy" ) ).thenReturn( Optional.empty() );

	assertThrows( ResponseStatusException.class, () -> coronaService.getByCountryAverage( "xy" ) );
 }

 @Test
 @DisplayName ("Get by valid country and province / 1 Entries")
 void getByCountryProvinceValidWith2EntriesTester () {
	CoronaApiService coronaApiService = mock( CoronaApiService.class );
	CoronaService coronaService = new CoronaService( coronaApiService );

	List<CoronaProvinceStatusData> responseList = new ArrayList<>( List.of(
					new CoronaProvinceStatusData( "germany", "berlin", "100", "date1" )
	) );
	when( coronaApiService.getByCountryAndProvince( "germany", "berlin" ) ).thenReturn( Optional.of( responseList ) );

	CoronaActiveCases actual = coronaService.getByProvinceAverage( "germany", "berlin" );
	CoronaActiveCases expected = new CoronaActiveCases( "germany", "berlin", 0 );

	assertThat( actual, equalTo( expected ) );
 }

 @Test
 @DisplayName ("Get by valid country and province / 3 Entries")
 void getByCountryProvinceValidWith3EntriesTester () {
	CoronaApiService coronaApiService = mock( CoronaApiService.class );
	CoronaService coronaService = new CoronaService( coronaApiService );

	List<CoronaProvinceStatusData> responseList = new ArrayList<>( List.of(
					new CoronaProvinceStatusData( "germany", "hamburg", "100", "date1" ),
					new CoronaProvinceStatusData( "germany", "hamburg", "200", "date2" ),
					new CoronaProvinceStatusData( "germany", "hamburg", "300", "date3" )
	) );
	when( coronaApiService.getByCountryAndProvince( "germany", "hamburg" ) ).thenReturn( Optional.of( responseList ) );

	CoronaActiveCases actual = coronaService.getByProvinceAverage( "germany", "hamburg" );
	CoronaActiveCases expected = new CoronaActiveCases( "germany", "hamburg", 100 );

	assertThat( expected, equalTo( actual ) );
 }

 @Test
 @DisplayName ("Get by country invalid and province valid")
 void getByCountryInvalidProvinceValidTester () {
	CoronaApiService coronaApiService = mock( CoronaApiService.class );
	CoronaService coronaService = new CoronaService( coronaApiService );

	when( coronaApiService.getByCountryAndProvince( "XY", "berlin" ) ).thenReturn( Optional.empty() );

	assertThrows( ResponseStatusException.class, () -> coronaService.getByProvinceAverage( "XY", "berlin" ) );
 }

 @Test
 @DisplayName ("Get by country valid and province invalid")
 void getByCountryValidProvinceInvalidTester () {
	CoronaApiService coronaApiService = mock( CoronaApiService.class );
	CoronaService coronaService = new CoronaService( coronaApiService );

	when( coronaApiService.getByCountryAndProvince( "germany", "xy" ) ).thenReturn( Optional.empty() );

	assertThrows( ResponseStatusException.class, () -> coronaService.getByProvinceAverage( "germany", "xy" ) );
 }

 @Test
 @DisplayName ("Get invalid country/province")
 void getByCountryInvalidProvinceInvalidTester () {
	CoronaApiService coronaApiService = mock( CoronaApiService.class );
	CoronaService coronaService = new CoronaService( coronaApiService );

	when( coronaApiService.getByCountryAndProvince( "XY", "xy" ) ).thenReturn( Optional.empty() );

	assertThrows( ResponseStatusException.class, () -> coronaService.getByProvinceAverage( "XY", "xy" ) );
 }

 @Test
 @DisplayName ("Homeschooling true")
 void calcHomeSchoolingTrue () {
 }

 @Test
 @DisplayName ("HomeSchooling false")
 void calcHomeSchoolingFalse () {
 }

 @Test
 @DisplayName ("Average cases by country with list size 1")
 void averageCasesByCountryListSize1 () {

 }

 @Test
 @DisplayName ("Average cases by country with list size > 1")
 void averageCasesByCountryListSize3 () {
 }

 @Test
 @DisplayName ("Average cases by province with list size 1")
 void averageCasesByProvinceListSize1 () {

 }

 @Test
 @DisplayName ("Average cases by province with list size > 1")
 void averageCasesByProvinceListSize3 () {
 }
}
