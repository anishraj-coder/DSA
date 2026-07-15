package heap;

import java.util.*;

/**
 * Problem: Find Median from Data Stream
 * Implementation should handle continuous additions and median calculations.
 */
class DataStreamMedian {

    PriorityQueue<Integer>left,right;
    // Initialize your data structures here
    public DataStreamMedian() {
        right=new PriorityQueue<>();
        left=new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {

        if(left.isEmpty()&&right.isEmpty()){
            left.offer(num);
        }else if(left.size()==right.size()){
            right.offer(num);
            left.offer(right.poll());
        }else{
            left.offer(num);
            right.offer(left.poll());
        }

    }

    public double findMedian() {
        if(left.size()==right.size()){
            return  (right.peek()-left.peek())/2.0 +left.peek();
        }else{
            return left.peek();
        }
    }

    // --- TEST RUNNER ENGINE ---
    public static void main(String[] args) {
        int passed = 0;
        int totalTests = 10;

        passed += runTest(1, new int[]{1, 2}, new Double[]{null, null, 1.5}, "Simple Even Sequence");
        passed += runTest(2, new int[]{1, 2, 3}, new Double[]{null, null, null, 2.0}, "Simple Odd Sequence");
        passed += runTest(3, new int[]{10, 10, 10, 10}, new Double[]{null, null, null, null, 10.0}, "Identical Elements");
        passed += runTest(4, new int[]{-1, -2, -3, -4, -5}, new Double[]{null, null, null, null, null, -3.0}, "Negative Numbers");
        passed += runTest(5, new int[]{5, 15, 1, 3}, new Double[]{null, null, null, null, 4.0}, "Unordered Stream");
        passed += runTest(6, new int[]{100000, -100000}, new Double[]{null, null, 0.0}, "Constraint Extremes");
        passed += runTest(7, new int[]{1}, new Double[]{null, 1.0}, "Single Element");
        passed += runTest(8, new int[]{0, 0, 1, 1}, new Double[]{null, null, null, null, 0.5}, "Zero and One Mix");

        // Large Stream Test
        int[] largeStream = new int[1000];
        for(int i = 0; i < 1000; i++) largeStream[i] = i;
        passed += runTest(9, largeStream, new Double[]{499.5}, "Large Sequential Stream (0-999)");

        // Duplicate Heavy Test
        passed += runTest(10, new int[]{1, 2, 1, 2, 1, 2}, new Double[]{null, null, null, null, null, null, 1.5}, "Repeated Interleaved Sequence");

        System.out.println("\n---------------------------------------");
        System.out.println("FINAL RESULT: " + passed + "/" + totalTests + " TESTS PASSED");
        System.out.println("---------------------------------------");
    }

    private static int runTest(int id, int[] inputs, Double[] expectedMedians, String description) {
        System.out.println("Test #" + id + ": " + description);
        DataStreamMedian ds = new DataStreamMedian();
        boolean success = true;
        Double lastActual = null;
        Double lastExpected = expectedMedians[expectedMedians.length - 1];

        try {
            int expectedIdx = 0;
            for (int val : inputs) {
                ds.addNum(val);
                expectedIdx++;
                // If expectedMedians provides intermediate checks, we could check them here.
            }
            lastActual = ds.findMedian();

            // Using a small epsilon for double comparison
            if (Math.abs(lastActual - lastExpected) > 1e-5) {
                success = false;
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] Exception occurred: " + e.getMessage());
            success = false;
        }

        if (success) {
            System.out.println("  [PASS] Expected: " + lastExpected + ", Actual: " + lastActual);
            return 1;
        } else {
            System.out.println("  [FAIL] Expected: " + lastExpected + ", Actual: " + lastActual);
            return 0;
        }
    }
}