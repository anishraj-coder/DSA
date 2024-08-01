package Array;

import java.util.Arrays;

public class FirstMissingPositive {
	public static void main(String[] args) {
		// Test cases
		int[][] testCases = { { 1, 2, 0 }, { 3, 4, -1, 1 }, { 7, 8, 9, 11, 12 }, { 1 }, { 1, 1 }, { 1, 2, 3 },
				{ -1, -2, -3 }, { Integer.MAX_VALUE }, { 0, -1, 2, 1, 4 }, {}, // Empty array
				{ 2, 3, 4, 5, 6 }, // Missing 1
				{ 1, 1, 1, 1, 1 }, // All same positive number
				{ -1, -1, 2, 2, 3 }, // Negative and positive numbers
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, // Consecutive positive numbers
				{ 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 }, // Reverse order
				{ 1, 2, 4, 5, 6, 7 }, // Missing 3
				{ 1, 2, 3, 4, 6, 7, 8 }, // Missing 5
				{ Integer.MAX_VALUE - 1, Integer.MAX_VALUE }, // Very large numbers
				{ 0, 0, 0, 0 }, // All zeros
				{ -5, -4, -3, -2, -1 } // All negative numbers
		};

		// Expected outputs for each test case
		int[] expectedOutputs = { 3, 2, 1, 2, 2, 4, 1, 1, 3, 1, 1, 2, 1, 11, 11, 3, 5, 1, 1, 1 };

		// Create an instance of the solution class

		// Run test cases
		for (int i = 0; i < testCases.length; i++) {
			int[] nums = testCases[i];
			int expected = expectedOutputs[i];
			int result = firstMissingPositive(nums);

			System.out.println("Test Case " + (i + 1) + ":");
			System.out.println("Input: " + Arrays.toString(nums));
			System.out.println("Expected Output: " + expected);
			System.out.println("Actual Output: " + result);

			if (result == expected) {
				System.out.println("Status: PASS");
			} else {
				System.out.println("Status: FAIL");
			}
			System.out.println();
		}
	}

	static public int firstMissingPositive(int[] arr) {
		int i = 0;
		int n = arr.length;
		while (i < n) {
			if (arr[i] < 1 || arr[i] > n || arr[i] - 1 == i) {
				i++;
			} else {
				int idx = arr[i] - 1;
				if (arr[i] == arr[idx]) {
					i++;
				} else {
					int t = arr[i];
					arr[i] = arr[idx];
					arr[idx] = t;
				}
			}

		}
		for (int j = 0; j < n; j++) {
			if (arr[j] - 1 != j) {
				return j + 1;
			}
		}
		return n + 1; // Placeholder return
	}
}
