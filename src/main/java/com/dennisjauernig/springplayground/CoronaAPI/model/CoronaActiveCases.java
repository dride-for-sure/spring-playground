package com.dennisjauernig.springplayground.CoronaAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoronaActiveCases {

 private String country;
 private String province;
 private int active;

}
