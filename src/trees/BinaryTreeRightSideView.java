package trees;

import java.util.*;

public class BinaryTreeRightSideView {

    /**
     * Imagine yourself standing on the right side of the tree.
     * Return the values of the nodes you can see ordered from top to bottom.
     * * @param root The root of the binary tree.
     * @return A list of integers visible from the right side.
     */
    public List<Integer> rightSideView(TreeNode root) {
        if(root==null)return new ArrayList<>();
        HashMap<Integer,Integer>map=new HashMap<>();
        int level=0;
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int n=q.size();
            for(int i=0;i<n;i++){
                TreeNode rem=q.poll();
                map.put(level,rem.val);
                if(rem.left!=null)q.offer(rem.left);
                if(rem.right!=null)q.offer(rem.right);
            }
            level++;
        }
        List<Integer>ans=new ArrayList<>();
        for(int i=0;i<level;i++){
            ans.add(map.get(i));
        }
        return ans;
    }

    public static void main(String[] args) {
        BinaryTreeRightSideView solver = new BinaryTreeRightSideView();
        int passed = 0;
        int total = 0;

        // Test Case 1: Example 1
        // [1, 2, 3, null, 5, null, 4]
        TreeNode tc1 = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(5)),
                new TreeNode(3, null, new TreeNode(4)));
        List<Integer> ex1 = Arrays.asList(1, 3, 4);
        if (runTest(1, solver.rightSideView(tc1), ex1)) passed++; total++;

        // Test Case 2: Example 2 (Left subtree deeper than right)
        // [1, 2, 3, 4, null, null, null, 5]
        TreeNode tc2 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4, new TreeNode(5), null), null),
                new TreeNode(3));
        List<Integer> ex2 = Arrays.asList(1, 3, 4, 5);
        if (runTest(2, solver.rightSideView(tc2), ex2)) passed++; total++;

        // Test Case 3: Example 3
        // [1, null, 3]
        TreeNode tc3 = new TreeNode(1, null, new TreeNode(3));
        List<Integer> ex3 = Arrays.asList(1, 3);
        if (runTest(3, solver.rightSideView(tc3), ex3)) passed++; total++;

        // Test Case 4: Empty Tree (Edge Case)
        List<Integer> ex4 = new ArrayList<>();
        if (runTest(4, solver.rightSideView(null), ex4)) passed++; total++;

        // Test Case 5: Single Node
        TreeNode tc5 = new TreeNode(1);
        List<Integer> ex5 = Arrays.asList(1);
        if (runTest(5, solver.rightSideView(tc5), ex5)) passed++; total++;

        // Test Case 6: Fully Left Skewed (Every node should be visible)
        TreeNode tc6 = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4), null), null), null);
        List<Integer> ex6 = Arrays.asList(1, 2, 3, 4);
        if (runTest(6, solver.rightSideView(tc6), ex6)) passed++; total++;

        // Test Case 7: Full Binary Tree
        TreeNode tc7 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), new TreeNode(7)));
        List<Integer> ex7 = Arrays.asList(1, 3, 7);
        if (runTest(7, solver.rightSideView(tc7), ex7)) passed++; total++;

        // Test Case 8: Zig-zag Tree
        // 1 -> L:2 -> R:3 -> L:4
        TreeNode tc8 = new TreeNode(1, new TreeNode(2, null, new TreeNode(3, new TreeNode(4), null)), null);
        List<Integer> ex8 = Arrays.asList(1, 2, 3, 4);
        if (runTest(8, solver.rightSideView(tc8), ex8)) passed++; total++;

        // Test Case 9: Right subtree exists but is shorter than left
        //       1
        //      / \
        //     2   3
        //    /
        //   4
        TreeNode tc9 = new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3));
        List<Integer> ex9 = Arrays.asList(1, 3, 4);
        if (runTest(9, solver.rightSideView(tc9), ex9)) passed++; total++;

        // Test Case 10: Nodes with negative and zero values
        TreeNode tc10 = new TreeNode(0, new TreeNode(-1), new TreeNode(-2, new TreeNode(100), null));
        List<Integer> ex10 = Arrays.asList(0, -2, 100);
        if (runTest(10, solver.rightSideView(tc10), ex10)) passed++; total++;

        // Test Case 11: Complex overlap
        //       1
        //      / \
        //     2   5
        //    / \
        //   3   4
        TreeNode tc11 = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5));
        List<Integer> ex11 = Arrays.asList(1, 5, 4);
        if (runTest(11, solver.rightSideView(tc11), ex11)) passed++; total++;

        System.out.println("\n---------------------------------------");
        System.out.println("Final Results: " + passed + "/" + total + " Passed");
        System.out.println("---------------------------------------");
    }

    private static boolean runTest(int id, List<Integer> actual, List<Integer> expected) {
        boolean isMatch = Objects.equals(actual, expected);
        System.out.println("Test Case " + id + ": " + (isMatch ? "PASSED" : "FAILED"));
        if (!isMatch) {
            System.out.println("   Expected: " + expected);
            System.out.println("   Actual:   " + (actual == null ? "null" : actual));
        }
        return isMatch;
    }
}