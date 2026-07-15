package stacks;

import java.util.Arrays;
import java.util.Stack;

public class AdjacentPairRemover {

    /**
     * Removes all pairs of adjacent equal characters from the string recursively.
     * Process: repeatedly remove any two adjacent identical characters until none remain.
     *
     * @param s the input string
     * @return the final string after all possible adjacent pair removals
     */
    public static String removeAdjacentPairs(String s) {
        if(s==null)return null;
        if(s.isEmpty())return s;
        int n=s.length();
        Stack<Character>st=new Stack<>();
        for(char ch: s.toCharArray()){
            if(st.isEmpty())st.push(ch);
            else if(st.peek()==ch){
                st.pop();
            }else st.push(ch);
        }
        StringBuilder str=new StringBuilder();
        while(!st.empty()){
            str.insert(0,st.pop());
        }
        return str.toString(); // Placeholder return
    }

    public static void main(String[] args) {
        // Test Case Structure: {input_string, expected_output}
        Object[][] testCases = {
                // Provided Examples
                {"assssd", "ad"},
                {"abbbe", "abe"},

                // Basic Edge Cases
                {"", ""},                      // Empty string
                {"a", "a"},                    // Single character
                {"aa", ""},                    // Single pair
                {"aaa", "a"},                  // Odd count: one pair removed, one remains

                // Multiple/Chained Removals
                {"aaaa", ""},                  // Even count: all pairs removed
                {"abba", ""},                  // Nested: bb removed → aa removed
                {"aabbcc", ""},                // Consecutive pairs
                {"aabccb", ""},                // Interleaved pairs: aa→cc→bb all removed

                // Complex/Hard Cases
                {"abccba", ""},                // Palindromic chain reaction
                {"ababab", "ababab"},          // No adjacent pairs → unchanged
                {"aabbccc", "c"},              // aabb→"", then ccc→c (one left)
                {"aaabbb", "ab"},              // aaa→a, bbb→b, no adjacent after first pass
                {"aabbccddeeffg", "g"},        // All pairs removed except trailing single

                // Tricky Edge: Case sensitivity & special chars (if applicable)
                {"Aa", "Aa"},                  // 'A' != 'a' (case-sensitive), no removal
                {"!!@@!!", ""},                // Special characters with pairs
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            String input = (String) testCases[i][0];
            String expected = (String) testCases[i][1];

            String actual = removeAdjacentPairs(input);
            boolean isPassed = expected.equals(actual);

            if (isPassed) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED");
            } else {
                failed++;
                System.out.println("Test Case " + (i + 1) + ": FAILED");
            }

            System.out.println("  Input   : \"" + input + "\"");
            System.out.println("  Expected: \"" + expected + "\"");
            System.out.println("  Actual  : \"" + actual + "\"");
            System.out.println("-----------------------------------");
        }

        System.out.println("Total Passed: " + passed + "/" + testCases.length);
        System.out.println("Total Failed: " + failed + "/" + testCases.length);
    }
}