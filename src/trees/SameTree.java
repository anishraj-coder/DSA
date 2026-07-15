package trees;


public class SameTree {

    /**
     * Determine if two binary trees are structurally identical with the same node values.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null)return true;
        if((p==null&&q!=null)||(q==null&&p!=null))return false;
        if(q.val!=p.val)return false;
        boolean left=isSameTree(p.left,q.left);
        boolean right=isSameTree(p.right,q.right);
        return left&&right;
    }

    public static void main(String[] args) {
        SameTree runner = new SameTree();
        int passed = 0;
        int total = 10;

        // Test Case 1: Identical full trees
        TreeNode t1_p = buildTree(new Integer[]{1, 2, 3});
        TreeNode t1_q = buildTree(new Integer[]{1, 2, 3});
        passed += evaluate(1, true, runner.isSameTree(t1_p, t1_q));

        // Test Case 2: Structural mismatch (Left vs Right child)
        TreeNode t2_p = buildTree(new Integer[]{1, 2});
        TreeNode t2_q = buildTree(new Integer[]{1, null, 2});
        passed += evaluate(2, false, runner.isSameTree(t2_p, t2_q));

        // Test Case 3: Value mismatch
        TreeNode t3_p = buildTree(new Integer[]{1, 2, 1});
        TreeNode t3_q = buildTree(new Integer[]{1, 1, 2});
        passed += evaluate(3, false, runner.isSameTree(t3_p, t3_q));

        // Test Case 4: Both trees are empty (Edge case)
        passed += evaluate(4, true, runner.isSameTree(null, null));

        // Test Case 5: One tree empty, one not (Edge case)
        TreeNode t5_q = new TreeNode(0);
        passed += evaluate(5, false, runner.isSameTree(null, t5_q));

        // Test Case 6: Deep skewed trees (Identical)
        TreeNode t6_p = buildTree(new Integer[]{1, 2, null, 3, null, 4});
        TreeNode t6_q = buildTree(new Integer[]{1, 2, null, 3, null, 4});
        passed += evaluate(6, true, runner.isSameTree(t6_p, t6_q));

        // Test Case 7: Deep skewed trees (Different leaf value)
        TreeNode t7_p = buildTree(new Integer[]{1, 2, null, 3, null, 4});
        TreeNode t7_q = buildTree(new Integer[]{1, 2, null, 3, null, 5});
        passed += evaluate(7, false, runner.isSameTree(t7_p, t7_q));

        // Test Case 8: Large values and symmetrical structure
        TreeNode t8_p = buildTree(new Integer[]{10000, -10000, -10000});
        TreeNode t8_q = buildTree(new Integer[]{10000, -10000, -10000});
        passed += evaluate(8, true, runner.isSameTree(t8_p, t8_q));

        // Test Case 9: Multi-level complex structure
        TreeNode t9_p = buildTree(new Integer[]{5, 4, 1, null, 1, null, 4, 2, null, null, 2});
        TreeNode t9_q = buildTree(new Integer[]{5, 4, 1, null, 1, null, 4, 2, null, null, 2});
        passed += evaluate(9, true, runner.isSameTree(t9_p, t9_q));

        // Test Case 10: Same nodes but mirrored (Should be false)
        TreeNode t10_p = buildTree(new Integer[]{1, 2, 3});
        TreeNode t10_q = buildTree(new Integer[]{1, 3, 2});
        passed += evaluate(10, false, runner.isSameTree(t10_p, t10_q));

        System.out.println("\n---------------------------");
        System.out.println("Final Result: " + passed + "/" + total + " Passed");
        System.out.println("---------------------------");
    }

    private static int evaluate(int id, boolean expected, boolean actual) {
        System.out.print("Test Case " + id + ": ");
        if (expected == actual) {
            System.out.println("PASSED (Expected: " + expected + ", Actual: " + actual + ")");
            return 1;
        } else {
            System.out.println("FAILED (Expected: " + expected + ", Actual: " + actual + ")");
            return 0;
        }
    }

    /**
     * Helper to build tree from Level-Order array (BFS style)
     */
    private static TreeNode buildTree(Integer[] vals) {
        if (vals == null || vals.length == 0) return null;
        TreeNode root = new TreeNode(vals[0]);
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < vals.length) {
            TreeNode curr = queue.poll();
            if (i < vals.length && vals[i] != null) {
                curr.left = new TreeNode(vals[i]);
                queue.add(curr.left);
            }
            i++;
            if (i < vals.length && vals[i] != null) {
                curr.right = new TreeNode(vals[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}