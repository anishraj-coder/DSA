package arrays;

import java.util.*;

public class MeetingRoomScheduler {

    /**
     * Problem: Minimum Number of Rooms Required
     * Given the start and end times of meetings, find the minimum number of
     * rooms required to accommodate all meetings.
     */
    public int findMinimumRooms(int[] start, int[] end) {
        int n=start.length;
        if(n==1)return 1;
        int count=0,max=0;
        int i=0,j=0;
        Arrays.sort(start);
        Arrays.sort(end);
        while(i<n&&j<n){
            if(start[i]<end[j]){
                count++;
                i++;
            }else{
                count--;
                j++;
            }
            max=Math.max(count,max);
        }

        return max;
    }

    public static void main(String[] args) {
        MeetingRoomScheduler scheduler = new MeetingRoomScheduler();

        // Test Case Definitions
        int[][][] inputs = {
                {{1, 10, 7}, {4, 15, 10}},             // Case 1: Sequential (1 room)
                {{2, 9, 6}, {4, 12, 10}},              // Case 2: Overlapping (2 rooms)
                {{0, 5, 15}, {10, 20, 30}},            // Case 3: Nested (1 room)
                {{1, 2, 3}, {2, 3, 4}},                // Case 4: Back-to-back (1 room - per note)
                {{1, 1, 1}, {2, 2, 2}},                // Case 5: Same start/end (3 rooms)
                {{1, 5, 8, 9, 15}, {2, 6, 10, 11, 20}},// Case 6: Multiple gaps (1 room)
                {{1, 2, 4, 7, 8}, {10, 5, 6, 9, 11}},  // Case 7: Complex overlaps (4 rooms)
                {{10}, {20}},                          // Case 8: Single meeting (1 room)
                {{0, 30, 5, 10}, {30, 60, 10, 15}},    // Case 9: Shared boundaries (2 rooms)
                {{1, 2, 3, 4, 5}, {10, 10, 10, 10, 10}}// Case 10: Heavy overlap (5 rooms)
        };

        int[] expectedOutputs = {1, 2, 1, 1, 3, 1, 4, 1, 2, 5};

        int passed = 0;
        System.out.println("--- Starting Performance Test ---");

        for (int i = 0; i < inputs.length; i++) {
            int[] startArr = inputs[i][0];
            int[] endArr = inputs[i][1];
            int expected = expectedOutputs[i];

            int actual = scheduler.findMinimumRooms(startArr, endArr);

            boolean isPassed = (actual == expected);
            if (isPassed) passed++;

            System.out.printf("Test Case %d: %s\n", i + 1, isPassed ? "PASSED" : "FAILED");
            System.out.println("   Input Start: " + Arrays.toString(startArr));
            System.out.println("   Input End:   " + Arrays.toString(endArr));
            System.out.println("   Expected:    " + expected);
            System.out.println("   Actual:      " + actual);
            System.out.println("---------------------------------");
        }

        System.out.printf("Summary: %d/%d Tests Passed.\n", passed, inputs.length);
    }
}