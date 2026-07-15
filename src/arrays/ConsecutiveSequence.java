package arrays;

import java.util.*;

public class ConsecutiveSequence {

    /**
     * Finds the length of the longest consecutive elements sequence.
     * The algorithm must run in O(n) time.
     * * @param arr An unsorted array of integers.
     * @return The length of the longest consecutive sequence.
     */
    public int longestConsecutive(int[] arr) {
        int n=arr.length;
        if(n<=1)return n;
        Set<Long>set=new HashSet<>();
        for(int a: arr)set.add(a*1L);

        int longest=1;

        for(int i=0;i<n;i++){
            if(!set.contains(arr[i]-1)){
                int curr=1;
                long x=arr[i];
                while(set.contains(x+1)){
                    curr++;
                    x++;
                }
                longest=Math.max(longest,curr);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        ConsecutiveSequence solver = new ConsecutiveSequence();

        // Test Case Definitions: {Input Array, Expected Output, Description}
        Object[][] testCases = {
                { new int[]{100, 4, 200, 1, 3, 2}, 4, "Standard case: [1, 2, 3, 4]" },
                { new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}, 9, "Large sequence with duplicates" },
                { new int[]{1, 0, 1, 2}, 3, "Sequence with duplicates: [0, 1, 2]" },
                { new int[]{}, 0, "Empty array" },
                { new int[]{10}, 1, "Single element array" },
                { new int[]{5, 5, 5, 5}, 1, "Multiple identical elements" },
                { new int[]{1, 2, 0, 1}, 3, "Small sequence with overlap" },
                { new int[]{-1, -2, -3, 0, 1}, 5, "Sequence with negative numbers" },
                { new int[]{1, 10, 2, 20, 3, 30}, 3, "Interleaved sequences: [1, 2, 3]" },
                { new int[]{2147483647, -2147483648, 0}, 1, "Integer boundary values" },
                { new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6}, 7, "Scattered sequence with negatives: [-1, 0, 1] and [3, 4, 5, 6, 7, 8, 9]" }
        };

        int passed = 0;
        System.out.println("Running Tests for ConsecutiveSequence...\n");
        System.out.printf("%-45s | %-10s | %-10s | %-10s%n", "Input", "Expected", "Actual", "Result");
        System.out.println("-".repeat(90));

        for (Object[] test : testCases) {
            int[] input = (int[]) test[0];
            int expected = (int) test[1];
            String description = (String) test[2];

            // We clone to ensure the implementation doesn't rely on modifying the original if passed by ref
            int actual = solver.longestConsecutive(input.clone());
            boolean isMatch = (expected == actual);

            if (isMatch) passed++;

            String inputStr = Arrays.toString(input);
            if (inputStr.length() > 42) inputStr = inputStr.substring(0, 39) + "...";

            System.out.printf("%-45s | %-10d | %-10d | %-10s%n",
                    inputStr,
                    expected,
                    actual,
                    isMatch ? "PASSED" : "FAILED"
            );
        }

        System.out.println("-".repeat(90));
        System.out.printf("Summary: %d/%d Tests Passed.%n", passed, testCases.length);
    }
}