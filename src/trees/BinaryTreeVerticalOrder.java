package trees;

import java.util.*;


public class BinaryTreeVerticalOrder {

    /**
     * Given the root of a binary tree, calculate the vertical order traversal.
     * For each node at (row, col), left child is at (row + 1, col - 1)
     * and right child is at (row + 1, col + 1).
     * If multiple nodes are at the same (row, col), sort them by value.
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if(root==null)return new ArrayList<>();
        HashMap<Integer,List<Pair>>map=new HashMap<>();
        Queue<Pair>q=new LinkedList<>();
        q.offer(new Pair(root,0,0));
        int max=0,min=0;
        List<List<Integer>>ans=new ArrayList<>();

        while(!q.isEmpty()){
            Pair rem=q.poll();
            int pos=rem.pos;
            min=Math.min(pos,min);
            max=Math.max(max,pos);
            map.putIfAbsent(pos,new ArrayList<>());
            map.get(pos).add(rem);
            if(rem.node.left!=null){
                q.offer(new Pair(rem.node.left,pos-1,rem.depth+1));
            }
            if(rem.node.right!=null){
                q.offer(new Pair(rem.node.right,pos+1,rem.depth+1));
            }
        }
        for(int i=min;i<=max;i++){
            Collections.sort(map.get(i));
            List<Integer>t=new ArrayList<>();
            for(Pair p:map.get(i)){
                t.add(p.node.val);
            }
            ans.add(t);
        }
        return ans;
    }

    private class Pair implements Comparable<Pair>{
        int pos,depth;
        TreeNode node;
        Pair(TreeNode node,int pos,int depth){
            this.node=node;
            this.pos=pos;
            this.depth=depth;
        }

        @Override
        public int compareTo(Pair o) {
            return this.depth==o.depth? Integer.compare(this.node.val,o.node.val)
                    :Integer.compare(this.depth,o.depth);
        }
    }

    public static void main(String[] args) {
        BinaryTreeVerticalOrder solver = new BinaryTreeVerticalOrder();
        int passed = 0;
        int total = 0;

        // Test Case 1: Example 1 from problem
        // [3,9,20,null,null,15,7]
        TreeNode tc1 = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        List<List<Integer>> ex1 = Arrays.asList(Arrays.asList(9), Arrays.asList(3, 15), Arrays.asList(20), Arrays.asList(7));
        if (runTest(1, solver.verticalTraversal(tc1), ex1)) passed++; total++;

        // Test Case 2: Example 2 (Overlapping nodes with sorting)
        // [1,2,3,4,5,6,7]
        TreeNode tc2 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), new TreeNode(7)));
        List<List<Integer>> ex2 = Arrays.asList(Arrays.asList(4), Arrays.asList(2), Arrays.asList(1, 5, 6), Arrays.asList(3), Arrays.asList(7));
        if (runTest(2, solver.verticalTraversal(tc2), ex2)) passed++; total++;

        // Test Case 3: Single Node (Edge Case)
        TreeNode tc3 = new TreeNode(1);
        List<List<Integer>> ex3 = Arrays.asList(Arrays.asList(1));
        if (runTest(3, solver.verticalTraversal(tc3), ex3)) passed++; total++;

        // Test Case 4: Left Skewed Tree (Edge Case)
        TreeNode tc4 = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4), null), null), null);
        List<List<Integer>> ex4 = Arrays.asList(Arrays.asList(4), Arrays.asList(3), Arrays.asList(2), Arrays.asList(1));
        if (runTest(4, solver.verticalTraversal(tc4), ex4)) passed++; total++;

        // Test Case 5: Right Skewed Tree (Edge Case)
        TreeNode tc5 = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4))));
        List<List<Integer>> ex5 = Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList(4));
        if (runTest(5, solver.verticalTraversal(tc5), ex5)) passed++; total++;

        // Test Case 6: Overlapping nodes where values must be sorted
        // Same row, same col, different values
        TreeNode tc6 = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(10)),
                new TreeNode(3, new TreeNode(5), null));
        // Node 10 and 5 are both at row 2, col 0. Expected order [1, 5, 10]
        List<List<Integer>> ex6 = Arrays.asList(Arrays.asList(2), Arrays.asList(1, 5, 10), Arrays.asList(3));
        if (runTest(6, solver.verticalTraversal(tc6), ex6)) passed++; total++;

        // Test Case 7: Complex Overlap (Multiple nodes at same spot)
        TreeNode tc7 = new TreeNode(3,
                new TreeNode(1, new TreeNode(0), new TreeNode(2)),
                new TreeNode(4, new TreeNode(2), null));
        // Nodes at col 0: 3 (0,0), 2 (2,0) from left, 2 (2,0) from right.
        List<List<Integer>> ex7 = Arrays.asList(Arrays.asList(0), Arrays.asList(1), Arrays.asList(3, 2, 2), Arrays.asList(4));
        if (runTest(7, solver.verticalTraversal(tc7), ex7)) passed++; total++;

        // Test Case 8: All nodes same value (Edge Case)
        TreeNode tc8 = new TreeNode(1, new TreeNode(1), new TreeNode(1));
        List<List<Integer>> ex8 = Arrays.asList(Arrays.asList(1), Arrays.asList(1), Arrays.asList(1));
        if (runTest(8, solver.verticalTraversal(tc8), ex8)) passed++; total++;

        // Test Case 9: Wide Tree
        TreeNode tc9 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4, new TreeNode(8), null), null),
                new TreeNode(3, null, new TreeNode(7, null, new TreeNode(9))));
        List<List<Integer>> ex9 = Arrays.asList(Arrays.asList(8), Arrays.asList(4), Arrays.asList(2), Arrays.asList(1), Arrays.asList(3), Arrays.asList(7), Arrays.asList(9));
        if (runTest(9, solver.verticalTraversal(tc9), ex9)) passed++; total++;

        // Test Case 10: Symmetrical Overlap
        TreeNode tc10 = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), null));
        // Col 0: 1 (0,0), 4 (2,0), 4 (2,0).
        List<List<Integer>> ex10 = Arrays.asList(Arrays.asList(2), Arrays.asList(1, 4, 4), Arrays.asList(2));
        if (runTest(10, solver.verticalTraversal(tc10), ex10)) passed++; total++;

        // Test Case 11: Deep tree with large values
        TreeNode tc11 = new TreeNode(100, new TreeNode(50, new TreeNode(25), new TreeNode(75)), new TreeNode(150, new TreeNode(125), new TreeNode(175)));
        List<List<Integer>> ex11 = Arrays.asList(Arrays.asList(25), Arrays.asList(50), Arrays.asList(100, 75, 125), Arrays.asList(150), Arrays.asList(175));
        if (runTest(11, solver.verticalTraversal(tc11), ex11)) passed++; total++;

        System.out.println("\n---------------------------------------");
        System.out.println("Final Results: " + passed + "/" + total + " Passed");
        System.out.println("---------------------------------------");
    }

    private static boolean runTest(int id, List<List<Integer>> actual, List<List<Integer>> expected) {
        boolean isMatch = Objects.equals(actual, expected);
        System.out.println("Test Case " + id + ": " + (isMatch ? "PASSED" : "FAILED"));
        if (!isMatch) {
            System.out.println("   Expected: " + expected);
            System.out.println("   Actual:   " + actual);
        }
        return isMatch;
    }
}