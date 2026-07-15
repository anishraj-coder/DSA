package twoPointer;

import java.util.Arrays;

public class TripleCharacterSubstrings {

    /**
     * Given a string s consisting only of characters a, b and c.
     * Return the number of substrings containing at least one occurrence
     * of all these characters a, b and c.
     */
    public int numberOfSubstrings(String s) {
        int[]hash=new int[3];
        Arrays.fill(hash,-1);
        int n=s.length(),count=0;
        for(int i=0;i<n;i++){
            char ch=s.charAt(i);
            hash[ch-'a']=i;
            count+=1+Math.min(hash[0],Math.min(hash[1],hash[2]));
        }
        return count;
    }

    public static void main(String[] args) {
        TripleCharacterSubstrings solver = new TripleCharacterSubstrings();

        Object[][] testCases = {
                // {input_string, expected_output}
                {"abcabc", 10},
                {"aaacb", 3},
                {"abc", 1},
                {"aaaaa", 0},                   // Edge case: Only one type of character
                {"aabbcc", 4},                  // Grouped characters: "aabbcc", "abbcc", "bbcc" is not, "aabbc" is not...
                {"abcabcabc", 28},              // Longer repeating pattern
                {"abacaba", 0},                 // Missing one character ('c')
                {"abcba", 3},                   // "abcb" no, "abc" (1), "abcb" (2), "abcba" (3) etc.
                {"cba", 1},                     // Minimum length valid
                {"aaabbbccc", 9},               // Distinct blocks
                {"ababababab", 0},              // Long string missing 'c'
                {"abccba", 4}                   // Symmetrical characters
        };

        int passed = 0;
        System.out.println("--- Starting Test Suite ---\n");

        for (int i = 0; i < testCases.length; i++) {
            String s = (String) testCases[i][0];
            int expected = (int) testCases[i][1];

            int actual = solver.numberOfSubstrings(s);
            boolean isPassed = (actual == expected);
            if (isPassed) passed++;

            System.out.printf("Test Case %d: %s\n", i + 1, isPassed ? "PASSED ✅" : "FAILED ❌");
            System.out.printf("   Input: s = \"%s\"\n", s);
            System.out.printf("   Expected: %d\n", expected);
            System.out.printf("   Actual:   %d\n", actual);
            System.out.println("--------------------------");
        }

        System.out.printf("\nFinal Result: %d/%d Passed\n", passed, testCases.length);
    }
}