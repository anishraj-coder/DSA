package linkedList;

import java.util.*;

public class LinkedListCycleEntryPoint {

    /**
     * Finds the node where the cycle begins.
     * @param head The head of the singly linked list.
     * @return     The node where the cycle starts, or null if no cycle exists.
     */
    public ListNode detectCycle(ListNode head) {
        if(head==null||head.next==null)return null;
        if(head.next==head)return head;
        ListNode slow=head,fast=head;
        while(fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(slow==fast)break;
        }
        if(slow!=fast)return null;

        fast=head;
        while(fast!=slow){
            fast=fast.next;
            slow=slow.next;
        }

        return slow;
    }



    // --- Testing Infrastructure ---

    public static void main(String[] args) {
        LinkedListCycleEntryPoint detector = new LinkedListCycleEntryPoint();
        detector.runTests();
    }

    private void runTests() {
        int passed = 0;
        int total = 10;

        // Test cases: {Array elements}, {pos: index where tail connects}
        Object[][] cases = {
                {new int[]{3, 2, 0, -4}, 1},    // 1. Standard cycle (Sample 1)
                {new int[]{1, 2}, 0},           // 2. Cycle at head (Sample 2)
                {new int[]{1}, -1},             // 3. Single node, no cycle
                {new int[]{}, -1},              // 4. Empty list
                {new int[]{1, 2, 3, 4, 5}, 2},  // 5. Cycle in middle
                {new int[]{1, 2, 3, 4, 5}, 4},  // 6. Tail self-loop
                {new int[]{10, 20, 30}, -1},    // 7. No cycle
                {new int[]{1, 1, 1}, 0},        // 8. Duplicate values, cycle at head
                {new int[]{7, 8, 9, 10}, 3},    // 9. Cycle at the very end
                {new int[50], 10}               // 10. Larger list
        };

        for (int i = 0; i < total; i++) {
            int[] vals = (int[]) cases[i][0];
            int pos = (int) cases[i][1];

            ListNode head = createListWithCycle(vals, pos);

            // Expected is the node at index 'pos'
            ListNode expectedNode = null;
            if (pos != -1) {
                ListNode temp = head;
                for (int j = 0; j < pos; j++) temp = temp.next;
                expectedNode = temp;
            }

            ListNode actualNode = detectCycle(head);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input:    " + (vals.length == 0 ? "[]" : Arrays.toString(vals)) + ", pos = " + pos);

            boolean isCorrect = (actualNode == expectedNode);

            // For output, we print the value of the node if it exists
            String expectedVal = (expectedNode == null) ? "null" : String.valueOf(expectedNode.val);
            String actualVal = (actualNode == null) ? "null" : String.valueOf(actualNode.val);

            System.out.println("Expected Entry Node Val: " + expectedVal);
            System.out.println("Actual Entry Node Val:   " + actualVal);

            if (isCorrect) {
                System.out.println("Status:   PASSED");
                passed++;
            } else {
                System.out.println("Status:   FAILED");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Results: " + passed + "/" + total + " cases passed.");
    }

    private ListNode createListWithCycle(int[] vals, int pos) {
        if (vals == null || vals.length == 0) return null;
        ListNode head = new ListNode(vals[0]);
        ListNode curr = head;
        ListNode cycleNode = (pos == 0) ? head : null;

        for (int i = 1; i < vals.length; i++) {
            curr.next = new ListNode(vals[i]);
            curr = curr.next;
            if (i == pos) cycleNode = curr;
        }

        if (pos != -1 && cycleNode != null) curr.next = cycleNode;
        return head;
    }
}