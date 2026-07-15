package linkedList;

import java.util.ArrayList;
import java.util.List;



public class LIstReorder {

    /**
     * Reorders the linked list in-place.
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     * @param head The head of the singly linked-list.
     */
    public void reorderList(ListNode head) {
        if(head==null)return ;
        if(head.next==null)return;
        if(head.next.next==null)return;

        ListNode slow=head,fast=head;

        while(fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        ListNode first=head,second=reverseList(slow.next);
        slow.next=null;

        while(first!=null&&second!=null){
            ListNode temp=first.next;
            ListNode temp2=second.next;
            first.next=second;
            second.next=temp;
            first=temp;
            second=temp2;
        }


    }

    public ListNode reverseList(ListNode head){
        ListNode  prev=null,curr=head;

        while(curr!=null){
            ListNode temp=curr.next;
            curr.next=prev;
            prev=curr;
            curr=temp;
        }
        return prev;
    }

    public static void main(String[] args) {
        LIstReorder manager = new LIstReorder();
        int passed = 0;
        int total = 10;

        // Test Case 1: Simple even length [1,2,3,4] -> [1,4,2,3]
        passed += runTest(1, new int[]{1, 2, 3, 4}, new int[]{1, 4, 2, 3}, manager);

        // Test Case 2: Simple odd length [1,2,3,4,5] -> [1,5,2,4,3]
        passed += runTest(2, new int[]{1, 2, 3, 4, 5}, new int[]{1, 5, 2, 4, 3}, manager);

        // Test Case 3: Single node [1] -> [1]
        passed += runTest(3, new int[]{1}, new int[]{1}, manager);

        // Test Case 4: Two nodes [1,2] -> [1,2]
        passed += runTest(4, new int[]{1, 2}, new int[]{1, 2}, manager);

        // Test Case 5: Three nodes [1,2,3] -> [1,3,2]
        passed += runTest(5, new int[]{1, 2, 3}, new int[]{1, 3, 2}, manager);

        // Test Case 6: Duplicate values [1,1,2,2] -> [1,2,1,2]
        passed += runTest(6, new int[]{1, 1, 2, 2}, new int[]{1, 2, 1, 2}, manager);

        // Test Case 7: Larger list (Even) [1,2,3,4,5,6] -> [1,6,2,5,3,4]
        passed += runTest(7, new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 6, 2, 5, 3, 4}, manager);

        // Test Case 8: All identical values
        passed += runTest(8, new int[]{7, 7, 7, 7}, new int[]{7, 7, 7, 7}, manager);

        // Test Case 9: Descending values [5,4,3,2,1] -> [5,1,4,2,3]
        passed += runTest(9, new int[]{5, 4, 3, 2, 1}, new int[]{5, 1, 4, 2, 3}, manager);

        // Test Case 10: Long list [1..10]
        passed += runTest(10, new int[]{1,2,3,4,5,6,7,8,9,10}, new int[]{1,10,2,9,3,8,4,7,5,6}, manager);

        System.out.println("\n---------------------------------------");
        System.out.println("Final Results: " + passed + " / " + total + " Passed");
        System.out.println("---------------------------------------");
    }

    private static int runTest(int id, int[] inputArr, int[] expectedArr, LIstReorder manager) {
        ListNode head = buildList(inputArr);
        manager.reorderList(head);
        int[] actualArr = listToArray(head);

        boolean isMatch = java.util.Arrays.equals(actualArr, expectedArr);

        System.out.println("Test Case " + id + ": " + (isMatch ? "PASSED ✅" : "FAILED ❌"));
        System.out.println("   Input:    " + java.util.Arrays.toString(inputArr));
        System.out.println("   Expected: " + java.util.Arrays.toString(expectedArr));
        System.out.println("   Actual:   " + java.util.Arrays.toString(actualArr));
        System.out.println();

        return isMatch ? 1 : 0;
    }

    private static ListNode buildList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : arr) {
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