package DynamicProgramming;

import java.util.Arrays;

public class AssignCookies {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i=0,j=0,count=0;
        while(i<g.length&&j<s.length){
            if(g[i]<=s[j]){
                i++;
                count++;
            }
            j++;

        }
        return count;
    }

    public static void main(String[] args) {
        AssignCookies tester = new AssignCookies();
        int totalPassed = 0;

        // Test Cases Definitions
        TestCase[] testCases = new TestCase[]{
                // 1. Standard Case: Example 1
                new TestCase(new int[]{1, 2, 3}, new int[]{1, 1}, 1, "Example 1 from description"),

                // 2. Standard Case: Example 2
                new TestCase(new int[]{1, 2}, new int[]{1, 2, 3}, 2, "Example 2 from description"),

                // 3. Edge Case: No cookies available
                new TestCase(new int[]{1, 2, 3}, new int[]{}, 0, "No cookies available"),

                // 4. Edge Case: No children
                new TestCase(new int[]{}, new int[]{1, 2, 3}, 0, "No children to assign to"),

                // 5. Edge Case: Both arrays empty
                new TestCase(new int[]{}, new int[]{}, 0, "Both children and cookies arrays empty"),

                // 6. Hard Case: All cookies are too small for any child
                new TestCase(new int[]{5, 6, 7}, new int[]{1, 2, 3, 4}, 0, "All cookies smaller than minimum greed"),

                // 7. Hard Case: Cookies exactly match all children's greed but arrays are unsorted
                new TestCase(new int[]{10, 20, 30}, new int[]{30, 10, 20}, 3, "Exact matches but inputs are out of order"),

                // 8. Hard Case: Many cookies, but only largest ones can satisfy a few children
                new TestCase(new int[]{4, 5, 6, 7}, new int[]{1, 2, 3, 5, 7}, 2, "Abundant small cookies, only two large enough"),

                // 9. Hard Case: Duplicate values in both greed and sizes
                new TestCase(new int[]{2, 2, 3, 3}, new int[]{2, 3, 3}, 3, "Duplicate greed levels and duplicate cookie sizes"),

                // 10. Hard Case: Large constraint values (handling large inputs gracefully)
                new TestCase(
                        new int[]{100, 200, 300, 400, 500},
                        new int[]{99, 199, 299, 399, 501},
                        1,
                        "Only one giant cookie satisfies the largest greed factor"
                )
        };

        System.out.println("Running Tests...\n--------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            // Clone arrays to prevent user solution from altering the original test case printout
            int[] gCopy = tc.g.clone();
            int[] sCopy = tc.s.clone();

            int actual = tester.findContentChildren(gCopy, sCopy);
            boolean passed = (actual == tc.expected);

            if (passed) {
                totalPassed++;
                System.out.printf("Test Case %d: PASSED\n", i + 1);
            } else {
                System.out.printf("Test Case %d: FAILED\n", i + 1);
            }
            System.out.printf("  Description: %s\n", tc.description);
            System.out.printf("  Input g:     %s\n", Arrays.toString(tc.g));
            System.out.printf("  Input s:     %s\n", Arrays.toString(tc.s));
            System.out.printf("  Expected:    %d\n", tc.expected);
            System.out.printf("  Actual:      %d\n", actual);
            System.out.println("--------------------------------------------------");
        }

        System.out.printf("\nTotal Results: %d/%d Passed.\n", totalPassed, testCases.length);
    }

    // Helper class to store test case payloads cleanly
    private static class TestCase {
        int[] g;
        int[] s;
        int expected;
        String description;

        TestCase(int[] g, int[] s, int expected, String description) {
            this.g = g;
            this.s = s;
            this.expected = expected;
            this.description = description;
        }
    }
}