package DynamicProgramming;

import java.util.Arrays;

/*The problem is to count all the possible paths from top left to bottom right 
 * of a MxN matrix with the constraints that from each cell you can either move 
 * to right or down.

mat[i][j] == 0 represents blocked cell and mat[i][j] == 1 represents unblocked 
cell and you can't move via blocked cell.
Input Format

The first line contains the number of rows N and number of columns M of matrix.
Each of next N rows contain m numbers representing the elements of array.
Constraints

1 <= m,n <=100
Output Format

Print the number of possible paths
Sample Input 0

3 3
1 0 1
1 1 1
1 0 1
Sample Output 0

1*/

public class CountPathBlockage {
	public static void printMatrix(int[][] matrix) {
		for (int[] row : matrix) {
			for (int num : row) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] mat1 = { { 1, 0, 1 }, { 1, 1, 1 }, { 1, 0, 1 } };
		System.out.println("Test Case 1:");
		System.out.println("Matrix:");
		printMatrix(mat1);
		System.out.println("Number of paths: " + countPaths(mat1));

		// Test Case 2
		int[][] mat2 = { { 1, 1, 0 }, { 1, 0, 1 }, { 0, 1, 1 } };
		System.out.println("\nTest Case 2:");
		System.out.println("Matrix:");
		printMatrix(mat2);
		System.out.println("Number of paths: " + countPaths(mat2));

	}

	static int countPaths(int[][] mat) {
		int[][] dp = new int[mat.length][mat[0].length];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);

		}

		int ans = countPathHelper(mat, mat.length - 1, mat[0].length - 1, dp);
		return ans;
	}

	static int countPathHelper(int[][] mat, int i, int j, int[][] dp) {
		if (i < 0 || j < 0)
			return 0;
		if (mat[i][j] == 0)
			return 0;
		if (i == 0 && j == 0)
			return 1;
		if (dp[i][j] != -1)
			return dp[i][j];
		int x = countPathHelper(mat, i - 1, j, dp);
		int y = countPathHelper(mat, i, j - 1, dp);
		dp[i][j] = x + y;
		return x + y;
	}
}
