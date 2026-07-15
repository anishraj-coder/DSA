package arrays;

import java.util.Arrays;

public class RotateImage {

    /**
     * You are given an n x n 2D matrix representing an image,
     * rotate the image by 90 degrees (clockwise) in-place.
     */
    public void rotate(int[][] arr) {
        int n=arr.length;

        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                int t=arr[i][j];
                arr[i][j]=arr[j][i];
                arr[j][i]=t;
            }
        }

        for(int[]a:arr){
            reverse(a,0,n-1);
        }

    }



    public void reverse(int[] arr,int s,int e){
        while(s<e){
            int t=arr[s];
            arr[s]=arr[e];
            arr[e]=t;
            s++;
            e--;
        }
    }

    public static void main(String[] args) {
        RotateImage solver = new RotateImage();

        int[][][] inputs = {
                // Case 1: Standard 3x3 matrix
                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                // Case 2: 4x4 matrix
                {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}},
                // Case 3: 1x1 matrix (Edge case)
                {{1}},
                // Case 4: 2x2 matrix (Smallest even)
                {{1, 2}, {3, 4}},
                // Case 5: 5x5 matrix
                {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}},
                // Case 6: Matrix with negative numbers
                {{-1, -2}, {-3, -4}},
                // Case 7: Matrix with same elements
                {{1, 1}, {1, 1}},
                // Case 8: Matrix where only center is different
                {{1, 1, 1}, {1, 2, 1}, {1, 1, 1}},
                // Case 9: 3x3 with zeros
                {{0, 1, 0}, {0, 0, 0}, {1, 0, 1}},
                // Case 10: 4x4 with symmetrical pattern
                {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 0}, {1, 0, 0, 1}}
        };

        int[][][] expected = {
                {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}},
                {{15, 13, 2, 5}, {14, 3, 4, 1}, {12, 6, 8, 9}, {16, 7, 10, 11}},
                {{1}},
                {{3, 1}, {4, 2}},
                {{21, 16, 11, 6, 1}, {22, 17, 12, 7, 2}, {23, 18, 13, 8, 3}, {24, 19, 14, 9, 4}, {25, 20, 15, 10, 5}},
                {{-3, -1}, {-4, -2}},
                {{1, 1}, {1, 1}},
                {{1, 1, 1}, {1, 2, 1}, {1, 1, 1}},
                {{1, 0, 0}, {0, 0, 1}, {1, 0, 0}},
                {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 0}, {1, 0, 0, 1}}
        };

        for (int i = 0; i < inputs.length; i++) {
            int[][] matrixToTest = copyMatrix(inputs[i]);
            solver.rotate(matrixToTest);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Expected: " + Arrays.deepToString(expected[i]));
            System.out.println("Actual:   " + Arrays.deepToString(matrixToTest));

            if (Arrays.deepEquals(matrixToTest, expected[i])) {
                System.out.println("Result:   PASSED ✅");
            } else {
                System.out.println("Result:   FAILED ❌");
            }
            System.out.println("--------------------------------------------------");
        }
    }

    private static int[][] copyMatrix(int[][] matrix) {
        int[][] copy = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = matrix[i].clone();
        }
        return copy;
    }
}