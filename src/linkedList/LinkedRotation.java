package linkedList;

import java.util.*;

public class LinkedRotation {

    /**
     * Rotates the linked list to the right by k places.
     * k can be larger than the length of the list.
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null||k==0||head.next==null)return head;
        ListNode tail=head;
        int n=1;
        while(tail.next!=null){
            n++;
            tail=tail.next;
        }
        k%=n;
        if(k==0)return head;
        tail.next=head;
        ListNode temp=head;
        for(int i=0;i<n-k-1;i++){
            temp=temp.next;
        }
        ListNode newHead=temp.next;
        temp.next=null;
        return newHead;
    }


    public static void main(String[] args) {
        LinkedRotation processor = new LinkedRotation();

        // Test Cases: {Input Array, k, Expected Output Array}
        List<Object[]> testCases = new ArrayList<>();

        // Standard cases
        testCases.add(new Object[]{new int[]{1, 2, 3, 4, 5}, 2, new int[]{4, 5, 1, 2, 3}});
        testCases.add(new Object[]{new int[]{0, 1, 2}, 4, new int[]{2, 0, 1}});

        // Edge: k is 0 (No rotation)
        testCases.add(new Object[]{new int[]{1, 2, 3}, 0, new int[]{1, 2, 3}});

        // Edge: Empty list
        testCases.add(new Object[]{new int[]{}, 10, new int[]{}});

        // Edge: Single element list
        testCases.add(new Object[]{new int[]{1}, 5, new int[]{1}});

        // Edge: k is exactly the length of the list
        testCases.add(new Object[]{new int[]{1, 2}, 2, new int[]{1, 2}});

        // Edge: k is a multiple of the length
        testCases.add(new Object[]{new int[]{1, 2, 3}, 6, new int[]{1, 2, 3}});

        // Edge: Large k value
        testCases.add(new Object[]{new int[]{1, 2}, 2000000001, new int[]{2, 1}});

        // Edge: List with two elements, k=1
        testCases.add(new Object[]{new int[]{10, 20}, 1, new int[]{20, 10}});

        // Edge: Rotating almost full circle
        testCases.add(new Object[]{new int[]{1, 2, 3, 4}, 3, new int[]{2, 3, 4, 1}});

        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            int[] inputArr = (int[]) testCases.get(i)[0];
            int k = (int) testCases.get(i)[1];
            int[] expectedArr = (int[]) testCases.get(i)[2];

            ListNode head = buildList(inputArr);
            ListNode result = processor.rotateRight(head, k);
            int[] actualArr = listToArray(result);

            boolean isMatch = Arrays.equals(expectedArr, actualArr);
            if (isMatch) passed++;

            System.out.println("Test Case " + (i + 1) + ": " + (isMatch ? "PASSED" : "FAILED"));
            System.out.println("   Input:    " + Arrays.toString(inputArr) + " (k=" + k + ")");
            System.out.println("   Expected: " + Arrays.toString(expectedArr));
            System.out.println("   Actual:   " + Arrays.toString(actualArr));
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Summary: " + passed + "/" + testCases.size() + " cases passed.");
    }

    // --- Helper Methods for Testing ---

    private static ListNode buildList(int[] vals) {
        if (vals == null || vals.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : vals) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    private static int[] listToArray(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}