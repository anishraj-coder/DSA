package BinarySearchTree;

import java.util.*;

// Definition for singly-linked list.



public class SortedListToBinarySearchTree {

    /**
     * Converts a sorted singly linked list into a height-balanced binary search tree.
     *
     * Time Complexity Goal: O(N log N) or O(N)
     * Space Complexity Goal: O(log N) recursion stack space
     *
     * @param head The head of the sorted singly linked list.
     * @return The root node of the height-balanced BST.
     */
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null)return null;
        if(head.next==null)return new TreeNode(head.val);

        return construct(head,null);
    }

    private TreeNode construct(ListNode head,ListNode end){
        if(head==null)return null;
        if(head==end)return null;
        ListNode s,f;
        s=f=head;
        while(f!=end&&f.next!=end){
            s=s.next;
            f=f.next.next;
        }
        TreeNode root=new TreeNode(s.val);
        root.left=construct(head,s);
        root.right=construct(s.next,end);
        return root;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    // ==========================================
    // TEST RUNNER & UTILITIES (Do not modify)
    // ==========================================

    public static void main(String[] args) {
        SortedListToBinarySearchTree program = new SortedListToBinarySearchTree();

        List<TestCase> testCases = createTestCases();
        int passed = 0;

        System.out.println("Running Tests...\n");
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            ListNode head = buildLinkedList(tc.input);
            TreeNode result = program.sortedListToBST(head);
            List<Integer> actual = serializeTree(result);

            boolean isMatch = tc.expected.equals(actual);
            if (isMatch) {
                passed++;
                System.out.printf("Test %d: PASSED\n", i + 1);
            } else {
                System.out.printf("Test %d: FAILED\n", i + 1);
                System.out.println("  Input List: " + Arrays.toString(tc.input));
                System.out.println("  Expected:   " + tc.expected);
                System.out.println("  Actual:     " + actual);
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

    private static ListNode buildLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    private static List<TestCase> createTestCases() {
        List<TestCase> cases = new ArrayList<>();

        // 1. Standard Example 1
        cases.add(new TestCase(
                new int[]{-10, -3, 0, 5, 9},
                new Integer[]{0, -3, 9, -10, null, 5}
        ));

        // 2. Empty List Edge Case
        cases.add(new TestCase(
                new int[]{},
                new Integer[]{}
        ));

        // 3. Single Node Element
        cases.add(new TestCase(
                new int[]{42},
                new Integer[]{42}
        ));

        // 4. Exactly Two Elements
        cases.add(new TestCase(
                new int[]{1, 2},
                new Integer[]{2, 1}
        ));

        // 5. Exactly Three Elements (Perfectly Balanced Root)
        cases.add(new TestCase(
                new int[]{10, 20, 30},
                new Integer[]{20, 10, 30}
        ));

        // 6. Four Elements (Tests left/right assignment bias on even sizes)
        cases.add(new TestCase(
                new int[]{1, 2, 3, 4},
                new Integer[]{3, 2, 4, 1}
        ));

        // 7. Perfect Full Binary Tree Profile (7 elements)
        cases.add(new TestCase(
                new int[]{-5, -2, 0, 3, 7, 12, 18},
                new Integer[]{3, -2, 12, -5, 0, 7, 18}
        ));

        // 8. Contains Duplicate Elements (Valid per constraints)
        cases.add(new TestCase(
                new int[]{1, 1, 1, 2, 2},
                new Integer[]{1, 1, 2, null, 1, null, 2}
        ));

        // 9. Large Gaps Between Consecutive Numbers
        cases.add(new TestCase(
                new int[]{-1000, -999, 0, 5000, 5001},
                new Integer[]{0, -999, 5001, -1000, null, 5000}
        ));

        // 10. Nine Elements (Deeper height testing)
        cases.add(new TestCase(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},
                new Integer[]{5, 3, 8, 2, 4, 7, 9, 1, null, null, null, 6}
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

        int i = result.size() - 1;
        while (i >= 0 && result.get(i) == null) {
            result.remove(i);
            i--;
        }
        return result;
    }
}