package com.dennisjauernig.springplayground.ToDoAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

 // uuidv4
 @JsonProperty ("id")
 private UUID uuid;

 @JsonProperty ("description")
 private String desc;

 // OPEN, IN_PROGRESS, DONE
 private String status;
}
