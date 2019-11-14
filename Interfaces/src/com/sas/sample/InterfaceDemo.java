package com.sas.sample;

interface MyInterface1 {
    int a = 10;

    // Default methods are used to support backward compatibility
    default void display() {
        System.out.println("Hello from MyInterface1");
    }

    // Static methods are used to create Utility methods
    static boolean isEvenNum(int num) {
        return num % 2 == 0;
    }
}

interface MyInterface2 {
    default void display() {
        System.out.println("Hello from MyInterface2");
    }
}

class MyClass1 implements MyInterface1, MyInterface2 {
    public void display() {
        // Solution 1: Need to override method because both the interfaces has same method signature
        System.out.println("Hello from MyClass1");
    }
}

class MyClass2 implements MyInterface1, MyInterface2 {
    public void display() {
        // Solution 2: Using the 'super' keyword
        MyInterface2.super.display();
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        MyClass1 myClass1 = new MyClass1();
        MyClass2 myClass2 = new MyClass2();
        // myClass.a = 100;        // fields are static and final by default
        myClass1.display();
        myClass2.display();
        System.out.println("50 is even: " + MyInterface1.isEvenNum(50));
    }
}
