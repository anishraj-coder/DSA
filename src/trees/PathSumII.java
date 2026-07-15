package trees;

import java.util.*;


public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>>ans=new ArrayList<>();
        List<Integer>curr=new ArrayList<>();
        int[]sum=new int[1];
        sum[0]=0;
        helper(root,targetSum,curr,ans,sum);
        return ans;
    }

    private void helper(TreeNode root,int target,List<Integer>curr,List<List<Integer>>ans,int[]sum){
        if(root==null)return;
        sum[0]+=root.val;
        curr.add(root.val);
        if(root.left==null&&root.right==null){
            if(sum[0]==target)ans.add(new ArrayList<>(curr));
        }

        helper(root.left,target,curr,ans,sum);
        helper(root.right,target,curr,ans,sum);
        sum[0]-=root.val;
        curr.removeLast();

    }

    public static void main(String[] args) {
        PathSumII solver = new PathSumII();
        int passed = 0;
        int totalTestCases = 10;

        System.out.println("Running Path Sum II Test Cases...\n");

        // --- Test Case 1: Empty Tree ---
        // Expected: []
        TreeNode root1 = null;
        int target1 = 0;
        List<List<Integer>> exp1 = new ArrayList<>();
        List<List<Integer>> act1 = solver.pathSum(root1, target1);
        passed += verifyTestCase(1, "Empty Tree", exp1, act1);

        // --- Test Case 2: Single Node Match ---
        // Expected: [[5]]
        TreeNode root2 = new TreeNode(5);
        int target2 = 5;
        List<List<Integer>> exp2 = Arrays.asList(Arrays.asList(5));
        List<List<Integer>> act2 = solver.pathSum(root2, target2);
        passed += verifyTestCase(2, "Single Node Match", exp2, act2);

        // --- Test Case 3: Single Node Mismatch ---
        // Expected: []
        TreeNode root3 = new TreeNode(5);
        int target3 = 2;
        List<List<Integer>> exp3 = new ArrayList<>();
        List<List<Integer>> act3 = solver.pathSum(root3, target3);
        passed += verifyTestCase(3, "Single Node Mismatch", exp3, act3);

        // --- Test Case 4: Target Achieved at Internal Node Only ---
        //     2
        //    /
        //   3
        // Target: 2 -> Expected: [] (2 is not a leaf node)
        TreeNode root4 = new TreeNode(2);
        root4.left = new TreeNode(3);
        int target4 = 2;
        List<List<Integer>> exp4 = new ArrayList<>();
        List<List<Integer>> act4 = solver.pathSum(root4, target4);
        passed += verifyTestCase(4, "Internal Node Match Only", exp4, act4);

        // --- Test Case 5: Standard Multiple Paths Match (Example 1) ---
        //          5
        //         / \
        //        4   8
        //       /   / \
        //      11  13  4
        //     /  \    / \
        //    7    2  5   1
        // Target: 22 -> Expected: [[5, 4, 11, 2], [5, 8, 4, 5]]
        TreeNode root5 = new TreeNode(5);
        root5.left = new TreeNode(4);
        root5.right = new TreeNode(8);
        root5.left.left = new TreeNode(11);
        root5.left.left.left = new TreeNode(7);
        root5.left.left.right = new TreeNode(2);
        root5.right.left = new TreeNode(13);
        root5.right.right = new TreeNode(4);
        root5.right.right.left = new TreeNode(5);
        root5.right.right.right = new TreeNode(1);
        int target5 = 22;
        List<List<Integer>> exp5 = Arrays.asList(
                Arrays.asList(5, 4, 11, 2),
                Arrays.asList(5, 8, 4, 5)
        );
        List<List<Integer>> act5 = solver.pathSum(root5, target5);
        passed += verifyTestCase(5, "Multiple Paths Match", exp5, act5);

        // --- Test Case 6: Negative Nodes Paths ---
        //      -2
        //        \
        //        -3
        //        /
        //       5
        // Target: 0 -> Expected: [[-2, -3, 5]]
        TreeNode root6 = new TreeNode(-2);
        root6.right = new TreeNode(-3);
        root6.right.left = new TreeNode(5);
        int target6 = 0;
        List<List<Integer>> exp6 = Arrays.asList(Arrays.asList(-2, -3, 5));
        List<List<Integer>> act6 = solver.pathSum(root6, target6);
        passed += verifyTestCase(6, "Negative Nodes Paths", exp6, act6);

        // --- Test Case 7: Trailing Zero Leaves Creating Duplicates ---
        //     1
        //    /
        //   2
        //  / \
        // 0   0
        // Target: 3 -> Expected: [[1, 2, 0], [1, 2, 0]]
        TreeNode root7 = new TreeNode(1);
        root7.left = new TreeNode(2);
        root7.left.left = new TreeNode(0);
        root7.left.right = new TreeNode(0);
        int target7 = 3;
        List<List<Integer>> exp7 = Arrays.asList(
                Arrays.asList(1, 2, 0),
                Arrays.asList(1, 2, 0)
        );
        List<List<Integer>> act7 = solver.pathSum(root7, target7);
        passed += verifyTestCase(7, "Trailing Zero Leaves", exp7, act7);

        // --- Test Case 8: Alternating Signs Canceling (Backtracking Check) ---
        //       10
        //      /  \
        //    -5    5
        //    /
        //   5
        // Target: 10 -> Expected: [[10, -5, 5], [10, 5]]
        TreeNode root8 = new TreeNode(10);
        root8.left = new TreeNode(-5);
        root8.left.left = new TreeNode(5);
        root8.right = new TreeNode(5);
        int target8 = 10;
        List<List<Integer>> exp8 = Arrays.asList(
                Arrays.asList(10, -5, 5),
                Arrays.asList(10, 5)
        );
        List<List<Integer>> act8 = solver.pathSum(root8, target8);
        passed += verifyTestCase(8, "Alternating Signs", exp8, act8);

        // --- Test Case 9: Deep Skewed Tree (No Match) ---
        // 1 -> 2 -> 3 -> 4
        // Target: 6 -> Expected: [] (1+2+3=6 but node 3 is not a leaf node)
        TreeNode root9 = new TreeNode(1);
        root9.right = new TreeNode(2);
        root9.right.right = new TreeNode(3);
        root9.right.right.right = new TreeNode(4);
        int target9 = 6;
        List<List<Integer>> exp9 = new ArrayList<>();
        List<List<Integer>> act9 = solver.pathSum(root9, target9);
        passed += verifyTestCase(9, "Deep Skewed Tree (No Match)", exp9, act9);

        // --- Test Case 10: Complete Mirror Branches with Duplicate Values ---
        //       1
        //      / \
        //     2   2
        //    /     \
        //   3       3
        // Target: 6 -> Expected: [[1, 2, 3], [1, 2, 3]]
        TreeNode root10 = new TreeNode(1);
        root10.left = new TreeNode(2);
        root10.right = new TreeNode(2);
        root10.left.left = new TreeNode(3);
        root10.right.right = new TreeNode(3);
        int target10 = 6;
        List<List<Integer>> exp10 = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 2, 3)
        );
        List<List<Integer>> act10 = solver.pathSum(root10, target10);
        passed += verifyTestCase(10, "Mirror Duplicate Branches", exp10, act10);

        // --- Summary ---
        System.out.println("----------------------------------------");
        System.out.printf("Execution Finished: %d/%d Test Cases Passed.\n", passed, totalTestCases);
        if (passed == totalTestCases) {
            System.out.println("Result: ALL PASSED! Excellent work.");
        } else {
            System.out.println("Result: SOME TEST CASES FAILED. Watch your deep references or backtracking list mutations!");
        }
    }

    private static int verifyTestCase(int id, String description, List<List<Integer>> expected, List<List<Integer>> actual) {
        // Sort lists to prevent ordering mismatches from throwing a false failure
        List<List<Integer>> expSorted = sortPaths(expected);
        List<List<Integer>> actSorted = sortPaths(actual);

        boolean status = expSorted.equals(actSorted);
        System.out.printf("Test Case %d [%s]:\n", id, description);
        System.out.println("  Expected Output: " + expected);
        System.out.println("  Actual Output:   " + actual);
        System.out.printf("  Status:          %s\n\n", status ? "PASSED" : "FAILED");
        return status ? 1 : 0;
    }

    private static List<List<Integer>> sortPaths(List<List<Integer>> paths) {
        if (paths == null) return new ArrayList<>();
        List<List<Integer>> sorted = new ArrayList<>();
        for (List<Integer> path : paths) {
            sorted.add(new ArrayList<>(path));
        }
        sorted.sort((a, b) -> {
            int len = Math.min(a.size(), b.size());
            for (int i = 0; i < len; i++) {
                int cmp = Integer.compare(a.get(i), b.get(i));
                if (cmp != 0) return cmp;
            }
            return Integer.compare(a.size(), b.size());
        });
        return sorted;
    }
}