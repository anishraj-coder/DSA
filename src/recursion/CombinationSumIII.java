package recursion;

import java.util.*;

/**
 * Problem: Combination Sum III
 * Restrictions: Numbers 1-9 only, each used at most once.
 */
public class CombinationSumIII {

    /**
     * Finds all valid combinations of k numbers that sum up to n.
     *
     * @param k - Number of elements in the combination
     * @param n - Target sum
     * @return List of all valid combinations
     */
    public List<List<Integer>> findCombinations(int k, int n) {
        int[]arr=new int[]{1,2,3,4,5,6,7,8,9};
        List<List<Integer>>ans=new ArrayList<>();
        helper(arr,0,k,n,new ArrayList<>(),ans);
        return ans;
    }

    private void helper(int[]arr,int idx,int k,int target,List<Integer>curr,List<List<Integer>>ans){
        if(curr.size()==k){
            if(target==0)ans.add(new ArrayList<>(curr));
            return;
        }
        for(int i=idx;i<arr.length;i++){
            if(i>idx&&arr[i]==arr[i-1])continue;
            if(arr[i]>target)break;
            curr.add(arr[i]);
            helper(arr,i+1,k,target-arr[i],curr,ans);
            curr.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationSumIII solver = new CombinationSumIII();

        // Test Cases: {k, n, Expected Output}
        Object[][] testCases = {
                {3, 7, List.of(List.of(1, 2, 4))},
                {3, 9, List.of(List.of(1, 2, 6), List.of(1, 3, 5), List.of(2, 3, 4))},
                {4, 1, Collections.emptyList()},
                {2, 18, Collections.emptyList()}, // Max sum for 2 nums is 9+8=17
                {9, 45, List.of(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9))}, // Sum of 1-9 is exactly 45
                {2, 5, List.of(List.of(1, 4), List.of(2, 3))},
                {3, 2, Collections.emptyList()}, // n < min possible sum (1+2+3=6)
                {9, 46, Collections.emptyList()}, // n > max possible sum (45)
                {2, 17, List.of(List.of(8, 9))},
                {3, 15, List.of(
                        List.of(1, 5, 9), List.of(1, 6, 8), List.of(2, 4, 9),
                        List.of(2, 5, 8), List.of(2, 6, 7), List.of(3, 4, 8), List.of(3, 5, 7), List.of(4, 5, 6)
                )}
        };

        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int k = (int) testCases[i][0];
            int n = (int) testCases[i][1];
            List<List<Integer>> expected = (List<List<Integer>>) testCases[i][2];

            List<List<Integer>> actual = solver.findCombinations(k, n);

            // Sort inner and outer lists for consistent comparison
            List<List<Integer>> processedActual = sortResult(actual);
            List<List<Integer>> processedExpected = sortResult(expected);

            boolean isMatch = processedActual.equals(processedExpected);
            if (isMatch) passed++;

            System.out.println("Test Case " + (i + 1) + ": k=" + k + ", n=" + n);
            System.out.println("Expected: " + processedExpected);
            System.out.println("Actual:   " + processedActual);
            System.out.println("Status:   " + (isMatch ? "PASSED ✅" : "FAILED ❌"));
            System.out.println("---------------------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + testCases.length + " tests passed.");
    }

    private static List<List<Integer>> sortResult(List<List<Integer>> input) {
        if (input == null) return new ArrayList<>();
        List<List<Integer>> sortedOuter = new ArrayList<>();
        for (List<Integer> inner : input) {
            List<Integer> sortedInner = new ArrayList<>(inner);
            Collections.sort(sortedInner);
            sortedOuter.add(sortedInner);
        }
        sortedOuter.sort((a, b) -> {
            for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
                if (!a.get(i).equals(b.get(i))) return a.get(i) - b.get(i);
            }
            return a.size() - b.size();
        });
        return sortedOuter;
    }
}