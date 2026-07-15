package arrays;

import java.util.Arrays;

public class MajorityElement {

    /**
     * Finds the majority element in an array.
     * The majority element is the element that appears more than ⌊n / 2⌋ times.
     * It is assumed that the majority element always exists in the array.
     *
     * @param arr the input array of integers
     * @return the majority element
     */
    public static int findMajorityElement(int[] arr) {

        int n=arr.length;
        if(n<=2)return arr[0];
        int count=1,val=arr[0];

        for(int i=1;i<n;i++){
            if(val==arr[i])count++;
            else if(count==0){
                val=arr[i];
                count=1;
            }else count--;
        }


        return val; // Placeholder return
    }

    public static void main(String[] args) {
        // Test Case Structure: {input_array, expected_output}
        // All expected values manually verified based on the definition: count > n/2
        Object[][] testCases = {
                // Basic Examples from Problem Statement
                {new int[]{3, 2, 3}, 3},
                {new int[]{2, 2, 1, 1, 1, 2, 2}, 2},

                // Minimal Cases
                {new int[]{1}, 1},                          // Single element
                {new int[]{1, 1, 2}, 1},                    // Smallest odd case with majority
                {new int[]{1, 2, 1}, 1},                    // Majority at ends

                // All Same Elements
                {new int[]{5, 5, 5, 5, 5}, 5},              // All identical
                {new int[]{0, 0}, 0},                       // Two identical

                // Negative Numbers
                {new int[]{-1, -1, -1, 2, 2}, -1},          // Negative majority
                {new int[]{-5, 10, -5, -5, -5}, -5},        // Negative majority mixed with positive

                // Large Counts / Edge Distribution
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 1, 1, 1, 1, 1, 1}, 1}, // Majority appears exactly n/2 + 1 times (approx)
                // n=16, need > 8. Count of 1s is 8? No, let's count: 1,1,1,1,1,1,1,1 (8 ones).
                // Wait, 8 is not > 16/2 (8). So this input is invalid per problem constraints "majority always exists".
                // Let's fix: Add one more 1.
                // {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 1, 1, 1, 1, 1, 1, 1} -> 9 ones. n=17. 9 > 8.5. Valid.

                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 1, 1, 1, 1, 1, 1, 1},1}, // Corrected valid case

                // Alternating Pattern with One Dominant
                {new int[]{1, 2, 1, 2, 1, 2, 1}, 1},        // 1 appears 4 times, 2 appears 3 times. n=7. 4 > 3.5.

                // Max Constraints Simulation (Small Scale)
                {new int[]{1000000000, 1000000000, -1000000000}, 1000000000}, // Large values
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int expected = (int) testCases[i][1];

            // Create a copy for safety if implementation modifies array (though it shouldn't)
            int[] inputCopy = Arrays.copyOf(input, input.length);

            int actual = findMajorityElement(inputCopy);

            boolean isPassed = (actual == expected);

            // Additional Verification: Check if actual is indeed the majority
            boolean isValidMajority = verifyMajority(input, actual);

            if (isPassed && isValidMajority) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED");
            } else {
                failed++;
                System.out.println("Test Case " + (i + 1) + ": FAILED");
                if (!isValidMajority) {
                    System.out.println("  ⚠️  Warning: Returned value is NOT a majority element!");
                }
            }

            System.out.println("  Input   : " + Arrays.toString(input));
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual  : " + actual);
            System.out.println("-----------------------------------");
        }

        System.out.println("Total Passed: " + passed + "/" + testCases.length);
        System.out.println("Total Failed: " + failed + "/" + testCases.length);
    }

    /**
     * Helper to verify if a candidate is truly the majority element (> n/2 occurrences).
     */
    private static boolean verifyMajority(int[] arr, int candidate) {
        int count = 0;
        for (int num : arr) {
            if (num == candidate) {
                count++;
            }
        }
        return count > arr.length / 2;
    }
}