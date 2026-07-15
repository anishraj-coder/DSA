package linkedList;

import java.util.HashMap;

/**
 * Problem: LFU Cache
 * Description: Design and implement a data structure for a Least Frequently Used (LFU) cache.
 * Time Complexity Requirement: O(1) for both get and put operations.
 */
class LFUCache {
    private class Node{
        int val,count,key;
        Node prev,next;
        Node(int key,int val){
            this.val=val;
            this.key=key;
            this.count=1;
        }
    }

    private class List{
        int size;
        Node head,tail;
        List(){
            this.head=new Node(-1,-1);
            this.tail=new Node(-1,-1);
            head.next=tail;
            tail.prev=head;
            size=0;
        }

        public Node remove(Node curr){
            curr.prev.next=curr.next;
            curr.next.prev=curr.prev;
            size--;
            return curr;
        }

        public Node addToTail(Node node){
            Node prev=tail.prev;
            prev.next=node;
            tail.prev=node;
            node.prev=prev;
            node.next=tail;
            size++;
            return node;
        }

        public Node removeFromHead(){
            if(head.next==tail||size==0)return null;
            Node rem=head.next;
            head.next=rem.next;
            rem.next.prev=head;
            size--;
            return rem;
        }
    }

    private final HashMap<Integer,Node> keyMap;
    private final HashMap<Integer,List> freqMap;
    int minFreq,capacity,currentSize;

    // TO BE IMPLEMENTED BY YOU
    public LFUCache(int capacity) {
        this.capacity=capacity;
        keyMap=new HashMap<>();
        freqMap=new HashMap<>();
        minFreq=0;currentSize=0;
    }

