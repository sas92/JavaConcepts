package com.sas.sample;

/**
 * Reference: https://www.linkedin.com/learning/java-memory-management/string-pools?autoplay=true&u=2113185
 */

public class StringPoolDemo {
    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = "hello";
        System.out.println("str1 and str2 are same: " + (str1 == str2));

        String str3 = new Integer(100).toString().intern();
        String str4 = "100";
        System.out.println("str3 and str4 are same: " + (str3 == str4));
    }
}
