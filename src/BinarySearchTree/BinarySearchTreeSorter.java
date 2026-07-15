package BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class BinarySearchTreeSorter {

    /**
     * Converts a Binary Search Tree into a sorted array in ascending order.
     *
     * @param root The root node of the Binary Search Tree.
     * @return An array of integers sorted in ascending order.
     */
    public int[] toAscendingArray(TreeNode root) {
        List<Integer>ans=new ArrayList<>();
        if(root==null)return new int[0];
        Stack<TreeNode>st=new Stack<>();
        TreeNode curr=root;
        while(curr!=null){
            st.push(curr);
            curr=curr.left;
        }
        while (!st.isEmpty()){
            TreeNode smallest=st.pop();
            TreeNode right=smallest.right;
            ans.add(smallest.val);
            while(right!=null){
                st.push(right);
                right=right.left;
            }
        }
        int[]res=new int[ans.size()];
        for(int i=0;i<ans.size();i++)res[i]=ans.get(i);
        return res;
    }

    /**
     * Converts a Binary Search Tree into a sorted array in descending order.
     *
     * @param root The root node of the Binary Search Tree.
     * @return An array of integers sorted in descending order.
     */
    public int[] toDescendingArray(TreeNode root) {
        if(root==null)return new int[0];
        List<Integer>ans=new ArrayList<>();
        Stack<TreeNode>st=new Stack<>();
        TreeNode curr=root;
        while(curr!=null){
            st.push(curr);
            curr=curr.right;
        }
        while(!st.isEmpty()){
            TreeNode largest=st.pop();
            ans.add(largest.val);
            TreeNode left=largest.left;
            while(left!=null){
                st.push(left);
                left=left.right;
            }
        }
        int[]res=new int[ans.size()];
        for(int i=0;i<ans.size();i++)res[i]=ans.get(i);
        return res;
    }

    // =========================================================================
    // TESTING HARNESS (Do not modify the code below)
    // =========================================================================

    private static class TestCase {
        String description;
        TreeNode root;
        int[] expectedAsc;
        int[] expectedDesc;

        TestCase(String description, TreeNode root, int[] expectedAsc, int[] expectedDesc) {
            this.description = description;
            this.root = root;
            this.expectedAsc = expectedAsc;
            this.expectedDesc = expectedDesc;
        }
    }

    public static void main(String[] args) {
        BinarySearchTreeSorter sorter = new BinarySearchTreeSorter();
        List<TestCase> testCases = new ArrayList<>();

        // Case 1: Empty Tree (Edge Case)
        testCases.add(new TestCase("Empty Tree", null, new int[]{}, new int[]{}));

        // Case 2: Single Node Tree (Edge Case)
        testCases.add(new TestCase("Single Node Tree", new TreeNode(5), new int[]{5}, new int[]{5}));

        // Case 3: Perfectly Balanced BST
        TreeNode root3 = new TreeNode(4);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(6);
        root3.left.left = new TreeNode(1);
        root3.left.right = new TreeNode(3);
        root3.right.left = new TreeNode(5);
        root3.right.right = new TreeNode(7);
        testCases.add(new TestCase("Perfectly Balanced BST", root3,
                new int[]{1, 2, 3, 4, 5, 6, 7},
                new int[]{7, 6, 5, 4, 3, 2, 1}));

        // Case 4: Left-Skewed Tree (Degenerate / Linked List Style)
        TreeNode root4 = new TreeNode(5);
        root4.left = new TreeNode(4);
        root4.left.left = new TreeNode(3);
        root4.left.left.left = new TreeNode(2);
        testCases.add(new TestCase("Left-Skewed Tree", root4,
                new int[]{2, 3, 4, 5},
                new int[]{5, 4, 3, 2}));

        // Case 5: Right-Skewed Tree (Degenerate / Linked List Style)
        TreeNode root5 = new TreeNode(10);
        root5.right = new TreeNode(20);
        root5.right.right = new TreeNode(30);
        root5.right.right.right = new TreeNode(40);
        testCases.add(new TestCase("Right-Skewed Tree", root5,
                new int[]{10, 20, 30, 40},
                new int[]{40, 30, 20, 10}));

        // Case 6: Tree containing Integer.MIN_VALUE and Integer.MAX_VALUE (Boundaries)
        TreeNode root6 = new TreeNode(0);
        root6.left = new TreeNode(Integer.MIN_VALUE);
        root6.right = new TreeNode(Integer.MAX_VALUE);
        testCases.add(new TestCase("Tree with Boundary Bounds", root6,
                new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE},
                new int[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE}));

        // Case 7: BST with duplicates handled on the left
        TreeNode root7 = new TreeNode(10);
        root7.left = new TreeNode(10);
        root7.left.left = new TreeNode(5);
        root7.right = new TreeNode(15);
        testCases.add(new TestCase("BST with Duplicate Values", root7,
                new int[]{5, 10, 10, 15},
                new int[]{15, 10, 10, 5}));

        // Case 8: Zig-Zag / Unbalanced Structure
        TreeNode root8 = new TreeNode(15);
        root8.left = new TreeNode(5);
        root8.left.right = new TreeNode(12);
        root8.left.right.left = new TreeNode(10);
        root8.right = new TreeNode(20);
        testCases.add(new TestCase("Zig-Zag Struct Tree", root8,
                new int[]{5, 10, 12, 15, 20},
                new int[]{20, 15, 12, 10, 5}));

        // Case 9: Large Negative Values Included
        TreeNode root9 = new TreeNode(-50);
        root9.left = new TreeNode(-100);
        root9.right = new TreeNode(-10);
        root9.right.left = new TreeNode(-25);
        testCases.add(new TestCase("All Negative Values", root9,
                new int []{-100, -50, -25, -10},
        new int[]{-10, -25, -50, -100}));

        // Case 10: Deep Unbalanced Tree
        TreeNode root10 = new TreeNode(1);
        root10.right = new TreeNode(2);
        root10.right.right = new TreeNode(5);
        root10.right.right.left = new TreeNode(3);
        root10.right.right.left.right = new TreeNode(4);
        testCases.add(new TestCase("Deeply Unbalanced Complex Structure", root10,
                new int[]{1, 2, 3, 4, 5},
                new int[]{5, 4, 3, 2, 1}));

        int passedCount = 0;
        System.out.println("Executing Test Suite...\n");

        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            System.out.println("----------------------------------------");
            System.out.println("Test Case " + (i + 1) + ": " + tc.description);

            int[] actualAsc = sorter.toAscendingArray(tc.root);
            int[] actualDesc = sorter.toDescendingArray(tc.root);

            boolean ascPassed = Arrays.equals(actualAsc, tc.expectedAsc);
            boolean descPassed = Arrays.equals(actualDesc, tc.expectedDesc);

            if (ascPassed && descPassed) {
                System.out.println("Result: PASSED");
                passedCount++;
            } else {
                System.out.println("Result: FAILED");
                if (!ascPassed) {
                    System.out.println("  [Ascending Failure]");
                    System.out.println("    Expected: " + Arrays.toString(tc.expectedAsc));
                    System.out.println("    Actual:   " + Arrays.toString(actualAsc));
                }
                if (!descPassed) {
                    System.out.println("  [Descending Failure]");
                    System.out.println("    Expected: " + Arrays.toString(tc.expectedDesc));
                    System.out.println("    Actual:   " + Arrays.toString(actualDesc));
                }
            }
        }

        System.out.println("\n========================================");
        System.out.println("SUMMARY REPORT");
        System.out.println("Total Passed: " + passedCount + " / " + testCases.size());
        System.out.println("Total Failed: " + (testCases.size() - passedCount));
        System.out.println("========================================");
    }
}