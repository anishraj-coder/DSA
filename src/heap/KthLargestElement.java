package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElement {

    /**
     * Finds and returns the K-th largest element in the array.
     * Note: This is the K-th largest element in sorted order, not the K-th distinct element.
     * @param arr The input array of integers.
     * @param k    The rank of the largest element to find (1-indexed).
     * @return     The K-th largest element.
     */
    public int findKthLargest(int[] arr, int k) {
        PriorityQueue<Integer>pq=new PriorityQueue<>();
        int n=arr.length;

        for(int i=0;i<k;i++){
            pq.offer(arr[i]);
        }

        for(int i=k;i<n;i++){
            if(arr[i]>pq.peek()){
                pq.poll();
                pq.offer(arr[i]);
            }
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        KthLargestElement engine = new KthLargestElement();

        // Define test cases: {Array, K, Expected Output}
        Object[][] testCases = {
                // Case 1: Standard case with distinct values (Sample 1)
                { new int[]{3, 2, 1, 5, 6, 4}, 2, 5 },

                // Case 2: Standard case with heavy duplicates (Sample 2)
                { new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4, 4 },

                // Case 3: Minimal array size (N = 1, K = 1)
                { new int[]{-5}, 1, -5 },

                // Case 4: K is 1 (Finding the absolute maximum)
                { new int[]{-10, -2, -30, -1, -5}, 1, -1 },

                // Case 5: K is N (Finding the absolute minimum)
                { new int[]{7, 12, 1, 9, 4}, 5, 1 },

                // Case 6: All elements are identical (Crucial duplicate edge case)
                { new int[]{8, 8, 8, 8, 8, 8}, 3, 8 },

                // Case 7: Array sorted in ascending order
                { new int[]{1, 2, 3, 4, 5, 6, 7}, 3, 5 },

                // Case 8: Array sorted in descending order
                { new int[]{7, 6, 5, 4, 3, 2, 1}, 5, 3 },

                // Case 9: Large negative and positive values alternating
                { new int[]{-10000, 10000, -5000, 5000, 0}, 2, 5000 },

                // Case 10: Multiple instances of the target K-th element
                { new int[]{1, 5, 5, 5, 5, 9, 10}, 3, 5 }
        };

        int passed = 0;
        System.out.println("================ RUNNING TESTS ================");

        for (int i = 0; i < testCases.length; i++) {
            int[] originalNums = (int[]) testCases[i][0];
            // Clone array to prevent potential in-place mutation side-effects during testing
            int[] nums = originalNums.clone();
            int k = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = engine.findKthLargest(nums, k);
            boolean isCorrect = (actual == expected);

            if (isCorrect) {
                passed++;
                System.out.printf("[PASS] Test Case %d\n", i + 1);
            } else {
                System.out.printf("[FAIL] Test Case %d\n", i + 1);
                System.out.printf("       Input Array : %s\n", Arrays.toString(originalNums));
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