package linkedList;

import java.util.*;

public class SortLinkedList {

    /**
     * Problem: Sort a linked list in O(n log n) time and O(1) space.
     * Implement the method below.
     */
    public ListNode sortList(ListNode head) {

        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head){
        if(head==null||head.next==null)return head;
        ListNode mid=findMid(head);
        ListNode next=mid.next;
        mid.next=null;
        ListNode first=mergeSort(head);
        ListNode second=mergeSort(next);
        return merge(first,second);
    }

    private ListNode merge(ListNode l1,ListNode l2){
        ListNode  temp=new ListNode(-1);
        ListNode newHead=temp;
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                temp.next=l1;
                l1=l1.next;
            }else{
                temp.next=l2;
                l2=l2.next;
            }
            temp=temp.next;
        }
        if(l1!=null)temp.next=l1;
        if(l2!=null)temp.next=l2;
        return newHead.next;
    }

    private ListNode findMid(ListNode head){
        ListNode slow=head,fast=head;
        while(fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }

    // --- Test Infrastructure ---

    public static void main(String[] args) {
        SortLinkedList runner = new SortLinkedList();

        // Defining test inputs
        List<int[]> testInputs = new ArrayList<>();
        testInputs.add(new int[]{4, 2, 1, 3});                   // Case 1: Standard
        testInputs.add(new int[]{-1, 5, 3, 4, 0});               // Case 2: Negative numbers
        testInputs.add(new int[]{});                             // Case 3: Empty (Edge)
        testInputs.add(new int[]{10});                           // Case 4: Single (Edge)
        testInputs.add(new int[]{1, 2, 3, 4, 5});                // Case 5: Already sorted
        testInputs.add(new int[]{5, 4, 3, 2, 1});                // Case 6: Reverse sorted
        testInputs.add(new int[]{2, 2, 1, 1, 2});                // Case 7: Duplicates
        testInputs.add(new int[]{0, 0, 0});                      // Case 8: All zeros
        testInputs.add(new int[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE}); // Case 9: Integer limits

        // Case 10: Large random set (Stress Test)
        int[] largeSet = new int[1000];
        Random rand = new Random(42);
        for(int i = 0; i < 1000; i++) largeSet[i] = rand.nextInt(2000) - 1000;
        testInputs.add(largeSet);

        int passed = 0;
        for (int i = 0; i < testInputs.size(); i++) {
            int[] inputArr = testInputs.get(i);

            // Prepare expected
            int[] expectedArr = inputArr.clone();
            Arrays.sort(expectedArr);

            // Execute
            ListNode head = createLinkedList(inputArr);
            ListNode result = runner.sortList(head);
            int[] actualArr = listToArray(result);

            boolean isMatch = Arrays.equals(actualArr, expectedArr);
            if (isMatch) passed++;

            System.out.println("Test Case " + (i + 1) + ": " + (isMatch ? "✅ PASSED" : "❌ FAILED"));
            if (inputArr.length <= 15) {
                System.out.println("   Input:    " + Arrays.toString(inputArr));
                System.out.println("   Expected: " + Arrays.toString(expectedArr));
                System.out.println("   Actual:   " + Arrays.toString(actualArr));
            } else {
                System.out.println("   Size: " + inputArr.length + " (Large input - details hidden)");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + testInputs.size() + " cases passed.");
    }

    private static ListNode createLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    private static int[] listToArray(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            list.add(current.val);
            current = current.next;
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) arr[i] = list.get(i);
        return arr;
    }
}