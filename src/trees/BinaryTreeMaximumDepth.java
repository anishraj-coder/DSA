package trees;

import java.util.*;


public class BinaryTreeMaximumDepth {

    /**
     * Constraints:
     * The number of nodes in the tree is in the range [0, 10^4].
     * -100 <= Node.val <= 100
     */
    public int maxDepth(TreeNode root) {
        if(root==null)return 0;
        if(root.left==null&&root.right==null)return 1;
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        int level=0;
        while(!q.isEmpty()){
            int n=q.size();
            for(int i=0;i<n;i++){
                TreeNode rem=q.poll();
                if(rem.left!=null)q.offer(rem.left);
                if(rem.right!=null)q.offer(rem.right);
            }
            level++;
        }
        return level;
    }



    public static void main(String[] args) {
        BinaryTreeMaximumDepth processor = new BinaryTreeMaximumDepth();

        // Test Cases Setup
        // format: {Description, TreeNode root, Expected Integer}
        Object[][] testCases = {
                { "Example 1: Balanced Tree", buildTree(new Integer[]{3, 9, 20, null, null, 15, 7}), 3 },
                { "Example 2: Skewed Right", buildTree(new Integer[]{1, null, 2}), 2 },
                { "Empty Tree", buildTree(new Integer[]{}), 0 },
                { "Single Node", buildTree(new Integer[]{1}), 1 },
                { "Deeply Skewed Left", buildTree(new Integer[]{1, 2, null, 3, null, 4, null, 5}), 5 },
                { "Perfect Binary Tree (Height 3)", buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7}), 3 },
                { "Zig-Zag Path", buildTree(new Integer[]{1, 2, null, null, 3, 4, null, null, 5}), 5 },
                { "Large Negative Values", buildTree(new Integer[]{-10, -20, -30, null, null, -40, -50}), 3 },
                { "Only Leaves at Last Level", buildTree(new Integer[]{1, 2, 3, null, null, null, 4}), 3 },
                { "Unbalanced Sparse Tree", buildTree(new Integer[]{1, null, 2, null, 3, null, 4, null, 5}), 5 }
        };

        int passed = 0;
        System.out.println("--- Starting Test Suite: Maximum Depth ---");

        for (int i = 0; i < testCases.length; i++) {
            String description = (String) testCases[i][0];
            TreeNode root = (TreeNode) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = processor.maxDepth(root);
            boolean isMatch = (actual == expected);

            if (isMatch) passed++;

            System.out.printf("Test %d: %s\n", i + 1, description);
            System.out.println("   Expected Depth: " + expected);
            System.out.println("   Actual Depth:   " + actual);
            System.out.println("   Status:         " + (isMatch ? "PASSED ✅" : "FAILED ❌"));
            System.out.println("------------------------------------------");
        }

        System.out.printf("Final Result: %d/%d Passed\n", passed, testCases.length);
    }

    /**
     * Helper method to build a tree from level-order array
     */
    private static TreeNode buildTree(Integer[] vals) {
        if (vals == null || vals.length == 0 || vals[0] == null) return null;
        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < vals.length) {
            TreeNode curr = queue.poll();
            if (i < vals.length && vals[i] != null) {
                curr.left = new TreeNode(vals[i]);
                queue.add(curr.left);
            }
            i++;
            if (i < vals.length && vals[i] != null) {
                curr.right = new TreeNode(vals[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}