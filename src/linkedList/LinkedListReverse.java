package linkedList;

import java.util.*;

public class LinkedListReverse {

    /**
     * Reverses a singly linked list.
     * @param head The head of the singly linked list (using your existing ListNode class).
     * @return     The head of the reversed linked list.
     */
    public static ListNode reverseList(ListNode head) {
        if(head==null||head.next==null)return head;
        ListNode curr=head,prev=null;
        while(curr!=null){
            ListNode next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }

    // --- Testing Infrastructure ---

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        int passed = 0;
        int total = 10;

        // Test Cases: Array representation for easy setup
        int[][] testInputs = {
                {1, 2, 3, 4, 5},            // 1. Standard sequence
                {1, 2},                     // 2. Minimum multi-node list
                {10},                       // 3. Single node
                {},                         // 4. Empty list (null head)
                {1, 1, 1, 1},               // 5. Identical elements
                {-5000, 0, 5000},           // 6. Constraint boundaries (min/max val)
                {10, 20, 30, 40, 50, 60},   // 7. Even length list
                {1, 2, 3, 2, 1},            // 8. Palindrome list
                {100, 99, 98, 97, 96, 95},  // 9. Already "reversed" order
                new int[100]                // 10. Larger list (all zeros)
        };

        // Initialize Case 10 with values
        for(int i = 0; i < 100; i++) testInputs[9][i] = i + 1;

        for (int i = 0; i < total; i++) {
            System.out.println("Test Case " + (i + 1) + ":");

            ListNode head = arrayToList(testInputs[i]);
            List<Integer> expected = getExpected(testInputs[i]);

            long startTime = System.nanoTime();
            ListNode resultHead = reverseList(head);
            long endTime = System.nanoTime();

            List<Integer> actual = listToArrayList(resultHead);
            boolean isCorrect = expected.equals(actual);

            // Minimal printing for the large test case
            if (i == 9) {
                System.out.println("Input:    [1, 2, ... 100]");
                System.out.println("Expected: [100, 99, ... 1]");
            } else {
                System.out.println("Input:    " + Arrays.toString(testInputs[i]));
                System.out.println("Expected: " + expected);
            }

            System.out.println("Actual:   " + actual);
            System.out.println("Status:   " + (isCorrect ? "PASSED" : "FAILED"));
            System.out.println("Time:     " + (endTime - startTime) / 1000 + " µs");
            System.out.println("--------------------------------------------------");

            if (isCorrect) passed++;
        }

        System.out.println("Final Results: " + passed + "/" + total + " cases passed.");
    }

    // Helper to convert array to ListNode chain
    private static ListNode arrayToList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        return head;
    }

    // Helper to convert ListNode chain to ArrayList for comparison
    private static List<Integer> listToArrayList(ListNode head) {
        List<Integer> res = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            res.add(curr.val);
            curr = curr.next;
        }
        return res;
    }

    // Helper to get expected reversed list
    private static List<Integer> getExpected(int[] arr) {
        List<Integer> res = new ArrayList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            res.add(arr[i]);
        }
        return res;
    }
}