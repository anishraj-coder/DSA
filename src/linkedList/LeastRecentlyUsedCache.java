package linkedList;

import java.util.*;


public class LeastRecentlyUsedCache {

    private  class Node {
        public int val,key;
        public Node next;
        public Node prev;

        Node(int key,int data) {
            this.val = data;
            this.next = null;
            this.prev = null;
            this.key=key;
        }
        Node (int data, Node next, Node prev){
            this.val=data;
            this.next=next;
            this.prev=prev;
        }
    }

    // Define your Node class and necessary fields (capacity, map, head, tail) here
    private HashMap<Integer,Node>hm;
    private int capacity;
    Node head,tail;

    public LeastRecentlyUsedCache(int capacity) {
        this.tail=new Node(-1,-1);
        this.head=new Node(-1,-1);
        tail.prev=head;
        head.next=tail;
        hm=new HashMap<>();
        this.capacity=capacity;
    }

    public int get(int key) {
        if(!hm.containsKey(key))return -1;
        Node curr=hm.get(key);
        remove(curr);
        addAtTail(curr);
        return curr.val;
    }

    public void put(int key, int value) {
        if(hm.containsKey(key)){
            Node curr=hm.get(key);
            remove(curr);
            addAtTail(curr);
            curr.val=value;
            return;
        }
        Node curr=new Node(key,value);
        if(hm.size()==capacity){
            removeFromHead();
        }
        addAtTail(curr);
        hm.put(key,curr);

    }

    private Node remove(Node curr){
        curr.prev.next=curr.next;
        curr.next.prev=curr.prev;
        curr.next=null;
        curr.prev=null;
        return curr;
    }

    private void removeFromHead(){
        Node rem=head.next;
        head.next=head.next.next;
        head.next.prev=head;
        rem.prev=rem.next=null;
        hm.remove(rem.key);
    }

    private Node addAtTail(Node curr){
        Node prev=tail.prev;
        prev.next=curr;
        tail.prev=curr;
        curr.next=tail;
        curr.prev=prev;
        return curr;
    }

    public static void main(String[] args) {
        System.out.println("--- Starting LRU Cache Test Suite ---");
        int passed = 0;
        int totalTests = 10;

        // Test Case 1: Standard Example from prompt
        passed += runTest(1, 2,
                new String[]{"put", "put", "get", "put", "get", "put", "get", "get", "get"},
                new Integer[][]{{1, 1}, {2, 2}, {1}, {3, 3}, {2}, {4, 4}, {1}, {3}, {4}},
                new Integer[]{null, null, 1, null, -1, null, -1, 3, 4}) ? 1 : 0;

        // Test Case 2: Capacity 1
        passed += runTest(2, 1,
                new String[]{"put", "put", "get"},
                new Integer[][]{{2, 1}, {3, 2}, {2}},
                new Integer[]{null, null, -1}) ? 1 : 0;

        // Test Case 3: Overwriting existing keys
        passed += runTest(3, 2,
                new String[]{"put", "put", "get", "put", "get"},
                new Integer[][]{{1, 1}, {1, 2}, {1}, {2, 2}, {2}},
                new Integer[]{null, null, 2, null, 2}) ? 1 : 0;

        // Test Case 4: Recent usage via GET
        passed += runTest(4, 2,
                new String[]{"put", "put", "get", "put", "get"},
                new Integer[][]{{1, 10}, {2, 20}, {1}, {3, 30}, {2}},
                new Integer[]{null, null, 10, null, -1}) ? 1 : 0;

        // Test Case 5: Large Capacity - no eviction
        passed += runTest(5, 100,
                new String[]{"put", "get", "put", "get"},
                new Integer[][]{{10, 100}, {10}, {20, 200}, {20}},
                new Integer[]{null, 100, null, 200}) ? 1 : 0;

        // Test Case 6: Evicting multiple items
        passed += runTest(6, 2,
                new String[]{"put", "put", "put", "put", "get"},
                new Integer[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}, {1}},
                new Integer[]{null, null, null, null, -1}) ? 1 : 0;

        // Test Case 7: Key 0 and Value 0
        passed += runTest(7, 2,
                new String[]{"put", "put", "get"},
                new Integer[][]{{0, 0}, {1, 1}, {0}},
                new Integer[]{null, null, 0}) ? 1 : 0;

        // Test Case 8: Update value and then trigger eviction
        passed += runTest(8, 2,
                new String[]{"put", "put", "put", "get", "get"},
                new Integer[][]{{1, 1}, {2, 2}, {1, 10}, {1}, {2}},
                new Integer[]{null, null, null, 10, 2}) ? 1 : 0;

        // Test Case 9: Repeatedly getting the same key
        passed += runTest(9, 2,
                new String[]{"put", "put", "get", "get", "put", "get"},
                new Integer[][]{{1, 1}, {2, 2}, {1}, {1}, {3, 3}, {2}},
                new Integer[]{null, null, 1, 1, null, -1}) ? 1 : 0;

        // Test Case 10: Capacity 3 - Middle item remains
        passed += runTest(10, 3,
                new String[]{"put", "put", "put", "get", "put", "get"},
                new Integer[][]{{1, 1}, {2, 2}, {3, 3}, {2}, {4, 4}, {1}},
                new Integer[]{null, null, null, 2, null, -1}) ? 1 : 0;

        System.out.println("Final Result: " + passed + "/" + totalTests + " Passed");
    }

    private static boolean runTest(int id, int cap, String[] ops, Integer[][] vals, Integer[] expected) {
        LeastRecentlyUsedCache cache = new LeastRecentlyUsedCache(cap);
        List<Integer> actual = new ArrayList<>();
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("put")) {
                cache.put(vals[i][0], vals[i][1]);
                actual.add(null);
            } else {
                actual.add(cache.get(vals[i][0]));
            }
        }
        boolean match = Arrays.equals(actual.toArray(), expected);
        System.out.println("Test Case " + id + ": " + (match ? "PASSED" : "FAILED"));
        if (!match) {
            System.out.println("   Expected: " + Arrays.toString(expected));
            System.out.println("   Actual:   " + actual);
        }
        return match;
    }
}