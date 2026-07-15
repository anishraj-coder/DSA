package arrays;

import java.util.Arrays;

public class BuySellStock1 {

    /**
     * Calculates the maximum profit from a single buy and sell transaction.
     * @param arr An array where arr[i] is the stock price on day i.
     * @return The maximum profit possible; 0 if no profit can be made.
     */
    public int maxProfit(int[] arr) {
        int n=arr.length;
        if(n<=1)return 0;
        if(n==2){
            return Math.max(arr[1]-arr[0], 0);
        }
        int min=Integer.MAX_VALUE,max=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            min=Math.min(arr[i],min);
            max=Math.max(arr[i]-min,max);
        }
        return max;
    }

    public static void main(String[] args) {
        BuySellStock1 runner = new BuySellStock1();

        // Test Cases: {Input Array, Expected Output, Description}
        Object[][] testCases = {
                {new int[]{7, 1, 5, 3, 6, 4}, 5, "Standard case: Buy at 1, Sell at 6"},
                {new int[]{7, 6, 4, 3, 1}, 0, "Decreasing prices: No profit possible"},
                {new int[]{1, 2, 3, 4, 5, 6}, 5, "Constantly increasing prices"},
                {new int[]{2, 4, 1, 7}, 6, "New minimum appears after a peak"},
                {new int[]{10, 2, 9, 1, 2}, 7, "Global min appears too late for global max"},
                {new int[]{3, 3, 3, 3}, 0, "Flat prices: No profit"},
                {new int[]{1}, 0, "Single day: Cannot sell"},
                {new int[]{1, 10}, 9, "Two days: Direct profit"},
                {new int[]{10, 1}, 0, "Two days: Loss only"},
                {new int[]{100, 180, 260, 310, 40, 535, 695}, 655, "Volatile market with large recovery"}
        };

        int passed = 0;

        System.out.println("--- Starting Test Execution ---\n");

        for (int i = 0; i < testCases.length; i++) {
            int[] prices = (int[]) testCases[i][0];
            int expected = (int) testCases[i][1];
            String description = (String) testCases[i][2];

            int actual = runner.maxProfit(prices);
            boolean isCorrect = (actual == expected);

            if (isCorrect) {
                passed++;
            }

            System.out.printf("Test Case %d: %s\n", i + 1, description);
            System.out.printf("Input: %s\n", Arrays.toString(prices));
            System.out.printf("Expected: %d | Actual: %d\n", expected, actual);
            System.out.println("Result: " + (isCorrect ? "✅ PASSED" : "❌ FAILED"));
            System.out.println("----------------------------------------");
        }

        System.out.printf("\nFinal Result: %d/%d Passed.\n", passed, testCases.length);
    }
}