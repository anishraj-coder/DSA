package trees;

import java.util.*;

public class ChildrenSumProperty {

    /**
     * Function to modify the tree to satisfy the Children Sum Property.
     * Constraint: You can only increment node values.
     */
    public void changeTree(TreeNode root) {
        helper(root);
    }

    private void helper(TreeNode root){
        if(root==null)return ;
        int childSum=0;
        if(root.left!=null)childSum+=root.left.val;
        if(root.right!=null)childSum+=root.right.val;

        if(childSum>=root.val){
            root.val=childSum;
        }else{
            if(root.left!=null)root.left.val=root.val;
            if(root.right!=null)root.right.val=root.val;
        }
        helper(root.left);
        helper(root.right);
        int total=0;
        if(root.left!=null)total+=root.left.val;
        if(root.right!=null)total+=root.right.val;
        if(total>root.val&&!(root.left==null&&root.right==null))root.val=total;
    }

    // --- Testing Harness ---

    public static void main(String[] args) {
        ChildrenSumProperty solver = new ChildrenSumProperty();
        List<Integer[]> inputs = getRawTestInputs();
        int passed = 0;

        System.out.println("Starting Children Sum Property Tests...\n");

        for (int i = 0; i < inputs.size(); i++) {
            Integer[] input = inputs.get(i);

            // Build two identical trees: one to modify, one to keep as a reference for original values
            TreeNode rootToModify = buildTree(input);
            TreeNode referenceRoot = buildTree(input);

            solver.changeTree(rootToModify);

            boolean result = validate(rootToModify, referenceRoot);

            if (result) {
                System.out.println("Test Case " + (i + 1) + ": PASSED");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAILED");
                System.out.println("   Note: Ensure node values only increase and Parent = Left + Right.");
            }
        }

        System.out.println("\nFinal Score: " + passed + "/" + inputs.size());
    }

    private static boolean validate(TreeNode modified, TreeNode original) {
        if (modified == null && original == null) return true;
        if (modified == null || original == null) return false;

        // Rule 1: Values must not decrease
        if (modified.val < original.val) return false;

        // Rule 2: Children Sum Property (ignore leaf nodes)
        if (modified.left != null || modified.right != null) {
            int sum = 0;
            if (modified.left != null) sum += modified.left.val;
            if (modified.right != null) sum += modified.right.val;
            if (modified.val != sum) return false;
        }

        return validate(modified.left, original.left) && validate(modified.right, original.right);
    }

    private static List<Integer[]> getRawTestInputs() {
        List<Integer[]> testInputs = new ArrayList<>();

        // 1. Basic Tree (Root < Children Sum)
        testInputs.add(new Integer[]{10, 20, 30});
        // 2. Basic Tree (Root > Children Sum)
        testInputs.add(new Integer[]{50, 1, 2});
        // 3. Sample 1 (Deep tree with small root)
        testInputs.add(new Integer[]{2, 35, 10, 2, 3, 5, 2});
        // 4. Single Node (Edge Case)
        testInputs.add(new Integer[]{10});
        // 5. Null Tree (Edge Case)
        testInputs.add(new Integer[]{});
        // 6. Left Skewed (Property must hold for every non-leaf)
        testInputs.add(new Integer[]{5, 2, null, 1, null});
        // 7. Right Skewed
        testInputs.add(new Integer[]{1, null, 10, null, 100});
        // 8. Large Values
        testInputs.add(new Integer[]{1, 1000000, 1000000});
        // 9. Already satisfies property
        testInputs.add(new Integer[]{40, 20, 20, 10, 10, 10, 10});
        // 10. Complex structure with zeros
        testInputs.add(new Integer[]{0, 0, 1, null, null, 0, 0});

        return testInputs;
    }

    private static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (i < arr.length) {
            TreeNode curr = q.poll();
            if (i < arr.length && arr[i] != null) {
                curr.left = new TreeNode(arr[i]);
                q.add(curr.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                curr.right = new TreeNode(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }
}