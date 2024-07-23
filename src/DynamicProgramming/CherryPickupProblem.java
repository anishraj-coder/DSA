package DynamicProgramming;

/*Cherry Pickup
Solved
Hard
Topics
Companies
You are given an n x n grid representing a field of cherries, each cell is one of three possible integers.

0 means the cell is empty, so you can pass through,
1 means the cell contains a cherry that you can pick up and pass through, or
-1 means the cell contains a thorn that blocks your way.
Return the maximum number of cherries you can collect by following the rules below:

Starting at the position (0, 0) and reaching (n - 1, n - 1) by moving right or down through valid path cells (cells with value 0 or 1).
After reaching (n - 1, n - 1), returning to (0, 0) by moving left or up through valid path cells.
When passing through a path cell containing a cherry, you pick it up, and the cell becomes an empty cell 0.
If there is no valid path between (0, 0) and (n - 1, n - 1), then no cherries can be collected.
 

Example 1:


Input: grid = [[0,1,-1],[1,0,-1],[1,1,1]]
Output: 5
Explanation: The player started at (0, 0) and went down, down, right right to reach (2, 2).
4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
Then, the player went left, up, up, left to return home, picking up one more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.
Example 2:

Input: grid = [[1,1,-1],[1,-1,1],[-1,1,1]]
Output: 0
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 50
grid[i][j] is -1, 0, or 1.
grid[0][0] != -1
grid[n - 1][n - 1] != -1*/

public class CherryPickupProblem {
	public static void main(String[] args) {

		// Test Case 1
		int[][] grid1 = { { 0, 1, -1 }, { 1, 0, -1 }, { 1, 1, 1 } };
		System.out.println("Test Case 1:");
		System.out.println("Input:");
		printGrid(grid1);
		int result1 = cherryPickup(grid1);
		System.out.println("Output: " + result1);
		System.out.println();

		// Test Case 2
		int[][] grid2 = { { 1, 1, -1 }, { 1, -1, 1 }, { -1, 1, 1 } };
		System.out.println("Test Case 2:");
		System.out.println("Input:");
		printGrid(grid2);
		int result2 = CherryPickupProblem.cherryPickup(grid2);
		System.out.println("Output: " + result2);
		System.out.println();

		// Additional Test Case 3
		int[][] grid3 = { { 0, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
		System.out.println("Additional Test Case 3:");
		System.out.println("Input:");
		printGrid(grid3);
		int result3 = CherryPickupProblem.cherryPickup(grid3);
		System.out.println("Output: " + result3);
		System.out.println();
	}

	// Helper method to print grid
	private static void printGrid(int[][] grid) {
		for (int[] row : grid) {
			for (int cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}

	public static int cherryPickup(int[][] grid) {
		int[][][] dp = new int[grid.length][grid[0].length][grid.length];
		int ans = cherryPickuphelper(grid, 0, 0, 0, dp);
		if (ans == Integer.MIN_VALUE)
			return 0;
		else
			return ans;
	}

	public static int cherryPickuphelper(int[][] grid, int row1, int col1, int row2, int[][][] dp) {
		int col2 = col1 + row1 - row2;

		if (row1 >= grid.length || row2 >= grid.length || col2 >= grid.length || col1 >= grid.length
				|| grid[row1][col1] == -1 || grid[row2][col2] == -1) {
			return Integer.MIN_VALUE;
		}
		if (dp[row1][col1][row2] != 0)
			return dp[row1][col1][row2];
		if (row1 == grid.length - 1 && row2 == grid.length - 1 && col1 == grid.length - 1 && col2 == grid.length - 1)
			return grid[row1][col1];
		int temp1 = cherryPickuphelper(grid, row1, col1 + 1, row2, dp);
		int temp2 = cherryPickuphelper(grid, row1 + 1, col1, row2 + 1, dp);
		int temp3 = cherryPickuphelper(grid, row1 + 1, col1, row2, dp);
		int temp4 = cherryPickuphelper(grid, row1, col1 + 1, row2 + 1, dp);
		int ans = Math.max(Math.max(temp1, temp2), Math.max(temp3, temp4));
		int contri = 0;
		if (row1 == row2 && col1 == col2)
			contri = grid[row1][col1];
		else
			contri = grid[row1][col1] + grid[row2][col2];
		if (ans == Integer.MIN_VALUE)
			ans = Integer.MIN_VALUE;
		else
			ans = ans + contri;
		dp[row1][col1][row1] = ans;
		return ans;
	}

}
