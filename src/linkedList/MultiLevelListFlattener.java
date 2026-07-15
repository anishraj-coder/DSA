package linkedList;

import java.util.*;

/**
 * Problem: Flatten A Linked List
 * Goal: Given a multi-level linked list where each node has a 'next' and a 'child' pointer,
 * flatten the list into a single level sorted by the 'child' pointer.
 */
public class MultiLevelListFlattener {

    static class Node {
        int val;
        Node next;
        Node child;

        Node(int data) {
            this.val = data;
            this.next = null;
            this.child = null;
        }
    }

    // ==========================================================
    // IMPLEMENT YOUR SOLUTION HERE
    // ==========================================================
    public static Node flattenLinkedList(Node head) {
        // Your code goes here
        return flatten(head);
    }

    private static Node flatten(Node head){
        if(head==null||head.next==null)return head;

        Node flatted=flatten(head.next);

        return merge(head,flatted);
    }
    private static Node merge(Node head1,Node head2){
        Node dummy=new Node(-1),curr=dummy;
        Node l1=head1,l2=head2;
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                curr.child=l1;
                l1=l1.child;
            }else{
                curr.child=l2;
                l2=l2.child;
            }
            curr.next=null;
            curr=curr.child;
        }

        if(l1!=null)curr.child=l1;
        if(l2!=null)curr.child=l2;

        if(dummy.child!=null)dummy.child.next=null;

        return dummy.child;

    }



    // ==========================================================
    // TEST RUNNER UTILITIES
    // ==========================================================

    private static Node createVerticalList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.child = new Node(arr[i]);
            temp = temp.child;
        }
        return head;
    }

    private static List<Integer> listToArrayList(Node head) {
        List<Integer> result = new ArrayList<>();
        Node temp = head;
        while (temp != null) {
            result.add(temp.val);
            temp = temp.child;
        }
        return result;
    }

    private static boolean compareLists(Node result, int[] expected) {
        List<Integer> resultList = listToArrayList(result);
        if (resultList.size() != expected.length) return false;
        for (int i = 0; i < expected.length; i++) {
            if (!resultList.get(i).equals(expected[i])) return false;
        }
        // Verify next pointers are null as per problem statement
        Node temp = result;
        while(temp != null) {
            if(temp.next != null) return false;
            temp = temp.child;
        }
        return true;
    }

    private static void runTest(String testName, Node head, int[] expected) {
        System.out.println("Running: " + testName);
        Node result = flattenLinkedList(head);
        boolean passed = compareLists(result, expected);

        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Actual:   " + listToArrayList(result));
        System.out.println("Result:   " + (passed ? "PASSED ✅" : "FAILED ❌"));
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        // 1. Sample Input 1
        Node h1 = createVerticalList(new int[]{1, 2, 3});
        h1.next = createVerticalList(new int[]{8, 10, 15});
        h1.next.next = createVerticalList(new int[]{18, 22});
        h1.next.next.next = createVerticalList(new int[]{29});
        runTest("Sample Input 1", h1, new int[]{1, 2, 3, 8, 10, 15, 18, 22, 29});

        // 2. Sample Input 2
        Node h2 = createVerticalList(new int[]{4, 6});
        h2.next = createVerticalList(new int[]{5, 71});
        h2.next.next = createVerticalList(new int[]{7, 8, 9});
        h2.next.next.next = createVerticalList(new int[]{11, 12, 19});
        h2.next.next.next.next = createVerticalList(new int[]{14, 15, 17});
        runTest("Sample Input 2", h2, new int[]{4, 5, 6, 7, 8, 9, 11, 12, 14, 15, 17, 19, 71});

        // 3. Single Node List
        Node h3 = new Node(5);
        runTest("Single Node", h3, new int[]{5});

        // 4. Multiple Heads, Single Child each
        Node h4 = new Node(10);
        h4.next = new Node(5);
        h4.next.next = new Node(20);
        runTest("Single Childs per Head", h4, new int[]{5, 10, 20});

        // 5. Empty List (Null input)
        runTest("Null Input", null, new int[]{});

        // 6. Overlapping Values
        Node h6 = createVerticalList(new int[]{10, 20});
        h6.next = createVerticalList(new int[]{10, 20});
        runTest("Duplicate Values", h6, new int[]{10, 10, 20, 20});

        // 7. Large Data Gap
        Node h7 = createVerticalList(new int[]{1, 1000000000});
        h7.next = createVerticalList(new int[]{2, 500});
        runTest("Large Constraints/Gaps", h7, new int[]{1, 2, 500, 1000000000});

        // 8. One Head empty (effectively checking next gaps)
        Node h8 = createVerticalList(new int[]{1, 5});
        h8.next = createVerticalList(new int[]{}); // Theoretically should be handled if list is 1 -> 5 -> null
        h8.next = createVerticalList(new int[]{2, 6});
        runTest("Normal case with 2 levels", h8, new int[]{1, 2, 5, 6});

        // 9. Descending order heads (Internal nodes sorted, but heads not)
        Node h9 = createVerticalList(new int[]{50, 100});
        h9.next = createVerticalList(new int[]{10, 20});
        h9.next.next = createVerticalList(new int[]{5, 15});
        runTest("Descending Heads", h9, new int[]{5, 10, 15, 20, 50, 100});

        // 10. Long Vertical Chain, Short Horizontal
        Node h10 = createVerticalList(new int[]{10, 20, 30, 40, 50});
        h10.next = createVerticalList(new int[]{1, 2});
        runTest("Long Vertical Chain", h10, new int[]{1, 2, 10, 20, 30, 40, 50});
    }
}