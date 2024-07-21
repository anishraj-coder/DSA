package PriorityQueue;

import java.util.Collections;
import java.util.PriorityQueue;

/*You are given an array of integers stones where stones[i] is the weight
 *  of the ith stone.

We are playing a game with the stones. On each turn, we choose the heaviest two
 stones and smash them together. Suppose the heaviest two stones have weights x
  and y with x <= y. The result of this smash is:

If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has 
new weight y - x.
At the end of the game, there is at most one stone left.

Return the weight of the last remaining stone. If there are no stones left, 
return 0.

 

Example 1:

Input: stones = [2,7,4,1,8,1]
Output: 1
Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's 
the value of the last stone.
Example 2:

Input: stones = [1]
Output: 1
 */

/*
 * Problem: Last Stone Weight
 *
 * We are given an array of integers representing stone weights. The goal is to simulate
 * a game where we repeatedly smash the two heaviest stones together until at most one stone remains.
 *
 * Game Rules:
 * 1. Choose the two heaviest stones.
 * 2. If they are equal, both are destroyed.
 * 3. If they are different, the smaller one is destroyed, and the larger one's weight is reduced by the smaller one's weight.
 * 4. Repeat until at most one stone remains.
 *
 * The task is to return the weight of the last remaining stone, or 0 if no stones remain.
 *
 * Solution Approach:
 * We use a max heap (implemented with PriorityQueue) to efficiently access the heaviest stones.
 * This allows us to always have the heaviest stones available for smashing without sorting the entire array each time.
 */

public class LastStoneWeight {

	public static void main(String[] args) {
		SolutionLastStone solution = new SolutionLastStone();

		// Test case 1
		int[] stones1 = { 2, 7, 4, 1, 8, 1 };
		System.out.println("Test Case 1 Output: " + solution.lastStoneWeight(stones1)); // Expected: 1

		// Test case 2
		int[] stones2 = { 1 };
		System.out.println("Test Case 2 Output: " + solution.lastStoneWeight(stones2)); // Expected: 1
	}
}

class SolutionLastStone {
	public int lastStoneWeight(int[] stones) {
		// Create a max heap using PriorityQueue with reverse order
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

		// Add all stones to the max heap
		for (int stone : stones) {
			maxHeap.add(stone);
		}

		// Continue smashing stones until at most one remains
		while (maxHeap.size() > 1) {
			// Remove the two heaviest stones
			int stone1 = maxHeap.remove();
			int stone2 = maxHeap.remove();

			// Calculate the difference and add back if not zero
			int difference = stone1 - stone2;
			if (difference != 0) {
				maxHeap.add(difference);
			}
		}

		// Return the last stone weight or 0 if no stones remain
		return maxHeap.isEmpty() ? 0 : maxHeap.peek();
	}
}

/*
 * Explanation of the solution:
 *
 * 1. Data Structure Choice: - We use a max heap (PriorityQueue with reverse
 * order) to always have quick access to the heaviest stones. - This allows
 * O(log n) time complexity for both insertion and removal of the heaviest
 * element.
 *
 * 2. Algorithm Steps: a. Initialize a max heap and add all stones to it. b.
 * While there are at least two stones in the heap: - Remove the two heaviest
 * stones. - Calculate their difference. - If the difference is not zero, add it
 * back to the heap. c. After the loop, if the heap is empty, return 0.
 * Otherwise, return the weight of the remaining stone.
 *
 * 3. Time Complexity: - Building the heap: O(n log n) - Each iteration of
 * smashing stones: O(log n) - In the worst case, we might have n/2 iterations.
 * - Overall time complexity: O(n log n)
 *//*
	 *
	 * 
	 * 4. Space Complexity: - O(n) to store all stones in the heap.
	 *
	 * 5. Advantages: - Efficient for large inputs as we don't need to sort the
	 * entire array each time. - Provides a straightforward implementation of the
	 * game rules. - Easily adaptable if the game rules change slightly.
	 *
	 * 6. Potential Optimizations: - For very small inputs, a simple sorting
	 * approach might be faster due to less overhead. - If the range of stone
	 * weights is limited, we could use a counting sort approach.
	 *
	 * 7. Edge Cases Handled: - Works correctly for a single stone input. - Handles
	 * the case where all stones are destroyed (returns 0).
	 *
	 * 8. Alternative Approaches: - We could use an array and sort it in descending
	 * order, updating the top two elements each time. This would have O(n log n)
	 * time complexity for the initial sort, but O(n) for each iteration. - For
	 * small datasets, this might be more efficient due to better cache locality.
	 *
	 * 9. Why This Solution Works: - The max heap ensures we always have access to
	 * the two largest stones in O(log n) time. - By repeatedly smashing the two
	 * largest stones, we're directly implementing the game rules. - The process
	 * naturally terminates when at most one stone remains, giving us our answer.
	 *
	 * This solution provides an efficient and intuitive approach to the problem,
	 * effectively simulating the stone-smashing game while maintaining good time
	 * complexity.
	 */
