package Array;

public class NextGreaterElementIII {
	public static void main(String[] args) {
		// Test cases
		int[][] testCases = { { 12, 21 }, // Example 1
				{ 21, -1 }, // Example 2
				{ 123, 132 }, // Next permutation
				{ 321, -1 }, // Already largest permutation
				{ 1234, 1243 }, // Swap last two digits
				{ 230241, 230412 }, // Rearrange multiple digits
				{ 12443322, 13222344 }, // Rearrange with repeated digits
				{ 1999999999, -1 }, // Largest possible 32-bit integer
				{ 2147483647, -1 }, // Max 32-bit integer (2^31 - 1)
				{ 1234321, 1241233 } // Palindromic number
		};

		NextGreaterElementIII solution = new NextGreaterElementIII();

		for (int[] testCase : testCases) {
			int input = testCase[0];
			int expectedOutput = testCase[1];

			System.out.println("Input: n = " + input);
			int result = solution.nextGreaterElement(input);
			System.out.println("Output: " + result);
			System.out.println("Expected Output: " + expectedOutput);
			System.out.println("Result: " + (result == expectedOutput ? "PASS" : "FAIL"));
			System.out.println();
		}
	}

	public static void reverse(char[] arr, int s, int e) {
		while (s < e) {
			char t = arr[s];
			arr[s] = arr[e];
			arr[e] = t;
			s++;
			e--;
		}
	}

	public int nextGreaterElement(int a) {
		char[] num = (a + "").toCharArray();
		int n = num.length;
		int idx = -1;
		for (int i = n - 2; i >= 0; i--) {
			if (num[i + 1] > num[i]) {
				idx = i;
				break;
			}
		}
		if (idx == -1) {
			return -1;
		}
		char val = num[idx];
		int small = idx + 1;
		for (int i = idx + 1; i < n; i++) {
			if (num[i] > val && num[i] <= num[small]) {
				small = i;
			}
		}
		char t = num[idx];
		num[idx] = num[small];
		num[small] = t;
		reverse(num, idx + 1, n - 1);
		long ans = Long.parseLong(String.valueOf(num));
		if (ans <= Integer.MAX_VALUE) {
			return (int) ans;
		} else {
			return -1;
		}
	}
}
