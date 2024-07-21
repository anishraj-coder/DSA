package PriorityQueue;

/*Question: 
 * Given an array arr[] and an integer k where k is smaller than the size of
 *  the array, the task is to find the kth smallest element in the given array. It is given that all array elements are distinct.

Note:-  l and r denotes the starting and ending index of the array.

Example 1:

Input:
n = 6
arr[] = 7 10 4 3 20 15
k = 3
l=0 r=5

Output : 
7

Explanation :
3rd smallest element in the given 
array is 7.
Example 2:

Input:
n = 5
arr[] = 7 10 4 20 15
k = 4 
l=0 r=4

Output : 
15

Explanation :
4th smallest element in the given 
array is 15.
Your Task:
You don't have to read input or print anything.
 Your task is to complete the function kthSmallest() which takes the array 
 arr[], integers l and r denoting the starting and ending index of the array 
 and an integer k as input and returns the kth smallest element.
 
Expected Time Complexity: O(n*log(n) )
Expected Auxiliary Space: O(log(n))
Constraints:
1 <= n <= 105
l = 0
r = N-1
1<= arr[i] <= 105
1 <= k <= n*/

import java.util.Collections;
import java.util.PriorityQueue;

/*
 * Problem: Find the kth smallest element in an array
 * 
 * Given an array arr[] and an integer k where k is smaller than the size 
 * of the array, the task is to find the kth smallest element in the given 
 * array. It is given that all array elements are distinct.
 * 
 * Detailed Approach:
 * 1. Use a max heap (PriorityQueue with reverse order) to keep track of the k smallest elements.
 *    - A max heap is chosen because we want to easily access and remove the largest among the k smallest elements.
 * 
 * 2. Initialize the heap with the first k elements of the array.
 *    - This gives us an initial set of k elements to work with.
 * 
 * 3. For the remaining elements (from index k to the end of the array):
 *    - Compare each element with the top of the heap (which is the largest among the k elements in the heap).
 *    - If the current element is smaller than the top of the heap:
 *      a. Remove the top element from the heap (as it's no longer among the k smallest).
 *      b. Add the current element to the heap.
 *    - This process ensures that at any point, the heap contains the k smallest elements seen so far.
 * 
 * 4. After processing all elements, the top of the heap will be the kth smallest element in the entire array.
 * 
 * Time Complexity Analysis:
 * - Initializing the heap with k elements: O(k log k)
 * - Processing the remaining (n-k) elements: O((n-k) log k)
 * - Overall: O(n log k), which is more efficient than sorting the entire array when k is small.
 * 
 * Space Complexity: O(k) for storing k elements in the heap.
 * 
 * Why this approach is efficient:
 * 1. We only keep k elements in the heap at any time, which is memory-efficient for large arrays and small k.
 * 2. We don't need to sort the entire array, which would take O(n log n) time.
 * 3. For each element, we perform at most one heap insertion and one heap deletion, both of which are O(log k) operations.
 * 
 * Alternative Approaches:
 * 1. Sorting the entire array: O(n log n) time, O(1) extra space, but inefficient for large arrays and small k.
 * 2. QuickSelect algorithm: O(n) average time complexity, but O(n^2) worst case.
 * 3. Counting sort or radix sort: O(n) time if the range of elements is known and small.
 */

class KthSmallestElement {
	public static void main(String[] args) {
		// Test case 1
		int[] arr1 = { 7, 10, 4, 3, 20, 15 };
		int k1 = 3;
		int n1 = arr1.length;
		System.out.println("Test case 1 output: " + SolutionKthSmallestElement.kthSmallest(arr1, 0, n1 - 1, k1));

		// Test case 2
		int[] arr2 = { 7, 10, 4, 20, 15 };
		int k2 = 4;
		int n2 = arr2.length;
		System.out.println("Test case 2 output: " + SolutionKthSmallestElement.kthSmallest(arr2, 0, n2 - 1, k2));
	}
}

class SolutionKthSmallestElement {
	public static int kthSmallest(int[] arr, int l, int r, int k) {
		// Create a max heap (PriorityQueue with reverse order)
		// This allows us to easily remove the largest element when needed
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

		// Add first k elements to the heap
		// This initializes our heap with a set of k elements to start with
		for (int i = l; i < l + k; i++) {
			maxHeap.add(arr[i]);
		}

		// Process the remaining elements
		// For each element, we check if it's smaller than the largest element in our
		// current k-smallest set
		for (int i = l + k; i <= r; i++) {
			// If the current element is smaller than the largest in our heap
			if (arr[i] < maxHeap.peek()) {
				// Remove the largest element from the heap
				maxHeap.poll();
				// Add the current element to the heap
				maxHeap.add(arr[i]);
			}
			// If the current element is larger, we ignore it as it can't be among the
			// k-smallest
		}

		// At this point, the heap contains the k smallest elements
		// The top of the heap (largest among these k) is the kth smallest element
		return maxHeap.peek();
	}
}