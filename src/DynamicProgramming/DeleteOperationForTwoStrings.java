package DynamicProgramming;

public class DeleteOperationForTwoStrings {

    // IMPLEMENT YOUR SOLUTION HERE
    public int minDistance(String a, String b) {
        int x=a.length(),y=b.length();
        int[]prev=new int[y+1];
        for(int i=1;i<=x;i++){
            int[]curr=new int[y+1];
            for(int j=1;j<=y;j++){
                if(a.charAt(i-1)==b.charAt(j-1))curr[j]=prev[j-1]+1;
                else{
                    int p1=prev[j],p2=curr[j-1];
                    curr[j]=Math.max(p1,p2);
                }
            }
            prev=curr;
        }
        int ans=(x-prev[y])+(y-prev[y]);
        return ans;
    }

    public static void main(String[] args) {
        DeleteOperationForTwoStrings testRunner = new DeleteOperationForTwoStrings();

        // Define Test Cases
        TestCase[] testCases = new TestCase[] {
                // 1. Standard Case: Partial overlap at the beginning/end
                new TestCase("sea", "eat", 2),

                // 2. Standard Case: One string is a scattered subsequence of the other
                new TestCase("leetcode", "etco", 4),

                // 3. Edge Case: Both strings are identical (Minimum deletion needed)
                new TestCase("java", "java", 0),

                // 4. Edge Case: Single characters that match
                new TestCase("a", "a", 0),

                // 5. Edge Case: Single characters that are completely different
                new TestCase("a", "b", 2),

                // 6. Standard Case: Completely disjoint strings (No characters in common)
                new TestCase("abc", "def", 6),

                // 7. Hard Case: One string is fully contained inside the other as a substring
                new TestCase("substring", "str", 6),

                // 8. Hard Case: Multiple repeating characters with complex interleaving
                new TestCase("plasma", "altruism", 8),

                // 9. Hard Case: Anagrams but with no common sequence ordering
                new TestCase("abcdef", "fecdba", 10),

                // 10. Hard/Stress Case: 500 characters each (Maximum constraint evaluation)
                new TestCase(generateMaxString('a', 500), generateMaxString('b', 500), 1000)
        };

        // Run Test Cases
        int passed = 0;
        System.out.println("Running tests for: Delete Operation for Two Strings\n");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];

            // Truncate long strings for console visibility
            String displayW1 = tc.word1.length() > 20 ? tc.word1.substring(0, 20) + "... [Len: " + tc.word1.length() + "]" : tc.word1;
            String displayW2 = tc.word2.length() > 20 ? tc.word2.substring(0, 20) + "... [Len: " + tc.word2.length() + "]" : tc.word2;

            int actual = testRunner.minDistance(tc.word1, tc.word2);
            boolean isPass = (actual == tc.expected);

            if (isPass) passed++;

            System.out.printf("Test Case %d: %s\n", i + 1, isPass ? "[PASS]" : "[FAIL]");
            System.out.printf("  Word 1:   \"%s\"\n", displayW1);
            System.out.printf("  Word 2:   \"%s\"\n", displayW2);
            System.out.printf("  Expected: %d\n", tc.expected);
            System.out.printf("  Actual:   %d\n\n", actual);
        }

        System.out.println("----------------------------------------");
        System.out.printf("Result: %d/%d Tests Passed.\n", passed, testCases.length);
    }

    // Helper class to encapsulate test structure
    private static class TestCase {
        String word1;
        String word2;
        int expected;

        TestCase(String word1, String word2, int expected) {
            this.word1 = word1;
            this.word2 = word2;
            this.expected = expected;
        }
    }

    // Helper to generate long uniform strings for constraint scaling tests
    private static String generateMaxString(char c, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}