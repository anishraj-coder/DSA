package heap;

import java.util.*;
import java.util.stream.Collectors;

public class KClosestElements {

    /**
     * Your task is to implement this method.
     * Given a sorted integer array arr, two integers k and x,
     * return the k closest integers to x in the array sorted in ascending order.
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        int n=arr.length;

        if(k>=n)return Arrays.stream(arr).boxed().collect(Collectors.toList());

        int left=0,right=n-1;
        while(right-left+1>k){
            int d1=Math.abs(arr[left]-x),d2=Math.abs(x-arr[right]);
            if(d1>d2)left++;
            else right--;
        }

        List<Integer>ans=new ArrayList<>();
        for(int i=left;i<=right;i++)ans.add(arr[i]);

        return ans;
    }

    public static void main(String[] args) {
        KClosestElements solver = new KClosestElements();
        int totalPassed = 0;
        int totalTests = 11;

        // --- TEST CASES ---

        // Test 1: Example 1
        int[] arr1 = {1, 2, 3, 4, 5};
        int k1 = 4, x1 = 3;
        List<Integer> e1 = Arrays.asList(1, 2, 3, 4);

        // Test 2: Example 2 (x is smaller than all elements)
        int[] arr2 = {1, 1, 2, 3, 4, 5};
        int k2 = 4, x2 = -1;
        List<Integer> e2 = Arrays.asList(1, 1, 2, 3);

        // Test 3: x is larger than all elements
        int[] arr3 = {1, 2, 3, 4, 5};
        int k3 = 3, x3 = 10;
        List<Integer> e3 = Arrays.asList(3, 4, 5);

        // Test 4: Tie-breaker condition (|a - x| == |b - x|, prefer smaller element)
        int[] arr4 = {1, 10, 15, 20, 30};
        int k4 = 2, x4 = 15;
        // |10-15|=5, |20-15|=5. Both are equidistant, 10 is smaller.
        List<Integer> e4 = Arrays.asList(10, 15);

        // Test 5: k equals the length of the array
        int[] arr5 = {2, 4, 6, 8};
        int k5 = 4, x5 = 5;
        List<Integer> e5 = Arrays.asList(2, 4, 6, 8);

        // Test 6: Array with massive duplicate blocks
        int[] arr6 = {1, 1, 1, 2, 2, 2, 2, 3, 3, 3};
        int k6 = 5, x6 = 2;
        List<Integer> e6 = Arrays.asList(2, 2, 2, 2, 2); // Handles identical elements correctly

        // Test 7: Single element array
        int[] arr7 = {10};
        int k7 = 1, x7 = 5;
        List<Integer> e7 = Arrays.asList(10);

        // Test 8: Negative values included in the array
        int[] arr8 = {-10, -5, -2, 0, 3, 7};
        int k8 = 3, x8 = -3;
        // Distances to -3: -2 (1), -5 (2), 0 (3), -10 (7), 3 (6)...
        List<Integer> e8 = Arrays.asList(-5, -2, 0);

        // Test 9: Two equidistant elements flanking x, but k only takes one
        int[] arr9 = {10, 20};
        int k9 = 1, x9 = 15;
        // Both 10 and 20 are distance 5 away. Prefer smaller.
        List<Integer> e9 = Arrays.asList(10);

        // Test 10: Strict boundaries with perfect tie breaks across a wider array
        int[] arr10 = {1, 2, 3, 10, 11, 12, 13};
        int k10 = 4, x10 = 7;
        // Distances to 7: 3 (4), 10 (3), 2 (5), 11 (4), 1 (6), 12 (5).
        // Sorted by distance/value priority: 10, 3, 11, 2.
        // Ascending result: [2, 3, 10, 11]
        List<Integer> e10 = Arrays.asList(2, 3, 10, 11);

        // Test 11: Alternating values with x perfectly centered
        int[] arr11 = {10, 10, 15, 20, 20};
        int k11 = 3, x11 = 15;
        List<Integer> e11 = Arrays.asList(10, 10, 15);


        // --- AUTOMATED EVALUATION ---

        int[][] allArrays = {arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10, arr11};
        int[] allK = {k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11};
        int[] allX = {x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11};
        List<List<Integer>> allExpected = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11);

        for (int i = 0; i < totalTests; i++) {
            System.out.println("----------------------------------------");
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: arr = " + Arrays.toString(allArrays[i]) + ", k = " + allK[i] + ", x = " + allX[i]);

            // Execute user implementation
            List<Integer> actual = solver.findClosestElements(allArrays[i].clone(), allK[i], allX[i]);

            System.out.println("Expected Output: " + allExpected.get(i));
            System.out.println("Actual Output:   " + actual);

            if (allExpected.get(i).equals(actual)) {
                System.out.println("Result:          PASSED");
                totalPassed++;
            } else {
                System.out.println("Result:          FAILED");
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("Final Verdict: " + totalPassed + " / " + totalTests + " Test Cases Passed.");
    }
}