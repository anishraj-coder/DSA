package linkedList;

import java.util.*;

/**
 * Problem: 2095. Delete the Middle Node of a Linked List
 * Constraint: Delete the ⌊n / 2⌋th node.
 */
public class DeleteMiddleNode {

    public ListNode deleteMiddle(ListNode head) {
        if(head==null||head.next==null)return null;
        if(head.next.next==null){
            head.next=null;
            return head;
        }

        ListNode prev=null,slow=head,fast=head;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            prev=slow;
            slow=slow.next;
        }

        prev.next=prev.next.next;

        return head;
    }

    public static void main(String[] args) {
        DeleteMiddleNode solver = new DeleteMiddleNode();

        // Test Cases: {Input Array, Expected Output Array}
        Object[][] testCases = {
                {new int[]{1, 3, 4, 7, 1, 2, 6}, new int[]{1, 3, 4, 1, 2, 6}}, // Standard odd length (7 nodes, idx 3 removed)
                {new int[]{1, 2, 3, 4}, new int[]{1, 2, 4}},                // Standard even length (4 nodes, idx 2 removed)
                {new int[]{2, 1}, new int[]{2}},                             // Edge: Two nodes (idx 1 removed)
                {new int[]{1}, new int[]{}},                                // Edge: Single node (idx 0 removed)
                {new int[]{1, 2, 3}, new int[]{1, 3}},                      // Small odd (3 nodes, idx 1 removed)
                {new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 2, 3, 5, 6}},    // Even length (6 nodes, idx 3 removed)
                {new int[]{1, 1, 1}, new int[]{1, 1}},                      // Identical values
                {new int[]{4, 4, 4, 4}, new int[]{4, 4, 4}},                // Identical values even
                {new int[]{10, 20, 30, 40, 50, 60, 70, 80}, new int[]{10, 20, 30, 40, 60, 70, 80}}, // 8 nodes, idx 4 removed
                {new int[]{5, 7, 9, 11, 13}, new int[]{5, 7, 11, 13}}       // 5 nodes, idx 2 removed
        };

        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] inputArr = (int[]) testCases[i][0];
            int[] expectedArr = (int[]) testCases[i][1];

            ListNode head = buildList(inputArr);
            ListNode result = solver.deleteMiddle(head);
            int[] actualArr = listToArray(result);

            boolean isCorrect = Arrays.equals(actualArr, expectedArr);
            if (isCorrect) passed++;

            System.out.println("Test Case " + (i + 1) + ": " + (isCorrect ? "PASSED" : "FAILED"));
            System.out.println("   Input:    " + Arrays.toString(inputArr));
            System.out.println("   Expected: " + Arrays.toString(expectedArr));
            System.out.println("   Actual:   " + Arrays.toString(actualArr));
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Summary: " + passed + "/" + testCases.length + " cases passed.");
    }

    // Helper to build list from array
    private static ListNode buildList(int[] vals) {
        if (vals.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : vals) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper to convert list back to array for comparison
    private static int[] listToArray(ListNode head) {
        if (head == null) return new int[]{};
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}