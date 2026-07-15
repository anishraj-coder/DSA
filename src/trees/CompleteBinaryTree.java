package trees;

import java.util.*;


public class CompleteBinaryTree {

    /**
     * Complete this method to determine if a binary tree is a complete binary tree.
     * Do not modify the function signature.
     */
    public boolean isComplete(TreeNode root) {
        if(root==null)return true;
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        boolean isSeen=false;
        while(!q.isEmpty()){
            TreeNode rem=q.poll();
            if(rem==null){
                isSeen=true;
            }else{
                if(isSeen)return false;
                q.offer(rem.left);
                q.offer(rem.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CompleteBinaryTree validator = new CompleteBinaryTree();
        int passed = 0;
        int totalTests = 10;

        // --- TEST CASE 1: Empty Tree (Edge Case) ---
        TreeNode root1 = null;
        passed += evaluateTestCase(1, "Empty Tree", validator.isComplete(root1), true);

        // --- TEST CASE 2: Single Node (Edge Case) ---
        TreeNode root2 = new TreeNode(1);
        passed += evaluateTestCase(2, "Single Node Tree", validator.isComplete(root2), true);

        // --- TEST CASE 3: Perfect Binary Tree ---
        //       1
        //      / \
        //     2   3
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        passed += evaluateTestCase(3, "Perfect Binary Tree", validator.isComplete(root3), true);

        // --- TEST CASE 4: Valid Complete Tree (Last level pushed left) ---
        //       1
        //      / \
        //     2   3
        //    /
        //   4
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);
        root4.left.left = new TreeNode(4);
        passed += evaluateTestCase(4, "Valid Complete Tree (Left-aligned leaf)", validator.isComplete(root4), true);

        // --- TEST CASE 5: Invalid Tree (Gap in the middle of last level) ---
        //       1
        //      / \
        //     2   3
        //    /     \
        //   4       5   <-- Invalid because 3 has a right child but no left child
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.right = new TreeNode(3);
        root5.left.left = new TreeNode(4);
        root5.right.right = new TreeNode(5);
        passed += evaluateTestCase(5, "Invalid Tree (Gap in last level)", validator.isComplete(root5), false);

        // --- TEST CASE 6: Invalid Tree (Missing left subtree completely) ---
        //       1
        //        \
        //         2
        TreeNode root6 = new TreeNode(1);
        root6.right = new TreeNode(2);
        passed += evaluateTestCase(6, "Invalid Tree (Right child only)", validator.isComplete(root6), false);

        // --- TEST CASE 7: Invalid Tree (Deep gap at intermediate level) ---
        //         1
        //        / \
        //       2   3
        //      /   / \
        //     4   5   6   <-- Invalid because 2 lacks a right child, yet 3 has children
        TreeNode root7 = new TreeNode(1);
        root7.left = new TreeNode(2);
        root7.right = new TreeNode(3);
        root7.left.left = new TreeNode(4);
        root7.right.left = new TreeNode(5);
        root7.right.right = new TreeNode(6);
        passed += evaluateTestCase(7, "Invalid Tree (Gap at parent level)", validator.isComplete(root7), false);

        // --- TEST CASE 8: Valid Large Complete Tree ---
        //          1
        //        /   \
        //       2     3
        //      / \   /
        //     4   5 6
        TreeNode root8 = new TreeNode(1);
        root8.left = new TreeNode(2);
        root8.right = new TreeNode(3);
        root8.left.left = new TreeNode(4);
        root8.left.right = new TreeNode(5);
        root8.right.left = new TreeNode(6);
        passed += evaluateTestCase(8, "Valid Multi-level Complete Tree", validator.isComplete(root8), true);

        // --- TEST CASE 9: Invalid Skewed Tree (Linked list pattern) ---
        //     1
        //    /
        //   2
        //  /
        // 3  <-- Valid so far, but if it goes deeper without filling level 1 completely, it fails.
        // Let's add an invalid placement further down:
        //       1
        //      /
        //     2
        //    / \
        //   3   4
        //  /
        // 5 <-- Invalid because Node 1's right branch is empty, so level 1 is not fully filled before level 2/3.
        TreeNode root9 = new TreeNode(1);
        root9.left = new TreeNode(2);
        root9.left.left = new TreeNode(3);
        root9.left.right = new TreeNode(4);
        root9.left.left.left = new TreeNode(5);
        passed += evaluateTestCase(9, "Invalid Deeply Skewed Left Tree", validator.isComplete(root9), false);

        // --- TEST CASE 10: Invalid Tree (Leaf at wrong depth level) ---
        //         1
        //       /   \
        //      2     3
        //     /     / \
        //    4     5   6
        //   /
        //  7 <-- Level 3 is missing node 2's right child, making level 4 invalid
        TreeNode root10 = new TreeNode(1);
        root10.left = new TreeNode(2);
        root10.right = new TreeNode(3);
        root10.left.left = new TreeNode(4);
        root10.right.left = new TreeNode(5);
        root10.right.right = new TreeNode(6);
        root10.left.left.left = new TreeNode(7);
        passed += evaluateTestCase(10, "Invalid Tree (Leaf skipping a filled tier)", validator.isComplete(root10), false);

        System.out.println("\n=================================");
        System.out.println("FINAL SCORE: " + passed + " / " + totalTests + " PASSED");
        System.out.println("=================================");
    }

    private static int evaluateTestCase(int id, String description, boolean actual, boolean expected) {
        System.out.print("Test #" + id + " [" + description + "]: ");
        if (actual == expected) {
            System.out.println("PASSED");
            return 1;
        } else {
            System.out.println("FAILED (Expected: " + expected + ", Actual: " + actual + ")");
            return 0;
        }
    }
}