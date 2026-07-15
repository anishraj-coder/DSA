package stacks;

import java.util.Stack;

public class PostfixToPrefix {

    /**
     * Converts a postfix expression to a prefix expression.
     * Time Complexity: O(N)
     * Auxiliary Space: O(N)
     */
    public String postToPre(String s) {
        Stack<StringBuilder>st=new Stack<>();
        for(char ch:s.toCharArray()){
            if(Character.isLetterOrDigit(ch)){
                st.push(new StringBuilder(String.valueOf(ch)));
            }else{
                StringBuilder second=st.pop();
                StringBuilder first=st.pop();
                first.insert(0,String.valueOf(ch));
                first.append(second);
                st.push(first);
            }
        }
        return st.peek().toString();
    }

    public static void main(String[] args) {
        PostfixToPrefix converter = new PostfixToPrefix();

        // Test Case Definitions: {Postfix, Expected Prefix}
        String[][] testCases = {
                {"ab+", "+ab"},                                // Simple addition
                {"ABC/-AK/L-*", "*-A/BC-/AKL"},                // Example 1 from problem
                {"ab+c*", "*+abc"},                            // Mixed operators
                {"abc*+", "+a*bc"},                            // Different precedence
                {"ab+cd+*", "*+ab+cd"},                        // Product of sums
                {"abcd^e-fgh*+^*+", "+a*b^-^cdef+g*h"},        // Complex expression
                {"ABC/DA-*+", "+A* /BC-DA"},                   // Complex operators
                {"ab/", "/ab"},                                // Division
                {"abc^^", "^a^bc"},                            // Power (Right-associative)
                {"abcd/-+", "+a-b/cd"}                         // Multi-level nesting
        };

        int passed = 0;
        System.out.println("Testing Postfix to Prefix Conversion...\n");
        System.out.printf("%-20s | %-20s | %-20s | %-8s%n", "Postfix", "Expected", "Actual", "Result");
        System.out.println("-".repeat(80));

        for (String[] test : testCases) {
            String input = test[0];
            String expected = test[1];
            String actual = "";

            try {
                actual = converter.postToPre(input);
            } catch (Exception e) {
                actual = "ERROR";
            }

            // We trim or remove spaces to ensure comparison is based on characters
            boolean isMatch = expected.replace(" ", "").equals(actual.replace(" ", ""));
            if (isMatch) passed++;

            System.out.printf("%-20s | %-20s | %-20s | %-8s%n",
                    input, expected, actual, isMatch ? "PASS" : "FAIL");
        }

        System.out.println("-".repeat(80));
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}