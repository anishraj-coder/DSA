package hashmap;

import java.util.*;

/**
 * Problem: Insert Delete GetRandom O(1)
 * Constraints: Average O(1) time complexity for all operations.
 */
public class RandomizedSet {

    List<Integer> list;
    HashMap<Integer,Integer>map;
    Random random;

    /**
     * Initializes the RandomizedSet object.
     */
    public RandomizedSet() {
        // TODO: Initialize your data structures here
        list=new ArrayList<>();
        map=new HashMap<>();
        random=new Random();
    }

    /**
     * Inserts an item val into the set if not present.
     * @return true if the item was not present, false otherwise.
     */
    public boolean insert(int val) {
        if(map.containsKey(val))return false;
        list.add(val);
        map.put(val,list.size()-1);
        return true;
    }

    /**
     * Removes an item val from the set if present.
     * @return true if the item was present, false otherwise.
     */
    public boolean remove(int val) {
        if(!map.containsKey(val))return false;
        int indexToRemove=map.get(val);
        if(indexToRemove==list.size()-1){
            map.remove(list.getLast());
            list.removeLast();
            return true;
        }
        map.remove(val);
        int last=list.getLast();
        list.set(indexToRemove,last);
        map.put(last,indexToRemove);
        list.removeLast();
        return true;
    }

    /**
     * Returns a random element from the current set of elements.
     * Each element must have the same probability of being returned.
     */
    public int getRandom() {
        if(map.isEmpty())return -1;
        if(list.size()==1)return list.getFirst();
        int idx=random.nextInt(0,list.size()-1);

        return list.get(idx);
    }

    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        System.out.println("Starting Test Cases...\n");

        // --- Test Case 1: Standard sequence from example ---
        RandomizedSet rs1 = new RandomizedSet();
        boolean t1_1 = rs1.insert(1);      // Expected: true
        boolean t1_2 = rs1.remove(2);      // Expected: false
        boolean t1_3 = rs1.insert(2);      // Expected: true
        int rand1 = rs1.getRandom();       // Expected: 1 or 2
        boolean t1_4 = rs1.remove(1);      // Expected: true
        boolean t1_5 = rs1.insert(2);      // Expected: false
        int rand2 = rs1.getRandom();       // Expected: 2

        boolean test1Result = t1_1 && !t1_2 && t1_3 && (rand1 == 1 || rand1 == 2) && t1_4 && !t1_5 && rand2 == 2;
        printResult(1, "Standard Sequence", test1Result, "true, false, true, [1|2], true, false, 2",
                t1_1 + ", " + t1_2 + ", " + t1_3 + ", " + rand1 + ", " + t1_4 + ", " + t1_5 + ", " + rand2);
        if (test1Result) passed++; else failed++;

        // --- Test Case 2: Duplicate Insertion ---
        RandomizedSet rs2 = new RandomizedSet();
        rs2.insert(10);
        boolean t2_1 = rs2.insert(10);
        printResult(2, "Duplicate Insertion", !t2_1, "false", String.valueOf(t2_1));
        if (!t2_1) passed++; else failed++;

        // --- Test Case 3: Removing non-existent element ---
        RandomizedSet rs3 = new RandomizedSet();
        rs3.insert(5);
        boolean t3_1 = rs3.remove(10);
        printResult(3, "Remove Non-existent", !t3_1, "false", String.valueOf(t3_1));
        if (!t3_1) passed++; else failed++;

        // --- Test Case 4: Single element getRandom ---
        RandomizedSet rs4 = new RandomizedSet();
        rs4.insert(100);
        int r4 = rs4.getRandom();
        printResult(4, "Single Element getRandom", r4 == 100, "100", String.valueOf(r4));
        if (r4 == 100) passed++; else failed++;

        // --- Test Case 5: Remove and Re-insert ---
        RandomizedSet rs5 = new RandomizedSet();
        rs5.insert(1);
        rs5.remove(1);
        boolean t5_1 = rs5.insert(1);
        printResult(5, "Remove and Re-insert", t5_1, "true", String.valueOf(t5_1));
        if (t5_1) passed++; else failed++;

        // --- Test Case 6: Large Negative Values ---
        RandomizedSet rs6 = new RandomizedSet();
        rs6.insert(-2147483648);
        boolean t6_1 = rs6.remove(-2147483648);
        printResult(6, "Integer.MIN_VALUE Handling", t6_1, "true", String.valueOf(t6_1));
        if (t6_1) passed++; else failed++;

        // --- Test Case 7: The "Last Element" Swap Edge Case ---
        // (Removing elements not at the end of the internal list)
        RandomizedSet rs7 = new RandomizedSet();
        rs7.insert(1);
        rs7.insert(2);
        rs7.insert(3);
        rs7.remove(1); // Usually logic swaps 1 with 3
        int r7 = rs7.getRandom();
        boolean t7_1 = (r7 == 2 || r7 == 3);
        printResult(7, "Intermediate Removal (Swap Logic)", t7_1, "2 or 3", String.valueOf(r7));
        if (t7_1) passed++; else failed++;

        // --- Test Case 8: Remove all then getRandom ---
        RandomizedSet rs8 = new RandomizedSet();
        rs8.insert(50);
        rs8.insert(60);
        rs8.remove(50);
        rs8.remove(60);
        rs8.insert(70);
        int r8 = rs8.getRandom();
        printResult(8, "Emptying then refilling", r8 == 70, "70", String.valueOf(r8));
        if (r8 == 70) passed++; else failed++;

        // --- Test Case 9: Rapid Insert/Remove same element ---
        RandomizedSet rs9 = new RandomizedSet();
        boolean t9 = true;
        for(int i=0; i<5; i++) {
            rs9.insert(1);
            if(!rs9.remove(1)) t9 = false;
        }
        printResult(9, "Repeated Cycle", t9, "true", String.valueOf(t9));
        if (t9) passed++; else failed++;

        // --- Test Case 10: Probability Check (Statistical) ---
        RandomizedSet rs10 = new RandomizedSet();
        rs10.insert(1);
        rs10.insert(2);
        int count1 = 0;
        for(int i=0; i<1000; i++) {
            if(rs10.getRandom() == 1) count1++;
        }
        // In 1000 trials, 1 should appear roughly 400-600 times
        boolean t10 = count1 > 350 && count1 < 650;
        printResult(10, "Random Distribution", t10, "Approx 500", String.valueOf(count1));
        if (t10) passed++; else failed++;

        System.out.println("\n----------------------------");
        System.out.println("Final Results - Passed: " + passed + " | Failed: " + failed);
        System.out.println("----------------------------");
    }

    private static void printResult(int id, String name, boolean success, String expected, String actual) {
        String status = success ? "[PASS]" : "[FAIL]";
        System.out.printf("%-6s Test %02d: %-30s | Expected: %-20s | Actual: %-20s%n",
                status, id, name, expected, actual);
    }
}