package Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeFactorizationTest {
	public static void main(String[] args) {
		// Test case 1: Given example
		testCase(50, new int[] { 10, 24 }, Arrays.asList(Arrays.asList(2, 5), Arrays.asList(2, 2, 2, 3)));

		// Test case 2: Edge case - N = 1
		testCase(1, new int[] { 1 }, Arrays.asList(Arrays.asList()));

		// Test case 3: Large N with various queries
		testCase(100000, new int[] { 100000, 99999, 2, 97 }, Arrays.asList(Arrays.asList(2, 2, 2, 2, 2, 5, 5, 5, 5),
				Arrays.asList(3, 3, 11111), Arrays.asList(2), Arrays.asList(97)));

		// Test case 4: Prime numbers
		testCase(20, new int[] { 2, 3, 5, 7, 11, 13, 17, 19 },
				Arrays.asList(Arrays.asList(2), Arrays.asList(3), Arrays.asList(5), Arrays.asList(7), Arrays.asList(11),
						Arrays.asList(13), Arrays.asList(17), Arrays.asList(19)));

		// Test case 5: Repeated factors
		testCase(100, new int[] { 16, 27, 64, 81 }, Arrays.asList(Arrays.asList(2, 2, 2, 2), Arrays.asList(3, 3, 3),
				Arrays.asList(2, 2, 2, 2, 2, 2), Arrays.asList(3, 3, 3, 3)));

		// Test case 6: Mixed queries
		testCase(1000, new int[] { 1000, 997, 500, 111, 6 }, Arrays.asList(Arrays.asList(2, 2, 2, 5, 5, 5),
				Arrays.asList(997), Arrays.asList(2, 2, 5, 5, 5), Arrays.asList(3, 37), Arrays.asList(2, 3)));
	}

	private static void testCase(int n, int[] queries, List<List<Integer>> expectedOutput) {
		System.out.println("N: " + n);
		System.out.println("Queries: " + Arrays.toString(queries));
		System.out.println("Expected output:");
		for (List<Integer> factors : expectedOutput) {
			System.out.println(factors);
		}

		List<List<Integer>> actualOutput = primeFactorize(n, queries);
		System.out.println("Actual output:");
		for (List<Integer> factors : actualOutput) {
			System.out.println(factors);
		}

		boolean passed = expectedOutput.equals(actualOutput);
		System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
		System.out.println("--------------------");
	}

	// Placeholder method - replace with your actual implementation
	private static List<List<Integer>> primeFactorize(int n, int[] q) {
		int[] p = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			p[i] = i;
		}
		for (int i = 2; i * i <= n; i++) {
			if (p[i] == i) {
				for (int j = i * i; j <= n; j += i) {
					p[j] = Math.min(p[j], i);
				}
			}
		}
		ArrayList<List<Integer>> ans = new ArrayList<>();
		for (int i = 0; i < q.length; i++) {
			int x = q[i];
			ArrayList<Integer> temp = new ArrayList<>();
			while (x > 1) {
				temp.add(p[x]);
				x /= p[x];
			}
			ans.add(temp);
		}
		return ans;
	}
}