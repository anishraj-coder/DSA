package arrays;

import java.util.Arrays;

public class BoundedMaximumSubarrays {

    /**
     * Returns the number of contiguous non-empty subarrays such that the
     * maximum element is in the range [left, right].
     *  @param arr  The integer array.
     * @param left  The lower bound.
     * @param right The upper bound.
     * @return Total count of valid subarrays.
     */
    public int numSubarrayBoundedMax(int[] arr, int left, int right) {
        int n=arr.length;
        int prevValid=0,lgei=0,ans=0;
        for(int i=0;i<n;i++){
            if(arr[i]>right){
                prevValid=0;
                lgei=i+1;
            }else if(arr[i]>=left&&arr[i]<=right){
                ans+=(i-lgei+1);
                prevValid=(i-lgei+1);
            }else ans+=prevValid;
        }
        return ans;
    }

    public static void main(String[] args) {
        BoundedMaximumSubarrays engine = new BoundedMaximumSubarrays();

        Object[][] testCases = {
                // {nums, left, right, expected_result}
                {new int[]{2, 1, 4, 3}, 2, 3, 3},                // Example 1
                {new int[]{2, 9, 2, 5, 6}, 2, 8, 7},             // Example 2
                {new int[]{1, 2, 3, 4}, 5, 10, 0},               // All elements below 'left'
                {new int[]{10, 11, 12}, 1, 5, 0},                // All elements above 'right'
                {new int[]{2, 2, 2, 2}, 2, 2, 10},               // All elements exactly equal to range
                {new int[]{1, 5, 1}, 2, 4, 0},                   // Sandwich: small, big, small (none in range)
                {new int[]{3}, 2, 4, 1},                         // Single element within range
                {new int[]{1, 3, 5, 2, 4, 1}, 2, 4, 9},          // Mixed values with multiple partitions
                {new int[]{7, 3, 6, 7, 1}, 1, 4, 3},             // Only the '3' and '1' segments matter
                {new int[]{0, 0, 0}, 0, 0, 6}                    // Zeros with zero bounds
        };

        int passed = 0;
        System.out.println(String.format("%-40s | %-8s | %-8s | %-8s | %-6s",
                "Input Array", "L-R", "Exp", "Act", "Result"));
        System.out.println("-----------------------------------------------------------------------------------------");

        for (Object[] test : testCases) {
            int[] nums = (int[]) test[0];
            int left = (int) test[1];
            int right = (int) test[2];
            int expected = (int) test[3];

            int actual = engine.numSubarrayBoundedMax(nums, left, right);
            boolean isMatch = actual == expected;

            if (isMatch) passed++;

            String inputStr = Arrays.toString(nums);
            if (inputStr.length() > 37) {
                inputStr = inputStr.substring(0, 35) + "...]";
            }

            System.out.println(String.format("%-40s | %d-%-6d | %-8d | %-8d | %-6s",
                    inputStr, left, right, expected, actual, isMatch ? "PASS" : "FAIL"));
        }

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Final Score: " + passed + "/" + testCases.length);
    }
}