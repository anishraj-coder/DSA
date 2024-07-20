package Tree;

/*
 * Same Tree Problem
 *
 * Problem Statement:
 * Given the roots of two binary trees p and q, determine if they are the same tree.
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 *
 * Ideology and Method:
 * 1. Recursive Approach: The solution uses recursion to traverse both trees simultaneously.
 * 2. Base Cases:
 *    a. If both nodes are null, the subtrees are identical (empty).
 *    b. If one node is null and the other isn't, the trees are not identical.
 *    c. If the values of the current nodes are different, the trees are not identical.
 * 3. Recursive Steps:
 *    a. Compare the left subtrees of both trees.
 *    b. Compare the right subtrees of both trees.
 * 4. The trees are identical only if both left and right subtrees are identical.
 *
 * Time Complexity: O(min(n,m)) where n and m are the number of nodes in the trees.
 * Space Complexity: O(min(h1,h2)) where h1 and h2 are the heights of the trees (due to recursion stack).
 */

public class SameTree {

	// Main method to compare two trees
	static boolean isSameTree(Node p, Node q) {
		// Base case 1: Both nodes are null (empty subtrees are identical)
		if (p == null && q == null)
			return true;

		// Base case 2: One node is null and the other isn't (trees are different)
		if (p == null || q == null)
			return false;

		// Base case 3: Values are different (trees are different)
		if (p.val != q.val)
			return false;

		// Recursive calls
		boolean left = isSameTree(p.left, q.left); // Compare left subtrees
		boolean right = isSameTree(p.right, q.right); // Compare right subtrees

		// Trees are identical only if both left and right subtrees are identical
		return left && right;
	}

	public static void main(String[] args) {
		// Create the first tree
		Node tree1 = new Node(1);
		tree1.left = new Node(2);
		tree1.right = new Node(3);

		// Create the second tree (identical to the first)
		Node tree2 = new Node(1);
		tree2.left = new Node(2);
		tree2.right = new Node(3);

		// Create the third tree (different from the first two)
		Node tree3 = new Node(1);
		tree3.left = new Node(2);
		tree3.right = new Node(4);

		// Compare trees
		System.out.println("Tree1 and Tree2 are the same: " + isSameTree(tree1, tree2)); // Should print true
		System.out.println("Tree1 and Tree3 are the same: " + isSameTree(tree1, tree3)); // Should print false
	}
}

/*
 * Node class (assumed to be defined elsewhere) class Node { int val; Node left;
 * Node right; Node(int val) { this.val = val; } }
 */