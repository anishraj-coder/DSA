package arrays;

import java.util.Arrays;

public class MaximumSubarraySum {

    /**
     * Finds the maximum sum of a contiguous subarray within the given integer array.
     *
     * @param arr the input integer array
     * @return the largest subarray sum
     */
    public static int findMaximumSubarraySum(int[] arr) {
        int n=arr.length, sum=0,max=Integer.MIN_VALUE;
        if(n==1)return arr[0];
        for(int i=0;i<n;i++){
            sum+=arr[i];
            max=Math.max(sum,max);
            if(sum<0)sum=0;
        }

        return max; // Placeholder return
    }

    public static void main(String[] args) {
        // Test Case Structure: {input_array, expected_output}
        // All expected values manually verified against Kadane's algorithm logic
        int[][] inputs = {
                {-2, 1, -3, 4, -1, 2, 1, -5, 4},   // Standard mixed
                {1},                                 // Single positive
                {5, 4, -1, 7, 8},                    // All positive except one
                {-5, -2, -9, -1, -4},                // All negative (edge: max is single largest)
                {0, 0, 0, 0},                        // All zeros
                {-3},                                // Single negative
                {1, 2, 3, -10, 100},                 // Large positive after heavy drop
                {-2, -3, 4, -1, -2, 1, 5, -3},       // Peak in middle with negatives on both sides
                {-1, -2, -3, 4, 5, 6},               // Transition from negative to positive run
                {10, -3, -4, 7, 6, -5, 9, -8, 2},    // Multiple local maxima
                {-10000, 10000, -10000},             // Constraint boundaries (min/max values)
                {2, -5, 3, -1, 2, -4, 3}             // Alternating, requires skipping negative dips
        };

        int[] expectedOutputs = {
                6, 1, 23, -1, 0, -3, 100, 7, 15, 20, 10000, 4
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < inputs.length; i++) {
            int[] inputCopy = Arrays.copyOf(inputs[i], inputs[i].length);
            int expected = expectedOutputs[i];

            int actual = findMaximumSubarraySum(inputCopy);

            boolean isPassed = (actual == expected);
            if (isPassed) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED");
            } else {
                failed++;
                System.out.println("Test Case " + (i + 1) + ": FAILED");
            }

            System.out.println("  Input   : " + Arrays.toString(inputs[i]));
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual  : " + actual);
            System.out.println("-----------------------------------");
        }

        System.out.println("Total Passed: " + passed + "/" + inputs.length);
        System.out.println("Total Failed: " + failed + "/" + inputs.length);
    }
}
