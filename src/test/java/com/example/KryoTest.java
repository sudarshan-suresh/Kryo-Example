package com.example;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

public class KryoTest {

    private Kryo kryo;
    private String fileName = "test_file.dat";
    private Output output;
    private Input input;

    @Before
    public void setUp() throws FileNotFoundException {
        kryo = new Kryo();
        output = new Output(new FileOutputStream(fileName));
        input = new Input(new FileInputStream(fileName));
    }

    @Test
    public void testKryoSerializeAndDeserializeString(){
        Object obj = "Hello World!!!";
        kryo.writeClassAndObject(output, obj);
        output.close();
        Object readObj = kryo.readClassAndObject(input);
        input.close();
        Assert.assertEquals(readObj, obj);
    }

    @Test
    public void testKryoSerializeAndDeserializeMultiObj(){
        String string = "Hello World!!!";
        int value = 55;
        Date date = new Date(651943079000L);
        kryo.writeObject(output, string);
        kryo.writeObject(output, value);
        // Date class need to be registered.
        kryo.register(Date.class);
        kryo.writeObject(output, date);
        output.close();
        String readString = kryo.readObject(input, String.class);
        int readValue = kryo.readObject(input, int.class);
        Date readDate = kryo.readObject(input,Date.class);
        input.close();
        Assert.assertEquals(string, readString);
        Assert.assertEquals(value, readValue);
        Assert.assertEquals(date, readDate);
    }

    @Test
    public void testSerializeAndDeserializeEmployee(){
        // registering class
        kryo.register(Employee.class);
        kryo.register(Date.class);
        Employee employee = new Employee("Rose", new Date(651943079000L));
        kryo.writeObject(output, employee);
        output.close();
        Employee readEmployee = kryo.readObject(input, Employee.class);
        input.close();
        Assert.assertEquals(employee.getName(), readEmployee.getName());
        Assert.assertEquals(employee.getBirthDate(), readEmployee.getBirthDate());
    }

    @Test
    public void testCustomSerializerAndDeserializeEmployee(){
        Employee employee = new Employee("Rose", new Date(651943079000L));
        kryo.register(Employee.class, new EmployeeSerializer());
        kryo.writeObject(output, employee);
        output.close();
        Employee readEmployee = kryo.readObject(input, Employee.class);
        input.close();
        Assert.assertEquals(employee.getName(), readEmployee.getName());
        Assert.assertEquals(employee.getBirthDate(), readEmployee.getBirthDate());
    }

    @Test
    public void testJavaSerializerAndDeserializeEStudent(){
        Student student = new Student("Rose", "Geller", new Date(951943079000L), new String[]{"Science", "History"}, "A", "GOVT");
        kryo.register(Student.class, new JavaSerializer());
        kryo.writeObject(output, student);
        output.close();

        Student readStudent = kryo.readObject(input, Student.class);
        input.close();
        Assert.assertEquals(student.getFirstName(), readStudent.getFirstName());
    }

}
