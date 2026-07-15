package Contest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CardFlippingGame {

    /**
     * Finds the minimum possible good integer after flipping any number of cards.
     * * @param fronts The integers on the front of the cards.
     * @param backs The integers on the back of the cards.
     * @return The minimum good integer, or 0 if none exist.
     */
    public int flipgame(int[] fronts, int[] backs) {
        Set<Integer> set=new HashSet<>();
        int n=fronts.length;
        for(int i=0;i<n;i++){
            if(fronts[i]==backs[i])set.add(fronts[i]);
        }
        if(set.size()==n)return 0;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){

            if(!set.contains(fronts[i]))min=Math.min(fronts[i],min);
            if(!set.contains(backs[i]))min=Math.min(backs[i],min);
        }
        return min;
    }

    public static void main(String[] args) {
        CardFlippingGame instance = new CardFlippingGame();

        // Define test cases: {fronts, backs, expected}
        TestCase[] testCases = new TestCase[] {
                // 1. Standard example case 1
                new TestCase(new int[]{1, 2, 4, 4, 7}, new int[]{1, 3, 4, 1, 3}, 2),

                // 2. Standard example case 2 (No valid choice possible)
                new TestCase(new int[]{1}, new int[]{1}, 0),

                // 3. Edge Case: Single card with different numbers (can always pick the minimum)
                new TestCase(new int[]{5}, new int[]{3}, 3),

                // 4. Edge Case: Multiple cards where one number is completely blocked (same front and back)
                new TestCase(new int[]{2, 2, 3}, new int[]{2, 5, 2}, 3),

                // 5. Hard Case: Multiple blocked numbers, return next smallest available
                new TestCase(new int[]{1, 1, 2, 2, 3, 4}, new int[]{1, 4, 2, 5, 6, 7}, 3),

                // 6. Hard Case: Numbers form a cyclical swap pattern but are not identical on the same card
                new TestCase(new int[]{1, 2, 3}, new int[]{2, 3, 1}, 1),

                // 7. Edge Case: All fronts are identical and all backs are identical, but different from each other
                new TestCase(new int[]{10, 10, 10}, new int[]{5, 5, 5}, 5),

                // 8. Edge Case: Interleaved duplicates where one valid path yields a much smaller element
                new TestCase(new int[]{4, 7, 1, 4}, new int[]{4, 4, 7, 1}, 1),

                // 9. Hard Case: Large values with complete overlapping blocking
                new TestCase(new int[]{2000, 1999}, new int[]{2000, 1999}, 0),

                // 10. Complex Case: Multi-card mix where the minimum available choice is sitting on the front of a card
                new TestCase(new int[]{3, 8, 4, 7, 5}, new int[]{9, 3, 8, 5, 6}, 4)
        };

        int passed = 0;
        System.out.println("--- Starting Test Execution ---\n");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int actual = instance.flipgame(tc.fronts, tc.backs);
            boolean isMatch = (actual == tc.expected);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("  Fronts:   " + Arrays.toString(tc.fronts));
            System.out.println("  Backs:    " + Arrays.toString(tc.backs));
            System.out.println("  Expected: " + tc.expected);
            System.out.println("  Actual:   " + actual);

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
        int[] fronts;
        int[] backs;
        int expected;

        TestCase(int[] fronts, int[] backs, int expected) {
            this.fronts = fronts;
            this.backs = backs;
            this.expected = expected;
        }
    }
}