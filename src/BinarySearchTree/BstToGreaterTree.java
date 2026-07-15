package BinarySearchTree;

import java.util.*;

public class BstToGreaterTree {



    // --- YOUR IMPLEMENTATION GOES HERE ---
    public TreeNode convertBST(TreeNode root) {

        if(root==null)return null;
        if(root.left==null&&root.right==null)return root;
        TreeNode curr=root;
        int sum=0;
        while(curr!=null){
            TreeNode right=curr.right;
            if(right==null){
                sum+=curr.val;
                curr.val=sum;
                curr=curr.left;
                continue;
            }
            while(right.left!=null&&right.left!=curr)right=right.left;
            if(right.left==curr){
                sum+=curr.val;
                curr.val=sum;
                right.left=null;
                curr=curr.left;
            }else{
                right.left=curr;
                curr=curr.right;
            }
        }

        return root;
    }



    // -------------------------------------

    public static void main(String[] args) {
        BstToGreaterTree runner = new BstToGreaterTree();
        int passed = 0;
        int totalTests = 10;

        // Test Case 1: Example 1 from problem description
        TreeNode tc1 = buildTree(new Integer[]{4,1,6,0,2,5,7,null,null,null,3,null,null,null,8});
        List<Integer> exp1 = Arrays.asList(30,36,21,36,35,26,15,null,null,null,33,null,null,null,8);
        passed += runTest(1, tc1, exp1, runner);

        // Test Case 2: Example 2 from problem description
        TreeNode tc2 = buildTree(new Integer[]{0, null, 1});
        List<Integer> exp2 = Arrays.asList(1, null, 1);
        passed += runTest(2, tc2, exp2, runner);

        // Test Case 3: Empty Tree (Edge Case)
        TreeNode tc3 = buildTree(new Integer[]{});
        List<Integer> exp3 = Collections.emptyList();
        passed += runTest(3, tc3, exp3, runner);

        // Test Case 4: Single Node (Edge Case)
        TreeNode tc4 = buildTree(new Integer[]{5});
        List<Integer> exp4 = Arrays.asList(5);
        passed += runTest(4, tc4, exp4, runner);

        // Test Case 5: All Negative Values
        TreeNode tc5 = buildTree(new Integer[]{-3, -4, -2});
        List<Integer> exp5 = Arrays.asList(-5, -9, -2); // -2 is max -> -2. -3 becomes -3 + (-2) = -5. -4 becomes -4 + (-5) = -9.
        passed += runTest(5, tc5, exp5, runner);

        // Test Case 6: Mix of Negative, Zero, and Positive Values
        TreeNode tc6 = buildTree(new Integer[]{0, -2, 3});
        List<Integer> exp6 = Arrays.asList(3, 1, 3); // 3 is max -> 3. 0 becomes 0 + 3 = 3. -2 becomes -2 + 3 = 1.
        passed += runTest(6, tc6, exp6, runner);

        // Test Case 7: Strictly Right-Skewed Tree (Linear Linked List Structure)
        TreeNode tc7 = buildTree(new Integer[]{1, null, 2, null, 3, null, 4});
        List<Integer> exp7 = Arrays.asList(10, null, 9, null, 7, null, 4);
        passed += runTest(7, tc7, exp7, runner);

        // Test Case 8: Strictly Left-Skewed Tree
        TreeNode tc8 = buildTree(new Integer[]{4, 3, null, 2, null, 1});
        List<Integer> exp8 = Arrays.asList(4, 7, null, 9, null, 10);
        passed += runTest(8, tc8, exp8, runner);

        // Test Case 9: Perfect Balanced Tree with Symmetric Structure
        TreeNode tc9 = buildTree(new Integer[]{2, 1, 3});
        List<Integer> exp9 = Arrays.asList(5, 6, 3); // 3->3, 2->2+3=5, 1->1+5=6
        passed += runTest(9, tc9, exp9, runner);

        // Test Case 10: Deep Complex Layout with Multi-level Accumulations
        TreeNode tc10 = buildTree(new Integer[]{5, 2, 7, 1, 3, 6, 8});
        List<Integer> exp10 = Arrays.asList(26, 31, 15, 32, 29, 21, 8);
        passed += runTest(10, tc10, exp10, runner);

        System.out.println("\n--------------------------------------------------");
        System.out.printf("Execution complete: %d/%d Tests Passed.\n", passed, totalTests);
        System.out.println("--------------------------------------------------");
    }

    // --- Helper Methods for Test Execution Architecture ---

    private static int runTest(int id, TreeNode root, List<Integer> expected, BstToGreaterTree runner) {
        System.out.println("--- Test Case " + id + " ---");

        // Deep copy of the tree to preserve original state visualization
        TreeNode inputCopy = cloneTree(root);
        List<Integer> inputAsList = serialize(inputCopy);
        System.out.println("Input BST (Level Order) : " + inputAsList);
        System.out.println("Expected Output         : " + expected);

        TreeNode resultTree = runner.convertBST(root);
        List<Integer> actual = serialize(resultTree);
        System.out.println("Actual Output           : " + actual);

        if (actual.equals(expected)) {
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
        // Clean trailing null elements to mirror standard serialization patterns
        while (!result.isEmpty() && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }
        return result;
    }

    private static TreeNode cloneTree(TreeNode root) {
        if (root == null) return null;
        TreeNode copy = new TreeNode(root.val);
        copy.left = cloneTree(root.left);
        copy.right = cloneTree(root.right);
        return copy;
    }
}