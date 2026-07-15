package BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTreeDeletion {

    /**
     * Deletes the node with the given key from the Binary Search Tree.
     *
     * @param root The root node of the BST
     * @param key  The value to be deleted from the tree
     * @return     The root node reference of the modified BST
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null)return root;

        if(root.val==key)return helper(root);

        TreeNode curr=root;
        while(curr!=null){
            if(curr.val>key){
                if(curr.left!=null&&curr.left.val==key){
                    curr.left=helper(curr.left);
                    break;
                }else curr=curr.left;
            }else{
                if(curr.right!=null&&curr.right.val==key){
                    curr.right=helper(curr.right);
                    break;
                }else curr=curr.right;
            }
        }

        return root;
    }

    private TreeNode helper(TreeNode root){
        if(root.left==null){
            return root.right;
        }
        if(root.right==null)return root.left;
        TreeNode tail=root.left;
        while(tail.right!=null)tail=tail.right;
        tail.right=root.right;
        return root.left;
    }

    // --- TEST UTILITIES ---

    // Verifies if the tree is a valid BST and collects elements in sorted order
    private static boolean validateAndCollect(TreeNode root, long min, long max, List<Integer> elements) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;

        boolean leftValid = validateAndCollect(root.left, min, root.val, elements);
        elements.add(root.val);
        boolean rightValid = validateAndCollect(root.right, root.val, max, elements);

        return leftValid && rightValid;
    }

    // Comprehensive test evaluator
    private static boolean verifyDeletion(TreeNode modifiedRoot, List<Integer> expectedElements) {
        if (modifiedRoot == null && expectedElements.isEmpty()) return true;
        if (modifiedRoot == null || expectedElements.isEmpty()) return false;

        List<Integer> actualElements = new ArrayList<>();
        boolean isBST = validateAndCollect(modifiedRoot, Long.MIN_VALUE, Long.MAX_VALUE, actualElements);

        if (!isBST) {
            System.out.println("   Result Validation: FAILED (The resulting tree is not a valid BST)");
            return false;
        }

        if (!actualElements.equals(expectedElements)) {
            System.out.println("   Result Validation: FAILED");
            System.out.println("   Expected Sorted Elements: " + expectedElements);
            System.out.println("   Actual Sorted Elements:   " + actualElements);
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        BinarySearchTreeDeletion bstDeletion = new BinarySearchTreeDeletion();
        int passed = 0;
        int failed = 0;

        // --- TEST CASE 1: Standard case - Deleting a node with two children ---
        //         5
        //       /   \
        //      3     6
        //     / \     \
        //    2   4     7
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(7);
        int key1 = 3;
        List<Integer> expectedList1 = Arrays.asList(2, 4, 5, 6, 7);

        // --- TEST CASE 2: Edge case - Key does not exist in the tree ---
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);
        int key2 = 0;
        List<Integer> expectedList2 = Arrays.asList(2, 3, 4, 5, 6, 7);

        // --- TEST CASE 3: Edge case - Empty Tree ---
        TreeNode root3 = null;
        int key3 = 0;
        List<Integer> expectedList3 = Arrays.asList();

        // --- TEST CASE 4: Edge case - Single element tree, deleting the root ---
        TreeNode root4 = new TreeNode(10);
        int key4 = 10;
        List<Integer> expectedList4 = Arrays.asList();

        // --- TEST CASE 5: Edge case - Deleting a leaf node ---
        TreeNode root5 = new TreeNode(5);
        root5.left = new TreeNode(2);
        root5.right = new TreeNode(8);
        int key5 = 2;
        List<Integer> expectedList5 = Arrays.asList(5, 8);

        // --- TEST CASE 6: Edge case - Deleting a node with only a left child ---
        TreeNode root6 = new TreeNode(10);
        root6.left = new TreeNode(5);
        root6.left.left = new TreeNode(3);
        int key6 = 5;
        List<Integer> expectedList6 = Arrays.asList(3, 10);

        // --- TEST CASE 7: Edge case - Deleting a node with only a right child ---
        TreeNode root7 = new TreeNode(10);
        root7.right = new TreeNode(15);
        root7.right.right = new TreeNode(20);
        int key7 = 15;
        List<Integer> expectedList7 = Arrays.asList(10, 20);

        // --- TEST CASE 8: Edge case - Deleting the root node when it has only a left child ---
        TreeNode root8 = new TreeNode(50);
        root8.left = new TreeNode(30);
        root8.left.left = new TreeNode(20);
        root8.left.right = new TreeNode(40);
        int key8 = 50;
        List<Integer> expectedList8 = Arrays.asList(20, 30, 40);

        // --- TEST CASE 9: Complex case - Deleting the root node when it has two rich subtrees ---
        //         30
        //       /    \
        //     20      40
        //    /       /  \
        //   10      35   50
        TreeNode root9 = new TreeNode(30);
        root9.left = new TreeNode(20);
        root9.right = new TreeNode(40);
        root9.left.left = new TreeNode(10);
        root9.right.left = new TreeNode(35);
        root9.right.right = new TreeNode(50);
        int key9 = 30;
        List<Integer> expectedList9 = Arrays.asList(10, 20, 35, 40, 50);

        // --- TEST CASE 10: Intricate case - Deleting a node deep inside whose successor has a right child ---
        //          50
        //         /  \
        //       25    75
        //            /  \
        //          60    80
        //            \
        //             65
        //            /  \
        //           62   70
        TreeNode root10 = new TreeNode(50);
        root10.left = new TreeNode(25);
        root10.right = new TreeNode(75);
        root10.right.left = new TreeNode(60);
        root10.right.right = new TreeNode(80);
        root10.right.left.right = new TreeNode(65);
        root10.right.left.right.left = new TreeNode(62);
        root10.right.left.right.right = new TreeNode(70);
        int key10 = 75; // 75 has two children. Its successor is 80 (which has no children) OR if we look at left-most of right child, it's complex. Let's trace successor of 75: it is 80.
        // Wait, the successor of 75 is the smallest element in its right subtree. The right child of 75 is 80. 80 has no left child. So 80 is the successor.
        // Let's target key 60 instead to force complex successor extraction (Successor of 60 is 62, which is a leaf, but 65 has children).
        // Let's stick to key = 50 (the absolute root) to test complex successor management where the successor (60) has a right subtree (65 -> 62, 70).
        int key10_final = 50;
        List<Integer> expectedList10 = Arrays.asList(25, 62, 65, 70, 60, 75, 80);
        // Let's sort the expected list explicitly to ensure verification safety: [25, 60, 62, 65, 70, 75, 80]
        List<Integer> sortedExpectedList10 = Arrays.asList(25, 60, 62, 65, 70, 75, 80);

        // Test Registry
        TreeNode[] testRoots = {root1, root2, root3, root4, root5, root6, root7, root8, root9, root10};
        int[] testKeys = {key1, key2, key3, key4, key5, key6, key7, key8, key9, key10_final};
        List<List<Integer>> expectedLists = Arrays.asList(
                expectedList1, expectedList2, expectedList3, expectedList4, expectedList5,
                expectedList6, expectedList7, expectedList8, expectedList9, sortedExpectedList10
        );

        System.out.println("Executing Deletion Test Suite...\n");
        for (int i = 0; i < testRoots.length; i++) {
            int currentCase = i + 1;

            TreeNode resultRoot = bstDeletion.deleteNode(testRoots[i], testKeys[i]);
            boolean isCorrect = verifyDeletion(resultRoot, expectedLists.get(i));

            if (isCorrect) {
                System.out.println("Test Case " + currentCase + ": PASSED");
                passed++;
            } else {
                System.out.println("Test Case " + currentCase + ": FAILED");
                System.out.println("   Attempted to delete key: " + testKeys[i]);
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