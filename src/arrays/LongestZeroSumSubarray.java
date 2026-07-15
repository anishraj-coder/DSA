package arrays;

import java.util.*;

/**
 * Problem: Longest Subarray with Sum 0
 * Find the length of the longest contiguous subarray that sums to zero.
 */
public class LongestZeroSumSubarray {

    /**
     * Calculates the length of the longest contiguous subarray with a sum of 0.
     * * @param arr The input array of integers
     * @param n   The size of the array
     * @return    The length of the longest subarray with sum 0
     */
    public int getMaxLength(int[] arr, int n) {
        if(n==1)return (arr[0]==0)?1:0;
        HashMap<Integer,Integer>map=new HashMap<>();
        map.put(0,-1);
        int sum=0,maxLen=0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
            if(map.containsKey(sum)){
                int curr=i-map.get(sum);
                maxLen=Math.max(curr,maxLen);
            }
            if(!map.containsKey(sum))map.put(sum,i);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestZeroSumSubarray solver = new LongestZeroSumSubarray();
        int testsPassed = 0;

        // Define Test Cases
        TestCase[] testCases = {
                // Case 1: Standard example
                new TestCase(new int[]{15, -2, 2, -8, 1, 7, 10, 23}, 8, 5),

                // Case 2: No subarray sums to zero
                new TestCase(new int[]{2, 10, 4}, 3, 0),

                // Case 3: Entire array sums to zero
                new TestCase(new int[]{1, 2, -3}, 3, 3),

                // Case 4: Single element is zero
                new TestCase(new int[]{0}, 1, 1),

                // Case 5: Multiple zeros scattered
                new TestCase(new int[]{0, 5, 0, -5, 0}, 5, 5),

                // Case 6: Large negative and positive values
                new TestCase(new int[]{1000, -1000, 500, -200, -300}, 5, 5),

                // Case 7: Zero at the very end
                new TestCase(new int[]{1, 2, 3, -6}, 4, 4),

                // Case 8: Smallest possible zero sum at the start
                new TestCase(new int[]{5, -5, 10, 20}, 4, 2),

                // Case 9: All zeros
                new TestCase(new int[]{0, 0, 0, 0}, 4, 4),

                // Case 10: Alternating values that don't immediately hit zero
                new TestCase(new int[]{1, -1, 1, -1, 1, -1}, 6, 6),

                // Case 11: Edge case - empty array
                new TestCase(new int[]{}, 0, 0)
        };

        // Run Tests
        for (int i = 0; i < testCases.length; i++) {
            int actual = solver.getMaxLength(testCases[i].arr, testCases[i].n);
            boolean passed = (actual == testCases[i].expected);

            if (passed) testsPassed++;

            System.out.println("Test Case " + (i + 1) + ": " + (passed ? "PASSED" : "FAILED"));
            System.out.println("   Input: " + Arrays.toString(testCases[i].arr));
            System.out.println("   Expected: " + testCases[i].expected);
            System.out.println("   Actual:   " + actual);
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Total Tests Passed: " + testsPassed + "/" + testCases.length);
    }

    static class TestCase {
        int[] arr;
        int n;
        int expected;

        TestCase(int[] arr, int n, int expected) {
            this.arr = arr;
            this.n = n;
            this.expected = expected;
        }
    }
}