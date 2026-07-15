package searching;


import java.util.Arrays;

public class PeakElement {

    /**
     * Finds a peak element in an unsorted array with distinct elements.
     * A peak element is strictly greater than its immediate neighbors.
     * For boundary elements, only one neighbor is considered.
     *
     * @param arr the input array of distinct integers
     * @return any peak element value from the array
     */
    public static int findPeakElement(int[] arr) {
        int n=arr.length;
        if(n==1)return arr[0];
        if(n==2)return Math.max(arr[0],arr[1]);
        if(arr[0]>arr[1])return arr[0];
        if(arr[n-1]>arr[n-1])return arr[n-1];
        int low=1,hi=n-2;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]>arr[mid-1]&&arr[mid]>arr[mid+1])return arr[mid];
            else if(arr[mid]>arr[mid-1]&&arr[mid]<arr[mid+1])low=mid+1;
            else if(arr[mid]<arr[mid-1]&&arr[mid]>arr[mid+1])hi=mid-1;
            else hi=mid-1;
        }
        return -1; // Placeholder return
    }

    public static void main(String[] args) {
        // Test Case Structure: {input_array, expected_output}
        // All expected values manually verified. For multiple valid peaks, one valid answer is chosen.
        Object[][] testCases = {
                // Basic Cases
                {new int[]{1, 3, 2}, 3},
                {new int[]{1, 2, 3, 1}, 3},

                // Edge: Peak at boundaries
                {new int[]{5, 4, 3, 2, 1}, 5},           // Peak at start (descending)
                {new int[]{1, 2, 3, 4, 5}, 5},           // Peak at end (ascending)
                {new int[]{2, 1, 3}, 2},                 // Two peaks: 2 or 3 (choosing 2)

                // Minimal Arrays
                {new int[]{7}, 7},                        // Single element is always a peak
                {new int[]{3, 5}, 5},                     // Two elements: larger is peak
                {new int[]{10, 2}, 10},                   // Two elements: first is peak

                // Negative Numbers & Mixed Signs
                {new int[]{-5, -2, -8, -1}, -1},         // Peak among negatives (-2 or -1 valid)
                {new int[]{-3, 5, -1, 2, -4}, 5},        // Mixed signs, peak at 5 (or 2)

                // Multiple Peaks - Return Any Valid One
                {new int[]{1, 5, 3, 6, 4}, 5},           // Peaks: 5 or 6 (choosing 5)
                {new int[]{10, 1, 9, 2, 8, 3}, 10},      // Peaks: 10, 9, or 8 (choosing 10)

                // Larger / Complex Cases
                {new int[]{1, 3, 5, 7, 9, 8, 6, 4, 2}, 9}, // Clear peak in middle
                {new int[]{1, 2, 1, 3, 5, 6, 4}, 6},     // Classic: peaks at 2 or 6 (choosing 6)
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int expected = (int) testCases[i][1];

            // Use a copy to preserve original input for display
            int[] inputCopy = Arrays.copyOf(input, input.length);

            int actual = findPeakElement(inputCopy);

            // Validation: actual must be in array AND satisfy peak condition
            boolean isValidPeak = validatePeak(input, actual);
            boolean isPassed = isValidPeak && (actual == expected);

            if (isPassed) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED");
            } else {
                failed++;
                System.out.println("Test Case " + (i + 1) + ": FAILED");
                if (!isValidPeak) {
                    System.out.println("  ⚠️  Warning: Returned value is not a valid peak!");
                }
            }

            System.out.println("  Input   : " + Arrays.toString(input));
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual  : " + actual);
            System.out.println("  Valid Peak?: " + isValidPeak);
            System.out.println("-----------------------------------");
        }

        System.out.println("Total Passed: " + passed + "/" + testCases.length);
        System.out.println("Total Failed: " + failed + "/" + testCases.length);
    }

    /**
     * Helper to verify if a returned value is a valid peak in the original array.
     * This ensures correctness even when multiple valid peaks exist.
     */
    private static boolean validatePeak(int[] arr, int candidate) {
        if (arr == null || arr.length == 0) return false;

        // Find index of candidate (distinct elements guarantee uniqueness)
        int idx = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == candidate) {
                idx = i;
                break;
            }
        }
        if (idx == -1) return false; // Candidate not in array

        // Check left neighbor (if exists)
        if (idx > 0 && arr[idx] <= arr[idx - 1]) return false;
        // Check right neighbor (if exists)
        if (idx < arr.length - 1 && arr[idx] <= arr[idx + 1]) return false;

        return true;
    }
}
