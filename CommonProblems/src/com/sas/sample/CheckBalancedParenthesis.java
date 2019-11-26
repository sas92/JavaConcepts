package com.sas.sample;

import java.util.Stack;

/**
 * Reference: https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/
 */

public class CheckBalancedParenthesis {
    private static boolean check(String expression) {
        Stack<Integer> expStack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            int c = expression.charAt(i);
            if (c == '(' || c == '{' || c == '[')
                expStack.push(c);
            else {
                switch (c) {
                    case ')':
                        if (expStack.pop() != '(')
                            return false;
                        break;
                    case '}':
                        if (expStack.pop() != '{')
                            return false;
                        break;
                    case ']':
                        if (expStack.pop() != '[')
                            return false;
                        break;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String expression = "[()]{}{[()()]()}";
        //String expression = "[}";
        System.out.println(check(expression));
    }
}
