package linkedList;

import java.util.*;

public class LinkedListCycle {

    /**
     * Determines if a linked list has a cycle.
     * @param head The head of the singly linked list.
     * @return     True if there is a cycle, false otherwise.
     */
    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null)return false;
        if(head.next==head)return true;
        ListNode slow=head,fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)return true;
        }

        return false;
    }

    // --- Testing Infrastructure ---

    public static void main(String[] args) {
        LinkedListCycle detector = new LinkedListCycle();
        detector.runTests();
    }

    private void runTests() {
        int passed = 0;
        int total = 9;

        // Test cases: {Array elements}, {pos: index where tail connects, -1 for no cycle}
        Object[][] cases = {
                {new int[]{3, 2, 0, -4}, 1},    // 1. Standard cycle (Sample 1)
                {new int[]{1, 2}, 0},           // 2. Small cycle (Sample 2)
                {new int[]{1}, -1},             // 3. Single node, no cycle
                {new int[]{1, 2, 3, 4, 5}, -1}, // 5. Long list, no cycle
                {new int[]{1, 2, 3, 4, 5}, 4},  // 6. Tail connects to itself (self-loop)
                {new int[]{1, 2, 3, 4, 5}, 0},  // 7. Tail connects to head
                {new int[]{1, 1, 1, 1}, 2},     // 8. Duplicate values with cycle
                {new int[]{-10, -20}, -1},      // 9. Negative values, no cycle
                {new int[100], 50}              // 10. Large list with cycle in the middle
        };

        for (int i = 0; i < total; i++) {
            int[] vals = (int[]) cases[i][0];
            int pos = (int) cases[i][1];
            String inputDisplay = (vals.length > 0) ? Arrays.toString(vals) : "[]";

            ListNode head = createListWithCycle(vals, pos);
            boolean expected = (pos != -1);

            long startTime = System.nanoTime();
            boolean actual = hasCycle(head);
            long endTime = System.nanoTime();

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input:    " + inputDisplay + ", pos = " + pos);
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);

            if (actual == expected) {
                System.out.println("Status:   PASSED");
                passed++;
            } else {
                System.out.println("Status:   FAILED");
            }
            System.out.println("Time:     " + (endTime - startTime) / 1000 + " µs");
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Results: " + passed + "/" + total + " cases passed.");
    }

    // Helper to build the list and create the cycle link
    private ListNode createListWithCycle(int[] vals, int pos) {
        if (vals.length == 0) return null;

        ListNode head = new ListNode(vals[0]);
        ListNode curr = head;
        ListNode cycleNode = null;

        if (pos == 0) cycleNode = head;

        for (int i = 1; i < vals.length; i++) {
            curr.next = new ListNode(vals[i]);
            curr = curr.next;
            if (i == pos) {
                cycleNode = curr;
            }
        }

        if (pos != -1 && cycleNode != null) {
            curr.next = cycleNode; // Create the cycle
        }

        return head;
    }
}