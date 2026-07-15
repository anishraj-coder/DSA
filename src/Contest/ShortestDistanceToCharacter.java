package Contest;

import java.util.Arrays;
import java.util.Stack;

public class ShortestDistanceToCharacter {

    /**
     * Computes the shortest distance from each character in string s to the closest occurrence of character c.
     * * @param s The input string.
     * @param c The target character.
     * @return An array of integers representing the shortest distances.
     */
    public int[] shortestToChar(String s, char c) {
        if(s.length()==1)return new int[]{s.charAt(0)==c?0:-1};
        Stack<Integer>right=new Stack<>();
        Stack<Integer>left=new Stack<>();
        int n=s.length();
        int[]ans=new int[n];
        Arrays.fill(ans,Integer.MAX_VALUE);
        for(int i=0;i<n;i++){
            while(!right.isEmpty()&&s.charAt(i)==c) {
                int idx=right.pop();
                ans[idx] = i-idx;
            }
            if(s.charAt(i)!=c) right.push(i);
            else ans[i]=0;
        }

        for(int i=n-1;i>=0;i--){
            while(!left.isEmpty()&&s.charAt(i)==c){
                int idx=left.pop();
                ans[idx]=Math.min(ans[idx],idx-i);
            }
            if(s.charAt(i)!=c)left.push(i);
            else ans[i]=0;
        }

        return ans;
    }

    public static void main(String[] args) {
        ShortestDistanceToCharacter instance = new ShortestDistanceToCharacter();

        // Define test cases: {String s, String c (as string for structure), Expected Output}
        TestCase[] testCases = new TestCase[] {
                // 1. Standard example case 1
                new TestCase("loveleetcode", 'e', new int[]{3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0}),

                // 2. Standard example case 2
                new TestCase("aaab", 'b', new int[]{3, 2, 1, 0}),

                // 3. Edge Case: Minimal single-character string where s is just c
                new TestCase("a", 'a', new int[]{0}),

                // 4. Edge Case: Character is only at the absolute beginning
                new TestCase("bxxx", 'b', new int[]{0, 1, 2, 3}),

                // 5. Edge Case: Character is only at the absolute end
                new TestCase("xxxb", 'b', new int[]{3, 2, 1, 0}),

                // 6. Hard/Edge Case: Entire string consists only of the target character
                new TestCase("eeee", 'e', new int[]{0, 0, 0, 0}),

                // 7. Hard Case: Alternating target and non-target characters
                new TestCase("abababa", 'b', new int[]{1, 0, 1, 0, 1, 0, 1}),

                // 8. Hard Case: Large blocks of non-target characters between targets
                new TestCase("baaaaab", 'b', new int[]{0, 1, 2, 3, 2, 1, 0}),

                // 9. Hard Case: Target characters clustered close together in a long string
                new TestCase("abcdefgfedcba", 'd', new int[]{3, 2, 1, 0, 1, 2, 3, 2, 1, 0, 1, 2, 3}),

                // 10. Complex Case: Multiple occurrences with varying gaps
                new TestCase("abaaca", 'a', new int[]{0, 1, 0, 0, 1, 0})
        };

        int passed = 0;
        System.out.println("--- Starting Test Execution ---\n");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int[] actual = instance.shortestToChar(tc.s, tc.c);
            boolean isMatch = Arrays.equals(actual, tc.expected);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("  Input: s = \"" + tc.s + "\", c = '" + tc.c + "'");
            System.out.println("  Expected: " + Arrays.toString(tc.expected));
            System.out.println("  Actual:   " + Arrays.toString(actual));

            if (isMatch) {
                System.out.println("  Result:   PASSED ✅");
                passed++;
            } else {
                System.out.println("  Result:   FAILED ❌");
            }
            System.out.println();
        }

        System.out.println("--- Summary ---");
        System.out.println("Total Tests: " + testCases.length);
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + (testCases.length - passed));
    }

    // Helper class to encapsulate test data cleanly
    private static class TestCase {
        String s;
        char c;
        int[] expected;

        TestCase(String s, char c, int[] expected) {
            this.s = s;
            this.c = c;
            this.expected = expected;
        }
    }
}