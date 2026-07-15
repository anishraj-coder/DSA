package heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestElement {

    /**
     * Finds and returns the K-th smallest value present in the array.
     * * @param arr The input array of distinct integers.
     * @param n   The size of the array.
     * @param k   The rank of the smallest element to find (1-indexed).
     * @return    The K-th smallest element.
     */
    public int findKthSmallest(int[] arr, int n, int k) {
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<k;i++)pq.add(arr[i]);
        for(int i=k;i<n;i++){
            if(arr[i]<pq.peek()){
                pq.poll();
                pq.offer(arr[i]);
            }
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        KthSmallestElement engine = new KthSmallestElement();

        // Define test cases: {Array, K, Expected Output}
        Object[][] testCases = {
                // Case 1: Standard case (Sample 1)
                { new int[]{5, 9, 1, 8, 10, 6, 4}, 5, 8 },

                // Case 2: Standard case (Sample 2)
                { new int[]{24, 8, 23, 28, 3, 1, 19}, 2, 3 },

                // Case 3: Minimal array size (N = 1, K = 1)
                { new int[]{42}, 1, 42 },

                // Case 4: K is 1 (Finding the absolute minimum)
                { new int[]{15, 7, 22, 9, 3, 11}, 1, 3 },

                // Case 5: K is N (Finding the absolute maximum)
                { new int[]{15, 7, 22, 9, 3, 11}, 6, 22 },

                // Case 6: Array sorted in ascending order
                { new int[]{10, 20, 30, 40, 50, 60}, 4, 40 },

                // Case 7: Array sorted in descending order
                { new int[]{60, 50, 40, 30, 20, 10}, 3, 30 },

                // Case 8: Array containing large numbers approaching 10^9
                { new int[]{1000000000, 999999999, 500000000, 1, 2}, 3, 500000000 },

                // Case 9: Zig-zag alternating sequence
                { new int[]{5, 100, 4, 200, 3, 300, 2, 400, 1}, 5, 5 },

                // Case 10: Large values with K in the exact middle (N is odd)
                { new int[]{55, 12, 89, 34, 67, 90, 21}, 4, 55 }
        };

        int passed = 0;
        System.out.println("================ RUNNING TESTS ================");

        for (int i = 0; i < testCases.length; i++) {
            int[] originalArr = (int[]) testCases[i][0];
            // Clone array to prevent potential in-place mutation side-effects during grading
            int[] arr = originalArr.clone();
            int k = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = engine.findKthSmallest(arr, arr.length, k);
            boolean isCorrect = (actual == expected);

            if (isCorrect) {
                passed++;
                System.out.printf("[PASS] Test Case %d\n", i + 1);
            } else {
                System.out.printf("[FAIL] Test Case %d\n", i + 1);
                System.out.printf("       Input Array : %s\n", Arrays.toString(originalArr));
                System.out.printf("       K           : %d\n", k);
                System.out.printf("       Expected    : %d\n", expected);
                System.out.printf("       Actual      : %d\n", actual);
            }
            System.out.println("-----------------------------------------------");
        }

        System.out.printf("Result: %d/%d Test Cases Passed.\n", passed, testCases.length);
        System.out.println("===============================================");
    }
}