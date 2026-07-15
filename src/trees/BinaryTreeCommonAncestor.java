package trees;

import java.util.*;


public class BinaryTreeCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return ancestors(root,p,q);
    }

    private TreeNode ancestors(TreeNode root,TreeNode p,TreeNode q){
        if(root==null||root==p||root==q)return root;

        TreeNode left=ancestors(root.left,p,q);
        TreeNode right=ancestors(root.right,p,q);

        if(left!=null&&right!=null){
            return root;
        }

        return (left==null)?right:left;
    }

    // --- Test Utility Methods ---

    public static void main(String[] args) {
        BinaryTreeCommonAncestor finder = new BinaryTreeCommonAncestor();
        int passed = 0;
        int totalTests = 10;

        // Test Cases
        // Format: {Description, TreeArray, pVal, qVal, expectedLcaVal}
        Object[][] testCases = {
                { "Example 1: Nodes in different subtrees", new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 1, 3 },
                { "Example 2: One node is ancestor of other", new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 4, 5 },
                { "Example 3: Simple two-node tree", new Integer[]{1, 2}, 1, 2, 1 },
                { "LCA is the root (Deep Tree)", new Integer[]{1, 2, 3, 4, null, null, 5, 6, null, null, 7}, 6, 7, 1 },
                { "Both nodes in the left subtree", new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 6, 4, 5 },
                { "Both nodes in the right subtree", new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 0, 8, 1 },
                { "Large path (Skewed Left)", new Integer[]{5, 4, null, 3, null, 2, null, 1}, 1, 3, 3 },
                { "LCA is a middle node", new Integer[]{10, 5, 15, 3, 7, 13, 18}, 3, 7, 5 },
                { "Nodes are siblings", new Integer[]{1, 2, 3}, 2, 3, 1 },
                { "Tree with negative values", new Integer[]{-1, -2, -3, -4, -5}, -4, -5, -2 }
        };

        for (int i = 0; i < testCases.length; i++) {
            String description = (String) testCases[i][0];
            Integer[] treeData = (Integer[]) testCases[i][1];
            int pVal = (int) testCases[i][2];
            int qVal = (int) testCases[i][3];
            int expectedVal = (int) testCases[i][4];

            TreeNode root = buildTree(treeData);
            TreeNode p = findNode(root, pVal);
            TreeNode q = findNode(root, qVal);

            TreeNode result = finder.lowestCommonAncestor(root, p, q);
            int actualVal = (result != null) ? result.val : Integer.MIN_VALUE;

            System.out.println("Test " + (i + 1) + ": " + description);
            System.out.println("Input: p=" + pVal + ", q=" + qVal);
            System.out.println("Expected LCA: " + expectedVal);
            System.out.println("Actual LCA:   " + (actualVal == Integer.MIN_VALUE ? "null" : actualVal));

            if (actualVal == expectedVal) {
                System.out.println("Result:       PASSED\n");
                passed++;
            } else {
                System.out.println("Result:       FAILED\n");
            }
        }

        System.out.println("Final Score: " + passed + "/" + totalTests);
    }

    private static TreeNode buildTree(Integer[] vals) {
        if (vals == null || vals.length == 0) return null;
        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < vals.length) {
            TreeNode curr = queue.poll();
            if (vals[i] != null) {
                curr.left = new TreeNode(vals[i]);
                queue.add(curr.left);
            }
            i++;
            if (i < vals.length && vals[i] != null) {
                curr.right = new TreeNode(vals[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }

    private static TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        TreeNode left = findNode(root.left, val);
        if (left != null) return left;
        return findNode(root.right, val);
    }
}