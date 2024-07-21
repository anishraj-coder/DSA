package PriorityQueue;

import java.util.Collections;
import java.util.PriorityQueue;

/*The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 

Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
 

Constraints:

-105 <= num <= 105
There will be at least one element in the data structure before calling findMedian.
At most 5 * 104 calls will be made to addNum and findMedian.
 

Follow up:

If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?*/

/*
 * Problem: Find Median from Data Stream
 *
 * Objective:
 * Implement a data structure that can efficiently add numbers to a stream of integers
 * and find the median of all numbers added so far.
 *
 * Key Concepts:
 * 1. Median: The middle value in an ordered list of numbers.
 *    - For odd number of elements: The middle element
 *    - For even number of elements: Average of the two middle elements
 *
 * 2. Data Stream: A continuous flow of data where we don't know the end beforehand.
 *
 * Solution Approach:
 * We use two priority queues to maintain the stream in a way that allows quick access to the median:
 * 1. A max heap (left) for the lower half of the numbers
 * 2. A min heap (right) for the upper half of the numbers
 *
 * Key Invariants:
 * 1. All elements in the left (max) heap are <= all elements in the right (min) heap
 * 2. The size difference between the two heaps is at most 1
 *
 * Algorithm:
 * 1. Adding a number (addNum):
 *    a. If heaps are equal size:
 *       - Add to right heap
 *       - Move the smallest from right to left
 *    b. If left heap is larger:
 *       - Add to left heap
 *       - Move the largest from left to right
 *    This ensures the size difference is at most 1 and maintains the ordering invariant.
 *
 * 2. Finding median (findMedian):
 *    a. If heaps are equal size:
 *       - Median is average of tops of both heaps
 *    b. If left heap is larger:
 *       - Median is the top of left heap
 *
 * Efficiency:
 * - addNum: O(log n) time due to heap operations
 * - findMedian: O(1) time as we're just peeking at the top elements
 * - Space: O(n) to store all elements in the heaps
 *
 * Benefits of this approach:
 * 1. Efficient for both adding numbers and finding median
 * 2. Works well for streaming data where we don't know the total count in advance
 * 3. Can handle both odd and even number of elements seamlessly
 *
 * Trade-offs:
 * 1. Uses more memory compared to sorting-based approaches
 * 2. Slightly more complex implementation
 *
 * Follow-up Optimizations:
 * 1. If all numbers are in [0, 100]:
 *    - Use an array of 101 counters instead of heaps
 *    - addNum becomes O(1), findMedian becomes O(100) = O(1)
 *    - Significantly reduces memory usage for large streams
 *
 * 2. If 99% of numbers are in [0, 100]:
 *    - Combine array approach for [0, 100] with heaps for outliers
 *    - Optimizes for common case while still handling all possible inputs
 *    - Requires careful implementation to maintain correct statistics
 *
 * This solution provides a robust and efficient approach to the median-finding problem
 * in a streaming context, with room for optimization in specific scenarios.
 */

public class MedianOfUnsortedArray {

	public static void main(String[] args) {
		MedianFinder medianFinder = new MedianFinder();

		// Test case from the problem
		medianFinder.addNum(1);
		medianFinder.addNum(2);
		System.out.println("Median after adding 1 and 2: " + medianFinder.findMedian()); // Expected: 1.5

		medianFinder.addNum(3);
		System.out.println("Median after adding 3: " + medianFinder.findMedian()); // Expected: 2.0
	}
}

class MedianFinder {
	// Max heap for the lower half of the numbers
	PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
	// Min heap for the upper half of the numbers
	PriorityQueue<Integer> right = new PriorityQueue<>();

	public MedianFinder() {
		// Constructor - no initialization needed as heaps are already created
	}

	public void addNum(int num) {
		if (left.size() == right.size()) {
			// If heaps are equal, add to right and move its smallest to left
			right.add(num);
			left.add(right.remove());
		} else {
			// If left is larger, add to left and move its largest to right
			left.add(num);
			right.add(left.remove());
		}
	}

	public double findMedian() {
		if (left.size() == right.size()) {
			// If equal size, median is average of tops of both heaps
			return (left.peek() + right.peek()) / 2.0;
		} else {
			// If unequal, left heap has one extra element, so its top is the median
			return left.peek();
		}
	}
}

/*
 * Time Complexity: - addNum: O(log n) due to heap operations - findMedian: O(1)
 * as we're just peeking at the top elements
 *
 * Space Complexity: O(n) to store all elements in the heaps
 *
 * Follow-up Questions: 1. If all numbers are in [0, 100]: - We could use an
 * array of 101 counters instead of heaps - addNum would be O(1), findMedian
 * would be O(100) = O(1)
 *
 * 2. If 99% of numbers are in [0, 100]: - Use the array approach for [0, 100]
 * and heaps for others - This would optimize for the common case while still
 * handling outliers
 */