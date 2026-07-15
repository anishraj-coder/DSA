package linkedList;

/**
 * Definition for singly-linked list.
 */


public class LinkedListSymmetry {

    /**
     * Determines if a singly linked list is a palindrome.
     * * @param head The head of the singly linked list.
     * @return true if it is a palindrome, false otherwise.
     */
    public boolean isPalindrome(ListNode head) {
        ListNode x=head;
        if(head==null)return true;
        if(head.next==null)return true;
        ListNode slow,fast=slow=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode secondHalf=reverse(slow);
        ListNode firstHalf=head;
        while(secondHalf!=null&&firstHalf!=null&&firstHalf.val==secondHalf.val){
            firstHalf=firstHalf.next;
            secondHalf=secondHalf.next;
        }

        return secondHalf==null;
    }
    public ListNode reverse(ListNode head){
        ListNode curr=head,prev=null;
        while(curr!=null){
            ListNode temp=curr.next;
            curr.next=prev;
            prev=curr;
            curr=temp;
        }
        return prev;
    }
    // --- TEST RUNNER LOGIC ---

    public static void main(String[] args) {
        LinkedListSymmetry checker = new LinkedListSymmetry();

        Object[][] testCases = {
                {new int[]{1, 2, 2, 1}, true, "Even length palindrome"},
                {new int[]{1, 2, 3, 2, 1}, true, "Odd length palindrome"},
                {new int[]{1, 2}, false, "Simple non-palindrome"},
                {new int[]{1}, true, "Single element list"},
                {new int[]{}, true, "Empty list"},
                {new int[]{1, 1}, true, "Two identical elements"},
                {new int[]{1, 0, 0}, false, "Leading vs trailing mismatch"},
                {new int[]{1, 2, 3, 3, 2, 1}, true, "Long even palindrome"},
                {new int[]{1, 2, 3, 4, 5}, false, "Sequential non-palindrome"},
                {new int[]{1, 2, 1, 2, 1}, true, "Repeating pattern palindrome"},
                {new int[]{1, 2, 3, 1}, false, "Almost palindrome (last element diff)"},
                {new int[]{10, 20, 30, 20, 10}, true, "Large value palindrome"}
        };

        int passed = 0;
        System.out.printf("%-5s | %-35s | %-10s | %-10s | %-8s%n", "ID", "Description", "Expected", "Actual", "Result");
        System.out.println("-".repeat(85));

        for (int i = 0; i < testCases.length; i++) {
            int[] inputArr = (int[]) testCases[i][0];
            boolean expected = (boolean) testCases[i][1];
            String description = (String) testCases[i][2];

            ListNode head = buildList(inputArr);
            boolean actual = checker.isPalindrome(head);

            boolean isMatch = actual == expected;
            if (isMatch) passed++;

            System.out.printf("%-5d | %-35s | %-10b | %-10b | %-8s%n",
                    i + 1, description, expected, actual, isMatch ? "PASSED" : "FAILED");
        }

        System.out.println("-".repeat(85));
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }

    /**
     * Helper to convert an array into a ListNode structure.
     */
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
}