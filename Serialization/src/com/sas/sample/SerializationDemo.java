package com.sas.sample;

import java.io.*;

/**
 * Reference: https://www.geeksforgeeks.org/serialization-in-java/
 * https://www.geeksforgeeks.org/externalizable-interface-java/
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

class Employee implements Externalizable {
    int id;
    String name;

    transient int a;
    static int b;

    public Employee() {
    }

    public Employee(int id, String name, int a, int b) {
        this.id = id;
        this.name = name;
        this.a = a;
        this.b = b;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeObject(name);
        out.writeInt(a);
        out.writeInt(b);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readInt();
        name = (String) in.readObject();
        a = in.readInt();
        b = in.readInt();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", a=" + a +
                ", b=" + b +
                '}';
    }
}

public class SerializationDemo {
    private static final String FILE_NAME = "demo.txt";

    private static void serializeDemo() {
        Person person = new Person("Sas", 27, 2, 1000);
        try {
            // Serialization
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(person);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Object is been serialized!");
            System.out.println(person);

            Person.b = 2000;    // Static variable value changed
            person = null;      // Object reset

            // Deserialization
            FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
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

    private static void externalizeDemo() {
        Employee employee = new Employee(1001, "Sas", 2, 1000);
        try {
            // Serialization
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(employee);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Object is been serialized!");
            System.out.println(employee);

            Employee.b = 2000;    // Static variable value changed
            employee = null;      // Object reset

            // Deserialization
            FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            employee = (Employee) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Object is been de-serialized!");
            System.out.println(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        serializeDemo();
        externalizeDemo();
    }
}
