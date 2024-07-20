package Tree;

/*Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along 
the longest path from the root node down to the farthest leaf node.

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 3
Example 2:

Input: root = [1,null,2]
Output: 2
 */

/*
Problem Statement:
Given the root of a binary tree, return its maximum depth.
A binary tree's maximum depth is the number of nodes along 
the longest path from the root node down to the farthest leaf node.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 3

Example 2:
Input: root = [1,null,2]
Output: 2

Detailed Explanation of the Problem and Solution:

1. Problem Understanding:
   - We are dealing with a binary tree data structure.
   - Each node in the tree can have at most two children (left and right).
   - The depth of a node is its distance from the root (number of edges).
   - We need to find the maximum depth, which is the depth of the deepest leaf node.

2. Solution Approach:
   a. Recursive Depth-First Search (DFS):
      - We use a recursive approach to traverse the tree.
      - This naturally implements a depth-first search, exploring each path to its end before backtracking.

   b. Divide and Conquer:
      - We break down the problem into subproblems for each subtree.
      - The maximum depth of a tree is the maximum of:
        1) The depth of its left subtree
        2) The depth of its right subtree
      - We add 1 to this maximum to account for the current node.

   c. Base Case:
      - An empty tree (null node) has a depth of 0.
      - This serves as our recursion termination condition.

3. Algorithm Steps:
   - If the current node is null, return 0.
   - Recursively calculate the maximum depth of the left subtree.
   - Recursively calculate the maximum depth of the right subtree.
   - Return the maximum of these two depths plus 1.

4. Time and Space Complexity:
   - Time Complexity: O(n), where n is the number of nodes in the tree.
     We visit each node exactly once.
   - Space Complexity: O(h), where h is the height of the tree.
     This is due to the recursion stack. In the worst case (skewed tree), it can be O(n).

5. Advantages of this Approach:
   - Simple and intuitive implementation.
   - Efficiently handles both balanced and unbalanced trees.
   - Can be easily modified to solve related problems (e.g., minimum depth, balanced tree check).

6. Potential Optimizations:
   - For very large trees, an iterative approach using a stack might be more space-efficient.
   - In a threaded environment, parallel processing of left and right subtrees could be considered for very large trees.

7. Edge Cases to Consider:
   - Empty tree (root is null)
   - Tree with only one node
   - Skewed tree (where the tree resembles a linked list)
*/

public class MaxDepth {
	// The main function to calculate the maximum depth of the binary tree
	public static int maxDepth(Node node) {
		// Base case: if the node is null, its depth is 0
		// This handles both empty trees and the termination of recursion at leaf nodes
		if (node == null)
			return 0;

		// Recursive case: explore left and right subtrees
		// Calculate the maximum depth of the left subtree
		int leftDepth = maxDepth(node.left);

		// Calculate the maximum depth of the right subtree
		int rightDepth = maxDepth(node.right);

		// The maximum depth at this node is the larger of the two subtree depths, plus
		// 1 for this node
		return Math.max(leftDepth, rightDepth) + 1;
	}

	public static void main(String[] args) {
		// Create a sample binary tree for testing
		Tree ro = new Tree(5); // Root node with value 5
		ro.addLeft(ro.root, 44);
		ro.addRight(ro.root, 4);
		ro.addLeft(ro.root.left, 6);
		ro.addRight(ro.root.left, 34);
		ro.addLeft(ro.root.right, 23);
		ro.addRight(ro.root.right, 129);
		ro.addLeft(ro.root.right.right, 56);
		ro.addRight(ro.root.right.right, 69);

		// The tree structure created looks like this:
		// 5
		// / \
		// 44 4
		// / \ / \
		// 6 34 23 129
		// / \
		// 56 69

		// Display the tree structure
		// This likely calls a method that prints the tree in a readable format
		ro.view();

		// Calculate and print the maximum depth of the tree
		// We pass the root of the tree to our maxDepth function
		System.out.println("MaxDepth:\t" + maxDepth(ro.root));

		// Expected output: MaxDepth: 4
		// Explanation of the result:
		// 1. The longest path in this tree is: 5 -> 4 -> 129 -> 69 (or 56)
		// 2. This path contains 4 nodes, so the maximum depth is 4

		// Additional test cases could be added here to verify the function's
		// correctness:
		// 1. Test with an empty tree
		// System.out.println("Empty tree depth: " + maxDepth(null)); // Should print 0

		// 2. Test with a tree containing only the root
		// Tree singleNodeTree = new Tree(1);
		// System.out.println("Single node tree depth: " +
		// maxDepth(singleNodeTree.root)); // Should print 1

		// 3. Test with a skewed tree (essentially a linked list)
		// Tree skewedTree = new Tree(1);
		// skewedTree.addRight(skewedTree.root, 2);
		// skewedTree.addRight(skewedTree.root.right, 3);
		// System.out.println("Skewed tree depth: " + maxDepth(skewedTree.root)); //
		// Should print 3
	}
}