package stacks;

import java.util.*;

public class NextSmallerElement {

    /**
     * Finds the next smaller element for each element in the array.
     * * Constraints:
     * 1 <= N <= 10^5
     * 0 <= ARR[i] <= 10^9
     * * @param arr Input array of integers
     * @param n   Length of the array
     * @return    An array containing the next smaller element for each index
     */
    public int[] nextSmaller(int[] arr, int n) {
        Deque<Integer>st=new ArrayDeque<>();
        int[]res=new int[n];
        for(int i=0;i<n;i++){
            while(!st.isEmpty()&&arr[i]<arr[st.peek()]){
                res[st.pop()]=arr[i];
            }
            st.push(i);
        }
        while(!st.isEmpty())res[st.pop()]=-1;
        return res;
    }

    public static void main(String[] args) {
        NextSmallerElement solver = new NextSmallerElement();

        // Test Case Definitions: {Input Array, Expected Output}
        Object[][] testCases = {
                {new int[]{2, 3, 1}, new int[]{1, 1, -1}},                // Basic example
                {new int[]{2, 1, 4, 3}, new int[]{1, -1, 3, -1}},        // Mixed example 1
                {new int[]{1, 3, 2}, new int[]{-1, 2, -1}},              // Mixed example 2
                {new int[]{1, 2, 3, 4}, new int[]{-1, -1, -1, -1}},      // Strictly increasing
                {new int[]{4, 3, 2, 1}, new int[]{3, 2, 1, -1}},        // Strictly decreasing
                {new int[]{3, 3, 1, 1}, new int[]{1, 1, -1, -1}},        // Handling duplicates (strictly smaller)
                {new int[]{5}, new int[]{-1}},                           // Single element
                {new int[]{2, 2, 2}, new int[]{-1, -1, -1}},            // All elements same
                {new int[]{1000000000, 0}, new int[]{0, -1}},            // Large values/Boundary
                {new int[]{1, 5, 2, 6, 3}, new int[]{-1, 2, -1, 3, -1}}, // Alternating hill/valley
                {new int[]{10, 8, 12, 5, 15, 2}, new int[]{8, 5, 5, 2, 2, -1}} // Multi-step jumps
        };

        int passed = 0;
        System.out.println("Running Next Smaller Element Tests...\n");
        System.out.printf("%-25s | %-20s | %-20s | %-8s%n", "Input", "Expected", "Actual", "Result");
        System.out.println("-".repeat(85));

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int[] expected = (int[]) testCases[i][1];
            int n = input.length;

            int[] actual = solver.nextSmaller(input, n);
            boolean isMatch = Arrays.equals(actual, expected);

            if (isMatch) passed++;

            System.out.printf("%-25s | %-20s | %-20s | %-8s%n",
                    Arrays.toString(input),
                    Arrays.toString(expected),
                    Arrays.toString(actual),
                    isMatch ? "PASS" : "FAIL");
        }

        System.out.println("-".repeat(85));
        System.out.printf("Final Result: %d/%d Tests Passed%n", passed, testCases.length);
    }
}