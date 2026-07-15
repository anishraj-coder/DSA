package stacks;

import java.util.*;

public class PrefixToPostfix {

    /**
     * Converts a prefix expression to a postfix expression.
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public String preToPost(String s) {
        Stack<StringBuilder>st=new Stack<>();
        for(int i=s.length()-1;i>=0;i--){
            char ch=s.charAt(i);
            if(Character.isLetterOrDigit(ch))st.push(new StringBuilder(String.valueOf(ch)));
            else{
                StringBuilder first=st.pop();
                StringBuilder second=st.pop();
                first.append(second).append(String.valueOf(ch));
                st.push(first);
            }
        }
        return st.peek().toString();
    }

    public static void main(String[] args) {
        PrefixToPostfix converter = new PrefixToPostfix();

        // Test Case Definitions: {Prefix, Expected Postfix}
        String[][] testCases = {
                {"/A+BC", "ABC+/"},                          // Example 1
                {"-/A+BC*DE", "ABC+/DE*-"},                  // Example 2
                {"+AB", "AB+"},                               // Basic addition
                {"*+ABC", "AB+C*"},                           // Multiple operators
                {"*A+BC", "ABC+*"},                           // Standard precedence
                {"/+-ABC*DE", "AB-C+DE*/"},                   // Complex nested
                {"*+AB-CD", "AB+CD-*"},                       // Product of sums
                {"-A/BC", "ABC/-"},                           // Subtraction and division
                {"/+A*BCD", "ABC*+D/"},                       // Division of sum
                {"*-A/BC-/AKL", "ABC/-AK/L-*-"}               // Long complex structure
        };

        int passed = 0;
        System.out.println("Testing Prefix to Postfix Conversion...\n");
        System.out.printf("%-15s | %-20s | %-20s | %-8s%n", "Prefix", "Expected", "Actual", "Result");
        System.out.println("-".repeat(75));

        for (String[] test : testCases) {
            String input = test[0];
            String expected = test[1];
            String actual = "";

            try {
                actual = converter.preToPost(input);
            } catch (Exception e) {
                actual = "ERROR";
            }

            boolean isMatch = expected.equals(actual);
            if (isMatch) passed++;

            System.out.printf("%-15s | %-20s | %-20s | %-8s%n",
                    input, expected, actual, isMatch ? "PASS" : "FAIL");
        }

        System.out.println("-".repeat(75));
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}