package backtracking;

import java.util.*;

/**
 * Problem: Count the number of subsets that sum up to a target value K.
 * Instructions: Implement the countSubsets method below.
 */
public class SubsetSumCounter {

    /**
     * Finds the number of subsets in the given array that sum up to k.
     *  @param arr The input array of integers.
     * @param k    The target sum.
     * @return     The total number of subsets with sum equal to k.
     */
    private static int ans;
    public int countSubsets(int[] arr, int k) {
        ans=0;
        count(arr,0,0,k);
        return ans;
    }

    public void count(int[] arr,int i,int sum,int k){
        if(i==arr.length){
            if(sum==k)ans++;
            return ;
        }
        count(arr,i+1,sum+arr[i],k);
        count(arr,i+1,sum,k);
    }

    public static void main(String[] args) {
        SubsetSumCounter solver = new SubsetSumCounter();
        int passed = 0;
        int total = 10;

        // Test Case 1: Standard case
        passed += runTest(1, solver, new int[]{1, 2, 3, 3}, 6, 3); // {1,2,3}, {1,2,3}, {3,3}

        // Test Case 2: Target is zero (The empty set {} always sums to 0)
        passed += runTest(2, solver, new int[]{1, 2, 3}, 0, 1);

        // Test Case 3: Elements larger than target
        passed += runTest(3, solver, new int[]{10, 20, 30}, 5, 0);

        // Test Case 4: Array containing zeros (Zeros double the subset possibilities)
        passed += runTest(4, solver, new int[]{0, 0, 1}, 1, 4); // {1}, {0,1}, {0,1}, {0,0,1}

        // Test Case 5: Single element matching target
        passed += runTest(5, solver, new int[]{5}, 5, 1);

        // Test Case 6: Single element not matching target
        passed += runTest(6, solver, new int[]{3}, 5, 0);

        // Test Case 7: All elements are the same
        passed += runTest(7, solver, new int[]{2, 2, 2, 2}, 4, 6); // 4C2 combinations

        // Test Case 8: Large target sum not possible
        passed += runTest(8, solver, new int[]{1, 1, 1}, 10, 0);

        // Test Case 9: Large array with multiple solutions
        passed += runTest(9, solver, new int[]{1, 2, 1, 2, 1}, 3, 6);
        // Subsets: {1,2}, {1,2}, {1,2}, {1,2}, {2,1}, {2,1} (indices based)

        // Test Case 10: Empty array
        passed += runTest(10, solver, new int[]{}, 10, 0);

        System.out.println("\n-------------------------------------------");
        System.out.println("Final Result: " + passed + "/" + total + " Tests Passed");
        System.out.println("-------------------------------------------");
    }

    private static int runTest(int id, SubsetSumCounter solver, int[] nums, int k, int expected) {
        int actual = solver.countSubsets(nums, k);
        boolean isPassed = (actual == expected);

        System.out.printf("Test Case %d: ", id);
        System.out.printf("Input: %s, K: %d | ", Arrays.toString(nums), k);
        System.out.printf("Expected: %d, Actual: %d | ", expected, actual);

        if (isPassed) {
            System.out.println("RESULT: PASSED ✅");
            return 1;
        } else {
            System.out.println("RESULT: FAILED ❌");
            return 0;
        }
    }
}