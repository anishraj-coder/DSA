package DynamicProgramming;

import java.util.Arrays;

public class CountSubsetsWithSumK {

    // IMPLEMENT YOUR SOLUTION HERE
    public static int findWays(int[] arr, int k) {
        // TODO: Implement your O(n * k) solution here
        // Remember to use modulo 1000000007 (10^9 + 7) for large counts

        int n=arr.length,mod=(int)(1e9+7);
        if(k==0){
            int count=0;
            for(int a:arr)count+=(a==0)?1:0;
            return (int )(Math.pow(2,count)%mod);
        }
        int[][]dp=new int[n][k+1];
        if(arr[0]==0)dp[0][0]=2;
        else dp[0][0]=1;
        if(arr[0]!=0&&arr[0]<=k)dp[0][arr[0]]=1;
        for(int i=1;i<n;i++){
            for(int j=0;j<=k;j++){
                int pick=(arr[i]<=j)?dp[i-1][j-arr[i]]:0;
                int not=dp[i-1][j];
                dp[i][j]=(int)((long) pick % mod + not % mod)%mod;
            }
        }
        return dp[n-1][k];
    }

    private static int helper(int[]arr,int i,int curr){
        int mod=(int)1e9+7;
        if(i==0){
            if(curr==0&&arr[0]==0)return 2;
            else if(curr==0||curr==arr[0])return 1;
            else return 0;
        }
        int not=helper(arr,i-1,curr);
        int pick=0;
        if(arr[i]<=curr){
            pick=helper(arr,i-1,curr-arr[i]);
        }

        return (pick+not)%mod;
    }

    public static void main(String[] args) {
        // Test Case Structures: {Array, Target, Expected Output}
        TestCase[] tests = new TestCase[] {
                // 1. Sample Input 1
                new TestCase(new int[]{1, 4, 4, 5}, 5, 3),

                // 2. Sample Input 2 (All identical elements)
                new TestCase(new int[]{1, 1, 1}, 2, 3),

                // 3. Sample Input 3 (Target impossible to reach)
                new TestCase(new int[]{2, 34, 5}, 40, 0),

                // 4. Single element matches target
                new TestCase(new int[]{5}, 5, 1),

                // 5. Single element does not match target
                new TestCase(new int[]{3}, 5, 0),

                // 6. Elements larger than K should be safely ignored
                new TestCase(new int[]{10, 20, 30, 2}, 2, 1),

                // 7. No elements can sum to K (Target less than minimum element)
                new TestCase(new int[]{5, 6, 7, 8}, 3, 0),

                // 8. Multiple distinct combinations forming the same sum
                new TestCase(new int[]{1, 2, 3, 4, 5}, 5, 3), // [5], [2,3], [1,4]

                // 9. Edge case: Array contains zeros (if constraints/variants allow, or treated as positive)
                // Note: The problem description says "positive integers" but constraints mention 0 <= arr[i].
                // If zeros are present, they double the number of subsets. Let's test standard positive behavior first.
                new TestCase(new int[]{2, 3, 5, 6, 8, 10}, 10, 3), // [10], [2,8], [2,3,5]

                // 10. Hard/Large Case: Requires modulo 10^9 + 7 tracking
                // 100 elements of 1s, target 50. Total ways is 100C50 % (10^9 + 7)
                // 100C50 % 1000000007 = 538992043
                new TestCase(createLargeArray(100, 1), 50, 538992043)
        };

        int passed = 0;
        System.out.println("Running tests for CountSubsetsWithSumK...\n");

        for (int i = 0; i < tests.length; i++) {
            int actual = findWays(tests[i].arr, tests[i].k);
            boolean isCorrect = (actual == tests[i].expected);

            if (isCorrect) {
                passed++;
                System.out.printf("Test %2d: [PASS] -> Target: %d\n", i + 1, tests[i].k);
            } else {
                System.out.printf("Test %2d: [FAIL] -> Target: %d\n", i + 1, tests[i].k);
                System.out.printf("         Input Array: %s\n", Arrays.toString(tests[i].arr));
                System.out.printf("         Expected   : %d\n", tests[i].expected);
                System.out.printf("         Actual     : %d\n\n", actual);
            }
        }

        System.out.println("\n-------------------------------------------");
        System.out.printf("Result: %d/%d Test Cases Passed.\n", passed, tests.length);
        System.out.println("-------------------------------------------");
    }

    // Helper class to encapsulate test cases cleanly
    static class TestCase {
        int[] arr;
        int k;
        int expected;

        TestCase(int[] arr, int k, int expected) {
            this.arr = arr;
            this.k = k;
            this.expected = expected;
        }
    }

    // Helper method to build the large test case
    private static int[] createLargeArray(int size, int value) {
        int[] arr = new int[size];
        Arrays.fill(arr, value);
        return arr;
    }
}