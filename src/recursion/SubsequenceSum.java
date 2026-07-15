package recursion;

import java.util.*;

/**
 * Problem: Check if there exists a subsequence with sum K
 * Logic: You must implement the 'hasSubsequenceWithSum' method.
 */
public class SubsequenceSum {

    /**
     * Determines if a subsequence exists that sums up to k.
     *
     * @param arr The input array of integers.
     * @param k    The target sum.
     * @return     True if such a subsequence exists, false otherwise.
     */
    public boolean hasSubsequenceWithSum(int[] arr, int k) {
        int n=arr.length;
        if(n==1)return arr[0]==k;

        return helper(arr,0,k);
    }

    private boolean helper(int[]arr,int i,int target){
        int n=arr.length;
        if(n==i){
            return target == 0;
        }
        if(helper(arr,i+1,target-arr[i]))return true;
        if(helper(arr,i+1,target))return true;
        return false;
    }

    public static void main(String[] args) {
        SubsequenceSum runner = new SubsequenceSum();

        // Test Case Definitions: {Input Array, Target K, Expected Boolean}
        Object[][] testCases = {
                {new int[]{1, 2, 3, 4, 5}, 8, true},       // Standard case
                {new int[]{4, 3, 9, 2}, 10, false},        // Standard case (impossible)
                {new int[]{1, 10, 4, 5}, 16, true},        // Example from prompt
                {new int[]{2, 4, 6}, 5, false},            // All even, target odd
                {new int[]{5, 5, 5}, 15, true},            // Using all elements
                {new int[]{10}, 10, true},                 // Single element matches
                {new int[]{10}, 5, false},                 // Single element mismatch
                {new int[]{}, 0, true},                    // Edge case: Empty array, sum 0 (Empty subsequence)
                {new int[]{1, 2, 7}, 0, true},             // Target 0 is always possible (Empty subsequence)
                {new int[]{1, 5, 20, 3}, 29, true},        // Large sum
                {new int[]{1, 2, 3}, 10, false},           // K > Total sum of array
                {new int[]{100, 200, 300}, 250, false}     // Large values, impossible combination
        };

        int passed = 0;
        System.out.println("Running Tests...\n" + "=".repeat(60));

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            boolean expected = (boolean) testCases[i][2];

            boolean actual = runner.hasSubsequenceWithSum(nums, k);
            boolean isCorrect = (actual == expected);

            if (isCorrect) passed++;

            System.out.printf("Test Case %d:\n", i + 1);
            System.out.printf("Input: nums = %s, k = %d\n", Arrays.toString(nums), k);
            System.out.printf("Expected: %b | Actual: %b\n", expected, actual);
            System.out.println("Result: " + (isCorrect ? "✅ PASSED" : "❌ FAILED"));
            System.out.println("-".repeat(60));
        }

        System.out.printf("\nSummary: %d/%d Tests Passed.\n", passed, testCases.length);
    }
}