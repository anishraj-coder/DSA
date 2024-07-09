package Hashmap;

import java.util.HashMap;

/*Given an array arr of positive integers and another number x. 
 * Determine whether two elements exist in arr whose sum is exactly x or not.

Examples:

Input: x = 16, arr[] = [1, 4, 45, 6, 10, 8]
Output: true
Explanation: arr[3] + arr[4] = 6 + 10 = 16
Input: x = 11, arr[] = [1, 2, 4, 3, 6]
Output: false
Explanation: None of the pair makes a sum of 11
Expected Time Complexity: O(n)
Expected Auxiliary Space: O(n)*/

public class SumPair {

	public static void main(String[] args) {
		// Sample array (replace with user input if needed)
		int[] arr = { 1, 1, 1 };

		// Target sum to find a pair for
		int targetSum = 2;

		// HashMap to store element frequencies
		HashMap<Integer, Integer> elementCount = new HashMap<>();

		// Build the frequency map for elements in the array
		for (int element : arr) {
			if (elementCount.containsKey(element)) {
				// If the element already exists in the map, increment its frequency count
				elementCount.put(element, elementCount.get(element) + 1);
			} else {
				// If the element is not found, add it to the map with a frequency of 1
				elementCount.put(element, 1);
			}
		}

		// Check if a pair exists for the target sum
		for (int element : arr) {
			int complement = targetSum - element; // Calculate the complement needed to reach the target sum

			// Check if the complement exists in the HashMap
			if (elementCount.containsKey(complement)) {
				// If the complement is found:
				// - If the element and complement are different (i.e., not the same number), a
				// valid pair exists.
				// - If the element and complement are the same, a valid pair exists only if
				// there's more than one instance of the element (frequency > 1).
				if (element != complement || elementCount.get(complement) > 1) {
					System.out.println(true);
					return; // Early exit if a pair is found
				}
			}
		}

		// If no pair is found after iterating through the array, print false
		System.out.println(false);
	}
}
