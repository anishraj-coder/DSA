package searching;

import java.util.Arrays;

public class PeakElement2 {

    /**
     * Problem: Find a Peak Element II
     * Complexity Requirement: O(m log(n)) or O(n log(m))
     * A peak is strictly greater than its top, bottom, left, and right neighbors.
     * * @return int[] {row, col}
     */
    public int[] findPeakGrid(int[][] arr) {
        int m=arr.length,n=arr[0].length;
        int low=0,hi=n-1;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            int maxIdx=findMax(arr,mid);
            int left=mid-1>=0?arr[maxIdx][mid-1]:-1;
            int right=mid+1<n?arr[maxIdx][mid+1]:-1;
            int val=arr[maxIdx][mid];
            if(val>left&&val>right)return new int[]{maxIdx,mid};
            else if(left>val)hi=mid-1;
            else low=mid+1;
        }
        return new int[]{-1, -1};
    }
    public int findMax(int[][]arr,int col){
        int n=arr.length,max=Integer.MIN_VALUE,maxIdx=-1;
        for(int i=0;i<n;i++){
            if(arr[i][col]>max){
                max=arr[i][col];
                maxIdx=i;
            }
        }
        return maxIdx;
    }

    public int peakElem(int[]arr){
        int n=arr.length;
        if(n==1){
            if(arr[0]>-1)return 0;
        }
        if(arr[0]>-1&&arr[1]<arr[0])return 0;
        if(arr[n-1]>arr[n-2]&&arr[n-1]>-1)return n-1;
        int low=1,hi=n-2;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]>arr[mid-1]&&arr[mid]>arr[mid+1])return mid;
            else if(arr[mid-1]<arr[mid]&&arr[mid]<arr[mid+1])low=mid+1;
            else hi=mid-1;
        }
        return -1;
    }

    public static void main(String[] args) {
        PeakElement2 solver = new PeakElement2();
        int testsPassed = 0;
        int totalTests = 10;

        // Test Case 1: Standard small matrix
        int[][] m1 = {{1, 4}, {3, 2}};
        // Expected: [0, 1] or [1, 0]
        testsPassed += runTest(1, m1, solver.findPeakGrid(m1));

        // Test Case 2: Multi-peak matrix
        int[][] m2 = {
                {10, 20, 15},
                {21, 30, 14},
                {7, 16, 32}
        };
        // Expected: [1, 1] or [2, 2]
        testsPassed += runTest(2, m2, solver.findPeakGrid(m2));

        // Test Case 3: Single Row
        int[][] m3 = {{1, 5, 10, 7}};
        testsPassed += runTest(3, m3, solver.findPeakGrid(m3));

        // Test Case 4: Single Column
        int[][] m4 = {{1}, {10}, {2}};
        testsPassed += runTest(4, m4, solver.findPeakGrid(m4));

        // Test Case 5: Single Element
        int[][] m5 = {{42}};
        testsPassed += runTest(5, m5, solver.findPeakGrid(m5));

        // Test Case 6: Peak at the corner (Top-Left)
        int[][] m6 = {
                {100, 90},
                {80, 70}
        };
        testsPassed += runTest(6, m6, solver.findPeakGrid(m6));

        // Test Case 7: Peak at the corner (Bottom-Right)
        int[][] m7 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        testsPassed += runTest(7, m7, solver.findPeakGrid(m7));

        // Test Case 8: "Valley" shape (Peak on edges)
        int[][] m8 = {
                {10, 1, 10},
                {1, 0, 1},
                {10, 1, 10}
        };
        testsPassed += runTest(8, m8, solver.findPeakGrid(m8));

        // Test Case 9: Large Matrix (Ascending towards middle)
        int[][] m9 = {
                {1, 2, 3, 2, 1},
                {2, 3, 4, 3, 2},
                {3, 4, 5, 4, 3},
                {2, 3, 4, 3, 2},
                {1, 2, 3, 2, 1}
        };
        testsPassed += runTest(9, m9, solver.findPeakGrid(m9));

        // Test Case 10: Checkerboard style (Multiple scattered peaks)
        int[][] m10 = {
                {5, 1, 5},
                {1, 5, 1},
                {5, 1, 5}
        };
        testsPassed += runTest(10, m10, solver.findPeakGrid(m10));

        System.out.println("\n---------------------------------------");
        System.out.println("Final Result: " + testsPassed + "/" + totalTests + " Tests Passed");
        System.out.println("---------------------------------------");
    }

    private static int runTest(int id, int[][] matrix, int[] actual) {
        System.out.print("Test Case " + id + ": ");

        if (actual == null || actual.length != 2) {
            System.out.println("FAILED (Invalid Output Format)");
            return 0;
        }

        int r = actual[0];
        int c = actual[1];
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Check if out of bounds
        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            System.out.println("FAILED (Indices out of bounds: " + Arrays.toString(actual) + ")");
            return 0;
        }

        int val = matrix[r][c];

        // Check neighbors (using -1 for boundaries as per instructions)
        int up = (r > 0) ? matrix[r - 1][c] : -1;
        int down = (r < rows - 1) ? matrix[r + 1][c] : -1;
        int left = (c > 0) ? matrix[r][c - 1] : -1;
        int right = (c < cols - 1) ? matrix[r][c + 1] : -1;

        if (val > up && val > down && val > left && val > right) {
            System.out.println("PASSED (Found peak " + val + " at " + Arrays.toString(actual) + ")");
            return 1;
        } else {
            System.out.println("FAILED (Value " + val + " at " + Arrays.toString(actual) + " is not a peak)");
            return 0;
        }
    }
}