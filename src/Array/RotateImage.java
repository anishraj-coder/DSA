package Array;

import java.util.Arrays;

public class RotateImage {
	public static void main(String[] args) {
		// Test case 1
		int[][] matrix1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println("Test Case 1:");
		System.out.println("Input:");
		printMatrix(matrix1);
		rotate(matrix1);
		System.out.println("Output:");
		printMatrix(matrix1);
		System.out.println();

		// Test case 2
		int[][] matrix2 = { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };
		System.out.println("Test Case 2:");
		System.out.println("Input:");
		printMatrix(matrix2);
		rotate(matrix2);
		System.out.println("Output:");
		printMatrix(matrix2);
		System.out.println();

		// Additional test case (1x1 matrix)
		int[][] matrix3 = { { 1 } };
		System.out.println("Test Case 3 (1x1 matrix):");
		System.out.println("Input:");
		printMatrix(matrix3);
		rotate(matrix3);
		System.out.println("Output:");
		printMatrix(matrix3);
		System.out.println();

		// Additional test case (2x2 matrix)
		int[][] matrix4 = { { 1, 2 }, { 3, 4 } };
		System.out.println("Test Case 4 (2x2 matrix):");
		System.out.println("Input:");
		printMatrix(matrix4);
		rotate(matrix4);
		System.out.println("Output:");
		printMatrix(matrix4);
	}

	// Method to rotate the matrix (to be implemented)
	public static void rotate(int[][] matrix) {
		reverseArray(matrix);
		transpose(matrix);
	}

	public static void reverseArray(int[][] arr) {
		int s = 0;
		int e = arr.length - 1;
		while (s < e) {
			int[] t = arr[s];
			arr[s] = arr[e];
			arr[e] = t;
			s++;
			e--;
		}
	}

	public static void transpose(int[][] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				int t = arr[i][j];
				arr[i][j] = arr[j][i];
				arr[j][i] = t;
			}
		}
	}

	// Helper method to print the matrix
	public static void printMatrix(int[][] matrix) {
		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}
}