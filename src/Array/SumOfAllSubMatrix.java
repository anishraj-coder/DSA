package Array;

public class SumOfAllSubMatrix {
	public static void main(String[] args) {
		// Test case 1: 2x2 matrix
		int[][] matrix1 = { { 1, 2 }, { 3, 4 } };
		testCase(1, matrix1, 40);

		// Test case 2: 3x3 matrix
		int[][] matrix2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		testCase(2, matrix2, 1215);

		// Test case 3: 2x3 matrix
		int[][] matrix3 = { { 1, 2, 3 }, { 4, 5, 6 } };
		testCase(3, matrix3, 168);

		// Test case 4: 1x1 matrix
		int[][] matrix4 = { { 5 } };
		testCase(4, matrix4, 5);

		// Test case 5: 3x2 matrix
		int[][] matrix5 = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
		testCase(5, matrix5, 168);

		// Test case 6: 4x4 matrix
		int[][] matrix6 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		testCase(6, matrix6, 13160);

		// Test case 7: 2x4 matrix
		int[][] matrix7 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
		testCase(7, matrix7, 540);

		// Test case 8: 4x2 matrix
		int[][] matrix8 = { { 1, 2 }, { 3, 4 }, { 5, 6 }, { 7, 8 } };
		testCase(8, matrix8, 540);

		// Test case 9: 3x4 matrix
		int[][] matrix9 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		testCase(9, matrix9, 2470);

		// Test case 10: 5x5 matrix
		int[][] matrix10 = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 },
				{ 21, 22, 23, 24, 25 } };
		testCase(10, matrix10, 72925);
	}

	private static void testCase(int caseNumber, int[][] matrix, long expectedOutput) {
		System.out.println("Test Case " + caseNumber + ":");
		System.out.println("Input:");
		printMatrix(matrix);
		System.out.println("Expected Output: " + expectedOutput);
		System.out.println("Actual Output: " + sumOfAllSubmatrices(matrix));
		System.out.println();
	}

	private static void printMatrix(int[][] matrix) {
		for (int[] row : matrix) {
			for (int num : row) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}

	private static int sumOfAllSubmatrices(int[][] matrix) {
		int ans = 0;
		int n = matrix.length;
		int m = matrix[0].length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int occ = (i + 1) * (j + 1) * (n - i) * (m - j);

				ans = ans + (occ * matrix[i][j]);
			}
		}

		return ans; // Placeholder return value
	}
}