package BinarySearchTree;



import java.util.*;

public class MergeTwoBst {

    // =========================================================================
    // IMPLEMENT YOUR SOLUTION HERE
    // Feel free to modify or destroy the tree structures dynamically!
    // =========================================================================
    public List<Integer> merge(TreeNode root1, TreeNode root2) {
        TreeNode[]track=new TreeNode[]{null,null};
        convertList(root1,track);
        TreeNode head1=track[0];
        track[0]=track[1]=null;
        convertList(root2,track);
        TreeNode head2=track[0];
        TreeNode merged=mergeList(head1,head2);
        List<Integer>ans=new ArrayList<>();
        while(merged!=null){
            ans.add(merged.val);
            merged=merged.right;
        }
        return ans;
    }

    private void convertList(TreeNode root,TreeNode[]track){
        if(root==null)return;
        convertList(root.left,track);
        TreeNode prev=track[1];
        if(prev==null)track[0]=root;
        else{
            root.left=prev;
            prev.right=root;
        }
        track[1]=root;
        convertList(root.right,track);
    }

    private TreeNode mergeList(TreeNode root1,TreeNode root2){
        if(root1==null)return root2;
        if(root2==null)return root1;
        TreeNode dummy=new TreeNode(-1),curr1=root1,curr2=root2,prev=dummy;
        while(curr1!=null&&curr2!=null){
            if(curr1.val<=curr2.val){
                prev.right=curr1;
                curr1.left=prev;
                curr1=curr1.right;
            }else{
                prev.right=curr2;
                curr2.left=prev;
                curr2=curr2.right;
            }
            prev=prev.right;
        }
        if(curr1!=null)prev.right=curr1;
        if(curr2!=null)prev.right=curr2;
        return dummy.right;
    }

    // =========================================================================
    // TESTING FRAMEWORK & HARNESS (Deep-Copies Input Automatically)
    // =========================================================================
    public static void main(String[] args) {
        MergeTwoBst merger = new MergeTwoBst();
        int passed = 0;
        int totalTests = 10;

        for (int i = 1; i <= totalTests; i++) {
            TreeNode root1 = getFreshDeepCopy(i, true);
            TreeNode root2 = getFreshDeepCopy(i, false);
            List<Integer> expected = getExpectedOutput(i);

            System.out.println("--- Test Case " + i + " ---");
            System.out.println("Expected In-order: " + expected);

            List<Integer> actual = merger.merge(root1, root2);
            System.out.println("Actual In-order:   " + actual);

            if (expected.equals(actual)) {
                System.out.println("Result: PASSED\n");
                passed++;
            } else {
                System.out.println("Result: FAILED\n");
            }
        }

        System.out.println("========================================");
        System.out.println("Final Score: " + passed + " / " + totalTests + " passed.");
        System.out.println("========================================");
    }

    private static TreeNode deepCopyTree(TreeNode root) {
        if (root == null) return null;
        TreeNode copy = new TreeNode(root.val);
        copy.left = deepCopyTree(root.left);
        copy.right = deepCopyTree(root.right);
        return copy;
    }

    private static TreeNode getFreshDeepCopy(int id, boolean isTree1) {
        return deepCopyTree(getTestCaseRaw(id, isTree1));
    }

    private static TreeNode getTestCaseRaw(int id, boolean isTree1) {
        if (isTree1) {
            switch (id) {
                case 1: // Example 1
                    return new TreeNode(3, new TreeNode(1), new TreeNode(5));
                case 2: // Example 2
                    return new TreeNode(8, new TreeNode(2, new TreeNode(1), null), new TreeNode(10));
                case 3: // Edge Case: Both trees are empty
                    return null;
                case 4: // Edge Case: Tree 1 empty, Tree 2 populated
                    return null;
                case 5: // Single node boundary matches
                    return new TreeNode(15);
                case 6: // Disjoint ranges (Tree 1 entirely smaller than Tree 2)
                    return new TreeNode(5, new TreeNode(2), new TreeNode(7));
                case 7: // Interleaved duplicate values across trees
                    return new TreeNode(10, new TreeNode(5), new TreeNode(15));
                case 8: // Extreme skewed trees (Left vs Right)
                    return new TreeNode(30, new TreeNode(20, new TreeNode(10), null), null);
                case 9: // Large value constraints matching zero boundary
                    return new TreeNode(0);
                case 10: // Highly overlapping multi-level configuration
                    return new TreeNode(50, new TreeNode(30, new TreeNode(25), new TreeNode(35)), new TreeNode(70));
                default:
                    return null;
            }
        } else {
            switch (id) {
                case 1: // Example 1
                    return new TreeNode(4, new TreeNode(2), new TreeNode(6));
                case 2: // Example 2
                    return new TreeNode(5, new TreeNode(3, new TreeNode(0), null), null);
                case 3: // Edge Case: Both trees are empty
                    return null;
                case 4: // Edge Case: Tree 1 empty, Tree 2 populated
                    return new TreeNode(42, new TreeNode(21), new TreeNode(84));
                case 5: // Single node boundary matches
                    return new TreeNode(15);
                case 6: // Disjoint ranges (Tree 2 entirely larger)
                    return new TreeNode(25, new TreeNode(20), new TreeNode(30));
                case 7: // Interleaved duplicate values across trees
                    return new TreeNode(10, new TreeNode(5), new TreeNode(20));
                case 8: // Extreme skewed trees (Left vs Right)
                    return new TreeNode(40, null, new TreeNode(50, null, new TreeNode(60)));
                case 9: // Large value constraints matching zero boundary
                    return new TreeNode(100000);
                case 10: // Highly overlapping multi-level configuration
                    return new TreeNode(45, new TreeNode(28), new TreeNode(60, new TreeNode(55), new TreeNode(65)));
                default:
                    return null;
            }
        }
    }

    private static List<Integer> getExpectedOutput(int id) {
        switch (id) {
            case 1: return Arrays.asList(1, 2, 3, 4, 5, 6);
            case 2: return Arrays.asList(0, 1, 2, 3, 5, 8, 10);
            case 3: return Arrays.asList();
            case 4: return Arrays.asList(21, 42, 84);
            case 5: return Arrays.asList(15, 15);
            case 6: return Arrays.asList(2, 5, 7, 20, 25, 30);
            case 7: return Arrays.asList(5, 5, 10, 10, 15, 20);
            case 8: return Arrays.asList(10, 20, 30, 40, 50, 60);
            case 9: return Arrays.asList(0, 100000);
            case 10: return Arrays.asList(25, 28, 30, 35, 45, 50, 55, 60, 65, 70);
            default: return Arrays.asList();
        }
    }
}