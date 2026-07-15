package arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeroes {

    /**
     * Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's.
     * You must do it in place.
     * * Constraints:
     * m == matrix.length, n == matrix[0].length
     * 1 <= m, n <= 200
     */
    public void setZeroes(int[][] arr) {
        int m=arr.length,n=arr[0].length;
        boolean row=false,col=false;
        for(int i=0;i<m;i++){
            if(arr[i][0]==0){
                col=true;
                break;
            }
        }
        for(int i=0;i<n;i++){
            if(arr[0][i]==0){
                row=true;
                break;
            }
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(arr[i][j]==0){
                    arr[i][0]=0;
                    arr[0][j]=0;
                }
            }
        }

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(arr[i][0]==0||arr[0][j]==0)arr[i][j]=0;
            }
        }
        if(col){
            for(int i=0;i<m;i++){
                arr[i][0]=0;
            }
        }
        if(row){
            for(int i=0;i<n;i++){
                arr[0][i]=0;
            }
        }
    }

    public static void main(String[] args) {
        SetMatrixZeroes solver = new SetMatrixZeroes();

        // Test Cases
        int[][][] inputs = {
                {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}, // 1. Standard 3x3
                {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}}, // 2. Multiple zeroes
                {{1}}, // 3. Single element (non-zero)
                {{0}}, // 4. Single element (zero)
                {{1, 2, 3}, {4, 0, 6}}, // 5. Rectangular matrix
                {{1, 0}}, // 6. 1D row matrix
                {{1}, {0}}, // 7. 1D column matrix
                {{0, 0}, {0, 0}}, // 8. All zeroes
                {{1, 2}, {3, 4}}, // 9. No zeroes
                {{1, 1, 3}, {0, 5, 6}, {7, 8, 9}} // 10. Overlapping row/col impact
        };

        int[][][] expected = {
                {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}},
                {{0, 0, 0, 0}, {0, 4, 5, 0}, {0, 3, 1, 0}},
                {{1}},
                {{0}},
                {{1, 0, 3}, {0, 0, 0}},
                {{0, 0}},
                {{0}, {0}},
                {{0, 0}, {0, 0}},
                {{1, 2}, {3, 4}},
                {{0, 1, 3}, {0, 0, 0}, {0, 8, 9}}
        };

        for (int i = 0; i < inputs.length; i++) {
            // Deep copy input for display and processing
            int[][] matrix = copyMatrix(inputs[i]);
            int[][] target = expected[i];

            solver.setZeroes(matrix);

            boolean passed = compareMatrices(matrix, target);

            System.out.println("Test Case " + (i + 1) + ": " + (passed ? "PASSED" : "FAILED"));
            if (!passed) {
                System.out.println("   Input:    " + Arrays.deepToString(inputs[i]));
                System.out.println("   Expected: " + Arrays.deepToString(target));
                System.out.println("   Actual:   " + Arrays.deepToString(matrix));
            }
        }
    }

    private static int[][] copyMatrix(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }

    private static boolean compareMatrices(int[][] m1, int[][] m2) {
        if (m1.length != m2.length) return false;
        for (int i = 0; i < m1.length; i++) {
            if (!Arrays.equals(m1[i], m2[i])) return false;
        }
        return true;
    }
}