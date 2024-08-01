package Array;

public class MaxChunksToMakeSorted {
	public static int[] preMax(int[] arr) {
		int n = arr.length;
		int[] pm = new int[n];
		pm[0] = arr[0];

		for (int i = 1; i < n; i++) {
			pm[i] = Math.max(pm[i - 1], arr[i]);
		}
		return pm;
	}

	public static int[] suffixMin(int[] arr) {
		int n = arr.length;
		int[] sm = new int[n];
		sm[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			sm[i] = Math.min(sm[i + 1], arr[i]);
		}
		return sm;
	}

	public static int maxChunksToSorted(int[] arr) {
		int n = arr.length;

		int count = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, arr[i]);
			if (max == i) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		// Test cases
		int[][] testCases = { { 4, 3, 2, 1, 0 }, { 1, 0, 2, 3, 4 }, { 0 }, { 1, 0 }, { 0, 1, 2, 3 }, { 3, 2, 1, 0 },
				{ 5, 4, 3, 2, 1, 0 }, { 0, 1, 3, 2, 4 }, { 1, 2, 3, 0, 4 }, { 2, 1, 3, 4, 0 } };

		// Expected results
		int[] expectedResults = { 1, 4, 1, 1, 4, 1, 1, 2, 3, 1 };

		for (int i = 0; i < testCases.length; i++) {
			int result = maxChunksToSorted(testCases[i]);
			System.out.println("Test case " + (i + 1) + ":");
			System.out.println("Input: " + java.util.Arrays.toString(testCases[i]));
			System.out.println("Expected result: " + expectedResults[i]);
			System.out.println("Actual result: " + result);
			System.out.println(result == expectedResults[i] ? "Pass" : "Fail");
			System.out.println();
		}
	}
}