package trees;

import java.util.*;


public class BinaryTreeInorderTraversal {

    /**
     * Follow-up: Try to implement this iteratively using a Stack!
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root==null)return new ArrayList<>();
        if(root.left==null&&root.right==null)return new ArrayList<>(Arrays.asList(root.val));
        TreeNode curr=root;
        Stack<TreeNode>st=new Stack<>();
        while(curr!=null){
            st.push(curr);
            curr=curr.left;
        }
        List<Integer>ans=new ArrayList<>();
        while(!st.isEmpty()){
            TreeNode rem=st.pop(),right=rem.right;
            ans.add(rem.val);
            while(right!=null){
                st.push(right);
                right=right.left;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        BinaryTreeInorderTraversal solver = new BinaryTreeInorderTraversal();

        // Test Cases Data
        Object[][] testCases = {
                { "Standard Right-Leaning", buildTree(new Integer[]{1, null, 2, 3}), Arrays.asList(1, 3, 2) },
                { "Complex Unbalanced", buildTree(new Integer[]{1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9}), Arrays.asList(4, 2, 6, 5, 7, 1, 3, 9, 8) },
                { "Empty Tree", buildTree(new Integer[]{}), Collections.emptyList() },
                { "Single Node", buildTree(new Integer[]{1}), Arrays.asList(1) },
                { "Full Binary Tree (H=2)", buildTree(new Integer[]{1, 2, 3}), Arrays.asList(2, 1, 3) },
                { "Left Skewed (Deep)", buildTree(new Integer[]{5, 4, null, 3, null, 2, null, 1}), Arrays.asList(1, 2, 3, 4, 5) },
                { "Right Skewed (Deep)", buildTree(new Integer[]{1, null, 2, null, 3, null, 4, null, 5}), Arrays.asList(1, 2, 3, 4, 5) },
                { "Complete Binary Tree", buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7}), Arrays.asList(4, 2, 5, 1, 6, 3, 7) },
                { "Zig-Zag structure", buildTree(new Integer[]{1, 2, null, null, 3, 4, null}), Arrays.asList(2, 4, 3, 1) },
                { "Symmetric Tree", buildTree(new Integer[]{1, 2, 2, 3, 4, 4, 3}), Arrays.asList(3, 2, 4, 1, 4, 2, 3) }
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            String description = (String) testCases[i][0];
            TreeNode root = (TreeNode) testCases[i][1];
            List<Integer> expected = (List<Integer>) testCases[i][2];

            List<Integer> actual = solver.inorderTraversal(root);

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
     * Helper method to build a tree from a level-order array
     */
    private static TreeNode buildTree(Integer[] nodes) {
        if (nodes == null || nodes.length == 0) return null;
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < nodes.length) {
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