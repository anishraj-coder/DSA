package stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Problem: Sliding Window Maximum
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] arr, int k) {
        int n=arr.length;
        if(k>n)return new int[0];
        Deque<Integer>st=new ArrayDeque<>();
        int[]ans=new int[n-k+1];
        for(int i=0;i<n;i++){
            if(!st.isEmpty()&&st.getFirst()<=i-k)st.pollFirst();
            while(!st.isEmpty()&&arr[i]>=arr[st.peekLast()])st.pop();
            st.offerLast(i);
            if(i>=k-1){
                ans[i-k+1]=arr[st.peekFirst()];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum solver = new SlidingWindowMaximum();

        Object[][] testCases = {
                // {nums, k, expectedOutput}
                {new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3, new int[]{3, 3, 5, 5, 6, 7}}, // Standard case
                {new int[]{1}, 1, new int[]{1}},                                     // Single element
                {new int[]{1, -1}, 1, new int[]{1, -1}},                             // Window size 1
                {new int[]{9, 11}, 2, new int[]{11}},                               // Window equals length
                {new int[]{4, -2}, 2, new int[]{4}},                                // Negative values
                {new int[]{7, 2, 4}, 2, new int[]{7, 4}},                           // Decreasing then increasing
                {new int[]{1, 3, 1, 2, 0, 5}, 3, new int[]{3, 3, 2, 5}},            // Local peaks
                {new int[]{-7, -8, -7, 5, 7, 1, 6, 0}, 4, new int[]{5, 7, 7, 7, 7}}, // Negative to positive
                {new int[]{1, 1, 1, 1, 1, 1}, 3, new int[]{1, 1, 1, 1}},            // All identical elements
                {new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, 2, new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2}} // Strictly decreasing
        };

        int passed = 0;

        System.out.println("Running Sliding Window Maximum Tests...\n");
        System.out.println("-----------------------------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int[] expected = (int[]) testCases[i][2];

            int[] actual = solver.maxSlidingWindow(nums, k);

            boolean isCorrect = Arrays.equals(actual, expected);
            if (isCorrect) passed++;

            System.out.printf("Test Case %d: %s\n", i + 1, isCorrect ? "PASSED ✅" : "FAILED ❌");
            System.out.println("Input: nums = " + Arrays.toString(nums) + ", k = " + k);
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Actual:   " + Arrays.toString(actual));
            System.out.println("-----------------------------------------------------------------------");
        }

        System.out.printf("\nFinal Result: %d/%d Passed\n", passed, testCases.length);
    }
}