package DynamicProgramming;

import java.util.Arrays;

public class CoinChangeII {

    // WRITE YOUR IMPLEMENTATION HERE
    public int change(int t, int[] arr) {
        int n=arr.length;
        int[]prev=new int[t+1];
        for(int i=0;i<=t;i++)prev[i]=(i%arr[0]==0)?1:0;

        for(int i=1;i<n;i++){
            int[]curr=new int[t+1];
            for(int j=0;j<=t;j++){
                int pick=(arr[i]<=j)?curr[j-arr[i]]:0;
                int not=prev[j];

                curr[j]=pick+not;
            }
            prev=curr;
        }

        return prev[t];
    }

    // Helper class to hold test case data
    static class TestCase {
        int id;
        int amount;
        int[] coins;
        int expected;

        TestCase(int id, int amount, int[] coins, int expected) {
            this.id = id;
            this.amount = amount;
            this.coins = coins;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        CoinChangeII tester = new CoinChangeII();

        // 10 Comprehensive Test Cases including Edge and Hard Cases
        TestCase[] testCases = new TestCase[] {
                // Case 1: Standard example case 1
                new TestCase(1, 5, new int[]{1, 2, 5}, 4),

                // Case 2: Standard example case 2 (impossible to make amount)
                new TestCase(2, 3, new int[]{2}, 0),

                // Case 3: Standard example case 3 (exact single match)
                new TestCase(3, 10, new int[]{10}, 1),

                // Case 4: Edge case - amount is 0 (always 1 way: choosing no coins)
                new TestCase(4, 0, new int[]{1, 2, 3}, 1),

                // Case 5: Element cannot divide the amount perfectly
                new TestCase(5, 7, new int[]{2, 4, 6}, 0),

                // Case 6: Multiple coin denominations, large amount
                new TestCase(6, 10, new int[]{2, 3, 5, 6}, 5),

                // Case 7: Single coin type, multiple combinations possible
                new TestCase(7, 100, new int[]{1}, 1),

                // Case 8: Large coin values that exceed the target amount
                new TestCase(8, 5, new int[]{10, 20, 30}, 0),

                // Case 9: Unsorted coin denominations with fractional combinations
                new TestCase(9, 8, new int[]{5, 1, 2}, 7),

                // Case 10: Hard Case - Large amount with dense combinations
                new TestCase(10, 500, new int[]{1, 2, 5}, 6576)
        };

        int passed = 0;

        System.out.println("--- Running CoinChangeII Tests ---");
        for (TestCase tc : testCases) {
            // Run the implementation
            int actual = tester.change(tc.amount, tc.coins);

            boolean isCorrect = (actual == tc.expected);
            if (isCorrect) {
                passed++;
                System.out.printf("Test Case %d: [PASS]\n", tc.id);
            } else {
                System.out.printf("Test Case %d: [FAIL]\n", tc.id);
                System.out.printf("   Input: amount = %d, coins = %s\n", tc.amount, Arrays.toString(tc.coins));
                System.out.printf("   Expected: %d\n", tc.expected);
                System.out.printf("   Actual:   %d\n", actual);
            }
        }

        System.out.println("-----------------------------------");
        System.out.printf("Result: %d/%d Tests Passed.\n", passed, testCases.length);
    }
}