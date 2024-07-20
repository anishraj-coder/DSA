package Searching;

/*You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

 

Example 1:

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: nums = [3,3,7,7,10,11,11]
Output: 10
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 105*/

public class SearchSingleElement {
	public static int search(int[] arr) {
		if (arr.length == 1)
			return arr[0];
		if (arr[0] != arr[1])
			return arr[0];
		if (arr[arr.length - 1] != arr[arr.length - 2])
			return arr[arr.length - 1];
		int low = 2, hi = arr.length - 3;
		while (low <= hi) {
			int mid = (low + hi) / 2;
			if (arr[mid] != arr[mid - 1] && arr[mid + 1] != arr[mid])
				return arr[mid];
			if (arr[mid] == arr[mid - 1])
				mid--;
			if (mid % 2 == 0)
				low = mid + 2;
			else
				hi = mid - 1;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] arr = { 3, 3, 7, 7, 10, 10, 11, 11 };
		System.out.println(search(arr));
	}

}