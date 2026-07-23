package DynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class LongestIncreasingSubsequence {

    // Approach 1: Memoization (Top-Down Dynamic Programming)
    public int lengthOfLISMemo(int[] arr) {
        int n=arr.length;
        int[][]dp=new int[n][n+1];
        for(int i=0;i<n;i++){
            for(int[]a:dp)Arrays.fill(a,-1);
        }

        return helper(arr,0,-1,dp);
    }

    private int helper(int[]arr,int i,int prev,int[][]dp){
        int n=arr.length;
        if(i==n){
            return 0;
        }

        if(dp[i][prev+1]!=-1)return dp[i][prev+1];

        int take=0;
        if(prev==-1||arr[i]>arr[prev]){
            take=helper(arr,i+1,i,dp)+1;
        }
        int not=helper(arr,i+1,prev,dp);
        return dp[i][prev+1]= Math.max(not,take);
    }

    // Approach 2: Tabulation (Bottom-Up Dynamic Programming)
    public int lengthOfLISTabulation(int[] arr) {
        int n=arr.length;
        int[]dp=new int[n];
        for(int i=0;i<n;i++){
            dp[i]=1;
            for(int j=0;j<i;j++){
                if(arr[j]<arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
        }
        int max=1;
        for(int a:dp)max=Math.max(a,max);
        return max;
    }

    // Approach 3: Binary Search (Patience Sorting / O(N log N))
    public int lengthOfLISBinarySearch(int[] arr) {
        int n=arr.length;
        int length=0;
        for(int a:arr){
            int low=0,hi=length;
            while(low<hi){
                int mid=low+(hi-low)/2;
                if(arr[mid]<a){
                    low=mid+1;
                }else hi=mid;
            }
            arr[low]=a;
            if(length==low)length++;
        }
        return length;
    }

    // Helper method to clone inputs and execute selected approach
    private static int runSelectedApproach(LongestIncreasingSubsequence instance, int approachChoice, int[] nums) {
        int[] inputCopy = nums.clone(); // Pass copy to prevent accidental in-place mutation
        switch (approachChoice) {
            case 1:
                return instance.lengthOfLISMemo(inputCopy);
            case 2:
                return instance.lengthOfLISTabulation(inputCopy);
            case 3:
                return instance.lengthOfLISBinarySearch(inputCopy);
            default:
                throw new IllegalArgumentException("Invalid approach selected.");
        }
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence instance = new LongestIncreasingSubsequence();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println(" LONGEST INCREASING SUBSEQUENCE TESTER");
        System.out.println("=========================================");
        System.out.println("Select the approach you want to test:");
        System.out.println("1. Memoization (Top-Down)");
        System.out.println("2. Tabulation (Bottom-Up)");
        System.out.println("3. Binary Search (O(N log N))");
        System.out.print("Enter choice (1-3): ");

        int choice = scanner.nextInt();
        String approachName = choice == 1 ? "Memoization" : choice == 2 ? "Tabulation" : "Binary Search";

        System.out.println("\nRunning 10 Test Cases using [" + approachName + "]...\n");

        // Define 10 test cases
        TestCase[] testCases = new TestCase[]{
                // Test 1: Standard Example 1
                new TestCase(new int[]{10, 9, 2, 5, 3, 7, 101, 18}, 4, "Standard Example 1"),

                // Test 2: Standard Example 2
                new TestCase(new int[]{0, 1, 0, 3, 2, 3}, 4, "Standard Example 2"),

                // Test 3: All Same Elements (Example 3 - Strictly Increasing Rule)
                new TestCase(new int[]{7, 7, 7, 7, 7, 7, 7}, 1, "All Same Elements"),

                // Test 4: Single Element Array (Minimum Constraint)
                new TestCase(new int[]{42}, 1, "Single Element"),

                // Test 5: Strictly Decreasing Sequence
                new TestCase(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}, 1, "Strictly Decreasing"),

                // Test 6: Strictly Increasing Sequence
                new TestCase(new int[]{1, 2, 3, 4, 5, 6, 7}, 7, "Strictly Increasing"),

                // Test 7: Array with Negative Numbers
                new TestCase(new int[]{-10, -5, -20, -2, 0, -1, 5}, 5, "Negative Numbers"),

                // Test 8: Alternating High and Low
                new TestCase(new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80}, 6, "Alternating High-Low"),

                // Test 9: Duplicates Mixed with Increasing Elements
                new TestCase(new int[]{1, 3, 2, 3, 4, 3, 5, 5, 6}, 5, "Duplicates Mixed"),

                // Test 10: Larger Sequence with Multiple Subsequences
                new TestCase(new int[]{1, 100, 2, 101, 3, 102, 4, 103}, 5, "Interleaved Subsequences")
        };

        int passedCount = 0;

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int actual = runSelectedApproach(instance, choice, tc.nums);
            boolean passed = actual == tc.expected;

            if (passed) passedCount++;

            System.out.printf("Test %2d [%-25s]: %s\n", (i + 1), tc.description, (passed ? "PASS ✅" : "FAIL ❌"));
            System.out.println("   Input:    " + Arrays.toString(tc.nums));
            System.out.println("   Expected: " + tc.expected);
            System.out.println("   Actual:   " + actual);
            System.out.println("--------------------------------------------------");
        }

        System.out.printf("\nSUMMARY: %d/%d Test Cases Passed.\n", passedCount, testCases.length);
        scanner.close();
    }

    // Inner class to structure individual test case inputs & expectations
    private static class TestCase {
        int[] nums;
        int expected;
        String description;

        TestCase(int[] nums, int expected, String description) {
            this.nums = nums;
            this.expected = expected;
            this.description = description;
        }
    }
}