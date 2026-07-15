package hashmap;

import java.util.*;

/**
 * GridIllumination handles queries on a massive grid to check if cells are lit
 * and turns off lamps in a 3x3 area around query points.
 */
public class GridIllumination {

    /**
     * Solves the grid illumination problem.
     * @param n The size of the n x n grid.
     * @param lamps 2D array of lamp positions.
     * @param queries 2D array of query positions.
     * @return Array of 1s (lit) and 0s (dark).
     */
    public int[] solveIllumination(int n, int[][] lamps, int[][] queries) {
        HashMap<Integer,Integer>row,col,dig,ant;
        row=new HashMap<>();
        col=new HashMap<>();
        ant=new HashMap<>();
        dig=new HashMap<>();
        HashMap<Long,Integer>positions=new HashMap<>();
        for(int[]a:lamps){
            int x=a[1],y=a[0];
            row.put(y,row.getOrDefault(y,0)+1);
            col.put(x,col.getOrDefault(x,0)+1);
            ant.put(x+y,ant.getOrDefault(x+y,0)+1);
            dig.put(y-x,dig.getOrDefault(y-x,0)+1);
            positions.put((long)n*y+x,positions.getOrDefault((long)n*y+x,0)+1);
        }
        int[]ans=new int[queries.length];
        int[][] dir = new int[][]{{0,0}, {0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {-1,-1}, {-1,1}, {1,-1}};
        for(int i=0;i<queries.length;i++){
            int[]q=queries[i];
            int x=q[1],y=q[0];
            if(col.containsKey(x)||row.containsKey(y)||dig.containsKey(y-x)||ant.containsKey(x+y)||positions.containsKey((long)y*n+x))ans[i]=1;
            else ans[i]=0;
            for(int[]d:dir){
                int x1=x+d[1],y1=y+d[0];
                if(x1<n&&y1<n&&x1>=0&&y1>=0){
                    int count = positions.getOrDefault((long) y1 * n + x1, 0);
                    if (count == 0) continue;
                    row.put(y1, row.getOrDefault(y1, 0) - count);
                    col.put(x1, col.getOrDefault(x1, 0) - count);
                    dig.put(y1 - x1, dig.getOrDefault(y1 - x1, 0) - count);
                    ant.put(x1 + y1, ant.getOrDefault(x1 + y1, 0) - count);
                    positions.put((long) y1 * n + x1, positions.getOrDefault((long) y1 * n + x1, 0) - count);
                    if (row.get(y1) <= 0) row.remove(y1);
                    if (col.get(x1) <= 0) col.remove(x1);
                    if (dig.get(y1 - x1) <= 0) dig.remove(y1 - x1);
                    if (ant.get(x1 + y1) <= 0) ant.remove(x1 + y1);
                    if (positions.get((long) y1 * n + x1) <= 0) positions.remove((long) y1 * n + x1);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        GridIllumination solver = new GridIllumination();
        int passed = 0;
        int total = 10;

        System.out.println("Starting GridIllumination Test Suite...\n");

        // Test 1: Basic Example 1
        passed += test(1, 5, new int[][]{{0, 0}, {4, 4}}, new int[][]{{1, 1}, {1, 0}}, new int[]{1, 0});

        // Test 2: Example 3
        passed += test(2, 5, new int[][]{{0, 0}, {0, 4}}, new int[][]{{0, 4}, {0, 1}, {1, 4}}, new int[]{1, 1, 0});

        // Test 3: Large N, distant lamps
        passed += test(3, 1000000000, new int[][]{{0, 0}, {999999999, 999999999}}, new int[][]{{500, 500}, {0, 999999999}}, new int[]{0, 1});

        // Test 4: Duplicate lamps in input (Should only count once for turning off)
        passed += test(4, 5, new int[][]{{0, 0}, {0, 0}, {4, 4}}, new int[][]{{1, 1}}, new int[]{1});

        // Test 5: Turning off lamp at query point
        passed += test(5, 5, new int[][]{{2, 2}}, new int[][]{{2, 2}, {2, 2}}, new int[]{1, 0});

        // Test 6: Diagonal illumination (r - c)
        passed += test(6, 10, new int[][]{{4, 4}}, new int[][]{{0, 0}, {8, 8}}, new int[]{1, 1});

        // Test 7: Anti-diagonal illumination (r + c)
        passed += test(7, 10, new int[][]{{4, 4}}, new int[][]{{0, 8}, {8, 0}}, new int[]{1, 1});

        // Test 8: 3x3 turn off zone - Edge of grid
        passed += test(8, 5, new int[][]{{0, 1}, {1, 0}}, new int[][]{{0, 0}, {0, 1}}, new int[]{1, 0});

        // Test 9: Multiple lamps on same row/col
        passed += test(9, 10, new int[][]{{1, 1}, {1, 5}}, new int[][]{{1, 3}, {1, 5}, {1, 8}}, new int[]{1, 1, 0});

        // Test 10: All lamps on one diagonal
        passed += test(10, 5, new int[][]{{0, 0}, {1, 1}, {2, 2}}, new int[][]{{3, 3}, {0, 0}, {4, 4}}, new int[]{1, 1, 0});

        System.out.println("\n-------------------------------------------");
        System.out.println("Final Result: " + passed + "/" + total + " Tests Passed.");
    }

    private static int test(int id, int n, int[][] lamps, int[][] queries, int[] expected) {
        GridIllumination solver = new GridIllumination();
        int[] result = solver.solveIllumination(n, lamps, queries);

        if (result.length != expected.length) {
            System.out.println("Test " + id + " FAILED: Expected length " + expected.length + ", got " + result.length);
            return 0;
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i] != expected[i]) {
                System.out.println("Test " + id + " FAILED at query " + i);
                System.out.println("   Expected: " + Arrays.toString(expected));
                System.out.println("   Actual:   " + Arrays.toString(result));
                return 0;
            }
        }
        System.out.println("Test " + id + " PASSED");
        return 1;
    }
}