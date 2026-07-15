package arrays;


import java.util.Arrays;

public class LongestSubarraySumPositive {

    /**
     * Finds the length of the longest sub-array that sums to k.
     * Optimized for non-negative integers using Two Pointers / Sliding Window.
     */
    public int getLongestSubarray(int[] arr, int k) {
        int n=arr.length;
        if(n==0)return 0;
        if(n==1&&arr[0]==k)return 1;
        else if (n==1) return 0;

        int maxLen=0,s=0,e=0,sum=arr[0];
        while(e<n){
            if(sum==k){
                maxLen=Math.max(e-s+1,maxLen);
                e++;
                if(e<n)sum+=arr[e];
            }else if(sum<k){
                e++;
                if(e<n)sum+=arr[e];
            }else{
                sum-=arr[s];
                s++;
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubarraySumPositive instance = new LongestSubarraySumPositive();

        // Test Cases: Array (int[]), Target K (int), Expected Output (int)
        Object[][] testCases = {
                {new int[]{1, 2, 3, 7, 5}, 12, 3},          // Case 1: Standard match [2,3,7]
                {new int[]{1, 2, 3, 1, 1, 1, 1, 4, 2, 3}, 3, 3}, // Case 2: Multiple matches, longest is [1, 1, 1]
                {new int[]{10, 5, 2, 7, 1, 9}, 15, 4},      // Case 3: Match in the middle [5, 2, 7, 1]
                {new int[]{8, 2, 3, 5}, 5, 2},              // Case 4: Multiple matches of same length ([2, 3] and [5]) -> 2
                {new int[]{2, 0, 0, 3}, 3, 3},              // Case 5: Zeros increasing length ([0, 0, 3])
                {new int[]{1, 4, 45, 6, 0, 19}, 51, 3},     // Case 6: Large numbers [45, 6, 0]
                {new int[]{5, 5, 5, 5}, 5, 1},              // Case 7: Repeating elements
                {new int[]{1, 2, 3}, 10, 0},                // Case 8: Sum never reached
                {new int[]{0, 0, 0}, 0, 3},                 // Case 9: All zeros k=0
                {new int[]{1, 2, 1, 3}, 2, 1},              // Case 10: First match is longest
                {new int[]{1, 1, 1, 1, 1}, 5, 5}            // Case 11: Entire array is the sum
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

            String arrayString = Arrays.toString(nums);
            if (arrayString.length() > 33) {
                arrayString = arrayString.substring(0, 30) + "...";
            }

            System.out.println(String.format("%-10d | %-35s | %-5d | %-8d | %-8d | %-8s",
                    (i + 1), arrayString, k, expected, actual, isMatch ? "PASSED" : "FAILED"));
        }

        System.out.println("-".repeat(95));
        System.out.println("Final Score: " + passed + "/" + testCases.length);
    }
}