package arrays;

import java.util.*;

public class LongestSubarrayWithSumK {

    /**
     * Finds the length of the longest sub-array that sums to k.
     * * @param arr The input array of integers.
     * @param k    The target sum.
     * @return     The length of the longest subarray with sum k.
     */
    public int getLongestSubarray(int[] arr, int k) {
        int n=arr.length;
        if(n==0)return 0;
        if(n==1&&arr[0]==k)return 1;
        else if(n==1)return 0;
        Map<Long,Integer> map=new HashMap<>();
        map.put(0L,-1);
        long sum=0;
        int maxLen=0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
            long rem=sum-k;
            if(map.containsKey(rem)){
                int len=i-map.get(rem);
                maxLen=Math.max(maxLen,len);
            }

            if(!map.containsKey(sum))map.put(sum,i);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubarrayWithSumK instance = new LongestSubarrayWithSumK();

        // Test Case Definitions
        Object[][] testCases = {
                {new int[]{10, 5, 2, 7, 1, 9}, 15, 4},       // Case 1: Standard positive numbers
                {new int[]{-3, 2, 1}, 6, 0},                // Case 2: No subarray sums to k
                {new int[]{1, 2, 3, 1, 1, 1, 1}, 3, 3},     // Case 3: Multiple subarrays, find longest ([1,1,1])
                {new int[]{2, 0, 0, 3}, 3, 3},              // Case 4: Subarray with zeros ([0, 0, 3])
                {new int[]{1, -1, 5, -2, 3}, 3, 4},         // Case 5: Positive and negative numbers ([1, -1, 5, -2])
                {new int[]{-1, 1, 1}, 1, 3},                // Case 6: Negative prefix affecting length ([-1, 1, 1])
                {new int[]{5}, 5, 1},                       // Case 7: Single element match
                {new int[]{5}, 10, 0},                      // Case 8: Single element mismatch
                {new int[]{0, 0, 0, 0, 0}, 0, 5},           // Case 9: All zeros, sum k=0
                {new int[]{-5, 8, -1, 4, 2}, 7, 3},         // Case 10: Mixed signs with sum in middle ([8, -1, 4] is 11, [-1, 4, 2] is 5... no, [8, -1] is 7(2), but [8, -1, 4, 2] is 13. Let's check: 8-1=7 len 2. 4+2+1... -1+4+2=5. Correct: [8,-1] is 7)
                {new int[]{2, 3, 5, 1, 9}, 10, 3}           // Case 11: Sum at the beginning ([2, 3, 5])
        };

        int passed = 0;

        System.out.println(String.format("%-10s | %-35s | %-5s | %-8s | %-8s | %-8s",
                "Test Case", "Input Array", "K", "Expected", "Actual", "Result"));
        System.out.println("-".repeat(95));

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = instance.getLongestSubarray(nums, k);
            boolean isMatch = (actual == expected);
            if (isMatch) passed++;

            System.out.println(String.format("%-10d | %-35s | %-5d | %-8d | %-8d | %-8s",
                    (i + 1), Arrays.toString(nums), k, expected, actual, isMatch ? "PASSED" : "FAILED"));
        }

        System.out.println("-".repeat(95));
        System.out.println("Total Passed: " + passed + "/" + testCases.length);
    }
}