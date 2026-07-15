package twoPointer;

import java.util.HashMap;
import java.util.Map;

public class SubarraysWithKDifferentIntegers {

    /**
     * Returns the number of good subarrays where the number of
     * different integers is exactly k.
     * * @param nums Array of integers.
     * @param k The required number of distinct integers.
     * @return Total count of good subarrays.
     */
    public int subarraysWithKDistinct(int[] arr, int k) {
        // Your implementation goes here
        // Hint: Helper function for "atMost(k)" might be useful
        return helper(arr,k)-helper(arr,k-1);
    }

    private int helper(int[]arr,int k){
        if(k<1)return 0;
        int count=0,n=arr.length;
        HashMap<Integer,Integer>map=new HashMap<>();
        int i=0,j=0;
        while(j<n){
            int add=arr[j];
            map.put(add,map.getOrDefault(add,0)+1);
            while(map.size()>k){
                int rem=arr[i];
                map.put(rem,map.getOrDefault(rem,0)-1);
                if(map.get(rem)==0)map.remove(rem);
                i++;
            }
            count+=j-i+1;
            j++;
        }
        return count;
    }

    public static void main(String[] args) {
        SubarraysWithKDifferentIntegers solver = new SubarraysWithKDifferentIntegers();

        Object[][] testCases = {
                // {nums, k, expectedOutput}
                {new int[]{1, 2, 1, 2, 3}, 2, 7},            // Example 1
                {new int[]{1, 2, 1, 3, 4}, 3, 3},            // Example 2
                {new int[]{1, 1, 1, 1, 1}, 1, 15},           // All same: n*(n+1)/2 = 5*6/2
                {new int[]{1, 2, 3, 4, 5}, 1, 5},            // All distinct, k=1: Each element is its own subarray
                {new int[]{1, 2, 3, 4, 5}, 5, 1},            // All distinct, k=n: Only the whole array works
                {new int[]{1, 2, 1, 2, 1}, 2, 10},           // Alternating: many combinations
                {new int[]{1, 2, 1, 2, 1}, 3, 0},            // k > distinct elements available
                {new int[]{1, 2}, 1, 2},                     // Minimal array
                {new int[]{2, 1, 2, 1, 2}, 2, 10},           // Similar to alternating case
                {new int[]{1, 2, 3, 1, 2, 3}, 2, 6},         // Multiple overlapping valid windows
                {new int[]{1, 2, 1, 2, 3, 4, 1, 2}, 4, 3}    // Hard: complex mix
        };

        int passed = 0;
        int failed = 0;

        System.out.println("Running Test Cases...\n" + "=".repeat(60));

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = solver.subarraysWithKDistinct(nums, k);
            boolean isMatch = (actual == expected);

            if (isMatch) {
                passed++;
            } else {
                failed++;
            }

            System.out.printf("Test Case %d:\n", i + 1);
            System.out.printf("  Input: nums = %s, k = %d\n", arrayToString(nums), k);
            System.out.printf("  Expected Output: %d\n", expected);
            System.out.printf("  Actual Output:   %d\n", actual);
            System.out.printf("  Result: %s\n", isMatch ? "PASSED ✅" : "FAILED ❌");
            System.out.println("-".repeat(60));
        }

        System.out.printf("\nFinal Result: %d Passed, %d Failed\n", passed, failed);
    }

    private static String arrayToString(int[] arr) {
        if (arr.length > 10) return "[...] (Length: " + arr.length + ")";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]).append(i == arr.length - 1 ? "" : ", ");
        }
        sb.append("]");
        return sb.toString();
    }
}