package Array;

import java.util.Arrays;

public class MaximumSubarray {
	public int maxSubArray(int[] arr) {
		int sum = 0;
		int n = arr.length;
		int ans = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (sum < 0) {
				sum = arr[i];
			} else {
				sum += arr[i];
			}
			ans = Math.max(ans, sum);

		}
		return ans;
	}

	public static void main(String[] args) {
		MaximumSubarray solution = new MaximumSubarray();

		// Test case 1
		int[] nums1 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		testCase(solution, nums1, 6);

		// Test case 2
		int[] nums2 = { 1 };
		testCase(solution, nums2, 1);

		// Test case 3
		int[] nums3 = { 5, 4, -1, 7, 8 };
		testCase(solution, nums3, 23);

		// Test case 4: All negative numbers
		int[] nums4 = { -1, -2, -3, -4 };
		testCase(solution, nums4, -1);

		// Test case 5: All positive numbers
		int[] nums5 = { 1, 2, 3, 4, 5 };
		testCase(solution, nums5, 15);

		// Test case 6: Alternating positive and negative
		int[] nums6 = { 1, -1, 1, -1, 1 };
		testCase(solution, nums6, 1);

		// Test case 7: Large numbers
		int[] nums7 = { 1000, -500, 1500, -2000, 3000 };
		testCase(solution, nums7, 3000);

		// Test case 8: Zeros and negative numbers
		int[] nums8 = { 0, -1, 0, -2, 0 };
		testCase(solution, nums8, 0);

		// Test case 9: Single negative number
		int[] nums9 = { -5 };
		testCase(solution, nums9, -5);

		// Test case 10: Long sequence with small numbers
		int[] nums10 = new int[100];
		Arrays.fill(nums10, 1);
		nums10[50] = -1000;
		testCase(solution, nums10, 99);
	}

	private static void testCase(MaximumSubarray solution, int[] nums, int expected) {
		int result = solution.maxSubArray(nums);
		System.out.println("Input: nums = " + Arrays.toString(nums));
		System.out.println("Expected Output: " + expected);
		System.out.println("Actual Output: " + result);
		System.out.println("Result: " + (result == expected ? "PASS" : "FAIL"));
		System.out.println();
	}
}