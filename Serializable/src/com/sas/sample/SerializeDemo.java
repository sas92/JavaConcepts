package com.sas.sample;

import java.io.*;

/**
 * Reference: https://www.geeksforgeeks.org/serialization-in-java/
 */

class Person implements Serializable {
    private static final long serialversionUID = 129348938L;
    String name;
    int age;

    transient int a;
    static int b;

    public Person(String name, int age, int a, int b) {
        this.name = name;
        this.age = age;
        this.a = a;
        Person.b = b;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", a=" + a +
                ", b=" + b +
                '}';
    }
}

public class SerializeDemo {
    private static final String fileName = "person.txt";

    public static void main(String[] args) {
        Person person = new Person("Sas", 27, 2, 1000);
        try {
            // Serialization
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(person);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Object is been serialized!");
            System.out.println(person);

            Person.b = 2000;    // Static variable value changed
            person = null;      // Object reset

            // Deserialization
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            person = (Person) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Object is been de-serialized!");
            System.out.println(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
