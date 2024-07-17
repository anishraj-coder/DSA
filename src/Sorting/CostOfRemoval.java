package Sorting;

import java.util.Arrays;

/*Given N length Array, at every step remove an array element.
 *  Cost to remove element = sum of array elements present. 
 *  Find min cost to remove all the elements.

Note: Add to cost first and then remove.
Input Format

Integer representing N representing length of array
N numbers of Array
Constraints

1<= N <= 10 ^ 6
1<= A[i] <=10 ^ 9
Output Format

Return the min cost to remove all the elements.
Sample Input 0

3
3 2 5
Sample Output 0

17*/

public class CostOfRemoval {

	public static void main(String[] args) {
		int[] arr = { 3, 2, 5 }; // Sample array

		// Sort the array in ascending order
		Arrays.sort(arr);

		int totalCost = 0;
		int n = arr.length;

		// Calculate the minimum cost to remove all elements
		for (int i = 0; i < n; i++) {
			// At each step (i):
			// - Number of elements remaining (to be removed) = n - i
			// - Cost to remove the current element = arr[i] * (n - i)
			// (sum of remaining elements including the current one)
			totalCost += arr[i] * (n - i);
		}

		System.out.println("Minimum cost to remove all elements: " + totalCost);
	}
}
