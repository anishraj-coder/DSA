package arrays;

import java.util.Arrays;

public class MaximumSubarraySumPrint {

    /**
     * Finds the contiguous subarray with the largest sum.
     * @param arr An array of integers.
     * @return The actual subarray that yields the maximum sum.
     */
    public  int[] findMaxSubArray(int[] arr) {

        int n=arr.length, sum=0,max=Integer.MIN_VALUE,s=-1,e=-1,t=-1;
        if(n<=1)return arr;

        for(int i=0;i<n;i++){
            if(sum==0)t=i;
            sum+=arr[i];
            if(sum>max){
                max=sum;
                s=t;
                e=i;
            }
            sum=Math.max(0,sum);
        }
        int[]res=new int[e-s+1];
        for(int i=s;i<=e;i++){
            res[i-s]=arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        MaximumSubarraySumPrint runner = new MaximumSubarraySumPrint();

        // Test Cases: {Input Array, Expected Subarray Output, Description}
        Object[][] testCases = {
                {new int[]{2, 3, 5, -2, 7, -4}, new int[]{2, 3, 5, -2, 7}, "Standard mixed values"},
                {new int[]{-2, -3, -7, -2, -10, -4}, new int[]{-2}, "All negative numbers (first peak)"},
                {new int[]{-1, 2, 3, -1, 2, -6, 5}, new int[]{2, 3, -1, 2}, "Mixed values with multiple peaks"},
                {new int[]{5, 4, -1, 7, 8}, new int[]{5, 4, -1, 7, 8}, "All elements form the max sum"},
                {new int[]{1}, new int[]{1}, "Single element positive"},
                {new int[]{-5}, new int[]{-5}, "Single element negative"},
                {new int[]{0, -1, -2, -3}, new int[]{0}, "Zero and negatives"},
                {new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}, new int[]{4, -1, 2, 1}, "Classic LC example"},
                {new int[]{10, -20, 30, -1, 5, -100, 50}, new int[]{50}, "Single element at the end is max"},
                {new int[]{1, 2, 3, -10, 4, 5}, new int[]{4, 5}, "Max sum at the end of array"}
        };

        int passed = 0;

        System.out.println("--- Starting Subarray Test Execution ---\n");

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int[] expected = (int[]) testCases[i][1];
            String description = (String) testCases[i][2];

            int[] actual = runner.findMaxSubArray(nums);
            boolean isCorrect = Arrays.equals(actual, expected);

            if (isCorrect) {
                passed++;
            }

            System.out.printf("Test Case %d: %s\n", i + 1, description);
            System.out.printf("Input: %s\n", Arrays.toString(nums));
            System.out.printf("Expected Subarray: %s\n", Arrays.toString(expected));
            System.out.printf("Actual Subarray:   %s\n", Arrays.toString(actual));
            System.out.println("Result: " + (isCorrect ? "✅ PASSED" : "❌ FAILED"));
            System.out.println("----------------------------------------");
        }

        System.out.printf("\nFinal Result: %d/%d Passed.\n", passed, testCases.length);
    }
}