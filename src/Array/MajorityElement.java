package Array;

import java.util.Arrays;

public class MajorityElement {
	public static void main(String[] args) {
		// Test cases
		int[][] testCases = { { 3, 2, 3 }, { 2, 2, 1, 1, 1, 2, 2 }, { 1 }, { 1, 1, 1, 2, 2, 2, 1 },
				{ -1, -1, -1, 1, 1, 1, 1 }, { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE },
				{ 0, 0, 0, 1, 1, 1, 1 }, { 1, 2, 3, 4, 5, 5, 5, 5, 5 }, { 1, 1, 2, 2, 3, 3, 3, 3, 3 },
				{ -1, -1, -1, -1, -2, -2 } };

		// Expected outputs for each test case
		int[] expectedOutputs = { 3, 2, 1, 1, 1, Integer.MAX_VALUE, 1, 5, 3, -1 };

		// Create an instance of the solution class

		// Run test cases
		for (int i = 0; i < testCases.length; i++) {
			int[] nums = testCases[i];
			int expected = expectedOutputs[i];
			int result = majorityElement(nums);

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

	public static int majorityElement(int[] arr) {
		int val = arr[0];
		int count = 0;
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			if (arr[i] == val) {
				count = count + 1;
			} else {
				if (count == 0) {
					count = 1;
					val = arr[i];
				} else {
					count--;
				}
			}

		}
		return val;
	}
}
