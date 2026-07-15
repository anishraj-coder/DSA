package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralMatrix {

    /**
     * Given an m x n matrix, return all elements of the matrix in spiral order.
     */
    public List<Integer> spiralOrder(int[][] arr) {
        List<Integer>ans=new ArrayList<>();
        int m=arr.length,n=arr[0].length;
        if(n==1){
            for(int i=0;i<m;i++)ans.add(arr[i][0]);
            return ans;
        }
        if(m==1){
            for(int i=0;i<n;i++)ans.add(arr[0][i]);
            return ans;
        }

        int csteps=n-1,rsteps=m-1,i=0,j=0;

        while(rsteps>0&&csteps>0){
            for(int k=0;k<csteps;k++,j++)ans.add(arr[i][j]);
            for(int k=0;k<rsteps;k++,i++)ans.add(arr[i][j]);
            for(int k=0;k<csteps;k++,j--)ans.add(arr[i][j]);
            for(int k=0;k<rsteps;k++,i--)ans.add(arr[i][j]);
            i++;j++;
            csteps-=2;
            rsteps-=2;
        }
        if(rsteps==0){
            for(int k=0;k<=csteps;k++,j++)ans.add(arr[i][j]);
        }else if(csteps==0){
            for(int k=0;k<=rsteps;k++,i++)ans.add(arr[i][j]);
        }

        return ans;
    }

    public static void main(String[] args) {
        SpiralMatrix solver = new SpiralMatrix();

        // Defining test cases
        Object[][] testCases = {
                // Case 1: Standard 3x3 square matrix
                {
                        new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                        Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5)
                },
                // Case 2: Rectangular matrix (m < n)
                {
                        new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}},
                        Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7)
                },
                // Case 3: Rectangular matrix (m > n)
                {
                        new int[][]{{1, 2}, {3, 4}, {5, 6}},
                        Arrays.asList(1, 2, 4, 6, 5, 3)
                },
                // Case 4: Single Row
                {
                        new int[][]{{1, 2, 3, 4, 5}},
                        Arrays.asList(1, 2, 3, 4, 5)
                },
                // Case 5: Single Column
                {
                        new int[][]{{1}, {2}, {3}, {4}},
                        Arrays.asList(1, 2, 3, 4)
                },
                // Case 6: 1x1 Matrix
                {
                        new int[][]{{99}},
                        Arrays.asList(99)
                },
                // Case 7: 2x2 Matrix
                {
                        new int[][]{{1, 2}, {3, 4}},
                        Arrays.asList(1, 2, 4, 3)
                },
                // Case 8: Matrix with negative numbers and zeros
                {
                        new int[][]{{0, -1}, {-2, -3}},
                        Arrays.asList(0, -1, -3, -2)
                },
                // Case 9: Thin 4x2 matrix
                {
                        new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}},
                        Arrays.asList(1, 2, 4, 6, 8, 7, 5, 3)
                },
                // Case 10: 5x1 Matrix (Deep vertical)
                {
                        new int[][]{{1}, {2}, {3}, {4}, {5}},
                        Arrays.asList(1, 2, 3, 4, 5)
                }
        };

        for (int i = 0; i < testCases.length; i++) {
            int[][] input = (int[][]) testCases[i][0];
            List<Integer> expected = (List<Integer>) testCases[i][1];
            List<Integer> actual = solver.spiralOrder(input);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input:    " + Arrays.deepToString(input));
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);

            if (expected.equals(actual)) {
                System.out.println("Result:   PASSED ✅");
            } else {
                System.out.println("Result:   FAILED ❌");
            }
            System.out.println("--------------------------------------------------");
        }
    }
}