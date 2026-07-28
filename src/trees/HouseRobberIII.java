package trees;

import java.util.*;

public class HouseRobberIII {

    
    /**
     * Given the root of a binary tree, returns the maximum amount of money 
     * the thief can rob without alerting the police.
     */
    public int rob(TreeNode root) {
        if(root==null)return 0;
        if(root.left==null&&root.right==null)return 0;
        int[]ans=helper(root);
        return Math.max(ans[0], ans[1]);
    }
    
    private int[] helper(TreeNode root) {
    	if(root==null)return new int[] {0,0};
    	
    	int[]left=helper(root.left);
    	int[]right=helper(root.right);
    	
    	int take=root.val+ left[1]+right[1];
    	
    	int not=Math.max(left[0], left[1])+Math.max(right[0], right[1]);
    	
    	return new int[] {take,not};
    }

    // =========================================================================
    // TEST RUNNER & HELPER METHODS
    // =========================================================================

    public static void main(String[] args) {
        HouseRobberIII solver = new HouseRobberIII();

        TestCase[] testCases = new TestCase[] {
            // 1. Example 1
            new TestCase(
                buildTree(new Integer[]{3, 2, 3, null, 3, null, 1}),
                7,
                "[3, 2, 3, null, 3, null, 1] - Root + Grandchildren"
            ),
            // 2. Example 2
            new TestCase(
                buildTree(new Integer[]{3, 4, 5, 1, 3, null, 1}),
                9,
                "[3, 4, 5, 1, 3, null, 1] - Children nodes chosen"
            ),
            // 3. Single Node Tree
            new TestCase(
                buildTree(new Integer[]{10}),
                10,
                "Single Node [10]"
            ),
            // 4. Skewed Tree (Right Heavy)
            new TestCase(
                buildTree(new Integer[]{1, null, 2, null, 3, null, 4}),
                6,
                "Right Skewed Chain [1 -> 2 -> 3 -> 4] (Rob 2 + 4)"
            ),
            // 5. Left Heavy Skewed Tree
            new TestCase(
                buildTree(new Integer[]{10, 1, null, 20, null, 2}),
                30,
                "Left Skewed Chain [10, 1, 20, 2] (Rob 10 + 20)"
            ),
            // 6. Tree with all zero values
            new TestCase(
                buildTree(new Integer[]{0, 0, 0, null, 0}),
                0,
                "All zero values in tree"
            ),
            // 7. Wide Complete Binary Tree
            new TestCase(
                buildTree(new Integer[]{4, 1, 2, 3, null, null, 1}),
                9,
                "Balanced Tree: Root (4) + Grandchildren (3 + 1 + 1...)"
            ),
            // 8. Skipping two levels is optimal
            new TestCase(
                buildTree(new Integer[]{2, 1, 3, null, 4, null, null}),
                7,
                "Root (2) vs Children vs Grandchild (4 + 3 = 7)"
            ),
            // 9. Root value extremely large
            new TestCase(
                buildTree(new Integer[]{100, 1, 1, 1, 1, 1, 1}),
                104,
                "Huge Root value vs Leaves"
            ),
            // 10. Symmetric Tree
            new TestCase(
                buildTree(new Integer[]{10, 20, 20, 5, 5, 5, 5}),
                40,
                "Symmetric Tree (Robbing children 20 + 20)"
            ),
            // 11. Deep linear chain requiring optimal gap selection
            new TestCase(
                buildTree(new Integer[]{5, 1, null, 1, null, 5, null}),
                10,
                "Alternating Chain [5, 1, 1, 5] (Rob first and last)"
            )
        };

        System.out.println("==========================================================================");
        System.out.printf("%-10s | %-10s | %-10s | %-8s | %s%n", "Test Case", "Expected", "Actual", "Status", "Details");
        System.out.println("==========================================================================");

        int passedCount = 0;
        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int actual = solver.rob(tc.root);
            boolean passed = actual == tc.expected;
            if (passed) passedCount++;

            String status = passed ? "PASSED" : "FAILED";
            System.out.printf("%-10d | %-10d | %-10d | %-8s | %s%n", 
                (i + 1), tc.expected, actual, status, tc.details);
        }

        System.out.println("==========================================================================");
        System.out.printf("SUMMARY: Passed %d / %d test cases. (Failed: %d)%n", 
            passedCount, testCases.length, testCases.length - passedCount);
        System.out.println("==========================================================================");
    }

    private static class TestCase {
        TreeNode root;
        int expected;
        String details;

        TestCase(TreeNode root, int expected, String details) {
            this.root = root;
            this.expected = expected;
            this.details = details;
        }
    }

    /**
     * Builds a binary tree from a LeetCode-style array representation (Level Order).
     */
    private static TreeNode buildTree(Integer[] nodes) {
        if (nodes == null || nodes.length == 0 || nodes[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode current = queue.poll();

            if (i < nodes.length && nodes[i] != null) {
                current.left = new TreeNode(nodes[i]);
                queue.add(current.left);
            }
            i++;

            if (i < nodes.length && nodes[i] != null) {
                current.right = new TreeNode(nodes[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }
}