package com.sas.sample;

class Class1 implements Cloneable {
    int x;
    int y;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Class1{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Class2 implements Cloneable {
    int a;
    int b;
    Class1 c = new Class1();

    // Shallow Copy
    /*@Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }*/

    // Deep Copy
    @Override
    public Object clone() throws CloneNotSupportedException {
        Class2 obj = (Class2) super.clone();
        obj.c = (Class1) c.clone();
        return obj;
    }

    @Override
    public String toString() {
        return "Class2{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}

public class CloneableDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Class2 obj = new Class2();
        obj.a = 10;
        obj.b = 20;
        obj.c.x = 30;
        obj.c.y = 40;

        Class2 clone = (Class2) obj.clone();

        clone.a = 100;
        clone.c.x = 300;

        System.out.println("obj: " + obj);
        System.out.println("clone: " + clone);
    }
}
