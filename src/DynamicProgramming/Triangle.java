package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class Triangle {

    // TO DO: Implement your solution here
    // Try to solve it using only O(n) extra space, where n is the number of rows!
    public int minimumTotal(List<List<Integer>> arr) {
        int n=arr.size();
        int[][]dp=new int[n][n];
        int[]next=new int[n],curr=new int[n];
        for(int i=0;i<n;i++)next[i]=arr.get(n-1).get(i);
        for(int i=n-2;i>=0;i--){
            for(int j=0;j<=i;j++){
                int min=arr.get(i).get(j)+next[j];
                min=Math.min(min,arr.get(i).get(j)+next[j+1]);
                curr[j]=min;
            }
            next=curr.clone();
        }
        return next[0];
    }

    public int helper(List<List<Integer>>arr,int i,int j){
        int n=arr.size();
        if(i==n-1)return arr.get(i).get(j);
        int first=helper(arr,i+1,j)+arr.get(i).get(j);
        int second=helper(arr,i+1,j+1)+arr.get(i).get(j);

        return Math.min(second,first);
    }

    public static void main(String[] args) {
        Triangle solver = new Triangle();
        int passed = 0;
        int total = 10;

        // ==========================================
        // TEST CASE 1: Standard Example 1
        // ==========================================
        List<List<Integer>> t1 = makeTriangle(new int[][]{
                {2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}
        });
        int expected1 = 11; // 2 -> 3 -> 5 -> 1
        int actual1 = solver.minimumTotal(t1);
        passed += verify(1, expected1, actual1);

        // ==========================================
        // TEST CASE 2: Standard Example 2 (Single Negative Element)
        // ==========================================
        List<List<Integer>> t2 = makeTriangle(new int[][]{
                {-10}
        });
        int expected2 = -10;
        int actual2 = solver.minimumTotal(t2);
        passed += verify(2, expected2, actual2);

        // ==========================================
        // TEST CASE 3: Single Positive Element
        // ==========================================
        List<List<Integer>> t3 = makeTriangle(new int[][]{
                {0}
        });
        int expected3 = 0;
        int actual3 = solver.minimumTotal(t3);
        passed += verify(3, expected3, actual3);

        // ==========================================
        // TEST CASE 4: Anti-Greedy Path Choice
        // Picking 9 instead of 2 early leads to a massive negative drop later
        // ==========================================
        List<List<Integer>> t4 = makeTriangle(new int[][]{
                {1},
                {2, 9},
                {10, 10, -50}
        });
        int expected4 = -40; // 1 -> 9 -> -50
        int actual4 = solver.minimumTotal(t4);
        passed += verify(4, expected4, actual4);

        // ==========================================
        // TEST CASE 5: All Values Negative
        // ==========================================
        List<List<Integer>> t5 = makeTriangle(new int[][]{
                {-1},
                {-2, -3},
                {-4, -5, -6}
        });
        int expected5 = -10; // -1 -> -3 -> -6
        int actual5 = solver.minimumTotal(t5);
        passed += verify(5, expected5, actual5);

        // ==========================================
        // TEST CASE 6: Strict Left Edge Path
        // ==========================================
        List<List<Integer>> t6 = makeTriangle(new int[][]{
                {1},
                {2, 20},
                {3, 20, 20},
                {4, 20, 20, 20}
        });
        int expected6 = 10; // 1 -> 2 -> 3 -> 4
        int actual6 = solver.minimumTotal(t6);
        passed += verify(6, expected6, actual6);

        // ==========================================
        // TEST CASE 7: Strict Right Edge Path
        // ==========================================
        List<List<Integer>> t7 = makeTriangle(new int[][]{
                {1},
                {20, 2},
                {20, 20, 3},
                {20, 20, 20, 4}
        });
        int expected7 = 10; // 1 -> 2 -> 3 -> 4
        int actual7 = solver.minimumTotal(t7);
        passed += verify(7, expected7, actual7);

        // ==========================================
        // TEST CASE 8: Large Values Near Upper Constraints (10^4)
        // ==========================================
        List<List<Integer>> t8 = makeTriangle(new int[][]{
                {10000},
                {10000, 10000},
                {10000, 10000, 10000}
        });
        int expected8 = 30000;
        int actual8 = solver.minimumTotal(t8);
        passed += verify(8, expected8, actual8);

        // ==========================================
        // TEST CASE 9: Alternating Choice Path Zig-Zag
        // ==========================================
        List<List<Integer>> t9 = makeTriangle(new int[][]{
                {0},
                {5, -5},
                {-10, 10, 10},
                {20, 20, -15, 20}
        });
        int expected9 = -20; // 0 -> -5 -> 10 -> -15
        int actual9 = solver.minimumTotal(t9);
        passed += verify(9, expected9, actual9);

        // ==========================================
        // TEST CASE 10: Zero-Sum Dominated Neutral Paths
        // ==========================================
        List<List<Integer>> t10 = makeTriangle(new int[][]{
                {-3},
                {1, 2},
                {5, -1, 4},
                {-2, 8, -3, 6}
        });
        int expected10 = -5; // -3 -> 2 -> -1 -> -3
        int actual10 = solver.minimumTotal(t10);
        passed += verify(10, expected10, actual10);

        // ==========================================
        // SUMMARY RESULT
        // ==========================================
        System.out.println("\n======================================");
        System.out.println("FINAL RESULT: " + passed + " / " + total + " TEST CASES PASSED");
        System.out.println("======================================");
    }

    private static List<List<Integer>> makeTriangle(int[][] arr) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int[] row : arr) {
            List<Integer> listRow = new ArrayList<>();
            for (int val : row) {
                listRow.add(val);
            }
            triangle.add(listRow);
        }
        return triangle;
    }

    private static int verify(int caseNum, int expected, int actual) {
        if (expected == actual) {
            System.out.println("Test Case " + caseNum + ": [PASSED]");
            return 1;
        } else {
            System.out.println("Test Case " + caseNum + ": [FAILED]");
            System.out.println("   Expected Output: " + expected);
            System.out.println("   Actual Output:   " + actual);
            return 0;
        }
    }
}