package Tree;

import java.util.LinkedList;
import java.util.Queue;

/*Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level x such that the sum of all the values of nodes at level x is maximal.

 

Example 1:


Input: root = [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
Example 2:

Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
Output: 2
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105*/

/*
 * Maximum Level Sum of a Binary Tree
 *
 * Problem Statement:
 * Given the root of a binary tree, find the level with the maximum sum of node values.
 * The root is at level 1, its children are at level 2, and so on.
 * Return the smallest level with the maximum sum.
 *
 * Detailed Explanation:
 * 1. Tree Structure:
 *    - Each node in the binary tree has a value and potentially left and right children.
 *    - Levels in the tree are defined by depth, starting with the root at level 1.
 *
 * 2. Goal:
 *    - Calculate the sum of node values at each level.
 *    - Identify the level with the maximum sum.
 *    - If multiple levels have the same maximum sum, return the smallest level number.
 *
 * 3. Approach:
 *    - Use Breadth-First Search (BFS) to traverse the tree level by level.
 *    - BFS ensures that we process all nodes at one level before moving to the next.
 *    - This allows us to easily keep track of the current level and calculate its sum.
 *
 * 4. Implementation Details:
 *    a. Queue Usage:
 *       - Utilize a queue to perform BFS.
 *       - The queue stores nodes to be processed at each level.
 *    
 *    b. Level Processing:
 *       - For each level, process all nodes currently in the queue.
 *       - Sum up the values of all nodes at the current level.
 *       - Add child nodes to the queue for processing in the next iteration.
 *    
 *    c. Tracking Maximum Sum:
 *       - Keep a running track of the maximum sum encountered.
 *       - When a new maximum is found, update both the sum and the level number.
 *    
 *    d. Level Counting:
 *       - Increment the level counter at the start of processing each new level.
 *       - This ensures accurate level numbering.
 *
 * 5. Time and Space Complexity:
 *    - Time Complexity: O(n), where n is the number of nodes in the tree.
 *      Each node is processed exactly once.
 *    - Space Complexity: O(w), where w is the maximum width of the tree.
 *      In the worst case, this could be O(n) for a complete binary tree.
 */

public class MaximumSum {
	// Method to print the sum of each level (for debugging/visualization)
	static void levelSum(Node node) {
		Queue<Node> q = new LinkedList<>();
		q.add(node);
		int level = 0;
		while (!q.isEmpty()) {
			int n = q.size();
			int sum = 0;
			level++;
			System.out.print("Level " + level + " sum: ");
			for (int i = 0; i < n; i++) {
				Node rem = q.remove();
				sum += rem.val;
				if (rem.left != null)
					q.add(rem.left);
				if (rem.right != null)
					q.add(rem.right);
			}
			System.out.println(sum);
		}
	}

	// Method to find the level with the maximum sum
	static int maxLevelSum(Node node) {
		if (node == null)
			return 0;

		Queue<Node> q = new LinkedList<>();
		q.add(node);
		int level = 0, maxLevel = 0;
		int maxSum = Integer.MIN_VALUE;

		while (!q.isEmpty()) {
			int n = q.size();
			int sum = 0;
			level++; // Increment level at the start of each iteration

			// Process all nodes at the current level
			for (int i = 0; i < n; i++) {
				Node rem = q.remove();
				sum += rem.val;
				// Add child nodes to the queue for the next level
				if (rem.left != null)
					q.add(rem.left);
				if (rem.right != null)
					q.add(rem.right);
			}

			// Update maxSum and maxLevel if current level sum is greater
			if (sum > maxSum) {
				maxSum = sum;
				maxLevel = level;
			}
		}
		return maxLevel;
	}

	public static void main(String[] args) {
		// Create a sample binary tree
		Tree ro = new Tree(5);
		ro.addLeft(ro.root, 44);
		ro.addRight(ro.root, 4);
		ro.addLeft(ro.root.left, 6);
		ro.addRight(ro.root.left, 34);
		ro.addLeft(ro.root.right, 23);
		ro.addRight(ro.root.right, 129);
		ro.addLeft(ro.root.right.right, 56);
		ro.addRight(ro.root.right.right, 69);

		// Display the tree structure
		System.out.println("Tree structure:");
		ro.view();

		// Print sum of each level
		System.out.println("\nSum of each level:");
		levelSum(ro.root);

		// Find and print the level with maximum sum
		int maxSumLevel = maxLevelSum(ro.root);
		System.out.println("\nLevel with Maximum Sum: " + maxSumLevel);
	}
}