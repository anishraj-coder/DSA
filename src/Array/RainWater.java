package Array;

/*
Explanation of the Trapping Rain Water problem solution:

1. Problem Understanding:
   - We are given an array representing heights of walls.
   - Each element's index represents its position, and the value represents its height.
   - We need to calculate how much water can be trapped between these walls after rainfall.

2. Problem Ideology:
   - For any given wall, water can be trapped above it if there are higher walls on both its left and right sides.
   - The amount of water that can be trapped above a wall is determined by the shorter of the highest walls to its left and right, minus its own height.
   - We need to efficiently determine the highest wall to the left and right of each position.

3. Solution Method:
   a. Precompute maximum heights:
      - Create two arrays: prefix maximum and suffix maximum.
      - Prefix maximum: At each index, store the maximum height from the start up to that index.
      - Suffix maximum: At each index, store the maximum height from the end up to that index.
   b. Calculate trapped water:
      - Iterate through each position in the array (excluding the first and last, as they can't trap water).
      - For each position, find:
        * The maximum height to its left (from prefix maximum array)
        * The maximum height to its right (from suffix maximum array)
      - Calculate water trapped at this position: min(left max, right max) - current height
      - If this value is positive, add it to the total trapped water.

4. Implementation Details:
   - preMax method:
     * Initializes prefix[0] with the first element's height.
     * Iterates from left to right, updating prefix[i] as max(prefix[i-1], height[i]).
   - sufMax method:
     * Initializes suffix[n-1] with the last element's height.
     * Iterates from right to left, updating suffix[i] as max(suffix[i+1], height[i]).
   - trap method:
     * Computes prefix and suffix maximum arrays.
     * Iterates through the array (skipping first and last elements).
     * For each element, calculates and accumulates trapped water.

5. Time and Space Complexity Analysis:
   - Time Complexity: O(n)
     * Prefix maximum calculation: O(n)
     * Suffix maximum calculation: O(n)
     * Final iteration to calculate trapped water: O(n)
     * Total: O(n) + O(n) + O(n) = O(n)
   - Space Complexity: O(n)
     * Two additional arrays (prefix and suffix) of length n are used.

6. Advantages of this Approach:
   - Linear time complexity makes it efficient for large inputs.
   - Avoids recalculating maximum heights for each element, reducing redundant computations.
   - Easy to understand and implement.

7. Potential Optimizations:
   - The space complexity can be reduced to O(1) by using two pointers approach, eliminating the need for prefix and suffix arrays.
   - However, the current approach is more intuitive and easier to understand.

8. Edge Cases Consideration:
   - Arrays with less than 3 elements will always return 0 (no water can be trapped).
   - The solution correctly handles cases where no water can be trapped (e.g., descending or ascending sequences of walls).

9. Correctness:
   - This solution works because it correctly identifies the limiting factor for water at each position (the shorter of the maximum heights on either side).
   - By precomputing maximum heights, we ensure that we're always considering the correct "container" for water at each position.
*/
/*
Explanation of the Trapping Rain Water problem solution:

1. Problem Visualization:

   Consider this elevation map: [0,1,0,2,1,0,1,3,2,1,2,1]
   It can be visualized as:
               
               |
       |       | |   |
   |   | |   | | | | | |
   ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾
   0 1 0 2 1 0 1 3 2 1 2 1

   The trapped water (represented by ~) would look like:
               
               |
       |~ ~ ~ ~| |   |
   |~ ~| |~ ~| | | | | |
   ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾
   0 1 0 2 1 0 1 3 2 1 2 1

2. Solution Approach Visualization:

   a. Prefix Maximum Array:
      [0,1,1,2,2,2,2,3,3,3,3,3]
               
               |‾ ‾ ‾ ‾ ‾
       |‾ ‾ ‾ ‾|
   |‾ ‾| |
   ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾
   0 1 1 2 2 2 2 3 3 3 3 3

   b. Suffix Maximum Array:
      [3,3,3,3,3,3,3,3,2,2,2,1]
    ‾ ‾ ‾ ‾ ‾ ‾ ‾|
    ‾ ‾ ‾ ‾ ‾ ‾ ‾| |‾ ‾|
    ‾ ‾ ‾ ‾ ‾ ‾ ‾| | | | |
   ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾
   3 3 3 3 3 3 3 3 2 2 2 1

   c. Water Trapped at each position:
      For each position i, water trapped = min(prefix[i-1], suffix[i+1]) - height[i]
      
               |
       |~ ~ ~ ~| |   |
   |~ ~| |~ ~| | | | | |
   ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾ ‾
   0 1 0 2 1 0 1 3 2 1 2 1
   - 0 1 0 1 2 1 0 0 0 0 -   <- Water trapped at each position

3. Calculation Example:
   For position 5 (height = 0):
   - Left max (prefix[4]) = 2
   - Right max (suffix[6]) = 3
   - Water trapped = min(2, 3) - 0 = 2

4. Final Result:
   Total water trapped = sum of water at each position = 6

This visual representation helps to understand:
- How the elevation map translates to trapped water
- How prefix and suffix maximum arrays are constructed
- How water trapped at each position is calculated
*/
public class RainWater {
	public static void main(String[] args) {
		// Test case 1
		int[] height1 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		int expectedOutput1 = 6;
		runTestCase(height1, expectedOutput1, 1);

		// Test case 2
		int[] height2 = { 4, 2, 0, 3, 2, 5 };
		int expectedOutput2 = 9;
		runTestCase(height2, expectedOutput2, 2);

		// Test case 3
		int[] height3 = { 1, 1, 1, 1 };
		int expectedOutput3 = 0;
		runTestCase(height3, expectedOutput3, 3);

		// Test case 4
		int[] height4 = { 2, 0, 2 };
		int expectedOutput4 = 2;
		runTestCase(height4, expectedOutput4, 4);

		// Test case 5
		int[] height5 = { 3, 0, 2, 0, 4 };
		int expectedOutput5 = 7;
		runTestCase(height5, expectedOutput5, 5);

		// Test case 6
		int[] height6 = { 0, 0, 0, 0 };
		int expectedOutput6 = 0;
		runTestCase(height6, expectedOutput6, 6);

		// Test case 7
		int[] height7 = { 0, 1, 0, 1, 0 };
		int expectedOutput7 = 1;
		runTestCase(height7, expectedOutput7, 7);

		// Test case 8
		int[] height8 = { 5, 4, 3, 2, 1 };
		int expectedOutput8 = 0;
		runTestCase(height8, expectedOutput8, 8);

		// Test case 9
		int[] height9 = { 1, 2, 1, 2, 1 };
		int expectedOutput9 = 1;
		runTestCase(height9, expectedOutput9, 9);

		// Test case 10
		int[] height10 = { 0, 2, 0, 0, 2, 0 };
		int expectedOutput10 = 4;
		runTestCase(height10, expectedOutput10, 10);

		// Test case 11
		int[] height11 = { 3, 2, 1, 2, 3 };
		int expectedOutput11 = 4;
		runTestCase(height11, expectedOutput11, 11);

		// Test case 12
		int[] height12 = { 2, 0, 1, 0, 2 };
		int expectedOutput12 = 3;
		runTestCase(height12, expectedOutput12, 12);

		// Test case 13
		int[] height13 = { 5, 2, 1, 2, 1, 5 };
		int expectedOutput13 = 14;
		runTestCase(height13, expectedOutput13, 13);

		// Test case 14
		int[] height14 = { 0, 3, 0, 3, 0, 3 };
		int expectedOutput14 = 6;
		runTestCase(height14, expectedOutput14, 14);

		// Test case 15
		int[] height15 = { 2, 2, 2, 2, 2, 2 };
		int expectedOutput15 = 0;
		runTestCase(height15, expectedOutput15, 15);
	}

