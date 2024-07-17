package Sorting;

import java.util.Arrays;

/*Q1.  Given N array Integers, Calculate number of good integers. 
 * An element is said to be good integers, if
 *  {No. of element < element == element itself}

Note: Array elements are distinct.
Input Format

Integer representing N representing length of array
N numbers of Array
Constraints

1<= N <= 10 ^ 6
1<= A[i] <=10 ^ 9
Output Format

Count of Good Integers
Sample Input 0

6
-1 -4 3 5 -15 4
Sample Output 0

3


Q2:  Given N array Integers, Calculate number of good integers. 
An element is said to be good integers, 
if {No. of element < element == element itself}

Note: Array elements can be duplicate.
Input Format

Integer representing N representing length of array
N numbers of Array
Constraints

1<= N <= 10 ^ 6
1<= A[i] <=10 ^ 9
Output Format

Count of Good Integers
Sample Input 0

6
2 3 3 8 2 0
Sample Output 0

3
*/

public class GoodInteger {
	public static int goodIntegerDistinct(int[] arr) {
		int n = arr.length;
		Arrays.sort(arr); // Sort the array for efficient comparison

		int count = 0;
		for (int i = 0; i < n; i++) {
			// Check if the current element's value (arr[i]) is equal to its index (i)
			if (arr[i] == i) {
				count++; // If yes, it's a good integer, increment the count
			}
		}
		return count;
	}

	public static int goodIntegerRepeat(int[] arr) {
		int n = arr.length;
		Arrays.sort(arr); // Sort the array to group duplicate elements

		int count = 0;
		int lessCount = 0; // Stores the count of elements less than the current element

		// Handle the special case where the first element is 0 (good integer)
		if (arr[0] == 0) {
			count++;
		}

		for (int i = 1; i < n; i++) {
			// Check if the current element is different from the previous element
			if (arr[i] != arr[i - 1]) {
				// If different, reset the count of elements less than the current element
				lessCount = i;
			}

			// Check if the count of elements less than the current element is equal to the
			// current element's value
			if (lessCount == arr[i]) {
				count++; // If yes, it's a good integer, increment the count
			}
		}
		return count;
	}

	public static void main(String[] args) {

		System.out.println(goodIntegerDistinct(new int[] { -1, -4, 3, 5, -15, 4 }));
		System.out.println(goodIntegerRepeat(new int[] { 2, 3, 3, 8, 2, 0 }));
	}

}
