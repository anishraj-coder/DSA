package stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Problem: Sum of Subarray Ranges
 * The range of a subarray is the difference between the largest and smallest element in that subarray.
 * Return the sum of all subarray ranges for the given integer array.
 */
public class SubarrayRangeSum {

    /**
     * Calculates the sum of all subarray ranges.
     * Range = max(subarray) - min(subarray)
     * @param arr An array of integers.
     * @return The total sum of ranges of all possible contiguous subarrays.
     */
    public long subArrayRanges(int[] arr) {
        long minSum=subarrayMinimumSum(arr);
        long maxSum=subarrayMaximumSum(arr);
        return maxSum-minSum;
    }

    private long subarrayMinimumSum(int[]arr){
        int n=arr.length;
        long sum=0L;
        int[]pse=previousSmallerOrEqual(arr);
        int[]ns=nextSmaller(arr);
        for(int i=0;i<n;i++){
            int right=ns[i]-i;
            int left=i-pse[i];
            long val=((long)arr[i])*((long)right*left);
            sum+=val;
        }
        return sum;
    }
    private long subarrayMaximumSum(int[]arr){
        int n=arr.length;
        long sum=0L;
        int[]pge=previousGreaterOrEqual(arr);
        int[]ng=nextGreater(arr);
        for(int i=0;i<n;i++){
            int right=ng[i]-i;
            int left=i-pge[i];
            long val=((long)arr[i])*((long)right*left);
            sum+=val;
        }
        return sum;
    }

    private int[] nextSmaller(int[]arr){
        int n=arr.length;
        int[]res=new int[n];
        Deque<Integer>st=new ArrayDeque<>();
        for(int i=0;i<n;i++){
            while(!st.isEmpty()&&arr[i]<arr[st.peek()])res[st.pop()]=i;
            st.push(i);
        }
        while(!st.isEmpty())res[st.pop()]=n;
        return res;
    }

    private int[] previousSmallerOrEqual(int[]arr){
        int n=arr.length;
        int[]res=new int[n];
        Deque<Integer>st=new ArrayDeque<>();
        for(int i=n-1;i>=0;i--){
            while(!st.isEmpty()&&arr[i]<=arr[st.peek()])res[st.pop()]=i;
            st.push(i);
        }
        while(!st.isEmpty())res[st.pop()]=-1;
        return res;
    }

    private int[] nextGreater(int[]arr){
        int n=arr.length;
        int[]res=new int[n];
        Deque<Integer>st=new ArrayDeque<>();
        for(int i=0;i<n;i++){
            while(!st.isEmpty()&&arr[i]>arr[st.peek()])res[st.pop()]=i;
            st.push(i);
        }

        while(!st.isEmpty())res[st.pop()]=n;
        return res;
    }
    private int[] previousGreaterOrEqual(int[]arr){
        int n=arr.length;
        int[]res=new int[n];
        Deque<Integer>st=new ArrayDeque<>();
        for(int i=n-1;i>=0;i--){
            while(!st.isEmpty()&&arr[i]>=arr[st.peek()])res[st.pop()]=i;
            st.push(i);
        }
        while(!st.isEmpty())res[st.pop()]=-1;
        return res;
    }

    public static void main(String[] args) {
        SubarrayRangeSum solver = new SubarrayRangeSum();

        Object[][] testCases = {
                {new int[]{1, 2, 3}, 4L, "Simple increasing sequence"},
                {new int[]{4, -2, -3, 4, 1}, 59L, "Mixed positive and negative numbers"},
                {new int[]{1, 3, 3}, 4L, "Sequence with duplicates"},
                {new int[]{10}, 0L, "Single element array (Range is always 0)"},
                {new int[]{5, 5, 5, 5}, 0L, "All elements identical"},
                {new int[]{1, 10, 1}, 27L, "Peak in the middle"},
                {new int[]{-10, -5, -2}, 11L, "All negative numbers"},
                {new int[]{10, 1, 10}, 27L, "Valley in the middle"},
                {new int[]{1000000000, 0, -1000000000}, 6000000000L, "Large values (Test for long overflow)"},
                {new int[]{3, 1, 2, 4}, 15L, "Random permutation"},
                {new int[]{1, 2, 1, 2}, 6L, "Alternating small sequence"}
        };

        int passed = 0;
        System.out.println("Running Test Cases...\n" + "=".repeat(60));

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            long expected = (long) testCases[i][1];
            String description = (String) testCases[i][2];

            long actual = solver.subArrayRanges(nums);
            boolean isCorrect = actual == expected;

            if (isCorrect) passed++;

            System.out.printf("Test Case %d: %s\n", i + 1, description);
            System.out.printf("Input: %s\n", Arrays.toString(nums));
            System.out.printf("Expected: %d | Actual: %d\n", expected, actual);
            System.out.printf("Result: %s\n", isCorrect ? "✅ PASSED" : "❌ FAILED");
            System.out.println("-".repeat(60));
        }

        System.out.printf("\nFinal Results: %d/%d Passed\n", passed, testCases.length);
    }
}