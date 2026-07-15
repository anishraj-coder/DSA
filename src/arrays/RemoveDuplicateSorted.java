package arrays;

import java.util.Arrays;

public class RemoveDuplicateSorted {

    /**
     * Removes duplicates from a sorted array in-place and returns the count of unique elements.
     * The first k elements of the array will contain the unique values in non-decreasing order.
     *
     * @param arr sorted integer array (non-decreasing order)
     * @return k, the number of unique elements
     */
    public static int removeDuplicates(int[] arr) {
        if(arr==null)return -1;
        int n=arr.length;
        if(n==2&&arr[0]==arr[1])return 1;
        else if(n==2) return 2;
        int j=0;
        for(int i=1;i<n;i++){
            if(arr[j]!=arr[i]){
                j++;
                arr[j]=arr[i];
            }
        }

        return j+1;
    }

    public static void main(String[] args) {
        // Test Case Structure: {input_array, expected_k, expected_first_k_elements}
        // All expected values manually verified against problem logic
        Object[][] testCases = {
                // Provided Examples
                {new int[]{1,1,2}, 2, new int[]{1,2}},
                {new int[]{0,0,1,1,1,2,2,3,3,4}, 5, new int[]{0,1,2,3,4}},

                // Edge Cases: Single element, all same, all unique
                {new int[]{1}, 1, new int[]{1}},
                {new int[]{1,1,1,1}, 1, new int[]{1}},
                {new int[]{1,2,3,4,5}, 5, new int[]{1,2,3,4,5}},

                // Edge Cases: Negative numbers, mixed signs
                {new int[]{-5,-3,-3,0,1,1,2}, 5, new int[]{-5,-3,0,1,2}},
                {new int[]{-100,-100,-99,0,100,100}, 4, new int[]{-100,-99,0,100}},

                // Edge Cases: Two elements, boundary duplicates
                {new int[]{0,0}, 1, new int[]{0}},
                {new int[]{-1,0}, 2, new int[]{-1,0}},

                // Hard / Complex Patterns: Multiple duplicate groups
                {new int[]{1,1,2,2,3,3}, 3, new int[]{1,2,3}},
                {new int[]{5,5,5,6,7,7,8,9,9,9,9}, 5, new int[]{5,6,7,8,9}},
                {new int[]{-1,-1,-1,0,0,1}, 3, new int[]{-1,0,1}}
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int expectedK = (int) testCases[i][1];
            int[] expectedFirstK = (int[]) testCases[i][2];

            // Defensive copy: function modifies array in-place
            int[] numsCopy = Arrays.copyOf(input, input.length);

            int actualK = removeDuplicates(numsCopy);
            int[] actualFirstK = Arrays.copyOfRange(numsCopy, 0, Math.min(actualK, numsCopy.length));

            boolean kMatches = (actualK == expectedK);
            boolean arrayMatches = Arrays.equals(actualFirstK, expectedFirstK);
            boolean isPassed = kMatches && arrayMatches;

            if (isPassed) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED ✓");
            } else {
                failed++;
                System.out.println("Test Case " + (i + 1) + ": FAILED ✗");
            }

            System.out.println("  Input              : " + Arrays.toString(input));
            System.out.println("  Expected k         : " + expectedK);
            System.out.println("  Expected first k   : " + Arrays.toString(expectedFirstK));
            System.out.println("  Actual k           : " + actualK);
            System.out.println("  Actual first k     : " + Arrays.toString(actualFirstK));
            if (!kMatches) System.out.println("  ❌ k mismatch");
            if (!arrayMatches) System.out.println("  ❌ array content mismatch");
            System.out.println("-----------------------------------");
        }

        System.out.println("\n=== SUMMARY ===");
        System.out.println("Total Passed: " + passed + "/" + testCases.length);
        System.out.println("Total Failed: " + failed + "/" + testCases.length);
    }
}