package DynamicProgramming;

import java.util.Objects;

public class PrintLongestCommonSubsequence {

    // IMPLEMENT YOUR SOLUTION HERE
    public String findLCS(String a, String b) {
        int x=a.length(),y=b.length();
        int[][]dp=new int[x+1][y+1];
        for(int i=1;i<=x;i++){
            for(int j=1;j<=y;j++){
                if(a.charAt(i-1)==b.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    int p1=dp[i-1][j],p2=dp[i][j-1];
                    dp[i][j]=Math.max(p1,p2);
                }
            }
        }

        StringBuilder sb=new StringBuilder();

        int i=x,j=y;
        while(i>0&&j>0){
            if(a.charAt(i-1)==b.charAt(j-1)){
                sb.insert(0,a.charAt(i-1));
                i--;j--;
            }else{
                if(dp[i-1][j]>=dp[i][j-1])i--;
                else j--;
            }
        }

        return sb.toString();
    }

    // Helper class to structure test cases
    static class TestCase {
        String s1;
        String s2;
        int expectedLength;

        TestCase(String s1, String s2, int expectedLength) {
            this.s1 = s1;
            this.s2 = s2;
            this.expectedLength = expectedLength;
        }
    }

    // Helper method to verify if candidate is a valid subsequence of source
    private static boolean isSubsequence(String candidate, String source) {
        int i = 0, j = 0;
        while (i < candidate.length() && j < source.length()) {
            if (candidate.charAt(i) == source.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == candidate.length();
    }

    public static void main(String[] args) {
        PrintLongestCommonSubsequence runner = new PrintLongestCommonSubsequence();

        // Generate large strings for stress/hard test cases
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb1.append((char) ('a' + (i % 26)));
            sb2.append((char) ('a' + ((i + 3) % 26)));
        }
        String largeS1 = sb1.toString();
        String largeS2 = sb2.toString();

        TestCase[] tests = new TestCase[] {
                // Test 1: Example 1 from problem statement
                new TestCase("abcab", "cbab", 3), // e.g. "bab" or "cab"

                // Test 2: Sample Input 1
                new TestCase("ababa", "cbbcad", 3), // "bba"

                // Test 3: Sample Input 2 (Disjoint strings)
                new TestCase("xyz", "abc", 0), // ""

                // Test 4: Identical strings
                new TestCase("coding", "coding", 6), // "coding"

                // Test 5: Single character match
                new TestCase("a", "a", 1), // "a"

                // Test 6: Single character mismatch
                new TestCase("a", "b", 0), // ""

                // Test 7: Repeating character patterns
                new TestCase("aaaaa", "aaa", 3), // "aaa"

                // Test 8: Complex pattern with interleaved characters
                new TestCase("ezupkr", "ubmrapg", 2), // e.g. "ur"

                // Test 9: Reversed characters (Length 1 match)
                new TestCase("abcdef", "fedcba", 1),

                // Test 10: Hard Case - Max constraints (1000 chars each)
                new TestCase(largeS1, largeS2, 962)
        };

        int passed = 0;
        System.out.println("=================== RUNNING TESTS ===================\n");

        for (int i = 0; i < tests.length; i++) {
            TestCase test = tests[i];
            String actual = runner.findLCS(test.s1, test.s2);
            if (actual == null) actual = "";

            // Validation: Actual output must have expected length and be a valid subsequence of both strings
            boolean correctLength = actual.length() == test.expectedLength;
            boolean validSub1 = isSubsequence(actual, test.s1);
            boolean validSub2 = isSubsequence(actual, test.s2);
            boolean isPass = correctLength && validSub1 && validSub2;

            if (isPass) passed++;

            // Truncate long strings for cleaner display in output
            String displayS1 = test.s1.length() > 20 ? test.s1.substring(0, 17) + "..." : test.s1;
            String displayS2 = test.s2.length() > 20 ? test.s2.substring(0, 17) + "..." : test.s2;
            String displayActual = actual.length() > 20 ? actual.substring(0, 17) + "..." : actual;

            System.out.printf("Test %-2d: %s\n", (i + 1), isPass ? "[PASS]" : "[FAIL]");
            System.out.printf("  Inputs         : s1 = \"%s\", s2 = \"%s\"\n", displayS1, displayS2);
            System.out.printf("  Expected Length: %d\n", test.expectedLength);
            System.out.printf("  Actual Output  : \"%s\" (Length: %d)\n", displayActual, actual.length());

            if (!isPass) {
                if (!correctLength) {
                    System.out.println("  Reason         : Length mismatch!");
                } else if (!validSub1 || !validSub2) {
                    System.out.println("  Reason         : Output string is not a valid common subsequence!");
                }
            }
            System.out.println("---------------------------------------------------");
        }

        System.out.printf("\nSUMMARY: %d / %d Test Cases Passed.\n", passed, tests.length);
        System.out.println("===================================================");
    }
}