package com.sas.sample;

import java.util.*;

/**
 * Reference: https://www.geeksforgeeks.org/comparable-vs-comparator-in-java/
 */

class Student implements Comparable<Student> {
    int rollNo;
    String name;

    public Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    // Sort using Comparable
    @Override
    public int compareTo(Student o) {
        return this.rollNo - o.rollNo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                '}';
    }
}

public class CollectionSortDemo {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(4, "Alison"));
        students.add(new Student(3, "Alice"));
        students.add(new Student(1, "Bob"));
        students.add(new Student(2, "Charlie"));
        System.out.println("Before sort: " + students);

        // Sort using Comparable - By rollNo
        Collections.sort(students);
        System.out.println("After sort (Comparable): " + students);

        // Sort using Comparator - By name
        Comparator<Student> comparator = (s1, s2) -> s1.name.compareTo(s2.name);
        Collections.sort(students, comparator);
        System.out.println("After sort (Comparator): " + students);
    }
}
