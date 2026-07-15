package BinarySearchTree;

public class BinarySearchTreeKthLargest {

    /**
     * Finds the kth largest value (1-indexed) in the Binary Search Tree.
     *
     * @param root The root node of the BST
     * @param k    The index of the largest element to find (1-indexed)
     * @return     The value of the kth largest node
     */
    public int kthLargest(TreeNode root, int k) {
        if(root==null)return -1;
        TreeNode[]ans=new TreeNode[1];
        int[]s=new int[]{k};
        helper(root,ans,s);
        return ans[0]==null?-1:ans[0].val;
    }

    private void helper(TreeNode root,TreeNode[]ans,int[]k){
         if(root==null)return;
         if(ans[0]!=null)return;
         helper(root.right,ans,k);
         if(ans[0]!=null)return;
         if(k[0]==1){
             ans[0]=root;
             return;
         }
         k[0]--;
         helper(root.left,ans,k);
    }

    public static void main(String[] args) {
        BinarySearchTreeKthLargest bstKth = new BinarySearchTreeKthLargest();
        int passed = 0;
        int failed = 0;

        // --- TEST CASE 1: Standard case - Finding the absolute maximum (k = 1) ---
        //        3
        //       / \
        //      1   4
        //       \
        //        2
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);
        int k1 = 1;
        int expected1 = 4;

        // --- TEST CASE 2: Standard case - Finding an intermediate element ---
        //         5
        //        / \
        //       3   6
        //      / \
        //     2   4
        //    /
        //   1
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.left.left.left = new TreeNode(1);
        int k2 = 3; // Sorted descending: 6, 5, 4, 3, 2, 1 -> 3rd largest is 4
        int expected2 = 4;

        // --- TEST CASE 3: Edge case - Single element tree ---
        TreeNode root3 = new TreeNode(88);
        int k3 = 1;
        int expected3 = 88;

        // --- TEST CASE 4: Edge case - k is the exact total number of nodes (Minimum element) ---
        //     10
        //    /  \
        //   5    15
        TreeNode root4 = new TreeNode(10);
        root4.left = new TreeNode(5);
        root4.right = new TreeNode(15);
        int k4 = 3; // Sorted descending: 15, 10, 5 -> 3rd largest is 5
        int expected4 = 5;

        // --- TEST CASE 5: Complex case - Deep Left-Skewed Tree ---
        //       50
        //      /
        //     40
        //    /
        //   30
        //  /
        // 20
        TreeNode root5 = new TreeNode(50);
        root5.left = new TreeNode(40);
        root5.left.left = new TreeNode(30);
        root5.left.left.left = new TreeNode(20);
        int k5 = 2; // Sorted descending: 50, 40, 30, 20 -> 2nd largest is 40
        int expected5 = 40;

        // --- TEST CASE 6: Complex case - Deep Right-Skewed Tree ---
        //   10
        //     \
        //      20
        //        \
        //         30
        //           \
        //            40
        TreeNode root6 = new TreeNode(10);
        root6.right = new TreeNode(20);
        root6.right.right = new TreeNode(30);
        root6.right.right.right = new TreeNode(40);
        int k6 = 3; // Sorted descending: 40, 30, 20, 10 -> 3rd largest is 20
        int expected6 = 20;

        // --- TEST CASE 7: Boundary values - Elements with 0 value ---
        //     0
        //      \
        //       5
        TreeNode root7 = new TreeNode(0);
        root7.right = new TreeNode(5);
        int k7 = 2; // Sorted descending: 5, 0 -> 2nd largest is 0
        int expected7 = 0;

        // --- TEST CASE 8: Intricate case - Root is the target k-th element ---
        //         25
        //       /    \
        //     15      35
        //    /  \    /  \
        //   10  20  30  40
        TreeNode root8 = new TreeNode(25);
        root8.left = new TreeNode(15);
        root8.right = new TreeNode(35);
        root8.left.left = new TreeNode(10);
        root8.left.right = new TreeNode(20);
        root8.right.left = new TreeNode(30);
        root8.right.right = new TreeNode(40);
        int k8 = 4; // Sorted descending: 40, 35, 30, 25, 20, 15, 10 -> 4th largest is 25
        int expected8 = 25;

        // --- TEST CASE 9: Intricate case - Large tree, targeting left subtree ---
        // Same tree structure as Test Case 8
        TreeNode root9 = root8;
        int k9 = 6; // Sorted descending: 40, 35, 30, 25, 20, 15, 10 -> 6th largest is 15
        int expected9 = 15;

        // --- TEST CASE 10: Complex structure - Uneven Zig-Zag paths ---
        //         10
        //        /
        //       5
        //        \
        //         8
        //        /
        //       7
        TreeNode root10 = new TreeNode(10);
        root10.left = new TreeNode(5);
        root10.left.right = new TreeNode(8);
        root10.left.right.left = new TreeNode(7);
        int k10 = 2; // Sorted descending: 10, 8, 7, 5 -> 2nd largest is 8
        int expected10 = 8;

        // Execution Registry
        int[] testKs = {k1, k2, k3, k4, k5, k6, k7, k8, k9, k10};
        int[] expectedOutputs = {expected1, expected2, expected3, expected4, expected5, expected6, expected7, expected8, expected9, expected10};
        TreeNode[] testRoots = {root1, root2, root3, root4, root5, root6, root7, root8, root9, root10};

        System.out.println("Executing Kth Largest Test Suite...\n");
        for (int i = 0; i < testRoots.length; i++) {
            int currentCase = i + 1;
            int actual = bstKth.kthLargest(testRoots[i], testKs[i]);
            int expected = expectedOutputs[i];

            if (actual == expected) {
                System.out.println("Test Case " + currentCase + ": PASSED");
                passed++;
            } else {
                System.out.println("Test Case " + currentCase + ": FAILED");
                System.out.println("   Target k:        " + testKs[i]);
                System.out.println("   Expected Output: " + expected);
                System.out.println("   Actual Output:   " + actual);
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