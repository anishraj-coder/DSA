package heap;

import java.util.Arrays;
import java.util.Collections;

public class ReplaceElementsWithRank {

    /**
     * Replaces each element in the array with its corresponding 1-based rank.
     * Duplicate elements share the exact same rank.
     *
     * @param arr The input array of integers
     * @return A new array containing the ranks in the original element order
     */
    public int[] replaceWithRank(int[] arr) {
        int n=arr.length;
        if(n==1)return new int[] {1};
        Pair[]temp=new Pair[n];
        for(int i=0;i<n;i++){
            temp[i]=new Pair(arr[i],i);
        }
        Arrays.sort(temp,(a,b)->Integer.compare(a.val,b.val));
        int[]ans=new int[n];
        int rank=1;
        for(int i=0;i<n;i++){
            if(i==0){
                ans[temp[i].idx]=1;
                continue;
            }
            if(temp[i-1].val<temp[i].val)rank++;
            ans[temp[i].idx]=rank;
        }

        return ans;
    }

    static class Pair{
        int val,idx;
        Pair(int val,int idx){
            this.val=val;
            this.idx=idx;
        }
    }

    public static void main(String[] args) {
        ReplaceElementsWithRank solver = new ReplaceElementsWithRank();

        // Define the 10 test cases
        int[][] inputs = {
                {20, 15, 26, 2, 98, 6},                  // Case 1: Standard example 1 (all unique)
                {1, 5, 8, 15, 8, 25, 9},                 // Case 2: Standard example 2 (contains duplicates)
                {40, 10, 20, 40, 30},                    // Case 3: Your provided sample input
                {100},                                   // Case 4: Single element array (Edge case)
                {5, 5, 5, 5, 5},                         // Case 5: All elements are identical (Edge case)
                {1, 2, 3, 4, 5},                         // Case 6: Already sorted array
                {5, 4, 3, 2, 1},                         // Case 7: Reverse sorted array
                {-10, 0, -5, 20, -10, 5},                // Case 8: Mix of negative numbers, positive numbers, and zero
                {Integer.MAX_VALUE, 0, Integer.MIN_VALUE},// Case 9: Extreme integer boundaries
                {10, 20, 10, 30, 20, 40, 30, 50}         // Case 10: Interleaved duplicates cascading upward
        };

        int[][] expectedOutputs = {
                {4, 3, 5, 1, 6, 2},
                {1, 2, 3, 5, 3, 6, 4},
                {4, 1, 2, 4, 3},
                {1},
                {1, 1, 1, 1, 1},
                {1, 2, 3, 4, 5},
                {5, 4, 3, 2, 1},
                {1, 3, 2, 5, 1, 4},
                {3, 2, 1},
                {1, 2, 1, 3, 2, 4, 3, 5}
        };

        int passed = 0;

        System.out.println("Running verification tests...\n");

        for (int i = 0; i < inputs.length; i++) {
            int[] arrCopy = Arrays.copyOf(inputs[i], inputs[i].length);
            int[] expected = expectedOutputs[i];

            // Run implementation safely
            int[] actual = null;
            try {
                actual = solver.replaceWithRank(arrCopy);
            } catch (Exception e) {
                System.out.printf("Test Case %d: FAILED (Exception thrown: %s)%n", i + 1, e.toString());
                continue;
            }

            boolean isCorrect = Arrays.equals(actual, expected);

            System.out.printf("Test Case %d: %s%n", i + 1, isCorrect ? "PASSED" : "FAILED");
            System.out.printf("  Input Array: %s%n", Arrays.toString(inputs[i]));
            System.out.printf("  Expected:    %s%n", Arrays.toString(expected));
            System.out.printf("  Actual:      %s%n", Arrays.toString(actual));
            System.out.println("------------------------------------------------------------------");

            if (isCorrect) {
                passed++;
            }
        }

        System.out.printf("\nFinal Result: %d/%d test cases passed.%n", passed, inputs.length);
    }
}