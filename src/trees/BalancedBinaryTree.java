package trees;

import java.util.*;


public class BalancedBinaryTree {

    /**
     * Constraints:
     * The number of nodes in the tree is in the range [0, 5000].
     * -10^4 <= Node.val <= 10^4
     */
    public boolean isBalanced(TreeNode root) {
        // Implementation goes here
        if(root==null)return true;
        return helper(root)!=-1;
    }

    private int helper(TreeNode root){
        if(root==null)return 0;
        int left=helper(root.left);
        int right=helper(root.right);
        if(left==-1||right==-1)return -1;
        if(Math.abs(left-right)>1)return -1;
        return Math.max(left,right)+1;
    }



    public static void main(String[] args) {
        BalancedBinaryTree processor = new BalancedBinaryTree();

        // Test Cases Setup
        // format: {Description, TreeNode root, Expected Boolean}
        Object[][] testCases = {
                { "Example 1: Balanced Tree", buildTree(new Integer[]{3, 9, 20, null, null, 15, 7}), true },
                { "Example 2: Unbalanced (Left Heavy)", buildTree(new Integer[]{1, 2, 2, 3, 3, null, null, 4, 4}), false },
                { "Example 3: Empty Tree", buildTree(new Integer[]{}), true },
                { "Single Node", buildTree(new Integer[]{1}), true },
                { "Unbalanced Right Skewed", buildTree(new Integer[]{1, null, 2, null, 3}), false },
                { "Perfectly Balanced (Full)", buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7}), true },
                { "Edge Case: Deeply Nested Balanced", buildTree(new Integer[]{1, 2, 3, 4, null, null, 5, 6, null, null, 7}), true },
                { "Edge Case: Root balanced but subtrees not", buildTree(new Integer[]{1, 2, 2, 3, 3, 3, 3, 4, 4, null, null, null, null, 4, 4}), false },
                { "Edge Case: Linked List Style (Unbalanced)", buildTree(new Integer[]{1, 2, null, 3, null, 4}), false },
                { "Slightly Unbalanced (Diff = 2)", buildTree(new Integer[]{1, 2, null, 3, null}), false }
        };

        int passed = 0;
        System.out.println("--- Starting Test Suite: Balanced Binary Tree ---");

        for (int i = 0; i < testCases.length; i++) {
            String description = (String) testCases[i][0];
            TreeNode root = (TreeNode) testCases[i][1];
            boolean expected = (boolean) testCases[i][2];

            boolean actual = processor.isBalanced(root);
            boolean isMatch = (actual == expected);

            if (isMatch) passed++;

            System.out.printf("Test %d: %s\n", i + 1, description);
            System.out.println("   Expected: " + expected);
            System.out.println("   Actual:   " + actual);
            System.out.println("   Status:   " + (isMatch ? "PASSED ✅" : "FAILED ❌"));
            System.out.println("-------------------------------------------------");
        }

        System.out.printf("Final Result: %d/%d Passed\n", passed, testCases.length);
    }

    /**
     * Helper method to build a tree from level-order array
     */
    private static TreeNode buildTree(Integer[] vals) {
        if (vals == null || vals.length == 0 || vals[0] == null) return null;
        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < vals.length) {
            TreeNode curr = queue.poll();
            if (i < vals.length && vals[i] != null) {
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
}