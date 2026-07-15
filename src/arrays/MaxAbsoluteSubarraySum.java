package arrays;

import java.util.Arrays;

/**
 * Problem: Maximum Absolute Sum of Any Subarray
 * Goal: Find the maximum absolute sum of any contiguous subarray.
 * Note: The subarray can be empty (sum = 0), but given the constraints and
 * logic of absolute values, the result will be >= 0.
 */
public class MaxAbsoluteSubarraySum {

    /**
     * Computes the maximum absolute sum of any subarray.
     *
     * @param arr Input array of integers.
     * @return The maximum absolute sum.
     */
    public int maxAbsoluteSum(int[] arr) {
        int n=arr.length;
        if(n==1)return Math.abs(arr[0]);
        int currMax=arr[0],currMin=arr[0],max=arr[0],min=arr[0];
        for(int i=1;i<n;i++){
            int v1=currMax+arr[i],v2=currMin+arr[i];
            max=Math.max(max,Math.max(arr[i],v1));
            min=Math.min(min,Math.min(arr[i],v2));
            currMax=Math.max(arr[i],v1);
            currMin=Math.min(arr[i],v2);

        }
        min=Math.abs(min);
        return Math.max(max,min);
    }

    public static void main(String[] args) {
        MaxAbsoluteSubarraySum solver = new MaxAbsoluteSubarraySum();

        Object[][] testCases = {
                {new int[]{1, -3, 2, 3, -4}, 5, "Standard case: subarray [2, 3] gives 5"},
                {new int[]{2, -5, 1, -4, 3, -2}, 8, "Standard case: subarray [-5, 1, -4] gives abs(-8) = 8"},
                {new int[]{-7, -8, -10, -2}, 27, "All negative numbers: sum all and take absolute"},
                {new int[]{5, 10, 15}, 30, "All positive numbers: sum all"},
                {new int[]{0, 0, 0}, 0, "All zeros"},
                {new int[]{-10000}, 10000, "Single negative element at constraint limit"},
                {new int[]{10000}, 10000, "Single positive element at constraint limit"},
                {new int[]{1, -1, 1, -1, 1}, 1, "Alternating small values"},
                {new int[]{-5, 5, -5, 5, -10}, 10, "Fluctuating values with a clear negative peak"},
                {new int[]{10, -20, -30, 10, 5}, 50, "Large negative sum in the middle"},
                {new int[]{-1, 2, -3, 4, -5, 6}, 6, "Single large element is the best subarray"}
        };

        int passed = 0;
        System.out.println("Running Tests...\n" + "=".repeat(60));

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int expected = (int) testCases[i][1];
            String description = (String) testCases[i][2];

            int actual = solver.maxAbsoluteSum(input);
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