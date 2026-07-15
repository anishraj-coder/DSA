package searching;

import java.util.Arrays;

public class SearchInsertPosition {

    /**
     * Given a sorted array of distinct integers and a target value,
     * return the index if the target is found. If not, return the
     * index where it would be if it were inserted in order.
     */
    public int searchInsert(int[] arr, int target) {
        int low=0,hi=arr.length;
        while(low<hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]>=target)hi=mid;
            else low=mid+1;
        }
        return low;
    }

    public static void main(String[] args) {
        SearchInsertPosition solver = new SearchInsertPosition();

        Object[][] testCases = {
                {new int[]{1, 3, 5, 6}, 5, 2, "Target exists in middle"},
                {new int[]{1, 3, 5, 6}, 2, 1, "Target missing, should be in middle"},
                {new int[]{1, 3, 5, 6}, 7, 4, "Target missing, should be at the end"},
                {new int[]{1, 3, 5, 6}, 0, 0, "Target missing, should be at the start"},
                {new int[]{1}, 0, 0, "Single element, target smaller"},
                {new int[]{1}, 1, 0, "Single element, target matches"},
                {new int[]{1}, 2, 1, "Single element, target larger"},
                {new int[]{1, 3, 5, 8, 10}, 8, 3, "Target exists at index 3"},
                {new int[]{10, 20, 30, 40, 50}, 25, 2, "Target missing between 20 and 30"},
                {new int[]{-10, -5, 0, 5, 10}, -7, 1, "Negative numbers range check"}
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int target = (int) testCases[i][1];
            int expected = (int) testCases[i][2];
            String description = (String) testCases[i][3];

            int actual = solver.searchInsert(nums, target);

            System.out.println("Test Case " + (i + 1) + ": " + description);
            System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
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