package linkedList;

import java.util.*;

public class DoublyLinkedListSumPairs {

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
     * Finds all pairs in a sorted doubly linked list that sum up to k.
     * @param head The head of the sorted doubly linked list.
     * @param k The target sum.
     * @return A list of integer arrays, where each array contains a pair [a, b].
     */
    public static List<int[]> findPairsWithSum(Node head, int k) {
        List<int[]> result = new ArrayList<>();
        Node tail =head;
        while(tail.next!=null){
            tail = tail.next;
        }
        Node curr=head;
        while(curr.data<=tail.data&&curr!=tail){
            int a=curr.data,b=tail.data,sum=a+b;
            if(sum==k){
                result.add(new int[]{a,b});
                curr=curr.next;
                tail=tail.prev;
            }else if(sum<k){
                curr=curr.next;
            }else tail=tail.prev;
        }
        return result;
    }

    // --- TEST HARNESS ---

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        int passed = 0;

        // Test Cases: {Input Array, Target k, Expected Pairs Array}
        Object[][] testCases = {
                {new int[]{1, 2, 3, 4, 9}, 5, new int[][]{{1, 4}, {2, 3}}},           // 1. Standard case
                {new int[]{1, 10, 11, 12, 27}, 7, new int[][]{}},                    // 2. No pairs possible
                {new int[]{1, 2, 3, 4, 5, 6}, 7, new int[][]{{1, 6}, {2, 5}, {3, 4}}}, // 3. Multiple sequential pairs
                {new int[]{2, 4, 6, 8, 10}, 12, new int[][]{{2, 10}, {4, 8}}},        // 4. Even numbers
                {new int[]{1, 5, 7, 10}, 15, new int[][]{{5, 10}}},                   // 5. One pair in middle/end
                {new int[]{1, 2, 3, 4, 5}, 10, new int[][]{}},                        // 6. Sum larger than max possible
                {new int[]{5, 8, 12}, 13, new int[][]{{5, 8}}},                       // 7. Small list
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 11, new int[][]{{1, 10}, {2, 9}, {3, 8}, {4, 7}, {5, 6}}}, // 8. Long list
                {new int[]{1, 9, 10, 11, 15}, 20, new int[][]{{1, 19}, {9, 11}}},     // 9. Mix of gaps (Wait: 1+19? No, 9+11 only)
                {new int[]{1, 2, 4, 5, 6}, 6, new int[][]{{1, 5}, {2, 4}}}            // 10. Sum matches middle elements
        };

        // Correcting TC 9: input {1, 9, 10, 11, 15}, k=20 -> 9+11 is the only pair.
        testCases[8] = new Object[]{new int[]{1, 9, 10, 11, 15}, 20, new int[][]{{9, 11}}};

        for (int i = 0; i < testCases.length; i++) {
            int[] inputArr = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int[][] expectedArr = (int[][]) testCases[i][2];

            Node head = arrayToDLL(inputArr);
            List<int[]> result = findPairsWithSum(head, k);
            int[][] actualArr = result.toArray(new int[0][]);

            if (compareResults(actualArr, expectedArr)) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAILED");
                System.out.println("   Input: " + Arrays.toString(inputArr) + " | k: " + k);
                System.out.println("   Expected: " + Arrays.deepToString(expectedArr));
                System.out.println("   Actual:   " + Arrays.deepToString(actualArr));
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

    // Helper: Deep compare two 2D arrays (order of pairs and order within pairs matters)
    private static boolean compareResults(int[][] actual, int[][] expected) {
        if (actual.length != expected.length) return false;
        for (int i = 0; i < actual.length; i++) {
            if (!Arrays.equals(actual[i], expected[i])) return false;
        }
        return true;
    }
}