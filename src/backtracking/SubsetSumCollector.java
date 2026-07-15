package backtracking;

import java.util.*;

/**
 * Problem: Find and return all subsets that sum up to a target value K.
 * Instructions: Implement the findSubsets method below.
 */
public class SubsetSumCollector {

    /**
     * Finds all unique subsets (by indices) in the given array that sum up to k.
     * @param arr The input array of integers.
     * @param k    The target sum.
     * @return     A list of lists, where each inner list is a subset that sums to k.
     */
    public List<List<Integer>> findSubsets(int[] arr, int k) {
        List<List<Integer>>ans=new ArrayList<>();
        List<Integer>current=new ArrayList<>();
        checkSubset(arr,0,0,k,current,ans);
        return ans;
    }

    private void checkSubset(int[]arr,int i,int sum,int k,List<Integer>curr,List<List<Integer>>ans){
        if(i==arr.length){
            if(sum==k)ans.add(List.copyOf(curr));
            return;
        }
        curr.add(arr[i]);
        checkSubset(arr,i+1,sum+arr[i],k,curr,ans);
        curr.removeLast();
        checkSubset(arr,i+1,sum,k,curr,ans);
    }

    public static void main(String[] args) {
        SubsetSumCollector solver = new SubsetSumCollector();
        int passed = 0;
        int total = 10;

        // Test Case 1: Standard case
        passed += runTest(1, solver, new int[]{1, 2, 3}, 3,
                List.of(List.of(1, 2), List.of(3)));

        // Test Case 2: Target is zero (The empty set [] sums to 0)
        passed += runTest(2, solver, new int[]{1, 2, 3}, 0,
                List.of(List.of()));

        // Test Case 3: Elements larger than target
        passed += runTest(3, solver, new int[]{10, 20}, 5,
                List.of());

        // Test Case 4: Array containing zeros (Each zero doubles the combinations)
        passed += runTest(4, solver, new int[]{0, 1}, 1,
                List.of(List.of(1), List.of(0, 1)));

        // Test Case 5: Single element matching target
        passed += runTest(5, solver, new int[]{5}, 5,
                List.of(List.of(5)));

        // Test Case 6: Multiple subsets with same values
        passed += runTest(6, solver, new int[]{2, 2, 3}, 4,
                List.of(List.of(2, 2)));

        // Test Case 7: All elements needed
        passed += runTest(7, solver, new int[]{1, 1, 1}, 3,
                List.of(List.of(1, 1, 1)));

        // Test Case 8: Large target sum impossible
        passed += runTest(8, solver, new int[]{1, 2, 3}, 10,
                List.of());

        // Test Case 9: Duplicate values forming different subsets (by index)
        // If the logic handles duplicate values as distinct items
        passed += runTest(9, solver, new int[]{1, 2, 1}, 2,
                List.of(List.of(1, 1), List.of(2)));

        // Test Case 10: Empty array
        passed += runTest(10, solver, new int[]{}, 5,
                List.of());

        System.out.println("\n-------------------------------------------");
        System.out.println("Final Result: " + passed + "/" + total + " Tests Passed");
        System.out.println("-------------------------------------------");
    }

    private static int runTest(int id, SubsetSumCollector solver, int[] nums, int k, List<List<Integer>> expected) {
        List<List<Integer>> actual = solver.findSubsets(nums, k);

        // Sorting both lists to ensure comparison is content-based and not order-based
        List<List<Integer>> sortedActual = sortSubsets(actual);
        List<List<Integer>> sortedExpected = sortSubsets(expected);

        boolean isPassed = sortedActual.equals(sortedExpected);

        System.out.printf("Test Case %d: ", id);
        System.out.printf("Input: %s, K: %d | ", Arrays.toString(nums), k);
        System.out.printf("Expected Count: %d, Actual Count: %d | ", expected.size(), actual.size());

        if (isPassed) {
            System.out.println("RESULT: PASSED ✅");
            return 1;
        } else {
            System.out.println("RESULT: FAILED ❌");
            System.out.println("   Expected: " + sortedExpected);
            System.out.println("   Actual:   " + sortedActual);
            return 0;
        }
    }

    private static List<List<Integer>> sortSubsets(List<List<Integer>> subsets) {
        List<List<Integer>> copy = new ArrayList<>();
        for (List<Integer> subset : subsets) {
            List<Integer> sortedSubset = new ArrayList<>(subset);
            Collections.sort(sortedSubset);
            copy.add(sortedSubset);
        }
        copy.sort((a, b) -> {
            int len = Math.min(a.size(), b.size());
            for (int i = 0; i < len; i++) {
                if (!a.get(i).equals(b.get(i))) return a.get(i) - b.get(i);
            }
            return a.size() - b.size();
        });
        return copy;
    }
}