package com.sas.sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Reference: https://www.linkedin.com/learning/advanced-java-programming-2/generic-methods-in-java?autoplay=true&u=2113185
 */

public class GenericDemo {
    static List<Building> buildingList = new ArrayList<>();
    static List<House> houseList = new ArrayList<>();
    static List<Office> officeList = new ArrayList<>();

    private static <T> List<T> arrayToList(T... array) {    // Varargs T...
        List<T> list = new ArrayList<>();
        for (T obj : array)
            list.add(obj);
        return list;
    }

    private static void printBuilding(Building building) {
        System.out.println(building);
    }

    private static void printBuildings(List<? extends Building> buildings) {        // Wildcard <? extends Building>
        for (Building b : buildings)
            System.out.println(b);
    }

    private static void addHouseToList(List<? super House> houses) {                // Wildcard <? super House>
        buildingList.add(new House());
    }

    public static void main(String[] args) {
        // Generic
        Character[] charArray = {'a', 'b', 'c'};
        Integer[] intArray = {1, 2, 3, 4, 5};
        List<Character> charList = arrayToList(charArray);
        List<Integer> intList = arrayToList(intArray);

        System.out.println(charList);
        System.out.println(intList);

        // Substitution Principle
        Building building = new Building();
        printBuilding(building);
        House house = new House();
        printBuilding(house);
        Office office = new Office();
        printBuilding(office);

        // Wildcards in Generic
        buildingList.add(building);
        printBuildings(buildingList);
        houseList.add(house);
        printBuildings(houseList);
        officeList.add(office);
        printBuildings(officeList);
        addHouseToList(buildingList);
        addHouseToList(houseList);
    }
}

class Building {
    @Override
    public String toString() {
        return "Building";
    }
}

class House extends Building {
    @Override
    public String toString() {
        return "House";
    }
}

class Office extends Building {
    @Override
    public String toString() {
        return "Office";
    }
}