package bitManipulation;

import java.util.Arrays;

/**
 * Problem: Single Number III
 * Task: Find the two elements that appear only once in an array where all other
 * elements appear exactly twice.
 * Constraints: O(n) time and O(1) space.
 */
public class SingleNumberIII {

    public int[] findSingleNumbers(int[] arr) {
        int xor=0;
        for(int a:arr)xor^=a;
        int last=xor&(~(xor-1));
        int on=0,off=0;
        for(int a:arr){
            if((a&last)==0){
                off^=a;
            }else{
                on^=a;
            }
        }
        return new int[]{on, off};
    }

    public static void main(String[] args) {
        SingleNumberIII solver = new SingleNumberIII();

        Object[][] testCases = {
                {new int[]{1, 2, 1, 3, 2, 5}, new int[]{3, 5}, "Standard case"},
                {new int[]{-1, 0}, new int[]{-1, 0}, "Two elements only"},
                {new int[]{0, 1}, new int[]{0, 1}, "Zero and one"},
                {new int[]{2, 1, 2, 3, 4, 1}, new int[]{3, 4}, "Unordered standard"},
                {new int[]{1, 1, 2, 2, 3, 3, 4, 5}, new int[]{4, 5}, "Consecutive pairs"},
                {new int[]{11, 8, 11, 8, 100, -100}, new int[]{-100, 100}, "Positive and negative large"},
                {new int[]{2147483647, 0, 2147483647, 10}, new int[]{0, 10}, "Max Integer boundary"},
                {new int[]{-2147483648, 1, -2147483648, 2}, new int[]{1, 2}, "Min Integer boundary"},
                {new int[]{5, 6, 6, 7, 7, 8, 8, 9}, new int[]{5, 9}, "Single numbers at ends"},
                {new int[]{10, 20, 30, 40, 10, 20, 30, 50}, new int[]{40, 50}, "Standard large gap"}
        };

        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int[] expected = (int[]) testCases[i][1];
            String description = (String) testCases[i][2];

            int[] actual = solver.findSingleNumbers(input);

            // Sort both for comparison since order doesn't matter
            Arrays.sort(expected);
            Arrays.sort(actual);

            boolean isCorrect = Arrays.equals(expected, actual);
            if (isCorrect) passed++;

            System.out.println("Test Case " + (i + 1) + ": " + description);
            System.out.println("Input:    " + Arrays.toString(input));
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Actual:   " + Arrays.toString(actual));
            System.out.println("Result:   " + (isCorrect ? "PASSED ✅" : "FAILED ❌"));
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Total Passed: " + passed + "/" + testCases.length);
    }
}