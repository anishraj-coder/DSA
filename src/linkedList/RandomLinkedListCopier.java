package linkedList;

import java.util.*;

public class RandomLinkedListCopier {

    // Nested Node class as requested
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * Deep copies a linked list where each node has a next and a random pointer.
     * @param head The head of the original linked list.
     * @return The head of the deep-copied linked list.
     */
    public Node copyRandomList(Node head) {
        if(head==null)return null;
        Node temp=head;
        while(temp!=null){
            Node next=temp.next;
            temp.next=new Node(temp.val);
            temp.next.next=next;
            temp=next;
        }
        temp=head;
        while(temp!=null){
            if(temp.random!=null){
                temp.next.random=temp.random.next;
            }
            temp=temp.next.next;
        }
        Node original=head,copy=head.next,newHead=head.next;

        while(copy!=null){
            original.next=original.next.next;
            if(copy.next!=null){
                copy.next=copy.next.next;
            }
            original=original.next;
            copy=copy.next;
        }


        return newHead;
    }

    public static void main(String[] args) {
        RandomLinkedListCopier solver = new RandomLinkedListCopier();
        int passed = 0;
        int total = 10;

        // Test Case 1: Example 1 - Standard list
        // [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node n1 = createList(new int[][]{{7, -1}, {13, 0}, {11, 4}, {10, 2}, {1, 0}});
        passed += runTest(1, n1, solver);

        // Test Case 2: Example 2 - Simple cycle to middle
        // [[1,1],[2,1]]
        Node n2 = createList(new int[][]{{1, 1}, {2, 1}});
        passed += runTest(2, n2, solver);

        // Test Case 3: Example 3 - Multiple identical values
        // [[3,null],[3,0],[3,null]]
        Node n3 = createList(new int[][]{{3, -1}, {3, 0}, {3, -1}});
        passed += runTest(3, n3, solver);

        // Test Case 4: Null head (Edge Case)
        passed += runTest(4, null, solver);

        // Test Case 5: Single node pointing to null (Edge Case)
        Node n5 = createList(new int[][]{{100, -1}});
        passed += runTest(5, n5, solver);

        // Test Case 6: Single node pointing to itself (Edge Case)
        Node n6 = createList(new int[][]{{42, 0}});
        passed += runTest(6, n6, solver);

        // Test Case 7: All nodes' random pointers point to the head
        Node n7 = createList(new int[][]{{1, 0}, {2, 0}, {3, 0}, {4, 0}});
        passed += runTest(7, n7, solver);

        // Test Case 8: All nodes' random pointers point to the last node
        Node n8 = createList(new int[][]{{10, 2}, {20, 2}, {30, 2}});
        passed += runTest(8, n8, solver);

        // Test Case 9: Long list with random pointers forming a reverse chain
        // 0->3, 1->2, 2->1, 3->0
        Node n9 = createList(new int[][]{{0, 3}, {1, 2}, {2, 1}, {3, 0}});
        passed += runTest(9, n9, solver);

        // Test Case 10: Maximum value constraints and negative values
        Node n10 = createList(new int[][]{{10000, -1}, {-10000, 0}});
        passed += runTest(10, n10, solver);

        System.out.println("\n---------------------------------------");
        System.out.println("Final Result: " + passed + "/" + total + " Passed");
        System.out.println("---------------------------------------");
    }

    private static int runTest(int id, Node head, RandomLinkedListCopier solver) {
        System.out.print("Test Case " + id + ": ");
        Node result = solver.copyRandomList(head);

        boolean isDeepCopy = verifyDeepCopy(head, result);
        boolean structureMatches = verifyStructure(head, result);

        if (isDeepCopy && structureMatches) {
            System.out.println("PASSED");
            return 1;
        } else {
            System.out.println("FAILED");
            System.out.println("  Expected Output: Structure matching input nodes but with different memory addresses.");
            System.out.print("  Actual Output: ");
            if (result == null && head != null) {
                System.out.println("null (List was expected)");
            } else if (!isDeepCopy && head != null) {
                System.out.println("Failure - Returned nodes from the original list (Not a deep copy)");
            } else if (!structureMatches) {
                System.out.println("Failure - Values or pointer indices do not match original structure");
            } else {
                System.out.println("null (As expected)");
            }
            return 0;
        }
    }

    private static boolean verifyDeepCopy(Node original, Node copy) {
        if (original == null && copy == null) return true;
        if (original == null || copy == null) return false;

        Set<Node> originalNodes = new HashSet<>();
        Node curr = original;
        while (curr != null) {
            originalNodes.add(curr);
            curr = curr.next;
        }

        curr = copy;
        while (curr != null) {
            if (originalNodes.contains(curr)) return false;
            if (curr.random != null && originalNodes.contains(curr.random)) return false;
            curr = curr.next;
        }
        return true;
    }

    private static boolean verifyStructure(Node original, Node copy) {
        if (original == null && copy == null) return true;
        if (original == null || copy == null) return false;

        Map<Node, Integer> originalIndices = new HashMap<>();
        List<Node> originalList = new ArrayList<>();
        Node curr = original;
        int idx = 0;
        while (curr != null) {
            originalIndices.put(curr, idx++);
            originalList.add(curr);
            curr = curr.next;
        }

        List<Node> copyList = new ArrayList<>();
        curr = copy;
        while (curr != null) {
            copyList.add(curr);
            curr = curr.next;
        }

        if (originalList.size() != copyList.size()) return false;

        for (int i = 0; i < originalList.size(); i++) {
            Node o = originalList.get(i);
            Node c = copyList.get(i);

            if (o.val != c.val) return false;

            if (o.random == null) {
                if (c.random != null) return false;
            } else {
                Integer rIdx = originalIndices.get(o.random);
                if (rIdx == null || c.random == null || c.random != copyList.get(rIdx)) return false;
            }
        }
        return true;
    }

    private static Node createList(int[][] data) {
        if (data == null || data.length == 0) return null;
        Node[] nodes = new Node[data.length];
        for (int i = 0; i < data.length; i++) {
            nodes[i] = new Node(data[i][0]);
        }
        for (int i = 0; i < data.length; i++) {
            if (i < data.length - 1) nodes[i].next = nodes[i + 1];
            if (data[i][1] != -1) nodes[i].random = nodes[data[i][1]];
        }
        return nodes[0];
    }
}