package linkedList;

public class NumberAdder {

    /**
     * Adds two numbers represented as linked lists where digits are in reverse order.
     * * @param l1 The head of the first linked list.
     * @param l2 The head of the second linked list.
     * @return The head of the sum linked list.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null&&l2==null)return null;
        if(l2==null)return l1;
        if(l1==null)return l2;
        int carry=0;
        ListNode dummy=new ListNode(-1);
        ListNode curr=dummy;
        while(l1!=null||l2!=null||carry!=0){
            int sum=carry;
            if(l1!=null){
                sum+=l1.val;
                l1=l1.next;
            }
            if(l2!=null){
                sum+=l2.val;
                l2=l2.next;
            }
            curr.next=new ListNode(sum%10);
            curr=curr.next;
            carry=sum/10;
        }
        return dummy.next;
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
        NumberAdder solver = new NumberAdder();
        int passed = 0;
        int totalTests = 10;

        for (int i = 1; i <= totalTests; i++) {
            TestData testData = getTestData(i);
            ListNode result = solver.addTwoNumbers(testData.l1, testData.l2);

            String actualStr = listToString(result);
            boolean isCorrect = actualStr.equals(testData.expectedOutput);

            if (isCorrect) passed++;

            System.out.println("Test Case " + i + ": " + testData.description);
            System.out.println("L1:       " + listToString(testData.l1));
            System.out.println("L2:       " + listToString(testData.l2));
            System.out.println("Expected: " + testData.expectedOutput);
            System.out.println("Actual:   " + actualStr);
            System.out.println("Result:   " + (isCorrect ? "PASSED" : "FAILED"));
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + totalTests + " tests passed.");
    }

    static class TestData {
        ListNode l1;
        ListNode l2;
        String expectedOutput;
        String description;

        TestData(ListNode l1, ListNode l2, String expected, String desc) {
            this.l1 = l1;
            this.l2 = l2;
            this.expectedOutput = expected;
            this.description = desc;
        }
    }

    private static TestData getTestData(int testId) {
        switch (testId) {
            case 1: // Example 1: Standard case
                return new TestData(buildList(new int[]{2, 4, 3}), buildList(new int[]{5, 6, 4}), "7->0->8", "Standard addition (Example 1)");

            case 2: // Example 2: Both are zero
                return new TestData(buildList(new int[]{0}), buildList(new int[]{0}), "0", "Adding zeros (Example 2)");

            case 3: // Example 3: Long carry propagation
                return new TestData(buildList(new int[]{9,9,9,9,9,9,9}), buildList(new int[]{9,9,9,9}), "8->9->9->9->0->0->0->1", "Heavy carry propagation (Example 3)");

            case 4: // Different lengths
                return new TestData(buildList(new int[]{1, 8}), buildList(new int[]{0}), "1->8", "Adding zero to a number");

            case 5: // Carry results in a new node at the end
                return new TestData(buildList(new int[]{5}), buildList(new int[]{5}), "0->1", "Single digits resulting in new node");

            case 6: // Large carry at the end
                return new TestData(buildList(new int[]{9, 9}), buildList(new int[]{1}), "0->0->1", "Carry creates multiple 0s and a final 1");

            case 7: // One list is significantly longer
                return new TestData(buildList(new int[]{1}), buildList(new int[]{9, 9, 9, 9}), "0->0->0->0->1", "L2 much longer than L1");

            case 8: // Nodes are at max constraint value (9s everywhere)
                return new TestData(buildList(new int[]{1}), buildList(new int[]{9, 9}), "0->0->1", "99 + 1 case");

            case 9: // Random digits, no carry at the end
                return new TestData(buildList(new int[]{2, 4}), buildList(new int[]{5, 3}), "7->7", "Simple addition, no carry");

            case 10: // Carry starts from middle
                return new TestData(buildList(new int[]{0, 5, 0}), buildList(new int[]{0, 5, 0}), "0->0->1", "Carry generated in middle node");

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