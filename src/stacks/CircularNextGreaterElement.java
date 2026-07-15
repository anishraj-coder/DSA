package stacks;

import java.util.*;

public class CircularNextGreaterElement {

    /**
     * Finds the next greater element for every element in a circular array.
     * Constraints: 1 <= nums.length <= 10^4, -10^9 <= nums[i] <= 10^9
     * Time Complexity Requirement: O(N)
     */
    public int[] nextGreaterElements(int[] arr) {
        Deque<Integer>st=new LinkedList<>();
        int n=arr.length;
        int[]res=new int[n];
        Arrays.fill(res,-1);
        for(int i=0;i<2*n;i++){
            int curr=arr[i%n];
            while(!st.isEmpty()&&curr>arr[st.peek()]){
                int idx=st.pop();
                res[idx]=curr;
            }
            if(i<n)st.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        CircularNextGreaterElement solver = new CircularNextGreaterElement();

        // Test Case Definitions: {Input Array, Expected Output Array}
        Object[][] testCases = {
                {new int[]{1, 2, 1}, new int[]{2, -1, 2}},             // Example 1
                {new int[]{1, 2, 3, 4, 3}, new int[]{2, 3, 4, -1, 4}}, // Example 2
                {new int[]{5, 4, 3, 2, 1}, new int[]{-1, 5, 5, 5, 5}}, // Reverse sorted (Circular check)
                {new int[]{1, 1, 1, 1}, new int[]{-1, -1, -1, -1}},    // All same elements
                {new int[]{1, 5, 3, 6, 8}, new int[]{5, 6, 6, 8, -1}}, // Random increasing/decreasing
                {new int[]{-1, -2, -3}, new int[]{-1, -1, -1}},       // All negative (Max is -1)
                {new int[]{10, 1, 1, 1}, new int[]{-1, 10, 10, 10}},   // Large first element
                {new int[]{1, 2, 10}, new int[]{2, 10, -1}},           // Standard increasing
                {new int[]{100, 1, 11, 1, 120}, new int[]{120, 11, 120, 120, -1}}, // Large range
                {new int[]{Integer.MAX_VALUE, 0, Integer.MAX_VALUE - 1}, new int[]{-1, Integer.MAX_VALUE, Integer.MAX_VALUE}} // Edge case: Max values
        };

        int passed = 0;
        System.out.println("Running Circular Next Greater Element Tests...\n");
        System.out.printf("%-20s | %-20s | %-20s | %-8s%n", "Input", "Expected", "Actual", "Result");
        System.out.println("-".repeat(80));

        for (Object[] test : testCases) {
            int[] input = (int[]) test[0];
            int[] expected = (int[]) test[1];
            int[] actual = solver.nextGreaterElements(input);

            boolean isMatch = Arrays.equals(actual, expected);
            if (isMatch) passed++;

            System.out.printf("%-20s | %-20s | %-20s | %-8s%n",
                    Arrays.toString(input), Arrays.toString(expected), Arrays.toString(actual), isMatch ? "PASS" : "FAIL");
        }

        System.out.println("-".repeat(80));
        System.out.printf("Final Score: %d/%d Tests Passed%n", passed, testCases.length);
    }
}
