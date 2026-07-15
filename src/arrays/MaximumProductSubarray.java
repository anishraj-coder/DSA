package arrays;

import java.util.Arrays;

public class MaximumProductSubarray {

    /**
     * Given an integer array nums, find a subarray that has the largest product,
     * and return the product.
     */
    public int maxProduct(int[] arr) {
        int n=arr.length;
        int ans=arr[0],min=arr[0],max=arr[0];
        for(int i=1;i<n;i++){
            int v1=arr[i],v2=min*arr[i],v3=max*arr[i];
            max=Math.max(v1,Math.max(v2,v3));
            min=Math.min(v1,Math.min(v2,v3));
            ans=Math.max(max,ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        MaximumProductSubarray solver = new MaximumProductSubarray();

        Object[][] testCases = {
                {new int[]{2, 3, -2, 4}, 6, "Standard case with one negative"},
                {new int[]{-2, 0, -1}, 0, "Array with zero breaking subarrays"},
                {new int[]{-2, 3, -4}, 24, "Two negatives making a large positive"},
                {new int[]{-2}, -2, "Single negative element"},
                {new int[]{0, 2}, 2, "Zero followed by positive"},
                {new int[]{2, -5, -2, -4, 3}, 24, "Odd number of negatives"},
                {new int[]{10, 10, 10}, 1000, "All positives"},
                {new int[]{-1, -2, -3, -4}, 24, "All negatives (even count)"},
                {new int[]{-1, -2, -3}, 6, "All negatives (odd count)"},
                {new int[]{0, 0, 0}, 0, "All zeros"},
                {new int[]{2, -1, 1, 1}, 2, "Negative in the middle of ones"}
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int expected = (int) testCases[i][1];
            String description = (String) testCases[i][2];

            int actual = solver.maxProduct(nums);

            System.out.println("Test Case " + (i + 1) + ": " + description);
            System.out.println("Input: " + Arrays.toString(nums));
            System.out.println("Expected: " + expected + ", Actual: " + actual);

            if (actual == expected) {
                System.out.println("Result: PASSED");
                passed++;
            } else {
                System.out.println("Result: FAILED");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Score: " + passed + "/" + testCases.length);
    }
}