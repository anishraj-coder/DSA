package recursion;

import java.util.*;

/**
 * Problem: Combination Sum II
 *
 * Rules:
 * 1. Each number in candidates may only be used once in the combination.
 * 2. The solution set must not contain duplicate combinations.
 * 3. The result combinations should be sorted internally, and the list of
 *    combinations should be sorted to ensure consistent comparison.
 */
public class CombinationSumII {

    /**
     * Finds all unique combinations in candidates where the numbers sum to target.
     *
     * @param arr Array of integers
     * @param target     Target sum
     * @return List of unique combinations
     */
    public List<List<Integer>> findUniqueCombinations(int[] arr, int target) {
        int n=arr.length;
        if(target==arr[0]){
            List<Integer>temp=new ArrayList<>();
            temp.add(arr[0]);
            List<List<Integer>>ans=new ArrayList<>();
            ans.add(temp);
            return ans;
        }
        Arrays.sort(arr);
        List<List<Integer>>ans=new ArrayList<>();
        List<Integer>curr=new ArrayList<>();
        helper(arr,0,target,curr,ans);
        return ans;
    }

    private void helper(int[]arr,int idx,int target,List<Integer>curr,List<List<Integer>>ans){
        int n=arr.length;
        if(target==0){
            ans.add(new ArrayList<>(curr));
            return ;
        }
        for(int i=idx;i<n;i++){
            if(i>idx&&arr[i]==arr[i-1])continue;
            if(arr[i]>target)break;
            curr.add(arr[i]);
            helper(arr,i+1,target-arr[i],curr,ans);
            curr.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationSumII solver = new CombinationSumII();
        int passed = 0;
        int total = 10;

        // Test Cases Definitions
        int[][] candidatesList = {
                {10, 1, 2, 7, 6, 1, 5},     // Case 1: Standard case with duplicates
                {2, 5, 2, 1, 2},           // Case 2: Small set, multiple identical numbers
                {1, 1},                    // Case 3: Target equals sum of all
                {2},                       // Case 4: Single element, target mismatch
                {1, 2, 3},                 // Case 5: Target impossible (too high)
                {1, 2, 3},                 // Case 6: Target impossible (no combination)
                {1, 1, 1, 1, 1},           // Case 7: All identical elements
                {3, 1, 3, 5, 1, 1},        // Case 8: Mixed duplicates
                {1, 2, 5, 6, 7, 10},       // Case 9: Large distinct values
                {1, 1, 2, 2}               // Case 10: Multiple ways to form target
        };

        int[] targets = {8, 5, 2, 1, 7, 4, 3, 5, 8, 4};

        List<List<List<Integer>>> expectedResults = new ArrayList<>();
        expectedResults.add(Arrays.asList(Arrays.asList(1, 1, 6), Arrays.asList(1, 2, 5), Arrays.asList(1, 7), Arrays.asList(2, 6)));
        expectedResults.add(Arrays.asList(Arrays.asList(1, 2, 2), Arrays.asList(5)));
        expectedResults.add(Arrays.asList(Arrays.asList(1, 1)));
        expectedResults.add(new ArrayList<>());
        expectedResults.add(new ArrayList<>());
        expectedResults.add(new ArrayList<>());
        expectedResults.add(Arrays.asList(Arrays.asList(1, 1, 1)));
        expectedResults.add(Arrays.asList(Arrays.asList(1, 1, 3), Arrays.asList(1, 1, 1, 2 /*not in input, adjust:*/).equals(null) ? null : Arrays.asList(1, 1, 3), Arrays.asList(5))); // Refined below

        // Correcting Expected for Case 8: {3, 1, 3, 5, 1, 1}, Target 5
        expectedResults.set(7, Arrays.asList(Arrays.asList(1, 1, 3), Arrays.asList(5)));

        // Case 9: {1, 2, 5, 6, 7, 10}, Target 8
        expectedResults.add(Arrays.asList(Arrays.asList(1, 2, 5), Arrays.asList(1, 7), Arrays.asList(2, 6)));

        // Case 10: {1, 1, 2, 2}, Target 4
        expectedResults.add(Arrays.asList(Arrays.asList(1, 1, 2), Arrays.asList(2, 2)));

        // Execution
        for (int i = 0; i < total; i++) {
            List<List<Integer>> actual = solver.findUniqueCombinations(candidatesList[i], targets[i]);

            // Sort for comparison
            sortResults(actual);
            List<List<Integer>> expected = expectedResults.get(i);
            sortResults(expected);

            boolean isMatch = actual.equals(expected);
            if (isMatch) passed++;

            System.out.println("--- Test Case " + (i + 1) + " ---");
            System.out.println("Candidates: " + Arrays.toString(candidatesList[i]) + " | Target: " + targets[i]);
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
            System.out.println("Result:   " + (isMatch ? "PASSED ✅" : "FAILED ❌"));
            System.out.println();
        }

        System.out.println("---------------------------------------");
        System.out.println("Final Score: " + passed + "/" + total);
        System.out.println("---------------------------------------");
    }

    /**
     * Utility to sort the list of lists for consistent comparison
     */
    private static void sortResults(List<List<Integer>> result) {
        for (List<Integer> list : result) {
            Collections.sort(list);
        }
        result.sort((a, b) -> {
            int len = Math.min(a.size(), b.size());
            for (int i = 0; i < len; i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i) - b.get(i);
                }
            }
            return a.size() - b.size();
        });
    }
}