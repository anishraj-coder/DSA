package trees;

import java.util.*;



public class BinaryTreeInverter {

    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    private void invert(TreeNode root){
        if(root==null)return;
        invert(root.left);
        invert(root.right);
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;
    }

    // --- Test Utility Methods ---

    public static void main(String[] args) {
        BinaryTreeInverter inverter = new BinaryTreeInverter();
        int passed = 0;
        int totalTests = 10;

        // Test Cases
        Object[][] testCases = {
                { "Example 1: Standard Tree", new Integer[]{4, 2, 7, 1, 3, 6, 9}, new Integer[]{4, 7, 2, 9, 6, 3, 1} },
                { "Example 2: Small Tree", new Integer[]{2, 1, 3}, new Integer[]{2, 3, 1} },
                { "Example 3: Empty Tree", new Integer[]{}, new Integer[]{} },
                { "Single Node", new Integer[]{1}, new Integer[]{1} },
                { "Left-Skewed Tree", new Integer[]{1, 2, null, 3}, new Integer[]{1, null, 2, null, 3} },
                { "Right-Skewed Tree", new Integer[]{1, null, 2, null, 3}, new Integer[]{1, 2, null, 3} },
                { "Tree with Negative Values", new Integer[]{0, -1, 1}, new Integer[]{0, 1, -1} },
                { "Perfect Binary Tree", new Integer[]{1, 2, 3, 4, 5, 6, 7}, new Integer[]{1, 3, 2, 7, 6, 5, 4} },
                { "Unbalanced Tree", new Integer[]{5, 2, null, 1, null}, new Integer[]{5, null, 2, null, 1} },
                { "Deep Tree", new Integer[]{1, 2, 2, 3, null, null, 3, 4, null, null, 4}, new Integer[]{1, 2, 2, 3, null, null, 3, null, 4, 4, null} }
        };

        for (int i = 0; i < testCases.length; i++) {
            String description = (String) testCases[i][0];
            TreeNode root = buildTree((Integer[]) testCases[i][1]);
            List<Integer> expected = Arrays.asList((Integer[]) testCases[i][2]);

            TreeNode resultNode = inverter.invertTree(root);
            List<Integer> actual = serialize(resultNode);

            System.out.println("Test " + (i + 1) + ": " + description);
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);

            if (expected.equals(actual)) {
                System.out.println("Result:   PASSED\n");
                passed++;
            } else {
                System.out.println("Result:   FAILED\n");
            }
        }

        System.out.println("Final Score: " + passed + "/" + totalTests);
    }

    private static TreeNode buildTree(Integer[] vals) {
        if (vals == null || vals.length == 0) return null;
        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < vals.length) {
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

    private static List<Integer> serialize(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                result.add(null);
                continue;
            }
            result.add(node.val);
            queue.add(node.left);
            queue.add(node.right);
        }
        // Remove trailing nulls for standard representation
        while (!result.isEmpty() && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }
        return result;
    }
}