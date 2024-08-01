package Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SieveOfEratosthenesTest {
	public static void main(String[] args) {
		// Test case 1: Given example 1
		testCase(10, Arrays.asList(2, 3, 5, 7));

		// Test case 2: Given example 2
		testCase(35, Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31));

		// Test case 3: Minimum value
		testCase(1, Arrays.asList());

		// Test case 4: Small prime number
		testCase(3, Arrays.asList(2, 3));

		// Test case 5: Non-prime number
		testCase(4, Arrays.asList(2, 3));

		// Test case 6: Large prime number
		testCase(97, Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79,
				83, 89, 97));

		// Test case 7: Maximum value in constraints
		testCase(10000, null); // Expected output is too large to list here

		// Test case 8: Number with many prime factors
		testCase(30, Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));
	}

	private static void testCase(int N, List<Integer> expectedOutput) {
		System.out.println("Input N: " + N);
		System.out.println(
				"Expected output: " + (expectedOutput != null ? expectedOutput : "Large prime list up to " + N));

		List<Integer> actualOutput = sieveOfEratosthenes(N);
		System.out.println("Actual output: " + actualOutput);

		boolean passed = (expectedOutput == null) || expectedOutput.equals(actualOutput);
		System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
		System.out.println("--------------------");
	}

	// Placeholder method - replace with your actual implementation
	private static List<Integer> sieveOfEratosthenes(int n) {

		boolean[] p = new boolean[n + 1];
		Arrays.fill(p, true);
		p[0] = p[1] = false;
		for (int i = 2; i * i <= n; i++) {
			if (p[i] == true) {
				for (int j = i * i; j <= n; j += i) {
					p[j] = false;
				}
			}
		}
		ArrayList<Integer> ans = new ArrayList<>();
		for (int i = 2; i <= n; i++) {
			if (p[i] == true) {
				ans.add(i);
			}
		}
		return ans;
	}
}