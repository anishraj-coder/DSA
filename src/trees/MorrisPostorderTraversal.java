package trees;

import java.util.*;

public class MorrisPostorderTraversal {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode() {}
        public TreeNode(int val) { this.val = val; }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // --- YOUR IMPLEMENTATION GOES HERE ---
    // Goal: Retrieve the postorder traversal using strictly O(1) extra space.
    // Remember to completely clean up your threads and leave the tree intact!
    // Hint: Use a dummy node pointing to the root to easily process the final right spine.
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root==null)return new ArrayList<>();
        if(root.left==null&&root.right==null)return new ArrayList<>(Arrays.asList(root.val));

        List<Integer> ans = new ArrayList<>();
        TreeNode curr=root;
        while(curr!=null){
            TreeNode right=curr.right;
            if(right==null){
                ans.add(curr.val);
                curr=curr.left;
                continue;
            }
            while(right.left!=null&&right.left!=curr)right=right.left;
            if(right.left==curr){
                ans.add(curr.val);
                right.left=null;
                curr=curr.left;
            }else{
                right.left=curr;
                curr=curr.right;
            }
        }

        return ans;
    }
    // -------------------------------------

    public static void main(String[] args) {
        MorrisPostorderTraversal runner = new MorrisPostorderTraversal();
        int passed = 0;
        int totalTests = 10;

        // Test Case 1: Standard Binary Tree
        TreeNode tc1 = buildTree(new Integer[]{1, null, 2, 3});
        List<Integer> exp1 = Arrays.asList(3, 2, 1);
        passed += runTest(1, tc1, exp1, runner);

        // Test Case 2: Empty Tree (Edge Case)
        TreeNode tc2 = buildTree(new Integer[]{});
        List<Integer> exp2 = Collections.emptyList();
        passed += runTest(2, tc2, exp2, runner);

        // Test Case 3: Single Node Tree (Edge Case)
        TreeNode tc3 = buildTree(new Integer[]{10});
        List<Integer> exp3 = Arrays.asList(10);
        passed += runTest(3, tc3, exp3, runner);

        // Test Case 4: Strictly Left-Skewed Tree
        TreeNode tc4 = buildTree(new Integer[]{4, 3, null, 2, null, 1});
        List<Integer> exp4 = Arrays.asList(1, 2, 3, 4);
        passed += runTest(4, tc4, exp4, runner);

        // Test Case 5: Strictly Right-Skewed Tree
        TreeNode tc5 = buildTree(new Integer[]{1, null, 2, null, 3, null, 4});
        List<Integer> exp5 = Arrays.asList(4, 3, 2, 1);
        passed += runTest(5, tc5, exp5, runner);

        // Test Case 6: Perfect Balanced Binary Tree
        TreeNode tc6 = buildTree(new Integer[]{4, 2, 6, 1, 3, 5, 7});
        List<Integer> exp6 = Arrays.asList(1, 3, 2, 5, 7, 6, 4);
        passed += runTest(6, tc6, exp6, runner);

        // Test Case 7: Elements containing Negative Values and Duplicates
        TreeNode tc7 = buildTree(new Integer[]{0, -5, 5, -10, null, 2, 5});
        List<Integer> exp7 = Arrays.asList(-10, -5, 2, 5, 5, 0);
        passed += runTest(7, tc7, exp7, runner);

        // Test Case 8: Dynamic Zig-Zag Interleaving Tree Configuration
        TreeNode tc8 = buildTree(new Integer[]{1, 2, null, null, 3, 4});
        List<Integer> exp8 = Arrays.asList(4, 3, 2, 1);
        passed += runTest(8, tc8, exp8, runner);

        // Test Case 9: Deep Left-Heavy Subtrees off a Single High Right Shoulder
        TreeNode tc9 = buildTree(new Integer[]{1, null, 10, 5, null, 2, null, 1});
        List<Integer> exp9 = Arrays.asList(1, 2, 5, 10, 1);
        passed += runTest(9, tc9, exp9, runner);

        // Test Case 10: Complete Structural Stress Test (Complex Node Asymmetry)
        TreeNode tc10 = buildTree(new Integer[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15});
        List<Integer> exp10 = Arrays.asList(1, 3, 2, 5, 7, 6, 4, 9, 11, 10, 13, 15, 14, 12, 8);
        passed += runTest(10, tc10, exp10, runner);

        System.out.println("\n--------------------------------------------------");
        System.out.printf("Execution complete: %d/%d Tests Passed.\n", passed, totalTests);
        System.out.println("--------------------------------------------------");
    }

    // --- Helper Methods for Test Execution Architecture ---

    private static int runTest(int id, TreeNode root, List<Integer> expected, MorrisPostorderTraversal runner) {
        System.out.println("--- Test Case " + id + " ---");

        List<Integer> inputAsList = serialize(root);
        System.out.println("Input Tree (Level Order): " + inputAsList);
        System.out.println("Expected Postorder Sort : " + expected);

        List<Integer> actual = runner.postorderTraversal(root);
        System.out.println("Actual Traversal Output : " + actual);

        // Validation rule: Also verify that the structure of the tree was correctly restored to standard states
        List<Integer> postTraversalStructure = serialize(root);
        boolean isTreeIntact = inputAsList.equals(postTraversalStructure);

        if (!isTreeIntact) {
            System.out.println("Validation Error        : Tree topology modified! Did you reverse the lists back correctly?");
        }

        if (actual.equals(expected) && isTreeIntact) {
            System.out.println("Result                  : PASSED");
            return 1;
        } else {
            System.out.println("Result                  : FAILED");
            return 0;
        }
    }

    private static TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) return null;
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode curr = queue.poll();
            if (i < values.length && values[i] != null) {
                curr.left = new TreeNode(values[i]);
                queue.add(curr.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                curr.right = new TreeNode(values[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }

    private static List<Integer> serialize(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                result.add(null);
            } else {
                result.add(curr.val);
                queue.add(curr.left);
                queue.add(curr.right);
            }
        }
        while (!result.isEmpty() && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }
        return result;
    }
}