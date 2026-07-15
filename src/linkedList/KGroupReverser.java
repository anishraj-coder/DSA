package linkedList;

import java.util.*;

public class KGroupReverser {

    /**
     * Reverses the nodes of the list k at a time and returns the modified list.
     * If the number of nodes is not a multiple of k, left-out nodes remain as is.
     * Constraints: O(1) extra memory space is the goal.
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp=head,prevNode=null,nextNode=null;
        while(temp!=null){
            ListNode kth=findKth(temp,k);
            if(kth==null){
                if(prevNode!=null) prevNode.next=temp;
                break;
            }
            nextNode=kth.next;
            kth.next=null;
            ListNode newHead=reverse(temp);

            if(temp==head)head=newHead;
            else {
                prevNode.next=newHead;
            }
            prevNode= temp;
            temp=nextNode;
        }
        return head;
    }

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

    private ListNode findKth(ListNode head,int k){
        int count=1;
        while(head!=null&&count<k){
            head=head.next;
            count++;
        }
        return head;
    }

    public static void main(String[] args) {
        KGroupReverser processor = new KGroupReverser();

        // Test Cases: {Input Array, k, Expected Output Array}
        List<Object[]> testCases = new ArrayList<>();

        // Standard cases
        testCases.add(new Object[]{new int[]{1, 2, 3, 4, 5}, 2, new int[]{2, 1, 4, 3, 5}});
        testCases.add(new Object[]{new int[]{1, 2, 3, 4, 5}, 3, new int[]{3, 2, 1, 4, 5}});

        // Edge: k = 1 (No change)
        testCases.add(new Object[]{new int[]{1, 2, 3}, 1, new int[]{1, 2, 3}});

        // Edge: k = length of list
        testCases.add(new Object[]{new int[]{1, 2, 3, 4}, 4, new int[]{4, 3, 2, 1}});

        // Edge: List length is exact multiple of k
        testCases.add(new Object[]{new int[]{1, 2, 3, 4, 5, 6}, 3, new int[]{3, 2, 1, 6, 5, 4}});

        // Edge: Only one element
        testCases.add(new Object[]{new int[]{1}, 1, new int[]{1}});

        // Edge: Large k, small list (Should stay same)
        testCases.add(new Object[]{new int[]{1, 2}, 3, new int[]{1, 2}});

        // Edge: Remaining nodes less than k at the end
        testCases.add(new Object[]{new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 3, new int[]{3, 2, 1, 6, 5, 4, 7, 8}});

        // Edge: All same values
        testCases.add(new Object[]{new int[]{5, 5, 5, 5}, 2, new int[]{5, 5, 5, 5}});

        // Edge: Max length constraints simulation (Short version)
        testCases.add(new Object[]{new int[]{10, 20, 30, 40, 50}, 2, new int[]{20, 10, 40, 30, 50}});

        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            int[] inputArr = (int[]) testCases.get(i)[0];
            int k = (int) testCases.get(i)[1];
            int[] expectedArr = (int[]) testCases.get(i)[2];

            ListNode head = buildList(inputArr);
            ListNode result = processor.reverseKGroup(head, k);
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