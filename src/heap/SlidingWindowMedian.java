package heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class SlidingWindowMedian {

    // TO BE IMPLEMENTED BY YOU
    public double[] medianSlidingWindow(int[] arr, int k) {
        PriorityQueue<Integer>left,right;
        left=new PriorityQueue<>(Collections.reverseOrder());
        right=new PriorityQueue<>();
        int n=arr.length,leftSize=0,rightSize=0;
        for(int i=0;i<k;i++){
            if(left.isEmpty()&&right.isEmpty())left.offer(arr[i]);
            else if(left.size()==right.size()){
                right.offer(arr[i]);
                left.offer(right.poll());
            }else{
                left.offer(arr[i]);
                right.offer(left.poll());
            }
        }
        leftSize=left.size();rightSize=right.size();
        double[]ans=new double[n-k+1];
        int j=0;
        ans[j++]=(leftSize==rightSize)?((double) left.peek()+right.peek())/2.0:left.peek()*1.0;
        HashMap<Integer,Integer>map=new HashMap<>();

        for(int i=k;i<n;i++){
            int add=arr[i],rem=arr[i-k];
            map.put(rem,map.getOrDefault(rem,0)+1);
            if(rem<=left.peek())leftSize--;
            else rightSize--;
            if(!left.isEmpty()&&left.peek()>=add){
                leftSize++;
                left.offer(add);
            }else{
                right.offer(add);
                rightSize++;
            }

            if(leftSize>rightSize+1){
                right.offer(left.poll());
                leftSize--;
                rightSize++;
            }else if(rightSize>leftSize){
                left.offer(right.poll());
                leftSize++;
                rightSize--;
            }

            while(!left.isEmpty()&&map.getOrDefault(left.peek(),0)>0){
                int removed=left.poll();
                map.put(removed,map.get(removed)-1);
            }
            while(!right.isEmpty()&&map.getOrDefault(right.peek(),0)>0){
                int removed=right.poll();
                map.put(removed,map.get(removed)-1);
            }
            if (k % 2 == 0) {
                ans[j++] = ((double)left.peek() + right.peek()) / 2.0;
            } else {
                ans[j++] = left.peek();
            }
        }

        return ans;
    }


    private void balance(PriorityQueue<Integer>left,PriorityQueue<Integer>right){
        while(Math.abs(left.size()-right.size())>1) {
            if(left.size()>right.size()){
                right.offer(left.poll());
            }else left.offer(right.poll());
        }

    }

    public static void main(String[] args) {
        SlidingWindowMedian solver = new SlidingWindowMedian();

        // Test Cases Setup
        TestCase[] testCases = new TestCase[]{
                // 1. Standard Example 1 (Odd window size k=3)
                new TestCase(
                        new int[]{1, 3, -1, -3, 5, 3, 6, 7},
                        3,
                        new double[]{1.0, -1.0, -1.0, 3.0, 5.0, 6.0},
                        "Standard array with odd window size"
                ),
                // 2. Standard Example 2 (Duplicates)
                new TestCase(
                        new int[]{1, 2, 3, 4, 2, 3, 1, 4, 2},
                        3,
                        new double[]{2.0, 3.0, 3.0, 3.0, 2.0, 3.0, 2.0},
                        "Array with heavy element repetition"
                ),
                // 3. Edge Case: Minimal window size k=1
                new TestCase(
                        new int[]{5, 2, 7, 1},
                        1,
                        new double[]{5.0, 2.0, 7.0, 1.0},
                        "Minimal boundary window size k = 1"
                ),
                // 4. Edge Case: Window size equals array size (Even k)
                new TestCase(
                        new int[]{1, 4, 2, 3},
                        4,
                        new double[]{2.5},
                        "Window size matches complete array size (Even k)"
                ),
                // 5. Hard Case: Integer Overflow Prevention
                new TestCase(
                        new int[]{2147483647, 2147483647, -2147483648, -2147483648},
                        2,
                        new double[]{2147483647.0, -0.5, -2147483648.0},
                        "Extreme integer limits testing division overflow"
                ),
                // 6. Hard Case: Monotonically decreasing sequence with even k
                new TestCase(
                        new int[]{10, 8, 6, 4, 2},
                        4,
                        new double[]{7.0, 5.0},
                        "Monotonically decreasing array with an even window size"
                ),
                // 7. Hard Case: Perfectly flat identical array elements
                new TestCase(
                        new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE},
                        3,
                        new double[]{Integer.MIN_VALUE, Integer.MIN_VALUE},
                        "Identical minimum integer values throughout"
                ),
                // 8. Hard Case: Alternating values producing jumping medians
                new TestCase(
                        new int[]{1, 10, 1, 10, 1},
                        3,
                        new double[]{1.0, 10.0, 1.0},
                        "Zig-zag value pattern forcing heavy re-balancing"
                ),
                // 9. Edge Case: Large scale inputs containing zeroes and negative items
                new TestCase(
                        new int[]{-10, 0, 10, -20, 0, 20},
                        4,
                        new double[]{-5.0, 0.0, 0.0},
                        "Balanced negative, zero, and positive spread"
                ),
                // 10. Hard Case: Single negative element traversal
                new TestCase(
                        new int[]{1, 2, -5, 3, 4},
                        3,
                        new double[]{1.0, 2.0, 3.0},
                        "Outlier negative value tracking through window steps"
                )
        };

        // Execution and Evaluation Loop
        int passed = 0;
        System.out.println("Executing Test Cases...\n");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            double[] actualOutput = solver.medianSlidingWindow(deepCopy(tc.nums), tc.k);

            boolean isCorrect = compareArrays(actualOutput, tc.expectedOutput);

            if (isCorrect) {
                passed++;
                System.out.printf("[PASS] Test Case %d: %s\n", i + 1, tc.description);
            } else {
                System.out.printf("[FAIL] Test Case %d: %s\n", i + 1, tc.description);
                System.out.println("       Nums: " + Arrays.toString(tc.nums));
                System.out.println("       k: " + tc.k);
                System.out.println("       Expected: " + Arrays.toString(tc.expectedOutput));
                System.out.println("       Actual:   " + Arrays.toString(actualOutput));
            }
            System.out.println("------------------------------------------------------------------");
        }

        System.out.printf("\nExecution Summary: %d/%d Passed.\n", passed, testCases.length);
    }

    // Helper class to encapsulate scenario data
    private static class TestCase {
        int[] nums;
        int k;
        double[] expectedOutput;
        String description;

        TestCase(int[] nums, int k, double[] expectedOutput, String description) {
            this.nums = nums;
            this.k = k;
            this.expectedOutput = expectedOutput;
            this.description = description;
        }
    }

    // Deep copy arrays to avoid reference leakage across runs
    private static int[] deepCopy(int[] source) {
        if (source == null) return null;
        return source.clone();
    }

    // Specialized array comparison adhering to the problem's precision tolerance limit (10^-5)
    private static boolean compareArrays(double[] a, double[] b) {
        if (a == null || b == null) return a == b;
        if (a.length != b.length) return false;

        double tolerance = 1e-5;
        for (int i = 0; i < a.length; i++) {
            if (Math.abs(a[i] - b[i]) > tolerance) {
                return false;
            }
        }
        return true;
    }
}