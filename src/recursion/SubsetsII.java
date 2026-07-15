package recursion;

import java.util.*;

class SubsetsII {

    /**
     * Given an integer array nums that may contain duplicates,
     * return all possible subsets (the power set).
     * The solution set must not contain duplicate subsets.
     */
    public List<List<Integer>> subsetsWithDup(int[] arr) {
        Arrays.sort(arr);
        List<Integer>curr=new ArrayList<>();
        List<List<Integer>>ans=new ArrayList<>();
        helper(arr,0,curr,ans);
        return ans;
    }

    private void helper(int[]arr,int idx,List<Integer>curr,List<List<Integer>>ans){
        ans.add(new ArrayList<>(curr));

        for(int i=idx;i<arr.length;i++){
            if(i>idx&&arr[i]==arr[i-1])continue;
            curr.add(arr[i]);
            helper(arr,i+1,curr,ans);
            curr.removeLast();
        }

    }

    public static void main(String[] args) {
        SubsetsII solver = new SubsetsII();

        // Test Cases Definitions
        Object[][] testCases = {
                {new int[]{1, 2, 2}, "[[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]"},
                {new int[]{0}, "[[], [0]]"},
                {new int[]{4, 4, 4, 1}, "[[], [1], [1, 4], [1, 4, 4], [1, 4, 4, 4], [4], [4, 4], [4, 4, 4]]"},
                {new int[]{-1, 1, 2}, "[[], [-1], [-1, 1], [-1, 1, 2], [-1, 2], [1], [1, 2], [2]]"},
                {new int[]{1, 1}, "[[], [1], [1, 1]]"},
                {new int[]{}, "[[]]"},
                {new int[]{10, -10}, "[[], [-10], [-10, 10], [10]]"},
                {new int[]{5, 5, 5}, "[[], [5], [5, 5], [5, 5, 5]]"},
                {new int[]{1, 2, 3}, "[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]"},
                {new int[]{2, 1, 2}, "[[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]"} // Duplicate handling with unsorted input
        };

        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            String expectedStr = (String) testCases[i][1];

            List<List<Integer>> actualResult = solver.subsetsWithDup(input);
            String actualStr = formatResult(actualResult);

            boolean isCorrect = compareSubsets(actualStr, expectedStr);

            System.out.println("Test Case " + (i + 1) + ": Input = " + Arrays.toString(input));
            System.out.println("Expected: " + expectedStr);
            System.out.println("Actual:   " + actualStr);

            if (isCorrect) {
                System.out.println("Result:   PASSED");
                passed++;
            } else {
                System.out.println("Result:   FAILED");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Score: " + passed + "/" + testCases.length);
    }

    /**
     * Helper method to format the list of lists into a consistent string for comparison.
     */
    private static String formatResult(List<List<Integer>> result) {
        if (result == null) return "null";
        for (List<Integer> list : result) {
            Collections.sort(list);
        }
        result.sort((a, b) -> {
            int sizeComp = Integer.compare(a.size(), b.size());
            if (sizeComp != 0) return sizeComp;
            for (int i = 0; i < a.size(); i++) {
                int valComp = Integer.compare(a.get(i), b.get(i));
                if (valComp != 0) return valComp;
            }
            return 0;
        });
        return result.toString();
    }

    /**
     * Compares strings regardless of specific internal order,
     * though formatResult handles sorting to make it reliable.
     */
    private static boolean compareSubsets(String actual, String expected) {
        return actual.equals(expected);
    }
}