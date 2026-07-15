package searching;

public class MatrixSearchII {

    /**
     * Problem: Search a 2D Matrix II
     * Properties: Rows sorted Left-to-Right, Columns sorted Top-to-Bottom.
     * Constraint: Must be efficient (Targeting O(m + n)).
     */
    public boolean searchMatrix(int[][] arr, int target) {
        int m=arr.length,n=arr[0].length;
        if(m==1&&n==1)return arr[0][0]==target;
        int i=0,j=n-1;
        while(i<m&&j>=0){
            int val=arr[i][j];
            if(val==target)return true;
            else if(val>target) j--;
            else i++;
        }
        return false;
    }

    public static void main(String[] args) {
        MatrixSearchII solver = new MatrixSearchII();
        int testsPassed = 0;
        int totalTests = 10;

        int[][] standardMatrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        // Test Case 1: Target exists in the middle
        testsPassed += runTest(1, solver.searchMatrix(standardMatrix, 5), true);

        // Test Case 2: Target does not exist (between values)
        testsPassed += runTest(2, solver.searchMatrix(standardMatrix, 20), false);

        // Test Case 3: Target is the minimum element (Top-Left)
        testsPassed += runTest(3, solver.searchMatrix(standardMatrix, 1), true);

        // Test Case 4: Target is the maximum element (Bottom-Right)
        testsPassed += runTest(4, solver.searchMatrix(standardMatrix, 30), true);

        // Test Case 5: Target is smaller than the smallest element
        testsPassed += runTest(5, solver.searchMatrix(standardMatrix, -5), false);

        // Test Case 6: Target is larger than the largest element
        testsPassed += runTest(6, solver.searchMatrix(standardMatrix, 35), false);

        // Test Case 7: Single row matrix (Match)
        int[][] m7 = {{1, 3, 5, 7}};
        testsPassed += runTest(7, solver.searchMatrix(m7, 3), true);

        // Test Case 8: Single column matrix (No Match)
        int[][] m8 = {{1}, {4}, {7}, {10}};
        testsPassed += runTest(8, solver.searchMatrix(m8, 5), false);

        // Test Case 9: Matrix with duplicate values (Search for one that exists)
        int[][] m9 = {
                {1, 2, 2},
                {2, 3, 4},
                {4, 4, 5}
        };
        testsPassed += runTest(9, solver.searchMatrix(m9, 2), true);

        // Test Case 10: 1x1 Matrix
        int[][] m10 = {{42}};
        testsPassed += runTest(10, solver.searchMatrix(m10, 42), true);

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