package twoPointer;

import java.util.*;

/**
 * Problem: Binary Subarrays With Sum
 * Description: Given a binary array nums and an integer goal, return the number
 * of non-empty subarrays with a sum equal to the goal.
 */
public class BinarySubarraySum {

    /**
     * Calculates the number of subarrays that sum up to exactly 'goal'.
     * @param arr Binary array of 0s and 1s.
     * @param goal The target sum.
     * @return Total count of subarrays with sum equal to goal.
     */
    public int numSubarraysWithSum(int[] arr, int goal) {
        int count=0,sum=0,n=arr.length;
        Map<Integer,Integer>map=new HashMap<>();
        map.put(0,1);
        for(int i=0;i<n;i++){
            sum+=arr[i];
            int diff=sum-goal;
            count+=map.getOrDefault(diff,0);
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        BinarySubarraySum solver = new BinarySubarraySum();

        // Test Case Data: {int[] nums, int goal, int expectedOutput}
        Object[][] testCases = {
                {new int[]{1, 0, 1, 0, 1}, 2, 4},       // Standard: multiple overlapping subarrays
                {new int[]{0, 0, 0, 0, 0}, 0, 15},      // Edge: all zeros, goal 0 (n*(n+1)/2)
                {new int[]{1, 1, 1, 1, 1}, 3, 3},       // Standard: all ones
                {new int[]{1, 0, 1, 0, 1}, 5, 0},       // Edge: goal impossible (too high)
                {new int[]{0, 0, 0, 1, 0, 0}, 1, 12},   // Hard: single 1 surrounded by zeros
                {new int[]{1, 1, 1, 1, 1}, 0, 0},       // Edge: goal 0 but no zeros in array
                {new int[]{0, 1, 1, 1, 0}, 3, 4},       // Standard: zeros at boundaries
                {new int[]{1}, 1, 1},                   // Edge: single element matches
                {new int[]{1}, 0, 0},                   // Edge: single element doesn't match
                {new int[]{0, 0, 1, 0, 1, 0, 0}, 2, 9}, // Hard: many zeros and mid-range goal
                {new int[]{1, 0, 0, 0, 1}, 1, 8},       // Case: goal 1 with large gap
                {new int[]{1, 1, 0, 0, 1}, 2, 5}        // Case: mixed density
        };

        int passed = 0;
        int failed = 0;

        System.out.println("Running Tests for: BinarySubarraySum\n");
        System.out.printf("%-30s | %-5s | %-10s | %-10s | %-10s%n", "Input Array", "Goal", "Expected", "Actual", "Result");
        System.out.println("----------------------------------------------------------------------------------");

        for (Object[] testCase : testCases) {
            int[] nums = (int[]) testCase[0];
            int goal = (int) testCase[1];
            int expected = (int) testCase[2];
            int actual = solver.numSubarraysWithSum(nums, goal);

            String status = (actual == expected) ? "PASSED" : "FAILED";
            if (status.equals("PASSED")) passed++; else failed++;

            String arrayStr = Arrays.toString(nums);
            String displayInput = arrayStr.length() > 25 ? arrayStr.substring(0, 22) + "..." : arrayStr;

            System.out.printf("%-30s | %-5d | %-10d | %-10d | %-10s%n", displayInput, goal, expected, actual, status);
        }

        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("Total Tests: %d | Passed: %d | Failed: %d%n", testCases.length, passed, failed);
    }
}