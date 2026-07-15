package trees;

public class ChildrenSumBinaryTree {

    /**
     * Checks if the given binary tree satisfies the Children Sum Property.
     * Each non-leaf node must equal the sum of its left and right children.
     * * @param root The root of the binary tree.
     * @return true if the property is satisfied, false otherwise.
     */
    public boolean isSumProperty(TreeNode root) {
        boolean[]ans=new boolean[1];
        ans[0]=true;
        check(root,ans);
        return ans[0];
    }

    private int check(TreeNode root,boolean[]ans){
        if(root==null)return 0;

        if(root.left==null&&root.right==null)return root.val;
        int left=check(root.left,ans);
        int right=check(root.right,ans);
        if(root.val!=left+right)ans[0]=false;
        return root.val;
    }

    public static void main(String[] args) {
        ChildrenSumBinaryTree solver = new ChildrenSumBinaryTree();
        int passed = 0;

        // Test Case Definitions
        TestCase[] cases = {
                // 1. Standard True Case
                new TestCase(buildTree(new Integer[]{35, 20, 15, 15, 5, 10, 5}), true, "Balanced tree - True"),

                // 2. Simple Root Mismatch
                new TestCase(buildTree(new Integer[]{1, 4, 3, 5}), false, "Root (1) != 4 + 3 - False"),

                // 3. Single Leaf Node
                new TestCase(buildTree(new Integer[]{10}), true, "Single node - True"),

                // 4. Null Root
                new TestCase(null, true, "Empty tree - True"),

                // 5. Left Child Only (Valid)
                new TestCase(buildTree(new Integer[]{10, 10, null}), true, "Only left child (10=10) - True"),

                // 6. Right Child Only (Invalid)
                new TestCase(buildTree(new Integer[]{10, null, 8}), false, "Only right child (10!=8) - False"),

                // 7. Large Data Values
                new TestCase(buildTree(new Integer[]{100000, 50000, 50000}), true, "Large values - True"),

                // 8. Skewed Tree (True)
                new TestCase(buildTree(new Integer[]{20, 20, null, 20, null, 20}), true, "Deeply skewed - True"),

                // 9. All Zeros
                new TestCase(buildTree(new Integer[]{0, 0, 0, 0, 0}), true, "All nodes zero - True"),

                // 10. Deep Violation
                // Structure: 50 -> (20, 30). 20 -> (10, 10). 30 -> (15, 15).
                // Then one leaf of 15 is 10 and 2 (Sum 12 != 15)
                new TestCase(buildTree(new Integer[]{50, 20, 30, 10, 10, 15, 15, null, null, null, null, 10, 2}), false, "Deep level mismatch - False")
        };

        System.out.println(String.format("%-40s | %-10s | %-10s | %-10s", "Description", "Expected", "Actual", "Result"));
        System.out.println("------------------------------------------------------------------------------------------");

        for (TestCase tc : cases) {
            boolean actual = solver.isSumProperty(tc.root);
            String result = (actual == tc.expected) ? "PASSED" : "FAILED";
            if (actual == tc.expected) passed++;

            System.out.println(String.format("%-40s | %-10b | %-10b | %-10s",
                    tc.description, tc.expected, actual, result));
        }

        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Final Result: " + passed + "/" + cases.length + " cases passed.");
    }

    static class TestCase {
        TreeNode root;
        boolean expected;
        String description;

        TestCase(TreeNode root, boolean expected, String description) {
            this.root = root;
            this.expected = expected;
            this.description = description;
        }
    }

    private static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) return null;
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