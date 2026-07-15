package searching;

import java.util.Arrays;

public class SingleElementLocator {

    /**
     * Finds the unique element in a sorted array where every other element appears exactly twice.
     * Duplicate elements are guaranteed to be adjacent.
     *
     * @param arr the sorted input array of integers
     * @return the element that appears only once
     */
    public static int findUniqueElement(int[] arr) {
        int n=arr.length;
        if(n==1)return arr[0];
        if(arr[0]!=arr[1])return arr[0];
        if(arr[n-2]!=arr[n-1])return arr[n-1];
        int hi=n-2,low=2;
        while(low<=hi){
            int mid=(hi-low)/2 +low;
            if(arr[mid]!=arr[mid-1]&&arr[mid]!=arr[mid+1])return arr[mid];
            if(arr[mid-1]==arr[mid])mid--;
            if(mid%2==0)low=mid+2;
            else hi=mid-1;
        }
        return -1; // Placeholder return
    }

    public static void main(String[] args) {
        // Test Case Structure: {input_array, expected_output}
        // All expected values manually verified for correctness
        Object[][] testCases = {
                // Basic Cases
                {new int[]{1, 1, 2, 3, 3, 4, 4, 5, 5}, 2},
                {new int[]{1, 2, 2, 3, 3, 4, 4}, 1},           // Unique at start
                {new int[]{1, 1, 2, 2, 3, 3, 4}, 4},           // Unique at end

                // Minimal / Edge Cases
                {new int[]{5}, 5},                              // Single element
                {new int[]{1, 1, 2}, 2},                        // Three elements, unique at end
                {new int[]{1, 2, 2}, 1},                        // Three elements, unique at start

                // Negative Numbers
                {new int[]{-5, -5, -3, -2, -2}, -3},           // Unique negative in middle
                {new int[]{-3, -2, -2, -1, -1}, -3},           // Unique negative at start

                // Zero and Mixed Signs
                {new int[]{0, 0, 1, 1, 2}, 2},                 // Zero paired, unique positive
                {new int[]{-2, -2, -1, -1, 0, 1, 1}, 0},       // Zero is unique

                // Larger Values
                {new int[]{100, 100, 200, 200, 300}, 300},     // Large numbers, unique at end
                {new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 7, 7, 8, 8}, 6}, // Unique in middle of larger array

                // Edge: Unique surrounded by same-value pairs
                {new int[]{10, 10, 20, 30, 30}, 20},           // Unique between two different pairs
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int expected = (int) testCases[i][1];

            // Use a copy to preserve original input for display
            int[] inputCopy = Arrays.copyOf(input, input.length);

            int actual = findUniqueElement(inputCopy);

            boolean isPassed = (actual == expected);
            if (isPassed) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED");
            } else {
                failed++;
                System.out.println("Test Case " + (i + 1) + ": FAILED");
            }

            System.out.println("  Input   : " + Arrays.toString(input));
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual  : " + actual);
            System.out.println("-----------------------------------");
        }

        System.out.println("Total Passed: " + passed + "/" + testCases.length);
        System.out.println("Total Failed: " + failed + "/" + testCases.length);
    }
}