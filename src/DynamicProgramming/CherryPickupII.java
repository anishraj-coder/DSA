package DynamicProgramming;

import java.util.Arrays;

public class CherryPickupII {

    /**
     * Given a rows x cols matrix grid representing a field of cherries,
     * returns the maximum number of cherries collected by both robots.
     * * Robot #1 starts at (0, 0) and Robot #2 starts at (0, cols - 1).
     */
    public int cherryPickup(int[][] arr) {
        int m=arr.length,n=arr[0].length;
        int[][][]dp=new int[n+1][m+1][m+1];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Arrays.fill(dp[i][j],-1);
            }
        }
        return helper(0,0,n-1,arr,dp);
    }

    public int helper(int i,int j1,int j2,int[][]arr,int[][][]dp){
        int m=arr.length,n=arr[0].length;
        if(i>=m||j1<0||j1>=n||j2<0||j2>=n)return (int) -1e8;
        if(dp[i][j1][j2]!=-1)return dp[i][j1][j2];
        if(i==m-1){
            if(j1==j2)return dp[i][j1][j2]= arr[i][j1];
            else return dp[i][j1][j1]= arr[i][j1]+arr[i][j2];
        }

        int max=(int)-1e8;

        for(int d1=-1;d1<=1;d1++){
            for(int d2=-1;d2<=1;d2++){
                int col1=j1+d1,col2=j2+d2;
                int val=0;
                if(j1==j2) val+=helper(i+1,col1,col2,arr,dp)+arr[i][j1];
                else val+=helper(i+1,col1,col2,arr,dp)+arr[i][j1]+arr[i][j2];
                max=Math.max(val,max);
            }
        }

        return dp[i][j1][j2]= max;
    }

    public static void main(String[] args) {
        CherryPickupII solver = new CherryPickupII();

        // Test Cases Setup
        int[][][] testGrids = {
                // Case 1: Standard example case from description
                {
                        {3, 1, 1},
                        {2, 5, 1},
                        {1, 5, 5},
                        {2, 1, 1}
                },
                // Case 2: Minimal 3-column grid (Robots must converge or pick smartly)
                {
                        {1, 0, 0},
                        {0, 0, 0},
                        {0, 0, 1}
                },
                // Case 3: Grid with zeroes, paths completely disjoint
                {
                        {1, 0, 0, 0, 0, 1},
                        {2, 0, 0, 0, 0, 2},
                        {1, 0, 0, 0, 0, 1}
                },
                // Case 4: Forced overlap on a small grid (cols = 3)
                {
                        {1, 1, 1},
                        {1, 10, 1},
                        {1, 1, 1}
                },
                // Case 5: Large values where prioritizing greedily fails
                {
                        {0, 8, 0, 0, 8, 0},
                        {5, 0, 0, 0, 0, 7},
                        {0, 9, 0, 0, 9, 0}
                },
                // Case 6: 1-row grid (Immediate collection, edge case)
                {
                        {4, 3, 2, 1, 10}
                },
                // Case 7: Only 2 columns (Robots start next to each other and immediately cross/overlap)
                {
                        {5, 5},
                        {2, 3},
                        {10, 0}
                },
                // Case 8: Diagonal tracks perfectly matching both robots' moves
                {
                        {1, 0, 0, 0, 1},
                        {0, 1, 0, 1, 0},
                        {0, 0, 2, 0, 0}
                },
                // Case 9: Dead-ends/Trap paths where high initial gain leads to a poor finish
                {
                        {1, 1, 1, 1},
                        {10, 0, 0, 10},
                        {0, 0, 0, 0},
                        {1, 2, 2, 1}
                },
                // Case 10: Complete zero field
                {
                        {0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                }
        };

        int[] expectedOutputs = {
                24, // Case 1
                2,  // Case 2
                7,  // Case 3
                15, // Case 4
                38, // Case 5
                14, // Case 6
                20, // Case 7
                6,  // Case 8
                25, // Case 9
                0   // Case 10
        };

        // Execution and Verification
        int passed = 0;
        int failed = 0;

        System.out.println("Running Test Cases for Cherry Pickup II...\n");
        for (int i = 0; i < testGrids.length; i++) {
            int expected = expectedOutputs[i];

            // Deep copy to prevent users from accidentally corrupting original test data
            int[][] gridCopy = new int[testGrids[i].length][];
            for (int j = 0; j < testGrids[i].length; j++) {
                gridCopy[j] = testGrids[i][j].clone();
            }

            int actual = solver.cherryPickup(gridCopy);
            boolean isCorrect = (actual == expected);

            System.out.printf("Test Case %2d: ", (i + 1));
            if (isCorrect) {
                System.out.print("[PASSED] ");
                passed++;
            } else {
                System.out.print("[FAILED] ");
                failed++;
            }

            System.out.printf("Dimensions: %dx%d | Expected: %2d | Actual: %2d\n",
                    testGrids[i].length, testGrids[i][0].length, expected, actual);
        }

        System.out.println("\n-------------------------------------------");
        System.out.printf("Total Test Cases: %d | Passed: %d | Failed: %d\n",
                testGrids.length, passed, failed);
        System.out.println("-------------------------------------------");
    }
}