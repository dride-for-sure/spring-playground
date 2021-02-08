package com.dennisjauernig.springplayground.ToDoAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoWithoutId {

 @JsonProperty ("description")
 private String desc;

 private Status status;
}
