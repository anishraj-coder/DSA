package Recursion;

/*Write a recursive function which returns
 *  true if the array is sorted and false
otherwise

print returned value.


Example(To be used only for expected output):
Input:
4
2 8 6 4
Output:
false
Input Format

Integer representing N representing length of array
N numbers of Array
Constraints

n <= 10 ^ 9
Output Format

Return true if array is sorted else false
Sample Input 0

5
10 20 30 40 50
Sample Output 0

true*/

public class IsSorted {

	public static boolean isSorted(int[] arr, int index) {
		// Base case: If the index reaches the end of the array (or beyond the second
		// last element),
		// it means the entire array has been checked and is considered sorted (assuming
		// the last element doesn't matter)
		if (index >= arr.length - 1) {
			return true;
		}

		// Make a recursive call to check if the remaining part of the array is sorted
		// (starting from the next element)
		boolean remainingIsSorted = isSorted(arr, index + 1);

		// Check if the current element (at index) is greater than the next element
		if (arr[index] > arr[index + 1]) {
			// If the current element is greater than the next, the array is not sorted
			return false;
		} else {
			// If the current element is not greater than the next, the sorted status
			// depends on the remaining part (returned by the recursive call)
			return remainingIsSorted;
		}
	}

	public static void main(String[] args) {
		int[] arr = { 2, 4, 6, 8 };
		System.out.println(isSorted(arr, 0)); // Output: true (array is sorted)
	}
}
