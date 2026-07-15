package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    /**
     * Problem: Merge all overlapping intervals.
     * Time Complexity Goal: O(N log N)
     * Space Complexity Goal: O(N)
     */
    public int[][] merge(int[][] arr) {
        int n=arr.length;
        if(n<=1)return arr;
        Arrays.sort(arr,(a,b)->(a[0]!=b[0])?Integer.compare(a[0],b[0]):Integer.compare(a[1],b[1]));
        int s1=arr[0][0],e1=arr[0][1];
        ArrayList<Integer[]>temp=new ArrayList<>();
        for(int i=1;i<n;i++){
            int e2=arr[i][1],s2=arr[i][0];
            if(s2>e1){
                Integer[]t=new Integer[2];
                t[0]=s1;
                t[1]=e1;
                temp.add(t);
                s1=arr[i][0];
                e1=arr[i][1];
            }

            e1=Math.max(e1,e2);
        }
        temp.add(new Integer[]{s1,e1});

        int[][]res=new int[temp.size()][2];
        for(int i=0;i<temp.size();i++){
            res[i][0]=temp.get(i)[0];
            res[i][1]=temp.get(i)[1];
        }
        return res;
    }

    public static void main(String[] args) {
        MergeIntervals solver = new MergeIntervals();

        // Test Cases: {Input, Expected Output}
        Object[][][] testCases = {
                { // 1. Standard overlap
                        new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}},
                        new int[][]{{1, 6}, {8, 10}, {15, 18}}
                },
                { // 2. Touched boundaries
                        new int[][]{{1, 4}, {4, 5}},
                        new int[][]{{1, 5}}
                },
                { // 3. Unsorted input
                        new int[][]{{4, 7}, {1, 4}},
                        new int[][]{{1, 7}}
                },
                { // 4. Fully contained interval
                        new int[][]{{1, 10}, {2, 3}, {4, 5}, {6, 7}},
                        new int[][]{{1, 10}}
                },
                { // 5. Single interval
                        new int[][]{{1, 5}},
                        new int[][]{{1, 5}}
                },
                { // 6. Empty input
                        new int[][]{},
                        new int[][]{}
                },
                { // 7. Multiple overlaps into one
                        new int[][]{{1, 4}, {2, 5}, {3, 8}},
                        new int[][]{{1, 8}}
                },
                { // 8. No overlaps
                        new int[][]{{1, 2}, {3, 4}, {5, 6}},
                        new int[][]{{1, 2}, {3, 4}, {5, 6}}
                },
                { // 9. Identical intervals
                        new int[][]{{1, 4}, {1, 4}, {1, 4}},
                        new int[][]{{1, 4}}
                },
                { // 10. Large gap with small overlaps
                        new int[][]{{1, 2}, {2, 3}, {10, 15}, {12, 20}},
                        new int[][]{{1, 3}, {10, 20}}
                }
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[][] input = (int[][]) testCases[i][0];
            int[][] expected = (int[][]) testCases[i][1];
            int[][] actual = solver.merge(input);

            boolean isMatch = Arrays.deepEquals(actual, expected);
            if (isMatch) passed++;

            System.out.println("Test Case " + (i + 1) + ": " + (isMatch ? "PASSED" : "FAILED"));
            System.out.println("   Input:    " + Arrays.deepToString(input));
            System.out.println("   Expected: " + Arrays.deepToString(expected));
            System.out.println("   Actual:   " + Arrays.deepToString(actual));
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + testCases.length + " cases passed.");
    }
}