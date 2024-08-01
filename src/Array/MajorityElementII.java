package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MajorityElementII {
	public static void main(String[] args) {
		// Test cases
		int[][] testCases = { { 3, 2, 3 }, { 1 }, { 1, 2 }, { 1, 2, 3, 4 }, { 1, 1, 1, 3, 3, 2, 2, 2 },
				{ 1, 1, 1, 1, 1, 2, 2, 2 }, { 1, 2, 2, 3, 2, 1, 1, 3 }, { 1, 2, 3, 1, 2, 3, 1, 2, 3 },
				{ 3, 3, 3, 3, 3, 3 }, { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5 } };

		// Expected outputs for each test case
		Integer[][] expectedOutputs = { { 3 }, { 1 }, { 1, 2 }, {}, { 1, 2 }, { 1, 2 }, { 1, 2 }, { 1, 2, 3 }, { 3 },
				{} };

		// Create an instance of the solution class

		// Run test cases
		for (int i = 0; i < testCases.length; i++) {
			int[] nums = testCases[i];
			List<Integer> expected = Arrays.asList(expectedOutputs[i]);
			List<Integer> result = majorityElement(nums);

			System.out.println("Test Case " + (i + 1) + ":");
			System.out.println("Input: " + Arrays.toString(nums));
			System.out.println("Expected Output: " + expected);
			System.out.println("Actual Output: " + result);

			if (result.size() == expected.size() && result.containsAll(expected)) {
				System.out.println("Status: PASS");
			} else {
				System.out.println("Status: FAIL");
			}
			System.out.println();
		}
	}

	static public List<Integer> majorityElement(int[] arr) {
		int val1 = arr[0];
		int count1 = 1;
		int val2 = arr[0];
		int count2 = 0;
		int n = arr.length;
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int i = 1; i < n; i++) {
			if (val1 == arr[i]) {
				count1++;
			} else if (val2 == arr[i]) {
				count2++;
			} else if (count1 == 0) {
				val1 = arr[i];
				count1 = 1;
			} else if (count2 == 0) {
				val2 = arr[i];
				count2 = 1;
			} else {
				count1--;
				count2--;
			}
		}
		int c1 = 0, c2 = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] == val1 && count1 > 0)
				c1++;
			if (arr[i] == val2 && count2 > 0)
				c2++;
		}
		if (c1 > (n / 3))
			ans.add(val1);
		if (c2 > (n / 3))
			ans.add(val2);
		return ans; // Placeholder return
	}
}
