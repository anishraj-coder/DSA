package linkedList;

public class LinkedListIncrementer {

    /**
     * Adds one to the number represented by the linked list.
     * * @param head The head of the linked list representing a number.
     * @return The head of the modified linked list.
     */
    public ListNode addOne(ListNode head) {
        head=reverse(head);
        int carry=1;
        ListNode curr=head,prev=null;
        while(curr!=null){
            int sum=curr.val+carry;
            curr.val=sum%10;
            carry=sum/10;
            prev=curr;
            curr=curr.next;
            if(carry==0)break;

        }
        if(carry!=0){
            prev.next=new ListNode(carry);
        }
        return reverse(head);
    }

    private ListNode reverse(ListNode head){
        ListNode prev=null,curr=head;
        while(curr!=null){
            ListNode next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }

    public static void main(String[] args) {
        LinkedListIncrementer solver = new LinkedListIncrementer();
        int passed = 0;
        int totalTests = 10;

        for (int i = 1; i <= totalTests; i++) {
            TestData testData = getTestData(i);
            ListNode result = solver.addOne(testData.inputHead);

            String actualStr = listToString(result);
            boolean isCorrect = actualStr.equals(testData.expectedOutput);

            if (isCorrect) passed++;

            System.out.println("Test Case " + i + ": " + testData.description);
            System.out.println("Input:    " + testData.inputStr);
            System.out.println("Expected: " + testData.expectedOutput);
            System.out.println("Actual:   " + actualStr);
            System.out.println("Result:   " + (isCorrect ? "PASSED" : "FAILED"));
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + totalTests + " tests passed.");
    }

    static class TestData {
        ListNode inputHead;
        String inputStr;
        String expectedOutput;
        String description;

        TestData(ListNode head, String inputStr, String expected, String desc) {
            this.inputHead = head;
            this.inputStr = inputStr;
            this.expectedOutput = expected;
            this.description = desc;
        }
    }

    private static TestData getTestData(int testId) {
        switch (testId) {
            case 1: // Simple case
                return new TestData(buildList(new int[]{1, 5, 2}), "1->5->2", "1->5->3", "Standard increment (no carry)");

            case 2: // Single digit, no carry
                return new TestData(buildList(new int[]{5}), "5", "6", "Single digit no carry");

            case 3: // Single digit with carry
                return new TestData(buildList(new int[]{9}), "9", "1->0", "Single digit with carry (new node needed)");

            case 4: // Multi-digit with carry propagation
                return new TestData(buildList(new int[]{1, 9, 9}), "1->9->9", "2->0->0", "Carry propagates halfway");

            case 5: // All nines (The "New Head" edge case)
                return new TestData(buildList(new int[]{9, 9, 9}), "9->9->9", "1->0->0->0", "All nines (carry propagates to new head)");

            case 6: // Large number
                return new TestData(buildList(new int[]{1, 0, 0, 0, 9}), "1->0->0->0->9", "1->0->0->1->0", "Carry propagation with zeros");

            case 7: // Ends in 8
                return new TestData(buildList(new int[]{1, 2, 8}), "1->2->8", "1->2->9", "Edge case: digit becomes 9 (no carry)");

            case 8: // Two digits, becomes three
                return new TestData(buildList(new int[]{9, 9}), "9->9", "1->0->0", "Sample 2 from problem");

            case 9: // Long list of nines
                return new TestData(buildList(new int[]{9, 9, 9, 9, 9}), "9->9->9->9->9", "1->0->0->0->0->0", "Long carry propagation");

            case 10: // Minimal input
                return new TestData(new ListNode(0), "0", "1", "Input is zero (positive integer constraint typically starts at 1, but good to handle)");

            default:
                return null;
        }
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

    private static String listToString(ListNode head) {
        if (head == null) return "null";
        StringBuilder sb = new StringBuilder();
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) sb.append("->");
            curr = curr.next;
        }
        return sb.toString();
    }
}