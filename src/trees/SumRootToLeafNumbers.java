package trees;

import java.util.*;



public class SumRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {
        long[]curr=new long[1];
        long[]sum=new long[]{0L};
        helper(root,curr,sum);
        return (int)sum[0];
    }

    private void helper(TreeNode root,long[] curr,long[]sum){
        if(root==null)return;
        curr[0]=curr[0]*10+root.val;
        if(root.left==null&&root.right==null){
            sum[0]+=curr[0];
        }
        helper(root.left,curr,sum);
        helper(root.right,curr,sum);
        curr[0]=curr[0]/10;
    }

    public static void main(String[] args) {
        SumRootToLeafNumbers solver = new SumRootToLeafNumbers();
        int passed = 0;
        int totalTestCases = 10;

        System.out.println("Running Sum Root to Leaf Numbers Test Cases...\n");

        // --- Test Case 1: Single Node Tree ---
        // Expected: 1 (The only path is '1')
        TreeNode root1 = new TreeNode(1);
        int exp1 = 1;
        int act1 = solver.sumNumbers(root1);
        passed += verifyTestCase(1, "Single Node Tree", exp1, act1);

        // --- Test Case 2: Standard Small Binary Tree (Example 1) ---
        //     1
        //    / \
        //   2   3
        // Paths: 12 + 13 = 25 -> Expected: 25
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        int exp2 = 25;
        int act2 = solver.sumNumbers(root2);
        passed += verifyTestCase(2, "Standard Small Tree", exp2, act2);

        // --- Test Case 3: Multiple Branches with Zeroes (Example 2) ---
        //          4
        //         / \
        //        9   0
        //       / \
        //      5   1
        // Paths: 495 + 491 + 40 = 1026 -> Expected: 1026
        TreeNode root3 = new TreeNode(4);
        root3.left = new TreeNode(9);
        root3.right = new TreeNode(0);
        root3.left.left = new TreeNode(5);
        root3.left.right = new TreeNode(1);
        int exp3 = 1026;
        int act3 = solver.sumNumbers(root3);
        passed += verifyTestCase(3, "Multiple Branches with Zeroes", exp3, act3);

        // --- Test Case 4: Deep Left-Skewed Tree (Straight Line) ---
        // 1 -> 2 -> 3 -> 4
        // Path: 1234 -> Expected: 1234
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(3);
        root4.left.left.left = new TreeNode(4);
        int exp4 = 1234;
        int act4 = solver.sumNumbers(root4);
        passed += verifyTestCase(4, "Deep Left-Skewed Tree", exp4, act4);

        // --- Test Case 5: Root Node is Zero ---
        //     0
        //    / \
        //   1   2
        // Paths: 01 (1) + 02 (2) = 3 -> Expected: 3
        TreeNode root5 = new TreeNode(0);
        root5.left = new TreeNode(1);
        root5.right = new TreeNode(2);
        int exp5 = 3;
        int act5 = solver.sumNumbers(root5);
        passed += verifyTestCase(5, "Root Node is Zero", exp5, act5);

        // --- Test Case 6: Subtree Branching from Zero ---
        //     1
        //    /
        //   0
        //  /
        // 5
        // Path: 105 -> Expected: 105
        TreeNode root6 = new TreeNode(1);
        root6.left = new TreeNode(0);
        root6.left.left = new TreeNode(5);
        int exp6 = 105;
        int act6 = solver.sumNumbers(root6);
        passed += verifyTestCase(6, "Subtree Branching from Zero", exp6, act6);

        // --- Test Case 7: Strict Internal Node Trap (Single-Child Paths) ---
        //     1
        //    / \
        //   2   3
        //  /
        // 4
        // Paths: 124 (from leaf 4) + 13 (from leaf 3) = 137
        // Note: 2 is an internal node, not a leaf!
        // Expected: 137
        TreeNode root7 = new TreeNode(1);
        root7.left = new TreeNode(2);
        root7.right = new TreeNode(3);
        root7.left.left = new TreeNode(4);
        int exp7 = 137;
        int act7 = solver.sumNumbers(root7);
        passed += verifyTestCase(7, "Single-Child Internal Node Trap", exp7, act7);

        // --- Test Case 8: Full Binary Tree of Depth 3 with Same Digits ---
        //        9
        //      /   \
        //     9     9
        //    / \   / \
        //   9   9 9   9
        // Paths: 4 paths of 999 = 999 * 4 = 3996 -> Expected: 3996
        TreeNode root8 = new TreeNode(9);
        root8.left = new TreeNode(9);
        root8.right = new TreeNode(9);
        root8.left.left = new TreeNode(9);
        root8.left.right = new TreeNode(9);
        root8.right.left = new TreeNode(9);
        root8.right.right = new TreeNode(9);
        int exp8 = 3996;
        int act8 = solver.sumNumbers(root8);
        passed += verifyTestCase(8, "Full Tree with Identical Digits", exp8, act8);

        // --- Test Case 9: Zig-Zag Path Structure ---
        //   5
        //    \
        //     4
        //    /
        //   3
        //    \
        //     2
        // Path: 5432 -> Expected: 5432
        TreeNode root9 = new TreeNode(5);
        root9.right = new TreeNode(4);
        root9.right.left = new TreeNode(3);
        root9.right.left.right = new TreeNode(2);
        int exp9 = 5432;
        int act9 = solver.sumNumbers(root9);
        passed += verifyTestCase(9, "Zig-Zag Path Structure", exp9, act9);

        // --- Test Case 10: All Leaves are Zeroes ---
        //        1
        //      /   \
        //     2     3
        //    /       \
        //   0         0
        // Paths: 120 + 130 = 250 -> Expected: 250
        TreeNode root10 = new TreeNode(1);
        root10.left = new TreeNode(2);
        root10.right = new TreeNode(3);
        root10.left.left = new TreeNode(0);
        root10.right.right = new TreeNode(0);
        int exp10 = 250;
        int act10 = solver.sumNumbers(root10);
        passed += verifyTestCase(10, "All Leaves are Zeroes", exp10, act10);

        // --- Summary ---
        System.out.println("----------------------------------------");
        System.out.printf("Execution Finished: %d/%d Test Cases Passed.\n", passed, totalTestCases);
        if (passed == totalTestCases) {
            System.out.println("Result: ALL PASSED! Stellar work.");
        } else {
            System.out.println("Result: SOME TEST CASES FAILED. Watch out for how you track partial values down single-child routes!");
        }
    }

    private static int verifyTestCase(int id, String description, int expected, int actual) {
        boolean status = (expected == actual);
        System.out.printf("Test Case %d [%s]:\n", id, description);
        System.out.printf("  Expected Output: %d\n", expected);
        System.out.printf("  Actual Output:   %d\n", actual);
        System.out.printf("  Status:          %s\n\n", status ? "PASSED" : "FAILED");
        return status ? 1 : 0;
    }
}