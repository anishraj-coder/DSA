package arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class IntervalInsertion {

    /**
     * Problem: Insert a new interval into a sorted list of non-overlapping intervals.
     * Merge overlapping intervals if necessary.
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length==0)return new int[][]{newInterval};
        ArrayList<int[]>arr=new ArrayList<>();
        insert(arr,intervals,newInterval);
        int s1=arr.get(0)[0],e1=arr.get(0)[1],n=arr.size();
        ArrayList<int[]>res=new ArrayList<>();
        for(int i=1;i<n;i++){
            int s2=arr.get(i)[0],e2=arr.get(i)[1];
            if(s2>e1){
                res.add(new int[]{s1,e1});
                s1=s2;
            }
            e1=Math.max(e1,e2);
        }
        res.add(new int[]{s1,e1});
        int[][]ans=new int[res.size()][2];
        for(int i=0;i<res.size();i++){
            ans[i][0]=res.get(i)[0];
            ans[i][1]=res.get(i)[1];
        }
        return ans;
    }

    private void insert(ArrayList<int[]> arr, int[][]intervals, int[]newInterval){
        int n=intervals.length,idx=-1;
        for(int i=0;i<n;i++){
            if(intervals[i][0]>newInterval[0]&&idx==-1) {
                arr.add(newInterval);
                idx=i;
            }
            arr.add(intervals[i]);
        }
        if(idx==-1)arr.add(newInterval);
    }


    public static void main(String[] args) {
        IntervalInsertion orchestrator = new IntervalInsertion();
        int testsPassed = 0;

        // Test Case Definitions: {intervals, newInterval, expectedOutput}
        Object[][] testCases = {
                {
                        new int[][]{{1, 3}, {6, 9}},
                        new int[]{2, 5},
                        new int[][]{{1, 5}, {6, 9}},
                        "Standard overlap in middle"
                },
                {
                        new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}},
                        new int[]{4, 8},
                        new int[][]{{1, 2}, {3, 10}, {12, 16}},
                        "Multi-interval overlap"
                },
                {
                        new int[][]{},
                        new int[]{5, 7},
                        new int[][]{{5, 7}},
                        "Empty initial intervals"
                },
                {
                        new int[][]{{1, 5}},
                        new int[]{2, 3},
                        new int[][]{{1, 5}},
                        "New interval fully contained in existing"
                },
                {
                        new int[][]{{1, 5}},
                        new int[]{0, 6},
                        new int[][]{{0, 6}},
                        "New interval fully consumes existing"
                },
                {
                        new int[][]{{3, 5}, {10, 12}},
                        new int[]{1, 2},
                        new int[][]{{1, 2}, {3, 5}, {10, 12}},
                        "Insert at the very beginning (no overlap)"
                },
                {
                        new int[][]{{3, 5}, {10, 12}},
                        new int[]{15, 18},
                        new int[][]{{3, 5}, {10, 12}, {15, 18}},
                        "Insert at the very end (no overlap)"
                },
                {
                        new int[][]{{1, 2}, {3, 4}, {5, 6}},
                        new int[]{2, 5},
                        new int[][]{{1, 6}},
                        "Chain reaction overlap"
                },
                {
                        new int[][]{{1, 5}},
                        new int[]{5, 6},
                        new int[][]{{1, 6}},
                        "Touching boundaries (end of A == start of New)"
                },
                {
                        new int[][]{{1, 10}},
                        new int[]{0, 1},
                        new int[][]{{0, 10}},
                        "Touching boundaries (start of A == end of New)"
                }
        };

        System.out.println("--- Running Tests ---");

        for (int i = 0; i < testCases.length; i++) {
            int[][] intervals = (int[][]) testCases[i][0];
            int[] newInterval = (int[]) testCases[i][1];
            int[][] expected = (int[][]) testCases[i][2];
            String description = (String) testCases[i][3];

            int[][] actual = orchestrator.insert(intervals, newInterval);

            boolean passed = Arrays.deepEquals(actual, expected);
            if (passed) {
                testsPassed++;
                System.out.printf("Test %d: PASSED (%s)\n", i + 1, description);
            } else {
                System.out.printf("Test %d: FAILED (%s)\n", i + 1, description);
                System.out.println("   Expected: " + Arrays.deepToString(expected));
                System.out.println("   Actual:   " + Arrays.deepToString(actual));
            }
        }

        System.out.println("\n--- Final Results ---");
        System.out.println("Total Tests: " + testCases.length);
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + (testCases.length - testsPassed));
    }
}