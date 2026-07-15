package arrays;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayLeaders {

    /**
     * Finds all the leaders in an array.
     * A leader is strictly greater than all elements to its right.
     * * @param arr The input integer array.
     * @return A list of leaders in the order they appear in the original array.
     */
    public List<Integer> findLeaders(int[] arr) {
        int n=arr.length;
        if(n==1)return List.of(arr[0]);

        List<Integer>ans=new ArrayList<>();
        int max=arr[n-1];
        ans.add(arr[n-1]);
        for(int i=n-2;i>=0;i--){
            if(arr[i]>max){
                ans.add(0,arr[i]);
            }
            max=Math.max(arr[i],max);
        }

        return ans;
    }

    public static void main(String[] args) {
        ArrayLeaders solver = new ArrayLeaders();

        // Test Case Definitions
        Object[][] testCases = {
                { new int[]{1, 2, 5, 3, 1, 2}, Arrays.asList(5, 3, 2), "Standard case with duplicates" },
                { new int[]{-3, 4, 5, 1, -4, -5}, Arrays.asList(5, 1, -4, -5), "Case with negative numbers" },
                { new int[]{10, 20, 30, 40, 50}, Arrays.asList(50), "Strictly increasing (only last is leader)" },
                { new int[]{50, 40, 30, 20, 10}, Arrays.asList(50, 40, 30, 20, 10), "Strictly decreasing (all are leaders)" },
                { new int[]{7}, Arrays.asList(7), "Single element" },
                { new int[]{8, 8, 8, 8}, Arrays.asList(8), "All elements identical (only last is leader due to 'strictly' rule)" },
                { new int[]{10, 5, 10, 3, 2}, Arrays.asList(10, 3, 2), "Duplicate of current max to the left" },
                { new int[]{1, 2, 3, 4, 5, 2}, Arrays.asList(5, 2), "Increasing then one drop" },
                { new int[]{-1, -2, -3, -4, -5}, Arrays.asList(-1, -2, -3, -4, -5), "Negative decreasing sequence" },
                { new int[]{100, 1, 2, 3, 4, 100}, Arrays.asList(100), "Two peaks, first peak equal to second" },
                { new int[]{16, 17, 4, 3, 5, 2}, Arrays.asList(17, 5, 2), "Random distribution" }
        };

        int passed = 0;
        System.out.println("Running Tests for ArrayLeaders...\n");
        System.out.printf("%-40s | %-20s | %-20s | %-10s%n", "Input", "Expected", "Actual", "Result");
        System.out.println("-".repeat(100));

        for (Object[] test : testCases) {
            int[] input = (int[]) test[0];
            List<Integer> expected = (List<Integer>) test[1];
            String description = (String) test[2];

            List<Integer> actual = solver.findLeaders(input);
            boolean isMatch = expected.equals(actual);

            if (isMatch) passed++;

            System.out.printf("%-40s | %-20s | %-20s | %-10s%n",
                    Arrays.toString(input),
                    expected.toString(),
                    actual.toString(),
                    isMatch ? "PASSED" : "FAILED"
            );
        }

        System.out.println("-".repeat(100));
        System.out.printf("Summary: %d/%d Tests Passed.%n", passed, testCases.length);
    }
}