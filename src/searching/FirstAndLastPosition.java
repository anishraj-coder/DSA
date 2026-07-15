package searching;

import java.util.Arrays;

public class FirstAndLastPosition {

    /**
     * Finds the starting and ending position of a given target value
     * in an array sorted in non-decreasing order.
     * Return [-1, -1] if the target is not found.
     */
    public int[] searchRange(int[] arr, int target) {
//        int n=arr.length;
//        if(n==0)return new int[]{-1,-1};
//        int low=lowerBound(arr,target);
//        int upper=upperBound(arr,target);
//        if(low==n||arr[low]!=target)return new int[]{-1,-1};
//        return new int[]{low, upper-1};

        int n=arr.length,first=-1,last=-1;
        int low=0,hi=n-1;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]==target){
                first=mid;
                hi=mid-1;
            }else if(arr[mid]<target)low=mid+1;
            else hi=mid-1;
        }
        low=0;hi=n-1;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]==target){
                last=mid;
                low=mid+1;
            }else if(arr[mid]<target)low=mid+1;
            else hi=mid-1;
        }

        return new int[]{first,last};
    }

    public int lowerBound(int[]arr,int x){
        int low=0,hi=arr.length;
        while(low<hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]>=x)hi=mid;
            else low=mid+1;
        }
        return low;
    }
    public int upperBound(int[]arr,int x){
        int low=0,hi=arr.length;
        while(low<hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]>x)hi=mid;
            else low=mid+1;
        }
        return low;
    }

    public static void main(String[] args) {
        FirstAndLastPosition solver = new FirstAndLastPosition();

        Object[][] testCases = {
                {new int[]{5, 7, 7, 8, 8, 10}, 8, new int[]{3, 4}, "Target exists multiple times"},
                {new int[]{5, 7, 7, 8, 8, 10}, 6, new int[]{-1, -1}, "Target does not exist"},
                {new int[]{}, 0, new int[]{-1, -1}, "Empty array"},
                {new int[]{1}, 1, new int[]{0, 0}, "Single element match"},
                {new int[]{1, 1, 1, 1, 1}, 1, new int[]{0, 4}, "All elements match target"},
                {new int[]{2, 2}, 2, new int[]{0, 1}, "Two elements match target"},
                {new int[]{1, 2, 3}, 2, new int[]{1, 1}, "Target exists once in middle"},
                {new int[]{5, 7, 7, 8, 8, 10}, 5, new int[]{0, 0}, "Target at the very start"},
                {new int[]{5, 7, 7, 8, 8, 10}, 10, new int[]{5, 5}, "Target at the very end"},
                {new int[]{1, 2, 3, 3, 3, 3, 4, 5, 9}, 3, new int[]{2, 5}, "Standard search with duplicates"},
                {new int[]{-10, -10, -10, -5, 0}, -10, new int[]{0, 2}, "Negative numbers with duplicates"}
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int target = (int) testCases[i][1];
            int[] expected = (int[]) testCases[i][2];
            String description = (String) testCases[i][3];

            int[] actual = solver.searchRange(nums, target);

            System.out.println("Test Case " + (i + 1) + ": " + description);
            System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
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