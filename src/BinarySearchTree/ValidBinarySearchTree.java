package BinarySearchTree;

import java.util.*;



public class ValidBinarySearchTree {

    /**
     * Determines if the given binary tree is a valid Binary Search Tree (BST).
     * Complete the implementation of this method.
     */
    public boolean isValidBST(TreeNode root) {
        boolean[]ans=new boolean[]{true};
        helper(root,new TreeNode[]{null},ans);
        return ans[0];
    }

    private void helper(TreeNode root,TreeNode[] prev,boolean[]ans){
        if(root==null)return;
        if(!ans[0])return ;
        helper(root.left,prev,ans);
        if(prev[0]!=null){
            if(prev[0].val>=root.val) {
                ans[0] = false;
                return;
            }
        }
        prev[0]=root;
        helper(root.right,prev,ans);
    }


    public static void main(String[] args) {
        ValidBinarySearchTree validator = new ValidBinarySearchTree();
        int passed = 0;
        int totalTests = 12;

        System.out.println("Running Validate Binary Search Tree Tests...\n");

        // --- TEST CASE 1: Standard Valid BST ---
        //      2
        //     / \
        //    1   3
        TreeNode tc1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        passed += runTest(1, validator.isValidBST(tc1), true);

        // --- TEST CASE 2: Invalid Right Subtree (Out of bounds for ancestor) ---
        //      5
        //     / \
        //    1   4
        //       / \
        //      3   6
        TreeNode tc2 = new TreeNode(5);
        tc2.left = new TreeNode(1);
        tc2.right = new TreeNode(4, new TreeNode(3), new TreeNode(6));
        passed += runTest(2, validator.isValidBST(tc2), false);

        // --- TEST CASE 3: Single Node (Edge Case) ---
        TreeNode tc3 = new TreeNode(0);
        passed += runTest(3, validator.isValidBST(tc3), true);

        // --- TEST CASE 4: Invalid Left Subtree (Value equal to root) ---
        //      10
        //     /
        //    10
        TreeNode tc4 = new TreeNode(10, new TreeNode(10), null);
        passed += runTest(4, validator.isValidBST(tc4), false);

        // --- TEST CASE 5: Deep Invalid Node (Hidden constraint trap) ---
        //       10
        //      /  \
        //     5    15
        //         /  \
        //        6    20   <-- 6 is valid for 15, but violates > 10 for the root
        TreeNode tc5 = new TreeNode(10);
        tc5.left = new TreeNode(5);
        tc5.right = new TreeNode(15, new TreeNode(6), new TreeNode(20));
        passed += runTest(5, validator.isValidBST(tc5), false);

        // --- TEST CASE 6: Integer.MAX_VALUE and Integer.MIN_VALUE Bounds ---
        //   Integer.MAX_VALUE
        //        /
        //   Integer.MIN_VALUE
        TreeNode tc6 = new TreeNode(Integer.MAX_VALUE, new TreeNode(Integer.MIN_VALUE), null);
        passed += runTest(6, validator.isValidBST(tc6), true);

        // --- TEST CASE 7: Overflows if using strict Integer bounds tracking ---
        //   Integer.MIN_VALUE
        //        \
        //   Integer.MAX_VALUE
        TreeNode tc7 = new TreeNode(Integer.MIN_VALUE, null, new TreeNode(Integer.MAX_VALUE));
        passed += runTest(7, validator.isValidBST(tc7), true);

        // --- TEST CASE 8: Invalid Right Child equal to Integer.MAX_VALUE boundary ---
        //        Integer.MAX_VALUE
        //             \
        //        Integer.MAX_VALUE
        TreeNode tc8 = new TreeNode(Integer.MAX_VALUE, null, new TreeNode(Integer.MAX_VALUE));
        passed += runTest(8, validator.isValidBST(tc8), false);

        // --- TEST CASE 9: Valid Skewed Left Tree ---
        //       4
        //      /
        //     3
        //    /
        //   2
        TreeNode tc9 = new TreeNode(4, new TreeNode(3, new TreeNode(2), null), null);
        passed += runTest(9, validator.isValidBST(tc9), true);

        // --- TEST CASE 10: Duplicate values deep in tree ---
        //       20
        //      /  \
        //    10    30
        //         /
        //        20   <-- Duplicate of root, strictly invalid
        TreeNode tc10 = new TreeNode(20);
        tc10.left = new TreeNode(10);
        tc10.right = new TreeNode(30, new TreeNode(20), null);
        passed += runTest(10, validator.isValidBST(tc10), false);

        // --- TEST CASE 11: Valid complex balance ---
        //        100
        //       /   \
        //     50     150
        //    /  \    /  \
        //   25  75  125 175
        TreeNode tc11 = new TreeNode(100);
        tc11.left = new TreeNode(50, new TreeNode(25), new TreeNode(75));
        tc11.right = new TreeNode(150, new TreeNode(125), new TreeNode(175));
        passed += runTest(11, validator.isValidBST(tc11), true);

        // --- TEST CASE 12: Precision Constraint Check ---
        //        3
        //       / \
        //      2   5
        //     / \
        //    1   4   <-- 4 is greater than its parent 2, but must be less than 3
        TreeNode tc12 = new TreeNode(3);
        tc12.left = new TreeNode(2, new TreeNode(1), new TreeNode(4));
        tc12.right = new TreeNode(5);
        passed += runTest(12, validator.isValidBST(tc12), false);

        System.out.println("\n-------------------------------------------");
        System.out.printf("Final Results: %d/%d Tests Passed.\n", passed, totalTests);
        System.out.println("-------------------------------------------");
    }

    private static int runTest(int testNum, boolean actual, boolean expected) {
        boolean isMatch = (actual == expected);
        System.out.printf("Test Case %2d: ", testNum);
        if (isMatch) {
            System.out.printf("[PASSED] | Expected: %-5b | Actual: %-5b\n", expected, actual);
            return 1;
        } else {
            System.out.printf("[FAILED] | Expected: %-5b | Actual: %-5b\n", expected, actual);
            return 0;
        }
    }
}