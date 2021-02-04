package com.dennisjauernig.springplayground.CoronaAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoronaCountryStatusData {

 @JsonProperty ("Country")
 private String country;

 @JsonProperty ("Cases")
 private String cases;

 @JsonProperty ("Date")
 private String date;
}
