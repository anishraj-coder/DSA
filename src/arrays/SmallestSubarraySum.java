package arrays;

import java.util.Arrays;

/**
 * Problem: Smallest Sum Contiguous Subarray
 * Goal: Find the minimum sum of any contiguous subarray.
 */
public class SmallestSubarraySum {

    /**
     * Computes the minimum sum of a contiguous subarray.
     *
     * @param arr The input array of integers
     * @param n   The size of the array
     * @return    The smallest contiguous subarray sum
     */
    public int smallestSubarraySum(int[] arr, int n) {
        if(n==1)return Math.max(arr[0],0);
        int sum=arr[0],min=arr[0];
        for(int i=1;i<n;i++){
            int v1=sum+arr[i];
            int v2=arr[i];
            sum=Math.min(v1,v2);
            min=Math.min(sum,min);
        }
        return min;
    }

    public static void main(String[] args) {
        SmallestSubarraySum instance = new SmallestSubarraySum();

        Object[][] testCases = {
                // {Array, N, Expected Output}
                {new int[]{3, -4, 2, -3, -1, 7, -5}, 7, -6},    // Mixed numbers
                {new int[]{2, 6, 8, 1, 4}, 5, 1},               // All positive (min is smallest element)
                {new int[]{-1, -2, -3, -4}, 4, -10},            // All negative (sum of all elements)
                {new int[]{10}, 1, 10},                         // Single positive element
                {new int[]{-10}, 1, -10},                       // Single negative element
                {new int[]{0, 0, 0}, 3, 0},                     // All zeros
                {new int[]{1, -1, 1, -1}, 4, -1},               // Alternating signs
                {new int[]{10, -5, -5, 10}, 4, -10},            // Minimum in the middle
                {new int[]{-100, 50, -100}, 3, -150},           // Gaps with positive numbers
                {new int[]{1000000, -1000000, 1000000}, 3, -1000000}, // Large values
                {new int[]{5, 4, 3, 2, 1, -10, 1, 2, 3}, 9, -10} // Dip at the end
        };

        int passed = 0;
        int failed = 0;

        System.out.println("Running Test Cases...\n" + "=".repeat(50));

        for (int i = 0; i < testCases.length; i++) {
            int[] arr = (int[]) testCases[i][0];
            int n = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = instance.smallestSubarraySum(arr, n);

            if (actual == expected) {
                System.out.printf("Test Case %d: PASSED\n", i + 1);
                passed++;
            } else {
                System.out.printf("Test Case %d: FAILED\n", i + 1);
                System.out.printf("   Input: %s\n", Arrays.toString(arr));
                System.out.printf("   Expected: %d\n", expected);
                System.out.printf("   Actual:   %d\n", actual);
                failed++;
            }
        }

        System.out.println("=".repeat(50));
        System.out.printf("Final Result: %d Passed, %d Failed\n", passed, failed);
    }
}