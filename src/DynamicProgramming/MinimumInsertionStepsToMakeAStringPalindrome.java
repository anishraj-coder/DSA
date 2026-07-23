package DynamicProgramming;

import java.util.Arrays;

public class MinimumInsertionStepsToMakeAStringPalindrome {

    // IMPLEMENT YOUR SOLUTION HERE
    public int minInsertions(String a) {
        int n=a.length();
        String b=new StringBuilder(a).reverse().toString();
        int[]prev=new int[n+1];
        for(int i=1;i<=n;i++){
            int[]curr=new int[n+1];
            for(int j=1;j<=n;j++){
                if(a.charAt(i-1)==b.charAt(j-1))curr[j]=prev[j-1]+1;
                else{
                    int p1=curr[j-1],p2=prev[j];
                    curr[j]=Math.max(p1,p2);
                }
            }
            prev=curr;
        }
        return n-prev[n];
    }


    public static void main(String[] args) {
        MinimumInsertionStepsToMakeAStringPalindrome testRunner = new MinimumInsertionStepsToMakeAStringPalindrome();

        // Define Test Cases
        TestCase[] testCases = new TestCase[] {
                // 1. Standard Case: Already a palindrome (Even length)
                new TestCase("zzazz", 0),

                // 2. Standard Case: Interleaved characters (Odd length)
                new TestCase("mbadm", 2),

                // 3. Standard Case: No matching characters at all
                new TestCase("leetcode", 5),

                // 4. Edge Case: Single character string (Minimum constraint)
                new TestCase("a", 0),

                // 5. Edge Case: Two identical characters
                new TestCase("aa", 0),

                // 6. Edge Case: Two completely different characters
                new TestCase("ab", 1),

                // 7. Hard Case: Completely sorted distinct characters (Worst case structure)
                new TestCase("abcdefg", 6),

                // 8. Hard Case: Repeated patterns that aren't centered
                new TestCase("zjveva", 3),

                // 9. Hard Case: Long uniform string with one disruptor
                new TestCase("aaaaabaaaaa", 0),

                // 10. Hard/Stress Case: 500 characters (Maximum constraint check)
                new TestCase(generateMaxConstraintString(), 499)
        };

        // Run Test Cases
        int passed = 0;
        System.out.println("Running tests for: Minimum Insertion Steps to Make a String Palindrome\n");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];

            // Handle display truncated for long strings
            String displayStr = tc.input.length() > 30 ? tc.input.substring(0, 30) + "... [Len: " + tc.input.length() + "]" : tc.input;

            int actual = testRunner.minInsertions(tc.input);
            boolean isPass = (actual == tc.expected);

            if (isPass) passed++;

            System.out.printf("Test Case %d: %s\n", i + 1, isPass ? "[PASS]" : "[FAIL]");
            System.out.printf("  Input:    \"%s\"\n", displayStr);
            System.out.printf("  Expected: %d\n", tc.expected);
            System.out.printf("  Actual:   %d\n\n", actual);
        }

        System.out.println("----------------------------------------");
        System.out.printf("Result: %d/%d Tests Passed.\n", passed, testCases.length);
    }

    // Helper class to store test configuration
    private static class TestCase {
        String input;
        int expected;

        TestCase(String input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }

    // Helper to generate a 500-character test case for the strict upper-bound constraint
    private static String generateMaxConstraintString() {
        StringBuilder sb = new StringBuilder(500);
        for (int i = 0; i < 500; i++) {
            sb.append((char) ('a' + (i % 26))); // "abcdefg..." repeated
        }
        return sb.toString();
    }
}