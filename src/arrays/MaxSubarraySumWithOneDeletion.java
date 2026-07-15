package arrays;

import java.util.Arrays;

/**
 * Problem: Maximum Subarray Sum with One Deletion
 * Goal: Find the maximum sum of a non-empty contiguous subarray with at most one deletion.
 */
public class MaxSubarraySumWithOneDeletion {

    /**
     * Computes the maximum sum of a contiguous subarray with at most one deletion.
     *
     * @param arr Input array of integers.
     * @return The maximum possible sum.
     */
    public int maximumSum(int[] arr) {
        int n=arr.length;
        if(n==1)return Math.max(0,arr[0]);
        int delete=0,nodelete=arr[0],res=arr[0];
        for(int i=1;i<n;i++){
            int v1=arr[i],v2=nodelete+arr[i],v3=delete+arr[i],v4=nodelete;
            res=Math.max(Math.max(v1,v2),Math.max(v4,v3));
            delete=Math.max(v4,v3);
            nodelete=Math.max(v1,v2);
        }
        return res;
    }

    public static void main(String[] args) {
        MaxSubarraySumWithOneDeletion solver = new MaxSubarraySumWithOneDeletion();

        Object[][] testCases = {
                {new int[]{1, -2, 0, 3}, 4, "Standard case with one negative in middle"},
                {new int[]{1, -2, -2, 3}, 3, "Two negatives, better to take single element"},
                {new int[]{-1, -1, -1, -1}, -1, "All negatives (must keep at least one)"},
                {new int[]{10, 20, -10, 30, -5, 40}, 85, "Large positive sum with one deletion at index 2"},
                {new int[]{7, 6, 5, 4}, 22, "All positives (no deletion needed)"},
                {new int[]{2, 1, -2, -5, -2}, 3, "Negative values at the end"},
                {new int[]{-50}, -50, "Single element array"},
                {new int[]{1, -2, 3}, 4, "One deletion makes a better sum"},
                {new int[]{-1, 12, -1, -1, 15, -1}, 26, "Multiple negatives, deleting one strategically"},
                {new int[]{1, 2, 3, -10, 4, 5, 6}, 21, "Long array with a large negative bridge"},
                {new int[]{0, -1, 0, -1, 0}, 0, "Zeros and negatives"}
        };

        int passed = 0;
        System.out.println("Running Tests...\n" + "=".repeat(60));

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int expected = (int) testCases[i][1];
            String description = (String) testCases[i][2];

            int actual = solver.maximumSum(input);
            boolean isCorrect = (actual == expected);

            if (isCorrect) {
                passed++;
                System.out.printf("Test Case %d: [PASSED]\n", i + 1);
            } else {
                System.out.printf("Test Case %d: [FAILED]\n", i + 1);
                System.out.println("   Description: " + description);
                System.out.println("   Input: " + Arrays.toString(input));
                System.out.println("   Expected: " + expected);
                System.out.println("   Actual:   " + actual);
            }
            System.out.println("-".repeat(60));
        }

        System.out.printf("\nFinal Result: %d/%d Passed\n", passed, testCases.length);
    }
}