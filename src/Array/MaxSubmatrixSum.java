package Array;

public class MaxSubmatrixSum {
	public static int[][] Presum(int[][] arr) {
		int n = arr.length;
		int m = arr[0].length;
		int[][] presum = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (j == 0) {
					presum[i][j] = arr[i][j];
				} else {
					presum[i][j] = presum[i][j - 1] + arr[i][j];
				}
			}
		}
		for (int j = 0; j < m; j++) {
			for (int i = 1; i < n; i++) {
				presum[i][j] = presum[i - 1][j] + presum[i][j];
			}
		}
		return presum;
	}

	// Function to find the sum of the submatrix with the maximum sum
	public static int maxSumSubmatrix(int[][] mat, int n, int m) {
		int[][] presum = Presum(mat);
		int max = Integer.MIN_VALUE;
		n = mat.length;
		m = mat[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int temp = sumRegion(i, j, n - 1, m - 1, presum);
				max = Math.max(max, temp);
			}
		}
		return max;
	}

	public static int sumRegion(int i, int j, int k, int l, int[][] presum) {
		int sum = presum[k][l];
		if (i - 1 >= 0) {
			sum -= presum[i - 1][k];
		}
		if (j - 1 >= 0) {
			sum -= presum[k][j - 1];
		}
		if (i - 1 >= 0 && j - 1 >= 0) {
			sum += presum[i - 1][j - 1];
		}
		return sum;
	}

	public static void main(String[] args) {
		// Test Case 1
		int[][] mat1 = { { -10, -5, 3 }, { -8, 4, 10 }, { 1, 6, 14 } };
		int expectedOutput1 = 34;
		int actualOutput1 = maxSumSubmatrix(mat1, 3, 3);
		System.out.println("Test Case 1: Expected = " + expectedOutput1 + ", Actual = " + actualOutput1 + " -> "
				+ (actualOutput1 == expectedOutput1 ? "Passed" : "Failed"));

		// Test Case 2
		int[][] mat2 = { { 1, 2, -1, -4, -20 }, { -8, -3, 4, 2, 1 }, { 3, 8, 10, 1, 3 }, { -4, -1, 1, 7, -6 } };
		int expectedOutput2 = 29; // Replace with the correct expected output
		int actualOutput2 = maxSumSubmatrix(mat2, 4, 5);
		System.out.println("Test Case 2: Expected = " + expectedOutput2 + ", Actual = " + actualOutput2 + " -> "
				+ (actualOutput2 == expectedOutput2 ? "Passed" : "Failed"));

		// Test Case 3
		int[][] mat3 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int expectedOutput3 = 45;
		int actualOutput3 = maxSumSubmatrix(mat3, 3, 3);
		System.out.println("Test Case 3: Expected = " + expectedOutput3 + ", Actual = " + actualOutput3 + " -> "
				+ (actualOutput3 == expectedOutput3 ? "Passed" : "Failed"));

		// Test Case 4
		int[][] mat4 = { { -1, -2, -3 }, { -4, -5, -6 }, { -7, -8, -9 } };
		int expectedOutput4 = -1;
		int actualOutput4 = maxSumSubmatrix(mat4, 3, 3);
		System.out.println("Test Case 4: Expected = " + expectedOutput4 + ", Actual = " + actualOutput4 + " -> "
				+ (actualOutput4 == expectedOutput4 ? "Passed" : "Failed"));

		// Test Case 5
		int[][] mat5 = { { 10, 20, 30 }, { 40, 50, 60 }, { 70, 80, 90 } };
		int expectedOutput5 = 450;
		int actualOutput5 = maxSumSubmatrix(mat5, 3, 3);
		System.out.println("Test Case 5: Expected = " + expectedOutput5 + ", Actual = " + actualOutput5 + " -> "
				+ (actualOutput5 == expectedOutput5 ? "Passed" : "Failed"));
	}
}