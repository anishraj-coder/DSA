package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalListIntersections {

    /**
     * Finds the intersection of two sorted, disjoint interval lists.
     * @param arr1  An array of closed intervals [start, end]
     * @param arr2 An array of closed intervals [start, end]
     * @return An array representing the intersections
     */
    public int[][] intervalIntersection(int[][] arr1, int[][] arr2) {
        int m=arr1.length,n=arr2.length;
        if(m==0)return new int[][]{{}};
        if(n==0)return new int[][]{{}};
        int i=0,j=0;
        ArrayList<int[]>res=new ArrayList<>();
        while(i<m&&j<n){
            int s1=arr1[i][0],e1=arr1[i][1];
            int s2=arr2[j][0], e2=arr2[j][1];
            if(s1<=s2){
                if(e1>=s2){
                    int s=Math.max(s1,s2);
                    int e=Math.min(e1,e2);
                    res.add(new int[]{s,e});
                }
            }else{
                if(e2>=s1){
                    int s=Math.max(s1,s2);
                    int e=Math.min(e1,e2);
                    res.add(new int[]{s,e});
                }
            }
            if(e1>=e2)j++;
            else i++;
        }
        int[][]ans=new int[res.size()][2];
        for(int x=0;x<res.size();x++){
            ans[x][0]=res.get(x)[0];
            ans[x][1]=res.get(x)[1];
        }
        return ans;
    }

    public static void main(String[] args) {
        IntervalListIntersections solver = new IntervalListIntersections();

        // Test Case Definitions
        TestCase[] testCases = {
                new TestCase(
                        "Example 1: Multiple intersections",
                        new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}},
                        new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}},
                        new int[][]{{1, 2}, {5, 5}, {8, 10}, {15, 23}, {24, 24}, {25, 25}}
                ),
                new TestCase(
                        "Example 2: One list empty",
                        new int[][]{{1, 3}, {5, 9}},
                        new int[][]{},
                        new int[][]{}
                ),
                new TestCase(
                        "Single point intersection (Touch at boundary)",
                        new int[][]{{1, 2}},
                        new int[][]{{2, 3}},
                        new int[][]{{2, 2}}
                ),
                new TestCase(
                        "Complete overlap",
                        new int[][]{{1, 10}},
                        new int[][]{{2, 5}, {7, 9}},
                        new int[][]{{2, 5}, {7, 9}}
                ),
                new TestCase(
                        "No intersections",
                        new int[][]{{1, 2}, {5, 6}},
                        new int[][]{{3, 4}, {7, 8}},
                        new int[][]{}
                ),
                new TestCase(
                        "Large gap then overlapping",
                        new int[][]{{100, 200}},
                        new int[][]{{1, 50}, {150, 250}},
                        new int[][]{{150, 200}}
                ),
                new TestCase(
                        "Identical lists",
                        new int[][]{{1, 5}, {10, 15}},
                        new int[][]{{1, 5}, {10, 15}},
                        new int[][]{{1, 5}, {10, 15}}
                ),
                new TestCase(
                        "One interval covering multiple in the other list",
                        new int[][]{{1, 20}},
                        new int[][]{{2, 3}, {5, 6}, {8, 9}, {15, 18}},
                        new int[][]{{2, 3}, {5, 6}, {8, 9}, {15, 18}}
                ),
                new TestCase(
                        "Back-to-back single point intervals",
                        new int[][]{{1, 1}, {2, 2}, {3, 3}},
                        new int[][]{{1, 3}},
                        new int[][]{{1, 1}, {2, 2}, {3, 3}}
                ),
                new TestCase(
                        "Constraints boundary (Max values)",
                        new int[][]{{1000000000 - 5, 1000000000}},
                        new int[][]{{1000000000 - 10, 1000000000}},
                        new int[][]{{999999995, 1000000000}}
                )
        };

        // Execution and Reporting
        int passed = 0;
        System.out.println("--- Starting Test Suite ---");
        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int[][] actual = solver.intervalIntersection(tc.list1, tc.list2);
            boolean isMatch = Arrays.deepEquals(actual, tc.expected);

            if (isMatch) passed++;

            System.out.println("Test Case " + (i + 1) + ": " + tc.description);
            System.out.println("   Expected: " + Arrays.deepToString(tc.expected));
            System.out.println("   Actual:   " + Arrays.deepToString(actual));
            System.out.println("   Result:   " + (isMatch ? "✅ PASSED" : "❌ FAILED"));
            System.out.println("---------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + testCases.length + " tests passed.");
    }

    // Helper class for test case structure
    static class TestCase {
        String description;
        int[][] list1;
        int[][] list2;
        int[][] expected;

        TestCase(String description, int[][] list1, int[][] list2, int[][] expected) {
            this.description = description;
            this.list1 = list1;
            this.list2 = list2;
            this.expected = expected;
        }
    }
}