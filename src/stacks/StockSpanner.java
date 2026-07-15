package stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.Arrays;

/**
 * Problem: Online Stock Span
 * Constraints:
 * 1 <= price <= 10^5
 * At most 10^4 calls will be made to next.
 */
public class StockSpanner {

    Deque<Integer> prices,count;

    public StockSpanner() {
        prices=new ArrayDeque<>();
        count=new ArrayDeque<>();
    }

    public int next(int price) {
        if(prices.isEmpty()){
            prices.offerLast(price);
            count.offerLast(1);
            return 1;
        }
        int sum=1;
        while(!prices.isEmpty()&&price>=prices.peekLast()){
            sum+=count.pollLast();
            prices.pollLast();
        }
        count.offerLast(sum);
        prices.offerLast(price);
        return sum;
    }

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        System.out.println("Running Online Stock Span Tests...\n");
        int passed = 0;
        int totalTests = 10;

        // Test Case 1: Standard Example
        passed += runTest(1,
                new int[]{100, 80, 60, 70, 60, 75, 85},
                new int[]{1, 1, 1, 2, 1, 4, 6});

        // Test Case 2: Strictly Increasing
        passed += runTest(2,
                new int[]{10, 20, 30, 40, 50},
                new int[]{1, 2, 3, 4, 5});

        // Test Case 3: Strictly Decreasing
        passed += runTest(3,
                new int[]{50, 40, 30, 20, 10},
                new int[]{1, 1, 1, 1, 1});

        // Test Case 4: Same Prices
        passed += runTest(4,
                new int[]{10, 10, 10, 10},
                new int[]{1, 2, 3, 4});

        // Test Case 5: Zig-zag (Small Peaks)
        passed += runTest(5,
                new int[]{10, 5, 11, 6, 12},
                new int[]{1, 1, 3, 1, 5});

        // Test Case 6: Deep Valley then Recovery
        passed += runTest(6,
                new int[]{100, 20, 30, 40, 50, 110},
                new int[]{1, 1, 2, 3, 4, 6});

        // Test Case 7: Alternating Flat and Increase
        passed += runTest(7,
                new int[]{31, 41, 41, 51},
                new int[]{1, 2, 3, 4});

        // Test Case 8: Large Single Spike at end
        passed += runTest(8,
                new int[]{1, 1, 1, 1, 10},
                new int[]{1, 2, 3, 4, 5});

        // Test Case 9: Single Element
        passed += runTest(9,
                new int[]{5},
                new int[]{1});

        // Test Case 10: Late recovery not quite hitting early peak
        passed += runTest(10,
                new int[]{90, 60, 70, 80, 50},
                new int[]{1, 1, 2, 3, 1});

        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("Final Result: %d/%d Passed\n", passed, totalTests);
    }

    private static int runTest(int id, int[] inputs, int[] expected) {
        StockSpanner spanner = new StockSpanner();
        int[] actual = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            actual[i] = spanner.next(inputs[i]);
        }

        boolean isCorrect = Arrays.equals(actual, expected);
        System.out.printf("Test Case %d: %s\n", id, isCorrect ? "PASSED ✅" : "FAILED ❌");
        System.out.println("Input Prices: " + Arrays.toString(inputs));
        System.out.println("Expected Spans: " + Arrays.toString(expected));
        System.out.println("Actual Spans:   " + Arrays.toString(actual));
        System.out.println("-----------------------------------------------------------------------");
        return isCorrect ? 1 : 0;
    }
}