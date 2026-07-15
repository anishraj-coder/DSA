package trees;

import java.util.*;

class BinaryTreeFlattener {


    /**
     * Flattens the binary tree into a linked list in-place using pre-order traversal.
     * The left child pointer of all nodes must be null, and the right child pointer
     * should point to the next node in the pre-order sequence.
     */
    public void flatten(TreeNode root) {
        helper(root);
    }


    private TreeNode helper(TreeNode root){
        if(root==null)return null;
        TreeNode lt=helper(root.left);
        TreeNode rt=helper(root.right);
        if(lt!=null&&rt!=null){
            TreeNode lc=root.left;
            TreeNode rc=root.right;
            lt.right=rc;
            root.right=lc;
            root.left=null;
            return rt;
        }else if(lt==null&&rt!=null)return rt;
        else if(lt!=null&&rt==null){
            root.right=root.left;
            root.left=null;
            return lt;
        }else return root;
    }

    // =========================================================================
    // TEST RUNNER CODE
    // =========================================================================

    public static void main(String[] args) {
        BinaryTreeFlattener flattener = new BinaryTreeFlattener();
        int passed = 0;
        int totalTests = 10;

        for (int i = 1; i <= totalTests; i++) {
            TreeNode root = getTestCase(i);
            List<Integer> expected = getExpectedOutput(i);

            // Execute user's implementation
            flattener.flatten(root);

            // Serialize the modified tree to a list for verification
            List<Integer> actual = serializeFlattenedTree(root);
            boolean isLeftNullValid = verifyLeftPointersAreNull(root);

            boolean testPassed = expected.equals(actual) && isLeftNullValid;
            if (!isLeftNullValid) {
                System.out.printf("Test %d: FAILED (Left pointers were not completely set to null)%n", i);
            } else if (testPassed) {
                System.out.printf("Test %d: PASSED%n", i);
                passed++;
            } else {
                System.out.printf("Test %d: FAILED%n", i);
            }
            System.out.println("   Expected: " + expected);
            System.out.println("   Actual:   " + actual);
            System.out.println("--------------------------------------------------");
        }

        System.out.printf("Result: %d/%d test cases passed.%n", passed, totalTests);
    }

    // Helper to verify that no node in the flattened sequence retains a left child
    private static boolean verifyLeftPointersAreNull(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                return false;
            }
            curr = curr.right;
        }
        return true;
    }

    // Helper to serialize the right-pointer chain into a List for comparison
    private static List<Integer> serializeFlattenedTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            result.add(curr.val);
            curr = curr.right;
        }
        return result;
    }

    // Generates 10 distinct test cases covering standard, extreme, and edge states
    private static TreeNode getTestCase(int id) {
        switch (id) {
            case 1: // Example 1: Standard balanced/mixed tree
                //       1
                //      / \
                //     2   5
                //    / \   \
                //   3   4   6
                return new TreeNode(1,
                        new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                        new TreeNode(5, null, new TreeNode(6))
                );

            case 2: // Example 2: Empty Tree (Edge Case)
                return null;

            case 3: // Example 3: Single Node (Edge Case)
                return new TreeNode(0);

            case 4: // Left-skewed tree (Deep recursion / Stack stressor)
                //     1
                //    /
                //   2
                //  /
                // 3
                return new TreeNode(1, new TreeNode(2, new TreeNode(3), null), null);

            case 5: // Right-skewed tree (Already flat)
                // 1
                //  \
                //   2
                //    \
                //     3
                return new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3)));

            case 6: // Complete Binary Tree with duplicate values
                //       5
                //      / \
                //     5   5
                //    / \ / \
                //   1  2 3  4
                return new TreeNode(5,
                        new TreeNode(5, new TreeNode(1), new TreeNode(2)),
                        new TreeNode(5, new TreeNode(3), new TreeNode(4))
                );

            case 7: // Node with only right-heavy subtrees on the left side
                //     1
                //    /
                //   2
                //    \
                //     3
                return new TreeNode(1, new TreeNode(2, null, new TreeNode(3)), null);

            case 8: // Node with only left-heavy subtrees on the right side
                //   1
                //    \
                //     2
                //    /
                //   3
                return new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));

            case 9: // Large value spans including negative bounds
                //      -100
                //      /   \
                //    50     100
                return new TreeNode(-100, new TreeNode(50), new TreeNode(100));

            case 10: // Zig-zag pattern tree
                //     1
                //    /
                //   2
                //    \
                //     3
                //    /
                //   4
                return new TreeNode(1,
                        new TreeNode(2, null, new TreeNode(3, new TreeNode(4), null)),
                        null
                );

            default:
                return null;
        }
    }

    // Pre-calculated correct Pre-order traversal outputs for verification
    private static List<Integer> getExpectedOutput(int id) {
        switch (id) {
            case 1: return Arrays.asList(1, 2, 3, 4, 5, 6);
            case 2: return Collections.emptyList();
            case 3: return Collections.singletonList(0);
            case 4: return Arrays.asList(1, 2, 3);
            case 5: return Arrays.asList(1, 2, 3);
            case 6: return Arrays.asList(5, 5, 1, 2, 5, 3, 4);
            case 7: return Arrays.asList(1, 2, 3);
            case 8: return Arrays.asList(1, 2, 3);
            case 9: return Arrays.asList(-100, 50, 100);
            case 10: return Arrays.asList(1, 2, 3, 4);
            default: return Collections.emptyList();
        }
    }
}