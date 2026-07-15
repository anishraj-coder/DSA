package searching;

import java.util.Arrays;

public class RowWiseSortedMatrixMedian {

    /**
     * Function to find the median in a row-wise sorted matrix.
     * m and n are always odd.
     * * Time Complexity Requirement: O(32 * m * log(n))
     */
    public int findMedian(int[][] arr, int m, int n) {
        int low=Integer.MAX_VALUE,hi=Integer.MIN_VALUE;
        for(int i=0;i<m;i++){
            low=Math.min(low,arr[i][0]);
            hi=Math.max(hi,arr[i][n-1]);
        }
        long req=((long) m *n+1L)/2L;
        while(low<hi){
            int mid=(hi-low)/2+low;
            long count=countLowerOrEqual(arr,mid);
            if(count>=req)hi=mid;
            else low=mid+1;
        }
        return low;
    }

    public long countLowerOrEqual(int[][]arr,int target){
        long count=0L;
        for(int []a:arr){
            count+=upperBound(a,target);
        }
        return count;
    }

    public int upperBound(int[]arr,int target){
        int low=0,hi=arr.length;
        while(low<hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]>target)hi=mid;
            else low=mid+1;
        }

        return low;
    }

    public static void main(String[] args) {
        RowWiseSortedMatrixMedian solver = new RowWiseSortedMatrixMedian();

        int[][][] matrices = {
                // Case 1: Sample Input 1
                {{1, 5, 7, 9, 11}, {2, 3, 4, 8, 9}, {4, 11, 14, 19, 20}, {6, 10, 22, 99, 100}, {7, 15, 17, 24, 28}},
                // Case 2: Sample Input 2
                {{1, 2, 3, 4, 5}, {8, 9, 11, 12, 13}, {21, 23, 25, 27, 29}},
                // Case 3: 1x1 Matrix (Minimum constraints)
                {{5}},
                // Case 4: All elements are the same
                {{7, 7, 7}, {7, 7, 7}, {7, 7, 7}},
                // Case 5: Large range of values
                {{1, 100, 1000}, {2, 50, 2000}, {5, 5, 50000}},
                // Case 6: Negative and Positive values (though constraints say 1 to 10^9, handling 0/neg is good practice)
                {{1, 2, 3}, {3, 3, 4}, {1, 1, 2}},
                // Case 7: High duplicates at the median point
                {{1, 3, 5}, {2, 6, 9}, {3, 6, 9}},
                // Case 8: Strictly increasing matrix
                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                // Case 9: Large N, Small M
                {{1, 2, 3, 10, 11, 12, 13}, {4, 5, 6, 14, 15, 16, 17}, {7, 8, 9, 18, 19, 20, 21}},
                // Case 10: Potential Integer Overflow handling (Max constraints)
                {{1000000000, 1000000000, 1000000000}, {1, 2, 3}, {500, 600, 700}}
        };

        int[] expectedOutputs = {10, 11, 5, 7, 50, 2, 5, 5, 11, 600};

        System.out.println("Running Test Cases...\n");
        int passed = 0;

        for (int i = 0; i < matrices.length; i++) {
            int m = matrices[i].length;
            int n = matrices[i][0].length;
            int actual = solver.findMedian(matrices[i], m, n);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Dimensions: " + m + "x" + n);
            System.out.println("Expected Output: " + expectedOutputs[i]);
            System.out.println("Actual Output:   " + actual);

            if (actual == expectedOutputs[i]) {
                System.out.println("Result: PASSED ✅");
                passed++;
            } else {
                System.out.println("Result: FAILED ❌");
            }
            System.out.println("------------------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + matrices.length + " cases passed.");
    }
}