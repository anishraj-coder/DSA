package recursion;

import java.util.*;

/**
 * Problem: Subsets I (Subset Sums)
 * Logic: Implement the 'calculateSubsetSums' method to return all possible sums of subsets.
 */
public class SubsetSums {

    /**
     * Computes the sum of all possible subsets of the given array.
     *
     * @param arr The input array of integers.
     * @return     A list containing the sums of all possible subsets.
     */
    public List<Integer> calculateSubsetSums(int[] arr) {
        List<Integer>ans=new ArrayList<>();
        helper(arr,0,0,ans);
        return ans;
    }

    private void helper(int[]arr,int i,int sum,List<Integer>ans){
        int n=arr.length;
        if(i==n){
            ans.add(sum);
            return;
        }
        helper(arr,i+1,sum+arr[i],ans);
        helper(arr,i+1,sum,ans);
    }

    public static void main(String[] args) {
        SubsetSums runner = new SubsetSums();

        // Test Case Definitions: {Input Array, Expected Output List}
        Object[][] testCases = {
                {new int[]{2, 3}, Arrays.asList(0, 2, 3, 5)},
                {new int[]{5, 2, 1}, Arrays.asList(0, 1, 2, 3, 5, 6, 7, 8)},
                {new int[]{1, 1}, Arrays.asList(0, 1, 1, 2)},              // Duplicate elements
                {new int[]{0}, Arrays.asList(0, 0)},                        // Array with zero
                {new int[]{}, Arrays.asList(0)},                            // Empty array (only empty subset sum 0)
                {new int[]{10, 20}, Arrays.asList(0, 10, 20, 30)},          // Standard 2-element
                {new int[]{1, 2, 3}, Arrays.asList(0, 1, 2, 3, 3, 4, 5, 6)},// Overlapping sums
                {new int[]{100}, Arrays.asList(0, 100)},                    // Single element
                {new int[]{5, 5, 5}, Arrays.asList(0, 5, 5, 5, 10, 10, 10, 15)}, // Multiple duplicates
                {new int[]{1, 2, 4, 8}, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)} // Power of 2
        };

        int passed = 0;
        System.out.println("Running Tests...\n" + "=".repeat(60));

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            List<Integer> expected = new ArrayList<>((List<Integer>) testCases[i][1]);
            Collections.sort(expected);

            List<Integer> actual = runner.calculateSubsetSums(nums);
            if (actual == null) actual = new ArrayList<>();
            Collections.sort(actual);

            boolean isCorrect = actual.equals(expected);
            if (isCorrect) passed++;

            System.out.printf("Test Case %d:\n", i + 1);
            System.out.printf("Input: %s\n", Arrays.toString(nums));
            System.out.printf("Expected: %s\n", expected);
            System.out.printf("Actual:   %s\n", actual);
            System.out.println("Result: " + (isCorrect ? "✅ PASSED" : "❌ FAILED"));
            System.out.println("-".repeat(60));
        }

        System.out.printf("\nSummary: %d/%d Tests Passed.\n", passed, testCases.length);
    }
}