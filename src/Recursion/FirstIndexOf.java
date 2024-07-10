package Recursion;

/*Write a recursive function which returns the first index at which M is found in
the array and -1 if M is not found anywhere.

print returned value.


Example(To be used only for expected output):
Input:
4
2 8 6 4 
5
Output:
-1
Input Format

Integer representing N representing length of array
N numbers of Array
Constraints

1<= N <= 10 ^ 9
1<= A[i] <=10 ^ 9
1<= M <= 10 ^ 9
Output Format

Return the first Index of the given number in the array.
Sample Input 0

5
10 20 30 40 10
20
Sample Output 0

1*/

public class FirstIndexOf {
	public static int firstIndex(int[] arr, int index, int target) {
		// Base case: If the index reaches the end of the array (or beyond), the target
		// element is not found
		if (index >= arr.length) {
			return -1;
		}

		// Check if the current element is the target element
		if (arr[index] == target) {
			// If the target element is found at the current index, return the index
			return index;
		} else {
			// If not the target element, make a recursive call to check the remaining part
			// of the array (starting from the next element)
			return firstIndex(arr, index + 1, target);
		}
	}

	public static void main(String[] args) {
		int[] arr = { 10, 20, 40, 60, 10 };
		System.out.println(firstIndex(arr, 0, 20));

	}

}
