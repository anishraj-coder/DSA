package DynamicProgramming;

import java.util.Arrays;

/* Unique Paths
Solved
Medium
Topics
Companies
There is a robot on an m x n grid. The robot is initially located at the 
top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right
 corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right
  at any point in time.

Given the two integers m and n, return the number of possible unique paths that
 the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 
2 * 109.

 

Example 1:


Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach 
the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down*/

public class UniquePaths {

	public static void main(String[] args) {
		int m1 = 3, n1 = 7;

		System.out.println("Example 1:");
		System.out.println("Input: m = " + m1 + ", n = " + n1);
		System.out.println("Output: " + uniquePaths(m1, n1));

		int m2 = 3, n2 = 2;
		System.out.println("\nExample 2:");
		System.out.println("Input: m = " + m2 + ", n = " + n2);
		System.out.println("Output: " + uniquePaths(m2, n2));

	}

	public static int uniquePaths(int i, int j) {
		int[][] dp = new int[i][j];
		for (int k = 0; k < dp.length; k++)
			Arrays.fill(dp[k], -1);
		int ans = uniquePathhelper(dp, i - 1, j - 1);
		return ans;
	}

	public static int uniquePathhelper(int[][] dp, int i, int j) {
		if (i == 0 || j == 0)
			return 1;
		if (dp[i][j] != -1)
			return dp[i][j];
		int x = uniquePathhelper(dp, i - 1, j);
		int y = uniquePathhelper(dp, i, j - 1);
		dp[i][j] = x + y;
		return x + y;

	}
}
