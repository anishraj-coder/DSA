package Array;

class PiviotIndex {
	public static int[] PreSum(int[] arr) {
		int n = arr.length;
		int[] pre = new int[n];
		pre[0] = arr[0];
		for (int i = 1; i < n; i++) {
			pre[i] = pre[i - 1] + arr[i];
		}
		return pre;
	}

	public static int pivotIndex(int[] nums) {

		int n = nums.length;
		if (n == 0)
			return -1;
		int[] pre = PreSum(nums);
		if (pre[n - 1] - pre[0] == 0)
			return 0;
		if (pre[n - 2] == 0)
			return n - 1;
		for (int i = 1; i < n - 1; i++) {
			int left = pre[i - 1];
			int right = pre[n - 1] - pre[i];
			if (left == right)
				return i;
		}
		return -1; // Placeholder return
	}

	public static void main(String[] args) {

		int[][] testCases = { { 1, 7, 3, 6, 5, 6 }, // Expected: 3
				{ 1, 2, 3 }, // Expected: -1
				{ 2, 1, -1 }, // Expected: 0
				{ 1, 2, 3, 4, 5, 6 }, // Expected: -1
				{ -1, -1, -1, 0, 1, 1 }, // Expected: 3
				{ -1, -1, 0, 1, 1, 0 }, // Expected: 5
				{ -1, -1, 0, 1, 1, -1 }, // Expected: 2
				{ 0 }, // Expected: 0
				{ 1 }, // Expected: 0
				{ -1 }, // Expected: 0
				{}, // Expected: -1
				{ 1, 1, 1, 1, 1 }, // Expected: 2
				{ 2, 3, 4, 1, 4, 3, 2 }, // Expected: 3
				{ 1, 2, 3, 4, 3, 2, 1 }, // Expected: 3
				{ 1, 100, 50, -51, 1, 1 }, // Expected: 1
				{ 20, 10, -80, 10, 10, 15, 35 }, // Expected: 0
				{ 1, 2, 3, 0, 3, 2, 1 }, // Expected: 3
				{ -1, -1, -1, 0, -1, -1, -1 }, // Expected: 3
				{ 0, 0, 0, 0 }, // Expected: 0
				{ 1, -1 }, // Expected: -1
				{ -1, 1 }, // Expected: -1
				{ 1, 2, 1 }, // Expected: 1
				{ 2, 1, 2 }, // Expected: 1
				{ 1, 1, 1, 1, 1, 1 }, // Expected: 2
				{ 9, 9 }, // Expected: -1
				{ 5, 2, 7 }, // Expected: -1
				{ 3, 4, 5, 6, 2, 1 }, // Expected: -1
				{ 10, -5, 15, -10, 5 }, // Expected: 2
				{ 1, 0, -1 }, // Expected: 1
				{ 0, 0 } // Expected: 0
		};

		for (int i = 0; i < testCases.length; i++) {
			int result = pivotIndex(testCases[i]);
			System.out.println("Test case " + (i + 1) + " output: " + result);
		}
	}
}