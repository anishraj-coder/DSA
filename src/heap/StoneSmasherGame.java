package heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class StoneSmasherGame {

    // TO BE IMPLEMENTED BY YOU
    public int lastStoneWeight(int[] arr) {
        PriorityQueue<Integer>pq=new PriorityQueue<>(Collections.reverseOrder());
        for(int a: arr)pq.offer(a);
        while(pq.size()>1){
            int first=pq.poll(),second=pq.poll();
            int diff=first-second;
            if(diff>0)pq.offer(diff);
        }
        return (pq.isEmpty())?0:pq.peek();
    }

    public static void main(String[] args) {
        StoneSmasherGame game = new StoneSmasherGame();
        int passed = 0;
        int totalTests = 11;

        // Test Case 1: Standard case (Example 1)
        int[] stones1 = {2, 7, 4, 1, 8, 1};
        int expected1 = 1;
        passed += runTest(1, game.lastStoneWeight(stones1), expected1, stones1);

        // Test Case 2: Single stone edge case (Example 2)
        int[] stones2 = {1};
        int expected2 = 1;
        passed += runTest(2, game.lastStoneWeight(stones2), expected2, stones2);

        // Test Case 3: Two identical stones (Complete mutual destruction)
        int[] stones3 = {5, 5};
        int expected3 = 0;
        passed += runTest(3, game.lastStoneWeight(stones3), expected3, stones3);

        // Test Case 4: Two different stones
        int[] stones4 = {3, 10};
        int expected4 = 7;
        passed += runTest(4, game.lastStoneWeight(stones4), expected4, stones4);

        // Test Case 5: All stones are identical and even count (Should destroy everything)
        int[] stones5 = {8, 8, 8, 8, 8, 8};
        int expected5 = 0;
        passed += runTest(5, game.lastStoneWeight(stones5), expected5, stones5);

        // Test Case 6: All stones are identical and odd count (One survives)
        int[] stones6 = {7, 7, 7, 7, 7};
        int expected6 = 7;
        passed += runTest(6, game.lastStoneWeight(stones6), expected6, stones6);

        // Test Case 7: Cascading chain reaction (Consecutive values collapsing)
        int[] stones7 = {1, 2, 3, 4, 5};
        int expected7 = 1; // 5-4=1 -> {1,2,3,1} -> 3-2=1 -> {1,1,1,1} -> collapses to 0
        passed += runTest(7, game.lastStoneWeight(stones7), expected7, stones7);

        // Test Case 8: Maximum constraint values
        int[] stones8 = {1000, 1000, 999};
        int expected8 = 999;
        passed += runTest(8, game.lastStoneWeight(stones8), expected8, stones8);

        // Test Case 9: Large dominant stone vs multiple small ones
        int[] stones9 = {20, 2, 3, 4, 1};
        int expected9 = 10; // Smallest combine up or drop down, 20 dominates
        passed += runTest(9, game.lastStoneWeight(stones9), expected9, stones9);

        // Test Case 10: Fibonacci-like weights causing tricky residue shifts
        int[] stones10 = {2, 3, 5, 8, 13};
        int expected10 = 1; // 13-8=5 -> {5,5,3,2} -> 5-5=0 -> {3,2} -> 3-2=1
        passed += runTest(10, game.lastStoneWeight(stones10), expected10, stones10);

        // Test Case 11: Array with 0 results through multiple asymmetric steps
        int[] stones11 = {3, 7, 8, 12, 14};
        int expected11 = 0; // 14-12=2 -> {8,7,3,2} -> 8-7=1 -> {3,2,1} -> 3-2=1 -> {1,1} -> 0
        passed += runTest(11, game.lastStoneWeight(stones11), expected11, stones11);

        System.out.println("\n--------------------------------------------");
        System.out.println("Final Result: " + passed + " / " + totalTests + " Test Cases Passed.");
        System.out.println("--------------------------------------------");
    }

    private static int runTest(int id, int actual, int expected, int[] stones) {
        boolean isSuccess = (actual == expected);
        System.out.printf("Test Case %2d: %s\n", id, isSuccess ? "PASSED" : "FAILED");
        if (!isSuccess) {
            System.out.println("  Inputs: stones = " + Arrays.toString(stones));
            System.out.println("  Expected Output : " + expected);
            System.out.println("  Actual Output   : " + actual);
        }
        return isSuccess ? 1 : 0;
    }
}