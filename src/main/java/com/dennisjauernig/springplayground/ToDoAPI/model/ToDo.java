package com.dennisjauernig.springplayground.ToDoAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

 @JsonProperty ("id")
 private String id;

 @JsonProperty ("description")
 private String desc;

 private String status;

 public ToDo (String desc, String status) {
	this.desc = desc;
	this.status = status;
 }
}
