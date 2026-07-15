package bitManipulation;

import java.util.*;

public class SubsetsGenerator {

    /**
     * Implementation Task:
     * Generate all possible subsets (the power set) of the given unique elements.
     * Use bit manipulation (bitmasks) to solve this.
     * * @param nums The array of unique integers.
     * @return A list of lists containing all subsets.
     */
    public List<List<Integer>> subsets(int[] arr) {
        List<List<Integer>>ans=new ArrayList<>();
        int n=arr.length,total=1<<n;
        for(int i=0;i<total;i++){
            List<Integer>temp=new ArrayList<>();
            for(int j=0;j<n;j++){
                if((i&(1<<j))!=0){
                    temp.add(arr[j]);
                }
            }
            ans.add(temp);
        }

        return ans;
    }

    public static void main(String[] args) {
        SubsetsGenerator generator = new SubsetsGenerator();

        // Test Cases: {Input Array, Expected Number of Subsets}
        // We track size because the order of subsets doesn't matter,
        // but the total count and content do.
        Object[][] testCases = {
                {new int[]{1, 2, 3}, 8},     // Example 1
                {new int[]{0}, 2},           // Example 2
                {new int[]{}, 1},            // Edge case: Empty array (subset is [[]])
                {new int[]{10, -10}, 4},     // Two elements
                {new int[]{1, 2, 3, 4}, 16}, // Four elements
                {new int[]{-1, 1, 2}, 8},    // Negative values
                {new int[]{5, 8, 9}, 8},     // Random unique values
                {new int[]{1, 2, 3, 4, 5}, 32}, // Five elements
                {new int[]{7}, 2},           // Single positive element
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 1024} // Max constraint size
        };

        int passed = 0;

        System.out.println("Running Power Set (Subsets) Tests...\n");
        System.out.printf("%-25s | %-10s | %-10s | %-10s%n", "Input Array", "Expected#", "Actual#", "Result");
        System.out.println("-----------------------------------------------------------------------");

        for (Object[] test : testCases) {
            int[] input = (int[]) test[0];
            int expectedSize = (int) test[1];

            List<List<Integer>> actualResult = generator.subsets(input);
            int actualSize = (actualResult != null) ? actualResult.size() : 0;

            boolean isCorrectSize = (actualSize == expectedSize);
            // Deep check for empty set and uniqueness can be added,
            // but size is the primary indicator for bitmask logic.

            String status = isCorrectSize ? "PASSED" : "FAILED";
            if (isCorrectSize) passed++;

            System.out.printf("%-25s | %-10d | %-10d | %-10s%n",
                    Arrays.toString(input), expectedSize, actualSize, status);
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
        System.out.println("Note: Ensure your code handles the empty list [] as one of the subsets!");
    }
}