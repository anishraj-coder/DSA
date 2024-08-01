package Math;

public class SubarraysWithGCDEqualToK {
	public static void main(String[] args) {
		// Test case 1: Given example 1
		testCase(new int[] { 9, 3, 1, 2, 6, 3 }, 3, 4);

		// Test case 2: Given example 2
		testCase(new int[] { 4 }, 7, 0);

		// Test case 3: Array with all elements equal to k
		testCase(new int[] { 5, 5, 5, 5, 5 }, 5, 15);

		// Test case 4: Array with no subarrays having GCD equal to k
		testCase(new int[] { 2, 4, 6, 8, 10 }, 3, 0);

		// Test case 5: Array with multiple subarrays having GCD equal to k
		testCase(new int[] { 18, 12, 24, 6, 30 }, 6, 8);

		// Test case 6: Array with large numbers
		testCase(new int[] { 1000000000, 1000000000 }, 1000000000, 3);

		// Test case 7: Array with minimum length and k not equal to the element
		testCase(new int[] { 5 }, 3, 0);

		// Test case 8: Array with minimum length and k equal to the element
		testCase(new int[] { 7 }, 7, 1);
	}

	private static void testCase(int[] nums, int k, int expectedOutput) {
		System.out.println("Input array: " + java.util.Arrays.toString(nums));
		System.out.println("k: " + k);
		System.out.println("Expected output: " + expectedOutput);

		int actualOutput = countSubarrays(nums, k);
		System.out.println("Actual output: " + actualOutput);

		boolean passed = (actualOutput == expectedOutput);
		System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
		System.out.println("--------------------");
	}

	// Placeholder method - replace with your actual implementation
	private static int countSubarrays(int[] arr, int k) {
		int count = 0;
		int n = arr.length;
		for (int sp = 0; sp < n; sp++) {
			int temp = 0;
			for (int ep = sp; ep < n; ep++) {
				temp = gcd(temp, arr[ep]);
				if (temp < k)
					break;
				if (temp == k)
					count++;
			}
		}
		return count;
	}

	private static int gcd(int a, int b) {
		if (a == 0) {
			return b;
		}
		return gcd(b % a, a);
	}
}