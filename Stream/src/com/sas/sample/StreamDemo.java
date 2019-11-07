package com.sas.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reference: https://www.mkyong.com/tutorials/java-8-tutorials/
 * mkyong.com/java8/java-8-streams-filter-examples/
 * https://www.geeksforgeeks.org/stream-map-java-examples/
 * https://www.baeldung.com/java-stream-reduce
 */

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class StreamDemo {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Alice", 25));
        personList.add(new Person("Bob", 47));
        personList.add(new Person("Charlie", 20));
        personList.add(new Person("Alison", 17));

        // filter(): Filter out minor persons [age<18]
        List result = personList.stream()
                .filter(person -> person.age >= 18)
                .collect(Collectors.toList());
        System.out.println(result);

        // findAny(): Search for Alison
        Person alison = personList.stream()
                .filter(person -> person.name.equals("Alison"))
                .findAny()
                .orElse(null);
        System.out.println(alison);

        // map(): Filter out person whose age>40 and Capitalize the name
        result = personList.stream()
                .filter(person -> person.age > 40)
                .map(person -> person.name.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(result);

        // reduce(): Calculate total age of all persons
        int totalAge = personList.stream()
                .reduce(0,
                        (sumAge, person) -> sumAge + person.age,
                        Integer::sum);
        System.out.println("Total age: " + totalAge);
    }
}
