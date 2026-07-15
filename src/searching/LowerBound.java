package searching;

import java.util.Arrays;

public class LowerBound {

    /**
     * Finds the lower bound index of x in a sorted array.
     * Returns the first index where nums[index] >= x.
     * If no such element exists, returns nums.length.
     *
     * @param arr sorted array of integers
     * @param x target value
     * @return lower bound index
     */
    public static int findLowerBound(int[] arr, int x) {
        int low=0,hi=arr.length;
        while(low<hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]>=x)hi=mid;
            else low=mid+1;
        }

        return hi; // Placeholder return
    }

    public static void main(String[] args) {
        // Test Case Structure: {input_array, target_x, expected_index}
        Object[][] testCases = {
                // Standard Cases
                {new int[]{1, 2, 2, 3}, 2, 1},
                {new int[]{3, 5, 8, 15, 19}, 9, 3},
                {new int[]{1, 3, 5, 7, 9}, 4, 2}, // 5 is at index 2

                // Edge: x smaller than all elements
                {new int[]{5, 10, 15}, 1, 0},
                {new int[]{-10, -5, 0}, -20, 0},

                // Edge: x larger than all elements
                {new int[]{1, 2, 3}, 5, 3},
                {new int[]{10, 20, 30}, 100, 3},

                // Duplicates & Exact Matches
                {new int[]{4, 4, 4, 4, 4}, 4, 0},
                {new int[]{1, 1, 2, 2, 3, 3}, 2, 2},
                {new int[]{1, 1, 1, 2, 2, 2}, 1, 0},

                // Negative Numbers & Mixed
                {new int[]{-8, -5, -3, 0, 4}, -4, 2}, // 0 is first >= -4
                {new int[]{-5, -2, -2, 3, 3}, -1, 3}, // 3 is first >= -1

                // Minimal / Empty Arrays
                {new int[]{}, 10, 0},
                {new int[]{7}, 7, 0},
                {new int[]{7}, 8, 1}
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int x = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = findLowerBound(nums.clone(), x);
            boolean isPassed = (actual == expected);

            if (isPassed) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED");
            } else {
                failed++;
                System.out.println("Test Case " + (i + 1) + ": FAILED");
            }

            System.out.println("  Input Array: " + Arrays.toString(nums));
            System.out.println("  Target (x) : " + x);
            System.out.println("  Expected   : " + expected);
            System.out.println("  Actual     : " + actual);
            System.out.println("-----------------------------------");
        }

        System.out.println("Total Passed: " + passed + "/" + testCases.length);
        System.out.println("Total Failed: " + failed + "/" + testCases.length);
    }
}
