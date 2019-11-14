package com.sas.sample;

/**
 * Reference: https://www.javatpoint.com/java-8-method-reference
 * <p>
 * There are following types of method references in java:
 * Reference to a static method.
 * Reference to an instance method.
 * Reference to a constructor.
 */

@FunctionalInterface
interface Displayable {
    void display(String message);
}

public class MethodReferenceDemo {
    private static void displayStatic(String message) {
        System.out.println(message);
    }

    private static void sayHello() {
        System.out.println("Hello world");
    }

    private void displayNonStatic(String message) {
        System.out.println(message);
    }

    public MethodReferenceDemo() {
    }

    public MethodReferenceDemo(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        // Reference to a static method
        Displayable displayable = MethodReferenceDemo::displayStatic;
        displayable.display("This is a static method");
        Thread t1 = new Thread(MethodReferenceDemo::sayHello);
        t1.start();

        // Reference to an Instance Method
        displayable = new MethodReferenceDemo()::displayNonStatic;
        displayable.display("This is a non-static method");

        // Reference to a Constructor
        displayable = MethodReferenceDemo::new;
        displayable.display("Hello from Constructor");
    }
}
