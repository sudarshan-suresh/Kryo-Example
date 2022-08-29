package com.example;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.util.Date;

public class EmployeeSerializer extends Serializer<Employee> {

    @Override
    public void write(Kryo kryo, Output output, Employee employee) {
        output.writeString(employee.getName());
        output.writeLong(employee.getBirthDate().getTime());
    }

    @Override
    public Employee read(Kryo kryo, Input input, Class<? extends Employee> aClass) {
        // This why we need default constructor
        Employee employee = new Employee();
        employee.setName(input.readString());
        employee.setBirthDate(new Date(input.readLong()));
        return employee;
    }
}

