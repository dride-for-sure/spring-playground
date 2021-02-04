package com.dennisjauernig.springplayground.CoronaAPI.services;

import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaActiveCases;
import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaCountryStatusData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoronaServiceTest {

 @Test
 @DisplayName ("Get by valid country")
 void getByCountryValidTester () {
	CoronaApiService coronaApiService = mock( CoronaApiService.class );
	CoronaService coronaService = new CoronaService( coronaApiService );

	List<CoronaCountryStatusData> responseList = new ArrayList<>( List.of(
					new CoronaCountryStatusData( "germany", "", "100", "date1" ),
					new CoronaCountryStatusData( "germany", "", "200", "date2" )
	) );
	when( coronaApiService.get( "germany" ) ).thenReturn( Optional.of( responseList ) );

	Optional<CoronaActiveCases> actual = coronaService.getAverageBy( "germany" );
	CoronaActiveCases expected = new CoronaActiveCases( "germany", "", 100 );

	assertThat( actual.get(), equalTo( expected ) );
 }

 @Test
 @DisplayName ("Get invalid country")
 void getByCountryInvalidTester () {
	CoronaApiService coronaApiService = mock( CoronaApiService.class );
	CoronaService coronaService = new CoronaService( coronaApiService );

	when( coronaApiService.get( "xy" ) ).thenReturn( Optional.empty() );

	assertThat( coronaService.getAverageBy( "xy" ), equalTo( Optional.empty() ) );
 }

 @Test
 @DisplayName ("Get by valid country and province / 1 Entries")
 void getByCountryProvinceValidWith2EntriesTester () {
	CoronaApiService coronaApiService = mock( CoronaApiService.class );
	CoronaService coronaService = new CoronaService( coronaApiService );

	List<CoronaCountryStatusData> responseList = new ArrayList<>( List.of(
					new CoronaCountryStatusData( "germany", "berlin", "100", "date1" )
	) );
	when( coronaApiService.get( "germany", "berlin" ) ).thenReturn( Optional.of( responseList ) );

	Optional<CoronaActiveCases> actual = coronaService.getAverageBy( "germany", "berlin" );
	CoronaActiveCases expected = new CoronaActiveCases( "germany", "berlin", 0 );

	assertThat( actual.get(), equalTo( expected ) );
 }

 @Test
 @DisplayName ("Get by valid country and province / 3 Entries")
 void getByCountryProvinceValidWith3EntriesTester () {
	CoronaApiService coronaApiService = mock( CoronaApiService.class );
	CoronaService coronaService = new CoronaService( coronaApiService );

	List<CoronaCountryStatusData> responseList = new ArrayList<>( List.of(
					new CoronaCountryStatusData( "germany", "hamburg", "100", "date1" ),
					new CoronaCountryStatusData( "germany", "hamburg", "200", "date2" ),
					new CoronaCountryStatusData( "germany", "hamburg", "300", "date3" )
	) );
	when( coronaApiService.get( "germany", "hamburg" ) ).thenReturn( Optional.of( responseList ) );

	Optional<CoronaActiveCases> actual = coronaService.getAverageBy( "germany", "hamburg" );
	CoronaActiveCases expected = new CoronaActiveCases( "germany", "hamburg", 100 );

	assertThat( actual.get(), equalTo( expected ) );
 }

 @Test
 @DisplayName ("Get by country invalid and province valid")
 void getByCountryInvalidProvinceValidTester () {
	CoronaApiService coronaApiService = mock( CoronaApiService.class );
	CoronaService coronaService = new CoronaService( coronaApiService );

	when( coronaApiService.get( "XY", "berlin" ) ).thenReturn( Optional.empty() );

	assertThat( coronaService.getAverageBy( "XY", "berlin" ), equalTo( Optional.empty() ) );
 }

 @Test
 @DisplayName ("Get by country valid and province invalid")
 void getByCountryValidProvinceInvalidTester () {
	CoronaApiService coronaApiService = mock( CoronaApiService.class );
	CoronaService coronaService = new CoronaService( coronaApiService );

	when( coronaApiService.get( "germany", "xy" ) ).thenReturn( Optional.empty() );

	assertThat( coronaService.getAverageBy( "germany", "xy" ), equalTo( Optional.empty() ) );
 }

 @Test
 @DisplayName ("Get invalid country/province")
 void getByCountryInvalidProvinceInvalidTester () {
	CoronaApiService coronaApiService = mock( CoronaApiService.class );
	CoronaService coronaService = new CoronaService( coronaApiService );

	when( coronaApiService.get( "XY", "xy" ) ).thenReturn( Optional.empty() );

	assertThat( coronaService.getAverageBy( "XY", "xy" ), equalTo( Optional.empty() ) );
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
 @DisplayName ("Average cases with list size 1")
 void averageCasesByCountryListSize1 () {
 }

 @Test
 @DisplayName ("Average cases with list size > 1")
 void averageCasesByCountryListSize3 () {
 }
}
