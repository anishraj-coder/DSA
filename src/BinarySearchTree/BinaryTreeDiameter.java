package BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

// Definitive Node Structure for the Binary Tree

public class BinaryTreeDiameter {

    public static int diameterOfBinaryTree(TreeNode<Integer> root) {
        if(root==null||(root.left==null&&root.right==null))return 0;
        int []max=new int[]{0};
        helper(root,max);
        return max[0];
    }


    private static int helper(TreeNode root,int[]max){
        if(root==null)return 0;
        int left=helper(root.left,max);
        int right=helper(root.right,max);
        if(left+right>max[0])max[0]=left+right;
        return Math.max(left,right)+1;
    }

     static class TreeNode<T> {
        public T data;
        public TreeNode<T> left;
        public TreeNode<T> right;

        TreeNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }


    // --- TEST RUNNER CODE ---
    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        // Test Case 1: Sample 1 from problem statement
        Integer[] t1 = {1, 2, 3, 4, 7, null, null, null, null, null, null};
        if (runTest(1, t1, 3)) passed++; else failed++;

        // Test Case 2: Sample 2 from problem statement
        Integer[] t2 = {1, 2, 3, null, null, null, null};
        if (runTest(2, t2, 2)) passed++; else failed++;

        // Test Case 3: Single Node Tree (Edge Case)
        Integer[] t3 = {1};
        if (runTest(3, t3, 0)) passed++; else failed++;

        // Test Case 4: Null Tree / Empty Tree (Edge Case)
        Integer[] t4 = {};
        if (runTest(4, t4, 0)) passed++; else failed++;

        // Test Case 5: Fully Left-Skewed Tree (Linear path)
        Integer[] t5 = {1, 2, null, 3, null, 4, null, 5, null};
        if (runTest(5, t5, 4)) passed++; else failed++;

        // Test Case 6: Perfect Binary Tree (Height 3)
        Integer[] t6 = {1, 2, 3, 4, 5, 6, 7};
        if (runTest(6, t6, 4)) passed++; else failed++;

        // Test Case 7: Path does NOT pass through the root node (Classic Trick Case)
        // Deepest paths are entirely localized within the left subtree
        Integer[] t7 = {1, 2, 3, 4, 5, null, null, 6, null, null, 7, 8, null, null, 9};
        if (runTest(7, t7, 6)) passed++; else failed++;

        // Test Case 8: V-Shaped Tree (Only left child of root and right child of root have long paths)
        Integer[] t8 = {1, 2, 3, 4, null, null, 5, 6, null, null, 7};
        if (runTest(8, t8, 4)) passed++; else failed++;

        // Test Case 9: Unbalanced Tree favoring one deep leaf branch
        Integer[] t9 = {1, 2, null, 3, 4, 5, null, null, 6};
        if (runTest(9, t9, 4)) passed++; else failed++;

        // Test Case 10: Symmetrical zig-zag tree paths
        Integer[] t10 = {1, 2, 3, null, 4, 5, null, null, 6, 7, null};
        if (runTest(10, t10, 5)) passed++; else failed++;

        System.out.println("\n-------------------------------------------");
        System.out.println("Execution Results: Passed [" + passed + "/10] | Failed [" + failed + "/10]");
        System.out.println("-------------------------------------------");
    }

    // Helper method to execute and format the evaluation matrix safely
    private static boolean runTest(int testNum, Integer[] treeArray, int expected) {
        TreeNode<Integer> root = buildTree(treeArray);
        int actual = diameterOfBinaryTree(root);

        if (actual == expected) {
            System.out.println("Test Case " + testNum + ": PASSED");
            return true;
        } else {
            System.out.println("Test Case " + testNum + ": FAILED!");
            System.out.println("   -> Input Array: " + java.util.Arrays.toString(treeArray));
            System.out.println("   -> Expected: " + expected);
            System.out.println("   -> Actual:   " + actual);
            return false;
        }
    }

    // Leverages Level-Order traversal queue strategy to parse flat arrays into memory trees
    private static TreeNode<Integer> buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) return null;

        TreeNode<Integer> root = new TreeNode<>(arr[0]);
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode<Integer> curr = queue.poll();

            // Setup Left Sub-Branch
            if (i < arr.length && arr[i] != null) {
                curr.left = new TreeNode<>(arr[i]);
                queue.add(curr.left);
            }
            i++;

            // Setup Right Sub-Branch
            if (i < arr.length && arr[i] != null) {
                curr.right = new TreeNode<>(arr[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}