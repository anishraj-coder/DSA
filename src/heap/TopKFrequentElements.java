package heap;

import java.util.*;

public class TopKFrequentElements {

    /**
     * Returns the k most frequent elements in the array.
     *
     * @param arr The input array of integers.
     * @param k The number of top frequent elements to return.
     * @return An array containing the k most frequent elements.
     */
    public static int[] getTopKFrequent(int[] arr, int k) {

        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->{
            if(a.freq!=b.freq)return a.freq-b.freq;
            else return b.val-a.val;
        });
        HashMap<Integer,Integer>hm=new HashMap<>();
        for(int a:arr)hm.put(a,hm.getOrDefault(a,0)+1);

        for(int i:hm.keySet()){
            int freq=hm.get(i);
            if(pq.size()<k)pq.offer(new Pair(i,freq));
            else if(freq>pq.peek().freq) {
                pq.poll();
                pq.offer(new Pair(i, freq));
            }
        }
        int[]ans=new int[k];
        for(int i=0;i<k;i++)ans[i]=pq.poll().val;
        return ans;
    }


    static class Pair{
        int val,freq;
        Pair(int val,int freq){
            this.val=val;
            this.freq=freq;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Standard case from example
        testCase(new int[]{1, 1, 1, 2, 2, 3}, 2, new int[]{1, 2}, "Standard Example 1");

        // Test Case 2: Single element
        testCase(new int[]{1}, 1, new int[]{1}, "Single Element");

        // Test Case 3: Mixed frequencies from example
        testCase(new int[]{1, 2, 1, 2, 1, 2, 3, 1, 3, 2}, 2, new int[]{1, 2}, "Mixed Frequencies Example 3");

        // Test Case 4: All elements are the same
        testCase(new int[]{5, 5, 5, 5, 5}, 1, new int[]{5}, "All Same Elements");

        // Test Case 5: Negative numbers involved
        testCase(new int[]{-1, -1, -1, -2, -2, -3}, 2, new int[]{-1, -2}, "Negative Numbers");

        // Test Case 6: Large range of unique elements, k equals number of unique elements
        testCase(new int[]{1, 2, 3, 4, 5}, 5, new int[]{1, 2, 3, 4, 5}, "K equals Unique Count");

        // Test Case 7: Elements with same frequency (order doesn't matter, but content must match)
        // Note: Since order doesn't matter, we check if the set of results matches expected set
        testCaseUnordered(new int[]{1, 1, 2, 2, 3, 3}, 2, new int[]{1, 2}, "Same Frequency Tie (Subset)");

        // Test Case 8: Zeroes and positive numbers
        testCase(new int[]{0, 0, 0, 1, 1, 2}, 2, new int[]{0, 1}, "Zeroes and Positives");

        // Test Case 9: Large input size simulation (smaller version for logic check) - Frequent edge at boundaries
        testCase(new int[]{10000, -10000, 10000, -10000, 10000}, 2, new int[]{10000, -10000}, "Boundary Values");

        // Test Case 10: K is 1, multiple candidates
        testCase(new int[]{4, 4, 4, 5, 5, 6}, 1, new int[]{4}, "K=1 Clear Winner");

        // Test Case 11: Alternating pattern
        testCase(new int[]{1, 2, 1, 2, 1, 2, 3}, 2, new int[]{1, 2}, "Alternating Pattern");

        // Test Case 12: Sparse frequencies
        testCase(new int[]{1, 2, 3, 4, 5, 1, 1, 1}, 3, new int[]{1, 2, 3}, "Sparse Frequencies");
    }

    private static void testCase(int[] nums, int k, int[] expected, String testName) {
        System.out.println("Running Test: " + testName);
        int[] result = getTopKFrequent(nums, k);

        // Sort both arrays to compare regardless of order since problem says "any order"
        Arrays.sort(result);
        int[] expectedSorted = expected.clone();
        Arrays.sort(expectedSorted);

        boolean passed = Arrays.equals(result, expectedSorted);

        System.out.println("Expected: " + Arrays.toString(expectedSorted));
        System.out.println("Actual:   " + Arrays.toString(result));
        System.out.println("Result:   " + (passed ? "PASSED" : "FAILED"));
        System.out.println("-------------------------");
    }

    private static void testCaseUnordered(int[] nums, int k, int[] expected, String testName) {
        System.out.println("Running Test: " + testName);
        int[] result = getTopKFrequent(nums, k);

        // Convert to sets to ignore order completely
        Set<Integer> resultSet = new HashSet<>();
        for (int num : result) resultSet.add(num);

        Set<Integer> expectedSet = new HashSet<>();
        for (int num : expected) expectedSet.add(num);

        boolean passed = resultSet.equals(expectedSet);

        System.out.println("Expected (Set): " + expectedSet);
        System.out.println("Actual (Set):   " + resultSet);
        System.out.println("Result:         " + (passed ? "PASSED" : "FAILED"));
        System.out.println("-------------------------");
    }
}