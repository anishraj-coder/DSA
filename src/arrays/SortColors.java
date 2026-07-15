package arrays;

import java.util.*;

public class SortColors {

    /**
     * Sorts the array in-place with 0s, 1s, and 2s.
     * Requirement: O(n) time and O(1) space.
     * * @param arr The input array containing only 0, 1, and 2.
     */
    public void sortColors(int[] arr) {
        int n=arr.length;
        if(n==1)return;
        int i=0,j=0,k=n-1;

        while(j<=k&&i<=j){
            if(arr[j]==0){
                swap(arr,i,j);
                i++;
                j++;
            }else if(arr[j]==1){
                j++;
            }else if(arr[j]==2){
                swap(arr,j,k);
                k--;
            }
        }

    }

    public void swap(int[]arr,int i,int j){
        int t=arr[i];
        arr[i]=arr[j];
        arr[j]=t;
    }

    public static void main(String[] args) {
        SortColors instance = new SortColors();

        // Test Cases: {Input Array, Expected Array}
        Object[][] testCases = {
                {new int[]{2, 0, 2, 1, 1, 0}, new int[]{0, 0, 1, 1, 2, 2}}, // Case 1: Standard mix
                {new int[]{2, 0, 1}, new int[]{0, 1, 2}},                // Case 2: Small mix
                {new int[]{0}, new int[]{0}},                            // Case 3: Single element
                {new int[]{2, 2, 2}, new int[]{2, 2, 2}},                // Case 4: All same (Blue)
                {new int[]{0, 0, 0}, new int[]{0, 0, 0}},                // Case 5: All same (Red)
                {new int[]{1, 1, 1}, new int[]{1, 1, 1}},                // Case 6: All same (White)
                {new int[]{2, 1, 0}, new int[]{0, 1, 2}},                // Case 7: Reverse sorted
                {new int[]{0, 1, 2}, new int[]{0, 1, 2}},                // Case 8: Already sorted
                {new int[]{1, 0, 2, 1, 0}, new int[]{0, 0, 1, 1, 2}},    // Case 9: Repeated zeros and ones
                {new int[]{2, 0, 2, 0, 1, 2, 1, 0}, new int[]{0, 0, 0, 1, 1, 2, 2, 2}} // Case 10: Larger mix
        };

        int passed = 0;

        System.out.println(String.format("%-10s | %-25s | %-25s | %-8s",
                "Test Case", "Original", "Expected", "Result"));
        System.out.println("-".repeat(80));

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int[] expected = (int[]) testCases[i][1];

            // Clone the input because the method sorts in-place
            int[] inputCopy = nums.clone();
            int[] displayCopy = nums.clone(); // For printing

            instance.sortColors(inputCopy);

            boolean isMatch = Arrays.equals(inputCopy, expected);
            if (isMatch) passed++;

            System.out.println(String.format("%-10d | %-25s | %-25s | %-8s",
                    (i + 1), Arrays.toString(displayCopy), Arrays.toString(expected), isMatch ? "PASSED" : "FAILED"));

            // If failed, show what the actual result was
            if (!isMatch) {
                System.out.println("           -> Actual Output: " + Arrays.toString(inputCopy));
            }
        }

        System.out.println("-".repeat(80));
        System.out.println("Final Score: " + passed + "/" + testCases.length);
    }
}