package arrays;

import java.util.HashMap;
import java.util.Map;

public class NumberSubarraySumEqualK {

    /**
     * Given an array of integers arr and an integer k,
     * return the total number of subarrays whose sum equals to k.
     */
    public int subarraySum(int[] arr, int k) {
        int n=arr.length;
        if(n==1){
            if(arr[0]==k)return 1;
            else return 0;
        }

        int sum=0,count=0;
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,1);
        for(int i=0;i<n;i++){
            sum+=arr[i];
            int diff=sum-k;
            count+=map.getOrDefault(diff,0);
            map.put(sum,map.getOrDefault(sum,0)+1);
        }

        return count;
    }

    public static void main(String[] args) {
        NumberSubarraySumEqualK solver = new NumberSubarraySumEqualK();

        // Test Cases
        int[][] inputs = {
                {1, 1, 1},          // Case 1: Simple repeats
                {1, 2, 3},          // Case 2: Incremental
                {1, -1, 0},         // Case 3: Zeroes and Negatives
                {3, 4, 7, 2, -3, 1, 4, 2}, // Case 4: Long array
                {0, 0, 0, 0, 0},    // Case 5: All zeroes
                {1},                // Case 6: Single element (Match)
                {-1},               // Case 7: Single element (No match)
                {1, 2, 1, 2, 1},    // Case 8: Multiple overlapping
                {10, 2, -2, -20, 10}, // Case 9: Large negatives
                {-1, -1, 1}         // Case 10: Negative sum targets
        };

        int[] ks = {2, 3, 0, 7, 0, 1, 0, 3, -10, 0};

        int[] expected = {2, 2, 3, 4, 15, 1, 0, 4, 3, 1};

        for (int i = 0; i < inputs.length; i++) {
            int actual = solver.subarraySum(inputs[i], ks[i]);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input:    nums = " + java.util.Arrays.toString(inputs[i]) + ", k = " + ks[i]);
            System.out.println("Expected: " + expected[i]);
            System.out.println("Actual:   " + actual);

            if (actual == expected[i]) {
                System.out.println("Result:   PASSED ✅");
            } else {
                System.out.println("Result:   FAILED ❌");
            }
            System.out.println("--------------------------------------------------");
        }
    }
}