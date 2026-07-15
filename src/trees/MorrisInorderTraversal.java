package trees;

import java.util.*;

public class MorrisInorderTraversal {



    // --- YOUR IMPLEMENTATION GOES HERE ---
    // Goal: Retrieve the inorder traversal using strictly O(1) extra space.
    // Remember to completely clean up any temporary structural pointers/threads before returning!
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans= new ArrayList<>();
        if(root==null)return ans;
        if(root.left==null&&root.right==null)return new ArrayList<>(Arrays.asList(root.val));
        TreeNode curr=root;
        while(curr!=null){
            TreeNode left=curr.left;
            if(left==null){
                ans.add(curr.val);
                curr=curr.right;
                continue;
            }
            while(left.right!=null&&left.right!=curr){
                left=left.right;
            }
            if(left.right==curr){
                ans.add(curr.val);
                left.right=null;
                curr=curr.right;
            }else{
                left.right=curr;
                curr=curr.left;
            }
        }
        return ans;
    }
    // -------------------------------------

    public static void main(String[] args) {
        MorrisInorderTraversal runner = new MorrisInorderTraversal();
        int passed = 0;
        int totalTests = 10;

        // Test Case 1: Standard Binary Tree
        TreeNode tc1 = buildTree(new Integer[]{1, null, 2, 3});
        List<Integer> exp1 = Arrays.asList(1, 3, 2);
        passed += runTest(1, tc1, exp1, runner);

        // Test Case 2: Empty Tree (Edge Case)
        TreeNode tc2 = buildTree(new Integer[]{});
        List<Integer> exp2 = Collections.emptyList();
        passed += runTest(2, tc2, exp2, runner);

        // Test Case 3: Single Node Tree (Edge Case)
        TreeNode tc3 = buildTree(new Integer[]{10});
        List<Integer> exp3 = Arrays.asList(10);
        passed += runTest(3, tc3, exp3, runner);

        // Test Case 4: Strictly Left-Skewed Tree (Crucial for testing thread creation logic)
        TreeNode tc4 = buildTree(new Integer[]{4, 3, null, 2, null, 1});
        List<Integer> exp4 = Arrays.asList(1, 2, 3, 4);
        passed += runTest(4, tc4, exp4, runner);

        // Test Case 5: Strictly Right-Skewed Tree (Verifies standard pointer progression)
        TreeNode tc5 = buildTree(new Integer[]{1, null, 2, null, 3, null, 4});
        List<Integer> exp5 = Arrays.asList(1, 2, 3, 4);
        passed += runTest(5, tc5, exp5, runner);

        // Test Case 6: Perfect Balanced Binary Tree
        TreeNode tc6 = buildTree(new Integer[]{4, 2, 6, 1, 3, 5, 7});
        List<Integer> exp6 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        passed += runTest(6, tc6, exp6, runner);

        // Test Case 7: Elements containing Negative Values and Duplicates
        TreeNode tc7 = buildTree(new Integer[]{0, -5, 5, -10, null, 2, 5});
        List<Integer> exp7 = Arrays.asList(-10, -5, 0, 2, 5, 5);
        passed += runTest(7, tc7, exp7, runner);

        // Test Case 8: Dynamic Zig-Zag Interleaving Tree Configuration
        TreeNode tc8 = buildTree(new Integer[]{1, 2, null, null, 3, 4});
        List<Integer> exp8 = Arrays.asList(2, 4, 3, 1);
        passed += runTest(8, tc8, exp8, runner);

        // Test Case 9: Deep Left-Heavy Subtrees off a Single High Right Shoulder
        TreeNode tc9 = buildTree(new Integer[]{1, null, 10, 5, null, 2, null, 1});
        List<Integer> exp9 = Arrays.asList(1, 1, 2, 5, 10);
        passed += runTest(9, tc9, exp9, runner);

        // Test Case 10: Complete Structural Stress Test (Complex Node Asymmetry)
        TreeNode tc10 = buildTree(new Integer[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15});
        List<Integer> exp10 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        passed += runTest(10, tc10, exp10, runner);

        System.out.println("\n--------------------------------------------------");
        System.out.printf("Execution complete: %d/%d Tests Passed.\n", passed, totalTests);
        System.out.println("--------------------------------------------------");
    }

    // --- Helper Methods for Test Execution Architecture ---

    private static int runTest(int id, TreeNode root, List<Integer> expected, MorrisInorderTraversal runner) {
        System.out.println("--- Test Case " + id + " ---");

        List<Integer> inputAsList = serialize(root);
        System.out.println("Input Tree (Level Order): " + inputAsList);
        System.out.println("Expected Inorder Sort   : " + expected);

        List<Integer> actual = runner.inorderTraversal(root);
        System.out.println("Actual Traversal Output : " + actual);

        // Validation rule: Also verify that the structure of the tree was correctly restored to standard states
        List<Integer> postTraversalStructure = serialize(root);
        boolean isTreeIntact = inputAsList.equals(postTraversalStructure);

        if (!isTreeIntact) {
            System.out.println("Validation Error        : Tree topology modified! Did you tear down your Morris threads properly?");
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