	private static void runTestCase(int[] height, int expectedOutput, int testCaseNumber) {
		System.out.println("Test Case " + testCaseNumber + ":");
		System.out.println("Input: " + arrayToString(height));
		System.out.println("Expected Output: " + expectedOutput);
		System.out.println("Actual Output: " + trap(height));
		System.out.println();
	}

	private static String arrayToString(int[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if (i < array.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public static int[] preMax(int[] arr) {
		int n = arr.length;
		int[] prefixMax = new int[n];
		prefixMax[0] = arr[0];
		for (int i = 1; i < n; i++) {
			prefixMax[i] = Math.max(prefixMax[i - 1], arr[i]);
		}
		return prefixMax;
	}

	public static int[] sufMax(int[] arr) {
		int n = arr.length;
		int[] suffixMax = new int[n];
		suffixMax[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			suffixMax[i] = Math.max(suffixMax[i + 1], arr[i]);
		}
		return suffixMax;
	}

	// Dummy 'trap' method to be implemented with the actual logic
	public static int trap(int[] arr) {
		int[] prefixMax = preMax(arr);
		int[] suffixMax = sufMax(arr);
		int n = arr.length;
		int amt = 0;
		for (int i = 1; i < n - 1; i++) {
			int lb = prefixMax[i - 1];
			int rb = suffixMax[i + 1];
			int contri = Math.min(lb, rb) - arr[i];
			if (contri > 0) {
				amt += contri;
			}

		}
		return amt;
	}
}