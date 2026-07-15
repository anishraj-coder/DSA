package trees;

import java.util.*;


public class BinaryTreePreorderTraversal {

    /**
     * Follow-up: Try to implement this iteratively!
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root==null)return new ArrayList<>();
        if(root.left==null&&root.right==null){
            List<Integer>ans=new ArrayList<>();
            ans.add(root.val);
            return ans;
        }
        List<Integer>ans=new ArrayList<>();
        Stack<TreeNode>st=new Stack<>();
        st.push(root);
        while(!st.isEmpty()){
            TreeNode rem=st.pop();
            ans.add(rem.val);
            if(rem.right!=null)st.push(rem.right);
            if(rem.left!=null)st.push(rem.left);
        }
        return ans;
    }



    public static void main(String[] args) {
        BinaryTreePreorderTraversal solver = new BinaryTreePreorderTraversal();

        // Test Cases Data
        Object[][] testCases = {
                { "Example 1 (Right Skewed)", buildTree(new Integer[]{1, null, 2, 3}), Arrays.asList(1, 2, 3) },
                { "Example 2 (Complex Tree)", buildTree(new Integer[]{1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9}), Arrays.asList(1, 2, 4, 5, 6, 7, 3, 8, 9) },
                { "Empty Tree", buildTree(new Integer[]{}), Collections.emptyList() },
                { "Single Node", buildTree(new Integer[]{1}), Arrays.asList(1) },
                { "Full Binary Tree", buildTree(new Integer[]{1, 2, 3}), Arrays.asList(1, 2, 3) },
                { "Left Skewed", buildTree(new Integer[]{5, 4, null, 3, null, 2, null, 1}), Arrays.asList(5, 4, 3, 2, 1) },
                { "Negative Values", buildTree(new Integer[]{-1, -2, -3}), Arrays.asList(-1, -2, -3) },
                { "Zig-Zag Tree", buildTree(new Integer[]{1, 2, null, null, 3, 4}), Arrays.asList(1, 2, 3, 4) },
                { "Complete Binary Tree", buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7}), Arrays.asList(1, 2, 4, 5, 3, 6, 7) },
                { "Large Unbalanced", buildTree(new Integer[]{10, 5, 15, null, null, 12, 20, 11}), Arrays.asList(10, 5, 15, 12, 11, 20) }
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            String description = (String) testCases[i][0];
            TreeNode root = (TreeNode) testCases[i][1];
            List<Integer> expected = (List<Integer>) testCases[i][2];

            List<Integer> actual = solver.preorderTraversal(root);

            boolean isMatch = actual.equals(expected);
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
     * Helper method to build a tree from a level-order array (standard LeetCode format)
     */
    private static TreeNode buildTree(Integer[] nodes) {
        if (nodes == null || nodes.length == 0) return null;
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < nodes.length) {
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