package linkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Definition for singly-linked list.
 */


public class ListMidpointFinder {

    /**
     * Finds the middle node of the linked list.
     * If there are two middle nodes, it returns the second middle node.
     * * @param head The head of the singly linked list.
     * @return The middle node of the list.
     */
    public ListNode findMiddle(ListNode head) {
        if(head==null)return head;
        if(head.next==null)return head;
        ListNode slow,fast=slow=head;
        while(fast!=null&& fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }

    // --- TEST RUNNER LOGIC ---

    public static void main(String[] args) {
        ListMidpointFinder finder = new ListMidpointFinder();

        // Test Cases: {Input Array, Expected Middle Value}
        Object[][] testCases = {
                {new int[]{1, 2, 3, 4, 5}, 3, "Odd length list"},
                {new int[]{1, 2, 3, 4, 5, 6}, 4, "Even length list (second middle)"},
                {new int[]{1}, 1, "Single element list"},
                {new int[]{1, 2}, 2, "Two elements (returns second)"},
                {new int[]{}, null, "Empty list"},
                {new int[]{10, 20, 30}, 20, "Multiples of 10"},
                {new int[]{1, 1, 1, 1, 1}, 1, "Identical elements"},
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 6, "Long even list"},
                {new int[]{5, 4, 3, 2, 1}, 3, "Descending values"},
                {new int[]{100, 200}, 200, "Two large values"}
        };

        int passed = 0;
        System.out.printf("%-5s | %-35s | %-10s | %-10s | %-8s%n", "ID", "Description", "Expected", "Actual", "Result");
        System.out.println("-".repeat(85));

        for (int i = 0; i < testCases.length; i++) {
            int[] inputArr = (int[]) testCases[i][0];
            Integer expectedVal = (Integer) testCases[i][1];
            String description = (String) testCases[i][2];

            ListNode head = buildList(inputArr);
            ListNode resultNode = finder.findMiddle(head);

            Integer actualVal = (resultNode == null) ? null : resultNode.val;

            boolean isMatch = (expectedVal == null && actualVal == null) ||
                    (expectedVal != null && expectedVal.equals(actualVal));

            // Optional: Extra verification to ensure it's the correct node (not just value)
            // by checking the remainder of the list.
            if (isMatch) passed++;

            System.out.printf("%-5d | %-35s | %-10s | %-10s | %-8s%n",
                    i + 1, description,
                    expectedVal == null ? "null" : expectedVal,
                    actualVal == null ? "null" : actualVal,
                    isMatch ? "PASSED" : "FAILED");
        }

        System.out.println("-".repeat(85));
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }

    private static ListNode buildList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }
}
