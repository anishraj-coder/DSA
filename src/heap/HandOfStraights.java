package heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HandOfStraights {

    /**
     * Checks if the hand can be rearranged into groups of 'groupSize' consecutive cards.
     *
     * @param arr      The array representing the cards in Alice's hand
     * @param k The required size of each consecutive group
     * @return true if rearrangement is possible, false otherwise
     */
    public boolean isNStraightHand(int[] arr, int k) {
        int n=arr.length;
        if(n%k!=0)return false;
        int times=n/k;
        PriorityQueue<Integer>pq=new PriorityQueue<>();
        Map<Integer,Integer>map=new HashMap<>();
        for(int a:arr){
            if(!map.containsKey(a))pq.offer(a);
            map.put(a,map.getOrDefault(a,0)+1);
        }

        for(int i=0;i<times;i++){
            int smallest=pq.peek();
            for(int j=0;j<k;j++){
                if(!map.containsKey(smallest))return false;
                map.put(smallest,map.get(smallest)-1);
                if(map.get(smallest)==0)map.remove(smallest);
                smallest++;
            }
            while(!pq.isEmpty()&&!map.containsKey(pq.peek()))pq.poll();

        }

        return true;
    }

    public static void main(String[] args) {
        HandOfStraights solver = new HandOfStraights();

        // Define the 10 test cases
        // Structure: { {hand array}, {groupSize} }
        int[][][] inputs = {
                { {1, 2, 3, 6, 2, 3, 4, 7, 8}, {3} },                         // Case 1: Standard Example 1 (Valid)
                { {1, 2, 3, 4, 5}, {4} },                                     // Case 2: Standard Example 2 (Invalid length mismatch)
                { {8, 10, 7, 9}, {4} },                                       // Case 3: Out-of-order unsorted array (Valid)
                { {1, 2, 3, 4, 5, 6}, {1} },                                  // Case 4: Group size of 1 (Edge case - always true)
                { {1, 1, 2, 2, 3, 3}, {3} },                                  // Case 5: Fully duplicated clean straight runs (Valid)
                { {1, 2, 3, 3, 4, 5, 5, 6, 7}, {3} },                         // Case 6: Uneven duplicate counts / overlapping groups (Valid)
                { {1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13}, {3} },            // Case 7: Valid subsets with a clean gap in the sequence (Valid)
                { {1000000000, 999999999, 999999998}, {3} },                  // Case 8: Large card values approaching constraints (Valid)
                { {1, 2, 3, 4, 5}, {6} },                                     // Case 9: Group size strictly greater than array length (Edge case - Invalid)
                { {2, 4, 6, 8, 10, 12}, {2} }                                 // Case 10: Array with length perfectly divisible but skips values (Invalid)
        };

        boolean[] expectedOutputs = {
                true,
                false,
                true,
                true,
                true,
                true,
                true,
                true,
                false,
                false
        };

        int passed = 0;

        System.out.println("Running verification tests...\n");

        for (int i = 0; i < inputs.length; i++) {
            int[] handCopy = Arrays.copyOf(inputs[i][0], inputs[i][0].length);
            int groupSize = inputs[i][1][0];
            boolean expected = expectedOutputs[i];

            // Run implementation safely
            boolean actual = false;
            boolean threwException = false;
            try {
                actual = solver.isNStraightHand(handCopy, groupSize);
            } catch (Exception e) {
                System.out.printf("Test Case %d: FAILED (Exception thrown: %s)%n", i + 1, e.toString());
                threwException = true;
            }

            if (!threwException) {
                boolean isCorrect = (actual == expected);

                System.out.printf("Test Case %d: %s%n", i + 1, isCorrect ? "PASSED" : "FAILED");
                System.out.printf("  Hand Array: %s, Group Size = %d%n", Arrays.toString(inputs[i][0]), groupSize);
                System.out.printf("  Expected:   %b%n", expected);
                System.out.printf("  Actual:     %b%n", actual);
                System.out.println("------------------------------------------------------------------");

                if (isCorrect) {
                    passed++;
                }
            }
        }

        System.out.printf("\nFinal Result: %d/%d test cases passed.%n", passed, inputs.length);
    }
}