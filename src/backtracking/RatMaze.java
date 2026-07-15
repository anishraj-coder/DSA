package backtracking;

import java.util.*;

public class RatMaze {

    /**
     * Problem: Find all possible paths for a rat from (0,0) to (n-1, n-1).
     * Directions: 'U' (Up), 'D' (Down), 'L' (Left), 'R' (Right).
     * Output: List of paths in lexicographically sorted order.
     *
     * Constraints: 2 <= n <= 5, maze[i][j] is 0 or 1.
     */
    public List<String> findPath(int[][] arr) {
        List<String>ans=new ArrayList<>();
        helper(arr,0,0,new StringBuilder(),ans);
        return ans;
    }

    private void helper(int[][]arr,int i,int j,StringBuilder curr,List<String>ans){
        int n=arr.length;
        if(i==n-1&&j==n-1){
            ans.add(curr.toString());
            return;
        }

        if(i<0||j<0||j>=n||i>=n||arr[i][j]==0||arr[i][j]==2)return;
        int[][]dir=new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
        char[]pos=new char[]{'U','D','R','L'};
        arr[i][j]=2;
        for(int k=0;k<4;k++){
            int i1=i+dir[k][0],j1=j+dir[k][1];
            if(i1>=0&&j1>=0&&i1<n&&j1<n&&arr[i1][j1]==1){
                curr.append(pos[k]);
                helper(arr,i1,j1,curr,ans);
                curr.deleteCharAt(curr.length()-1);
            }
        }

        arr[i][j]=1;

    }

    public static void main(String[] args) {
        RatMaze solver = new RatMaze();

        // Test Case 1: Standard 4x4 from example
        int[][] maze1 = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}
        };
        List<String> expected1 = Arrays.asList("DDRDRR", "DRDDRR");

        // Test Case 2: Destination Blocked
        int[][] maze2 = {
                {1, 0},
                {1, 0}
        };
        List<String> expected2 = Collections.emptyList();

        // Test Case 3: Start Blocked (Edge Case)
        int[][] maze3 = {
                {0, 1},
                {1, 1}
        };
        List<String> expected3 = Collections.emptyList();

        // Test Case 4: Only one straight path
        int[][] maze4 = {
                {1, 1},
                {0, 1}
        };
        List<String> expected4 = Arrays.asList("RD");

        // Test Case 5: 3x3 with multiple paths
        int[][] maze5 = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        List<String> expected5 = Arrays.asList("DDRR", "RRDD");

        // Test Case 6: Path requires moving Up and Left
        int[][] maze6 = {
                {1, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        List<String> expected6 = Arrays.asList("DDRR", "DRRD", "RDDR", "RDRD");

        // Test Case 7: All 1s (Maximum possible paths for 3x3)
        int[][] maze7 = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        // Selection of paths in lexicographical order (partial list for validation)
        // Expected size for 3x3 full grid is 12 paths
        int expectedSize7 = 12;

        // Test Case 8: Snake-like narrow path
        int[][] maze8 = {
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 1}
        };
        List<String> expected8 = Arrays.asList("RDDR");

        // Test Case 9: Dead end
        int[][] maze9 = {
                {1, 1, 1},
                {1, 0, 0},
                {1, 1, 1}
        };
        List<String> expected9 = Collections.emptyList();

        // Test Case 10: 2x2 Full Grid
        int[][] maze10 = {
                {1, 1},
                {1, 1}
        };
        List<String> expected10 = Arrays.asList("DR", "RD");

        // Execution and Reporting
        Object[][] suite = {
                {"Example 4x4", maze1, expected1},
                {"Dest Blocked", maze2, expected2},
                {"Start Blocked", maze3, expected3},
                {"Straight path", maze4, expected4},
                {"3x3 Two paths", maze5, expected5},
                {"Complex 3x3", maze6, expected6},
                {"Full 3x3 Grid", maze7, expectedSize7},
                {"Snake Path", maze8, expected8},
                {"Dead End", maze9, expected9},
                {"Full 2x2 Grid", maze10, expected10}
        };

        System.out.println("RatMaze Test Suite Results:\n");
        System.out.printf("%-15s | %-20s | %-20s | %-10s%n", "Test Name", "Expected", "Actual", "Result");
        System.out.println("---------------------------------------------------------------------------------------");

        for (Object[] test : suite) {
            String name = (String) test[0];
            int[][] maze = (int[][]) test[1];
            List<String> actual = solver.findPath(maze);

            boolean passed;
            String expectedStr;

            if (test[2] instanceof Integer) {
                int expectedSize = (int) test[2];
                passed = actual.size() == expectedSize;
                expectedStr = "Size: " + expectedSize;
            } else {
                List<String> expectedList = (List<String>) test[2];
                passed = compareLists(expectedList, actual);
                expectedStr = expectedList.toString();
            }

            System.out.printf("%-15s | %-20s | %-20s | %-10s%n",
                    name,
                    truncate(expectedStr),
                    truncate(actual.toString()),
                    (passed ? "PASSED" : "FAILED"));
        }
    }

    private static boolean compareLists(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) return false;
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) return false;
        }
        return true;
    }

    private static String truncate(String s) {
        return s.length() > 20 ? s.substring(0, 17) + "..." : s;
    }
}