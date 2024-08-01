package Math;

public class MaxGCDAfterRemoval {
	public static void main(String[] args) {
		// Test case 1: Given example
		testCase(new int[] { 24, 16, 18, 30, 15 }, 3);

		// Test case 2: Small array
		testCase(new int[] { 6, 9, 12 }, 6);

		// Test case 3: Array with prime numbers
		testCase(new int[] { 2, 3, 5, 7, 11 }, 1);

		// Test case 4: Array with repeated elements
		testCase(new int[] { 10, 20, 30, 10, 20 }, 10);

		// Test case 5: Array with all elements same
		testCase(new int[] { 5, 5, 5, 5, 5 }, 5);

		// Test case 6: Array with large numbers
		testCase(new int[] { 1000000000, 999999999, 999999998 }, 1);

		// Test case 7: Array with minimum length (N = 2)
		testCase(new int[] { 4, 6 }, 2);
	}

	private static void testCase(int[] arr, int expectedOutput) {
		System.out.println("Input array: " + java.util.Arrays.toString(arr));
		System.out.println("Array length: " + arr.length);
		System.out.println("Expected output: " + expectedOutput);

		int actualOutput = calculateMaxGCD(arr);
		System.out.println("Actual output: " + actualOutput);

		boolean passed = (actualOutput == expectedOutput);
		System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
		System.out.println("--------------------");
	}

	// Placeholder method - replace with your actual implementation
	private static int calculateMaxGCD(int[] arr) {
		int[] p = preGcd(arr);
		int[] s = sufGcd(arr);
		int n = arr.length;
		int max = Integer.MIN_VALUE;
		max = Math.max(max, s[0]);
		for (int i = 1; i < n - 1; i++) {
			max = Math.max(p[i - 1], s[i + 1]);
		}
		max = Math.max(max, p[n - 1]);
		return max;
	}

	private static int[] preGcd(int[] arr) {
		int n = arr.length;
		int[] p = new int[n];
		p[0] = arr[0];
		for (int i = 1; i < n; i++) {
			p[i] = gcd(p[i - 1], arr[i]);
		}
		return p;
	}

	private static int[] sufGcd(int[] arr) {
		int n = arr.length;
		int[] s = new int[n];
		s[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			s[i] = gcd(s[i + 1], arr[i]);
		}
		return s;
	}

	public static int gcd(int a, int b) {
		if (a == 0) {
			return b;
		}
		return gcd(b % a, a);
	}
}