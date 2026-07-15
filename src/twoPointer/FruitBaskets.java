package twoPointer;

import java.util.*;

/**
 * Problem: Fruit Into Baskets
 * Description: Find the maximum number of fruits you can collect in two baskets,
 * where each basket holds only one type of fruit and you must pick from every
 * tree moving right until you hit a third type.
 */
public class FruitBaskets {

    /**
     * Calculates the maximum number of fruits that can be picked.
     * * @param fruits An array of integers representing the fruit type of each tree.
     * @return The maximum number of fruits in the longest valid subarray.
     */
    public int totalFruit(int[] arr) {
        int i=0,j=0,max=0,n=arr.length,count=0;
        if(n<=2)return n;
        HashMap<Integer,Integer>map=new HashMap<>();
        while(j<n){
            int a=arr[j];
            map.put(a,map.getOrDefault(a,0)+1);

            if(map.size()>2){
                int rem=arr[i];
                map.put(rem,map.get(rem)-1);
                if(map.get(rem)==0)map.remove(rem);
                i++;
            }

            if(map.size()<=2){
                max=Math.max(j-i+1,max);
            }
            j++;
        }
        return max;
    }

    public static void main(String[] args) {
        FruitBaskets collector = new FruitBaskets();

        // Test Case Data: {int[] fruits, int expectedOutput}
        Object[][] testCases = {
                {new int[]{1, 2, 1}, 3},                // Standard: fits in two baskets
                {new int[]{0, 1, 2, 2}, 3},             // Shift start: [1, 2, 2]
                {new int[]{1, 2, 3, 2, 2}, 4},          // Multiple types: [2, 3, 2, 2]
                {new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}, 5}, // Hard: [1, 2, 1, 1, 2]
                {new int[]{1}, 1},                      // Edge: single tree
                {new int[]{1, 1, 1, 1}, 4},             // Edge: all same type
                {new int[]{1, 2}, 2},                   // Edge: exactly two types
                {new int[]{0, 0, 1, 1, 2, 2}, 4},       // Hard: multiple valid pairs of same length
                {new int[]{1, 0, 1, 4, 1, 4, 1, 2, 3}, 5}, // Hard: jumpy sequence [1, 4, 1, 4, 1]
                {new int[]{1, 2, 1, 2, 3}, 4},          // Standard: alternating types
                {new int[]{1, 2, 3, 4, 5, 6}, 2}        // Edge: all types unique
        };

        int passed = 0;
        int failed = 0;

        System.out.println("Running Tests for: FruitBaskets\n");
        System.out.printf("%-30s | %-10s | %-10s | %-10s%n", "Input Array", "Expected", "Actual", "Result");
        System.out.println("--------------------------------------------------------------------------");

        for (Object[] testCase : testCases) {
            int[] fruits = (int[]) testCase[0];
            int expected = (int) testCase[1];
            int actual = collector.totalFruit(fruits);

            String status = (actual == expected) ? "PASSED" : "FAILED";
            if (status.equals("PASSED")) passed++; else failed++;

            // Format array for printing
            String arrayStr = Arrays.toString(fruits);
            String displayInput = arrayStr.length() > 25 ? arrayStr.substring(0, 22) + "..." : arrayStr;

            System.out.printf("%-30s | %-10d | %-10d | %-10s%n", displayInput, expected, actual, status);
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("Total Tests: %d | Passed: %d | Failed: %d%n", testCases.length, passed, failed);
    }
}