package DynamicProgramming;

import java.util.Objects;

public class LongestCommonSubsequence {

    // IMPLEMENT YOUR SOLUTION HERE
    public int longestCommonSubsequence(String s1, String s2) {
        int x=s1.length(),y=s2.length();
        int[]prev=new int[y+1];
        for(int i=1;i<=x;i++){
            int[] curr=new int[y+1];
            for(int j=1;j<=y;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    curr[j]=prev[j-1]+1;
                }else{
                    int p1=prev[j],p2=curr[j-1],p3=prev[j-1];
                    curr[j]=Math.max(p1,Math.max(p2,p3));
                }
            }
            prev=curr;
        }
        return prev[y];
    }
    public int helper(String a,String b,int i,int j){
        if(i<0||j<0)return 0;
        int min=(int)-1e9;
        if(a.charAt(i)==b.charAt(j)){
            return helper(a,b,i-1,j-1)+1;
        }else{
            int p1=helper(a,b,i-1,j);
            int p2=helper(a,b,i,j-1);
            int p3=helper(a,b,i-1,j-1);
            min=Math.max(min,Math.max(p1,Math.max(p2,p3)));
        }

        return min;
    }

    // Helper class to structure test cases
    static class TestCase {
        String text1;
        String text2;
        int expected;

        TestCase(String text1, String text2, int expected) {
            this.text1 = text1;
            this.text2 = text2;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        LongestCommonSubsequence runner = new LongestCommonSubsequence();

        // Generate a large string for stress/hard test cases
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb1.append((char) ('a' + (i % 26)));
            sb2.append((char) ('a' + ((i + 3) % 26)));
        }
        String largeText1 = sb1.toString();
        String largeText2 = sb2.toString();

        TestCase[] tests = new TestCase[] {
                // Test 1: Standard Example 1
                new TestCase("abcde", "ace", 3),

                // Test 2: Identical strings
                new TestCase("abc", "abc", 3),

                // Test 3: Completely disjoint strings
                new TestCase("abc", "def", 0),

                // Test 4: Single character match
                new TestCase("a", "a", 1),

                // Test 5: Single character mismatch
                new TestCase("a", "b", 0),

                // Test 6: One string is a subset pattern scattered inside the other
                new TestCase("oxcpqrsvwf", "shmtulqrpy", 2), // "qr"

                // Test 7: Repeating characters with interleaved order
                new TestCase("aaaaa", "aaa", 3),

                // Test 8: Subsequence with alternating patterns
                new TestCase("ezupkr", "ubmrapg", 2), // "ur"

                // Test 9: Strings with completely reversed order (No valid >1 subsequence)
                new TestCase("abcdef", "fedcba", 1),

                // Test 10: Hard Case - Max constraints (1000 chars each)
                new TestCase(largeText1, largeText2, 962)
        };

        int passed = 0;
        System.out.println("=================== RUNNING TESTS ===================\n");

        for (int i = 0; i < tests.length; i++) {
            TestCase test = tests[i];
            int actual = runner.longestCommonSubsequence(test.text1, test.text2);
            boolean isPass = actual == test.expected;

            if (isPass) passed++;

            // Truncate long strings for cleaner display in output
            String displayT1 = test.text1.length() > 20 ? test.text1.substring(0, 17) + "..." : test.text1;
            String displayT2 = test.text2.length() > 20 ? test.text2.substring(0, 17) + "..." : test.text2;

            System.out.printf("Test %-2d: %s\n", (i + 1), isPass ? "[PASS]" : "[FAIL]");
            System.out.printf("  Inputs  : text1 = \"%s\", text2 = \"%s\"\n", displayT1, displayT2);
            System.out.printf("  Expected: %d\n", test.expected);
            System.out.printf("  Actual  : %d\n", actual);
            System.out.println("---------------------------------------------------");
        }

        System.out.printf("\nSUMMARY: %d / %d Test Cases Passed.\n", passed, tests.length);
        System.out.println("===================================================");
    }
}