package Array;

/*Number of Subarrays with Bounded Maximum
Medium
Topics
Companies
Given an integer array nums and two integers left and right, return the number of contiguous non-empty subarrays such that the value of the maximum array element in that subarray is in the range [left, right].

The test cases are generated so that the answer will fit in a 32-bit integer.

 

Example 1:

Input: nums = [2,1,4,3], left = 2, right = 3
Output: 3
Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
Example 2:

Input: nums = [2,9,2,5,6], left = 2, right = 8
Output: 7
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 109
0 <= left <= right <= 109*/

//Solution:
/*
Detailed Explanation of the Solution:

Problem Overview:
The problem asks to count the number of contiguous non-empty subarrays where the maximum element falls within a given range [left, right].

Solution Approach:
The solution uses a single-pass, linear time algorithm that employs a sliding window technique combined with dynamic programming concepts.

Key Variables:
1. lgei (Last Greater Element Index): 
   - Tracks the index after the last element that was greater than 'right'.
   - Serves as the potential starting point for new valid subarrays.

2. lastValid:
   - Stores the count of valid subarrays that ended at the previous element.
   - Acts as a memoization technique to avoid recounting.

3. ans:
   - Accumulates the total count of valid subarrays found so far.

Algorithm Breakdown:

1. Initialization:
   - Set lgei = 0, lastValid = 0, ans = 0
   - These initializations assume we start with a clean slate.

2. Iterate through the array (for i = 0 to n-1):

   Case 1: arr[i] > right
   - This element is too large, breaking any ongoing valid subarrays.
   - Action:
     * Set lastValid = 0 (no valid subarrays can end here)
     * Update lgei = i + 1 (next element is the new potential start)
   - Reasoning: Any subarray including this element would have a max > right, so we reset our counters.

   Case 2: left <= arr[i] <= right
   - This element is within the valid range.
   - Action:
     * Add (i - lgei + 1) to ans
     * Set lastValid = (i - lgei + 1)
   - Reasoning:
     * (i - lgei + 1) represents all subarrays ending at i and starting at or after lgei.
     * These are all new valid subarrays as they have a max within [left, right].
     * We update lastValid for potential use in the next iteration.

   Case 3: arr[i] < left
   - This element is too small to make a valid subarray on its own, but can extend previous valid subarrays.
   - Action:
     * Add lastValid to ans
   - Reasoning:
     * This element can be appended to all subarrays that were valid up to the previous element.
     * The count of such subarrays is exactly lastValid.

3. Return the final value of 'ans' as the result.

Time Complexity: O(n), where n is the length of the input array.
Space Complexity: O(1), as we only use a constant amount of extra space.

Key Insights:
1. Efficient Counting: The algorithm avoids explicitly generating all subarrays, instead using clever counting techniques.
2. State Maintenance: By keeping track of lgei and lastValid, we maintain just enough information to compute the result on the fly.
3. Handling Range Violations: The algorithm elegantly handles elements outside the [left, right] range by either resetting counters (for too large) or extending previous valid counts (for too small).
4. Single Pass: The entire array is processed in a single pass, contributing to the algorithm's efficiency.

This solution demonstrates an effective use of sliding window and dynamic programming concepts to solve what could otherwise be a complex counting problem in linear time and constant space.
Improved Visual Representation of the Algorithm

Array: [1, 3, 4, 2, 7, 5, 3, 1]
left = 3, right = 5

Legend:
{ } - Element < left
[ ] - Element within [left, right]
( ) - Element > right

Step-by-step visualization:

Initial state: lgei = 0, lastValid = 0, ans = 0

1. {1} 3 4 2 7 5 3 1    i = 0
   ^
   lgei = 0, lastValid = 0, ans = 0
   No valid subarrays yet

2. {1}[3]4 2 7 5 3 1    i = 1
    ^
   lgei = 0, lastValid = 2, ans = 2
   New valid subarrays: [3], [1,3]

3. {1}[3][4]2 7 5 3 1   i = 2
      ^
   lgei = 0, lastValid = 3, ans = 5
   New valid subarrays: [4], [3,4], [1,3,4]

4. {1}[3][4][2]7 5 3 1  i = 3
        ^
   lgei = 0, lastValid = 4, ans = 9
   New valid subarrays: [2], [4,2], [3,4,2], [1,3,4,2]

5. {1}[3][4][2](7)5 3 1 i = 4
            ^
   lgei = 5, lastValid = 0, ans = 9
   No new valid subarrays (7 is too large)

6. {1}[3][4][2](7)[5]3 1 i = 5
              ^
   lgei = 5, lastValid = 1, ans = 10
   New valid subarray: [5]

7. {1}[3][4][2](7)[5][3]1 i = 6
                ^
   lgei = 5, lastValid = 2, ans = 12
   New valid subarrays: [3], [5,3]

8. {1}[3][4][2](7)[5][3]{1} i = 7
                  ^
   lgei = 5, lastValid = 2, ans = 14
   Extended valid subarrays: [3,1], [5,3,1]

Final result: ans = 14

Breakdown of all valid subarrays:
1. [3]
2. [1,3]
3. [4]
4. [3,4]
5. [1,3,4]
6. [2]
7. [4,2]
8. [3,4,2]
9. [1,3,4,2]
10. [5]
11. [3]
12. [5,3]
13. [3,1]
14. [5,3,1]

Key Observations:
- The algorithm efficiently handles elements below, within, and above the range.
- lgei resets when we encounter 7, which is above the range.
- lastValid updates with each in-range element, allowing extension of subarrays.
- Elements below the range (like the last 1) extend previous valid subarrays.
*/

