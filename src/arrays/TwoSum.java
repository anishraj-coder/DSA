package arrays;

import java.util.*;

public class TwoSum {

    /**
     * Finds the indices of two numbers that add up to the target.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * * @param arr   The input array of integers.
     * @param target The target sum.
     * @return       An array containing the two indices.
     */
    public int[] getTwoSum(int[] arr, int target) {
        int n=arr.length;
        if(n==2&& arr[0]+arr[1]==target)return new int[]{0,1};
        Map<Integer,Integer>map=new HashMap<>();
        for( int i=0;i<n;i++){
            int rem=target-arr[i];
            if(map.containsKey(rem)){
                return new int[]{i,map.get(rem)};
            }
            map.put(arr[i],i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        TwoSum instance = new TwoSum();

        // Test Cases: {Input Array, Target, Expected Indices (order doesn't matter)}
        // Note: Expected values are represented as a sorted array for easier comparison.
        Object[][] testCases = {
                {new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}},    // Case 1: Standard match
                {new int[]{3, 2, 4}, 6, new int[]{1, 2}},         // Case 2: Match not at start
                {new int[]{3, 3}, 6, new int[]{0, 1}},            // Case 3: Duplicate numbers
                {new int[]{1, 5, 8, 3}, 11, new int[]{2, 3}},     // Case 4: Larger numbers
                {new int[]{-1, -2, -3, -4, -5}, -8, new int[]{2, 4}}, // Case 5: Negative numbers
                {new int[]{10, -5, 3, 8}, 5, new int[]{0, 1}},    // Case 6: Mixed positive/negative
                {new int[]{0, 4, 3, 0}, 0, new int[]{0, 3}},      // Case 7: Target is zero
                {new int[]{-10, 20, 35, 80}, 70, new int[]{-1}}, // Case 8: No match (though constraints say one exists)
                {new int[]{1000000, 500, 1000000}, 2000000, new int[]{0, 2}}, // Case 9: Large values
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 19, new int[]{8, 9}} // Case 10: End of long array
        };

        int passed = 0;

        System.out.println(String.format("%-10s | %-30s | %-8s | %-12s | %-12s | %-8s",
                "Test Case", "Input Array", "Target", "Expected", "Actual", "Result"));
        System.out.println("-".repeat(100));

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int target = (int) testCases[i][1];
            int[] expected = (int[]) testCases[i][2];

            int[] actual = instance.getTwoSum(nums, target);

            // Sort both for comparison since order doesn't matter
            Arrays.sort(actual);
            Arrays.sort(expected);

            boolean isMatch = Arrays.equals(actual, expected);
            if (isMatch) passed++;

            String arrayStr = Arrays.toString(nums);
            if (arrayStr.length() > 28) arrayStr = arrayStr.substring(0, 25) + "...";

            System.out.println(String.format("%-10d | %-30s | %-8d | %-12s | %-12s | %-8s",
                    (i + 1), arrayStr, target, Arrays.toString(expected), Arrays.toString(actual), isMatch ? "PASSED" : "FAILED"));
        }

        System.out.println("-".repeat(100));
        System.out.println("Final Score: " + passed + "/" + testCases.length);
    }
}