package linkedList;

public class SortedListCombiner {

    /**
     * Merges two sorted linked lists into one sorted linked list.
     * @param list1 The head of the first sorted linked list.
     * @param list2 The head of the second sorted linked list.
     * @return The head of the merged sorted linked list.
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode first=list1,second=list2,curr=null;
        curr=new ListNode(-1,null);
        ListNode currHead=curr;
        while(first!=null&&second!=null){
            if(first.val<second.val){
                curr.next=first;
                first= first.next;
            }else{
                curr.next=second;
                second=second.next;
            }
            curr=curr.next;
        }
        if(first!=null)curr.next=first;
        if(second!=null)curr.next=second;
        return currHead.next;
    }

    public static void main(String[] args) {
        SortedListCombiner combiner = new SortedListCombiner();

        // Test Case Definitions: {Array1, Array2, ExpectedArray}
        Object[][] tests = {
                {new int[]{1, 2, 4}, new int[]{1, 3, 4}, new int[]{1, 1, 2, 3, 4, 4}},
                {new int[]{}, new int[]{}, new int[]{}},
                {new int[]{}, new int[]{0}, new int[]{0}},
                {new int[]{1}, new int[]{}, new int[]{1}},
                {new int[]{2, 5, 10}, new int[]{1, 3, 4, 6, 7, 8}, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10}},
                {new int[]{1, 1, 1}, new int[]{2, 2, 2}, new int[]{1, 1, 1, 2, 2, 2}},
                {new int[]{5, 6, 7}, new int[]{1, 2, 3}, new int[]{1, 2, 3, 5, 6, 7}},
                {new int[]{-10, -5, 0}, new int[]{-7, -3, 2}, new int[]{-10, -7, -5, -3, 0, 2}},
                {new int[]{1, 3, 5, 7, 9}, new int[]{2, 4, 6, 8, 10}, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}},
                {new int[]{100}, new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5, 100}}
        };

        int passedCount = 0;

        for (int i = 0; i < tests.length; i++) {
            int[] arr1 = (int[]) tests[i][0];
            int[] arr2 = (int[]) tests[i][1];
            int[] expectedArr = (int[]) tests[i][2];

            ListNode l1 = buildList(arr1);
            ListNode l2 = buildList(arr2);
            ListNode expected = buildList(expectedArr);

            ListNode actual = combiner.mergeTwoLists(l1, l2);

            boolean isCorrect = compareLists(actual, expected);
            if (isCorrect) passedCount++;

            System.out.println("Test Case " + (i + 1) + ": " + (isCorrect ? "PASSED" : "FAILED"));
            System.out.println("   Input L1: " + java.util.Arrays.toString(arr1));
            System.out.println("   Input L2: " + java.util.Arrays.toString(arr2));
            System.out.println("   Expected: " + listToString(expected));
            System.out.println("   Actual:   " + listToString(actual));
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Result: " + passedCount + "/" + tests.length + " tests passed.");
    }

    // Helper to build a linked list from an array
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

    // Helper to compare two linked lists for equality
    private static boolean compareLists(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1 == null && l2 == null;
    }

    // Helper to convert a list to a string for display
    private static String listToString(ListNode head) {
        if (head == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) sb.append(", ");
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
