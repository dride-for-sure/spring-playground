package com.dennisjauernig.springplayground.CoronaAPI.controller;

import com.dennisjauernig.springplayground.CoronaAPI.model.CoronaActiveCases;
import com.dennisjauernig.springplayground.CoronaAPI.services.CoronaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("api/corona")
public class CoronaController {

 private final CoronaService coronaService;

 @Autowired
 public CoronaController (CoronaService coronaService) {
	this.coronaService = coronaService;
 }

 @GetMapping ("average/{country}")
 public CoronaActiveCases getWeekAverageByCountry (@PathVariable String country) {
	return this.coronaService.getByCountryAverage( country );
 }

 @GetMapping ("average/{country}/{province}")
 public CoronaActiveCases getWeekAverageByProvince (@PathVariable String country,
																										@PathVariable String province) {
	return this.coronaService.getByProvinceAverage( country, province );
 }

 @GetMapping ("homeschooling/{country}/{province}")
 public List<String> getHomeSchooling (@PathVariable String country,
																			 @PathVariable String province) {
	return this.coronaService.getHomeSchooling( country, province );
 }
}