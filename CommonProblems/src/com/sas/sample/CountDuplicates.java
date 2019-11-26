package com.sas.sample;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CountDuplicates {
    public static void main(String[] args) {
        // Count duplicate characters
        {
            Map<String, Long> result = "The quick brown fox jumps over the lazy dog"
                    .chars()
                    .filter(c -> c != ' ')
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(Object::toString, Collectors.counting()));
            System.out.println(result);
            System.out.print("Duplicate characters: ");
            result.forEach((k, v) -> {
                if (v > 1)
                    System.out.print(k + ",");
            });
            System.out.println("\n");
        }

        // Count duplicate words
        {
            Map<String, Long> result = Arrays.stream("big black bug bit a big black dog on his big black nose".split(" "))
                    .collect(Collectors.groupingBy(Object::toString,Collectors.counting()));
            System.out.println(result);
        }
    }
}