import java.util.Arrays;

public class NumberOfSubArraysWithBoundedMaximum {
	public int numSubarrayBoundedMax(int[] arr, int left, int right) {
		// lgei: Last Greater Element Index - tracks the index after the last element >
		// right
		int lgei = 0;
		// lastValid: Count of valid subarrays ending at the previous element
		int lastValid = 0;
		// ans: Total count of valid subarrays
		int ans = 0;
		// n: Length of the input array
		int n = arr.length;

		// Iterate through each element of the array
		for (int i = 0; i < n; i++) {
			if (arr[i] > right) {
				// Case 1: Current element is greater than the upper bound
				// Reset lastValid as no subarray ending here can be valid
				lastValid = 0;
				// Update lgei to the next index, as this element breaks any ongoing valid
				// subarrays
				lgei = i + 1;
			} else if (arr[i] >= left && arr[i] <= right) {
				// Case 2: Current element is within the valid range [left, right]
				// Add count of new valid subarrays ending at this element
				ans += (i - lgei + 1);
				// Update lastValid for potential use in next iteration
				lastValid = (i - lgei + 1);
			} else {
				// Case 3: Current element is less than the lower bound
				// Extend previously valid subarrays by adding lastValid to ans
				ans += lastValid;
			}
		}

		// Return the total count of valid subarrays
		return ans;
	}

	public static void main(String[] args) {
		NumberOfSubArraysWithBoundedMaximum solution = new NumberOfSubArraysWithBoundedMaximum();

		// Test case 1: Original example 1
		testCase(solution, new int[] { 2, 1, 4, 3 }, 2, 3, 3);

		// Test case 2: Original example 2
		testCase(solution, new int[] { 2, 9, 2, 5, 6 }, 2, 8, 7);

		// Test case 3: All elements within range
		testCase(solution, new int[] { 3, 3, 3, 3 }, 2, 4, 10);

		// Test case 4: No elements within range
		testCase(solution, new int[] { 1, 1, 1, 10, 10, 10 }, 2, 9, 0);

		// Test case 5: Single element array, within range
		testCase(solution, new int[] { 5 }, 1, 10, 1);

		// Test case 6: Single element array, outside range
		testCase(solution, new int[] { 11 }, 1, 10, 0);

		// Test case 7: Alternating in-range and out-of-range elements
		testCase(solution, new int[] { 1, 5, 1, 5, 1 }, 2, 6, 9);

		// Test case 8: Range covers all possible values
		testCase(solution, new int[] { 1, 1000000000, 500000000 }, 0, 1000000000, 6);

		// Test case 9: Very narrow range
		testCase(solution, new int[] { 1, 2, 3, 4, 5 }, 3, 3, 3);

		// Test case 10: All elements just below range
		testCase(solution, new int[] { 1, 1, 1, 1, 1 }, 2, 5, 0);

		// Test case 11: All elements just above range
		testCase(solution, new int[] { 6, 6, 6, 6 }, 1, 5, 0);

		// Test case 12: Mix of in-range and out-of-range, with range at array bounds
		testCase(solution, new int[] { 2, 1, 4, 3, 5, 6 }, 1, 6, 21);

		// Test case 13: Large array with repeated pattern
		testCase(solution, new int[] { 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 }, 2, 4, 16);

		// Test case 14: Array with negative numbers (assuming constraints allow it)
		testCase(solution, new int[] { -1, 0, 1, 2, 3 }, 0, 2, 9);

		// Test case 15: Array with all elements at range boundaries
		testCase(solution, new int[] { 2, 2, 8, 8, 2, 8 }, 2, 8, 21);
	}

	private static void testCase(NumberOfSubArraysWithBoundedMaximum solution, int[] nums, int left, int right,
			int expected) {
		int result = solution.numSubarrayBoundedMax(nums, left, right);
		System.out.println("Input: nums = " + Arrays.toString(nums) + ", left = " + left + ", right = " + right);
		System.out.println("Expected: " + expected);
		System.out.println("Actual: " + result);
		System.out.println("Result: " + (result == expected ? "PASS" : "FAIL"));
		System.out.println();
	}
}