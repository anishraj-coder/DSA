package arrays;

import java.util.*;

/**
 * Problem: Subsets (Power Set)
 * Given an integer array of unique elements, return all possible subsets.
 * The solution must not contain duplicate subsets.
 */
public class PowerSet {

    /**
     * Generates all possible subsets for the given unique integer array.
     *
     * @param arr Array of unique integers.
     * @return A list of lists representing the power set.
     */
    public List<List<Integer>> subsets(int[] arr) {
        int n=arr.length;
        if(n==0)return new ArrayList<>();
        List<Integer>curr=new ArrayList<>();
        List<List<Integer>>ans=new ArrayList<>();
        helper(arr,0,curr,ans);
        return ans;
    }

    public void helper(int[]arr,int i,List<Integer>curr,List<List<Integer>>ans){
        int n=arr.length;
        if(i==n){
            ans.add(new ArrayList<>(curr));
            return;
        }
        curr.add(arr[i]);
        helper(arr,i+1,curr,ans);
        curr.removeLast();
        helper(arr,i+1,curr,ans);
    }

    public static void main(String[] args) {
        PowerSet instance = new PowerSet();

        // Test Case Definitions
        Object[][] testCases = {
                { new int[]{1, 2, 3}, 8 },
                { new int[]{0}, 2 },
                { new int[]{}, 1 }, // Edge case: Empty input
                { new int[]{9, 10}, 4 },
                { new int[]{-1, 1, 2}, 8 }, // Negative numbers
                { new int[]{5, -5, 0}, 8 }, // Including zero
                { new int[]{1, 2, 3, 4}, 16 },
                { new int[]{-10, 10}, 4 }, // Constraint boundaries
                { new int[]{7, 8, 9, 10, 11}, 32 }, // Larger set
                { new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 1024 } // Max constraint
        };

        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int expectedSize = (int) testCases[i][1];

            List<List<Integer>> result = instance.subsets(nums);
            boolean isValid = validateSubsets(nums, result, expectedSize);

            System.out.println("Test Case " + (i + 1) + ": Input = " + Arrays.toString(nums));
            System.out.println("Expected Size: " + expectedSize);
            System.out.println("Actual Size:   " + (result == null ? "null" : result.size()));

            if (isValid) {
                System.out.println("Result:        PASSED");
                passed++;
            } else {
                System.out.println("Result:        FAILED (Check for duplicates, missing sets, or wrong size)");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Score: " + passed + "/" + testCases.length);
    }

    /**
     * Validates if the returned result is a valid Power Set.
     */
    private static boolean validateSubsets(int[] nums, List<List<Integer>> result, int expectedSize) {
        if (result == null || result.size() != expectedSize) return false;

        Set<Set<Integer>> uniqueSubsets = new HashSet<>();
        for (List<Integer> list : result) {
            // Sort to ensure [1,2] is treated same as [2,1] for validation
            // though input is unique and logic should handle order
            Set<Integer> set = new HashSet<>(list);
            if (list.size() != set.size()) return false; // Contains duplicates within a subset

            // Check if subset contains elements not in original nums
            for (int val : list) {
                boolean found = false;
                for (int n : nums) if (n == val) { found = true; break; }
                if (!found) return false;
            }
            uniqueSubsets.add(set);
        }

        return uniqueSubsets.size() == expectedSize;
    }
}