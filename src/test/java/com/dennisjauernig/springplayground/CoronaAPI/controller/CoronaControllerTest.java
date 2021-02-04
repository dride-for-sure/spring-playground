package com.dennisjauernig.springplayground.CoronaAPI.controller;

import com.dennisjauernig.springplayground.CoronaAPI.services.CoronaApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoronaControllerTest {

 @LocalServerPort
 private int port;

 @Autowired
 private RestTemplate restTemplate;

 @Autowired
 private CoronaApiService coronaApiService;
 private CoronaController coronaController;

 @Test
 @DisplayName ("Get week average with covid api offline")
 void getWithApiOffline () {
  
 }

 @Test
 @DisplayName ("Get week average by valid country")
 void getByValidCountry () {

 }

 @Test
 @DisplayName ("Get week average by invalid country")
 void getByInvalidCountry () {

 }

 @Test
 @DisplayName ("Get week average by valid country and valid province")
 void getByValidCountryAndProvince () {
 }

 @Test
 @DisplayName ("Get week average by invalid country and valid province")
 void getByInvalidCountryAndValidProvince () {

 }

 @Test
 @DisplayName ("Get week average by valid country and invalid province")
 void getByValidCountryAndInvalidProvince () {

 }

 @Test
 @DisplayName ("Get week average by invalid country and invalid province")
 void getByInvalidCountryAndProvince () {

 }

 @Test
 @DisplayName ("Get homeschooling by valid country and province")
 void getHomeSchoolingByValidCountryAndProvince () {
 }

 @Test
 @DisplayName ("Get homeschooling by invalid country and valid province")
 void getHomeSchoolingByInvalidCountryAndValidProvince () {
 }

 @Test
 @DisplayName ("Get homeschooling by valid country an invalid province")
 void getHomeSchoolingByValidCountryAndInvalidProvince () {
 }

 @Test
 @DisplayName ("Get homeschooling by invalid country and province")
 void getHomeSchoolingByInvalidCountryAndProvince () {

 }
}
