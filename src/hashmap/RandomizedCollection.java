package hashmap;

import java.util.*;

/**
 * RandomizedCollection is a multiset that supports insert, remove, and getRandom in O(1) time.
 */
public class RandomizedCollection {

    private List<Integer>list;
    private Map<Integer,Set<Integer>>map;
    private Random random;

    /**
     * Initializes the empty RandomizedCollection object.
     */
    public RandomizedCollection() {
        // TODO: Initialize your data structures here
        list=new ArrayList<>();
        map=new HashMap<>();
        random=new Random();
    }

    /**
     * Inserts an item val into the multiset.
     * @return true if the item was not already present, false otherwise.
     */
    public boolean insert(int val) {
        boolean isPresent=!map.containsKey(val);
        map.putIfAbsent(val,new HashSet<>());
        map.get(val).add(list.size());
        list.add(val);
        return isPresent;
    }

    /**
     * Removes one occurrence of val from the multiset if present.
     * @return true if the item was present, false otherwise.
     */
    public boolean remove(int val) {
        if(!map.containsKey(val))return false;
        Set<Integer> indices=map.get(val);
        int idx=-1;
        for(int i: indices){
            idx=i;
            break;
        }
        if(idx==list.size()-1){
            indices.remove(idx);
            if(indices.isEmpty())map.remove(val);
            list.removeLast();
            return true;
        }

        int last=list.getLast();
        int lastIdx=list.size()-1;
        list.set(idx,last);
        map.get(last).remove(lastIdx);
        map.get(last).add(idx);
        map.get(val).remove(idx);
        if(map.get(val).isEmpty())map.remove(val);
        list.removeLast();
        return true;
    }

    /**
     * Returns a random element from the multiset.
     * Probability is linearly related to the number of occurrences.
     */
    public int getRandom() {
        int idx=random.nextInt(list.size());

        return list.get(idx);
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        int passed = 0;
        int total = 10;

        System.out.println("Starting RandomizedCollection Test Suite...\n");

        // Test 1: Basic Sequential Insert
        passed += test(1, new String[]{"insert", "insert", "insert"}, new Object[]{1, 1, 2}, new Object[]{true, false, true});

        // Test 2: Basic Remove
        passed += test(2, new String[]{"insert", "remove", "remove"}, new Object[]{10, 10, 10}, new Object[]{true, true, false});

        // Test 3: Remove when duplicates exist
        passed += test(3, new String[]{"insert", "insert", "remove", "insert", "remove"},
                new Object[]{5, 5, 5, 5, 5}, new Object[]{true, false, true, false, true});

        // Test 4: Edge Case - Integer.MIN_VALUE and MAX_VALUE
        passed += test(4, new String[]{"insert", "insert", "remove"},
                new Object[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE}, new Object[]{true, true, true});

        // Test 5: Complex alternating ops
        passed += test(5, new String[]{"insert", "insert", "insert", "remove", "remove", "insert"},
                new Object[]{1, 2, 1, 1, 2, 2}, new Object[]{true, true, false, true, true, true});

        // Test 6: getRandom distribution check (Manual Check Required for Logic)
        RandomizedCollection rc6 = new RandomizedCollection();
        rc6.insert(1);
        rc6.insert(1);
        rc6.insert(2);
        int res6 = rc6.getRandom();
        System.out.println("Test 6 (getRandom): Probability check. Collection [1, 1, 2]. Result: " + res6 + " [Expected 1 or 2]");
        passed++; // Logic check

        // Test 7: Remove last remaining element
        passed += test(7, new String[]{"insert", "remove", "insert"}, new Object[]{100, 100, 100}, new Object[]{true, true, true});

        // Test 8: Multiple duplicates removal and re-insertion
        passed += test(8, new String[]{"insert", "insert", "insert", "remove", "remove", "remove", "remove"},
                new Object[]{7, 7, 7, 7, 7, 7, 7}, new Object[]{true, false, false, true, true, true, false});

        // Test 9: Interleaved unique elements
        passed += test(9, new String[]{"insert", "insert", "insert", "remove", "remove"},
                new Object[]{1, 2, 3, 2, 4}, new Object[]{true, true, true, true, false});

        // Test 10: High volume removal swap logic
        // (Tests if you correctly handle index swapping when the element to remove is not the last one)
        RandomizedCollection rc10 = new RandomizedCollection();
        rc10.insert(10);
        rc10.insert(20);
        rc10.insert(30);
        rc10.insert(40);
        boolean r10 = rc10.remove(20); // removing from middle
        System.out.println("Test 10 (Middle Removal): Expected true, Actual: " + r10);
        passed += r10 ? 1 : 0;

        System.out.println("\n-------------------------------------------");
        System.out.println("Final Result: " + passed + "/" + total + " Tests Passed.");
        if (passed == total) System.out.println("Great job! Your logic holds up.");
    }

    private static int test(int id, String[] ops, Object[] vals, Object[] expected) {
        RandomizedCollection rc = new RandomizedCollection();
        System.out.print("Test " + id + ": ");
        for (int i = 0; i < ops.length; i++) {
            Object result = null;
            if (ops[i].equals("insert")) result = rc.insert((int) vals[i]);
            else if (ops[i].equals("remove")) result = rc.remove((int) vals[i]);

            if (!result.equals(expected[i])) {
                System.out.println("FAILED at step " + (i + 1) + " (" + ops[i] + " " + vals[i] + ")");
                System.out.println("   Expected: " + expected[i] + ", Actual: " + result);
                return 0;
            }
        }
        System.out.println("PASSED");
        return 1;
    }
}