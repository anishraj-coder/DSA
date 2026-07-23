package DynamicProgramming;

import java.util.Arrays;

public class CoinChange {

    public int coinChange(int[] arr, int t) {
        int n= arr.length;
        int[]prev=new int[t+1];
        for(int j=0;j<=t;j++){
            if(j%arr[0]==0)prev[j]=j/arr[0];
            else prev[j]=(int)1e9;
        }
        for(int i=1;i<n;i++){
            int[]curr=new int[t+1];
            for(int j=0;j<=t;j++){
                int pick=(arr[i]<=j)?curr[j-arr[i]]+1:(int)1e9;
                int not=prev[j];
                curr[j]=Math.min(pick,not);
            }
            prev=curr;
        }
        return prev[t]>=1e9?-1:prev[t];
    }
    private int helper(int[]arr,int i,int t){
        if(t==0)return 0;
        if(i==0){
            return (t%arr[0]==0)?t/arr[0]:(int)1e9;
        }
        int pick=(arr[i]<=t)?helper(arr,i,t-arr[i])+1:(int)1e9;
        int not=helper(arr,i-1,t);
        return Math.min(pick,not);
    }

    public static void main(String[] args) {
        CoinChange tester = new CoinChange();
        int totalPassed = 0;

        // Test Cases Definitions
        TestCase[] testCases = new TestCase[]{
                // 1. Standard Case: Example 1
                new TestCase(new int[]{1, 2, 5}, 11, 3, "Example 1 from description (5 + 5 + 1)"),

                // 2. Standard Case: Example 2
                new TestCase(new int[]{2}, 3, -1, "Example 2 from description (Impossible combination)"),

                // 3. Edge Case: Example 3
                new TestCase(new int[]{1}, 0, 0, "Example 3 from description (Amount is 0)"),

                // 4. Edge Case: Amount cannot be divided by any coin
                new TestCase(new int[]{5, 10}, 7, -1, "Amount smaller than smallest coin and unmatchable"),

                // 5. Edge Case: Large amount with a single large coin matching perfectly
                new TestCase(new int[]{10000}, 10000, 1, "Single large denomination matching exactly"),

                // 6. Hard Case: Greedy choice fails (Greedy takes 9 + 1 + 1 = 3 coins, but 6 + 5 = 2 coins is optimal)
                new TestCase(new int[]{1, 5, 6, 9}, 11, 2, "Greedy algorithm failure trap (Optimal is 6 + 5)"),

                // 7. Hard Case: Another classic greedy trap (Greedy takes 4 + 1 + 1 = 3 coins, but 3 + 3 = 2 coins is optimal)
                new TestCase(new int[]{1, 3, 4}, 6, 2, "Greedy algorithm failure trap (Optimal is 3 + 3)"),

                // 8. Hard Case: Large amount with large denominations that can't form the target
                new TestCase(new int[]{4, 6, 8}, 11, -1, "All coins are even, amount is odd (Impossible)"),

                // 9. Hard Case: Multiple combinations possible, testing deep minimal search
                new TestCase(new int[]{2, 5, 10, 25}, 47, 5, "Multiple choices available (25 + 10 + 10 + 2 or 25 + 5 + 5 + 10 + 2, etc.)"),

                // 10. Hard Case: Maximum constraint limits
                new TestCase(new int[]{186, 419, 83, 408}, 6249, 20, "Large amount with irregular coin values")
        };

        System.out.println("Running Tests...\n--------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            // Clone array to protect test data integrity
            int[] coinsCopy = tc.coins.clone();

            int actual = tester.coinChange(coinsCopy, tc.amount);
            boolean passed = (actual == tc.expected);

            if (passed) {
                totalPassed++;
                System.out.printf("Test Case %d: PASSED\n", i + 1);
            } else {
                System.out.printf("Test Case %d: FAILED\n", i + 1);
            }
            System.out.printf("  Description: %s\n", tc.description);
            System.out.printf("  Coins:       %s\n", Arrays.toString(tc.coins));
            System.out.printf("  Amount:      %d\n", tc.amount);
            System.out.printf("  Expected:    %d\n", tc.expected);
            System.out.printf("  Actual:      %d\n", actual);
            System.out.println("--------------------------------------------------");
        }

        System.out.printf("\nTotal Results: %d/%d Passed.\n", totalPassed, testCases.length);
    }

    // Helper class to store test case payloads cleanly
    private static class TestCase {
        int[] coins;
        int amount;
        int expected;
        String description;

        TestCase(int[] coins, int amount, int expected, String description) {
            this.coins = coins;
            this.amount = amount;
            this.expected = expected;
            this.description = description;
        }
    }
}