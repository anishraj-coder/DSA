package BinarySearchTree;

import java.util.*;


public class BinarySearchTreeLowestCommonAncestor {

    /**
     * Finds the lowest common ancestor (LCA) node of two given nodes p and q in the BST.
     *
     * Implement your solution inside this method.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode temp=root;
        TreeNode max=(p.val>q.val)?p:q;
        TreeNode min=(max.equals(p))?q:p;
        while(temp!=null){
            if(temp.val<=max.val&&temp.val>=min.val){
                break;
            }else if(temp.val>=max.val)temp=temp.left;
            else temp=temp.right;
        }
        return temp;
    }

    // ==========================================
    // TESTING FRAMEWORK & TEST CASES
    // ==========================================

    public static void main(String[] args) {
        BinarySearchTreeLowestCommonAncestor solver = new BinarySearchTreeLowestCommonAncestor();
        int totalTests = 0;
        int passedTests = 0;

        // --- Helper for creating a BST node quickly ---
        java.util.function.BiFunction<TreeNode, Integer, TreeNode> findNode = new java.util.function.BiFunction<>() {
            @Override
            public TreeNode apply(TreeNode root, Integer val) {
                if (root == null || root.val == val) return root;
                if (val < root.val) return apply(root.left, val);
                return apply(root.right, val);
            }
        };

        // ----------------------------------------------------
        // TEST CASE 1: Standard Balanced BST (Example 1)
        // ----------------------------------------------------
        totalTests++;
        TreeNode root1 = buildTree(new Integer[]{6, 2, 8, 0, 4, 7, 9, null, null, 3, 5});
        TreeNode p1 = findNode.apply(root1, 2);
        TreeNode q1 = findNode.apply(root1, 8);
        int expected1 = 6;
        TreeNode actualNode1 = solver.lowestCommonAncestor(root1, p1, q1);
        int actual1 = actualNode1 != null ? actualNode1.val : -1;
        passedTests += reportResult(1, "Standard Balanced BST", expected1, actual1);

        // ----------------------------------------------------
        // TEST CASE 2: One node is the ancestor of another (Example 2)
        // ----------------------------------------------------
        totalTests++;
        TreeNode root2 = buildTree(new Integer[]{6, 2, 8, 0, 4, 7, 9, null, null, 3, 5});
        TreeNode p2 = findNode.apply(root2, 2);
        TreeNode q2 = findNode.apply(root2, 4);
        int expected2 = 2;
        TreeNode actualNode2 = solver.lowestCommonAncestor(root2, p2, q2);
        int actual2 = actualNode2 != null ? actualNode2.val : -1;
        passedTests += reportResult(2, "p is ancestor of q", expected2, actual2);

        // ----------------------------------------------------
        // TEST CASE 3: Minimal Tree (Example 3)
        // ----------------------------------------------------
        totalTests++;
        TreeNode root3 = buildTree(new Integer[]{2, 1});
        TreeNode p3 = findNode.apply(root3, 2);
        TreeNode q3 = findNode.apply(root3, 1);
        int expected3 = 2;
        TreeNode actualNode3 = solver.lowestCommonAncestor(root3, p3, q3);
        int actual3 = actualNode3 != null ? actualNode3.val : -1;
        passedTests += reportResult(3, "Minimal Tree (Root and Left Child)", expected3, actual3);

        // ----------------------------------------------------
        // TEST CASE 4: Deep Right-Skewed Tree (Linear Structure)
        // ----------------------------------------------------
        totalTests++;
        TreeNode root4 = buildTree(new Integer[]{1, null, 2, null, 3, null, 4, null, 5});
        TreeNode p4 = findNode.apply(root4, 3);
        TreeNode q4 = findNode.apply(root4, 5);
        int expected4 = 3;
        TreeNode actualNode4 = solver.lowestCommonAncestor(root4, p4, q4);
        int actual4 = actualNode4 != null ? actualNode4.val : -1;
        passedTests += reportResult(4, "Right-Skewed Tree", expected4, actual4);

        // ----------------------------------------------------
        // TEST CASE 5: Deep Left-Skewed Tree (Linear Structure)
        // ----------------------------------------------------
        totalTests++;
        TreeNode root5 = buildTree(new Integer[]{5, 4, null, 3, null, 2, null, 1});
        TreeNode p5 = findNode.apply(root5, 1);
        TreeNode q5 = findNode.apply(root5, 3);
        int expected5 = 3;
        TreeNode actualNode5 = solver.lowestCommonAncestor(root5, p5, q5);
        int actual5 = actualNode5 != null ? actualNode5.val : -1;
        passedTests += reportResult(5, "Left-Skewed Tree", expected5, actual5);

        // ----------------------------------------------------
        // TEST CASE 6: Negative and Zero Values
        // ----------------------------------------------------
        totalTests++;
        TreeNode root6 = buildTree(new Integer[]{0, -5, 10, -10, -2, 5, 15});
        TreeNode p6 = findNode.apply(root6, -10);
        TreeNode q6 = findNode.apply(root6, -2);
        int expected6 = -5;
        TreeNode actualNode6 = solver.lowestCommonAncestor(root6, p6, q6);
        int actual6 = actualNode6 != null ? actualNode6.val : -1;
        passedTests += reportResult(6, "Negative and Zero Values", expected6, actual6);

        // ----------------------------------------------------
        // TEST CASE 7: Subtree split across different deep branches
        // ----------------------------------------------------
        totalTests++;
        TreeNode root7 = buildTree(new Integer[]{20, 10, 30, 5, 15, 25, 35, 3, 7, 12, 17});
        TreeNode p7 = findNode.apply(root7, 7);
        TreeNode q7 = findNode.apply(root7, 17);
        int expected7 = 10;
        TreeNode actualNode7 = solver.lowestCommonAncestor(root7, p7, q7);
        int actual7 = actualNode7 != null ? actualNode7.val : -1;
        passedTests += reportResult(7, "Deep Subtree Branch Split", expected7, actual7);

        // ----------------------------------------------------
        // TEST CASE 8: Large Node Values (Checking bounds/overflow vulnerability)
        // ----------------------------------------------------
        totalTests++;
        TreeNode root8 = buildTree(new Integer[]{0, -1000000000, 1000000000});
        TreeNode p8 = findNode.apply(root8, -1000000000);
        TreeNode q8 = findNode.apply(root8, 1000000000);
        int expected8 = 0;
        TreeNode actualNode8 = solver.lowestCommonAncestor(root8, p8, q8);
        int actual8 = actualNode8 != null ? actualNode8.val : -1;
        passedTests += reportResult(8, "Integer Limit Bounds", expected8, actual8);

        // ----------------------------------------------------
        // TEST CASE 9: Swapped p and q values (testing value asymmetry bugs)
        // ----------------------------------------------------
        totalTests++;
        TreeNode root9 = buildTree(new Integer[]{6, 2, 8, 0, 4, 7, 9, null, null, 3, 5});
        TreeNode p9 = findNode.apply(root9, 5); // Right child of 4
        TreeNode q9 = findNode.apply(root9, 3); // Left child of 4
        int expected9 = 4;
        TreeNode actualNode9 = solver.lowestCommonAncestor(root9, p9, q9);
        int actual9 = actualNode9 != null ? actualNode9.val : -1;
        passedTests += reportResult(9, "Swapped Inputs (q < p layout)", expected9, actual9);

        // ----------------------------------------------------
        // TEST CASE 10: Root is one of the target nodes
        // ----------------------------------------------------
        totalTests++;
        TreeNode root10 = buildTree(new Integer[]{10, 5, 15, 3, 7});
        TreeNode p10 = findNode.apply(root10, 10);
        TreeNode q10 = findNode.apply(root10, 7);
        int expected10 = 10;
        TreeNode actualNode10 = solver.lowestCommonAncestor(root10, p10, q10);
        int actual10 = actualNode10 != null ? actualNode10.val : -1;
        passedTests += reportResult(10, "Root is the target node p", expected10, actual10);

        // ----------------------------------------------------
        // TEST CASE 11: Nodes are direct siblings at the absolute bottom
        // ----------------------------------------------------
        totalTests++;
        TreeNode root11 = buildTree(new Integer[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7});
        TreeNode p11 = findNode.apply(root11, 5);
        TreeNode q11 = findNode.apply(root11, 7);
        int expected11 = 6;
        TreeNode actualNode11 = solver.lowestCommonAncestor(root11, p11, q11);
        int actual11 = actualNode11 != null ? actualNode11.val : -1;
        passedTests += reportResult(11, "Deepest Siblings", expected11, actual11);

        // --- Final Summary ---
        System.out.println("\n----------------------------------------------------");
        System.out.println("Execution Summary:");
        System.out.println("Total Tests Run: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));
        System.out.println("----------------------------------------------------");
    }

    // --- Helper utility to construct a binary tree from LeetCode array representation ---
    private static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode curr = queue.poll();
            if (arr[i] != null) {
                curr.left = new TreeNode(arr[i]);
                queue.add(curr.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                curr.right = new TreeNode(arr[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }

    // --- Helper utility to print clean execution reports ---
    private static int reportResult(int id, String description, int expected, int actual) {
        boolean pass = (expected == actual);
        System.out.printf("Test #%02d [%s]: ", id, description);
        if (pass) {
            System.out.println("PASSED");
            return 1;
        } else {
            System.out.println("FAILED");
            System.out.printf("   -> Expected Output: %d\n", expected);
            System.out.printf("   -> Actual Output:   %d\n", actual);
            return 0;
        }
    }
}