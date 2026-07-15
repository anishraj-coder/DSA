package hashmap;

import java.util.*;

public class ContiguousArray {

    /**
     * Problem: Find the maximum length of a contiguous subarray with an equal number of 0 and 1.
     * Implement your solution in the method below.
     */
    public int findMaxLength(int[] arr) {
        int zero=0,one=0,n=arr.length,max=0;
        if(n==1)return 0;
        HashMap<Integer,Integer>map=new HashMap<>();
        map.put(0,-1);
        for(int i=0;i<n;i++){
            one+=(arr[i]==1)?1:0;
            zero+=(arr[i]==0)?1:0;
            int diff=one - zero;
            if(map.containsKey(diff)){
                int length=i-map.get(diff);
                max=Math.max(max,length);
            }
            if(!map.containsKey(diff))map.put(diff,i);
        }
        return max;
    }

    public static void main(String[] args) {
        ContiguousArray solver = new ContiguousArray();

        Object[][] testCases = {
                {new int[]{0, 1}, 2, "Simple case: one 0, one 1"},
                {new int[]{0, 1, 0}, 2, "Odd length array"},
                {new int[]{0, 1, 1, 1, 1, 1, 0, 0, 0}, 6, "Longer balanced subarray at the end"},
                {new int[]{0, 0, 0, 1, 1, 1}, 6, "Entire array is balanced"},
                {new int[]{0, 0, 1, 0, 0, 0, 1, 1}, 6, "Balanced section in the middle/end"},
                {new int[]{0}, 0, "Edge Case: Single element 0"},
                {new int[]{1}, 0, "Edge Case: Single element 1"},
                {new int[]{0, 0, 0, 0}, 0, "Edge Case: No 1s present"},
                {new int[]{1, 1, 1, 1}, 0, "Edge Case: No 0s present"},
                {new int[]{1, 0, 1, 0, 1, 0, 1, 0}, 8, "Perfectly alternating sequence"},
                {new int[]{0, 1, 1, 0, 1, 1, 1, 0, 0, 0}, 10, "Complex balanced structure"},
                {new int[]{1, 1, 1, 0, 0, 1, 1, 0, 0, 0}, 8, "Balanced subarray starts from index 2 to 9"}
        };

        int passed = 0;

        System.out.println("Running Tests for ContiguousArray...\n");
        System.out.printf("%-60s | %-10s | %-10s | %-10s%n", "Description", "Expected", "Actual", "Result");
        System.out.println("-------------------------------------------------------------------------------------------------------");

        for (Object[] testCase : testCases) {
            int[] nums = (int[]) testCase[0];
            int expected = (int) testCase[1];
            String description = (String) testCase[2];

            int actual = solver.findMaxLength(nums);
            boolean isCorrect = (actual == expected);

            if (isCorrect) passed++;

            System.out.printf("%-60s | %-10d | %-10d | %-10s%n",
                    description, expected, actual, isCorrect ? "PASSED" : "FAILED");
        }

        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("Total Passed: " + passed + "/" + testCases.length);
    }
}