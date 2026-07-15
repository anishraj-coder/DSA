package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

    /**
     * Given an m x n grid representing the state of oranges:
     * 0 = empty cell, 1 = fresh orange, 2 = rotten orange.
     * * Returns the minimum number of minutes until no cell has a fresh orange.
     * If this is impossible, returns -1.
     */
    public int orangesRotting(int[][] arr) {
        int m=arr.length,n=arr[0].length;
        Queue<Pair>q=new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]==2){
                    q.offer(new Pair(i,j,0));
                }
            }
        }
        if(q.isEmpty())return -1;
        int[][]dir=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        int max=Integer.MIN_VALUE;
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                Pair rem=q.poll();
                max=Math.max(rem.t,max);
                for(int[]d:dir){
                    int row=rem.i+d[0],col=rem.j+d[1],t=rem.t+1;
                    if(row>=0&&row<m&&col>=0&&col<n&&arr[row][col]!=2&&arr[row][col]!=0){
                        arr[row][col]=2;
                        q.offer(new Pair(row,col,t));
                    }
                }
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]==1){
                    return -1;
                }
            }
        }

        return max;
    }

    static class Pair{
        int i,j,t;
        Pair(int i,int j,int t){
            this.i=i;
            this.j=j;
            this.t=t;
        }
    }

    public static void main(String[] args) {
        RottingOranges solver = new RottingOranges();
        int passed = 0;
        int total = 10;

        // Test Case 1: Standard BFS propagation (Example 1)
        int[][] grid1 = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        passed += runTest(1, solver, grid1, 4);

        // Test Case 2: Impossible to rot all due to separation (Example 2)
        int[][] grid2 = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };
        passed += runTest(2, solver, grid2, -1);

        // Test Case 3: Already no fresh oranges at minute 0 (Example 3)
        int[][] grid3 = {{0, 2}};
        passed += runTest(3, solver, grid3, 0);

        // Test Case 4: No fresh and no rotten oranges (entirely empty grid)
        int[][] grid4 = {
                {0, 0, 0},
                {0, 0, 0}
        };
        passed += runTest(4, solver, grid4, 0);

        // Test Case 5: Grid with only fresh oranges (impossible to start rotting)
        int[][] grid5 = {
                {1, 1},
                {1, 1}
        };
        passed += runTest(5, solver, grid5, -1);

        // Test Case 6: Multiple rotten sources progressing simultaneously
        int[][] grid6 = {
                {2, 1, 0, 1, 2},
                {1, 0, 1, 0, 1},
                {0, 1, 1, 1, 0}
        };
        passed += runTest(6, solver, grid6, 2);

        // Test Case 7: Single cell grid - Fresh orange
        int[][] grid7 = {{1}};
        passed += runTest(7, solver, grid7, -1);

        // Test Case 8: Single cell grid - Rotten orange
        int[][] grid8 = {{2}};
        passed += runTest(8, solver, grid8, 0);

        // Test Case 9: Long linear snake-like chain of fresh oranges
        int[][] grid9 = {
                {2, 1, 1, 1},
                {0, 0, 0, 1},
                {1, 1, 1, 1}
        };
        passed += runTest(9, solver, grid9, 7);

        // Test Case 10: Large dense grid where rot spreads outwards fully
        int[][] grid10 = {
                {2, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        passed += runTest(10, solver, grid10, 8);

        System.out.println("\n-------------------------------------------");
        System.out.println("Final Result: " + passed + " / " + total + " Test Cases Passed.");
        System.out.println("-------------------------------------------");
    }

    private static int runTest(int testNum, RottingOranges solver, int[][] grid, int expected) {
        // Deep copy the grid to present input cleanly without modifications showing
        int[][] gridCopy = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            gridCopy[i] = grid[i].clone();
        }

        int actual = solver.orangesRotting(grid);
        boolean isPassed = (actual == expected);

        System.out.println("Test Case " + testNum + ":");
        System.out.println("  Input Grid: " + Arrays.deepToString(gridCopy));
        System.out.println("  Expected Output: " + expected);
        System.out.println("  Actual Output:   " + actual);
        System.out.println("  Status:          " + (isPassed ? "PASSED ✅" : "FAILED ❌"));
        System.out.println();

        return isPassed ? 1 : 0;
    }
}