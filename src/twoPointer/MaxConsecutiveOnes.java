package twoPointer;

import java.util.*;

/**
 * Problem: Max Consecutive Ones III
 * Description: Given a binary array nums and an integer k, return the maximum
 * number of consecutive 1's in the array if you can flip at most k 0's.
 */
public class MaxConsecutiveOnes {

    /**
     * Finds the maximum number of consecutive 1s after flipping at most k 0s.
     * * @param nums Binary array of 0s and 1s.
     * @param k Maximum number of 0s allowed to be flipped.
     * @return The length of the longest subarray of 1s.
     */
    public int longestOnes(int[] arr, int k) {
        int i=0,j=0,max=0,zero=0,n=arr.length;
        if(k>=n)return n;

        while(j<n){
            int a=arr[j];
            if(a==0)zero++;

            if(zero>k){
                if(arr[i]==0)zero--;
                i++;
            }
            if(zero<=k){
                max=Math.max(j-i+1,max);
            }
            j++;
        }

        return max;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnes calculator = new MaxConsecutiveOnes();

        // Test Case Data: {int[] nums, int k, int expectedOutput}
        Object[][] testCases = {
                {new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2, 6},   // Standard case
                {new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3, 10}, // Complex case
                {new int[]{1, 1, 1, 1}, 0, 4},                       // k=0, all ones
                {new int[]{0, 0, 0, 0}, 2, 2},                       // all zeros, k < length
                {new int[]{0, 0, 0, 0}, 5, 4},                       // k > length
                {new int[]{1, 0, 0, 1}, 1, 2},                       // single flip options
                {new int[]{0, 1, 0, 1, 0, 1, 0}, 1, 3},              // alternating with k=1
                {new int[]{1, 0, 1, 0, 1}, 2, 5},                    // alternating with enough k to fill
                {new int[]{1}, 0, 1},                                // edge: single element 1, k=0
                {new int[]{0}, 0, 0},                                // edge: single element 0, k=0
                {new int[]{0, 0, 1, 1, 0, 0, 1, 1}, 2, 6},           // zeros at both ends of the window
                {new int[]{1, 1, 0, 1}, 1, 4}                        // one zero in middle
        };

        int passed = 0;
        int failed = 0;

        System.out.println("Running Tests for: MaxConsecutiveOnes\n");
        System.out.printf("%-35s | %-3s | %-10s | %-10s | %-10s%n", "Input Array", "k", "Expected", "Actual", "Result");
        System.out.println("---------------------------------------------------------------------------------------");

        for (Object[] testCase : testCases) {
            int[] nums = (int[]) testCase[0];
            int k = (int) testCase[1];
            int expected = (int) testCase[2];
            int actual = calculator.longestOnes(nums, k);

            String status = (actual == expected) ? "PASSED" : "FAILED";
            if (status.equals("PASSED")) passed++; else failed++;

            // Format array for printing
            String arrayStr = Arrays.toString(nums);
            String displayInput = arrayStr.length() > 30 ? arrayStr.substring(0, 27) + "..." : arrayStr;

            System.out.printf("%-35s | %-3d | %-10d | %-10d | %-10s%n", displayInput, k, expected, actual, status);
        }

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("Total Tests: %d | Passed: %d | Failed: %d%n", testCases.length, passed, failed);
    }
}