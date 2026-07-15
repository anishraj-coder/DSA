package trees;

import java.util.*;

public class SymmetricTree {

    /**
     * Complete the function below to check if the tree is symmetric.
     */
    public boolean isSymmetric(TreeNode root) {
        // Your implementation here
        return root==null||helper(root.left,root.right);
    }

    private boolean helper(TreeNode left,TreeNode right){
        if(left==null||right==null)return left==right;
        if(left.val!=right.val)return false;

        return helper(left.left,right.right)&&helper(left.right,right.left);
    }

    public static void main(String[] args) {
        SymmetricTree solver = new SymmetricTree();

        // Test Cases
        TestCase[] testCases = {
                // Case 1: Standard Symmetric Tree
                new TestCase(buildTree(new Integer[]{1, 2, 2, 3, 4, 4, 3}), true, "Standard Symmetric Tree"),

                // Case 2: Standard Asymmetric Tree
                new TestCase(buildTree(new Integer[]{1, 2, 2, null, 3, null, 3}), false, "Standard Asymmetric Tree"),

                // Case 3: Single Node (Edge Case)
                new TestCase(buildTree(new Integer[]{1}), true, "Single Node Tree"),

                // Case 4: Values match but structure doesn't
                new TestCase(buildTree(new Integer[]{1, 2, 2, 2, null, 2}), false, "Matching values, asymmetric structure"),

                // Case 5: Left heavy tree
                new TestCase(buildTree(new Integer[]{1, 2, null, 3, null}), false, "Left heavy asymmetric"),

                // Case 6: Large Symmetric Tree
                new TestCase(buildTree(new Integer[]{1, 2, 2, 3, 4, 4, 3, 5, 6, 7, 8, 8, 7, 6, 5}), true, "Large Symmetric Tree"),

                // Case 7: All nodes same value but asymmetric structure
                new TestCase(buildTree(new Integer[]{1, 1, 1, 1, null, 1, null}), false, "Same values, asymmetric structure"),

                // Case 8: Mirror values are different
                new TestCase(buildTree(new Integer[]{1, 2, 2, 3, 4, 4, 5}), false, "Values mismatch at leaves"),

                // Case 9: Empty Tree (Constraint says 1-1000 nodes, but good to handle)
                new TestCase(null, true, "Empty Tree"),

                // Case 10: Two nodes, different values
                new TestCase(buildTree(new Integer[]{1, 2, 3}), false, "Two children, different values")
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            boolean actual = solver.isSymmetric(testCases[i].root);
            boolean status = actual == testCases[i].expected;
            if (status) passed++;

            System.out.printf("Test Case %d: %s\n", i + 1, testCases[i].description);
            System.out.println("Expected: " + testCases[i].expected + " | Actual: " + actual);
            System.out.println("Result: " + (status ? "PASSED ✅" : "FAILED ❌"));
            System.out.println("--------------------------------------------------");
        }

        System.out.printf("Final Result: %d/%d Passed\n", passed, testCases.length);
    }

    // Helper class for test cases
    static class TestCase {
        TreeNode root;
        boolean expected;
        String description;

        TestCase(TreeNode root, boolean expected, String description) {
            this.root = root;
            this.expected = expected;
            this.description = description;
        }
    }

    // Helper method to build tree from Level-Order array
    public static TreeNode buildTree(Integer[] nodes) {
        if (nodes == null || nodes.length == 0) return null;
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < nodes.length) {
            TreeNode curr = queue.poll();
            if (nodes[i] != null) {
                curr.left = new TreeNode(nodes[i]);
                queue.add(curr.left);
            }
            i++;
            if (i < nodes.length && nodes[i] != null) {
                curr.right = new TreeNode(nodes[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}