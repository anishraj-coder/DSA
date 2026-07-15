package hashmap;

import java.util.*;

public class EqualDistributionSubarrays {

    /**
     * Problem: Subarrays With Equal 0’s, 1’s and 2’s
     * Find the count of non-empty subarrays containing an equal number of 0’s, 1’s and 2’s.
     * * Strategy: Use a HashMap to store the frequency of the "difference tuple"
     * (count0 - count1, count1 - count2).
     */
    public int countEqualSubarrays(int[] arr) {
        int one=0,zero=0,two=0,count=0,n=arr.length;
        HashMap<String,Integer> diff=new HashMap<>();
        diff.put("0@0",1);
        for(int i=0;i<n;i++){
            zero+=arr[i]==0?1:0;
            one+=arr[i]==1?1:0;
            two+=arr[i]==2?1:0;
            int diff1=one-zero;
            int diff2=one -two;
            String current=diff1+"@"+diff2;
            count+=diff.getOrDefault(current,0);
            diff.put(current,diff.getOrDefault(current,0)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        EqualDistributionSubarrays solver = new EqualDistributionSubarrays();

        // Test Cases: {int[] array, int expectedOutput}
        Object[][] testCases = {
                {new int[]{1, 1, 0, 2, 1}, 2},
                {new int[]{1, 1, 0, 0}, 0}, // Missing '2'
                {new int[]{1, 0, 2, 1, 0, 2}, 5},
                {new int[]{1, 1, 0, 0, 2, 2}, 1},
                // Edge Case: Single element
                {new int[]{0}, 0},
                // Edge Case: Smallest valid subarray
                {new int[]{0, 1, 2}, 1},
                // Edge Case: All same elements
                {new int[]{2, 2, 2, 2}, 0},
                // Edge Case: Multiple overlapping subarrays
                {new int[]{0, 1, 2, 0, 1, 2}, 5}, // [0,1,2], [0,1,2], [1,2,0], [2,0,1], [0..5]
                // Hard Case: Large array with no valid subarrays
                {new int[]{0, 0, 0, 1, 1, 1}, 0},
                // Hard Case: Interleaved values
                {new int[]{0, 1, 2, 2, 1, 0, 1, 0, 2}, 4}
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] arr = (int[]) testCases[i][0];
            int expected = (int) testCases[i][1];

            int actual = solver.countEqualSubarrays(arr);

            boolean isPassed = (actual == expected);
            if (isPassed) passed++;

            System.out.printf("Test Case %d: %s%n", i + 1, Arrays.toString(arr));
            System.out.printf("Expected: %d | Actual: %d%n", expected, actual);
            System.out.println("Result: " + (isPassed ? "✅ PASSED" : "❌ FAILED"));
            System.out.println("--------------------------------------------------");
        }

        System.out.printf("Final Score: %d/%d cases passed.%n", passed, testCases.length);
    }
}