package DynamicProgramming;

import java.util.Objects;

public class LongestPalindromicSubsequence {

    // IMPLEMENT YOUR SOLUTION HERE
    public int longestPalindromeSubseq(String a) {
        int n=a.length();
        int[]prev=new int[n+1];
        String b=rev(a);
        for(int i=1;i<=n;i++){
            int[]curr=new int[n+1];
            for(int j=1;j<=n;j++){
                if(a.charAt(i-1)==b.charAt(j-1))curr[j]=prev[j-1]+1;
                else{
                    int p1=prev[j],p2=curr[j-1],p3=prev[j-1];
                    curr[j]=Math.max(p1,Math.max(p2,p3));
                }
            }
            prev=curr;
        }
        return prev[n];
    }

    public String rev(String s){
        StringBuilder sb=new StringBuilder(s);
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        LongestPalindromicSubsequence instance = new LongestPalindromicSubsequence();

        // Test Case Definitions
        TestCase[] testCases = new TestCase[]{
                // 1. Standard Case 1 (From Example 1)
                new TestCase("bbbab", 4),

                // 2. Standard Case 2 (From Example 2)
                new TestCase("cbbd", 2),

                // 3. Edge Case: Single character
                new TestCase("a", 1),

                // 4. Edge Case: All characters are the same
                new TestCase("zzzzzzz", 7),

                // 5. Edge Case: No palindrome longer than 1 (All unique characters)
                new TestCase("abcdefg", 1),

                // 6. Standard Case: Full palindrome already
                new TestCase("racecar", 7),

                // 7. Standard Case: Two separate palindromes, picking the longest
                // "aba" (3) vs "pqpqp" (5) -> "pqpqp" should be chosen
                new TestCase("abapqpqp", 5),

                // 8. Hard Case: Alternating patterns with noise
                new TestCase("dabcbaeda", 7), // "abacaba" or similar length 7

                // 9. Hard Case: Even length palindrome buried inside
                new TestCase("tattletale", 6), // "attata" or "tattet" -> length 6

                // 10. Hard/Long Case: Maximum constraints representation (repeating clusters)
                new TestCase("aaaaaaaaaabbbbbbbbbbccccccccccbbbbbbbbbbaaaaaaaaaa", 40)
        };

        // Test Runner Execution
        int passed = 0;
        System.out.println("Running Tests...\n--------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int actual = instance.longestPalindromeSubseq(tc.input);
            boolean isPass = actual == tc.expected;

            if (isPass) {
                passed++;
                System.out.printf("Test Case %2d: [PASS]\n", i + 1);
            } else {
                System.out.printf("Test Case %2d: [FAIL]\n", i + 1);
                System.out.printf("   Input:    \"%s\"\n", tc.input);
                System.out.printf("   Expected: %d\n", tc.expected);
                System.out.printf("   Actual:   %d\n", actual);
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.printf("Result: %d/%d Tests Passed.\n", passed, testCases.length);
    }

    // Helper class to store test case details cleanly
    private static class TestCase {
        String input;
        int expected;

        TestCase(String input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}