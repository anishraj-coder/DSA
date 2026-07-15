package BinarySearchTree;

public class BinarySearchTreeCeiling {


    /**
     * Finds the Ceil of x in the given Binary Search Tree.
     * Ceil(x) is the smallest node value greater than or equal to x.
     *
     * @param root The root node of the BST
     * @param x    The target integer
     * @return     The ceiling value of x, or -1 if it does not exist
     */
    public int findCeil(TreeNode root, int x) {
        int[]ans=new int[]{-1};
        helper(root,ans,x);
        return ans[0];
    }

    private void helper(TreeNode root,int[]ans,int target){
        if(root==null)return;
        if(root.val==target){
            ans[0]=root.val;
            return;
        }else if(root.val>target){
            ans[0]=root.val;
            helper(root.left,ans,target);
        }else helper(root.right,ans,target);
    }

    public static void main(String[] args) {
        BinarySearchTreeCeiling bstCeiling = new BinarySearchTreeCeiling();
        int passed = 0;
        int failed = 0;

        // --- TEST CASE 1: Standard case - Value exists in the tree ---
        //        5
        //       / \
        //      1   7
        //       \
        //        2
        //         \
        //          3
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(7);
        root1.left.right = new TreeNode(2);
        root1.left.right.right = new TreeNode(3);
        int x1 = 3;
        int expected1 = 3;

        // --- TEST CASE 2: Standard case - Value doesn't exist, ceil is a leaf node ---
        //        10
        //       /  \
        //      5    11
        //     / \
        //    4   7
        //         \
        //          8
        TreeNode root2 = new TreeNode(10);
        root2.left = new TreeNode(5);
        root2.right = new TreeNode(11);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(7);
        root2.left.right.right = new TreeNode(8);
        int x2 = 6;
        int expected2 = 7;

        // --- TEST CASE 3: Edge case - Empty Tree ---
        TreeNode root3 = null;
        int x3 = 5;
        int expected3 = -1;

        // --- TEST CASE 4: Edge case - x is smaller than the minimum value in the tree ---
        //      20
        //     /
        //    10
        TreeNode root4 = new TreeNode(20);
        root4.left = new TreeNode(10);
        int x4 = 5;
        int expected4 = 10;

        // --- TEST CASE 5: Edge case - x is larger than the maximum value in the tree (No ceil exists) ---
        //     15
        //       \
        //        25
        TreeNode root5 = new TreeNode(15);
        root5.right = new TreeNode(25);
        int x5 = 30;
        int expected5 = -1;

        // --- TEST CASE 6: Edge case - Single Node Tree (x equals root) ---
        TreeNode root6 = new TreeNode(12);
        int x6 = 12;
        int expected6 = 12;

        // --- TEST CASE 7: Edge case - Single Node Tree (x greater than root) ---
        TreeNode root7 = new TreeNode(12);
        int x7 = 15;
        int expected7 = -1;

        // --- TEST CASE 8: Complex case - Deep Left-Skewed Tree ---
        //       100
        //       /
        //      80
        //     /
        //    60
        //   /
        //  40
        TreeNode root8 = new TreeNode(100);
        root8.left = new TreeNode(80);
        root8.left.left = new TreeNode(60);
        root8.left.left.left = new TreeNode(40);
        int x8 = 55;
        int expected8 = 60;

        // --- TEST CASE 9: Complex case - Deep Right-Skewed Tree ---
        //   10
        //     \
        //      20
        //        \
        //         30
        //           \
        //            40
        TreeNode root9 = new TreeNode(10);
        root9.right = new TreeNode(20);
        root9.right.right = new TreeNode(30);
        root9.right.right.right = new TreeNode(40);
        int x9 = 25;
        int expected9 = 30;

        // --- TEST CASE 10: Intricate case - Ceil is an ancestor higher up up in the traversal path ---
        //         50
        //        /  \
        //      20    60
        //     /  \
        //   10    30
        //          \
        //           35
        TreeNode root10 = new TreeNode(50);
        root10.left = new TreeNode(20);
        root10.right = new TreeNode(60);
        root10.left.left = new TreeNode(10);
        root10.left.right = new TreeNode(30);
        root10.left.right.right = new TreeNode(35);
        int x10 = 38;
        int expected10 = 50;

        // Executing Test Suite
        int[][] testInputs = {
                {1, x1, expected1}, {2, x2, expected2}, {3, x3, expected3},
                {4, x4, expected4}, {5, x5, expected5}, {6, x6, expected6},
                {7, x7, expected7}, {8, x8, expected8}, {9, x9, expected9},
                {10, x10, expected10}
        };
        TreeNode[] testRoots = {root1, root2, root3, root4, root5, root6, root7, root8, root9, root10};

        System.out.println("Executing Test Cases...\n");
        for (int i = 0; i < testInputs.length; i++) {
            int actual = bstCeiling.findCeil(testRoots[i], testInputs[i][1]);
            int expected = testInputs[i][2];

            if (actual == expected) {
                System.out.println("Test Case " + testInputs[i][0] + ": PASSED");
                passed++;
            } else {
                System.out.println("Test Case " + testInputs[i][0] + ": FAILED");
                System.out.println("   Target (x): " + testInputs[i][1]);
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