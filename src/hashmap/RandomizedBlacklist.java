package hashmap;

import java.util.*;

/**
 * RandomizedBlacklist allows picking a random integer in [0, n-1] excluding blacklist.
 * It optimizes the random function calls to a single call per pick.
 */
public class RandomizedBlacklist {

    private HashMap<Integer,Integer>map;
    private int n,valid;
    private Random random;

    /**
     * Initializes the object with the integer n and the blacklisted integers.
     * Expected Complexity: O(B) where B is the size of blacklist.
     */
    public RandomizedBlacklist(int n, int[] blacklist) {
        this.n=n;
        this.random=new Random();
        this.map=new HashMap<>();
        for(int a:blacklist){
            map.put(a,-1);
        }

        int curr=n-1;
        this.valid=n-blacklist.length;
        for(int i=0;i<blacklist.length;i++){
            if(blacklist[i]<valid){
                while (map.containsKey(curr)) curr--;
                map.put(blacklist[i], curr);
            }
        }
    }

    /**
     * Returns a random integer in the range [0, n - 1] and not in blacklist.
     * Expected Complexity: O(1)
     */
    public int pick() {
        int num=random.nextInt(valid);
        if(map.containsKey(num))return map.get(num);
        return num;
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        int passed = 0;
        int total = 10;

        System.out.println("Starting RandomizedBlacklist Test Suite...\n");

        // Test 1: Basic Small Range
        passed += test(1, 7, new int[]{2, 3, 5}, 10);

        // Test 2: Empty Blacklist
        passed += test(2, 5, new int[]{}, 5);

        // Test 3: Blacklist at the very end
        passed += test(3, 10, new int[]{8, 9}, 10);

        // Test 4: Blacklist at the very beginning
        passed += test(4, 10, new int[]{0, 1}, 10);

        // Test 5: Scattered Blacklist
        passed += test(5, 10, new int[]{1, 3, 5, 7, 9}, 20);

        // Test 6: Large Range, Small Blacklist
        passed += test(6, 1000000, new int[]{500, 999999}, 100);

        // Test 7: Blacklist contains almost everything
        passed += test(7, 5, new int[]{0, 1, 2, 3}, 10);

        // Test 8: n is 1, blacklist is empty
        passed += test(8, 1, new int[]{}, 5);

        // Test 9: Consecutive blacklisted numbers
        passed += test(9, 10, new int[]{4, 5, 6}, 15);

        // Test 10: Potential Overflow Case (n is large)
        passed += test(10, 1000000000, new int[]{123456, 789012}, 50);

        System.out.println("\n-------------------------------------------");
        System.out.println("Final Result: " + passed + "/" + total + " Tests Passed.");
    }

    /**
     * Helper to validate that 'pick' never returns a blacklisted item
     * and always stays within range.
     */
    private static int test(int id, int n, int[] blacklist, int iterations) {
        try {
            RandomizedBlacklist rb = new RandomizedBlacklist(n, blacklist);
            Set<Integer> blackSet = new HashSet<>();
            for (int x : blacklist) blackSet.add(x);

            for (int i = 0; i < iterations; i++) {
                int val = rb.pick();
                if (val < 0 || val >= n || blackSet.contains(val)) {
                    System.out.println("Test " + id + " FAILED: Returned " + val +
                            " (Blacklisted or Out of Range)");
                    return 0;
                }
            }
            System.out.println("Test " + id + " PASSED");
            return 1;
        } catch (Exception e) {
            System.out.println("Test " + id + " FAILED with Exception: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
}