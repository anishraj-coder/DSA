package linkedList;

import java.util.*;

public class DoublyLinkedListReverse {

    static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * Reverses a doubly linked list and returns the new head.
     * * @param head The head of the doubly linked list.
     * @return     The head of the reversed doubly linked list.
     */
    public static Node reverse(Node head) {
        if(head==null||head.next==null)return head;
        Node curr=head,prev=null;
        while(curr!=null){
            prev=curr.prev;
            curr.prev=curr.next;
            curr.next=prev;
            curr=curr.prev;
        }
        return prev.prev;
    }

    // --- Testing Infrastructure ---

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        int passed = 0;
        int total = 10;

        // Test Case Data
        int[][] testInputs = {
                {1, 2, 3, 4, 5, 6, 7, 8}, // Case 1: Standard even length
                {5, 8, 4, 9, 1},          // Case 2: Standard odd length
                {10},                     // Case 3: Single element
                {10, 20},                 // Case 4: Two elements
                {1, 1, 1, 1},             // Case 5: All identical elements
                {1, 2, 3, 2, 1},          // Case 6: Palindrome list
                {100, 200, 300},          // Case 7: Small list
                {},                       // Case 8: Empty list
                {5, 4, 3, 2, 1},          // Case 9: Already "sorted" reverse
                {10, 20, 30, 40, 50, 60}  // Case 10: Longer list
        };

        for (int i = 0; i < total; i++) {
            System.out.println("Test Case " + (i + 1));
            Node head = arrayToDLL(testInputs[i]);
            List<Integer> original = testInputs[i].length == 0 ? new ArrayList<>() : intArrayToList(testInputs[i]);

            System.out.print("Original: " + original);

            Node reversedHead = reverse(head);
            List<Integer> actual = dllToArray(reversedHead);

            List<Integer> expected = new ArrayList<>(original);
            Collections.reverse(expected);

            boolean isCorrect = actual.equals(expected) && verifyIntegrity(reversedHead);

            System.out.println("\nExpected: " + expected);
            System.out.println("Actual:   " + actual);

            if (isCorrect) {
                System.out.println("Result:   PASSED");
                passed++;
            } else {
                System.out.println("Result:   FAILED (Check pointer swaps and head return)");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Score: " + passed + "/" + total);
    }

    // Checks if prev and next pointers are correctly linked after reverse
    private static boolean verifyIntegrity(Node head) {
        if (head == null) return true;
        if (head.prev != null) return false;

        Node curr = head;
        while (curr.next != null) {
            if (curr.next.prev != curr) return false;
            curr = curr.next;
        }
        return true;
    }

    private static Node arrayToDLL(int[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node curr = head;
        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            curr.next = newNode;
            newNode.prev = curr;
            curr = newNode;
        }
        return head;
    }

    private static List<Integer> dllToArray(Node head) {
        List<Integer> list = new ArrayList<>();
        Node curr = head;
        while (curr != null) {
            list.add(curr.data);
            curr = curr.next;
        }
        return list;
    }

    private static List<Integer> intArrayToList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i : arr) list.add(i);
        return list;
    }
}
