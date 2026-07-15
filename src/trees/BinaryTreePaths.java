package trees;

import java.util.*;



public class BinaryTreePaths {

    /**
     * Complete the function below to return all root-to-leaf paths.
     */
    public List<String> binaryTreePaths(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        List<String>ans=new ArrayList<>();
        helper(root,sb,ans);
        return ans;
    }

    private void helper(TreeNode root,StringBuilder curr,List<String>ans){
        if(root==null)return;

        int length=curr.length();
        curr.append(root.val);
        if(root.left==null&&root.right==null){
            ans.add(curr.toString());
        }else{
            curr.append("->");
            helper(root.left,curr,ans);
            helper(root.right,curr,ans);
        }

        curr.setLength(length);
    }

    public static void main(String[] args) {
        BinaryTreePaths solver = new BinaryTreePaths();

        // Test Cases
        TestCase[] testCases = {
                // Case 1: Standard Tree (Example 1)
                new TestCase(
                        buildTree(new Integer[]{1, 2, 3, null, 5}),
                        Arrays.asList("1->2->5", "1->3"),
                        "Standard tree with multiple paths"
                ),

                // Case 2: Single Node (Example 2)
                new TestCase(
                        buildTree(new Integer[]{1}),
                        Arrays.asList("1"),
                        "Single node tree"
                ),

                // Case 3: Strictly Left Skewed
                new TestCase(
                        buildTree(new Integer[]{1, 2, null, 3, null, 4}),
                        Arrays.asList("1->2->3->4"),
                        "Left skewed tree (Single path)"
                ),

                // Case 4: Strictly Right Skewed
                new TestCase(
                        buildTree(new Integer[]{1, null, 2, null, 3, null, 4}),
                        Arrays.asList("1->2->3->4"),
                        "Right skewed tree (Single path)"
                ),

                // Case 5: Full Binary Tree
                new TestCase(
                        buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7}),
                        Arrays.asList("1->2->4", "1->2->5", "1->3->6", "1->3->7"),
                        "Full binary tree (All paths)"
                ),

                // Case 6: Negative Values
                new TestCase(
                        buildTree(new Integer[]{-1, -2, -3, null, -5}),
                        Arrays.asList("-1->-2->-5", "-1->-3"),
                        "Tree with negative values"
                ),

                // Case 7: Path with zero
                new TestCase(
                        buildTree(new Integer[]{0, 1, 0}),
                        Arrays.asList("0->1", "0->0"),
                        "Tree containing zeros"
                ),

                // Case 8: Zig-zag Tree
                new TestCase(
                        buildTree(new Integer[]{1, 2, null, null, 3, 4, null}),
                        Arrays.asList("1->2->3->4"),
                        "Zig-zag structure"
                ),

                // Case 9: Large values (boundary)
                new TestCase(
                        buildTree(new Integer[]{100, -100, 50}),
                        Arrays.asList("100->-100", "100->50"),
                        "Nodes with maximum/minimum constraint values"
                ),

                // Case 10: Deep tree
                new TestCase(
                        buildTree(new Integer[]{1, 2, 2, 3, 3, 3, 3, 4, 4}),
                        Arrays.asList("1->2->3->4", "1->2->3->4", "1->2->3", "1->2->3"),
                        "Deep tree with multiple identical path values"
                )
        };

        int passedCount = 0;
        for (int i = 0; i < testCases.length; i++) {
            List<String> actual = solver.binaryTreePaths(testCases[i].root);

            // Sort both for comparison since order doesn't matter
            Collections.sort(actual);
            List<String> expected = new ArrayList<>(testCases[i].expected);
            Collections.sort(expected);

            boolean isPassed = actual.equals(expected);
            if (isPassed) passedCount++;

            System.out.printf("Test Case %d: %s\n", i + 1, testCases[i].description);
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
            System.out.println("Result:   " + (isPassed ? "PASSED ✅" : "FAILED ❌"));
            System.out.println("--------------------------------------------------");
        }

        System.out.printf("Final Result: %d/%d Passed\n", passedCount, testCases.length);
    }

    // Helper class for test cases
    static class TestCase {
        TreeNode root;
        List<String> expected;
        String description;

        TestCase(TreeNode root, List<String> expected, String description) {
            this.root = root;
            this.expected = expected;
            this.description = description;
        }
    }

    // Helper method to build tree from Level-Order array
    public static TreeNode buildTree(Integer[] nodes) {
        if (nodes == null || nodes.length == 0 || nodes[0] == null) return null;
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < nodes.length) {
            TreeNode curr = queue.poll();
            if (i < nodes.length && nodes[i] != null) {
                curr.left = new TreeNode(nodes[i]);
                queue.add(curr.left);
            }
            i++;
            if (i < nodes.length && nodes[i] != null) {
                curr.right = new TreeNode(nodes[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}