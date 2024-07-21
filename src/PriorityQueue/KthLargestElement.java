package PriorityQueue;

import java.util.PriorityQueue;
/*Question: Kth Largest Element in an Array
 * Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?

 

Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4*/

/*
 * Detailed Explanation of the Solution:
 *
 * 1. Problem Understanding:
 *    We need to find the kth largest element in an unsorted array without sorting the entire array.
 *    This requires a more efficient approach than the O(n log n) time complexity of sorting.
 *
 * 2. Solution Approach:
 *    We use a min-heap (implemented with PriorityQueue in Java) to keep track of the k largest elements.
 *    This approach is efficient because we only need to maintain k elements at any given time.
 *
 * 3. Algorithm Steps:
 *    a. Initialize a min-heap of size k.
 *    b. Add the first k elements from the array to the min-heap.
 *    c. For each remaining element in the array:
 *       - Compare it with the smallest element in the min-heap (the top element).
 *       - If the current element is larger, remove the top element and add the current one.
 *    d. After processing all elements, the top of the min-heap is the kth largest element.
 *
 * 4. Detailed Walkthrough:
 *    - The min-heap always contains the k largest elements seen so far.
 *    - The top of the heap is always the smallest among these k elements.
 *    - By comparing each new element with the top of the heap, we ensure that:
 *      * If it's smaller than the top, it's not among the k largest elements.
 *      * If it's larger, it replaces the smallest of the k largest elements.
 *    - This process maintains the invariant that the heap always contains the k largest elements.
 *
 * 5. Time Complexity Analysis:
 *    - Building initial heap with k elements: O(k log k)
 *    - Processing remaining (n-k) elements: O((n-k) log k)
 *    - Overall time complexity: O(n log k)
 *    This is more efficient than sorting when k is significantly smaller than n.
 *
 * 6. Space Complexity:
 *    - O(k) for storing k elements in the min-heap.
 *
 * 7. Advantages:
 *    - Works efficiently for large arrays with small k.
 *    - Doesn't require sorting the entire array.
 *    - Can be adapted for streaming data or very large datasets that don't fit in memory.
 *
 * 8. Potential Optimizations:
 *    - If k > n/2, it might be more efficient to use a max-heap of size n-k+1 instead.
 *    - For very large n and small k, a selection algorithm like QuickSelect might be considered.
 *
 * This solution elegantly balances time efficiency and implementation simplicity,
 * making it an excellent choice for finding the kth largest element in most practical scenarios.
 */

public class KthLargestElement {

	public static void main(String[] args) {
		// Test case 1
		int[] nums1 = { 3, 2, 1, 5, 6, 4 };
		int k1 = 2;
		System.out.println("Example 1 Output: " + findKthLargest(nums1, k1)); // Expected output: 5

		// Test case 2
		int[] nums2 = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
		int k2 = 4;
		System.out.println("Example 2 Output: " + findKthLargest(nums2, k2)); // Expected output: 4
	}

	public static int findKthLargest(int[] nums, int k) {
		// Create a min-heap (PriorityQueue) to store the k largest elements
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		// Add the first k elements to the min-heap
		for (int i = 0; i < k; i++) {
			minHeap.add(nums[i]);
		}

		// Process the remaining elements
		for (int i = k; i < nums.length; i++) {
			// If the current element is larger than the smallest element in the heap,
			// remove the smallest and add the current element
			if (nums[i] > minHeap.peek()) {
				minHeap.remove();
				minHeap.add(nums[i]);
			}
		}

		// The top of the min-heap now contains the kth largest element
		return minHeap.peek();
	}
}