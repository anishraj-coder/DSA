package trees;



public class BinaryTreeDiameter {

    /**
     * Computes the diameter of the binary tree.
     * The diameter is the length of the longest path between any two nodes.
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null)return 0;
        int[]ans=new int[1];
        height(root,ans);
        return ans[0];
    }

    private int height(TreeNode root,int[]max){
        if(root==null)return 0;
        int left=height(root.left,max);
        int right=height(root.right,max);
        max[0]=Math.max(max[0],left+right);
        return Math.max(left,right)+1;
    }

    public static void main(String[] args) {
        BinaryTreeDiameter solver = new BinaryTreeDiameter();

        // Test Case Definitions
        TestCase[] cases = {
                // Case 1: Standard balanced tree
                new TestCase("Example 1: Balanced Tree", buildTree(new Integer[]{1, 2, 3, 4, 5}), 3),

                // Case 2: Simple two-node tree
                new TestCase("Example 2: Two Nodes", buildTree(new Integer[]{1, 2}), 1),

                // Case 3: Single node (Edge case)
                new TestCase("Single Node", buildTree(new Integer[]{1}), 0),

                // Case 4: Skewed Left Tree
                new TestCase("Left Skewed", buildTree(new Integer[]{1, 2, null, 3, null, 4}), 3),

                // Case 5: Skewed Right Tree
                new TestCase("Right Skewed", buildTree(new Integer[]{1, null, 2, null, 3, null, 4}), 3),

                // Case 6: Diameter does NOT pass through root (Long side branch)
                new TestCase("Diameter not through root",
                        buildTree(new Integer[]{1, 2, 3, 4, 5, null, null, 6, null, null, 7, 8, null, null, 9}), 6),

                // Case 7: Large "V" shape
                new TestCase("V Shape", buildTree(new Integer[]{1, 2, 3, 4, null, null, 5}), 4),

                // Case 8: Zig-zag path
                new TestCase("Zig-Zag", buildTree(new Integer[]{1, 2, null, null, 3, 4}), 3),

                // Case 9: Perfect Binary Tree (Height 3)
                new TestCase("Perfect Tree", buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7}), 4),

                // Case 10: Null Root (Edge case)
                new TestCase("Null Root", null, 0)
        };

        int passed = 0;
        System.out.println(String.format("%-30s | %-10s | %-10s | %-10s", "Test Name", "Expected", "Actual", "Result"));
        System.out.println("-------------------------------------------------------------------------------");

        for (TestCase tc : cases) {
            int actual = solver.diameterOfBinaryTree(tc.root);
            boolean isPass = (actual == tc.expected);
            if (isPass) passed++;

            System.out.println(String.format("%-30s | %-10d | %-10d | %-10s",
                    tc.name, tc.expected, actual, isPass ? "PASSED" : "FAILED"));
        }

        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Final Score: " + passed + "/" + cases.length);
    }

    // Helper class for test cases
    static class TestCase {
        String name;
        TreeNode root;
        int expected;

        TestCase(String name, TreeNode root, int expected) {
            this.name = name;
            this.root = root;
            this.expected = expected;
        }
    }

    // Level-order tree builder for convenience
    private static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0) return null;
        TreeNode root = new TreeNode(arr[0]);
        java.util.Queue<TreeNode> q = new java.util.LinkedList<>();
        q.add(root);
        int i = 1;
        while (i < arr.length) {
            TreeNode curr = q.poll();
            if (i < arr.length && arr[i] != null) {
                curr.left = new TreeNode(arr[i]);
                q.add(curr.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                curr.right = new TreeNode(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }
}