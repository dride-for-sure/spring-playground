package com.dennisjauernig.springplayground.CoronaAPI.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Data
@NoArgsConstructor
public class Time {

 public LocalDate getLocalTime () {
	return LocalDate.now();
 }
}
