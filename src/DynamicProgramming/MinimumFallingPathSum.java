package DynamicProgramming;

public class MinimumFallingPathSum {

    // TO DO: Implement your solution here
    public int minFallingPathSum(int[][] arr) {
        int n=arr.length;
        int[][]dp=new int[n][n];
        for(int i=0;i<n;i++)dp[0][i]=arr[0][i];
        for(int i=1;i<n;i++){
            for(int j=0;j<n;j++){
                int min=dp[i-1][j]+arr[i][j];
                if(j>0)min=Math.min(dp[i-1][j-1]+arr[i][j],min);
                if(j+1<n)min=Math.min(dp[i-1][j+1]+arr[i][j],min);
                dp[i][j]=min;
            }
        }
        int min=Integer.MAX_VALUE;
        for(int a:dp[n-1])min=Math.min(a,min);
        return min;
    }

    public static void main(String[] args) {
        MinimumFallingPathSum solver = new MinimumFallingPathSum();
        int passed = 0;
        int total = 10;

        // ==========================================
        // TEST CASE 1: Standard Example 1
        // ==========================================
        int[][] matrix1 = {
                {2, 1, 3},
                {6, 5, 4},
                {7, 8, 9}
        };
        int expected1 = 13;
        int actual1 = solver.minFallingPathSum(matrix1);
        passed += verify(1, expected1, actual1);

        // ==========================================
        // TEST CASE 2: Standard Example 2 (Negative Numbers)
        // ==========================================
        int[][] matrix2 = {
                {-19, 57},
                {-40, -5}
        };
        int expected2 = -59;
        int actual2 = solver.minFallingPathSum(matrix2);
        passed += verify(2, expected2, actual2);

        // ==========================================
        // TEST CASE 3: Single Element Edge Case (1x1 Matrix)
        // ==========================================
        int[][] matrix3 = {
                {7}
        };
        int expected3 = 7;
        int actual3 = solver.minFallingPathSum(matrix3);
        passed += verify(3, expected3, actual3);

        // ==========================================
        // TEST CASE 4: Negative Single Element Edge Case
        // ==========================================
        int[][] matrix4 = {
                {-100}
        };
        int expected4 = -100;
        int actual4 = solver.minFallingPathSum(matrix4);
        passed += verify(4, expected4, actual4);

        // ==========================================
        // TEST CASE 5: All Elements Same Value
        // ==========================================
        int[][] matrix5 = {
                {5, 5, 5},
                {5, 5, 5},
                {5, 5, 5}
        };
        int expected5 = 15;
        int actual5 = solver.minFallingPathSum(matrix5);
        passed += verify(5, expected5, actual5);

        // ==========================================
        // TEST CASE 6: Anti-Greedy Choice Path
        // Choosing the larger number early opens up a much smaller path later
        // ==========================================
        int[][] matrix6 = {
                {1, 100, 100},
                {100, 50, 100},
                {100, -50, 100}
        };
        int expected6 = 101; // 1 -> 50 -> -50
        int actual6 = solver.minFallingPathSum(matrix6);
        passed += verify(6, expected6, actual6);

        // ==========================================
        // TEST CASE 7: Strictly Bound Diagonal Path (Left to Right Edge)
        // ==========================================
        int[][] matrix7 = {
                {1, 10, 10, 10},
                {10, 1, 10, 10},
                {10, 10, 1, 10},
                {10, 10, 10, 1}
        };
        int expected7 = 4;
        int actual7 = solver.minFallingPathSum(matrix7);
        passed += verify(7, expected7, actual7);

        // ==========================================
        // TEST CASE 8: Multi-Path Symmetry with Sharp Drops
        // ==========================================
        int[][] matrix8 = {
                {0, 20, 0},
                {10, -20, 10},
                {5, 10, 5}
        };
        int expected8 = -15; // 0 -> -20 -> 5
        int actual8 = solver.minFallingPathSum(matrix8);
        passed += verify(8, expected8, actual8);

        // ==========================================
        // TEST CASE 9: Out-of-Bound Prevention at Column Edges
        // Verifies that matrix[-1] or matrix[col+1] doesn't trip up boundaries
        // ==========================================
        int[][] matrix9 = {
                {-50, 10, 10},
                {-50, 10, 10},
                {10, -50, 10}
        };
        int expected9 = -90; // -50 -> -50 -> -50 (diagonally down-right)
        int actual9 = solver.minFallingPathSum(matrix9);
        passed += verify(9, expected9, actual9);

        // ==========================================
        // TEST CASE 10: Maximum and Minimum Value Constraints
        // ==========================================
        int[][] matrix10 = {
                {-100, 100, -100, 100},
                {100, -100, 100, -100},
                {-100, 100, -100, 100},
                {100, -100, 100, -100}
        };
        int expected10 = -400; // Alternating path to hit all -100s
        int actual10 = solver.minFallingPathSum(matrix10);
        passed += verify(10, expected10, actual10);

        // ==========================================
        // SUMMARY RESULT
        // ==========================================
        System.out.println("\n======================================");
        System.out.println("FINAL RESULT: " + passed + " / " + total + " TEST CASES PASSED");
        System.out.println("======================================");
    }

    private static int verify(int caseNum, int expected, int actual) {
        if (expected == actual) {
            System.out.println("Test Case " + caseNum + ": [PASSED]");
            return 1;
        } else {
            System.out.println("Test Case " + caseNum + ": [FAILED]");
            System.out.println("   Expected Output: " + expected);
            System.out.println("   Actual Output:   " + actual);
            return 0;
        }
    }
}