package searching;

import java.util.Arrays;

public class FloorAndCeil {

    /**
     * Finds the floor and ceil of x in a sorted array.
     * Returns an array of two integers [floor, ceil].
     * If no floor or ceil exists, use -1.
     */
    public int[] getFloorAndCeil(int[] arr, int x) {
        int n=arr.length;
        int floor=-1,ceil=-1,low=0,hi=n-1;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]==x){
                floor=arr[mid];
                break;
            }else if(arr[mid]<x){
                floor=arr[mid];
                low=mid+1;
            }else hi=mid-1;
        }
        low=0;hi=n-1;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]==x){
                ceil=arr[mid];
                break;
            }else if(arr[mid]>x){
                ceil=arr[mid];
                hi=mid-1;
            }else low=mid+1;
        }
        return new int[]{floor, ceil};
    }

    public static void main(String[] args) {
        FloorAndCeil solver = new FloorAndCeil();

        Object[][] testCases = {
                {new int[]{3, 4, 4, 7, 8, 10}, 5, new int[]{4, 7}, "Target in middle, not present"},
                {new int[]{3, 4, 4, 7, 8, 10}, 8, new int[]{8, 8}, "Target present in array"},
                {new int[]{3, 4, 4, 7, 8, 10}, 2, new int[]{-1, 3}, "Target smaller than all (no floor)"},
                {new int[]{3, 4, 4, 7, 8, 10}, 11, new int[]{10, -1}, "Target larger than all (no ceil)"},
                {new int[]{5, 10, 15}, 10, new int[]{10, 10}, "Target matches exact element"},
                {new int[]{5, 10, 15}, 6, new int[]{5, 10}, "Target between two elements"},
                {new int[]{10}, 10, new int[]{10, 10}, "Single element match"},
                {new int[]{10}, 5, new int[]{-1, 10}, "Single element, target smaller"},
                {new int[]{10}, 15, new int[]{10, -1}, "Single element, target larger"},
                {new int[]{}, 5, new int[]{-1, -1}, "Empty array check"}
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int x = (int) testCases[i][1];
            int[] expected = (int[]) testCases[i][2];
            String description = (String) testCases[i][3];

            int[] actual = solver.getFloorAndCeil(nums, x);

            System.out.println("Test Case " + (i + 1) + ": " + description);
            System.out.println("Input: nums = " + Arrays.toString(nums) + ", x = " + x);
            System.out.println("Expected: " + Arrays.toString(expected) + ", Actual: " + Arrays.toString(actual));

            if (Arrays.equals(actual, expected)) {
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