package DynamicProgramming;

public class PalindromicSubstrings {

    // Implement your solution here
    public int countSubstrings(String s) {
        int n=s.length(),count=0;
        for(int i=0;i<n;i++){
            int even=palindromeCount(s,i,i+1);
            int odd=palindromeCount(s,i,i);
            count+=even+odd;
        }

        return count;
    }

    private int palindromeCount(String s,int l,int r){
        int count=0,n=s.length();
        while (l>=0&&r<n&&s.charAt(l)==s.charAt(r)){
            --l;++r;
            count++;
        }

        return count;
    }

    private int bottomUp(String s){
        int n=s.length();
        int[][]dp=new int[n][n];
        int count=0;
        for(int i=0;i<n;i++) {
            dp[i][i] = 1;
            count++;
        }
        for(int i=0;i<n-1;i++){
            if(s.charAt(i)==s.charAt(i+1)){
                count++;
                dp[i][i+1]=1;
            }
        }

        for(int len=3;len<=n;len++){
            for(int j=0;j<=n-len;j++){
                int k=j+len-1;
                if(s.charAt(j)==s.charAt(k)&&(dp[j+1][k-1])==1){
                    dp[j][k]=1;
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        PalindromicSubstrings tester = new PalindromicSubstrings();

        TestCase[] testCases = new TestCase[] {
                // Test 1: Standard Example 1
                new TestCase("abc", 3),

                // Test 2: Standard Example 2 (All identical characters)
                new TestCase("aaa", 6),

                // Test 3: Edge Case - Single character
                new TestCase("a", 1),

                // Test 4: Edge Case - Two identical characters
                new TestCase("aa", 3),

                // Test 5: Edge Case - Two distinct characters
                new TestCase("ab", 2),

                // Test 6: Even-length palindromes included
                new TestCase("abba", 6),

                // Test 7: Odd-length palindromes included
                new TestCase("racecar", 10),

                // Test 8: Mixed repeated characters with nested palindromes
                new TestCase("abaa", 6),

                // Test 9: Alternating characters
                new TestCase("abababa", 16),

                // Test 10: Hard Case - Long string with identical characters
                new TestCase("aaaaa", 15),

                // Test 11: Hard Case - Long string with no palindromes longer than 1
                new TestCase("abcdefghijklmnopqrstuvwxyz", 26),

                // Test 12: Hard Case - Complex combination with multiple overlaps
                new TestCase("bananas", 9)
        };

        int passed = 0;
        int failed = 0;

        System.out.println("==================================================");
        System.out.println("            RUNNING TEST CASES                   ");
        System.out.println("==================================================\n");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int actual = tester.countSubstrings(tc.input);
            boolean isCorrect = actual == tc.expected;

            if (isCorrect) {
                passed++;
                System.out.printf("[PASS] Test %2d | Input: \"%s\" | Expected: %d | Actual: %d\n",
                        i + 1, tc.input, tc.expected, actual);
            } else {
                failed++;
                System.out.printf("[FAIL] Test %2d | Input: \"%s\" | Expected: %d | Actual: %d\n",
                        i + 1, tc.input, tc.expected, actual);
            }
        }

        System.out.println("\n==================================================");
        System.out.println("RESULTS: " + passed + " Passed, " + failed + " Failed out of " + testCases.length + " Total");
        System.out.println("==================================================");
    }

    private static class TestCase {
        String input;
        int expected;

        TestCase(String input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}