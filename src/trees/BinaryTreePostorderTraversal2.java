package trees;


import java.util.*;

public class BinaryTreePostorderTraversal2 {

    /**
     * Constraints:
     * The number of nodes in the tree is in the range [0, 100].
     * -100 <= Node.val <= 100
     * * Follow up: Recursive solution is trivial, could you do it iteratively?
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st=new Stack<>();
        TreeNode node=root;
        while(!st.isEmpty()||node!=null){
            if(node !=null){
                st.push(node);
                node=node.left;
            }else{
                TreeNode temp=st.peek().right;
                if(temp==null){
                    temp=st.pop();
                    ans.add(temp.val);
                    while(!st.isEmpty()&&temp==st.peek().right){
                        temp=st.pop();
                        ans.add(temp.val);
                    }
                }else node=temp;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        BinaryTreePostorderTraversal2 processor = new BinaryTreePostorderTraversal2();

        // Test Cases
        Object[][] testCases = {
                {"Example 1: Skewed Right", buildTree(new Integer[]{1, null, 2, 3}), Arrays.asList(3, 2, 1)},
                {"Example 2: Complex Tree", buildTree(new Integer[]{1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9}), Arrays.asList(4, 6, 7, 5, 2, 9, 8, 3, 1)},
                {"Example 3: Empty Tree", buildTree(new Integer[]{}), Arrays.asList()},
                {"Example 4: Single Node", buildTree(new Integer[]{1}), Arrays.asList(1)},
                {"Edge Case: Full Binary Tree", buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7}), Arrays.asList(4, 5, 2, 6, 7, 3, 1)},
                {"Edge Case: Left Skewed", buildTree(new Integer[]{1, 2, null, 3, null, 4}), Arrays.asList(4, 3, 2, 1)},
                {"Edge Case: Negative Values", buildTree(new Integer[]{-1, -2, -3}), Arrays.asList(-2, -3, -1)},
                {"Edge Case: Zig-Zag", buildTree(new Integer[]{1, 2, null, null, 3, 4}), Arrays.asList(4, 3, 2, 1)},
                {"Large Random: Depth 4", buildTree(new Integer[]{10, 5, 15, 2, 7, 12, 20, 1}), Arrays.asList(1, 2, 7, 5, 12, 20, 15, 10)},
                {"Symmetric Tree", buildTree(new Integer[]{1, 2, 2, 3, 4, 4, 3}), Arrays.asList(3, 4, 2, 4, 3, 2, 1)}
        };

        int passed = 0;
        System.out.println("--- Starting Test Suite ---");

        for (int i = 0; i < testCases.length; i++) {
            String description = (String) testCases[i][0];
            TreeNode root = (TreeNode) testCases[i][1];
            List<Integer> expected = (List<Integer>) testCases[i][2];

            List<Integer> actual = processor.postorderTraversal(root);

            boolean isMatch = Objects.equals(expected, actual);
            if (isMatch) passed++;

            System.out.printf("Test %d: %s\n", i + 1, description);
            System.out.println("   Expected: " + expected);
            System.out.println("   Actual:   " + actual);
            System.out.println("   Status:   " + (isMatch ? "PASSED ✅" : "FAILED ❌"));
            System.out.println("---------------------------");
        }

        System.out.printf("Final Result: %d/%d Passed\n", passed, testCases.length);
    }

    /**
     * Helper method to build a tree from level-order array (standard LeetCode format)
     */
    private static TreeNode buildTree(Integer[] vals) {
        if (vals == null || vals.length == 0) return null;
        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < vals.length) {
            TreeNode curr = queue.poll();
            if (vals[i] != null) {
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
