package stacks;

import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class SubarrayMinimumSum {

    /**
     * Given an array of integers arr, find the sum of min(b),
     * where b ranges over every (contiguous) subarray of arr.
     * Return the answer modulo 10^9 + 7.
     */
    public int sumSubarrayMins(int[] arr) {
        int n=arr.length,sum=0,mod=((int)1e9)+7;
        int[]pse=findPreviousSmallerOrEqual(arr);
        int[]ns=findNextSmaller(arr);
        for(int i=0;i<n;i++){
            int left=i-pse[i];
            int right=ns[i]-i;
            long freq=(long)left*right,val=arr[i]*freq;
            sum=(int)((sum+val)%mod);
        }
        return sum;
    }

    private int[] findNextSmaller(int[]arr){
        int n=arr.length;
        int[]res=new int[n];
        Stack<Integer>st=new Stack<>();
        for(int i=0;i<n;i++){
            while(!st.isEmpty()&&arr[i]<arr[st.peek()])res[st.pop()]=i;
            st.push(i);
        }
        while(!st.isEmpty())res[st.pop()]=n;
        return res;
    }

    private int[] findPreviousSmallerOrEqual(int[]arr){
        int n=arr.length;
        Stack<Integer>st=new Stack<>();
        int[]res=new  int[n];
        for(int i=n-1;i>=0;i--){
            while(!st.isEmpty()&&arr[i]<=arr[st.peek()])res[st.pop()]=i;
            st.push(i);
        }
        while(!st.isEmpty())res[st.pop()]=-1;
        return res;
    }


    public static void main(String[] args) {
        SubarrayMinimumSum instance = new SubarrayMinimumSum();

        int[][] inputs = {
                {3, 1, 2, 4},                          // Example 1
                {11, 81, 94, 43, 3},                   // Example 2
                {1, 1, 1, 1},                          // All identical elements
                {1, 2, 3, 4, 5},                       // Strictly increasing
                {5, 4, 3, 2, 1},                       // Strictly decreasing
                {10},                                  // Single element
                {7, 1, 7, 1, 7},                       // Fluctuating duplicates
                {3, 1, 2, 1, 3},                       // Symmetric with duplicates
                {30000, 30000, 30000},                 // Max value constraints
                {1, 2, 1, 2, 1, 2, 1, 2, 1, 2}         // Repeating pattern
        };

        int[] expectedResults = {
                17,
                444,
                10,
                35,
                35,
                10,
                23,
                24,
                180000,
                75
        };

        int passed = 0;

        System.out.println("Running Test Cases...\n" + "=".repeat(40));

        for (int i = 0; i < inputs.length; i++) {
            int[] testCase = inputs[i];
            int expected = expectedResults[i];
            int actual = instance.sumSubarrayMins(testCase);

            System.out.printf("Test Case %d: Input = %s\n", i + 1, Arrays.toString(testCase));
            System.out.printf("Expected: %d | Actual: %d\n", expected, actual);

            if (actual == expected) {
                System.out.println("Result: [PASSED]");
                passed++;
            } else {
                System.out.println("Result: [FAILED]");
            }
            System.out.println("-".repeat(40));
        }

        System.out.printf("\nFinal Score: %d/%d Test Cases Passed.\n", passed, inputs.length);
    }
}
