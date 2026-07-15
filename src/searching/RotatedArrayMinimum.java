package searching;

import java.util.Arrays;

public class RotatedArrayMinimum {

    /**
     * Finds the minimum element in a rotated sorted array of unique integers.
     * Efficiency requirement: O(log n)
     * @param arr The rotated sorted integer array.
     * @return The minimum element in the array.
     */
    public int findMin(int[] arr) {
        int n=arr.length;
        if(n==1)return arr[0];

        int low=0,hi=n-1,min=Integer.MAX_VALUE;

        while(low<=hi){
            int mid=(hi-low)/2+low;

            if(arr[low]<=arr[mid]){
                min=Math.min(min,arr[low]);
                low=mid+1;
            }else {
                min=Math.min(min,arr[mid]);
                hi=mid-1;
            }
        }


        return min;
    }

    public static void main(String[] args) {
        RotatedArrayMinimum engine = new RotatedArrayMinimum();

        Object[][] testCases = {
                // {nums, expected_result}
                {new int[]{3, 4, 5, 1, 2}, 1},               // Standard rotation
                {new int[]{4, 5, 6, 7, 0, 1, 2}, 0},         // Inflection point in second half
                {new int[]{11, 13, 15, 17}, 11},             // Rotated n times (fully sorted)
                {new int[]{2, 1}, 1},                        // Minimum at the end (size 2)
                {new int[]{1, 2}, 1},                        // Sorted (size 2)
                {new int[]{1}, 1},                           // Single element
                {new int[]{5, 1, 2, 3, 4}, 1},               // Minimum at index 1
                {new int[]{2, 3, 4, 5, 1}, 1},               // Minimum at the very end
                {new int[]{10, 20, 30, -10, -5}, -10},       // Negative values included
                {new int[]{0, 1, 2, 3, 4, 5, 6, -1}, -1},    // Inflection at the very last index
                {new int[]{5000, -5000, -4999, -4998}, -5000}, // Boundary constraint values
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, 0} // Inflection at the very last index (larger)
        };

        int passed = 0;
        System.out.println(String.format("%-45s | %-10s | %-10s | %-6s", "Input Array", "Expected", "Actual", "Result"));
        System.out.println("---------------------------------------------------------------------------------------------");

        for (Object[] test : testCases) {
            int[] nums = (int[]) test[0];
            int expected = (int) test[1];

            int actual = engine.findMin(nums);
            boolean isMatch = actual == expected;

            if (isMatch) passed++;

            String inputStr = Arrays.toString(nums);
            // Truncate long arrays for display
            if (inputStr.length() > 42) {
                inputStr = inputStr.substring(0, 40) + "...]";
            }

            System.out.println(String.format("%-45s | %-10d | %-10d | %-6s",
                    inputStr, expected, actual, isMatch ? "PASS" : "FAIL"));
        }

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Final Score: " + passed + "/" + testCases.length);
    }
}