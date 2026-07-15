package twoPointer;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringAtMostKDistinct {

    /**
     * Finds the length of the largest substring with at most K distinct characters.
     * * @param str The input string.
     * @param k   The maximum number of distinct characters allowed.
     * @return The length of the longest valid substring.
     */
    public int getLongestSubstringLength(String str, int k) {
        int n= str.length();
        HashMap<Character,Integer>map=new HashMap<>();
        int i=0,j=0,max=0;
        while(j<n){
            char add=str.charAt(j);
            map.put(add,map.getOrDefault(add,0)+1);
            if(map.size()>k){
                char rem=str.charAt(i);
                map.put(rem,map.getOrDefault(rem,0)-1);
                if(map.get(rem)==0)map.remove(rem);
                i++;
            }
            max=Math.max(j-i+1,max);
            j++;
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubstringAtMostKDistinct solver = new LongestSubstringAtMostKDistinct();

        Object[][] testCases = {
                // {str, k, expectedOutput}
                {"abbbbbbc", 2, 7},             // Example 1
                {"abcddefg", 3, 4},             // Example 2: [cdde, ddef]
                {"aaaaaaaa", 3, 8},             // All characters same, k > 1
                {"abcefg", 1, 1},               // All distinct, k = 1
                {"eceba", 2, 3},                // Standard case: "ece"
                {"a", 1, 1},                    // Single character string
                {"aaabbbccc", 2, 6},            // Equal blocks
                {"abcde", 5, 5},                // k equals string length
                {"abacccaaaccba", 2, 7},        // Hard: Longest buried in middle
                {"aabbcc", 1, 2},               // Pairs with k = 1
                {"", 2, 0},                     // Edge: Empty string (though constraints say length >= 1)
                {"abcdefghij", 10, 10}          // Large k, all distinct
        };

        int passed = 0;
        int failed = 0;

        System.out.println("Running Test Cases...\n" + "=".repeat(60));

        for (int i = 0; i < testCases.length; i++) {
            String str = (String) testCases[i][0];
            int k = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = solver.getLongestSubstringLength(str, k);
            boolean isMatch = (actual == expected);

            if (isMatch) {
                passed++;
            } else {
                failed++;
            }

            System.out.printf("Test Case %d:\n", i + 1);
            System.out.printf("  Input: str = \"%s\", k = %d\n", str, k);
            System.out.printf("  Expected Output: %d\n", expected);
            System.out.printf("  Actual Output:   %d\n", actual);
            System.out.printf("  Result: %s\n", isMatch ? "PASSED ✅" : "FAILED ❌");
            System.out.println("-".repeat(60));
        }

        System.out.printf("\nFinal Result: %d Passed, %d Failed\n", passed, failed);
    }
}