package searching;

import java.util.Arrays;

public class UpperBound {

    /**
     * Finds the smallest index i such that nums[i] > x.
     * If no such index exists, return the length of the array.
     */
    public int findUpperBound(int[] arr, int x) {
        int low=0,hi=arr.length;
        while(low<hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]>x)hi=mid;
            else low=mid+1;
        }
        return low;
    }

    public static void main(String[] args) {
        UpperBound solver = new UpperBound();

        Object[][] testCases = {
                {new int[]{1, 2, 2, 3}, 2, 3, "Target exists multiple times, return index after last occurrence"},
                {new int[]{3, 5, 8, 15, 19}, 9, 3, "Target missing, return next greater element (same as lower bound here)"},
                {new int[]{1, 2, 4, 5}, 3, 2, "Target missing in middle"},
                {new int[]{1, 3, 5, 7}, 0, 0, "Target smaller than all elements"},
                {new int[]{1, 3, 5, 7}, 7, 4, "Target is the last element (return n)"},
                {new int[]{1, 3, 5, 7}, 10, 4, "Target larger than all elements (return n)"},
                {new int[]{5, 5, 5, 5, 5}, 5, 5, "All elements are the target (return n)"},
                {new int[]{2, 2, 3, 3, 5, 5}, 2, 2, "Target is at the start"},
                {new int[]{}, 5, 0, "Empty array check"},
                {new int[]{10}, 10, 1, "Single element match"},
                {new int[]{10}, 9, 0, "Single element, target smaller"}
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int x = (int) testCases[i][1];
            int expected = (int) testCases[i][2];
            String description = (String) testCases[i][3];

            int actual = solver.findUpperBound(nums, x);

            System.out.println("Test Case " + (i + 1) + ": " + description);
            System.out.println("Input: nums = " + Arrays.toString(nums) + ", x = " + x);
            System.out.println("Expected: " + expected + ", Actual: " + actual);

            if (actual == expected) {
                System.out.println("Result: PASSED");
                passed++;
            } else {
                System.out.println("Result: FAILED");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Score: " + passed + "/" + testCases.length);
    }
}