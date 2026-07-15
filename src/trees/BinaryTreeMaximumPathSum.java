package trees;

import java.util.*;


public class BinaryTreeMaximumPathSum {

    /**
     * Complete the function below.
     * Logic: A path can be formed by (left subtree + node + right subtree),
     * but the value returned to the parent can only include one branch.
     */
    public int maxPathSum(TreeNode root) {
        if(root==null)return 0;
        int[]max=new int[1];
        max[0]=Integer.MIN_VALUE;
        helper(root,max);
        return max[0];
    }

    private int helper(TreeNode root,int[]max){
        if(root==null)return 0;
        int left=Math.max(0,helper(root.left,max));
        int right=Math.max(0,helper(root.right,max));
        max[0]=Math.max(max[0],left+right+root.val);
        return Math.max(left,right)+root.val;
    }

    public static void main(String[] args) {
        BinaryTreeMaximumPathSum solver = new BinaryTreeMaximumPathSum();

        Object[][] testCases = {
                { new Integer[]{1, 2, 3}, 6, "Simple balanced tree" },
                { new Integer[]{-10, 9, 20, null, null, 15, 7}, 42, "Large values with negatives" },
                { new Integer[]{-3}, -3, "Single negative node" },
                { new Integer[]{2, -1}, 2, "Root with one negative child" },
                { new Integer[]{1, -2, -3, 1, 3, -2, null}, 3, "Path in a sub-branch" },
                { new Integer[]{-2, 1}, 1, "Negative root, positive child" },
                { new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1}, 48, "Complex path through multiple levels" },
                { new Integer[]{-10, -20, -30}, -10, "All negative values (should return max node)" },
                { new Integer[]{1, 2, null, 3, null, 4, null, 5}, 15, "Skewed tree (Linear path)" },
                { new Integer[]{10, 2, 10, 20, 1, null, -25, null, null, null, null, 3, 4}, 42, "Path avoiding deep negative branches" }
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            Integer[] treeArray = (Integer[]) testCases[i][0];
            int expected = (int) testCases[i][1];
            String description = (String) testCases[i][2];

            TreeNode root = buildTree(treeArray);
            int actual = solver.maxPathSum(root);

            boolean isCorrect = actual == expected;
            if (isCorrect) passed++;

            System.out.printf("Test Case %d: %s\n", i + 1, description);
            System.out.printf("   Expected: %d | Actual: %d\n", expected, actual);
            System.out.println("   Result: " + (isCorrect ? "PASSED ✅" : "FAILED ❌"));
            System.out.println("-----------------------------------------");
        }

        System.out.printf("Final Score: %d/%d\n", passed, testCases.length);
    }

    // Helper method to build tree from Level-order array
    private static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < arr.length) {
            TreeNode curr = queue.poll();
            if (i < arr.length && arr[i] != null) {
                curr.left = new TreeNode(arr[i]);
                queue.add(curr.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                curr.right = new TreeNode(arr[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}