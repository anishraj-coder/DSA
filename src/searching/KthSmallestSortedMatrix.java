package searching;

import java.util.Arrays;

public class KthSmallestSortedMatrix {

    // TO BE IMPLEMENTED BY YOU
    public int kthSmallest(int[][] arr, int k) {
        int n=arr.length,low=arr[0][0],hi=arr[n-1][n-1],ans=-1;
        while(low<=hi){
            int mid=low+(hi-low)/2;
            int count=count(arr,mid);
            if(count>=k){
                hi=mid-1;
                ans=mid;
            }else low=mid+1;
        }
        return ans;
    }


    private int upperBound(int []arr,int k){
        int low=0,hi=arr.length-1,count=0;
        while(low<=hi){
            int mid=low+(hi-low)/2;
            if(arr[mid]<=k){
                count=mid+1;
                low=mid+1;
            }else{
                hi=mid-1;
            }
        }
        return count;
    }

    private int count(int[][]arr,int val){
        int count=0,n=arr.length;
        int row=0,col=n-1;
        while(row<n&&col>=0){
            if(arr[row][col]<=val){
                count+=col+1;
                row++;
            }else{
                col--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        KthSmallestSortedMatrix solver = new KthSmallestSortedMatrix();

        // Test Cases Setup
        TestCase[] testCases = new TestCase[]{
                // 1. Standard Example 1
                new TestCase(
                        new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}},
                        8,
                        13,
                        "Standard 3x3 matrix"
                ),
                // 2. Standard Example 2 (Minimal 1x1 matrix)
                new TestCase(
                        new int[][]{{-5}},
                        1,
                        -5,
                        "Minimal 1x1 matrix with negative value"
                ),
                // 3. Edge Case: Duplicate elements across rows/columns
                new TestCase(
                        new int[][]{{1, 2, 2}, {2, 2, 3}, {3, 3, 4}},
                        5,
                        2,
                        "Matrix with heavily duplicated elements"
                ),
                // 4. Edge Case: Finding the absolute smallest element (k = 1)
                new TestCase(
                        new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}},
                        1,
                        1,
                        "Boundary check: k = 1 (Smallest element)"
                ),
                // 5. Edge Case: Finding the absolute largest element (k = n^2)
                new TestCase(
                        new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}},
                        9,
                        15,
                        "Boundary check: k = n^2 (Largest element)"
                ),
                // 6. Hard Case: Large value variance with massive negative to positive gaps
                new TestCase(
                        new int[][]{{-100, -50, -5}, {-40, 0, 1000}, {50, 200, 1000000000}},
                        5,
                        0,
                        "Matrix with large range gaps and zeros"
                ),
                // 7. Edge Case: All elements are identical
                new TestCase(
                        new int[][]{{7, 7, 7}, {7, 7, 7}, {7, 7, 7}},
                        6,
                        7,
                        "Matrix where all elements are identical"
                ),
                // 8. Hard Case: Strict column-heavy progression vs row progression
                new TestCase(
                        new int[][]{{1, 10, 20}, {2, 11, 21}, {3, 12, 22}},
                        4,
                        10,
                        "Matrix with steep column jumps"
                ),
                // 9. Hard Case: 4x4 balanced matrix targeting a middle element
                new TestCase(
                        new int[][]{
                                {1,  4,  7, 11},
                                {2,  5,  8, 12},
                                {3,  6,  9, 16},
                                {10, 13, 14, 17}
                        },
                        10,
                        9,
                        "4x4 balanced matrix targeting mid-rank"
                ),
                // 10. Edge Case: 2x2 grid representing maximum and minimum integer bounds
                new TestCase(
                        new int[][]{{-1000000000, -500000000}, {500000000, 1000000000}},
                        3,
                        500000000,
                        "2x2 matrix testing extreme outer constraint values"
                )
        };

        // Execution and Evaluation Loop
        int passed = 0;
        System.out.println("Executing Test Cases...\n");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int actualOutput = solver.kthSmallest(deepCopy(tc.matrix), tc.k);
            boolean isCorrect = actualOutput == tc.expectedOutput;

            if (isCorrect) {
                passed++;
                System.out.printf("[PASS] Test Case %d: %s\n", i + 1, tc.description);
            } else {
                System.out.printf("[FAIL] Test Case %d: %s\n", i + 1, tc.description);
                System.out.println("       Matrix: " + Arrays.deepToString(tc.matrix));
                System.out.println("       k: " + tc.k);
                System.out.println("       Expected: " + tc.expectedOutput);
                System.out.println("       Actual:   " + actualOutput);
            }
            System.out.println("------------------------------------------------------------------");
        }

        System.out.printf("\nExecution Summary: %d/%d Passed.\n", passed, testCases.length);
    }

    // Helper class to safely bundle test scenarios
    private static class TestCase {
        int[][] matrix;
        int k;
        int expectedOutput;
        String description;

        TestCase(int[][] matrix, int k, int expectedOutput, String description) {
            this.matrix = matrix;
            this.k = k;
            this.expectedOutput = expectedOutput;
            this.description = description;
        }
    }

    // Prevents potential reference leakages modifying source matrices across test instances
    private static int[][] deepCopy(int[][] source) {
        if (source == null) return null;
        int[][] result = new int[source.length][];
        for (int i = 0; i < source.length; i++) {
            result[i] = source[i].clone();
        }
        return result;
    }
}