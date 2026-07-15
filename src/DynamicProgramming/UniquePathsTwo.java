package DynamicProgramming;

public class UniquePathsTwo {

    // Function to implement by the user
    public int uniquePathsWithObstacles(int[][] arr) {
        if(arr[0][0]==1)return 0;
        int m=arr.length,n=arr[0].length;
        if(arr[m-1][n-1]==1)return 0;
        int [][]dp=new int[m][n];
        dp[0][0]=1;
        for(int i=1;i<m;i++)dp[i][0]=arr[i][0]==0?dp[i-1][0]:0;
        for(int i=1;i<n;i++)dp[0][i]=arr[0][i]==0?dp[0][i-1]:0;
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(arr[i][j]!=1){
                    int up=dp[i-1][j],down=dp[i][j-1];
                    dp[i][j]=up+down;
                }else{
                    dp[i][j]=0;
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        UniquePathsTwo pathFinder = new UniquePathsTwo();

        // Test Cases Definitions

        // TC 1: Standard case with a single obstacle in the middle
        int[][] tc1 = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int expected1 = 2;

        // TC 2: Small grid with obstacle at the top right
        int[][] tc2 = {
                {0, 1},
                {0, 0}
        };
        int expected2 = 1;

        // TC 3: Obstacle right at the starting position (robot cannot move at all)
        int[][] tc3 = {
                {1, 0},
                {0, 0}
        };
        int expected3 = 0;

        // TC 4: Obstacle right at the destination position
        int[][] tc4 = {
                {0, 0},
                {0, 1}
        };
        int expected4 = 0;

        // TC 5: Single cell grid without obstacle
        int[][] tc5 = {
                {0}
        };
        int expected5 = 1;

        // TC 6: Single cell grid with an obstacle
        int[][] tc6 = {
                {1}
        };
        int expected6 = 0;

        // TC 7: Full dead end blocking the entire first row and column downstream
        int[][] tc7 = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        int expected7 = 0;

        // TC 8: Diagonal obstacles blocking all possible routing paths completely
        int[][] tc8 = {
                {0, 0, 1},
                {0, 1, 0},
                {1, 0, 0}
        };
        int expected8 = 0;

        // TC 9: Long single line row with an obstacle in between
        int[][] tc9 = {
                {0, 0, 0, 1, 0, 0}
        };
        int expected9 = 0;

        // TC 10: Highly winding corridor forcing a single mandatory path
        int[][] tc10 = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0}
        };
        int expected10 = 1;
        // Path details: Down to (2,0) -> Right to (2,2) -> Up to (0,2) -> Right to (0,4) -> Down to (2,4)
        // Note: The robot can only move either down or right. Wait, let's re-verify robot constraint!
        // "The robot can only move either down or right at any point in time."
        // Ah, let's re-verify the grid path logic for moving ONLY down or right:
        // From (0,0) down to (1,0) down to (2,0). From (2,0) right to (2,1) right to (2,2).
        // From (2,2) you cannot move up! You can only move down or right.
        // Let's change TC 10 to ensure a valid down-right path that evaluates correctly without moving up.

        int[][] tc10Fixed = {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        // Paths from (0,0) to (2,3):
        // 1. Down -> Down -> Right -> Right -> Right (via bottom edge)
        // 2. Right -> Right -> Right -> Down -> Down (via top edge)
        int expected10Fixed = 2;

        // Evaluation Array List
        TestCase[] testCases = {
                new TestCase(1, tc1, expected1),
                new TestCase(2, tc2, expected2),
                new TestCase(3, tc3, expected3),
                new TestCase(4, tc4, expected4),
                new TestCase(5, tc5, expected5),
                new TestCase(6, tc6, expected6),
                new TestCase(7, tc7, expected7),
                new TestCase(8, tc8, expected8),
                new TestCase(9, tc9, expected9),
                new TestCase(10, tc10Fixed, expected10Fixed)
        };

        // Execution Loop
        int passedCount = 0;
        System.out.println("================ UNIQUE PATHS II TEST REPORT ================\n");
        for (TestCase tc : testCases) {
            int actual = pathFinder.uniquePathsWithObstacles(tc.input);
            boolean passed = (actual == tc.expected);
            if (passed) passedCount++;

            System.out.printf("Test Case %d:\n", tc.id);
            System.out.printf("  Grid Dimension  : %d x %d\n", tc.input.length, tc.input[0].length);
            System.out.printf("  Expected Output : %d\n", tc.expected);
            System.out.printf("  Actual Output   : %d\n", actual);
            System.out.printf("  Status          : %s\n\n", passed ? "PASSED ✅" : "FAILED ❌");
        }

        System.out.println("--------------------------------------------------------------");
        System.out.printf("Final Results: %d/%d Test Cases Passed.\n", passedCount, testCases.length);
        System.out.println("==============================================================");
    }

    // Helper class to map test inputs smoothly
    private static class TestCase {
        int id;
        int[][] input;
        int expected;

        TestCase(int id, int[][] input, int expected) {
            this.id = id;
            this.input = input;
            this.expected = expected;
        }
    }
}