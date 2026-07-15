package trees;

import java.util.*;


public class BinaryTreeZigzagLevelOrderTraversal {

    /**
     * TO BE IMPLEMENTED
     * Returns the zigzag level order traversal of the tree.
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root==null)return new ArrayList<>();
        if(root.left==null&&root.right==null){
            List<Integer>temp=new ArrayList<>();
            temp.add(root.val);
            List<List<Integer>>ans=new ArrayList<>();
            ans.add(temp);
            return new ArrayList<>(ans);
        }
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        List<List<Integer>>ans=new ArrayList<>();
        int level=0;
        while(!q.isEmpty()){
            int n=q.size();
            List<Integer>temp=new ArrayList<>();
            for(int i=0;i<n;i++){
                TreeNode rem=q.poll();
                temp.add(rem.val);
                if(rem.left!=null)q.offer(rem.left);
                if(rem.right!=null)q.offer(rem.right);
            }
            if((level&1)!=0)Collections.reverse(temp);
            level++;
            ans.add(temp);
        }
        return ans;
    }

    public static void main(String[] args) {
        BinaryTreeZigzagLevelOrderTraversal instance = new BinaryTreeZigzagLevelOrderTraversal();

        // Test Case Definitions
        TestCase[] testCases = {
                new TestCase("Standard Tree",
                        buildTree(new Integer[]{3, 9, 20, null, null, 15, 7}),
                        Arrays.asList(Arrays.asList(3), Arrays.asList(20, 9), Arrays.asList(15, 7))),

                new TestCase("Single Node",
                        buildTree(new Integer[]{1}),
                        Arrays.asList(Arrays.asList(1))),

                new TestCase("Empty Tree",
                        buildTree(new Integer[]{}),
                        new ArrayList<>()),

                new TestCase("Skewed Left (Zigzag)",
                        buildTree(new Integer[]{1, 2, null, 3, null, 4, null}),
                        Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList(4))),

                new TestCase("Skewed Right (Zigzag)",
                        buildTree(new Integer[]{1, null, 2, null, 3, null, 4}),
                        Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList(4))),

                new TestCase("Perfect Binary Tree",
                        buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7}),
                        Arrays.asList(Arrays.asList(1), Arrays.asList(3, 2), Arrays.asList(4, 5, 6, 7))),

                new TestCase("Complete Binary Tree (Uneven)",
                        buildTree(new Integer[]{1, 2, 3, 4}),
                        Arrays.asList(Arrays.asList(1), Arrays.asList(3, 2), Arrays.asList(4))),

                new TestCase("Values with Negatives",
                        buildTree(new Integer[]{-1, -2, -3, 4, null, null, 5}),
                        Arrays.asList(Arrays.asList(-1), Arrays.asList(-3, -2), Arrays.asList(4, 5))),

                new TestCase("Deep Tree - Alternating Directions",
                        buildTree(new Integer[]{1, 2, 3, null, null, null, 4, 5, 6}),
                        Arrays.asList(Arrays.asList(1), Arrays.asList(3, 2), Arrays.asList(4), Arrays.asList(6, 5))),

                new TestCase("Large Root Constraints",
                        buildTree(new Integer[]{100, -100, 50, 0, 0, 0, 0}),
                        Arrays.asList(Arrays.asList(100), Arrays.asList(50, -100), Arrays.asList(0, 0, 0, 0)))
        };

        // Execution and Verification
        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            List<List<Integer>> actual = instance.zigzagLevelOrder(testCases[i].root);
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

    // Helper to build tree from Level Order Array
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
        List<List<Integer>> expected;

        TestCase(String d, TreeNode r, List<List<Integer>> e) {
            this.description = d;
            this.root = r;
            this.expected = e;
        }
    }
}