    public int get(int key) {
        if(!keyMap.containsKey(key))return -1;
        Node node=keyMap.get(key);
        updateList(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(capacity==0)return;
        if(keyMap.containsKey(key)){
            Node node=keyMap.get(key);
            node.val=value;
            updateList(node);
            return;
        }
        if(currentSize==capacity){
            Node rem=freqMap.get(minFreq).removeFromHead();
            if(freqMap.get(minFreq).size==0)freqMap.remove(minFreq);
            keyMap.remove(rem.key);
            currentSize--;
        }
        minFreq=1;
        Node node=new Node(key,value);
        if(!freqMap.containsKey(1))freqMap.put(1,new List());
        List list=freqMap.get(1);
        list.addToTail(node);
        keyMap.put(key,node);
        currentSize++;
    }

    private void updateList(Node node){
        List list=freqMap.get(node.count);
        list.remove(node);
        if(!freqMap.containsKey(node.count+1))freqMap.put(node.count+1,new List());
        List newList=freqMap.get(node.count+1);
        node.count+=1;
        newList.addToTail(node);

        if(freqMap.get(node.count-1).size==0) {
            freqMap.remove(node.count-1);
            if(minFreq==node.count-1)minFreq++;
        }
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        int passed = 0;
        int failed = 0;

        // Test Case 1: Basic Put and Get
        System.out.println("Test 1: Basic Put/Get");
        LFUCache lfu1 = new LFUCache(2);
        lfu1.put(1, 10);
        int res1 = lfu1.get(1);
        passed += verify(10, res1, "Get existing key");

        // Test Case 2: Capacity 0
        System.out.println("\nTest 2: Capacity 0");
        LFUCache lfu2 = new LFUCache(0);
        lfu2.put(1, 1);
        passed += verify(-1, lfu2.get(1), "Should not store anything in 0 capacity");

        // Test Case 3: LFU Eviction (Standard)
        System.out.println("\nTest 3: LFU Eviction");
        LFUCache lfu3 = new LFUCache(2);
        lfu3.put(1, 1);
        lfu3.put(2, 2);
        lfu3.get(1); // freq of 1 becomes 2
        lfu3.put(3, 3); // 2 is LFU (freq 1), should be evicted
        passed += verify(-1, lfu3.get(2), "Key 2 should be evicted");
        passed += verify(1, lfu3.get(1), "Key 1 should remain");

        // Test Case 4: LRU Tie-break (Same frequency)
        System.out.println("\nTest 4: LRU Tie-break");
        LFUCache lfu4 = new LFUCache(2);
        lfu4.put(1, 1);
        lfu4.put(2, 2);
        // Both have freq 1. 1 was put first (LRU).
        lfu4.put(3, 3); // 1 should be evicted
        passed += verify(-1, lfu4.get(1), "Key 1 (LRU) should be evicted");
        passed += verify(2, lfu4.get(2), "Key 2 should remain");

        // Test Case 5: Update existing key (Increments frequency)
        System.out.println("\nTest 5: Update existing key");
        LFUCache lfu5 = new LFUCache(2);
        lfu5.put(1, 1);
        lfu5.put(2, 2);
        lfu5.put(1, 10); // Update 1, freq becomes 2
        lfu5.put(3, 3);  // 2 is LFU, evict 2
        passed += verify(-1, lfu5.get(2), "Key 2 should be evicted");
        passed += verify(10, lfu5.get(1), "Key 1 should have updated value 10");

        // Test Case 6: Multiple frequency levels
        System.out.println("\nTest 6: Multiple frequency levels");
        LFUCache lfu6 = new LFUCache(3);
        lfu6.put(1, 1);
        lfu6.put(2, 2);
        lfu6.put(3, 3);
        lfu6.get(1); lfu6.get(1); // freq 3
        lfu6.get(2);             // freq 2
        lfu6.put(4, 4);          // 3 is LFU (freq 1), evict 3
        passed += verify(-1, lfu6.get(3), "Key 3 should be evicted");
        passed += verify(4, lfu6.get(4), "Key 4 should be stored");

        // Test Case 7: Key re-insertion after eviction
        System.out.println("\nTest 7: Re-insertion");
        LFUCache lfu7 = new LFUCache(1);
        lfu7.put(1, 1);
        lfu7.put(2, 2); // 1 evicted
        lfu7.put(1, 5); // 2 evicted, 1 re-inserted
        passed += verify(5, lfu7.get(1), "Key 1 should be 5");
        passed += verify(-1, lfu7.get(2), "Key 2 should be evicted");

        // Test Case 8: Max constraints check (Small Scale)
        System.out.println("\nTest 8: Rapid frequency updates");
        LFUCache lfu8 = new LFUCache(2);
        lfu8.put(1, 1);
        lfu8.put(2, 2);
        for(int i=0; i<5; i++) lfu8.get(1); // 1 is very frequent
        for(int i=0; i<5; i++) lfu8.get(2); // 2 is equally frequent
        lfu8.put(3, 3); // 1 is LRU among tied frequencies, evict 1
        passed += verify(-1, lfu8.get(1), "Key 1 should be evicted (LRU tie)");

        // Test Case 9: Large Values
        System.out.println("\nTest 9: Large Values");
        LFUCache lfu9 = new LFUCache(2);
        lfu9.put(100000, 999999999);
        passed += verify(999999999, lfu9.get(100000), "Should handle large integers");

        // Test Case 10: Frequency reset behavior
        System.out.println("\nTest 10: Frequency reset");
        LFUCache lfu10 = new LFUCache(2);
        lfu10.put(1, 1);
        lfu10.get(1); // freq 2
        lfu10.put(2, 2); // freq 1
        lfu10.put(3, 3); // evict 2 (LFU)
        lfu10.put(2, 20); // 2 re-inserted with freq 1 (NOT its old freq)
        lfu10.put(4, 4); // 2 is LFU again, evict 2
        passed += verify(-1, lfu10.get(2), "Key 2 should be evicted again");

        System.out.println("\n---------------------------");
        System.out.println("FINAL RESULTS:");
        System.out.println("Passed: " + passed + "/10");
        System.out.println("Failed: " + (10 - passed));
        System.out.println("---------------------------");
    }

    private static int verify(int expected, int actual, String message) {
        if (expected == actual) {
            System.out.println("  [PASS] " + message);
            return 1;
        } else {
            System.out.println("  [FAIL] " + message + " | Expected: " + expected + ", Actual: " + actual);
            return 0;
        }
    }
}