package trees;

import java.util.*;



public class MaxLevelSum {

    /**
     * Finds the smallest level x such that the sum of all values of nodes
     * at level x is maximal.
     * * @param root The root of the binary tree
     * @return The smallest level index (starting from 1) with the maximum sum
     */
    public int maxLevelSum(TreeNode root) {
        if (root==null)return -1;
        if(root.left==null&&root.right==null)return 1;
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        int maxLevel=0,level=0,max=Integer.MIN_VALUE;
        while(!q.isEmpty()){
            int sum=0,n=q.size();
            level++;
            for(int i=0;i<n;i++){
                TreeNode rem=q.poll();
                sum+=rem.val;
                if(rem.left!=null)q.offer(rem.left);
                if(rem.right!=null)q.offer(rem.right);
            }
            if(sum>max){
                maxLevel=level;
                max=sum;
            }

        }
        return maxLevel;
    }

    public static void main(String[] args) {
        MaxLevelSum optimizer = new MaxLevelSum();

        // Test Case Definitions
        Object[][] testCases = {
                // {Level nodes in level-order, Expected Output, Description}
                {new Integer[]{1, 7, 0, 7, -8, null, null}, 2, "Standard tree with positive/negative mix"},
                {new Integer[]{989, null, 10250, 98693, -89388, null, null, null, -32127}, 2, "Sparse tree with large values"},
                {new Integer[]{-100, -200, -300, -10, -20, -30, -40}, 3, "All negative values (Sum should be closest to 0)"},
                {new Integer[]{1}, 1, "Single node tree"},
                {new Integer[]{1, 10, 10, -5, -5, -5, -5}, 2, "Tie in sums (Level 2 sum: 20, Level 3 sum: -20)"},
                {new Integer[]{10, 5, 5, 2, 3, 3, 2}, 1, "Tie in sums between Level 1 and Level 2 (Return smallest level)"},
                {new Integer[]{1, 2, null, 3, null, 4, null, 5}, 1, "Skewed tree (Left-heavy) - All levels sum to single node value"},
                {new Integer[]{-1, -1, -1, -1, -1, -1, -1}, 1, "All identical negative values"},
                {new Integer[]{0, -1, 1, -2, 2, -3, 3}, 1, "Levels sum to zero (Should return level 1)"},
                {new Integer[]{100, -100, -100, 50, 50, 50, 50}, 3, "Large negative gap at Level 2, recovery at Level 3"}
        };

        int passed = 0;

        System.out.println("Running DSA Test Suite: Maximum Level Sum\n");
        System.out.printf("%-5s | %-15s | %-15s | %-10s | %s\n", "ID", "Expected", "Actual", "Result", "Description");
        System.out.println("---------------------------------------------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            TreeNode root = buildTree((Integer[]) testCases[i][0]);
            int expected = (int) testCases[i][1];
            String description = (String) testCases[i][2];

            int actual = optimizer.maxLevelSum(root);
            boolean isMatch = actual == expected;

            if (isMatch) passed++;

            System.out.printf("%-5d | %-15d | %-15d | %-10s | %s\n",
                    i + 1, expected, actual, isMatch ? "PASSED" : "FAILED", description);
        }

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("Final Result: %d/%d Passed\n", passed, testCases.length);
    }

    /**
     * Helper method to build a tree from level-order array
     */
    private static TreeNode buildTree(Integer[] nodes) {
        if (nodes == null || nodes.length == 0 || nodes[0] == null) return null;
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode current = queue.poll();
            if (nodes[i] != null) {
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