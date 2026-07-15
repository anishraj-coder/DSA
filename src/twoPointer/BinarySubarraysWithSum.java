package twoPointer;

import java.util.*;

public class BinarySubarraysWithSum {

    /**
     * Given a binary array arr and an integer goal,
     * return the number of non-empty subarrays with a sum goal.
     */
    public int numSubarraysWithSum(int[] arr, int goal) {
        return  helper(arr,goal)-helper(arr,goal-1);
    }

    public int helper(int[]arr,int goal){
        if(goal<0)return 0;
        int n=arr.length,i=0,j=0,count=0,sum=0;
        while(j<n){
            sum+=arr[j];
            while(sum>goal){
                sum-=arr[i];
                i++;
            }
            count+=j-i+1;
            j++;
        }
        return count;
    }

    public static void main(String[] args) {
        BinarySubarraysWithSum solver = new BinarySubarraysWithSum();

        Object[][] testCases = {
                // {nums, goal, expectedOutput}
                {new int[]{1, 0, 1, 0, 1}, 2, 4},
                {new int[]{0, 0, 0, 0, 0}, 0, 15},
                {new int[]{1, 1, 1, 1, 1}, 3, 3},
                {new int[]{0, 0, 0, 0, 0}, 1, 0},         // Goal impossible (all zeros)
                {new int[]{1, 1, 1, 1, 1}, 5, 1},         // Goal is total sum
                {new int[]{1}, 1, 1},                     // Single element (match)
                {new int[]{1}, 0, 0},                     // Single element (no match)
                {new int[]{0}, 0, 1},                     // Single zero, goal zero
                {new int[]{1, 0, 1, 0, 1, 0}, 2, 6},      // Mixed with trailing zero
                {new int[]{0, 0, 0, 1, 0, 0, 0}, 1, 16},  // Single 1 surrounded by 0s
                {new int[]{1, 0, 0, 1, 0, 1}, 2, 5}       // Intermittent zeros
        };

        int passed = 0;
        int failed = 0;

        System.out.println("Running Tests...\n" + "=".repeat(50));

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int goal = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = solver.numSubarraysWithSum(nums, goal);

            if (actual == expected) {
                System.out.printf("Test Case %d: PASSED\n", i + 1);
                passed++;
            } else {
                System.out.printf("Test Case %d: FAILED\n", i + 1);
                System.out.println("   Input: nums = " + Arrays.toString(nums) + ", goal = " + goal);
                System.out.println("   Expected: " + expected);
                System.out.println("   Actual:   " + actual);
                failed++;
            }
        }

        System.out.println("=".repeat(50));
        System.out.printf("Results: %d Passed, %d Failed\n", passed, failed);
    }
}