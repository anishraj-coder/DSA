package BinarySearchTree;

public class BinarySearchTreeFloor {



    /**
     * Finds the Floor of k in the given Binary Search Tree.
     * Floor(k) is the greatest node value less than or equal to k.
     *
     * @param root The root node of the BST
     * @param k    The target integer
     * @return     The floor value of k, or -1 if it does not exist
     */
    public int floor(TreeNode root, int k) {
        int[]ans=new int[]{-1};
        helper(root,ans,k);
        return ans[0];
    }

    private void helper(TreeNode root,int[]ans,int target){
        if(root==null)return;
        if(root.val==target)ans[0]=root.val;
        else if(root.val<target){
            ans[0]=root.val;
            helper(root.right,ans,target);
        }else helper(root.left,ans,target);
    }

    public static void main(String[] args) {
        BinarySearchTreeFloor bstFloor = new BinarySearchTreeFloor();
        int passed = 0;
        int failed = 0;

        // --- TEST CASE 1: Standard case - Value doesn't exist, floor is in an inner right subtree ---
        //         10
        //       /    \
        //      7      15
        //     / \    /  \
        //    2   8  11  16
        TreeNode root1 = new TreeNode(10);
        root1.left = new TreeNode(7);
        root1.right = new TreeNode(15);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(8);
        root1.right.left = new TreeNode(11);
        root1.right.right = new TreeNode(16);
        int k1 = 14;
        int expected1 = 11;

        // --- TEST CASE 2: Standard case - Value doesn't exist, floor is an inner deep node ---
        //         5
        //       /   \
        //      2     12
        //     / \   /  \
        //    1   3 9   21
        //             /  \
        //            19  25
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(12);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);
        root2.right.left = new TreeNode(9);
        root2.right.right = new TreeNode(21);
        root2.right.right.left = new TreeNode(19);
        root2.right.right.right = new TreeNode(25);
        int k2 = 24;
        int expected2 = 21;

        // --- TEST CASE 3: Standard case - Same tree as above, checking floor for a smaller gap ---
        TreeNode root3 = root2;
        int k3 = 4;
        int expected3 = 3;

        // --- TEST CASE 4: Edge case - Empty Tree ---
        TreeNode root4 = null;
        int k4 = 10;
        int expected4 = -1;

        // --- TEST CASE 5: Edge case - k is smaller than the absolute minimum value in the tree (No floor exists) ---
        //      20
        //     /
        //    10
        TreeNode root5 = new TreeNode(20);
        root5.left = new TreeNode(10);
        int k5 = 5;
        int expected5 = -1;

        // --- TEST CASE 6: Edge case - k is larger than the absolute maximum value in the tree ---
        //     15
        //       \
        //        25
        TreeNode root6 = new TreeNode(15);
        root6.right = new TreeNode(25);
        int k6 = 30;
        int expected6 = 25;

        // --- TEST CASE 7: Edge case - Single Node Tree (k equals root) ---
        TreeNode root7 = new TreeNode(42);
        int k7 = 42;
        int expected7 = 42;

        // --- TEST CASE 8: Edge case - Single Node Tree (k smaller than root) ---
        TreeNode root8 = new TreeNode(42);
        int k8 = 40;
        int expected8 = -1;

        // --- TEST CASE 9: Complex case - Deep Left-Skewed Tree ---
        //       100
        //       /
        //      80
        //     /
        //    60
        //   /
        //  40
        TreeNode root9 = new TreeNode(100);
        root9.left = new TreeNode(80);
        root9.left.left = new TreeNode(60);
        root9.left.left.left = new TreeNode(40);
        int k9 = 75;
        int expected9 = 60;

        // --- TEST CASE 10: Complex case - Floor requires traveling up to a previously passed ancestor ---
        //         50
        //        /  \
        //      20    60
        //           /  \
        //         55    70
        //        /
        //       52
        TreeNode root10 = new TreeNode(50);
        root10.left = new TreeNode(20);
        root10.right = new TreeNode(60);
        root10.right.left = new TreeNode(55);
        root10.right.right = new TreeNode(70);
        root10.right.left.left = new TreeNode(52);
        int k10 = 54;
        int expected10 = 52;

        // Executing Test Suite
        int[][] testInputs = {
                {1, k1, expected1}, {2, k2, expected2}, {3, k3, expected3},
                {4, k4, expected4}, {5, k5, expected5}, {6, k6, expected6},
                {7, k7, expected7}, {8, k8, expected8}, {9, k9, expected9},
                {10, k10, expected10}
        };
        TreeNode[] testRoots = {root1, root2, root3, root4, root5, root6, root7, root8, root9, root10};

        System.out.println("Executing Test Cases...\n");
        for (int i = 0; i < testInputs.length; i++) {
            int actual = bstFloor.floor(testRoots[i], testInputs[i][1]);
            int expected = testInputs[i][2];

            if (actual == expected) {
                System.out.println("Test Case " + testInputs[i][0] + ": PASSED");
                passed++;
            } else {
                System.out.println("Test Case " + testInputs[i][0] + ": FAILED");
                System.out.println("   Target (k): " + testInputs[i][1]);
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