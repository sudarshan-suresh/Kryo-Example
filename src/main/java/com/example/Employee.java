package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

// NoArgsConstructor required by kryo.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee  {
    private String name;
    private Date birthDate;
}
