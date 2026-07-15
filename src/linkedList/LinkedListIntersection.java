package linkedList;

public class LinkedListIntersection {

    /**
     * Finds the node where two singly linked lists intersect.
     * * Time Complexity Goal: O(m + n)
     * Space Complexity Goal: O(1)
     *  @param head1 The head of the first linked list.
     * @param head2 The head of the second linked list.
     * @return The node where the lists intersect, or null if there is no intersection.
     */
    public ListNode getIntersectionNode(ListNode head1, ListNode head2) {
        if(head1==null||head2==null)return null;
        if(head1==head2)return head1;
        if(head1.next==null&&head2.next==null)return null;
        ListNode curr=head2;
        while(curr.next!=null)curr=curr.next;
        curr.next=head2;
        ListNode slow=head1,fast=head1;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(slow==fast)break;
        }
        if(slow!=fast) {
            curr.next=null;
            return null;
        }
        fast=head1;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        curr.next=null;
        return fast;
    }

    public static void main(String[] args) {
        LinkedListIntersection solver = new LinkedListIntersection();
        int passed = 0;
        int totalTests = 10;

        for (int i = 1; i <= totalTests; i++) {
            TestData testData = getTestData(i);
            ListNode result = solver.getIntersectionNode(testData.headA, testData.headB);

            // Important: We compare references (==), not values (.val)
            boolean isCorrect = (result == testData.expectedNode);
            if (isCorrect) passed++;

            System.out.println("Test Case " + i + ": " + testData.description);
            System.out.println("Expected Node Val: " + (testData.expectedNode == null ? "null" : testData.expectedNode.val));
            System.out.println("Actual Node Val:   " + (result == null ? "null" : result.val));
            System.out.println("Result:            " + (isCorrect ? "PASSED" : "FAILED"));
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + totalTests + " tests passed.");
    }

    static class TestData {
        ListNode headA;
        ListNode headB;
        ListNode expectedNode;
        String description;

        TestData(ListNode a, ListNode b, ListNode exp, String desc) {
            this.headA = a;
            this.headB = b;
            this.expectedNode = exp;
            this.description = desc;
        }
    }

    private static TestData getTestData(int testId) {
        switch (testId) {
            case 1: // Basic intersection (Example 1)
                return createIntersectingLists(new int[]{4, 1}, new int[]{5, 6, 1}, new int[]{8, 4, 5}, "Standard intersection (m != n)");

            case 2: // Short lists intersection (Example 2)
                return createIntersectingLists(new int[]{1, 9, 1}, new int[]{3}, new int[]{2, 4}, "Intersection with short list B");

            case 3: // No intersection (Example 3)
                return new TestData(buildList(new int[]{2, 6, 4}), buildList(new int[]{1, 5}), null, "Lists do not intersect at all");

            case 4: // Immediate intersection
                ListNode common4 = buildList(new int[]{10, 20});
                return new TestData(common4, common4, common4, "Intersection starts at both heads");

            case 5: // Last node intersection
                return createIntersectingLists(new int[]{1, 2, 3}, new int[]{9, 8}, new int[]{7}, "Intersection at the very last node");

            case 6: // One list is a sub-part of the other
                ListNode common6 = buildList(new int[]{5, 6, 7});
                ListNode headA6 = buildList(new int[]{1, 2, 3, 4});
                linkTo(headA6, common6);
                return new TestData(headA6, common6, common6, "HeadB is the intersection node of ListA");

            case 7: // Duplicate values, different nodes
                // List A: 1 -> 2 -> [Reference X] (val 5)
                // List B: 1 -> 2 -> [Reference Y] (val 5)
                // These should NOT intersect because references differ.
                return new TestData(buildList(new int[]{1, 2, 5}), buildList(new int[]{1, 2, 5}), null, "Same values, different memory addresses (No Intersection)");

            case 8: // Large length disparity
                int[] longPrefix = new int[100];
                for(int i=0; i<100; i++) longPrefix[i] = i;
                return createIntersectingLists(longPrefix, new int[]{101}, new int[]{202}, "List A significantly longer than List B");

            case 9: // Single node lists (No intersection)
                return new TestData(new ListNode(1), new ListNode(2), null, "Two single-node lists, no intersection");

            case 10: // Empty List scenario (Edge case)
                return new TestData(null, buildList(new int[]{1, 2, 3}), null, "One list is null");

            default:
                return null;
        }
    }

    // Utility to link the end of a prefix to the start of a common list
    private static void linkTo(ListNode prefixHead, ListNode commonHead) {
        if (prefixHead == null) return;
        ListNode curr = prefixHead;
        while (curr.next != null) curr = curr.next;
        curr.next = commonHead;
    }

    private static ListNode buildList(int[] vals) {
        if (vals == null || vals.length == 0) return null;
        ListNode head = new ListNode(vals[0]);
        ListNode curr = head;
        for (int i = 1; i < vals.length; i++) {
            curr.next = new ListNode(vals[i]);
            curr = curr.next;
        }
        return head;
    }

    private static TestData createIntersectingLists(int[] prefixA, int[] prefixB, int[] common, String desc) {
        ListNode pA = buildList(prefixA);
        ListNode pB = buildList(prefixB);
        ListNode c = buildList(common);

        linkTo(pA, c);
        linkTo(pB, c);

        // If prefix was empty, head becomes the common list head
        ListNode headA = (pA != null) ? pA : c;
        ListNode headB = (pB != null) ? pB : c;

        return new TestData(headA, headB, c, desc);
    }
}