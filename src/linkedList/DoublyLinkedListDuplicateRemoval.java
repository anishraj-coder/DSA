package linkedList;

import java.util.*;

public class DoublyLinkedListDuplicateRemoval {

    // Static Inner Class for Node
    public static class Node {
        public int data;
        public Node next;
        public Node prev;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * Removes duplicate nodes from a sorted doubly linked list.
     * @param head The head of the sorted doubly linked list.
     * @return The head of the modified doubly linked list.
     */
    public static Node removeDuplicates(Node head) {
        if(head==null||head.next==null)return head;
        Node temp=head;
        while (temp!=null&&temp.next!=null){
            Node next=temp.next;
            while(next!=null&&next.data==temp.data){
                next=next.next;
            }
            temp.next=next;
            if(next!=null)next.prev=temp;
            temp=temp.next;
        }
        return head;
    }

    // --- TEST HARNESS ---

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        int passed = 0;

        // Test Cases: {Input Array, Expected Output Array}
        Object[][] testCases = {
                {new int[]{1, 2, 2, 2, 3}, new int[]{1, 2, 3}},              // 1. Standard multiple duplicates
                {new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}},              // 2. No duplicates
                {new int[]{1, 1, 1, 1, 1}, new int[]{1}},                    // 3. All nodes are duplicates
                {new int[]{1, 1, 2, 2, 3, 3}, new int[]{1, 2, 3}},           // 4. Consecutive pairs of duplicates
                {new int[]{1, 2, 2}, new int[]{1, 2}},                       // 5. Duplicates at the tail
                {new int[]{1, 1, 2}, new int[]{1, 2}},                       // 6. Duplicates at the head
                {new int[]{5}, new int[]{5}},                                // 7. Single node list
                {new int[]{1, 1, 2, 3, 3, 3, 4, 5, 5}, new int[]{1, 2, 3, 4, 5}}, // 8. Mixed duplicates throughout
                {new int[]{1, 2, 3, 3}, new int[]{1, 2, 3}},                 // 9. Last two are same
                {new int[]{1, 1, 1, 2, 2, 2, 2}, new int[]{1, 2}}            // 10. Large blocks of duplicates
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] inputArr = (int[]) testCases[i][0];
            int[] expectedArr = (int[]) testCases[i][1];

            Node head = arrayToDLL(inputArr);
            Node result = removeDuplicates(head);
            int[] actualArr = dllToArray(result);

            boolean isContentMatch = Arrays.equals(actualArr, expectedArr);
            boolean isIntegrityValid = verifyIntegrity(result);

            if (isContentMatch && isIntegrityValid) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAILED");
                System.out.println("   Input:    " + Arrays.toString(inputArr));
                System.out.println("   Expected: " + Arrays.toString(expectedArr));
                System.out.println("   Actual:   " + Arrays.toString(actualArr));
                if (!isIntegrityValid) {
                    System.out.println("   FAIL REASON: DLL pointer integrity broken.");
                }
                System.out.println("--------------------------------------------------");
            }
        }

        System.out.println("\n--- FINAL RESULTS ---");
        System.out.println("Total Passed: " + passed + "/" + testCases.length);
    }

    // Helper: Convert array to Doubly Linked List
    private static Node arrayToDLL(int[] arr) {
        if (arr == null || arr.length == 0) return null;
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

    // Helper: Convert Doubly Linked List to array
    private static int[] dllToArray(Node head) {
        List<Integer> list = new ArrayList<>();
        Node curr = head;
        while (curr != null) {
            list.add(curr.data);
            curr = curr.next;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    // Helper: Integrity check for prev/next consistency
    private static boolean verifyIntegrity(Node head) {
        if (head == null) return true;
        if (head.prev != null) return false;

        Node curr = head;
        while (curr != null && curr.next != null) {
            if (curr.next.prev != curr) return false;
            curr = curr.next;
        }
        return true;
    }
}