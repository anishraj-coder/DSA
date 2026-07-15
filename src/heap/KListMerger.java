package heap;

import linkedList.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;


public class KListMerger {

    /**
     * Merges k sorted linked-lists into one sorted linked-list.
     * IMPLEMENT YOUR SOLUTION HERE.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head=new ListNode(-1);
        ListNode curr=head;
        int k=lists.length;
        PriorityQueue<ListNode>pq=new PriorityQueue<>((a,b)->a.val-b.val);
        for(ListNode root:lists){
            if(root!=null)pq.offer(root);
        }

        while(!pq.isEmpty()){
            ListNode res=pq.poll();
            curr.next=res;
            curr=curr.next;
            if(res.next!=null){
                pq.offer(res.next);
                res=res.next;
            }
        }

        return head.next;
    }


    // =========================================================================
    // TESTING HARNESS (Do not modify below this line)
    // =========================================================================

    public static void main(String[] args) {
        KListMerger driver = new KListMerger();
        int totalTests = 10;
        int passedTests = 0;

        System.out.println("Running verification tests...\n");

        for (int i = 1; i <= totalTests; i++) {
            ListNode[] input = getTestCaseInput(i);
            List<Integer> expected = getTestCaseExpected(i);

            ListNode resultHead = driver.mergeKLists(input);
            List<Integer> actual = linkedListToList(resultHead);

            if (actual.equals(expected)) {
                System.out.println("Test Case " + i + ": PASSED");
                passedTests++;
            } else {
                System.out.println("Test Case " + i + ": FAILED");
                System.out.println("   Expected: " + expected);
                System.out.println("   Actual:   " + actual);
            }
        }

        System.out.println("\n-----------------------------------------");
        System.out.println("Execution Summary: " + passedTests + " / " + totalTests + " Test Cases Passed.");
        System.out.println("-----------------------------------------");
    }

    private static ListNode[] getTestCaseInput(int caseNum) {
        switch (caseNum) {
            case 1: // Example 1: Regular mix
                return createLists(new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}});
            case 2: // Example 2: Completely empty array
                return new ListNode[0];
            case 3: // Example 3: Array containing one empty list
                return new ListNode[]{null};
            case 4: // Array containing multiple empty lists
                return new ListNode[]{null, null, null};
            case 5: // Negative numbers and interleaving
                return createLists(new int[][]{{-10, -5, 0}, {-9, -2, 1, 5}, {-3, 2}});
            case 6: // Heavily duplicated values across all lists
                return createLists(new int[][]{{1, 1, 1}, {1, 1}, {1, 1, 1, 1}});
            case 7: // Single elements in multiple lists
                return createLists(new int[][]{{5}, {3}, {8}, {1}, {0}});
            case 8: // One very long list, others are empty or short
                return createLists(new int[][]{{1, 2, 3, 4, 5, 6, 7}, {}, {3, 4}, null});
            case 9: // Disjoint ranges (No overlaps)
                return createLists(new int[][]{{1, 2, 3}, {10, 11, 12}, {100, 101}});
            case 10: // Alternating elements (Strictly sequential interleaving)
                return createLists(new int[][]{{1, 4, 7}, {2, 5, 8}, {3, 6, 9}});
            default:
                return new ListNode[0];
        }
    }

    private static List<Integer> getTestCaseExpected(int caseNum) {
        switch (caseNum) {
            case 1: return Arrays.asList(1, 1, 2, 3, 4, 4, 5, 6);
            case 2: return Arrays.asList();
            case 3: return Arrays.asList();
            case 4: return Arrays.asList();
            case 5: return Arrays.asList(-10, -9, -5, -3, -2, 0, 1, 2, 5);
            case 6: return Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1);
            case 7: return Arrays.asList(0, 1, 3, 5, 8);
            case 8: return Arrays.asList(1, 2, 3, 3, 4, 4, 5, 6, 7);
            case 9: return Arrays.asList(1, 2, 3, 10, 11, 12, 100, 101);
            case 10: return Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
            default: return Arrays.asList();
        }
    }

    private static ListNode[] createLists(int[][] arrays) {
        ListNode[] lists = new ListNode[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == null || arrays[i].length == 0) {
                lists[i] = null;
            } else {
                ListNode dummy = new ListNode(0);
                ListNode curr = dummy;
                for (int val : arrays[i]) {
                    curr.next = new ListNode(val);
                    curr = curr.next;
                }
                lists[i] = dummy.next;
            }
        }
        return lists;
    }

    private static List<Integer> linkedListToList(ListNode head) {
        List<Integer> result = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            result.add(curr.val);
            curr = curr.next;
        }
        return result;
    }
}
