package linkedList;

import java.util.*;

/**
 * Problem: 328. Odd Even Linked List
 * Constraints: O(1) space, O(n) time.
 */
public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {
        if(head==null||head.next==null||head.next.next==null)return head;

        ListNode odd=head,even=head.next,evenHead=even;

        while(even!=null&&even.next!=null){
            odd.next=even.next;
            odd=odd.next;
            even.next=odd.next;
            even=even.next;
        }
        odd.next=evenHead;
        return head;
    }

    public static void main(String[] args) {
        OddEvenLinkedList solver = new OddEvenLinkedList();

        // Test Case Definitions: {Input Array, Expected Output Array}
        Object[][] testCases = {
                {new int[]{1, 2, 3, 4, 5}, new int[]{1, 3, 5, 2, 4}},            // Standard odd length
                {new int[]{2, 1, 3, 5, 6, 4, 7}, new int[]{2, 3, 6, 7, 1, 5, 4}}, // Standard even length mixed vals
                {new int[]{}, new int[]{}},                                     // Edge: Empty list
                {new int[]{1}, new int[]{1}},                                   // Edge: Single node
                {new int[]{1, 2}, new int[]{1, 2}},                             // Edge: Two nodes
                {new int[]{1, 2, 3}, new int[]{1, 3, 2}},                       // Small odd list
                {new int[]{1, 2, 3, 4}, new int[]{1, 3, 2, 4}},                 // Small even list
                {new int[]{1, 1, 1, 1}, new int[]{1, 1, 1, 1}},                 // Identical values
                {new int[]{-1, -2, -3, -4, -5}, new int[]{-1, -3, -5, -2, -4}}, // Negative values
                {new int[]{10, 20, 30, 40, 50, 60}, new int[]{10, 30, 50, 20, 40, 60}} // Longer even list
        };

        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] inputArr = (int[]) testCases[i][0];
            int[] expectedArr = (int[]) testCases[i][1];

            ListNode head = buildList(inputArr);
            ListNode result = solver.oddEvenList(head);
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
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}