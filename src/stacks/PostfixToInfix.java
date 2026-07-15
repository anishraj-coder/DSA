package stacks;

import java.util.*;

public class PostfixToInfix {

    /**
     * Converts a postfix expression to an infix expression.
     * Every sub-expression must be surrounded by parentheses.
     * Constraints: 3 <= postfix.length <= 10^4
     */
    public String convert(String postfix) {
        Stack<StringBuilder>st=new Stack<>();
        for(char ch:postfix.toCharArray()){
            if(Character.isLetterOrDigit(ch))st.push(new StringBuilder(String.valueOf(ch)));
            else {
                StringBuilder second=st.pop();
                StringBuilder first=st.pop();
                StringBuilder temp=new StringBuilder();
                temp.append("(");
                temp.append(first);
                temp.append(String.valueOf(ch));
                temp.append(second);
                temp.append(")");
                st.push(temp);
            }
        }
        return st.peek().toString();
    }

    public static void main(String[] args) {
        PostfixToInfix converter = new PostfixToInfix();

        // Test Case Definitions: {Postfix, Expected Infix}
        String[][] testCases = {
                {"ab+", "(a+b)"},                                // Basic addition
                {"ab+c+", "((a+b)+c)"},                          // Sequential addition
                {"ABC/DA-*+", "(A+((B/C)*(D-A)))"},              // Complex mixed operators
                {"ab*cd*+", "((a*b)+(c*d))"},                    // Parallel multiplications
                {"abc^+", "(a+(b^c))"},                          // Power operator
                {"abcde/*-+", "(a+(b-(c*(d/e))))"},              // Deeply nested right-side
                {"ab+cd+*", "((a+b)*(c+d))"},                    // Product of sums
                {"a", "a"},                                      // Single operand (Boundary case)
                {"ab-c*def+/-", "((a-b)*c)-(d+(e/f)))"},         // Long complex expression
                {"ABC*+DE/-", "((A+(B*C))-(D/E))"}               // Standard algebraic mix
        };

        int passed = 0;
        System.out.println("Testing Postfix to Infix Conversion...\n");
        System.out.printf("%-20s | %-30s | %-30s | %-8s%n", "Postfix", "Expected", "Actual", "Result");
        System.out.println("-".repeat(95));

        for (String[] test : testCases) {
            String input = test[0];
            String expected = test[1];
            String actual = "";

            try {
                actual = converter.convert(input);
            } catch (Exception e) {
                actual = "ERROR";
            }

            boolean isMatch = expected.equals(actual);
            if (isMatch) passed++;

            System.out.printf("%-20s | %-30s | %-30s | %-8s%n",
                    input, expected, actual, isMatch ? "PASS" : "FAIL");
        }

        System.out.println("-".repeat(95));
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}
