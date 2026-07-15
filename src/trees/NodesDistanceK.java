package trees;

import java.util.*;

public class NodesDistanceK {

    private void hashParent(TreeNode root,HashMap<TreeNode,TreeNode>map){
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode rem=q.poll();
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

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if(k==0){
            List<Integer>ans=new ArrayList<>();
            ans.add(target.val);
            return ans;
        }
        HashMap<TreeNode,TreeNode >parents=new HashMap<>();
        hashParent(root,parents);
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(target);
        Set<TreeNode>vis=new HashSet<>();
        vis.add(target);
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
            if(count==k)break;
        }
        List<Integer>ans=new ArrayList<>();
        while(!q.isEmpty()){
            ans.add(q.poll().val);
        }
        return ans;
    }

    public static void main(String[] args) {
        NodesDistanceK solver = new NodesDistanceK();
        int passed = 0;
        int total = 10;

        // --- Test Case 1: Standard Tree (Example 1) ---
        TreeNode r1 = buildTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        TreeNode t1 = findNode(r1, 5);
        List<Integer> ex1 = Arrays.asList(7, 4, 1);
        passed += runTest(1, solver.distanceK(r1, t1, 2), ex1);

        // --- Test Case 2: Single Node, K > 0 ---
        TreeNode r2 = new TreeNode(1);
        passed += runTest(2, solver.distanceK(r2, r2, 3), Arrays.asList());

        // --- Test Case 3: K = 0 (Should return target itself) ---
        TreeNode r3 = buildTree(new Integer[]{1, 2, 3});
        TreeNode t3 = findNode(r3, 2);
        passed += runTest(3, solver.distanceK(r3, t3, 0), Arrays.asList(2));

        // --- Test Case 4: Target is root, distance K ---
        TreeNode r4 = buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        passed += runTest(4, solver.distanceK(r4, r4, 2), Arrays.asList(4, 5, 6, 7));

        // --- Test Case 5: Linear Tree (Skewed Left), Target at Leaf ---
        TreeNode r5 = buildTree(new Integer[]{1, 2, null, 3, null, 4, null, 5});
        TreeNode t5 = findNode(r5, 5);
        passed += runTest(5, solver.distanceK(r5, t5, 2), Arrays.asList(3));

        // --- Test Case 6: Target in middle, K reaches both up and down ---
        TreeNode r6 = buildTree(new Integer[]{0, 1, null, 3, 2, null, null, 4});
        TreeNode t6 = findNode(r6, 2);
        passed += runTest(6, solver.distanceK(r6, t6, 2), Arrays.asList(0, 4));

        // --- Test Case 7: Large K (Out of bounds) ---
        TreeNode r7 = buildTree(new Integer[]{1, 2, 3});
        passed += runTest(7, solver.distanceK(r7, r7, 10), Arrays.asList());

        // --- Test Case 8: Balanced Tree, Target is Leaf ---
        TreeNode r8 = buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        TreeNode t8 = findNode(r8, 4);
        passed += runTest(8, solver.distanceK(r8, t8, 2), Arrays.asList(5, 1));

        // --- Test Case 9: Complete Binary Tree, Multiple nodes at distance K ---
        TreeNode r9 = buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        TreeNode t9 = findNode(r9, 2);
        passed += runTest(9, solver.distanceK(r9, t9, 1), Arrays.asList(1, 4, 5));

        // --- Test Case 10: Star-like structure (Root with two deep paths) ---
        TreeNode r10 = buildTree(new Integer[]{1, 2, 3, null, 4, 5, null, null, 6, 7});
        TreeNode t10 = findNode(r10, 4);
        passed += runTest(10, solver.distanceK(r10, t10, 3), Arrays.asList(1, 7));

        System.out.println("\n---------------------------------------");
        System.out.println("Final Result: " + passed + "/" + total + " Passed");
    }

    // Helper: Compare lists regardless of order
    private static int runTest(int id, List<Integer> actual, List<Integer> expected) {
        Collections.sort(actual);
        Collections.sort(expected);
        boolean isMatch = actual.equals(expected);
        System.out.println("Test Case " + id + ": " + (isMatch ? "PASSED" : "FAILED"));
        System.out.println("   Expected: " + expected);
        System.out.println("   Actual:   " + actual);
        return isMatch ? 1 : 0;
    }

    // Helper: Build Tree from Level-Order Array
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

    // Helper: Find node by value
    private static TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        TreeNode left = findNode(root.left, val);
        return (left != null) ? left : findNode(root.right, val);
    }
}