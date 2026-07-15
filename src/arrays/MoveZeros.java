package arrays;

import java.util.Arrays;

public class MoveZeros {

    /**
     * Moves all 0's to the end of the array while maintaining the relative order
     * of the non-zero elements. Modifies the input array in-place.
     */
    public void moveZeroes(int[] arr) {
        if(arr==null)return ;
        int n=arr.length,j=-1;
        if(n==1)return;
        for(int i=0;i<n;i++){
            if(arr[i]==0){
                j=i;
                break;
            }
        }
        if(j==-1) return;

        for(int i=j+1;i<n;i++){
            if(arr[i]!=0){
                int t=arr[i];
                arr[i]=arr[j];
                arr[j]=t;

                j++;
            }
        }

    }

    public static void main(String[] args) {
        MoveZeros solver = new MoveZeros();

        // Test Cases: {Input, Expected}
        Object[][] testCases = {
                {new int[]{0, 1,1, 0, 3, 12}, new int[]{1, 1,3, 12, 0, 0}},  // Standard case
                {new int[]{0}, new int[]{0}},                           // Single element zero
                {new int[]{1}, new int[]{1}},                           // Single element non-zero
                {new int[]{2, 1}, new int[]{2, 1}},                     // No zeros
                {new int[]{0, 0, 0}, new int[]{0, 0, 0}},               // All zeros
                {new int[]{4, 5, 0, 0, 6}, new int[]{4, 5, 6, 0, 0}},   // Zeros in middle
                {new int[]{0, 0, 1, 2, 3}, new int[]{1, 2, 3, 0, 0}},   // Leading zeros
                {new int[]{1, 2, 3, 0, 0}, new int[]{1, 2, 3, 0, 0}},   // Trailing zeros
                {new int[]{0, -1, 0, -3, 12}, new int[]{-1, -3, 12, 0, 0}}, // Negative numbers
                {new int[]{1, 0, 2, 0, 3, 0, 4}, new int[]{1, 2, 3, 4, 0, 0, 0}} // Alternating zeros
        };

        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int[] expected = (int[]) testCases[i][1];

            // Clone input for "Actual" output tracking since we modify in-place
            int[] actual = input.clone();
            solver.moveZeroes(actual);

            boolean isMatch = Arrays.equals(actual, expected);
            if (isMatch) passed++;

            System.out.println("Test Case " + (i + 1) + ": " + (isMatch ? "PASSED" : "FAILED"));
            System.out.println("   Input:    " + Arrays.toString(input));
            System.out.println("   Expected: " + Arrays.toString(expected));
            System.out.println("   Actual:   " + Arrays.toString(actual));
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Summary: " + passed + "/" + testCases.length + " test cases passed.");
    }
}
