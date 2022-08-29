package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student implements Serializable {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String[] courses;
    private String classGroup;
    private String schoolName;
}
