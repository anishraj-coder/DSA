package trees;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {

    /**
     * Given the root of a binary tree, return the level order traversal
     * of its nodes' values. (i.e., from left to right, level by level).
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null)return new ArrayList<>();
        List<List<Integer>>ans=new ArrayList<>();
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int n=q.size();
            List<Integer>level=new ArrayList<>();
            for(int i=0;i<n;i++){
                TreeNode rem=q.poll();
                level.add(rem.val);
                if(rem.left!=null)q.offer(rem.left);
                if(rem.right!=null)q.offer(rem.right);
            }
            ans.add(level);
        }
        return ans;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal solver = new BinaryTreeLevelOrderTraversal();

        // Test Cases Data
        Object[][] testCases = {
                { "Standard Balanced", new Integer[]{3, 9, 20, null, null, 15, 7},
                        Arrays.asList(Arrays.asList(3), Arrays.asList(9, 20), Arrays.asList(15, 7)) },

                { "Single Node", new Integer[]{1},
                        Arrays.asList(Arrays.asList(1)) },

                { "Empty Tree", new Integer[]{},
                        Collections.emptyList() },

                { "Full Binary Tree", new Integer[]{1, 2, 3, 4, 5, 6, 7},
                        Arrays.asList(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6, 7)) },

                { "Left Skewed", new Integer[]{1, 2, null, 3, null, 4},
                        Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList(4)) },

                { "Right Skewed", new Integer[]{1, null, 2, null, 3, null, 4},
                        Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList(4)) },

                { "Zig-Zag", new Integer[]{1, 2, 3, null, 4, 5, null},
                        Arrays.asList(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5)) },

                { "Nodes with 0 and Negatives", new Integer[]{0, -1, 5, null, 8, -9},
                        Arrays.asList(Arrays.asList(0), Arrays.asList(-1, 5), Arrays.asList(8, -9)) },

                { "Asymmetric sparse", new Integer[]{1, null, 2, null, null, 3, 4},
                        Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3, 4)) },

                { "Deep Right Skew with Large Values", new Integer[]{100, null, 100, null, 100},
                        Arrays.asList(Arrays.asList(100), Arrays.asList(100), Arrays.asList(100)) }
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            String description = (String) testCases[i][0];
            Integer[] inputData = (Integer[]) testCases[i][1];
            List<List<Integer>> expected = (List<List<Integer>>) testCases[i][2];

            TreeNode root = buildTree(inputData);
            List<List<Integer>> actual = solver.levelOrder(root);

            boolean isMatch = Objects.equals(actual, expected);
            if (isMatch) passed++;

            System.out.println("Test Case " + (i + 1) + ": " + description);
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
            System.out.println("Result:   " + (isMatch ? "PASSED" : "FAILED"));
            System.out.println("----------------------------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + testCases.length + " test cases passed.");
    }

    /**
     * Fixed Helper: Safely builds tree from LeetCode-style level order array.
     */
    private static TreeNode buildTree(Integer[] nodes) {
        if (nodes == null || nodes.length == 0 || nodes[0] == null) return null;

        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode curr = queue.poll();

            // Handle Left Child
            if (i < nodes.length && nodes[i] != null) {
                curr.left = new TreeNode(nodes[i]);
                queue.add(curr.left);
            }
            i++;

            // Handle Right Child
            if (i < nodes.length && nodes[i] != null) {
                curr.right = new TreeNode(nodes[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}