package trees;

import java.util.*;


public class PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null)return false;
        int[]sum=new int[]{0};
        return helper(root,targetSum,sum);
    }

    private boolean helper(TreeNode root,int target,int[]sum){
        if(root==null)return false;
        if(root.right==null&&root.left==null){
            if(sum[0]+root.val==target)return true;
            else return false;
        }
        sum[0]+=root.val;
        boolean left=helper(root.left,target,sum);
        boolean right=helper(root.right,target,sum);
        sum[0]-=root.val;
        return left||right;
    }

    public static void main(String[] args) {
        PathSum solver = new PathSum();
        int passed = 0;
        int totalTestCases = 10;

        System.out.println("Running Path Sum Test Cases...\n");

        // --- Test Case 1: Empty Tree ---
        // Expected: false (An empty tree has no root-to-leaf paths)
        TreeNode root1 = null;
        int target1 = 0;
        boolean exp1 = false;
        boolean act1 = solver.hasPathSum(root1, target1);
        passed += verifyTestCase(1, "Empty Tree", exp1, act1);

        // --- Test Case 2: Single Node (Match) ---
        // Expected: true
        TreeNode root2 = new TreeNode(5);
        int target2 = 5;
        boolean exp2 = true;
        boolean act2 = solver.hasPathSum(root2, target2);
        passed += verifyTestCase(2, "Single Node (Match)", exp2, act2);

        // --- Test Case 3: Single Node (Mismatch) ---
        // Expected: false
        TreeNode root3 = new TreeNode(5);
        int target3 = 0;
        boolean exp3 = false;
        boolean act3 = solver.hasPathSum(root3, target3);
        passed += verifyTestCase(3, "Single Node (Mismatch)", exp3, act3);

        // --- Test Case 4: Target Sum at Internal Node Only ---
        //     1
        //    /
        //   2
        // Target: 1 -> Expected: false (1 is a root but NOT a leaf node here)
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        int target4 = 1;
        boolean exp4 = false;
        boolean act4 = solver.hasPathSum(root4, target4);
        passed += verifyTestCase(4, "Target Sum at Internal Node Only", exp4, act4);

        // --- Test Case 5: Standard False Case (Example 2) ---
        //     1
        //    / \
        //   2   3
        // Target: 5 -> Expected: false (Paths sum to 3 and 4)
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.right = new TreeNode(3);
        int target5 = 5;
        boolean exp5 = false;
        boolean act5 = solver.hasPathSum(root5, target5);
        passed += verifyTestCase(5, "Standard False Case", exp5, act5);

        // --- Test Case 6: Negative Values Path Match ---
        //    -3
        //      \
        //      -2
        // Target: -5 -> Expected: true (-3 + -2 = -5)
        TreeNode root6 = new TreeNode(-3);
        root6.right = new TreeNode(-2);
        int target6 = -5;
        boolean exp6 = true;
        boolean act6 = solver.hasPathSum(root6, target6);
        passed += verifyTestCase(6, "Negative Values Path Match", exp6, act6);

        // --- Test Case 7: All Zeroes Tree ---
        //     0
        //    / \
        //   0   0
        // Target: 0 -> Expected: true
        TreeNode root7 = new TreeNode(0);
        root7.left = new TreeNode(0);
        root7.right = new TreeNode(0);
        int target7 = 0;
        boolean exp7 = true;
        boolean act7 = solver.hasPathSum(root7, target7);
        passed += verifyTestCase(7, "All Zeroes Tree", exp7, act7);

        // --- Test Case 8: Deep Right-Skewed Tree ---
        // 1 -> 2 -> 3 -> 4
        // Target: 10 -> Expected: true (1 + 2 + 3 + 4 = 10)
        TreeNode root8 = new TreeNode(1);
        root8.right = new TreeNode(2);
        root8.right.right = new TreeNode(3);
        root8.right.right.right = new TreeNode(4);
        int target8 = 10;
        boolean exp8 = true;
        boolean act8 = solver.hasPathSum(root8, target8);
        passed += verifyTestCase(8, "Deep Right-Skewed Tree", exp8, act8);

        // --- Test Case 9: Complex Example (Example 1) ---
        //          5
        //         / \
        //        4   8
        //       /   / \
        //      11  13  4
        //     /  \      \
        //    7    2      1
        // Target: 22 -> Expected: true (5 -> 4 -> 11 -> 2 = 22)
        TreeNode root9 = new TreeNode(5);
        root9.left = new TreeNode(4);
        root9.right = new TreeNode(8);
        root9.left.left = new TreeNode(11);
        root9.left.left.left = new TreeNode(7);
        root9.left.left.right = new TreeNode(2);
        root9.right.left = new TreeNode(13);
        root9.right.right = new TreeNode(4);
        root9.right.right.right = new TreeNode(1);
        int target9 = 22;
        boolean exp9 = true;
        boolean act9 = solver.hasPathSum(root9, target9);
        passed += verifyTestCase(9, "Complex Example (Match)", exp9, act9);

        // --- Test Case 10: Alternating Signs Canceling Out ---
        //       5
        //      /
        //    -5
        //    /
        //   5
        // Target: 5 -> Expected: true (5 + -5 + 5 = 5)
        TreeNode root10 = new TreeNode(5);
        root10.left = new TreeNode(-5);
        root10.left.left = new TreeNode(5);
        int target10 = 5;
        boolean exp10 = true;
        boolean act10 = solver.hasPathSum(root10, target10);
        passed += verifyTestCase(10, "Alternating Signs", exp10, act10);

        // --- Summary Summary ---
        System.out.println("----------------------------------------");
        System.out.printf("Execution Finished: %d/%d Test Cases Passed.\n", passed, totalTestCases);
        if (passed == totalTestCases) {
            System.out.println("Result: ALL PASSED! Excellent work.");
        } else {
            System.out.println("Result: SOME TEST CASES FAILED. Check your edge cases!");
        }
    }

    private static int verifyTestCase(int id, String description, boolean expected, boolean actual) {
        boolean status = (expected == actual);
        System.out.printf("Test Case %d [%s]:\n", id, description);
        System.out.printf("  Expected Output: %b\n", expected);
        System.out.printf("  Actual Output:   %b\n", actual);
        System.out.printf("  Status:          %s\n\n", status ? "PASSED" : "FAILED");
        return status ? 1 : 0;
    }
}