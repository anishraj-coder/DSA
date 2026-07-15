package searching;

import java.util.Arrays;

public class SingleElementSortedArray1 {

    /**
     * Finds the element that appears exactly once in a sorted array of pairs.
     * Efficiency requirement: O(log n) time and O(1) space.
     *  @param arr The sorted integer array.
     * @return The single element appearing once.
     */
    public int singleNonDuplicate(int[] arr) {
        int n=arr.length;
        if(n==1)return arr[0];

        if(arr[0]!=arr[1])return arr[0];
        if(arr[n-2]!=arr[n-1])return arr[n-1];

        int low=1,hi=n-2;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]==arr[mid+1]){
                if(mid%2==0)low=mid+2;
                else hi=mid-1;
            }else if(arr[mid]==arr[mid-1]){
                if(mid%2==0)hi=mid-2;
                else low=mid+1;
            }else return arr[mid];
        }


        return -1;
    }

    public static void main(String[] args) {
        SingleElementSortedArray1 engine = new SingleElementSortedArray1();

        Object[][] testCases = {
                // {nums, expected_result}
                {new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}, 2},       // Single element in the first half
                {new int[]{3, 3, 7, 7, 10, 11, 11}, 10},         // Single element in the second half
                {new int[]{1, 1, 2, 2, 3}, 3},                   // Single element at the very end
                {new int[]{1, 2, 2, 3, 3}, 1},                   // Single element at the very beginning
                {new int[]{1}, 1},                               // Array size 1
                {new int[]{1, 1, 2, 2, 4, 4, 5, 8, 8}, 5},       // Middle-right position
                {new int[]{1, 1, 3, 3, 4, 4, 5, 5, 6}, 6},       // Last element single
                {new int[]{0, 1, 1, 2, 2}, 0},                   // First element single
                {new int[]{10, 10, 20, 20, 30}, 30},             // Multiples of 10
                {new int[]{1, 1, 2, 2, 3, 3, 4, 5, 5}, 4},       // Standard odd-indexed split
                {new int[]{1, 1, 2, 3, 3}, 2},                   // Small array size 5
                {new int[]{1, 2, 2} , 1}                         // Small array size 3
        };

        int passed = 0;
        System.out.println(String.format("%-45s | %-10s | %-10s | %-6s", "Input Array", "Expected", "Actual", "Result"));
        System.out.println("---------------------------------------------------------------------------------------------");

        for (Object[] test : testCases) {
            int[] nums = (int[]) test[0];
            int expected = (int) test[1];

            int actual = engine.singleNonDuplicate(nums);
            boolean isMatch = actual == expected;

            if (isMatch) passed++;

            String inputStr = Arrays.toString(nums);
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