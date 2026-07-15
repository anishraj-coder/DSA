package arrays;

import java.util.*;

public class SubarraysWithXorK {

    /**
     * Finds the total number of subarrays whose XOR sum equals k.
     * * @param arr The input array of integers.
     * @param k    The target XOR value.
     * @return     The count of subarrays with XOR equal to k.
     */
    public int countSubarrays(int[] arr, int k) {
        int n=arr.length;
        if(n==1)return (arr[0]==k)?1:0;
        Map<Integer,Integer>map=new HashMap<>();
        map.put(0,1);
        int xr=0,count=0;
        for(int i=0;i<n;i++){
            xr^=arr[i];
            int x=xr^k;
            count+=map.getOrDefault(x,0);
            map.put(xr,map.getOrDefault(xr,0)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        SubarraysWithXorK solver = new SubarraysWithXorK();

        Object[][] testCases = {
                {new int[]{4, 2, 2, 6, 4}, 6, 4, "Standard case from example 1"},
                {new int[]{5, 6, 7, 8, 9}, 5, 2, "Standard case from example 2"},
                {new int[]{1, 2, 3, 2}, 2, 3, "Overlapping subarrays"},
                {new int[]{0, 0, 0}, 0, 6, "All zeros, target zero (all combinations)"},
                {new int[]{1, 1, 1}, 1, 4, "Alternating XORs"},
                {new int[]{10, 1, 0, 3, 4, 7}, 11, 3, "Large values and zero inclusion"},
                {new int[]{7}, 7, 1, "Single element matching k"},
                {new int[]{7}, 5, 0, "Single element not matching k"},
                {new int[]{0, 5, 0, 5, 0}, 5, 9, "Zero padding around targets"},
                {new int[]{1, 2, 4, 8, 16}, 31, 1, "Full array XOR matches k"}
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int expected = (int) testCases[i][2];
            String description = (String) testCases[i][3];

            int actual = solver.countSubarrays(nums, k);

            System.out.println("Test Case " + (i + 1) + ": " + description);
            System.out.println("Input: nums = " + Arrays.toString(nums) + ", k = " + k);
            System.out.println("Expected: " + expected + ", Actual: " + actual);

            if (actual == expected) {
                System.out.println("Result: PASSED");
                passed++;
            } else {
                System.out.println("Result: FAILED");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Score: " + passed + "/" + testCases.length);
    }
}