package trees;

import java.util.*;



public class BinaryTreeTopView {

    /**
     * Finds the top view of the given binary tree from left to right.
     * The top view is the set of nodes visible when looking at the tree from the top.
     * * @param root The root of the binary tree.
     * @return A list of integers representing the top view from leftmost to rightmost.
     */
    public List<Integer> getTopView(TreeNode root) {
        if(root==null)return new ArrayList<>();
        Map<Integer,Integer> map=new HashMap<>();
        Queue<Pair>q=new LinkedList<>();
        q.offer(new Pair(root,0));
        int max=0,min=0;
        List<Integer>ans=new ArrayList<>();
        while(!q.isEmpty()){
            Pair rem=q.poll();
            int pos=rem.pos;
            TreeNode node=rem.node;
            max=Math.max(pos,max);
            min=Math.min(pos,min);
            if(!map.containsKey(pos))map.put(pos,node.val);
            if(node.left!=null)q.offer(new Pair(node.left,pos-1));
            if(node.right!=null)q.offer(new Pair(node.right,pos+1));
        }

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
        BinaryTreeTopView solver = new BinaryTreeTopView();
        int passed = 0;
        int total = 0;

        // Test Case 1: Simple Balanced Tree
        //      1
        //    /   \
        //   2     3
        TreeNode tc1 = new TreeNode(1);
        tc1.left = new TreeNode(2);
        tc1.right = new TreeNode(3);
        List<Integer> ex1 = Arrays.asList(2, 1, 3);
        if (runTest(1, solver.getTopView(tc1), ex1)) passed++; total++;

        // Test Case 2: Left Skewed Tree
        //     1
        //    /
        //   2
        //  /
        // 3
        TreeNode tc2 = new TreeNode(1);
        tc2.left = new TreeNode(2);
        tc2.left.left = new TreeNode(3);
        List<Integer> ex2 = Arrays.asList(3, 2, 1);
        if (runTest(2, solver.getTopView(tc2), ex2)) passed++; total++;

        // Test Case 3: Right Skewed Tree
        TreeNode tc3 = new TreeNode(1);
        tc3.right = new TreeNode(2);
        tc3.right.right = new TreeNode(3);
        List<Integer> ex3 = Arrays.asList(1, 2, 3);
        if (runTest(3, solver.getTopView(tc3), ex3)) passed++; total++;

        // Test Case 4: Overlapping Hidden Nodes
        //      1
        //    /   \
        //   2     3
        //    \   /
        //     4 5    <-- 4 and 5 are hidden by 1 at column 0
        TreeNode tc4 = new TreeNode(1);
        tc4.left = new TreeNode(2);
        tc4.right = new TreeNode(3);
        tc4.left.right = new TreeNode(4);
        tc4.right.left = new TreeNode(5);
        List<Integer> ex4 = Arrays.asList(2, 1, 3);
        if (runTest(4, solver.getTopView(tc4), ex4)) passed++; total++;

        // Test Case 5: Single Node (Edge Case)
        TreeNode tc5 = new TreeNode(10);
        List<Integer> ex5 = Arrays.asList(10);
        if (runTest(5, solver.getTopView(tc5), ex5)) passed++; total++;

        // Test Case 6: Large "V" Shape
        //      1
        //    /   \
        //   2     3
        //  /       \
        // 4         5
        TreeNode tc6 = new TreeNode(1);
        tc6.left = new TreeNode(2);
        tc6.right = new TreeNode(3);
        tc6.left.left = new TreeNode(4);
        tc6.right.right = new TreeNode(5);
        List<Integer> ex6 = Arrays.asList(4, 2, 1, 3, 5);
        if (runTest(6, solver.getTopView(tc6), ex6)) passed++; total++;

        // Test Case 7: Node hidden behind another at the same horizontal distance
        //        1
        //      /   \
        //     2     3
        //      \
        //       4
        //        \
        //         5  <-- 5 is at col 1, hidden by 3
        TreeNode tc7 = new TreeNode(1);
        tc7.left = new TreeNode(2);
        tc7.right = new TreeNode(3);
        tc7.left.right = new TreeNode(4);
        tc7.left.right.right = new TreeNode(5);
        List<Integer> ex7 = Arrays.asList(2, 1, 3);
        if (runTest(7, solver.getTopView(tc7), ex7)) passed++; total++;

        // Test Case 8: Sample Input 2 from Problem
        // [1, 2, 3, 4, 5, 6, 7, 8, 9] structure
        TreeNode tc8 = new TreeNode(1);
        tc8.left = new TreeNode(2); tc8.right = new TreeNode(3);
        tc8.left.left = new TreeNode(4); tc8.left.right = new TreeNode(5);
        tc8.right.left = new TreeNode(6); tc8.right.right = new TreeNode(7);
        tc8.left.left.left = new TreeNode(8); tc8.left.left.right = new TreeNode(9);
        List<Integer> ex8 = Arrays.asList(8, 4, 2, 1, 3, 7);
        if (runTest(8, solver.getTopView(tc8), ex8)) passed++; total++;

        // Test Case 9: Empty Tree (Edge Case)
        List<Integer> ex9 = new ArrayList<>();
        if (runTest(9, solver.getTopView(null), ex9)) passed++; total++;

        // Test Case 10: Deep zigzag (Tests distance calculation)
        // 1 (col 0) -> L: 2 (-1) -> R: 3 (0) -> L: 4 (-1) -> R: 5 (0)
        TreeNode tc10 = new TreeNode(1);
        tc10.left = new TreeNode(2);
        tc10.left.right = new TreeNode(3);
        tc10.left.right.left = new TreeNode(4);
        tc10.left.right.left.right = new TreeNode(5);
        List<Integer> ex10 = Arrays.asList(2, 1);
        if (runTest(10, solver.getTopView(tc10), ex10)) passed++; total++;

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