package searching;

import java.util.Arrays;

public class KthElementTwoArrays {

    /**
     * Problem: Find the K-th element of two combined sorted arrays.
     * Constraints: 1 <= arr1.size(), b.size() <= 10^6
     * Time Complexity Requirement: O(log(min(n, m)))
     */
    public long kthElement(int[] arr1, int[] arr2, int k) {
        if(arr1.length>arr2.length)return kthElement(arr2, arr1,k);
        int n1= arr1.length,n2=arr2.length;
        int low=Math.max(0,k-n2),hi=Math.min(k,n1);

        while(low<=hi){
            int part1=(hi-low)/2+low;
            int part2=k-part1;
            int left1=part1==0?Integer.MIN_VALUE:arr1[part1-1];
            int left2=part2==0?Integer.MIN_VALUE:arr2[part2-1];
            int right1=part1==n1?Integer.MAX_VALUE:arr1[part1];
            int right2=part2==n2?Integer.MAX_VALUE:arr2[part2];
            if(left1<=right2&&left2<=right1)
                return Math.max(left1,left2);
            else if(left1>right2) hi=part1-1;
            else low=part1+1;
        }
        return -1;
    }

    public static void main(String[] args) {
        KthElementTwoArrays solver = new KthElementTwoArrays();

        // Test Case Format: {Array A, Array B, K, Expected Output}
        Object[][] testCases = {
                {new int[]{2, 3, 6, 7, 9}, new int[]{1, 4, 8, 10}, 5, 6L},          // Case 1: Standard odd k
                {new int[]{1, 4, 8, 10, 12}, new int[]{5, 7, 11, 15, 17}, 6, 10L},  // Case 2: Standard even k
                {new int[]{1, 10, 20}, new int[]{4, 5, 6, 7, 8, 9}, 4, 6L},         // Case 3: K is smaller than first array
                {new int[]{1, 2, 3}, new int[]{10, 20, 30, 40}, 7, 40L},            // Case 4: K is the last element
                {new int[]{1, 2, 3}, new int[]{10, 20, 30, 40}, 1, 1L},             // Case 5: K is the first element
                {new int[]{2, 3, 4}, new int[]{1}, 1, 1L},                          // Case 6: Array B is smaller and contains k
                {new int[]{10, 20, 30, 40}, new int[]{5, 15, 25, 35, 45}, 5, 25L},  // Case 7: Interleaved arrays
                {new int[]{1, 1, 1}, new int[]{1, 1, 1}, 3, 1L},                    // Case 8: All duplicates
                {new int[]{1, 5, 9}, new int[]{2, 4, 8, 10}, 7, 10L},               // Case 9: K at the very end of combined
                {new int[]{100}, new int[]{1, 2, 3, 4, 5}, 2, 2L},                  // Case 10: Size mismatch (1 vs 5)
                {new int[]{1, 2, 5, 10}, new int[]{3, 4, 7, 8}, 4, 4L},             // Case 11: Middle of merged
                {new int[]{0, 0, 0}, new int[]{0, 0, 0, 0}, 5, 0L}                  // Case 12: All zeros
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] a = (int[]) testCases[i][0];
            int[] b = (int[]) testCases[i][1];
            int k = (int) testCases[i][2];
            long expected = (long) testCases[i][3];
            long actual = solver.kthElement(a, b, k);

            boolean isCorrect = (actual == expected);
            if (isCorrect) passed++;

            System.out.printf("Test Case %d: %s\n", i + 1, isCorrect ? "PASSED" : "FAILED");
            System.out.println("  k: " + k);
            System.out.println("  Arr1: " + Arrays.toString(a));
            System.out.println("  Arr2: " + Arrays.toString(b));
            System.out.println("  Expected: " + expected + " | Actual: " + actual);
            System.out.println("--------------------------------------------------");
        }

        System.out.printf("Final Result: %d/%d Passed\n", passed, testCases.length);
    }
}