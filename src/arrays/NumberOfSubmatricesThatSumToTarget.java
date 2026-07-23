package arrays;

import java.util.Arrays;
import java.util.HashMap;

public class NumberOfSubmatricesThatSumToTarget {

    /**
     * Implement your solution here.
     *
     * @param arr 2D integer array
     * @param target target sum
     * @return number of non-empty submatrices that sum to target
     */
    public int numSubmatrixSumTarget(int[][] arr, int target) {
        int m=arr.length,n=arr[0].length;
        for(int i=0;i<m;i++){
            for(int j=1;j<n;j++)arr[i][j]+=arr[i][j-1];
        }
        int count=0;
        for(int c1=0;c1<n;c1++){
            for(int c2=c1;c2<n;c2++){
                HashMap<Integer,Integer>map=new HashMap<>();
                map.put(0,1);
                int sum=0;
                for(int r=0;r<m;r++){
                    sum+=arr[r][c2]-((c1>0)?arr[r][c1-1]:0);
                    int k=sum-target;
                    count+=map.getOrDefault(k,0);
                    map.put(sum,map.getOrDefault(sum,0)+1);
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        NumberOfSubmatricesThatSumToTarget runner = new NumberOfSubmatricesThatSumToTarget();

        // Test Cases Setup
        TestCase[] testCases = new TestCase[] {
                // 1. Standard Example 1
                new TestCase(
                        new int[][] {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}},
                        0,
                        4,
                        "Standard 3x3 matrix with target 0"
                ),
                // 2. Standard Example 2 (with negatives)
                new TestCase(
                        new int[][] {{1, -1}, {-1, 1}},
                        0,
                        5,
                        "2x2 matrix with negative numbers"
                ),
                // 3. Single element (target not matched)
                new TestCase(
                        new int[][] {{904}},
                        0,
                        0,
                        "1x1 matrix, target not matched"
                ),
                // 4. Single element (target matched)
                new TestCase(
                        new int[][] {{5}},
                        5,
                        1,
                        "1x1 matrix, target matched"
                ),
                // 5. Single row matrix
                new TestCase(
                        new int[][] {{1, 2, 3, 4}},
                        5,
                        1, // Subarray [2, 3]
                        "Single row matrix (1x4)"
                ),
                // 6. Single column matrix
                new TestCase(
                        new int[][] {{1}, {2}, {3}, {4}},
                        5,
                        1, // Subarray [2, 3]
                        "Single column matrix (4x1)"
                ),
                // 7. Matrix with all zeroes
                new TestCase(
                        new int[][] {{0, 0}, {0, 0}},
                        0,
                        9, // 4 (1x1) + 2 (1x2) + 2 (2x1) + 1 (2x2)
                        "All zero matrix"
                ),
                // 8. Large target not attainable
                new TestCase(
                        new int[][] {{1, 2}, {3, 4}},
                        100,
                        0,
                        "Target out of range/unreachable"
                ),
                // 9. Rectangular matrix with positive and negative sums
                new TestCase(
                        new int[][] {
                                {0, 1,  0, 0},
                                {1, 1,  1, 0},
                                {0, 1,  0, 0},
                                {0, 0,  0, 1}
                        },
                        0,
                        8,
                        "4x4 sparse matrix with multiple submatrices summing to 0"
                ),
                // 10. Harder/Larger case with multiple overlapping target submatrices
                new TestCase(
                        new int[][] {
                                { 2, -1,  3},
                                {-1,  2, -1},
                                { 3, -1,  2}
                        },
                        3,
                        7,
                        "3x3 symmetric matrix with multiple overlapping submatrix targets"
                )
        };

        // Run Test Suite
        int passed = 0;
        System.out.println("==================================================");
        System.out.println(" Running Tests for: NumberOfSubmatricesThatSumToTarget");
        System.out.println("==================================================\n");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];

            // Deep copy input matrix to avoid modification issues
            int[][] matrixCopy = deepCopy(tc.matrix);

            int actual = runner.numSubmatrixSumTarget(matrixCopy, tc.target);
            boolean isCorrect = actual == tc.expected;

            if (isCorrect) {
                passed++;
                System.out.printf("[PASS] Test %2d: %s\n", (i + 1), tc.description);
            } else {
                System.out.printf("[FAIL] Test %2d: %s\n", (i + 1), tc.description);
                System.out.println("       Input Matrix:");
                printMatrix(tc.matrix);
                System.out.println("       Target:   " + tc.target);
                System.out.println("       Expected: " + tc.expected);
                System.out.println("       Actual:   " + actual);
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.printf("\nTest Results: %d/%d Passed.\n", passed, testCases.length);
        System.out.println("==================================================");
    }

    // Helper class for organizing test cases
    private static class TestCase {
        int[][] matrix;
        int target;
        int expected;
        String description;

        TestCase(int[][] matrix, int target, int expected, String description) {
            this.matrix = matrix;
            this.target = target;
            this.expected = expected;
            this.description = description;
        }
    }

    // Helper method to clone 2D array
    private static int[][] deepCopy(int[][] original) {
        if (original == null) return null;
        int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = original[i].clone();
        }
        return result;
    }

    // Helper method to print 2D array formatted
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println("         " + Arrays.toString(row));
        }
    }
}