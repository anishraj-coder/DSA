package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class TargetSumBinarySearchTree {

    /**
     * Determines if there exist two distinct elements in the BST
     * such that their sum equals the target k.
     *
     * @param root The root node of the Binary Search Tree.
     * @param k    The target sum integer.
     * @return true if two elements sum up to k; false otherwise.
     */
    public boolean findTarget(TreeNode root, int k) {
        Stack<TreeNode>asc=new Stack<>();
        Stack<TreeNode>desc=new Stack<>();
        TreeNode curr=root;
        while(curr!=null){
            asc.push(curr);
            curr=curr.left;
        }
        curr=root;
        while(curr!=null){
            desc.push(curr);
            curr=curr.right;
        }
        TreeNode i=getSmall(asc),j=getBig(desc);
        while(i!=null&&j!=null&&i!=j&&i.val<=j.val){
            int sum=i.val+j.val;
            if(sum==k)return true;
            if(sum>k)j=getBig(desc);
            else i=getSmall(asc);
        }
        return false;
    }

    private TreeNode getSmall(Stack<TreeNode>asc){
        if(asc.isEmpty())return null;
        TreeNode small=asc.pop(),right=small.right;
        while (right!=null){
            asc.push(right);
            right=right.left;
        }
        return small;
    }

    private TreeNode getBig(Stack<TreeNode>desc){
        if(desc.isEmpty())return null;
        TreeNode big=desc.pop(),left=big.left;
        while(left!=null){
            desc.push(left);
            left=left.right;
        }
        return big;
    }

    // =========================================================================
    // TESTING HARNESS (Do not modify the code below)
    // =========================================================================

    private static class TestCase {
        String description;
        TreeNode root;
        int target;
        boolean expected;

        TestCase(String description, TreeNode root, int target, boolean expected) {
            this.description = description;
            this.root = root;
            this.target = target;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        TargetSumBinarySearchTree bstSum = new TargetSumBinarySearchTree();
        List<TestCase> testCases = new ArrayList<>();

        // Case 1: Single Node Tree (Edge Case - Cannot reuse same node)
        testCases.add(new TestCase("Single Node Tree", new TreeNode(5), 10, false));

        // Case 2: Standard Valid Pair (Example 1)
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);
        testCases.add(new TestCase("Standard Tree - Valid Pair (4 + 5 = 9)", root2, 9, true));

        // Case 3: Standard Invalid Target (Example 2)
        testCases.add(new TestCase("Standard Tree - Invalid Target", root2, 28, false));

        // Case 4: Target equals exactly twice a node's value (Edge Case - Cannot double-dip)
        TreeNode root4 = new TreeNode(5);
        root4.left = new TreeNode(3);
        root4.right = new TreeNode(8);
        testCases.add(new TestCase("Target is twice a single node's value (Requires 2 distinct nodes)", root4, 6, false));

        // Case 5: Negative Values Pair
        TreeNode root5 = new TreeNode(-10);
        root5.left = new TreeNode(-20);
        root5.right = new TreeNode(0);
        root5.left.right = new TreeNode(-15);
        testCases.add(new TestCase("Negative Elements Sum (-15 + 0 = -15)", root5, -15, true));

        // Case 6: Mixed Positive and Negative Elements
        TreeNode root6 = new TreeNode(10);
        root6.left = new TreeNode(-5);
        root6.right = new TreeNode(20);
        testCases.add(new TestCase("Mixed Positive/Negative Pair (-5 + 20 = 15)", root6, 15, true));

        // Case 7: Degenerate Left-Skewed Tree (Linked List check)
        TreeNode root7 = new TreeNode(20);
        root7.left = new TreeNode(15);
        root7.left.left = new TreeNode(10);
        root7.left.left.left = new TreeNode(5);
        testCases.add(new TestCase("Left-Skewed Tree (5 + 15 = 20)", root7, 20, true));

        // Case 8: Degenerate Right-Skewed Tree (Linked List check)
        TreeNode root8 = new TreeNode(5);
        root8.right = new TreeNode(10);
        root8.right.right = new TreeNode(15);
        root8.right.right.right = new TreeNode(20);
        testCases.add(new TestCase("Right-Skewed Tree (No combination matches 40)", root8, 40, false));

        // Case 9: BST with Explicit Duplicate Values
        TreeNode root9 = new TreeNode(10);
        root9.left = new TreeNode(10); // Handled duplicate
        root9.left.left = new TreeNode(5);
        root9.right = new TreeNode(15);
        testCases.add(new TestCase("Tree with Duplicate Nodes (10 + 10 = 20)", root9, 20, true));

        // Case 10: Sum requires Root and Deepest Leaf
        TreeNode root10 = new TreeNode(50);
        root10.left = new TreeNode(25);
        root10.right = new TreeNode(75);
        root10.left.left = new TreeNode(12);
        root10.left.left.left = new TreeNode(6);
        testCases.add(new TestCase("Sum Root and Deepest Leaf (50 + 6 = 56)", root10, 56, true));

        int passedCount = 0;
        System.out.println("Executing Test Suite...\n");

        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            System.out.println("----------------------------------------");
            System.out.println("Test Case " + (i + 1) + ": " + tc.description);
            System.out.println("Target (k): " + tc.target);

            boolean actual = bstSum.findTarget(tc.root, tc.target);

            if (actual == tc.expected) {
                System.out.println("Result: PASSED");
                passedCount++;
            } else {
                System.out.println("Result: FAILED");
                System.out.println("  Expected: " + tc.expected);
                System.out.println("  Actual:   " + actual);
            }
        }

        System.out.println("\n========================================");
        System.out.println("SUMMARY REPORT");
        System.out.println("Total Passed: " + passedCount + " / " + testCases.size());
        System.out.println("Total Failed: " + (testCases.size() - passedCount));
        System.out.println("========================================");
    }
}