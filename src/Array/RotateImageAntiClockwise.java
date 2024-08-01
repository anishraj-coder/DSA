package Array;

import java.util.Arrays;

public class RotateImageAntiClockwise {
	public static void main(String[] args) {
		// Test case 1
		int[][] matrix1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println("Test Case 1:");
		System.out.println("Input:");
		printMatrix(matrix1);
		rotateAntiClockwise(matrix1);
		System.out.println("Output (Rotated 90 degrees anti-clockwise):");
		printMatrix(matrix1);
		System.out.println();

		// Test case 2
		int[][] matrix2 = { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };
		System.out.println("Test Case 2:");
		System.out.println("Input:");
		printMatrix(matrix2);
		rotateAntiClockwise(matrix2);
		System.out.println("Output (Rotated 90 degrees anti-clockwise):");
		printMatrix(matrix2);
		System.out.println();

		// Test case 3 (1x1 matrix)
		int[][] matrix3 = { { 1 } };
		System.out.println("Test Case 3 (1x1 matrix):");
		System.out.println("Input:");
		printMatrix(matrix3);
		rotateAntiClockwise(matrix3);
		System.out.println("Output (Rotated 90 degrees anti-clockwise):");
		printMatrix(matrix3);
		System.out.println();

		// Test case 4 (2x2 matrix)
		int[][] matrix4 = { { 1, 2 }, { 3, 4 } };
		System.out.println("Test Case 4 (2x2 matrix):");
		System.out.println("Input:");
		printMatrix(matrix4);
		rotateAntiClockwise(matrix4);
		System.out.println("Output (Rotated 90 degrees anti-clockwise):");
		printMatrix(matrix4);
		System.out.println();

		// Test case 5 (matrix with negative numbers)
		int[][] matrix5 = { { -1, -2, -3 }, { -4, -5, -6 }, { -7, -8, -9 } };
		System.out.println("Test Case 5 (matrix with negative numbers):");
		System.out.println("Input:");
		printMatrix(matrix5);
		rotateAntiClockwise(matrix5);
		System.out.println("Output (Rotated 90 degrees anti-clockwise):");
		printMatrix(matrix5);
	}

	// Method to rotate the matrix anti-clockwise (to be implemented)
	public static void rotateAntiClockwise(int[][] matrix) {
		// Implementation goes here
		transpose(matrix);
		reverseArray(matrix);
	}

	public static void reverseArray(int[][] arr) {
		for (int i = 0; i < arr.length / 2; i++) {
			int[] t = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - 1 - i] = t;
		}
	}

	public static void transpose(int[][] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
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