package Array;

import java.util.Arrays;

public class MaximumProductSubarray {
	public int maxProduct(int[] arr) {
		int n = arr.length;
		long prefixProduct = 1l;
		long suffixProduct = 1l;
		long max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (prefixProduct == 0) {
				prefixProduct = arr[i];
			} else {
				prefixProduct = prefixProduct * arr[i];
			}
			max1 = (max1 > prefixProduct) ? max1 : prefixProduct;
		}
		for (int i = n - 1; i >= 0; i--) {
			if (suffixProduct == 0) {
				suffixProduct = arr[i];

			} else {
				suffixProduct = suffixProduct * arr[i];
			}
			max2 = (max2 > suffixProduct) ? max2 : suffixProduct;
		}

		return (int) Math.max(max1, max2); // Placeholder return
	}

	public static void main(String[] args) {
		MaximumProductSubarray solution = new MaximumProductSubarray();

		// Test case 1: Given example
		testCase(solution, new int[] { 2, 3, -2, 4 }, 6);

		// Test case 2: Given example
		testCase(solution, new int[] { -2, 0, -1 }, 0);

		// Test case 3: All positive numbers
		testCase(solution, new int[] { 1, 2, 3, 4 }, 24);

		// Test case 4: All negative numbers
		testCase(solution, new int[] { -1, -2, -3, -4 }, 24);

		// Test case 5: Mix of positive and negative with zero
		testCase(solution, new int[] { -2, 3, -4, 0, 5, -6 }, 120);

		// Test case 6: Single element array
		testCase(solution, new int[] { 5 }, 5);

		// Test case 7: Two negative numbers
		testCase(solution, new int[] { -1, -2 }, 2);

		// Test case 8: Array with zeros
		testCase(solution, new int[] { 0, 2, 3, 0, -2, 4 }, 6);

		// Test case 9: Alternating positive and negative
		testCase(solution, new int[] { 1, -2, 3, -4, 5, -6 }, 120);

		// Test case 10: Large numbers within constraints
		testCase(solution, new int[] { 10, -10, 10, -10 }, 10000);

		// Test case 11: Negative number followed by zero
		testCase(solution,
				new int[] { 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, -10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 0 },
				1000000000);

		// Test case 12: Long array with small numbers
		int[] longArray = new int[20000];
		Arrays.fill(longArray, 1);
		longArray[10000] = 2;
		testCase(solution, longArray, 2);
	}

	private static void testCase(MaximumProductSubarray solution, int[] nums, int expected) {
		int result = solution.maxProduct(nums);
		System.out.println("Input: nums = " + Arrays.toString(nums));
		System.out.println("Expected Output: " + expected);
		System.out.println("Actual Output: " + result);
		System.out.println("Result: " + (result == expected ? "PASS" : "FAIL"));
		System.out.println();
	}
}