package linkedList;

import java.util.*;

/**
 * Problem: 19. Remove Nth Node From End of List
 * Constraint: Could you do this in one pass?
 */
public class RemoveNthNodeFromEnd {

    private ListNode reverse(ListNode head){
        ListNode curr=head,prev=null;
        while(curr!=null){
            ListNode next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        head=reverse(head);
        if(n==1){
            head=head.next;
            return reverse(head);
        }
        int count=1;
        ListNode curr=head;
        while(count<n-1&&curr.next!=null){
            curr=curr.next;
            count++;
        }
        if(curr.next==null) {
            head=reverse(head);
            return head.next;
        }

        curr.next=curr.next.next;

        return reverse(head);
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEnd solver = new RemoveNthNodeFromEnd();

        // Test Cases: {Input Array, n, Expected Output Array}
        Object[][] testCases = {
                {new int[]{1, 2, 3, 4, 5}, 2, new int[]{1, 2, 3, 5}}, // Standard case
                {new int[]{1}, 1, new int[]{}},                      // Edge: Single node, remove only node
                {new int[]{1, 2}, 1, new int[]{1}},                   // Edge: Two nodes, remove last
                {new int[]{1, 2}, 2, new int[]{2}},                   // Edge: Two nodes, remove first (head)
                {new int[]{1, 2, 3}, 3, new int[]{2, 3}},             // Remove head of list
                {new int[]{1, 2, 3}, 1, new int[]{1, 2}},             // Remove tail of list
                {new int[]{1, 2, 3, 4}, 2, new int[]{1, 2, 4}},       // Remove middle-back
                {new int[]{10, 20, 30, 40, 50, 60}, 4, new int[]{10, 20, 40, 50, 60}}, // Generic mid
                {new int[]{5, 10, 15}, 2, new int[]{5, 15}},          // Remove exactly middle
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10, new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10}} // Remove head of long list
        };

        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] inputArr = (int[]) testCases[i][0];
            int n = (int) testCases[i][1];
            int[] expectedArr = (int[]) testCases[i][2];

            ListNode head = buildList(inputArr);
            ListNode result = solver.removeNthFromEnd(head, n);
            int[] actualArr = listToArray(result);

            boolean isCorrect = Arrays.equals(actualArr, expectedArr);
            if (isCorrect) passed++;

            System.out.println("Test Case " + (i + 1) + ": " + (isCorrect ? "PASSED" : "FAILED"));
            System.out.println("   Input:    " + Arrays.toString(inputArr) + ", n = " + n);
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