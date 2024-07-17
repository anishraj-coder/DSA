package Searching;

/*Given a sorted array of n integers and a number k, Return floor(k).
Input Format

First line contains integer n representing the length of array
Second line contains n integers
Third line contains integer k
Constraints

1 <= n <= 10^9
Output Format

Return floor of k.
Sample Input 0

9
-4 3 4 7 10 11 12 15 19
5
Sample Output 0

4*/

public class FloorInArray {

	public static int floor(int[] arr, int k) {
		// Initialize floor value to the minimum possible value (Integer.MIN_VALUE)
		int ans = Integer.MIN_VALUE;

		// Initialize low and high indices for binary search
		int low = 0;
		int hi = arr.length - 1;

		while (low <= hi) {
			// Calculate the middle index
			int mid = (low + hi) / 2;

			// Check if the middle element is equal to k (exact match)
			if (arr[mid] == k) {
				return arr[mid]; // Found the exact value, return it
			} else if (arr[mid] < k) {
				// If the middle element is less than k, it could be a potential floor
				ans = arr[mid]; // Update the floor candidate
				low = mid + 1; // Search in the right half for a larger element closer to k
			} else {
				// If the middle element is greater than k, floor must be in the left half
				hi = mid - 1; // Search in the left half for elements less than k
			}
		}

		// If no element is less than or equal to k (k is greater than all elements),
		// return the maximum element found (ans)
		return ans;
	}

	public static void main(String[] args) {
		int[] arr = { -4, 3, 4, 7, 10, 11, 12, 15, 19 };
		System.out.println(floor(arr, 9)); // Output: 7 (floor of 9 in the array)
	}
}
