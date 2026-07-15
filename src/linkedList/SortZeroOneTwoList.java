package linkedList;

import java.util.*;

public class SortZeroOneTwoList {

    /**
     * Problem: Sort a linked list of 0s, 1s, and 2s by changing pointers.
     * Constraints: 1 <= N <= 10^3, data is 0, 1, or 2.
     * Implement the method below.
     */
    public ListNode sortList(ListNode head) {
        ListNode zeroDummy=new ListNode(-1);
        ListNode oneDummy=new ListNode(-1);
        ListNode twoDummy=new ListNode(-1);
        ListNode zeroTail=zeroDummy,oneTail=oneDummy,twoTail=twoDummy,curr=head;

        while(curr!=null){
            if(curr.val==0){
                zeroTail.next=curr;
                zeroTail=zeroTail.next;
            }else if(curr.val==1){
                oneTail.next=curr;
                oneTail=oneTail.next;
            }else{
                twoTail.next=curr;
                twoTail=twoTail.next;
            }
            curr=curr.next;
        }
        zeroTail.next=(oneDummy.next==null)?twoDummy.next:oneDummy.next;
        oneTail.next=twoDummy.next;
        twoTail.next=null;
        return zeroDummy.next;
    }


    // --- Test Infrastructure ---

    public static void main(String[] args) {
        SortZeroOneTwoList runner = new SortZeroOneTwoList();

        List<int[]> testInputs = new ArrayList<>();
        testInputs.add(new int[]{1, 0, 2, 1, 2});                // Example 1
        testInputs.add(new int[]{1, 0, 2, 1, 0, 2, 1});          // Sample 1
        testInputs.add(new int[]{2, 1, 0, 2, 1, 0, 0, 2});       // Sample 2
        testInputs.add(new int[]{0, 0, 0});                      // Edge: All zeros
        testInputs.add(new int[]{1, 1, 1});                      // Edge: All ones
        testInputs.add(new int[]{2, 2, 2});                      // Edge: All twos
        testInputs.add(new int[]{0, 1, 2});                      // Edge: Already sorted
        testInputs.add(new int[]{2, 1, 0});                      // Edge: Reverse sorted
        testInputs.add(new int[]{1});                            // Edge: Single node
        testInputs.add(new int[]{2, 0});                         // Edge: Missing one value (no 1s)
        testInputs.add(new int[]{0, 2, 0, 2});                   // Edge: Missing middle value
        testInputs.add(new int[]{1, 1, 0, 0});                   // Edge: Missing end value

        int passedCount = 0;
        for (int i = 0; i < testInputs.size(); i++) {
            int[] inputArr = testInputs.get(i);

            // Generate expected output by sorting a copy of the array
            int[] expectedArr = inputArr.clone();
            Arrays.sort(expectedArr);

            // Create list, run solution, convert back to array
            ListNode head = createLinkedList(inputArr);
            ListNode result = runner.sortList(head);
            int[] actualArr = listToArray(result);

            boolean isMatch = Arrays.equals(actualArr, expectedArr);
            if (isMatch) passedCount++;

            System.out.println("Test Case " + (i + 1) + ": " + (isMatch ? "✅ PASSED" : "❌ FAILED"));
            System.out.println("   Input:    " + Arrays.toString(inputArr));
            System.out.println("   Expected: " + Arrays.toString(expectedArr));
            System.out.println("   Actual:   " + Arrays.toString(actualArr));
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Result: " + passedCount + "/" + testInputs.size() + " cases passed.");
    }

    private static ListNode createLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    private static int[] listToArray(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr.val);
            curr = curr.next;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}