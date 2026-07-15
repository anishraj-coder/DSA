package searching;

public class MatrixSearch {

    /**
     * Problem: Search a 2D Matrix
     * Constraint: O(log(m * n)) time complexity.
     */
    public boolean searchMatrix(int[][] arr, int target) {
        if(arr==null)return false;
        int m=arr.length,n=arr[0].length;
        long size= (long) m * n;
        long low=0,hi=size-1L;
        while(low<=hi){
            long mid=(hi-low)/2+low;
            int row=(int)(mid/n);
            int col=(int)(mid%n);
            if(arr[row][col]==target)return true;
            else if(arr[row][col]<target)low=mid+1;
            else hi=mid-1;
        }

        return false;
    }

    public static void main(String[] args) {
        MatrixSearch solver = new MatrixSearch();
        int testsPassed = 0;
        int totalTests = 10;

        // Test Case 1: Target in the middle of a row
        int[][] m1 = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        testsPassed += runTest(1, solver.searchMatrix(m1, 3), true);

        // Test Case 2: Target not in matrix (within range)
        testsPassed += runTest(2, solver.searchMatrix(m1, 13), false);

        // Test Case 3: Target is the very first element
        testsPassed += runTest(3, solver.searchMatrix(m1, 1), true);

        // Test Case 4: Target is the very last element
        testsPassed += runTest(4, solver.searchMatrix(m1, 60), true);

        // Test Case 5: Single element matrix (Match)
        int[][] m5 = {{5}};
        testsPassed += runTest(5, solver.searchMatrix(m5, 5), true);

        // Test Case 6: Single element matrix (No Match)
        testsPassed += runTest(6, solver.searchMatrix(m5, 2), false);

        // Test Case 7: Target smaller than the smallest element
        testsPassed += runTest(7, solver.searchMatrix(m1, 0), false);

        // Test Case 8: Target larger than the largest element
        testsPassed += runTest(8, solver.searchMatrix(m1, 100), false);

        // Test Case 9: Single row matrix
        int[][] m9 = {{1, 3, 5, 7, 9}};
        testsPassed += runTest(9, solver.searchMatrix(m9, 9), true);

        // Test Case 10: Single column matrix
        int[][] m10 = {{1}, {3}, {5}, {7}};
        testsPassed += runTest(10, solver.searchMatrix(m10, 5), true);

        System.out.println("\n---------------------------------------");
        System.out.println("Final Result: " + testsPassed + "/" + totalTests + " Tests Passed");
        System.out.println("---------------------------------------");
    }

    private static int runTest(int id, boolean actual, boolean expected) {
        System.out.print("Test Case " + id + ": ");
        if (actual == expected) {
            System.out.println("PASSED (Expected: " + expected + ", Actual: " + actual + ")");
            return 1;
        } else {
            System.out.println("FAILED (Expected: " + expected + ", Actual: " + actual + ")");
            return 0;
        }
    }
}