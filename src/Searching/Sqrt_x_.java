package Searching;

/*Given a non-negative integer x, return the square root of x 
 * rounded down to the nearest integer. 
 * The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 

Example 1:

Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.
Example 2:

Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., 
and since we round it down to the nearest integer, 2 is returned.*/

public class Sqrt_x_ {

	public static int sqrt(int n) {
		// Handle special cases: 0 and numbers less than or equal to 3
		if (n == 0) {
			return 0;
		}
		if (n <= 3) {
			return 1;
		}

		// Initialize low and high for binary search
		int low = 1;
		int high = n - 1; // Avoid going beyond n (potential overflow)

		// Variable to store the possible answer (square root)
		int ans = 1;

		while (low <= high) {
			// Calculate the middle index
			int mid = (low + high) / 2;

			// Check if the middle squared is equal to n (perfect square)
			if (mid * mid == n) {
				return mid; // Found the exact square root
			} else if (mid * mid > n) {
				// If middle squared is greater than n, search in the left half
				high = mid - 1;
			} else {
				// If middle squared is less than n, it could be an answer (update ans)
				// and search in the right half for a better approximation
				ans = mid;
				low = mid + 1;
			}
		}

		// If no perfect square is found, return the closest approximation (ans)
		return ans;
	}

	public static void main(String[] args) {
		int n = 36;
		System.out.println(sqrt(n)); // Output: 5 (integer square root rounded down)
	}
}
