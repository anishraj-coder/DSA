package trees;

import java.util.*;

/**
 * Problem: 2385. Amount of Time for Binary Tree to Be Infected
 * Complexity: O(N) where N is the number of nodes.
 */
class BinaryTreeInfectionClock {


    /**
     * IMPLEMENT YOUR SOLUTION HERE
     * @param root The root of the unique-valued binary tree.
     * @param start The value of the node where infection begins at minute 0.
     * @return The number of minutes for the entire tree to be infected.
     */
    public int amountOfTime(TreeNode root, int start) {
        if(root==null)return 0;
        HashMap<TreeNode,TreeNode>parent=new HashMap<>();
        TreeNode target=hashParent(root,parent,start);
        if(target==null)return -1;
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(target);
        HashSet<TreeNode>vis=new HashSet<>();
        vis.add(target);
        int count=-1;
        if(target==root&&root.left==null&&root.right==null)return 0;
        while(!q.isEmpty()){
            int n=q.size();
            for(int i=0;i<n;i++){
                TreeNode rem=q.poll();
                if(parent.containsKey(rem)&&!vis.contains(parent.get(rem))){
                    q.offer(parent.get(rem));
                    vis.add(parent.get(rem));
                }
                if(rem.left!=null&&!vis.contains(rem.left)){
                    q.offer(rem.left);
                    vis.add(rem.left);
                }

               if(rem.right!=null&&!vis.contains(rem.right)){
                   q.offer(rem.right);
                   vis.add(rem.right);
               }
            }
            count++;
        }
        return count;
    }


    private TreeNode hashParent(TreeNode root,HashMap<TreeNode,TreeNode>parent,int start){
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        TreeNode target=null;
        while(!q.isEmpty()){
            int n=q.size();
            for(int i=0;i<n;i++){
                TreeNode rem=q.poll();
                if(rem.val==start)target=rem;
                if(rem.left!=null){
                    parent.put(rem.left,rem);
                    q.offer(rem.left);
                }
                if(rem.right!=null){
                    parent.put(rem.right,rem);
                    q.offer(rem.right);
                }
            }
        }
        return target;
    }

    public static void main(String[] args) {
        BinaryTreeInfectionClock solver = new BinaryTreeInfectionClock();

        // Test Case 1: Example 1 from description
        // Structure: [1,5,3,null,4,10,6,9,2], start = 3
        TreeNode tc1 = new TreeNode(1);
        tc1.left = new TreeNode(5);
        tc1.right = new TreeNode(3);
        tc1.left.right = new TreeNode(4);
        tc1.left.right.left = new TreeNode(9);
        tc1.left.right.right = new TreeNode(2);
        tc1.right.left = new TreeNode(10);
        tc1.right.right = new TreeNode(6);
        runTest(1, solver.amountOfTime(tc1, 3), 4);

        // Test Case 2: Single node tree (Edge Case)
        TreeNode tc2 = new TreeNode(1);
        runTest(2, solver.amountOfTime(tc2, 1), 0);

        // Test Case 3: Start node is root, balanced tree
        TreeNode tc3 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        runTest(3, solver.amountOfTime(tc3, 1), 1);

        // Test Case 4: Start node is a leaf in a skewed tree (Max distance)
        TreeNode tc4 = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4), null), null), null);
        runTest(4, solver.amountOfTime(tc4, 4), 3);

        // Test Case 5: Start node is the root of a skewed tree
        runTest(5, solver.amountOfTime(tc4, 1), 3);

        // Test Case 6: Start node is in the middle of a long chain
        TreeNode tc6 = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), new TreeNode(4));
        runTest(6, solver.amountOfTime(tc6, 2), 2);

        // Test Case 7: Star/Fan topology (Root connected to many leaves)
        TreeNode tc7 = new TreeNode(10);
        tc7.left = new TreeNode(20);
        tc7.right = new TreeNode(30);
        tc7.left.left = new TreeNode(40);
        tc7.left.right = new TreeNode(50);
        runTest(7, solver.amountOfTime(tc7, 50), 3);

        // Test Case 8: Infection starts at leaf, must travel up to other deep branch
        // Root(1) -> L(2)->L(3); Root(1) -> R(4)->R(5)->R(6)
        TreeNode tc8 = new TreeNode(1);
        tc8.left = new TreeNode(2, new TreeNode(3), null);
        tc8.right = new TreeNode(4, null, new TreeNode(5, null, new TreeNode(6)));
        runTest(8, solver.amountOfTime(tc8, 3), 5); // 3->2->1->4->5->6

        // Test Case 9: Large symmetrical tree, start at root
        TreeNode tc9 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), new TreeNode(7)));
        runTest(9, solver.amountOfTime(tc9, 1), 2);

        // Test Case 10: Deeply nested one side, start node at bottom of other side
        TreeNode tc10 = new TreeNode(1);
        tc10.left = new TreeNode(2); // Start here
        tc10.right = new TreeNode(3, new TreeNode(4, new TreeNode(5, new TreeNode(6), null), null), null);
        runTest(10, solver.amountOfTime(tc10, 2), 5); // 2->1->3->4->5->6
    }

    private static void runTest(int id, int actual, int expected) {
        boolean passed = (actual == expected);
        System.out.printf("Test Case %d: %s\n", id, passed ? "PASSED" : "FAILED");
        System.out.println("   Expected: " + expected);
        System.out.println("   Actual:   " + actual);
        System.out.println("------------------------------------");
    }
}