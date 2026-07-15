package trees;

import java.util.*;




public class BinaryTreeBoundaryTraversal {

    /**
     * TO BE IMPLEMENTED
     * Returns the boundary nodes of the binary tree in anti-clockwise order.
     * The order is: Root, Left Boundary (excluding leaves), Leaf Nodes, Right Boundary (in reverse, excluding leaves).
     */
    public List<Integer> boundaryTraversal(TreeNode root) {
        if(root==null)return new ArrayList<>();
        if(root.left==null&&root.right==null){
            List<Integer>ans=new ArrayList<>();
            ans.add(root.val);
            return ans;
        }
        List<Integer>res=new ArrayList<>();
        res.add(root.val);
        addLeft(root,res);
        addLeaf(root,res);
        addRight(root,res);
        return res;
    }

    private void addLeft(TreeNode root, List<Integer>res){
        TreeNode curr=root.left;
        while(curr!=null){
            if(!isLeaf(curr))res.add(curr.val);
            if(curr.left==null)curr=curr.right;
            else curr=curr.left;
        }
    }

    private void addRight(TreeNode root, List<Integer>res){
        TreeNode curr=root.right;
        List<Integer>temp=new ArrayList<>();
        while(curr!=null){
            if(!isLeaf(curr))temp.add(curr.val);
            if(curr.right!=null)curr=curr.right;
            else curr=curr.left;
        }
        for(int i=temp.size()-1;i>=0;i--){
            res.add(temp.get(i));
        }
    }

    private void addLeaf(TreeNode root, List<Integer> res){
        if(isLeaf(root)){
            res.add(root.val);
            return;
        }
        if(root.left!=null)addLeaf(root.left,res);
        if(root.right!=null)addLeaf(root.right,res);
    }

    private boolean isLeaf(TreeNode root){
        return root.left==null&&root.right==null;
    }

    public static void main(String[] args) {
        BinaryTreeBoundaryTraversal instance = new BinaryTreeBoundaryTraversal();

        // Test Case Definitions
        TestCase[] testCases = {
                new TestCase("Example 1: Standard Tree",
                        buildTree(new Integer[]{10, 5, 20, 3, 8, 18, 25, null, null, 7, null, null, null, null, null}),
                        Arrays.asList(10, 5, 3, 7, 18, 25, 20)),

                new TestCase("Example 2: Complex Tree",
                        buildTree(new Integer[]{100, 50, 150, 25, 75, 140, 200, null, 30, 70, 80, null, null, null, null, null, 35, null, null, null, null}),
                        Arrays.asList(100, 50, 25, 30, 35, 70, 80, 140, 200, 150)),

                new TestCase("Single Node Tree",
                        buildTree(new Integer[]{1}),
                        Arrays.asList(1)),

                new TestCase("Skewed Left Tree",
                        buildTree(new Integer[]{1, 2, null, 3, null, 4, null}),
                        Arrays.asList(1, 2, 3, 4)),

                new TestCase("Skewed Right Tree",
                        buildTree(new Integer[]{1, null, 2, null, 3, null, 4}),
                        Arrays.asList(1, 4, 3, 2)),

                new TestCase("Full Binary Tree",
                        buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7}),
                        Arrays.asList(1, 2, 4, 5, 6, 7, 3)),

                new TestCase("Zigzag Tree",
                        buildTree(new Integer[]{1, 2, null, null, 3, 4, null, null, 5}),
                        Arrays.asList(1, 2, 4, 5, 3)),

                new TestCase("Left Boundary missing (only root and right)",
                        buildTree(new Integer[]{1, null, 2, null, 3}),
                        Arrays.asList(1, 3, 2)),

                new TestCase("No Left Child but deep Left Boundary",
                        buildTree(new Integer[]{1, null, 2, 3, 4, 5, null, null, 6}),
                        Arrays.asList(1, 5, 6, 4, 2)),

                new TestCase("All leaves at different levels",
                        buildTree(new Integer[]{1, 2, 3, 4, null, null, 5, 6, 7, 8, 9}),
                        Arrays.asList(1, 2, 4, 6, 7, 8, 9, 5, 3))
        };

        // Execution and Verification
        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            List<Integer> actual = instance.boundaryTraversal(testCases[i].root);
            boolean isMatch = Objects.equals(testCases[i].expected, actual);

            System.out.println("Test Case " + (i + 1) + ": " + testCases[i].description);
            System.out.println("Expected: " + testCases[i].expected);
            System.out.println("Actual:   " + actual);

            if (isMatch) {
                System.out.println("Result:   PASSED");
                passed++;
            } else {
                System.out.println("Result:   FAILED");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Total Passed: " + passed + "/" + testCases.length);
    }

    // Helper to build tree from Level Order Array (Standard LeetCode format)
    private static TreeNode buildTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
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

    // Container for test cases
    static class TestCase {
        String description;
        TreeNode root;
        List<Integer> expected;

        TestCase(String d, TreeNode r, List<Integer> e) {
            this.description = d;
            this.root = r;
            this.expected = e;
        }
    }
}