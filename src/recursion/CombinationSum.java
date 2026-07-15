package recursion;

import java.util.*;

/**
 * Problem: Combination Sum
 * Goal: Find all unique combinations of distinct integers that sum up to a target.
 * Candidates can be used an unlimited number of times.
 */
public class CombinationSum {

    /**
     * Finds all unique combinations in candidates that sum to target.
     *
     * @param arr An array of distinct integers.
     * @param target     The integer sum to achieve.
     * @return           A list of lists containing the valid combinations.
     */
    public List<List<Integer>> combine(int[] arr, int target) {
        List<Integer>curr=new ArrayList<>();
        List<List<Integer>>ans=new ArrayList<>();
        helper(arr,0,target,0,curr,ans);
        return ans;
    }

    private void helper(int[]arr,int i,int target,int sum,List<Integer>curr,List<List<Integer>>ans){
        int n=arr.length;
        if(sum==target){
            ans.add(new ArrayList<>(curr));
            return;
        }
        if(n==i||target<sum)return;

        if(sum+arr[i]<=target){
            sum+=arr[i];
            curr.add(arr[i]);
            helper(arr,i,target,sum,curr,ans);
            sum-=arr[i];
            curr.removeLast();
        }
        helper(arr,i+1,target,sum,curr,ans);
    }

    public static void main(String[] args) {
        CombinationSum solver = new CombinationSum();
        int passed = 0;
        int totalTests = 10;

        // Test Case 1: Standard case (Example 1)
        passed += runTest(1, solver, new int[]{2, 3, 6, 7}, 7,
                Arrays.asList(Arrays.asList(2, 2, 3), Arrays.asList(7)));

        // Test Case 2: Multiple combinations (Example 2)
        passed += runTest(2, solver, new int[]{2, 3, 5}, 8,
                Arrays.asList(Arrays.asList(2, 2, 2, 2), Arrays.asList(2, 3, 3), Arrays.asList(3, 5)));

        // Test Case 3: Target smaller than any candidate (Example 3)
        passed += runTest(3, solver, new int[]{2}, 1,
                Collections.emptyList());

        // Test Case 4: Target is exactly one candidate
        passed += runTest(4, solver, new int[]{5, 10, 15}, 5,
                Arrays.asList(Arrays.asList(5)));

        // Test Case 5: Large target with small candidate (Deep recursion)
        passed += runTest(5, solver, new int[]{10}, 40,
                Arrays.asList(Arrays.asList(10, 10, 10, 10)));

        // Test Case 6: Candidates with no possible combination
        passed += runTest(6, solver, new int[]{3, 5}, 7,
                Collections.emptyList());

        // Test Case 7: All candidates are factors of target
        passed += runTest(7, solver, new int[]{2, 4}, 6,
                Arrays.asList(Arrays.asList(2, 2, 2), Arrays.asList(2, 4)));

        // Test Case 8: Large array, target reachable by many single elements
        passed += runTest(8, solver, new int[]{1, 2}, 3,
                Arrays.asList(Arrays.asList(1, 1, 1), Arrays.asList(1, 2)));

        // Test Case 9: Max constraint values
        passed += runTest(9, solver, new int[]{40}, 40,
                Arrays.asList(Arrays.asList(40)));

        // Test Case 10: Pruning test (Target reached via various paths)
        passed += runTest(10, solver, new int[]{2, 3}, 5,
                Arrays.asList(Arrays.asList(2, 3)));

        System.out.println("\n-------------------------------------------");
        System.out.println("Final Result: " + passed + "/" + totalTests + " tests passed.");
        System.out.println("-------------------------------------------");
    }

    private static int runTest(int id, CombinationSum solver, int[] candidates, int target, List<List<Integer>> expected) {
        List<List<Integer>> actual = solver.combine(candidates, target);

        // Sort both for comparison because the order of combinations/elements doesn't matter in the problem
        List<List<Integer>> sortedActual = sortResult(actual);
        List<List<Integer>> sortedExpected = sortResult(expected);

        boolean isMatch = sortedActual.equals(sortedExpected);

        System.out.println("Test Case " + id + ": " + (isMatch ? "PASSED" : "FAILED"));
        System.out.println("   Input: candidates=" + Arrays.toString(candidates) + ", target=" + target);
        System.out.println("   Expected: " + sortedExpected);
        System.out.println("   Actual:   " + sortedActual);
        System.out.println();

        return isMatch ? 1 : 0;
    }

    private static List<List<Integer>> sortResult(List<List<Integer>> result) {
        if (result == null) return new ArrayList<>();
        List<List<Integer>> sortedList = new ArrayList<>();
        for (List<Integer> list : result) {
            List<Integer> copy = new ArrayList<>(list);
            Collections.sort(copy);
            sortedList.add(copy);
        }
        sortedList.sort((a, b) -> {
            int size = Math.min(a.size(), b.size());
            for (int i = 0; i < size; i++) {
                if (!a.get(i).equals(b.get(i))) return a.get(i) - b.get(i);
            }
            return a.size() - b.size();
        });
        return sortedList;
    }
}