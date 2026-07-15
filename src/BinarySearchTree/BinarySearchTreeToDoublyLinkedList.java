package BinarySearchTree;

import java.util.*;

public class BinarySearchTreeToDoublyLinkedList {



    // =========================================================================
    // IMPLEMENT YOUR SOLUTION HERE
    // =========================================================================
    public TreeNode convertToDLL(TreeNode root) {
        if(root==null)return null;
        if(root.left==null&&root.right==null)return root;
        TreeNode[]track=new TreeNode[]{null,null};
        helper(root,track);
        return track[0];
    }

    private void helper(TreeNode root,TreeNode[]track){
        if(root==null)return;

        helper(root.left,track);
        TreeNode prev=track[1];
        if(prev==null)track[0]=root;
        else{
            root.left=prev;
            prev.right=root;
        }

        track[1]=root;

        helper(root.right,track);
    }

    // =========================================================================
    // TESTING FRAMEWORK & HARNESS
    // =========================================================================
    public static void main(String[] args) {
        BinarySearchTreeToDoublyLinkedList converter = new BinarySearchTreeToDoublyLinkedList();
        int passed = 0;
        int totalTests = 10;

        for (int i = 1; i <= totalTests; i++) {
            TreeNode root = getTestCaseRoot(i);
            List<Integer> expected = getExpectedOutput(i);

            System.out.println("--- Test Case " + i + " ---");
            System.out.println("Expected DLL sequence: " + expected);

            TreeNode resultHead = converter.convertToDLL(root);
            List<Integer> actualForward = serializeDLLForward(resultHead);
            List<Integer> actualBackward = serializeDLLBackward(resultHead);

            System.out.println("Actual DLL sequence:   " + actualForward);

            boolean isCorrect = verifyDLL(expected, actualForward, actualBackward);
            if (isCorrect) {
                System.out.println("Result: PASSED\n");
                passed++;
            } else {
                System.out.println("Result: FAILED");
                if (actualForward.size() == expected.size() && !actualBackward.isEmpty()) {
                    System.out.println("-> Hint: Check if backward ('left') pointers or circular loops are broken.");
                }
                System.out.println();
            }
        }

        System.out.println("========================================");
        System.out.println("Final Score: " + passed + " / " + totalTests + " passed.");
        System.out.println("========================================");
    }

    private static boolean verifyDLL(List<Integer> expected, List<Integer> forward, List<Integer> backward) {
        if (!expected.equals(forward)) return false;

        // Ensure backward traversal matches reverse forward traversal
        List<Integer> expectedReverse = new ArrayList<>(expected);
        Collections.reverse(expectedReverse);
        return expectedReverse.equals(backward);
    }

    private static List<Integer> serializeDLLForward(TreeNode head) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = head;
        Set<TreeNode> visited = new HashSet<>(); // Guard against infinite loop configurations
        while (curr != null) {
            if (visited.contains(curr)) {
                res.add(-99999); // Sentinel indicating cycle detected
                break;
            }
            visited.add(curr);
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    private static List<Integer> serializeDLLBackward(TreeNode head) {
        List<Integer> res = new ArrayList<>();
        if (head == null) return res;

        TreeNode curr = head;
        Set<TreeNode> visited = new HashSet<>();
        // Find the tail first using right pointers safely
        while (curr.right != null && !visited.contains(curr.right)) {
            visited.add(curr);
            curr = curr.right;
        }

        // Traverse backwards using left pointers
        visited.clear();
        while (curr != null) {
            if (visited.contains(curr)) {
                res.add(-99999);
                break;
            }
            visited.add(curr);
            res.add(curr.val);
            curr = curr.left;
        }
        return res;
    }

    private static TreeNode getTestCaseRoot(int id) {
        switch (id) {
            case 1: // Edge Case: Empty tree
                return null;

            case 2: // Edge Case: Single node
                return new TreeNode(42);

            case 3: // Standard balanced BST
                //      4
                //    /   \
                //   2     5
                //  / \
                // 1   3
                return new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(5));

            case 4: // Edge Case: Left skewed tree (Degenerate)
                //     5
                //    /
                //   4
                //  /
                // 3
                return new TreeNode(5, new TreeNode(4, new TreeNode(3), null), null);

            case 5: // Edge Case: Right skewed tree (Degenerate)
                // 1
                //  \
                //   2
                //    \
                //     3
                return new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3)));

            case 6: // Complex structural changes (Zig-Zag tree)
                //     10
                //    /
                //   5
                //    \
                //     8
                //    /
                //   7
                return new TreeNode(10, new TreeNode(5, null, new TreeNode(8, new TreeNode(7), null)), null);

            case 7: // Handling Negative Values
                //      0
                //    /   \
                //  -10    5
                //  /
                // -20
                return new TreeNode(0, new TreeNode(-10, new TreeNode(-20), null), new TreeNode(5));

            case 8: // Large duplicate structure look-alike (Strict BST rules maintained)
                //       50
                //     /    \
                //    30     70
                //   /  \   /  \
                //  20  40 60  80
                return new TreeNode(50,
                        new TreeNode(30, new TreeNode(20), new TreeNode(40)),
                        new TreeNode(70, new TreeNode(60), new TreeNode(80))
                );

            case 9: // Deeply nested linear links requiring precise pointer updates
                //       1
                //        \
                //         5
                //        /
                //       2
                //        \
                //         4
                //        /
                //       3
                return new TreeNode(1, null, new TreeNode(5, new TreeNode(2, null, new TreeNode(4, new TreeNode(3), null)), null));

            case 10: // Extreme balance boundary layout
                //        15
                //      /    \
                //     6      18
                //    / \     /
                //   3   7   17
                //    \   \
                //     4   13
                //        /
                //       9
                return new TreeNode(15,
                        new TreeNode(6, new TreeNode(3, null, new TreeNode(4)), new TreeNode(7, null, new TreeNode(13, new TreeNode(9), null))),
                        new TreeNode(18, new TreeNode(17), null)
                );
            default:
                return null;
        }
    }

    private static List<Integer> getExpectedOutput(int id) {
        switch (id) {
            case 1: return Arrays.asList();
            case 2: return Arrays.asList(42);
            case 3: return Arrays.asList(1, 2, 3, 4, 5);
            case 4: return Arrays.asList(3, 4, 5);
            case 5: return Arrays.asList(1, 2, 3);
            case 6: return Arrays.asList(5, 7, 8, 10);
            case 7: return Arrays.asList(-20, -10, 0, 5);
            case 8: return Arrays.asList(20, 30, 40, 50, 60, 70, 80);
            case 9: return Arrays.asList(1, 2, 3, 4, 5);
            case 10: return Arrays.asList(3, 4, 6, 7, 9, 13, 15, 17, 18);
            default: return Arrays.asList();
        }
    }
}