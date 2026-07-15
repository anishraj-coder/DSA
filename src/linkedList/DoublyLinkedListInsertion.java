package linkedList;

import java.util.*;

public class DoublyLinkedListInsertion {

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
     * Inserts a node with value 'val' at position 'k' in a doubly linked list.
     * * @param head The head of the doubly linked list.
     * @param k    The 0-indexed position where the value should be inserted.
     * @param val  The integer value to insert.
     * @return     The head of the updated doubly linked list.
     */
    public static Node insertAtPosition(Node head, int k, int val) {
        Node newNode=new Node(val);
        if(k==0){
            newNode.next=head;
            head.prev=newNode;
            return newNode;
        }
        int count=0;
        Node curr=head;
        while(count<k-1&&curr!=null){
            curr=curr.next;
            count++;
        }
        if(curr==null)return head;
        if(curr.next==null){
            curr.next=newNode;
            newNode.prev=curr;
            return head;
        }

        newNode.next=curr.next;
        newNode.prev=curr;
        curr.next.prev=newNode;
        curr.next=newNode;
        return head;
    }

    // --- Testing Infrastructure ---

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        int passed = 0;
        int total = 10;

        // Test Case Definitions
        int[][] inputs = {
                {1, 2, 3},            // Case 1: End insertion (Sample 1)
                {1, 2, 3, 4},         // Case 2: Head insertion (Sample 1)
                {10, 11, 5},          // Case 3: Middle insertion (Sample 2)
                {2, 8},               // Case 4: Middle insertion (Sample 2)
                {100},                // Case 5: Single element - insert at head
                {100},                // Case 6: Single element - insert at tail
                {5, 10, 15, 20},      // Case 7: Middle insertion near end
                {10, 20, 30, 40, 50}, // Case 8: Large K at the very end
                {1, 2},               // Case 9: Insert between two elements
                {9, 8, 7, 6}          // Case 10: Head insertion on longer list
        };

        int[] ks = {3, 0, 2, 1, 0, 1, 3, 5, 1, 0};
        int[] vals = {4, 0, 4, 5, 50, 200, 18, 60, 99, 10};

        int[][] expected = {
                {1, 2, 3, 4},
                {0, 1, 2, 3, 4},
                {10, 11, 4, 5},
                {2, 5, 8},
                {50, 100},
                {100, 200},
                {5, 10, 15, 18, 20},
                {10, 20, 30, 40, 50, 60},
                {1, 99, 2},
                {10, 9, 8, 7, 6}
        };

        for (int i = 0; i < total; i++) {
            Node head = arrayToList(inputs[i]);
            System.out.println("Test Case " + (i + 1) + ": K=" + ks[i] + ", VAL=" + vals[i]);
            System.out.print("Input List: ");
            printList(head);

            Node resultHead = insertAtPosition(head, ks[i], vals[i]);
            List<Integer> actualList = listToArray(resultHead);
            List<Integer> expectedList = intArrayToList(expected[i]);

            boolean isCorrect = compareLists(actualList, expectedList) && verifyIntegrity(resultHead);

            System.out.println("Expected: " + expectedList);
            System.out.println("Actual:   " + actualList);

            if (isCorrect) {
                System.out.println("Result:   PASSED");
                passed++;
            } else {
                System.out.println("Result:   FAILED (Check logic or Prev/Next pointers)");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Score: " + passed + "/" + total);
    }

    // Utility to verify DL integrity (prev <-> next connections)
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

    private static Node arrayToList(int[] arr) {
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

    private static List<Integer> listToArray(Node head) {
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

    private static boolean compareLists(List<Integer> l1, List<Integer> l2) {
        return l1.equals(l2);
    }

    private static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + (curr.next != null ? " <-> " : ""));
            curr = curr.next;
        }
        System.out.println();
    }
}