package com.sas.sample;

import java.util.function.Function;

/**
 * Reference: https://www.geeksforgeeks.org/function-interface-in-java-with-examples/
 * https://www.geeksforgeeks.org/currying-functions-in-java-with-examples/
 */

public class FunctionInterfaceDemo {
    public static void main(String[] args) {
        Function<Integer, Integer> square = num -> num * num;
        // Currying
        Function<Integer,
                Function<Integer, Integer>> multiply = a -> b -> 2 * a * b;

        int a = 2, b = 3;
        int result = square.apply(a) + square.apply(b) + multiply.apply(a).apply(b);
        System.out.println("The result is: " + result);

        // While the compose() executes the caller last and the parameter first,
        // the andThen() executes the caller first and the parameter last.
        Function<Integer, Integer> times2 = e -> e * 2;
        System.out.println(times2.compose(square).apply(4));
        System.out.println(times2.andThen(square).apply(4));
    }
}
