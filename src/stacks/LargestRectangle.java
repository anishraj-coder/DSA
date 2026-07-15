package stacks;

import java.util.*;

public class LargestRectangle {

    /**
     * Given an array of integers heights representing the histogram's bar height
     * where the width of each bar is 1, return the area of the largest rectangle
     * in the histogram.
     * * Constraints:
     * 1 <= heights.length <= 10^5
     * 0 <= heights[i] <= 10^4
     */
    public int largestRectangleArea(int[] arr) {
        int max=Integer.MIN_VALUE,n=arr.length;
        int[]pre=previousSmaller(arr),post=nextSmaller(arr);
        for(int i=0;i<n;i++){
            int left=pre[i]+1;
            int right=post[i]-1;
            int length=right-left+1,area=length*arr[i];
            max=Math.max(area,max);
        }
        return max;
    }

    private int[]nextSmaller(int[]arr){
        int n=arr.length;
        int[]res=new int[n];
        Deque<Integer>st=new ArrayDeque<>();
        for(int i=0;i<n;i++){
            while(!st.isEmpty()&&arr[i]<arr[st.peek()])res[st.pop()]=i;
            st.push(i);
        }
        while (!st.isEmpty())res[st.pop()]=n;
        return res;
    }

    private int[] previousSmaller(int[]arr){
        int n=arr.length;
        int[]res=new int[n];
        Deque<Integer>st=new ArrayDeque<>();
        for(int i=n-1;i>=0;i--){
            while(!st.isEmpty()&&arr[i]<arr[st.peek()])res[st.pop()]=i;
            st.push(i);
        }
        while(!st.isEmpty())res[st.pop()]=-1;
        return res;
    }

    public static void main(String[] args) {
        LargestRectangle solver = new LargestRectangle();

        // Test Case Definitions
        Object[][] tests = {
                {new int[]{2, 1, 5, 6, 2, 3}, 10},     // Standard case (Example 1)
                {new int[]{2, 4}, 4},                 // Simple increasing (Example 2)
                {new int[]{1, 1, 1, 1}, 4},           // All same heights
                {new int[]{5, 4, 3, 2, 1}, 9},        // Strictly decreasing (3x3 rectangle)
                {new int[]{1, 2, 3, 4, 5}, 9},        // Strictly increasing (3x3 rectangle)
                {new int[]{0, 9}, 9},                 // Zero height handling
                {new int[]{10000}, 10000},            // Single bar (Maximum height)
                {new int[]{2, 1, 2}, 3},              // Valley shape
                {new int[]{1, 2, 1, 2, 1}, 5},        // Zig-zag / Alternating
                {new int[]{6, 2, 5, 4, 5, 1, 6}, 12}  // Multi-peak complex case
        };

        int passed = 0;

        System.out.println(String.format("%-25s | %-10s | %-10s | %-8s",
                "Input Heights", "Expected", "Actual", "Result"));
        System.out.println("-------------------------------------------------------------------------");

        for (Object[] test : tests) {
            int[] heights = (int[]) test[0];
            int expected = (int) test[1];

            int actual = solver.largestRectangleArea(heights);

            boolean isMatch = (expected == actual);
            if (isMatch) passed++;

            String inputStr = Arrays.toString(heights);
            if (inputStr.length() > 22) inputStr = inputStr.substring(0, 19) + "...";

            System.out.println(String.format("%-25s | %-10d | %-10d | %-8s",
                    inputStr, expected, actual, isMatch ? "PASSED" : "FAILED"));
        }

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Total Passed: " + passed + "/" + tests.length);
    }
}