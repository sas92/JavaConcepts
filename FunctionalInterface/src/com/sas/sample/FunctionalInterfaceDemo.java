package com.sas.sample;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Reference: https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
 * https://www.geeksforgeeks.org/function-interface-in-java-with-examples/
 * https://www.geeksforgeeks.org/currying-functions-in-java-with-examples/
 * https://www.geeksforgeeks.org/java-8-consumer-interface-in-java-with-examples/
 * https://www.geeksforgeeks.org/supplier-interface-in-java-with-examples/
 * https://www.baeldung.com/java-8-double-colon-operator
 * https://www.geeksforgeeks.org/java-8-predicate-with-examples/
 */

public class FunctionalInterfaceDemo {

    // Code snippet on Function
    private static void functionDemo() {
        Function<Integer, Integer> square = num -> num * num;
        // Currying
        Function<Integer,
                Function<Integer, Integer>> multiply = a -> b -> 2 * a * b;
        BiFunction<Integer, Integer, Integer> sum = Integer::sum;

        final int a = 2, b = 3;
        final int result = sum.apply(
                sum.apply(square.apply(a), square.apply(b)),
                multiply.apply(a).apply(b));
        System.out.println("The result is: " + result);

        // While the compose() executes the caller last and the parameter first,
        // the andThen() executes the caller first and the parameter last.
        Function<Integer, Integer> times2 = e -> e * 2;
        System.out.println(times2.compose(square).apply(4));
        System.out.println(times2.andThen(square).apply(4));
    }

    // Code snippet on Consumer
    private static void consumerDemo() {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4);
        Consumer<List<Integer>> squareList = list -> {
            for (int i = 0; i < numList.size(); i++) {
                int num = numList.get(i);
                numList.set(i, num * num);
            }
        };
        Consumer<List<Integer>> displayList = list ->
                list.forEach(System.out::println);
        squareList.andThen(displayList)
                .accept(numList);
    }

    // Code snippet on Supplier
    private static void supplierDemo() {
        Supplier<Double> randomVal = Math::random;  // randomVal = () -> Math.random();
        System.out.println(randomVal.get());
    }

    // Code snippet on Predicate
    private static void predicateDemo() {
        List<Integer> numList = Arrays.asList(10, 5, 36, 7, 55, 12, 19);
        List tempList;

        // Display numbers divided by 5 and greater than 30
        Predicate<Integer> numbersDividedByFive = num -> num % 5 == 0;
        Predicate<Integer> numbersGreaterThan30 = num -> num > 30;
        tempList = numList.stream()
                .filter(numbersDividedByFive.and(numbersGreaterThan30))
                .collect(Collectors.toList());
        System.out.println("Numbers divided by 5 and greater than 30: " + tempList);

        System.out.println("19 is divided by 5: " + numbersDividedByFive.test(19));

        // Display numbers divided by 5 or greater than 30
        tempList = numList.stream()
                .filter(numbersDividedByFive.or(numbersGreaterThan30))
                .collect(Collectors.toList());
        System.out.println("Numbers divided by 5 or greater than 30: " + tempList);
    }

    // Code snippet on UnaryOperator & BinaryOperator
    private static void operatorDemo() {
        UnaryOperator<Integer> squareNum = num -> num * num;
        System.out.println("Square of 5 is: " + squareNum.apply(5));
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        System.out.println("Sum of 5 and 2 is: " + sum.apply(5, 2));
        System.out.println("(5+2)^2 is: " + sum.andThen(squareNum).apply(5, 2));
    }

    public static void main(String[] args) {
        System.out.println("Function Demo");
        functionDemo();
        System.out.println("\nConsumer Demo");
        consumerDemo();
        System.out.println("\nSupplier Demo");
        supplierDemo();
        System.out.println("\nPredicate Demo");
        predicateDemo();
        System.out.println("\nUnaryOperator & BinaryOperator Demo");
        operatorDemo();
    }
}
