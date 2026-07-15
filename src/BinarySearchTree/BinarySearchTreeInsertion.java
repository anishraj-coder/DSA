package BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTreeInsertion {

    /**
     * Inserts a value into the Binary Search Tree.
     * Guaranteed that the new value does not already exist in the BST.
     *
     * @param root The root node of the BST
     * @param val  The value to insert
     * @return     The root node of the modified BST
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null)return new TreeNode(val);
        TreeNode curr=root;
        while(true){
            if(curr.val>val){
                if(curr.left==null){
                    curr.left=new TreeNode(val);
                    return root;
                }else curr=curr.left;
            }else {
                if(curr.right==null){
                    curr.right=new TreeNode(val);
                    return root;
                }else curr=curr.right;
            }
        }
    }

    private void helper(TreeNode root,int val){
        if(root==null)return;
        if(root.left==null&&root.right==null){
            TreeNode newNode=new TreeNode(val);
            if(val>root.val)root.right=newNode;
            else root.left=newNode;
            return;
        }
        if(val>root.val)helper(root.right,val);
        else helper(root.left,val);
    }

    // --- TEST UTILITIES ---

    // Verifies if the tree is a valid BST and collects elements in sorted order
    private static boolean validateAndCollect(TreeNode root, long min, long max, List<Integer> elements) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;

        // Inorder traversal captures elements sequentially
        boolean leftValid = validateAndCollect(root.left, min, root.val, elements);
        elements.add(root.val);
        boolean rightValid = validateAndCollect(root.right, root.val, max, elements);

        return leftValid && rightValid;
    }

    // Comprehensive test evaluator
    private static boolean verifyInsertion(TreeNode originalRoot, TreeNode modifiedRoot, int insertedVal, List<Integer> expectedElements) {
        if (originalRoot == null && modifiedRoot == null) return false;
        if (modifiedRoot == null) return false;

        List<Integer> actualElements = new ArrayList<>();
        boolean isBST = validateAndCollect(modifiedRoot, Long.MIN_VALUE, Long.MAX_VALUE, actualElements);

        if (!isBST) {
            System.out.println("   Result Validation: FAILED (The resulting tree is not a valid BST)");
            return false;
        }

        // Check if the exact expected set of sorted elements matches
        if (!actualElements.equals(expectedElements)) {
            System.out.println("   Result Validation: FAILED");
            System.out.println("   Expected Sorted Elements: " + expectedElements);
            System.out.println("   Actual Sorted Elements:   " + actualElements);
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        BinarySearchTreeInsertion bstInsertion = new BinarySearchTreeInsertion();
        int passed = 0;
        int failed = 0;

        // --- TEST CASE 1: Standard case - Value goes into an internal deep leaf position ---
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        int val1 = 5;
        List<Integer> expectedList1 = Arrays.asList(1, 2, 3, 4, 5, 7);

        // --- TEST CASE 2: Standard case - Value goes deep right as a new child ---
        TreeNode root2 = new TreeNode(40);
        root2.left = new TreeNode(20);
        root2.right = new TreeNode(60);
        root2.left.left = new TreeNode(10);
        root2.left.right = new TreeNode(30);
        root2.right.left = new TreeNode(50);
        root2.right.right = new TreeNode(70);
        int val2 = 25;
        List<Integer> expectedList2 = Arrays.asList(10, 20, 25, 30, 40, 50, 60, 70);

        // --- TEST CASE 3: Edge case - Empty Tree ---
        TreeNode root3 = null;
        int val3 = 5;
        List<Integer> expectedList3 = Arrays.asList(5);

        // --- TEST CASE 4: Edge case - Single element tree, inserting smaller value ---
        TreeNode root4 = new TreeNode(10);
        int val4 = 4;
        List<Integer> expectedList4 = Arrays.asList(4, 10);

        // --- TEST CASE 5: Edge case - Single element tree, inserting larger value ---
        TreeNode root5 = new TreeNode(10);
        int val5 = 22;
        List<Integer> expectedList5 = Arrays.asList(10, 22);

        // --- TEST CASE 6: Extreme values - Handling negative bounds ---
        TreeNode root6 = new TreeNode(-10);
        root6.left = new TreeNode(-20);
        root6.right = new TreeNode(0);
        int val6 = -15;
        List<Integer> expectedList6 = Arrays.asList(-20, -15, -10, 0);

        // --- TEST CASE 7: Complex case - Deep Left-Skewed Tree (inserting absolute lowest item) ---
        TreeNode root7 = new TreeNode(100);
        root7.left = new TreeNode(80);
        root7.left.left = new TreeNode(60);
        root7.left.left.left = new TreeNode(40);
        int val7 = 20;
        List<Integer> expectedList7 = Arrays.asList(20, 40, 60, 80, 100);

        // --- TEST CASE 8: Complex case - Deep Left-Skewed Tree (inserting intermediate item breaking down the line) ---
        TreeNode root8 = new TreeNode(100);
        root8.left = new TreeNode(80);
        root8.left.left = new TreeNode(60);
        root8.left.left.left = new TreeNode(40);
        int val8 = 70;
        List<Integer> expectedList8 = Arrays.asList(40, 60, 70, 80, 100);

        // --- TEST CASE 9: Complex case - Deep Right-Skewed Tree (inserting absolute highest item) ---
        TreeNode root9 = new TreeNode(10);
        root9.right = new TreeNode(20);
        root9.right.right = new TreeNode(30);
        root9.right.right.right = new TreeNode(40);
        int val9 = 50;
        List<Integer> expectedList9 = Arrays.asList(10, 20, 30, 40, 50);

        // --- TEST CASE 10: Intricate Case - Inserting next to the root parameter bound ---
        TreeNode root10 = new TreeNode(50);
        root10.left = new TreeNode(10);
        root10.right = new TreeNode(90);
        root10.left.right = new TreeNode(30);
        root10.left.right.left = new TreeNode(20);
        root10.left.right.right = new TreeNode(40);
        int val10 = 45;
        List<Integer> expectedList10 = Arrays.asList(10, 20, 30, 40, 45, 50, 90);

        // Test Registry
        TreeNode[] testRoots = {root1, root2, root3, root4, root5, root6, root7, root8, root9, root10};
        int[] testVals = {val1, val2, val3, val4, val5, val6, val7, val8, val9, val10};
        List<List<Integer>> expectedLists = Arrays.asList(
                expectedList1, expectedList2, expectedList3, expectedList4, expectedList5,
                expectedList6, expectedList7, expectedList8, expectedList9, expectedList10
        );

        System.out.println("Executing Insertion Test Suite...\n");
        for (int i = 0; i < testRoots.length; i++) {
            int currentCase = i + 1;

            // Execute user implementation
            TreeNode resultRoot = bstInsertion.insertIntoBST(testRoots[i], testVals[i]);

            boolean isCorrect = verifyInsertion(testRoots[i], resultRoot, testVals[i], expectedLists.get(i));

            if (isCorrect) {
                System.out.println("Test Case " + currentCase + ": PASSED");
                passed++;
            } else {
                System.out.println("Test Case " + currentCase + ": FAILED");
                System.out.println("   Attempted to insert value: " + testVals[i]);
                failed++;
            }
        }

        System.out.println("\n-----------------------------------");
        System.out.println("Execution Summary:");
        System.out.println("Total Passed: " + passed);
        System.out.println("Total Failed: " + failed);
        System.out.println("-----------------------------------");
    }
}