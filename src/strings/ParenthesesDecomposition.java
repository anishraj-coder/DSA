package strings;

public class ParenthesesDecomposition {

    /**
     * Function to remove the outermost parentheses of every primitive
     * string in the primitive decomposition of s.
     * * Constraints:
     * 1 <= s.length <= 10^5
     * s consists only of '(' and ')'
     * s is a valid parentheses string
     */
    public String removeOuterParentheses(String s) {
        StringBuilder sb=new StringBuilder();
        int level=0;
        for(char ch:s.toCharArray()){
            if(ch=='('){
                if(level>0)sb.append(ch);
                level++;
            }else if(ch==')'){
                level--;
                if(level>0)sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ParenthesesDecomposition solver = new ParenthesesDecomposition();

        String[] testInputs = {
                "(()())(())",                // Case 1: LeetCode Example 1
                "(()())(())(()(()))",        // Case 2: LeetCode Example 2
                "()()",                      // Case 3: LeetCode Example 3 (Result: empty)
                "((()))",                    // Case 4: Striver Example 1
                "()(()())(())",              // Case 5: Striver Example 2
                "()",                        // Case 6: Minimum valid string
                "(((())))",                  // Case 7: Deeply nested single primitive
                "()()()()()",                // Case 8: Multiple flat primitives
                "((()())(()()))",            // Case 9: Single primitive with internal complexity
                "(()(()(())))"               // Case 10: Deeply nested with varied structure
        };

        String[] expectedOutputs = {
                "()()()",
                "()()()()(())",
                "",
                "(())",
                "()()()",
                "",
                "((()))",
                "",
                "(()())(()())",
                "()(()(()))"
        };

        System.out.println("Running Test Cases...\n");
        int passed = 0;

        for (int i = 0; i < testInputs.length; i++) {
            String input = testInputs[i];
            String expected = expectedOutputs[i];
            String actual = solver.removeOuterParentheses(input);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input:    \"" + input + "\"");
            System.out.println("Expected: \"" + expected + "\"");
            System.out.println("Actual:   \"" + actual + "\"");

            if (expected.equals(actual)) {
                System.out.println("Result: PASSED ✅");
                passed++;
            } else {
                System.out.println("Result: FAILED ❌");
            }
            System.out.println("------------------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + testInputs.length + " cases passed.");
    }
}