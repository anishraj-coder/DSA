package trees;

import java.util.*;


public class TreeBurning {

    private void hashParent(TreeNode root,HashMap<TreeNode,TreeNode>map,HashMap<Integer,TreeNode>elem){
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode rem=q.poll();
            elem.put(rem.val,rem);
            if(rem.left!=null){
                map.put(rem.left,rem);
                q.offer(rem.left);
            }
            if(rem.right!=null){
                map.put(rem.right,rem);
                q.offer(rem.right);
            }
        }
    }
    public int timeToBurnTree(TreeNode root, int start) {

        HashMap<TreeNode,TreeNode >parents=new HashMap<>();
        HashMap<Integer,TreeNode>elem=new HashMap<>();
        hashParent(root,parents,elem);
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(elem.get(start));
        Set<TreeNode>vis=new HashSet<>();
        vis.add(elem.get(start));
        int count=0;
        while(!q.isEmpty()){
            int n=q.size();
            for(int i=0;i<n;i++){
                TreeNode rem=q.poll();
                if(parents.containsKey(rem)&&!vis.contains(parents.get(rem))){
                    TreeNode parent=parents.get(rem);
                    vis.add(parent);
                    q.offer(parent);
                }
                if(rem.left!=null&&!vis.contains(rem.left)){
                    vis.add(rem.left);
                    q.offer(rem.left);
                }
                if(rem.right!=null&&!vis.contains(rem.right)){
                    vis.add(rem.right);
                    q.offer(rem.right);
                }
            }
            count++;

        }
        return count-1;
    }

    public static void main(String[] args) {
        TreeBurning solver = new TreeBurning();
        int passed = 0;
        int total = 10;

        // --- Test Case 1: Example 1 ---
        TreeNode r1 = buildTree(new Integer[]{1, 2, 3, null, null, 4, 5});
        passed += runTest(1, solver.timeToBurnTree(r1, 3), 2);

        // --- Test Case 2: Example 2 (Start node 2) ---
        TreeNode r2 = buildTree(new Integer[]{1, 2, 3, 4, null, null, 5});
        passed += runTest(2, solver.timeToBurnTree(r2, 2), 3);

        // --- Test Case 3: Single Node ---
        TreeNode r3 = new TreeNode(1);
        passed += runTest(3, solver.timeToBurnTree(r3, 1), 0);

        // --- Test Case 4: Start Node is Root ---
        TreeNode r4 = buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        passed += runTest(4, solver.timeToBurnTree(r4, 1), 2);

        // --- Test Case 5: Skewed Tree (All nodes on one side) ---
        TreeNode r5 = buildTree(new Integer[]{1, 2, null, 3, null, 4, null, 5});
        passed += runTest(5, solver.timeToBurnTree(r5, 5), 4);

        // --- Test Case 6: Start Node is a Leaf (Deepest) ---
        TreeNode r6 = buildTree(new Integer[]{1, 2, 3, 4, null, null, null, 5});
        passed += runTest(6, solver.timeToBurnTree(r6, 5), 4);

        // --- Test Case 7: Large Tree, Start Node in Middle ---
        TreeNode r7 = buildTree(new Integer[]{10, 20, 30, 40, 50, null, null, null, null, 60});
        passed += runTest(7, solver.timeToBurnTree(r7, 20), 3);

        // --- Test Case 8: Balanced Tree, Start Node at Leaf ---
        TreeNode r8 = buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        passed += runTest(8, solver.timeToBurnTree(r8, 4), 4);

        // --- Test Case 9: All nodes in a line, start at one end ---
        TreeNode r9 = buildTree(new Integer[]{1, null, 2, null, 3, null, 4});
        passed += runTest(9, solver.timeToBurnTree(r9, 1), 3);

        // --- Test Case 10: Two Long Paths from Root, Start at end of one ---
        TreeNode r10 = buildTree(new Integer[]{1, 2, 3, 4, null, null, 5, 6, null, null, 7});
        // Structure: 1->2->4->6 (left), 1->3->5->7 (right). Start at 6.
        // Path: 6 -> 4 -> 2 -> 1 -> 3 -> 5 -> 7 (Total distance 6)
        passed += runTest(10, solver.timeToBurnTree(r10, 6), 6);

        System.out.println("\n---------------------------------------");
        System.out.println("Final Result: " + passed + "/" + total + " Passed");
    }

    private static int runTest(int id, int actual, int expected) {
        boolean isMatch = (actual == expected);
        System.out.println("Test Case " + id + ": " + (isMatch ? "PASSED" : "FAILED"));
        System.out.println("   Expected: " + expected);
        System.out.println("   Actual:   " + actual);
        return isMatch ? 1 : 0;
    }

    private static TreeNode buildTree(Integer[] vals) {
        if (vals.length == 0 || vals[0] == null) return null;
        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < vals.length) {
            TreeNode curr = q.poll();
            if (vals[i] != null) {
                curr.left = new TreeNode(vals[i]);
                q.add(curr.left);
            }
            i++;
            if (i < vals.length && vals[i] != null) {
                curr.right = new TreeNode(vals[i]);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }
}