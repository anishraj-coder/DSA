package heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumRefuelingStops {

    public int minRefuelStops(int target, int startFuel, int[][] arr) {

        PriorityQueue<Integer>pq=new PriorityQueue<>(Collections.reverseOrder());
        int curr=startFuel,i=0,n=arr.length,count=0;

        while(curr<target){
            if(i<n&&arr[i][0]<=curr){
                pq.offer(arr[i][1]);
                i++;
            }else{
                if(pq.isEmpty())return -1;
                curr+=pq.poll();
                count++;
            }
        }

        return count;
    }

    static class Pair{
        int pos,fuel;
        Pair(int pos,int fuel){
            this.pos=pos;
            this.fuel=fuel;
        }
    }

    public static void main(String[] args) {
        MinimumRefuelingStops solver = new MinimumRefuelingStops();

        // Test Case structures
        TestCase[] testCases = new TestCase[] {
                // 1. Base Case: Target already reached/reachable without stations
                new TestCase(1, 1, new int[][]{}, 0, "Target reachable immediately without stations"),

                // 2. Base Case: Unreachable even to the first point
                new TestCase(100, 1, new int[][]{{10, 100}}, -1, "Cannot even reach the first station"),

                // 3. Standard Case: Example 3 from description
                new TestCase(100, 10, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}, 2, "Standard multi-hop scenario"),

                // 4. Edge Case: No stations but target is exactly equal to start fuel
                new TestCase(50, 50, new int[][]{}, 0, "No stations, fuel exactly equals target"),

                // 5. Edge Case: Station at the very beginning (position 0 or close) but we can skip it
                new TestCase(100, 50, new int[][]{{25, 25}, {50, 50}}, 1, "Must pick the optimal station later down the road"),

                // 6. Edge Case: Reach station with exactly 0 fuel remaining
                new TestCase(100, 10, new int[][]{{10, 90}}, 1, "Reaching a station with exactly 0 fuel left"),

                // 7. Edge Case: Large chain where you have to skip multiple small stations for a massive one
                new TestCase(100, 20, new int[][]{{10, 5}, {15, 5}, {20, 80}}, 1, "Skipping multiple suboptimal stations"),

                // 8. Tricky Case: Multiple stations at same distance (not possible per constraint, but consecutive close ones)
                new TestCase(100, 10, new int[][]{{5, 5}, {10, 5}, {15, 5}, {20, 5}, {25, 75}}, 4, "Must collect all small fuels to reach the big one"),

                // 9. Large Input/Edge Case: Huge target and large fuel capacities (testing potential integer overflows)
                new TestCase(1_000_000_000, 100_000_000, new int[][]{{100_000_000, 900_000_000}}, 1, "Large values verifying overflow handling"),

                // 10. Fail Case: Trapped right before the target
                new TestCase(100, 10, new int[][]{{10, 10}, {20, 10}, {30, 10}, {40, 59}}, -1, "Fails just 1 mile short of the target"),

                // 11. Edge Case: Station is past the target (should not happen per constraints, but safely handled by ignoring)
                new TestCase(50, 20, new int[][]{{30, 30}, {60, 100}}, 1, "Ignoring stations past the target boundary")
        };

        int passed = 0;
        System.out.println("=======================================================================");
        System.out.printf("%-5s | %-45s | %-8s | %-8s | %-6s\n", "Test", "Description", "Expected", "Actual", "Status");
        System.out.println("=======================================================================");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int actual = solver.minRefuelStops(tc.target, tc.startFuel, tc.stations);
            boolean isCorrect = (actual == tc.expected);
            if (isCorrect) passed++;

            System.out.printf("%-5d | %-45s | %-8d | %-8d | %-6s\n",
                    (i + 1),
                    tc.description,
                    tc.expected,
                    actual,
                    isCorrect ? "PASSED" : "FAILED"
            );
        }

        System.out.println("=======================================================================");
        System.out.printf("Final Result: %d/%d Passed\n", passed, testCases.length);
        System.out.println("=======================================================================");
    }

    private static class TestCase {
        int target;
        int startFuel;
        int[][] stations;
        int Annapolis; // hidden container for expected out
        int expected;
        String description;

        TestCase(int target, int startFuel, int[][] stations, int expected, String description) {
            this.target = target;
            this.startFuel = startFuel;
            this.stations = stations;
            this.expected = expected;
            this.description = description;
        }
    }
}