package linkedList;

import java.util.*;

public class DoublyLinkedListNodeDeletion {

    // Static Inner Class for Node
    private static class Node {
        public int data;
        public Node next;
        public Node prev;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
        Node (int data,Node next,Node prev){
            this.data=data;
            this.next=next;
            this.prev=prev;
        }
    }

    /**
     * Deletes all occurrences of 'k' from the doubly linked list.
     * * @param head The head of the doubly linked list.
     * @param k The value to be deleted.
     * @return The head of the modified doubly linked list.
     */
    public static Node deleteAllOccurrences(Node head, int k) {
        if(head==null)return null;
        if(head.next==null){
            return head.data==k?null:head;
        }

        Node curr=head;
        while(curr!=null&&curr.data==k){
            Node nextNode=curr.next;
            if(nextNode!=null){
                nextNode.prev=null;
                curr.next=null;
            }
            curr=nextNode;
        }
        head=curr;
        while(curr!=null){
            if(curr.data==k){
                Node prev=curr.prev;
                Node next=curr.next;
                if(prev!=null)prev.next=next;
                if(next!=null)next.prev=prev;
                curr.next=curr.prev=null;
                curr=next;
            }else{
                curr=curr.next;
            }
        }
        return head;
    }

    // --- TEST HARNESS ---

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        int passed = 0;

        // Test Cases: {Input Array, Key to Delete, Expected Output Array}
        Object[][] testCases = {
                {new int[]{10, 4, 10, 3, 5, 20, 10}, 10, new int[]{4, 3, 5, 20}}, // 1. Standard multiple occurrences
                {new int[]{10, 4, 10, 3, 5, 20, 10}, 30, new int[]{10, 4, 10, 3, 5, 20, 10}}, // 2. Key not present
                {new int[]{5, 5, 5, 5}, 5, new int[]{}}, // 3. All nodes match key (Result Empty)
                {new int[]{1, 2, 3}, 1, new int[]{2, 3}}, // 4. Key at head
                {new int[]{1, 2, 3}, 3, new int[]{1, 2}}, // 5. Key at tail
                {new int[]{}, 10, new int[]{}}, // 6. Empty list input
                {new int[]{7}, 7, new int[]{}}, // 7. Single node list (matches key)
                {new int[]{7}, 8, new int[]{7}}, // 8. Single node list (doesn't match)
                {new int[]{1, 1, 2, 1, 1}, 1, new int[]{2}}, // 9. Consecutive matches at start and end
                {new int[]{1, 2, 2, 1}, 2, new int[]{1, 1}} // 10. Consecutive matches in the middle
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] inputArr = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int[] expectedArr = (int[]) testCases[i][2];

            Node head = arrayToDLL(inputArr);
            Node result = deleteAllOccurrences(head, k);
            int[] actualArr = dllToArray(result);

            boolean isContentMatch = Arrays.equals(actualArr, expectedArr);
            boolean isIntegrityValid = verifyIntegrity(result);

            if (isContentMatch && isIntegrityValid) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAILED");
                System.out.println("   Input:    " + Arrays.toString(inputArr) + " | Key: " + k);
                System.out.println("   Expected: " + Arrays.toString(expectedArr));
                System.out.println("   Actual:   " + Arrays.toString(actualArr));
                if (!isIntegrityValid) {
                    System.out.println("   FAIL REASON: DLL pointer integrity broken (prev/next links mismatch).");
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

    // Helper: Convert Doubly Linked List to array for comparison
    private static int[] dllToArray(Node head) {
        List<Integer> list = new ArrayList<>();
        Node curr = head;
        while (curr != null) {
            list.add(curr.data);
            curr = curr.next;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    // Helper: Integrity check to ensure 'prev' and 'next' pointers are correctly linked
    private static boolean verifyIntegrity(Node head) {
        if (head == null) return true;
        if (head.prev != null) return false; // Head's prev must be null

        Node curr = head;
        while (curr != null && curr.next != null) {
            if (curr.next.prev != curr) return false; // Next's prev must point to current
            curr = curr.next;
        }
        return true;
    }
}