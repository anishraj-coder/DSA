package recursion;

import java.util.List;

public class SubsequenceSumCount {

    /**
     * Given an array nums and an integer k, return the number of
     * non-empty subsequences of nums such that the sum of all elements
     * in the subsequence is equal to k.
     */
    public int countSubsequences(int[] arr, int k) {
        int n=arr.length;
        if(n==1){
            return (arr[0]==k)?1:0;
        }
        int[]ans=new int[1];
        helper(arr,0,k,ans);
       return ans[0];
    }

    private void helper(int[]arr,int i,int target,int[]ans){
        int n=arr.length;
        if(n==i){
            if(0==target)ans[0]++;
            return;
        }
        if(target-arr[i]>=0)helper(arr,i+1,target-arr[i],ans);
        helper(arr,i+1,target,ans);
    }



    public static void main(String[] args) {
        SubsequenceSumCount instance = new SubsequenceSumCount();

        // Test Case Definitions
        Object[][] testCases = {
                {new int[]{4, 9, 2, 5, 1}, 10, 2},               // Example 1
                {new int[]{4, 2, 10, 5, 1, 3}, 5, 3},            // Example 2
                {new int[]{1, 1, 1}, 2, 3},                      // Duplicate elements
                {new int[]{10, 20, 30}, 5, 0},                   // No possible sum
                {new int[]{0, 0, 1}, 1, 4},                      // Handling zeros: [1], [0,1], [0,1], [0,0,1]
                {new int[]{5}, 5, 1},                            // Single element match
                {new int[]{2, 4, 6}, 6, 2},                      // Single element and combination match
                {new int[]{1, 2, 3, 4, 5}, 15, 1},               // All elements sum to K
                {new int[]{1, 2, 1, 2, 1}, 3, 6},                // Multiple combinations with duplicates
                {new int[]{100, 200, 500}, 1000, 0},             // Sum larger than all elements combined
                {new int[]{0, 0, 0}, 0, 7}                       // K is 0 with zeros in array (2^n - 1 non-empty)
        };

        int passed = 0;
        int failed = 0;

        System.out.println("Running Test Cases...\n" + "=".repeat(60));

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = instance.countSubsequences(nums, k);
            boolean isPassed = (actual == expected);

            if (isPassed) passed++; else failed++;

            System.out.printf("Test Case %d:\n", i + 1);
            System.out.printf("Input: nums = %s, k = %d\n", java.util.Arrays.toString(nums), k);
            System.out.printf("Expected Output: %d\n", expected);
            System.out.printf("Actual Output:   %d\n", actual);
            System.out.printf("Result: %s\n", isPassed ? "PASSED" : "FAILED");
            System.out.println("-".repeat(60));
        }

        System.out.printf("\nFinal Results: %d Passed, %d Failed\n", passed, failed);
    }
}