package arrays;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MajorityElement2 {

    /**
     * Finds all elements that appear more than ⌊ n/3 ⌋ times in the given array.
     *
     * @param arr the input array of integers
     * @return a list of integers representing the majority elements
     */
    public static List<Integer> findMajorityElements(int[] arr) {
        int n=arr.length;
        if(n<=2)return Arrays.stream(arr).boxed().toList();

        List<Integer>ans=new ArrayList<>();
        int count1=1,val1=arr[0],val2=arr[0],count2=0;
        for(int i=1;i<n;i++){
            if(arr[i]==val1){
                count1++;
            }else if(val2==arr[i]){
                count2++;
            }else if(count1==0){
                val1=arr[i];
                count1++;
            }else if(count2==0){
                val2=arr[i];
                count2++;
            }else {
                count1--;
                count2--;
            }
        }

        int c1=0,c2=0;
        for(int a:arr){
            if(a==val1)c1++;
            else if(a==val2)c2++;
        }
        if(c1>n/3)ans.add(val1);
        if(c2>n/3)ans.add(val2);
        return ans;
    }

    public static void main(String[] args) {
        // Test Case Structure: {input_array, expected_output_list}
        // Expected outputs are sorted to ensure consistent comparison regardless of return order.
        Object[][] testCases = {
                // Basic Examples from Problem Statement
                {new int[]{3, 2, 3}, Arrays.asList(3)},
                {new int[]{1}, Arrays.asList(1)},
                {new int[]{1, 2}, Arrays.asList(1, 2)},

                // No Majority Element
                {new int[]{1, 2, 3, 4, 5}, Arrays.asList()}, // n=5, threshold=1. All appear once. 1 > 1 is false.
                {new int[]{1, 1, 2, 2, 3, 3}, Arrays.asList()}, // n=6, threshold=2. All appear twice. 2 > 2 is false.

                // Single Dominant Element
                {new int[]{1, 1, 1, 2, 2, 3}, Arrays.asList(1)}, // n=6, threshold=2. 1 appears 3 times.
                {new int[]{0, 0, 0, 0, 1, 1, 2}, Arrays.asList(0)}, // n=7, threshold=2. 0 appears 4 times.

                // Two Dominant Elements
                {new int[]{1, 1, 1, 2, 2, 2, 3}, Arrays.asList(1, 2)}, // n=7, threshold=2. Both appear 3 times.
                {new int[]{1, 2, 1, 2, 1, 2, 3, 3}, Arrays.asList(1, 2)}, // n=8, threshold=2. 1&2 appear 3 times.

                // Negative Numbers and Zeros
                {new int[]{-1, -1, -1, 0, 0}, Arrays.asList(-1)}, // n=5, threshold=1. -1 appears 3 times.
                {new int[]{0, 0, 0, -1, -1, -1}, Arrays.asList(-1, 0)}, // n=6, threshold=2. Both appear 3 times.

                // Large Values / Edge Constraints
                {new int[]{1000000000, 1000000000, 1000000000, -1000000000}, Arrays.asList(1000000000)},
                {new int[]{1, 1, 2, 2, 3, 3, 4}, Arrays.asList()}, // n=7, threshold=2. Max freq is 2. 2>2 is false.

                // All Same Elements
                {new int[]{5, 5, 5, 5, 5}, Arrays.asList(5)}, // n=5, threshold=1. 5 appears 5 times.
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            @SuppressWarnings("unchecked")
            List<Integer> expected = (List<Integer>) testCases[i][1];

            // Sort expected for consistent comparison
            List<Integer> sortedExpected = new ArrayList<>(expected);
            Collections.sort(sortedExpected);

            // Call function
            List<Integer> actual = findMajorityElements(input);

            // Sort actual for consistent comparison
            List<Integer> sortedActual = new ArrayList<>(actual);
            Collections.sort(sortedActual);

            boolean isPassed = sortedExpected.equals(sortedActual);

            if (isPassed) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED");
            } else {
                failed++;
                System.out.println("Test Case " + (i + 1) + ": FAILED");
            }

            System.out.println("  Input   : " + Arrays.toString(input));
            System.out.println("  Expected: " + sortedExpected);
            System.out.println("  Actual  : " + sortedActual);
            System.out.println("-----------------------------------");
        }

        System.out.println("Total Passed: " + passed + "/" + testCases.length);
        System.out.println("Total Failed: " + failed + "/" + testCases.length);
    }
}