package searching;

import java.util.Arrays;

public class BinarySearch {

    /**
     * Given an array of integers nums which is sorted in ascending order,
     * and an integer target, write a function to search target in nums.
     * If target exists, then return its index. Otherwise, return -1.
     */
    public int search(int[] arr, int target) {
        int n=arr.length;
        if(n==1)return (arr[0]==target)?0:-1;
        int low=0,hi=n-1;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]==target)return mid;
            else if(arr[mid]>target)hi=mid-1;
            else low=mid+1;
        }
        return -1; // Default return for template
    }

    public static void main(String[] args) {
        BinarySearch solver = new BinarySearch();

        Object[][] testCases = {
                {new int[]{-1, 0, 3, 5, 9, 12}, 9, 4, "Target exists in upper half"},
                {new int[]{-1, 0, 3, 5, 9, 12}, 2, -1, "Target does not exist"},
                {new int[]{5}, 5, 0, "Single element array (Target exists)"},
                {new int[]{5}, -5, -1, "Single element array (Target missing)"},
                {new int[]{2, 5}, 2, 0, "Two elements (Target is first)"},
                {new int[]{2, 5}, 5, 1, "Two elements (Target is second)"},
                {new int[]{-10, -5, 0, 3, 7}, -10, 0, "Target is at the very start"},
                {new int[]{-10, -5, 0, 3, 7}, 7, 4, "Target is at the very end"},
                {new int[]{1, 3, 5, 7, 9, 11}, 6, -1, "Target falls between two existing values"},
                {new int[]{-1000, -500, 0, 500, 1000}, 0, 2, "Target is the exact middle"}
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int target = (int) testCases[i][1];
            int expected = (int) testCases[i][2];
            String description = (String) testCases[i][3];

            int actual = solver.search(nums, target);

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