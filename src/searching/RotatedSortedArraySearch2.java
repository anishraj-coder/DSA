package searching;

import java.util.Arrays;

public class RotatedSortedArraySearch2 {

    /**
     * Determines if the target exists in a rotated sorted array that may contain duplicates.
     *  @param arr   The rotated sorted integer array.
     * @param target The integer value to search for.
     * @return true if target is found, false otherwise.
     */
    public boolean search(int[] arr, int target) {
        int n=arr.length;
        if(n==1)return arr[0] == target;

        int low=0,hi=n-1;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]==target)return true;
            if(arr[mid]==arr[low]&&arr[mid]==arr[hi]){
                low++;
                hi--;
                continue;
            }else if(arr[low]<=arr[mid]){
                if(target>=arr[low]&&target<=arr[mid])hi=mid-1;
                else low=mid+1;
            }else {
                if(target>=arr[mid]&&target<=arr[hi])low=mid+1;
                else hi=mid-1;
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        RotatedSortedArraySearch2 engine = new RotatedSortedArraySearch2();

        Object[][] testCases = {
                // {nums, target, expected_result}
                {new int[]{2, 5, 6, 0, 0, 1, 2}, 0, true},           // Standard rotated with duplicates
                {new int[]{2, 5, 6, 0, 0, 1, 2}, 3, false},          // Target not present
                {new int[]{1, 0, 1, 1, 1}, 0, true},                 // Critical case: low == mid == high
                {new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 13, 1, 1}, 13, true}, // Deeply nested target with duplicates
                {new int[]{1}, 0, false},                            // Single element (not found)
                {new int[]{1}, 1, true},                             // Single element (found)
                {new int[]{1, 3}, 3, true},                          // Two elements
                {new int[]{3, 1}, 1, true},                          // Two elements rotated
                {new int[]{1, 1, 1, 2, 1}, 2, true},                 // Target in right half with duplicates
                {new int[]{1, 2, 1, 1, 1}, 2, true},                 // Target in left half with duplicates
                {new int[]{-10, -10, -5, -10}, -5, true},            // Negative values and duplicates
                {new int[]{5, 5, 5, 5, 5, 5}, 1, false}              // All same elements, target missing
        };

        int passed = 0;
        System.out.println(String.format("%-45s | %-10s | %-10s | %-6s", "Input Array & Target", "Expected", "Actual", "Result"));
        System.out.println("---------------------------------------------------------------------------------------------");

        for (Object[] test : testCases) {
            int[] nums = (int[]) test[0];
            int target = (int) test[1];
            boolean expected = (boolean) test[2];

            boolean actual = engine.search(nums, target);
            boolean isMatch = actual == expected;

            if (isMatch) passed++;

            String inputStr = Arrays.toString(nums) + " T:" + target;
            System.out.println(String.format("%-45s | %-10b | %-10b | %-6s",
                    inputStr, expected, actual, isMatch ? "PASS" : "FAIL"));
        }

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Final Score: " + passed + "/" + testCases.length);
    }
}