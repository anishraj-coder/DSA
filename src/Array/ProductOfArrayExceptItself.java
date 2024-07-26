package Array;

import java.util.Arrays;

public class ProductOfArrayExceptItself {
	public static void main(String[] args) {
		// List of test cases
		int[][] testCases = { { 1, 2, 3, 4 }, // Example 1
				{ -1, 1, 0, -3, 3 }, // Example 2
				{ 2, 3, 4, 5 }, // Additional
				{ 1, 0 }, // Additional
				{ 5, 9 }, // Additional
				{ 1, 1, 1, 1 }, // All ones
				{ 0, 0, 0, 0 }, // All zeros
				{ 1, -1, 1, -1 }, // Alternating signs
				{ 2, 2, 2, 2 }, // All same non-zero
				{ -1, -2, -3, -4 }, // All negatives
				{ 1, 2, 0, 4, 5 }, // Single zero
				{ 0, 1, 2, 3, 4 }, // Leading zero
				{ 1, 2, 3, 4, 0 }, // Trailing zero
				{ 0, 1, 2, 0, 4 }, // Multiple zeros
				{ 1, 2, 3, -4, -5 }, // Mixed signs
		};

		// List of expected outputs
		int[][] expectedOutputs = { { 24, 12, 8, 6 }, // Example 1
				{ 0, 0, 9, 0, 0 }, // Example 2
				{ 60, 40, 30, 24 }, // Additional
				{ 0, 1 }, // Additional
				{ 9, 5 }, // Additional
				{ 1, 1, 1, 1 }, // All ones
				{ 0, 0, 0, 0 }, // All zeros
				{ 1, -1, 1, -1 }, // Alternating signs
				{ 8, 8, 8, 8 }, // All same non-zero
				{ -24, -12, -8, -6 }, // All negatives
				{ 0, 0, 40, 0, 0 }, // Single zero
				{ 24, 0, 0, 0, 0 }, // Leading zero
				{ 0, 0, 0, 0, 24 }, // Trailing zero
				{ 0, 0, 0, 0, 0 }, // Multiple zeros
				{ 120, 60, 40, -30, -24 }, // Mixed signs
		};

		// Loop through all test cases
		for (int i = 0; i < testCases.length; i++) {
			int[] nums = testCases[i];
			int[] result = productExceptSelf(nums);
			System.out.println("Test case " + (i + 1) + ":");
			System.out.println("Input: " + Arrays.toString(nums));
			System.out.println("Output: " + Arrays.toString(result));
			System.out.println("Expected: " + Arrays.toString(expectedOutputs[i]) + "\n");
		}
	}

	// Dummy implementation of productExceptSelf for compilation
	// Replace this with your actual implementation
	public static int[] productExceptSelf(int[] nums) {
		int n = nums.length;
		if (n <= 1)
			return nums;
		int[] pre = preProduct(nums);
		int right = nums[n - 1];
		pre[n - 1] = pre[n - 2];
		for (int i = n - 2; i >= 1; i--) {
			pre[i] = pre[i - 1] * right;
			right *= nums[i];
		}
		pre[0] = right;
		return pre;
	}

	public static int[] preProduct(int[] arr) {
		int n = arr.length;
		if (n <= 1)
			return arr;
		int[] pre = new int[n];
		pre[0] = arr[0];
		for (int i = 1; i < n; i++) {
			pre[i] = pre[i - 1] * arr[i];
		}
		return pre;
	}
}