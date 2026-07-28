package greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class EatPizzas {

    /**
     * Complete this method to solve the Eat Pizzas! problem.
     * 
     * @param arr Array representing weights of pizzas (length is a multiple of 4).
     * @return Maximum total weight gained after eating all pizzas optimally.
     */
    public long maxWeight(int[] arr) {
        int n=arr.length,k=n/4,curr=n-1;
        Arrays.sort(arr);
        long total=0;
        for(int i=1;i<=k;i+=2)total+=arr[curr--];
        curr--;
        for(int i=2;i<=k;i+=2) {
        	total+=arr[curr];
        	curr-=2;
        }
        return total;
    }

    public static void main(String[] args) {
        EatPizzas solver = new EatPizzas();

        TestCase[] testCases = new TestCase[] {
            // Case 1: Example 1 (n = 8, 2 days: 1 odd, 1 even)
            new TestCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 14L),
            
            // Case 2: Example 2 (All small values)
            new TestCase(new int[]{2, 1, 1, 1, 1, 1, 1, 1}, 3L),
            
            // Case 3: Minimum size array (n = 4, 1 odd day only)
            new TestCase(new int[]{5, 2, 9, 1}, 9L),
            
            // Case 4: Identical pizza weights (n = 8)
            new TestCase(new int[]{4, 4, 4, 4, 4, 4, 4, 4}, 8L),
            
            // Case 5: 3 Days (n = 12: Days 1, 3 are odd, Day 2 is even)
            new TestCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, 29L),
            
            // Case 6: 4 Days (n = 16: Days 1, 3 are odd [Z], Days 2, 4 are even [Y])
            new TestCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, 56L),
            
            // Case 7: Unsorted random elements with duplicates
            new TestCase(new int[]{10, 20, 10, 30, 20, 40, 50, 30}, 80L),
            
            // Case 8: Heavily skewed values
            new TestCase(new int[]{1, 1, 1, 100, 1, 1, 1, 200}, 300L),
            
            // Case 9: Large integer values (Tests integer overflow prevention)
            new TestCase(new int[]{100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000}, 200000L),
            
            // Case 10: Skewed cluster where even days drop significantly
            new TestCase(new int[]{1, 2, 3, 4, 5, 10, 15, 20}, 30L),
            
            // Case 11: 5 Days (n = 20: 3 odd days, 2 even days)
            new TestCase(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11}, 87L)
        };

        int passed = 0;
        int total = testCases.length;

        System.out.println("==========================================================================");
        System.out.printf("%-11s | %-10s | %-10s | %-8s | %s%n", "Test Case", "Expected", "Actual", "Status", "Details");
        System.out.println("==========================================================================");

        for (int i = 0; i < total; i++) {
            TestCase tc = testCases[i];
            long actual = solver.maxWeight(tc.pizzas.clone());
            boolean isPassed = (actual == tc.expected);
            if (isPassed) passed++;

            String status = isPassed ? "PASSED" : "FAILED";
            String details = "pizzas: " + Arrays.toString(tc.pizzas);

            System.out.printf("%-11d | %-10d | %-10d | %-8s | %s%n", 
                              (i + 1), tc.expected, actual, status, details);
        }

        System.out.println("==========================================================================");
        System.out.printf("SUMMARY: Passed %d / %d test cases. (Failed: %d)%n", passed, total, (total - passed));
        System.out.println("==========================================================================");
    }

    private static class TestCase {
        int[] pizzas;
        long expected;

        TestCase(int[] pizzas, long expected) {
            this.pizzas = pizzas;
            this.expected = expected;
        }
    }
}