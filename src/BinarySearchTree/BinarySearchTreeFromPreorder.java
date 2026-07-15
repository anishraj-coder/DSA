package BinarySearchTree;

import java.util.*;

public class BinarySearchTreeFromPreorder {

    /**
     * Constructs a Binary Search Tree from its preorder traversal.
     *
     * Time Complexity Goal: O(N) or O(N log N)
     * Space Complexity Goal: O(N) for the tree structure and recursion stack
     *
     * @param pre An array of integers representing the preorder traversal of a BST.
     * @return The root node of the constructed BST.
     */
    public TreeNode bstFromPreorder(int[] pre) {
        if(pre.length==0)return null;
        if(pre.length==1)return new TreeNode(pre[0]);

        return construct(pre,new int[1],Integer.MAX_VALUE);
    }

    private TreeNode construct(int[]pre,int[]i,int max){
        if(i[0]==pre.length||pre[i[0]]>max)return null;
        TreeNode root=new TreeNode(pre[i[0]++]);
        root.left=construct(pre,i,root.val);
        root.right=construct(pre,i,max);
        return root;
    }

    // ==========================================
    // TEST RUNNER & UTILITIES (Do not modify)
    // ==========================================

    public static void main(String[] args) {
        BinarySearchTreeFromPreorder program = new BinarySearchTreeFromPreorder();

        List<TestCase> testCases = createTestCases();
        int passed = 0;

        System.out.println("Running Tests...\n");
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            TreeNode result = program.bstFromPreorder(tc.input);
            List<Integer> actual = serializeTree(result);

            boolean isMatch = tc.expected.equals(actual);
            if (isMatch) {
                passed++;
                System.out.printf("Test %d: PASSED\n", i + 1);
            } else {
                System.out.printf("Test %d: FAILED\n", i + 1);
                System.out.println("  Input:    " + Arrays.toString(tc.input));
                System.out.println("  Expected: " + tc.expected);
                System.out.println("  Actual:   " + actual);
            }
            System.out.println("----------------------------------------");
        }

        System.out.printf("\nResult: %d/%d Tests Passed.\n", passed, testCases.size());
    }

    private static class TestCase {
        int[] input;
        List<Integer> expected;

        TestCase(int[] input, Integer[] expectedArr) {
            this.input = input;
            this.expected = Arrays.asList(expectedArr);
        }
    }

    private static List<TestCase> createTestCases() {
        List<TestCase> cases = new ArrayList<>();

        // 1. Standard Example 1
        cases.add(new TestCase(
                new int[]{8, 5, 1, 7, 10, 12},
                new Integer[]{8, 5, 10, 1, 7, null, 12}
        ));

        // 2. Standard Example 2
        cases.add(new TestCase(
                new int[]{1, 3},
                new Integer[]{1, null, 3}
        ));

        // 3. Single Node (Minimal constraint edge case)
        cases.add(new TestCase(
                new int[]{50},
                new Integer[]{50}
        ));

        // 4. Strictly Decreasing (Left-skewed tree / line)
        cases.add(new TestCase(
                new int[]{5, 4, 3, 2, 1},
                new Integer[]{5, 4, null, 3, null, 2, null, 1}
        ));

        // 5. Strictly Increasing (Right-skewed tree / line)
        cases.add(new TestCase(
                new int[]{1, 2, 3, 4, 5},
                new Integer[]{1, null, 2, null, 3, null, 4, null, 5}
        ));

        // 6. Balanced Complete Tree
        cases.add(new TestCase(
                new int[]{4, 2, 1, 3, 6, 5, 7},
                new Integer[]{4, 2, 6, 1, 3, 5, 7}
        ));

        // 7. Zig-Zag path (Alternating left and right)
        cases.add(new TestCase(
                new int[]{10, 5, 8, 6, 7},
                new Integer[]{10, 5, null, null, 8, 6, null, null, 7}
        ));

        // 8. Large Jump / Unbalanced elements
        cases.add(new TestCase(
                new int[]{100, 20, 10, 30, 40, 200},
                new Integer[]{100, 20, 200, 10, 30, null, null, null, null, null, 40}
        ));

        // 9. Root with only a deep left subtree
        cases.add(new TestCase(
                new int[]{50, 30, 20, 40},
                new Integer[]{50, 30, null, 20, 40}
        ));

        // 10. Root with only a deep right subtree
        cases.add(new TestCase(
                new int[]{10, 20, 15, 30, 25},
                new Integer[]{10, null, 20, 15, 30, null, null, 25}
        ));

        return cases;
    }

    /**
     * Serializes a binary tree to a LeetCode-style level-order list representation.
     * Trailing nulls are stripped to match the expected format exactly.
     */
    private static List<Integer> serializeTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            } else {
                result.add(null);
            }
        }

        // Strip trailing null elements safely
        int i = result.size() - 1;
        while (i >= 0 && result.get(i) == null) {
            result.remove(i);
            i--;
        }
        return result;
    }
}