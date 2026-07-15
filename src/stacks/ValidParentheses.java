package stacks;

import java.util.*;

public class ValidParentheses {

    /**
     * Determines if the input string of parentheses is valid.
     * * Constraints:
     * 1 <= s.length <= 10^4
     * s consists of characters '(', ')', '{', '}', '[' and ']' only.
     */
    public boolean isValid(String s) {
        Stack<Character>st=new Stack<>();
        if(s.length()%2!=0)return false;
        for(char ch:s.toCharArray()){
            if(st.isEmpty())st.push(ch);
            else if(ch=='('||ch=='{'||ch=='[')st.push(ch);
            else if((ch==')'&&st.peek()=='(')||(ch=='}'&&st.peek()=='{')||(ch==']'&&st.peek()=='['))st.pop();
            else return false;
        }
        return st.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses validator = new ValidParentheses();

        // Test Case Definitions: {Input, Expected Output}
        Object[][] testCases = {
                {"()", true},                             // Simple match
                {"()[]{}", true},                         // Multiple types sequential
                {"(]", false},                            // Mismatched types
                {"([])", true},                           // Nested match
                {"([)]", false},                          // Incorrect nesting order
                {"((()))", true},                         // Deeply nested same type
                {"{[]}", true},                           // Nested different types
                {"[", false},                             // Single open (Edge case)
                {"]", false},                             // Single close (Edge case)
                {"((", false},                            // Unclosed multiple
                {"))", false},                            // Unopened multiple
                {"{()[]}", true},                         // Complex valid
                {"[(])", false},                          // Interleaved
                {"([]{()})", true},                       // Complex nested valid
                {"(((((((())))))))", true}                // Large symmetrical
        };

        int passed = 0;
        System.out.println("Running Tests...\n");
        System.out.printf("%-25s | %-10s | %-10s | %-10s%n", "Input", "Expected", "Actual", "Result");
        System.out.println("-----------------------------------------------------------------------");

        for (Object[] test : testCases) {
            String input = (String) test[0];
            boolean expected = (boolean) test[1];
            boolean actual = validator.isValid(input);
            boolean isMatch = (actual == expected);

            if (isMatch) passed++;

            System.out.printf("%-25s | %-10b | %-10b | %-10s%n",
                    input, expected, actual, isMatch ? "PASS" : "FAIL");
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}