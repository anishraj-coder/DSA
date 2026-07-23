package DynamicProgramming;

import java.util.Objects;

public class LongestCommonSubstring {

    // IMPLEMENT YOUR SOLUTION HERE
    public int longestCommonSubstr(String a, String b) {
        int x=a.length(),y=b.length();
        int[]prev=new int[y+1];
        int max=(int)-1e9;
        for(int i=1;i<=x;i++){
            int[]curr=new int[y+1];
            for(int j=1;j<=y;j++){
                curr[j]=(a.charAt(i-1)==b.charAt(j-1))?prev[j-1]+1:0;
                max=Math.max(curr[j],max);
            }
            prev=curr;
        }
        return max;
    }

    // Helper class to structure test cases
    static class TestCase {
        String s1;
        String s2;
        int expected;

        TestCase(String s1, String s2, int expected) {
            this.s1 = s1;
            this.s2 = s2;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        LongestCommonSubstring runner = new LongestCommonSubstring();

        // Generate large strings for stress/hard test cases (1000 characters each)
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb1.append((char) ('a' + (i % 26)));
            sb2.append((char) ('a' + (i % 26))); // Identical long pattern
        }
        String largeS1 = sb1.toString();
        String largeS2 = sb2.toString();

        TestCase[] tests = new TestCase[] {
                // Test 1: Standard Example 1
                new TestCase("ABCDGH", "ACDGHR", 4), // "CDGH"

                // Test 2: Standard Example 2 (Multiple 1-char matches)
                new TestCase("abc", "acb", 1), // "a", "b", or "c"

                // Test 3: Standard Example 3 (Case sensitivity test)
                new TestCase("YZ", "yz", 0),

                // Test 4: Single character match
                new TestCase("a", "a", 1),

                // Test 5: Single character mismatch
                new TestCase("a", "b", 0),

                // Test 6: Entire string is a common substring
                new TestCase("geeks", "geeks", 5),

                // Test 7: Substring vs Subsequence (verifies contiguity, unlike LCS)
                new TestCase("abcde", "ace", 1), // "a", "c", or "e" (NOT 3)

                // Test 8: Substring at the boundaries (start and end)
                new TestCase("helloWorld", "Worldhello", 5), // "hello" or "World"

                // Test 9: Overlapping pattern within different context
                new TestCase("AABAA", "ABAA", 4), // "ABAA"

                // Test 10: Hard Case - Max constraints (1000 chars each, identical match)
                new TestCase(largeS1, largeS2, 1000)
        };

        int passed = 0;
        System.out.println("=================== RUNNING TESTS ===================\n");

        for (int i = 0; i < tests.length; i++) {
            TestCase test = tests[i];
            int actual = runner.longestCommonSubstr(test.s1, test.s2);
            boolean isPass = actual == test.expected;

            if (isPass) passed++;

            // Truncate long strings for cleaner display in output
            String displayS1 = test.s1.length() > 20 ? test.s1.substring(0, 17) + "..." : test.s1;
            String displayS2 = test.s2.length() > 20 ? test.s2.substring(0, 17) + "..." : test.s2;

            System.out.printf("Test %-2d: %s\n", (i + 1), isPass ? "[PASS]" : "[FAIL]");
            System.out.printf("  Inputs  : s1 = \"%s\", s2 = \"%s\"\n", displayS1, displayS2);
            System.out.printf("  Expected: %d\n", test.expected);
            System.out.printf("  Actual  : %d\n", actual);
            System.out.println("---------------------------------------------------");
        }

        System.out.printf("\nSUMMARY: %d / %d Test Cases Passed.\n", passed, tests.length);
        System.out.println("===================================================");
    }
}