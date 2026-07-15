package BinarySearchTree;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearchTreeRecoverer {

    /**
     * Recovers the BST by finding the exactly two swapped nodes
     * and correcting their values without altering the structure.
     */
    public void recoverTree(TreeNode root) {
        if(root==null)return ;
        if(root.left==null&&root.right==null)return;
        TreeNode[]prev=new TreeNode[]{null};
        TreeNode[]first=new TreeNode[]{null,null},second=new TreeNode[]{null,null};
        helper(root,prev,first);
        if (first[0] != null) {
            swap(first[0],first[1]);
        }
    }

    private void swap(TreeNode i,TreeNode j){
        int t=i.val;
        i.val=j.val;
        j.val=t;
    }

    private void helper(TreeNode root,TreeNode[]prev,TreeNode[]first){
        if(root==null)return;

        helper(root.left,prev,first);
        if(prev[0]!=null){
            if(prev[0].val>=root.val){
                if(first[0]==null){
                    first[0]=prev[0];
                    first[1]=root;
                }else{

                    first[1]=root;
                }
            }
        }
        prev[0]=root;
        helper(root.right,prev,first);
    }

    // ==========================================
    // TESTING FRAMEWORK & EDGE CASES
    // ==========================================

    public static void main(String[] args) {
        BinarySearchTreeRecoverer recoverer = new BinarySearchTreeRecoverer();
        int passed = 0;
        int totalTests = 10;

        for (int i = 1; i <= totalTests; i++) {
            TreeNode root = buildTestCase(i);
            List<Integer> expected = getExpectedInorder(i);

            System.out.println("--------------------------------------------------");
            System.out.println("Test Case " + i + ": " + getDescription(i));
            System.out.print("Initial Inorder:  ");
            printInorder(root);
            System.out.println();

            // Run user's implementation
            recoverer.recoverTree(root);

            // Fetch resulting inorder traversal
            List<Integer> actual = new ArrayList<>();
            fetchInorder(root, actual);

            System.out.println("Expected Inorder: " + expected);
            System.out.println("Actual Inorder:   " + actual);

            if (actual.equals(expected)) {
                System.out.println("Result:           PASSED");
                passed++;
            } else {
                System.out.println("Result:           FAILED");
            }
        }

        System.out.println("==================================================");
        System.out.println("FINAL SCORE: " + passed + " / " + totalTests + " Passed");
        System.out.println("==================================================");
    }

    private static String getDescription(int tc) {
        switch (tc) {
            case 1: return "Example 1 (Swapped root and left child's right child)";
            case 2: return "Example 2 (Swapped root and right child's left child)";
            case 3: return "Minimal Tree (Only 2 nodes swapped - Parent and Left Child)";
            case 4: return "Minimal Tree (Only 2 nodes swapped - Parent and Right Child)";
            case 5: return "Adjacent elements swapped in sorted inorder array sequence";
            case 6: return "Non-adjacent elements swapped far apart in a larger tree";
            case 7: return "Left-skewed deep tree chain with swapped boundary values";
            case 8: return "Right-skewed deep tree chain with swapped inner adjacent nodes";
            case 9: return "Edge cases containing Integer.MIN_VALUE and Integer.MAX_VALUE";
            case 10: return "Swapped deep leaf node in left subtree with deep leaf node in right subtree";
            default: return "Unknown Test Case";
        }
    }

    private static TreeNode buildTestCase(int tc) {
        switch (tc) {
            case 1: {
                // [1, 3, null, null, 2] -> Expected sorted: [1, 2, 3]
                TreeNode root = new TreeNode(1);
                root.left = new TreeNode(3);
                root.left.right = new TreeNode(2);
                return root;
            }
            case 2: {
                // [3, 1, 4, null, null, 2] -> Expected sorted: [1, 2, 3, 4]
                TreeNode root = new TreeNode(3);
                root.left = new TreeNode(1);
                root.right = new TreeNode(4);
                root.right.left = new TreeNode(2);
                return root;
            }
            case 3: {
                // Parent (1) and Left (2) swapped. Valid should be Parent 2, Left 1
                TreeNode root = new TreeNode(1);
                root.left = new TreeNode(2);
                return root;
            }
            case 4: {
                // Parent (2) and Right (1) swapped. Valid should be Parent 1, Right 2
                TreeNode root = new TreeNode(2);
                root.right = new TreeNode(1);
                return root;
            }
            case 5: {
                // Complete valid tree inorder is 10, 20, 30, 40, 50.
                // Swap 30 and 40 (adjacent in sequence) -> 10, 20, 40, 30, 50
                TreeNode root = new TreeNode(40);
                root.left = new TreeNode(20);
                root.left.left = new TreeNode(10);
                root.left.right = new TreeNode(30);
                root.right = new TreeNode(50);
                return root;
            }
            case 6: {
                // Valid tree inorder: 5, 10, 15, 20, 25, 30, 35
                // Swap 10 and 30 (non-adjacent) -> inorder: 5, 30, 15, 20, 25, 10, 35
                TreeNode root = new TreeNode(20);
                root.left = new TreeNode(30);
                root.left.left = new TreeNode(5);
                root.left.right = new TreeNode(15);
                root.right = new TreeNode(10);
                root.right.left = new TreeNode(25);
                root.right.right = new TreeNode(35);
                return root;
            }
            case 7: {
                // Left skewed tree. Valid sequence: 1, 2, 3, 4, 5
                // Swap 1 and 5 -> 5, 4, 3, 2, 1
                TreeNode root = new TreeNode(1);
                root.left = new TreeNode(4);
                root.left.left = new TreeNode(3);
                root.left.left.left = new TreeNode(2);
                root.left.left.left.left = new TreeNode(5);
                return root;
            }
            case 8: {
                // Right skewed tree. Valid sequence: 10, 20, 30, 40, 50
                // Swap 20 and 30 -> 10, 30, 20, 40, 50
                TreeNode root = new TreeNode(10);
                root.right = new TreeNode(30);
                root.right.right = new TreeNode(20);
                root.right.right.right = new TreeNode(40);
                root.right.right.right.right = new TreeNode(50);
                return root;
            }
            case 9: {
                // Overflow traps: Min/Max values swapped.
                // Valid tree sequence: MIN_VALUE, 0, MAX_VALUE
                // Swap MIN and MAX -> MAX_VALUE, 0, MIN_VALUE
                TreeNode root = new TreeNode(0);
                root.left = new TreeNode(Integer.MAX_VALUE);
                root.right = new TreeNode(Integer.MIN_VALUE);
                return root;
            }
            case 10: {
                // Deep leaf node swap.
                // Valid tree sequence: 2, 4, 5, 6, 8, 10, 12, 14, 15
                // Swap leaf 2 and leaf 15 -> 15, 4, 5, 6, 8, 10, 12, 14, 2
                TreeNode root = new TreeNode(8);
                root.left = new TreeNode(5);
                root.left.left = new TreeNode(4);
                root.left.left.left = new TreeNode(15); // should be 2
                root.left.right = new TreeNode(6);

                root.right = new TreeNode(12);
                root.right.left = new TreeNode(10);
                root.right.right = new TreeNode(14);
                root.right.right.right = new TreeNode(2); // should be 15
                return root;
            }
            default:
                return null;
        }
    }

    private static List<Integer> getExpectedInorder(int tc) {
        switch (tc) {
            case 1: return Arrays.asList(1, 2, 3);
            case 2: return Arrays.asList(1, 2, 3, 4);
            case 3: return Arrays.asList(1, 2);
            case 4: return Arrays.asList(1, 2);
            case 5: return Arrays.asList(10, 20, 30, 40, 50);
            case 6: return Arrays.asList(5, 10, 15, 20, 25, 30, 35);
            case 7: return Arrays.asList(1, 2, 3, 4, 5);
            case 8: return Arrays.asList(10, 20, 30, 40, 50);
            case 9: return Arrays.asList(Integer.MIN_VALUE, 0, Integer.MAX_VALUE);
            case 10: return Arrays.asList(2, 4, 5, 6, 8, 10, 12, 14, 15);
            default: return new ArrayList<>();
        }
    }

    private static void fetchInorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        fetchInorder(node.left, list);
        list.add(node.val);
        fetchInorder(node.right, list);
    }

    private static void printInorder(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        fetchInorder(node, list);
        System.out.print(list);
    }
}