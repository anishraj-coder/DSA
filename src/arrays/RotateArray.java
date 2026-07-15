package arrays;

import java.util.Arrays;

public class RotateArray {

    /**
     * Rotates the given array to the right by k steps in-place.
     *
     * @param arr the array to rotate
     * @param k the number of steps to rotate (non-negative)
     */
    public static void rotateArray(int[] arr, int k) {
        int n=arr.length;
        k=k%n;
        if(k==0)return;

        reverse(arr,0,n-1);
        reverse(arr,0,k-1);
        reverse(arr,k,n-1);
    }

    public static void reverse(int[]arr,int s,int e){
        while(s<e){
            int t=arr[s];
            arr[s]=arr[e];
            arr[e]=t;
            s++;
            e--;
        }
    }

    public static void main(String[] args) {
        // Test Case Structure: {input_array, k_value, expected_output_array}
        Object[][] testCases = {
                // Basic Examples
                {new int[]{1, 2, 3, 4, 5, 6, 7}, 3, new int[]{5, 6, 7, 1, 2, 3, 4}},
                {new int[]{-1, -100, 3, 99}, 2, new int[]{3, 99, -1, -100}},

                // Edge Cases: k = 0 or k equals array length
                {new int[]{1, 2, 3}, 0, new int[]{1, 2, 3}},
                {new int[]{1, 2, 3}, 3, new int[]{1, 2, 3}},

                // Edge Cases: Single element array
                {new int[]{42}, 0, new int[]{42}},
                {new int[]{42}, 100, new int[]{42}},

                // Edge Cases: k greater than array length (tests k % n handling)
                {new int[]{1, 2, 3}, 5, new int[]{2, 3, 1}},
                {new int[]{1, 2, 3, 4, 5}, 7, new int[]{4, 5, 1, 2, 3}},

                // Edge Cases: All elements identical
                {new int[]{5, 5, 5, 5}, 2, new int[]{5, 5, 5, 5}},

                // Edge Cases: Negative numbers
                {new int[]{-5, -4, -3, -2, -1}, 1, new int[]{-1, -5, -4, -3, -2}},

                // Edge Cases: Mix of zeros and values
                {new int[]{0, 0, 0, 1, 2}, 3, new int[]{0, 1, 2, 0, 0}},

                // Edge Cases: Two element array
                {new int[]{1, 2}, 1, new int[]{2, 1}}
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int[] expected = (int[]) testCases[i][2];

            // Create a copy to pass to the function (since it modifies in-place)
            int[] actual = Arrays.copyOf(input, input.length);
            rotateArray(actual, k);

            boolean isPassed = Arrays.equals(actual, expected);
            if (isPassed) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED");
            } else {
                failed++;
                System.out.println("Test Case " + (i + 1) + ": FAILED");
            }

            System.out.println("  Input   : " + Arrays.toString(input) + ", k=" + k);
            System.out.println("  Expected: " + Arrays.toString(expected));
            System.out.println("  Actual  : " + Arrays.toString(actual));
            System.out.println("-----------------------------------");
        }

        System.out.println("\n=== SUMMARY ===");
        System.out.println("Total Tests : " + testCases.length);
        System.out.println("Passed      : " + passed);
        System.out.println("Failed      : " + failed);
    }
}
