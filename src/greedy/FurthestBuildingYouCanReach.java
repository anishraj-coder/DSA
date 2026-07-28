package greedy;

import java.util.*;

public class FurthestBuildingYouCanReach {

    public int furthestBuilding(int[] arr, int bricks, int ladders) {
        int n=arr.length;
        PriorityQueue<Integer>pq=new PriorityQueue<>();
        for(int i=0;i<n-1;i++) {
        	int diff=arr[i+1]-arr[i];
        	if(diff>0)pq.offer(diff);
        	if(pq.size()>ladders) {
        		bricks-=pq.poll();        		
        	}
        	if(bricks<0)return i;
        }
        return n-1;
    }

    public static void main(String[] args) {
        FurthestBuildingYouCanReach solver = new FurthestBuildingYouCanReach();

        TestCase[] testCases = new TestCase[] {
            // 1. Standard LeetCode Example 1
            new TestCase(
                new int[]{4, 2, 7, 6, 9, 14, 12}, 5, 1, 4
            ),
            // 2. Standard LeetCode Example 2
            new TestCase(
                new int[]{4, 12, 2, 7, 3, 18, 20, 3, 19}, 10, 2, 7
            ),
            // 3. Standard LeetCode Example 3
            new TestCase(
                new int[]{14, 3, 19, 3}, 17, 0, 3
            ),
            // 4. Single building (no moves possible/needed)
            new TestCase(
                new int[]{10}, 0, 0, 0
            ),
            // 5. Strictly decreasing heights (no resources required)
            new TestCase(
                new int[]{10, 9, 8, 7, 6, 5}, 0, 0, 5
            ),
            // 6. Zero ladders, rely purely on exact brick counts
            new TestCase(
                new int[]{1, 5, 1, 3}, 6, 0, 3
            ),
            // 7. Not enough bricks for a single climb, 0 ladders
            new TestCase(
                new int[]{1, 10, 20}, 5, 0, 0
            ),
            // 8. Ladders used for the largest climbs, bricks used for small ones
            new TestCase(
                new int[]{1, 5, 1, 2, 3, 4, 100}, 4, 1, 6
            ),
            // 9. Exact match on total resources (large jump needs ladder, small jumps need bricks)
            new TestCase(
                new int[]{1, 2, 4, 7, 100}, 6, 1, 4
            ),
            // 10. Enough ladders to jump every single step (bricks completely unused)
            new TestCase(
                new int[]{1, 10, 20, 30, 40}, 0, 10, 4
            ),
            // 11. Large input values to check boundary conditions
            new TestCase(
                new int[]{1, 1000000, 2000000}, 1000000, 1, 2
            )
        };

        int passed = 0;
        int failed = 0;

        System.out.println("==========================================================================");
        System.out.printf("%-10s | %-10s | %-10s | %-8s | %s\n", "Test Case", "Expected", "Actual", "Status", "Details");
        System.out.println("==========================================================================");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int actual = solver.furthestBuilding(tc.heights, tc.bricks, tc.ladders);
            boolean isPassed = (actual == tc.expected);

            if (isPassed) {
                passed++;
            } else {
                failed++;
            }

            String status = isPassed ? "PASSED" : "FAILED";
            String details = String.format("Bricks: %d, Ladders: %d, Heights: %s", 
                                           tc.bricks, tc.ladders, Arrays.toString(tc.heights));

            System.out.printf("%-10d | %-10d | %-10d | %-8s | %s\n", 
                              (i + 1), tc.expected, actual, status, details);
        }

        System.out.println("==========================================================================");
        System.out.printf("SUMMARY: Passed %d / %d test cases. (Failed: %d)\n", passed, testCases.length, failed);
        System.out.println("==========================================================================");
    }

    static class TestCase {
        int[] heights;
        int bricks;
        int ladders;
        int expected;

        TestCase(int[] heights, int bricks, int ladders, int expected) {
            this.heights = heights;
            this.bricks = bricks;
            this.ladders = ladders;
            this.expected = expected;
        }
    }
}