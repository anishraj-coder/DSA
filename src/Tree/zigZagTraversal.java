package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*Binary Tree Zigzag Level Order Traversal
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []
 */

/*
 * Binary Tree Zigzag Level Order Traversal
 * 
 * Problem Statement:
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * Zigzag order means alternating between left-to-right and right-to-left for each level.
 * 
 * Example:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 * 
 * Detailed Explanation:
 * 
 * 1. Problem Analysis:
 *    - We need to traverse the binary tree level by level.
 *    - The traversal direction alternates between levels:
 *      - Odd levels: left to right
 *      - Even levels: right to left
 *    - The output should be a list of lists, where each inner list represents a level.
 * 
 * 2. Solution Approach:
 *    - Use two stacks to manage the traversal:
 *      a. Main Stack (ms): Holds nodes of the current level
 *      b. Child Stack (cs): Temporarily stores nodes for the next level
 *    - Maintain a level counter to determine the traversal direction.
 *    - Process levels iteratively:
 *      a. Pop nodes from the main stack
 *      b. Add their values to the current level's list
 *      c. Push their children to the child stack in the appropriate order
 *    - After processing a level, swap the main and child stacks.
 * 
 * 3. Key Concepts:
 *    - Stack data structure: LIFO (Last In, First Out) principle
 *    - Level-order traversal: Processing nodes level by level
 *    - Alternating traversal direction: Achieved by changing the order of pushing children
 * 
 * 4. Time and Space Complexity:
 *    - Time Complexity: O(n), where n is the number of nodes in the tree
 *    - Space Complexity: O(w), where w is the maximum width of the tree
 * 
 * 5. Edge Cases and Considerations:
 *    - Empty tree: Return an empty list
 *    - Single node tree: Return a list containing a single list with the root value
 *    - Unbalanced trees: The algorithm handles these correctly due to level-wise processing
 */

class zigZagTraversal {
	static List<List<Integer>> zizZagTraverse(Node node) {
		// Initialize data structures
		Stack<Node> ms = new Stack<>(); // Main stack for current level
		Stack<Node> cs = new Stack<>(); // Child stack for next level
		int level = 1; // Level counter (starting from 1 for odd/even distinction)
		List<List<Integer>> ans = new ArrayList<>(); // Final result list

		// Handle empty tree case
		if (node == null)
			return ans;

		ms.push(node); // Push root node to start traversal

		while (!ms.isEmpty()) {
			List<Integer> temp = new ArrayList<>(); // Temporary list for current level

			// Process all nodes in the current level
			while (!ms.isEmpty()) {
				Node rem = ms.pop();
				temp.add(rem.val);

				// Determine order of adding children based on current level
				if (level % 2 != 0) { // Odd level: left to right
					if (rem.left != null)
						cs.push(rem.left);
					if (rem.right != null)
						cs.push(rem.right);
				} else { // Even level: right to left
					if (rem.right != null)
						cs.push(rem.right);
					if (rem.left != null)
						cs.push(rem.left);
				}
			}

			level++; // Move to next level
			ans.add(temp); // Add current level's list to final result

			// Swap stacks for next iteration
			Stack<Node> t = cs;
			cs = ms;
			ms = t;
		}

		return ans;
	}

	public static void main(String[] args) {
		// Create and populate the binary tree
		Tree ro = new Tree(5);
		ro.addLeft(ro.root, 44);
		ro.addRight(ro.root, 4);
		ro.addLeft(ro.root.left, 6);
		ro.addRight(ro.root.left, 34);
		ro.addLeft(ro.root.right, 23);
		ro.addRight(ro.root.right, 29);
		ro.addLeft(ro.root.right.right, 56);
		ro.addRight(ro.root.right.right, 69);

		ro.view(); // Display the tree structure
		System.out.println("_____________________________");

		// Perform zigzag traversal and print the result
		List<List<Integer>> ans = zizZagTraverse(ro.root);
		for (int i = 0; i < ans.size(); i++) {
			for (int j = 0; j < ans.get(i).size(); j++) {
				System.out.print(ans.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}
}

/*
 * Additional Notes on the Implementation:
 * 
 * 1. Stack Swapping: - After processing each level, we swap the main stack (ms)
 * and child stack (cs). - This efficiently prepares us for the next level
 * without needing to transfer elements.
 * 
 * 2. Level Tracking: - The 'level' variable keeps track of the current tree
 * level. - It's used to determine whether to process left-to-right or
 * right-to-left. - Incrementing it after each level ensures alternating
 * behavior.
 * 
 * 3. Null Checks: - We only add non-null children to the child stack,
 * preventing null pointer exceptions.
 * 
 * 4. Result Building: - Each level's nodes are collected in a temporary list
 * (temp). - This temp list is then added to the final result list (ans).
 * 
 * 5. Main Method: - Demonstrates how to use the zizZagTraverse method. -
 * Creates a sample binary tree and performs the zigzag traversal. - Prints the
 * result, showing each level on a new line.
 * 
 * 6. Flexibility: - This implementation works for any binary tree, regardless
 * of its structure or balance.
 * 
 * 7. Potential Optimizations: - For very large trees, consider using queues
 * instead of stacks for better memory efficiency. - If tree modification is
 * allowed, you could add a 'level' field to nodes to avoid using a separate
 * level counter.
 */