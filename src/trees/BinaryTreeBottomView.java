package trees;

import java.util.*;



public class BinaryTreeBottomView {

    /**
     * Finds the bottom view of the binary tree from left to right.
     * A node is in the bottom-view if it is the bottom-most node at its
     * horizontal distance. If multiple nodes are at the same depth and
     * distance, the one further to the right is chosen.
     * * @param root The root of the binary tree.
     * @return A list of integers representing the bottom view.
     */
    public List<Integer> getBottomView(TreeNode root) {
        if(root==null)return new ArrayList<>();

        Map<Integer,Integer>map=new HashMap<>();
        Queue<Pair>q=new LinkedList<>();
        q.offer(new Pair(root,0));
        int max=0,min=0;
        while(!q.isEmpty()){
            Pair rem=q.poll();
            int pos=rem.pos;
            TreeNode node=rem.node;
            max=Math.max(max,pos);
            min=Math.min(min,pos);
            map.put(pos,node.val);
            if(node.left!=null)q.offer(new Pair(node.left,pos-1));
            if(node.right!=null)q.offer(new Pair(node.right,pos+1));
        }
        List<Integer>ans=new ArrayList<>();
        for(int i=min;i<=max;i++){
            ans.add(map.get(i));
        }
        return ans;
    }

    private class Pair{
        TreeNode node;
        int pos;
        Pair(TreeNode node,int pos){
            this.node=node;
            this.pos=pos;
        }
    }

    public static void main(String[] args) {
        BinaryTreeBottomView solver = new BinaryTreeBottomView();
        int passed = 0;
        int total = 0;

        // Test Case 1: Example from problem description
        //       1
        //     /   \
        //    2     3
        //   / \   / \
        //  4   5 6   7
        TreeNode tc1 = new TreeNode(1);
        tc1.left = new TreeNode(2); tc1.right = new TreeNode(3);
        tc1.left.left = new TreeNode(4); tc1.left.right = new TreeNode(5);
        tc1.right.left = new TreeNode(6); tc1.right.right = new TreeNode(7);
        // Col -2: 4, Col -1: 2, Col 0: 6 (replaces 5), Col 1: 3, Col 2: 7
        List<Integer> ex1 = Arrays.asList(4, 2, 6, 3, 7);
        if (runTest(1, solver.getBottomView(tc1), ex1)) passed++; total++;

        // Test Case 2: Sample Input 1
        TreeNode tc2 = new TreeNode(1);
        tc2.left = new TreeNode(2); tc2.right = new TreeNode(3);
        tc2.right.left = new TreeNode(5); tc2.right.right = new TreeNode(6);
        tc2.right.left.left = new TreeNode(7); tc2.right.left.right = new TreeNode(8);
        List<Integer> ex2 = Arrays.asList(7, 5, 8, 6);
        if (runTest(2, solver.getBottomView(tc2), ex2)) passed++; total++;

        // Test Case 3: Single Node
        TreeNode tc3 = new TreeNode(10);
        List<Integer> ex3 = Arrays.asList(10);
        if (runTest(3, solver.getBottomView(tc3), ex3)) passed++; total++;

        // Test Case 4: Left Skewed
        TreeNode tc4 = new TreeNode(1);
        tc4.left = new TreeNode(2);
        tc4.left.left = new TreeNode(3);
        List<Integer> ex4 = Arrays.asList(3, 2, 1);
        if (runTest(4, solver.getBottomView(tc4), ex4)) passed++; total++;

        // Test Case 5: Right Skewed
        TreeNode tc5 = new TreeNode(1);
        tc5.right = new TreeNode(2);
        tc5.right.right = new TreeNode(3);
        List<Integer> ex5 = Arrays.asList(1, 2, 3);
        if (runTest(5, solver.getBottomView(tc5), ex5)) passed++; total++;

        // Test Case 6: Root hidden by lower nodes
        //     1
        //    / \
        //   2   3
        //    \ /
        //     4  <-- 4 is at col 0, should replace 1
        TreeNode tc6 = new TreeNode(1);
        tc6.left = new TreeNode(2);
        tc6.right = new TreeNode(3);
        tc6.left.right = new TreeNode(4);
        List<Integer> ex6 = Arrays.asList(2, 4, 3);
        if (runTest(6, solver.getBottomView(tc6), ex6)) passed++; total++;

        // Test Case 7: Complex overwriting at the same level
        //       1
        //      / \
        //     2   3
        //      \   \
        //       4   5
        //      /
        //     6      <-- 6 is at col 0, depth 3. Should replace 1.
        TreeNode tc7 = new TreeNode(1);
        tc7.left = new TreeNode(2); tc7.right = new TreeNode(3);
        tc7.left.right = new TreeNode(4); tc7.right.right = new TreeNode(5);
        tc7.left.right.left = new TreeNode(6);
        List<Integer> ex7 = Arrays.asList(2, 6, 4, 3, 5);
        if (runTest(7, solver.getBottomView(tc7), ex7)) passed++; total++;

        // Test Case 8: Vertical line with multiple nodes
        // 1 -> L:2 -> L:3 (Col -2)
        //   -> R:4 -> R:5 (Col 2)
        TreeNode tc8 = new TreeNode(1);
        tc8.left = new TreeNode(2); tc8.left.left = new TreeNode(3);
        tc8.right = new TreeNode(4); tc8.right.right = new TreeNode(5);
        List<Integer> ex8 = Arrays.asList(3, 2, 1, 4, 5);
        if (runTest(8, solver.getBottomView(tc8), ex8)) passed++; total++;

        // Test Case 9: Empty Tree
        List<Integer> ex9 = new ArrayList<>();
        if (runTest(9, solver.getBottomView(null), ex9)) passed++; total++;

        // Test Case 10: Deep Tree with multiple overlaps at bottom
        // Logic check: Ensure the BFS naturally handles the "right-most" requirement
        // for nodes at the same horizontal distance and depth.
        TreeNode tc10 = new TreeNode(1);
        tc10.left = new TreeNode(2); tc10.right = new TreeNode(3);
        tc10.left.right = new TreeNode(4); // col 0
        tc10.right.left = new TreeNode(5); // col 0, but right of 4 in level order
        List<Integer> ex10 = Arrays.asList(2, 5, 3);
        if (runTest(10, solver.getBottomView(tc10), ex10)) passed++; total++;

        System.out.println("\n---------------------------------------");
        System.out.println("Final Results: " + passed + "/" + total + " Passed");
        System.out.println("---------------------------------------");
    }

    private static boolean runTest(int id, List<Integer> actual, List<Integer> expected) {
        boolean isMatch = Objects.equals(actual, expected);
        System.out.println("Test Case " + id + ": " + (isMatch ? "PASSED" : "FAILED"));
        if (!isMatch) {
            System.out.println("   Expected: " + expected);
            System.out.println("   Actual:   " + actual);
        }
        return isMatch;
    }
}