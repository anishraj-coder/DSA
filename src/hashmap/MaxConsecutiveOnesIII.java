package hashmap;

import java.util.Arrays;

public class MaxConsecutiveOnesIII {

    /**
     * Problem: Max Consecutive Ones III
     * Given a binary array nums and an integer k, return the maximum number
     * of consecutive 1's in the array if you can flip at most k 0's.
     */
    public int longestOnes(int[] arr, int k) {
        int i=0,j=0,count=0,max=0,n=arr.length;

        while(j<n){
            if(arr[j]==0)count++;
            while(count>k){
                if(arr[i]==0)count--;
                i++;
            }
            max=Math.max(max,j-i+1);
            j++;
        }

        return max;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnesIII solver = new MaxConsecutiveOnesIII();

        Object[][] testCases = {
                // {nums, k, expected}
                {new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2, 6},
                {new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3, 10},
                // Edge Case: k is 0 (No flips allowed)
                {new int[]{1, 0, 1, 1, 0, 1}, 0, 2},
                // Edge Case: All zeros, k smaller than length
                {new int[]{0, 0, 0, 0}, 2, 2},
                // Edge Case: k is greater than or equal to array length
                {new int[]{0, 0, 1, 0}, 5, 4},
                // Edge Case: Array with only ones
                {new int[]{1, 1, 1, 1}, 0, 4},
                // Edge Case: Empty-like / Single element 0, k=0
                {new int[]{0}, 0, 0},
                // Edge Case: Single element 0, k=1
                {new int[]{0}, 1, 1},
                // Hard Case: Alternating bits with k=1
                {new int[]{1, 0, 1, 0, 1, 0, 1, 0}, 1, 3},
                // Hard Case: Large gaps
                {new int[]{1, 1, 0, 0, 0, 0, 0, 1, 1}, 1, 3},
                // Hard Case: k allows flipping all interior zeros
                {new int[]{1, 0, 0, 1, 1, 0, 1}, 2, 5}
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = solver.longestOnes(nums, k);

            boolean isPassed = actual == expected;
            if (isPassed) passed++;

            System.out.printf("Test Case %d: %s | k: %d%n", i + 1, Arrays.toString(nums), k);
            System.out.printf("Expected: %d | Actual: %d%n", expected, actual);
            System.out.println("Result: " + (isPassed ? "✅ PASSED" : "❌ FAILED"));
            System.out.println("--------------------------------------------------");
        }

        System.out.printf("Final Score: %d/%d cases passed.%n", passed, testCases.length);
    }
}