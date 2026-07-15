package BinarySearchTree;

import java.util.*;

public class SortedTwoDimensionalMatrix {

    public boolean searchMatrix(int[][] arr, int target) {
        if(arr.length==0||arr[0].length==0)return false;
        int m=arr.length,n=arr[0].length;
        int low=0,hi=m*n-1;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            int i=mid/n,j=mid%n;
            if(arr[i][j]==target)return true;
            else if(target>arr[i][j])low=mid+1;
            else hi=mid-1;
        }
        return false;
    }

    // ==========================================
    // TEST RUNNER ENGINE
    // ==========================================
    public static void main(String[] args) {
        SortedTwoDimensionalMatrix searcher = new SortedTwoDimensionalMatrix();
        int passedTests = 0;
        int totalTests = 11;

        System.out.println("Executing Search a 2D Matrix Test Cases...\n");

        // --- TEST 1: Standard Example 1 (Target Exists) ---
        try {
            int[][] matrix = {
                    {1, 3, 5, 7},
                    {10, 11, 16, 20},
                    {23, 30, 34, 60}
            };
            boolean result = searcher.searchMatrix(matrix, 3);
            printResult(1, "Standard Matrix (Target Exists)", result == true, "true", String.valueOf(result));
            if (result == true) passedTests++;
        } catch (Exception e) {
            printException(1, "Standard Matrix (Target Exists)", e);
        }

        // --- TEST 2: Standard Example 2 (Target Missing) ---
        try {
            int[][] matrix = {
                    {1, 3, 5, 7},
                    {10, 11, 16, 20},
                    {23, 30, 34, 60}
            };
            boolean result = searcher.searchMatrix(matrix, 13);
            printResult(2, "Standard Matrix (Target Missing)", result == false, "false", String.valueOf(result));
            if (result == false) passedTests++;
        } catch (Exception e) {
            printException(2, "Standard Matrix (Target Missing)", e);
        }

        // --- TEST 3: Edge Case - 1x1 Matrix (Target Exists) ---
        try {
            int[][] matrix = {{5}};
            boolean result = searcher.searchMatrix(matrix, 5);
            printResult(3, "1x1 Matrix (Target Exists)", result == true, "true", String.valueOf(result));
            if (result == true) passedTests++;
        } catch (Exception e) {
            printException(3, "1x1 Matrix (Target Exists)", e);
        }

        // --- TEST 4: Edge Case - 1x1 Matrix (Target Missing) ---
        try {
            int[][] matrix = {{5}};
            boolean result = searcher.searchMatrix(matrix, 2);
            printResult(4, "1x1 Matrix (Target Missing)", result == false, "false", String.valueOf(result));
            if (result == false) passedTests++;
        } catch (Exception e) {
            printException(4, "1x1 Matrix (Target Missing)", e);
        }

        // --- TEST 5: Edge Case - Single Row Matrix ---
        try {
            int[][] matrix = {{1, 3, 5, 8, 10}};
            boolean result = searcher.searchMatrix(matrix, 8);
            printResult(5, "Single Row Matrix (Target Exists)", result == true, "true", String.valueOf(result));
            if (result == true) passedTests++;
        } catch (Exception e) {
            printException(5, "Single Row Matrix (Target Exists)", e);
        }

        // --- TEST 6: Edge Case - Single Column Matrix ---
        try {
            int[][] matrix = {{1}, {3}, {5}, {8}, {10}};
            boolean result = searcher.searchMatrix(matrix, 8);
            printResult(6, "Single Column Matrix (Target Exists)", result == true, "true", String.valueOf(result));
            if (result == true) passedTests++;
        } catch (Exception e) {
            printException(6, "Single Column Matrix (Target Exists)", e);
        }

        // --- TEST 7: Boundary Check - Target Smaller than Minimum ---
        try {
            int[][] matrix = {
                    {10, 20},
                    {30, 40}
            };
            boolean result = searcher.searchMatrix(matrix, 5);
            printResult(7, "Target Smaller than Matrix Min", result == false, "false", String.valueOf(result));
            if (result == false) passedTests++;
        } catch (Exception e) {
            printException(7, "Target Smaller than Matrix Min", e);
        }

        // --- TEST 8: Boundary Check - Target Larger than Maximum ---
        try {
            int[][] matrix = {
                    {10, 20},
                    {30, 40}
            };
            boolean result = searcher.searchMatrix(matrix, 50);
            printResult(8, "Target Larger than Matrix Max", result == false, "false", String.valueOf(result));
            if (result == false) passedTests++;
        } catch (Exception e) {
            printException(8, "Target Larger than Matrix Max", e);
        }

        // --- TEST 9: Exact Edge Matching - First and Last Elements ---
        try {
            int[][] matrix = {
                    {1, 3, 5},
                    {7, 9, 11},
                    {13, 15, 17}
            };
            boolean rFirst = searcher.searchMatrix(matrix, 1);
            boolean rLast = searcher.searchMatrix(matrix, 17);
            boolean pass = (rFirst == true && rLast == true);
            printResult(9, "Exact Corner Matrix Element Matching", pass, "true", "First=" + rFirst + ", Last=" + rLast);
            if (pass) passedTests++;
        } catch (Exception e) {
            printException(9, "Exact Corner Matrix Element Matching", e);
        }

        // --- TEST 10: Target Sandwiched Between Row Boundaries ---
        try {
            int[][] matrix = {
                    {1, 3, 5},
                    {10, 12, 14},
                    {20, 22, 24}
            };
            // 7 is greater than 5 (prev row last) and smaller than 10 (curr row first)
            boolean result = searcher.searchMatrix(matrix, 7);
            printResult(10, "Target Row Gap Sandwich Gap Check", result == false, "false", String.valueOf(result));
            if (result == false) passedTests++;
        } catch (Exception e) {
            printException(10, "Target Row Gap Sandwich Gap Check", e);
        }

        // --- TEST 11: Negative Value Handling ---
        try {
            int[][] matrix = {
                    {-10, -8, -5},
                    {-3, -1, 2},
                    {5, 7, 9}
            };
            boolean rNeg = searcher.searchMatrix(matrix, -8);
            boolean rPos = searcher.searchMatrix(matrix, 2);
            boolean pass = (rNeg == true && rPos == true);
            printResult(11, "Negative and Mixed Value Bounds", pass, "true", "Neg=" + rNeg + ", Pos=" + rPos);
            if (pass) passedTests++;
        } catch (Exception e) {
            printException(11, "Negative and Mixed Value Bounds", e);
        }

        // --- FINAL SUMMARY ---
        System.out.println("----------------------------------------");
        System.out.printf("Execution Done: %d/%d Tests Passed.\n", passedTests, totalTests);
        if (passedTests == totalTests) {
            System.out.println("Perfect score! Your solution handles all multi-dimensional layout edge cases.");
        } else {
            System.out.println("Some test cases failed. Watch out for row-to-index conversion formula mapping.");
        }
    }

    private static void printResult(int testNo, String description, boolean pass, String expected, String actual) {
        String status = pass ? "PASSED" : "FAILED";
        System.out.printf("Test #%d [%s]: %s\n", testNo, description, status);
        if (!pass) {
            System.out.println("   -> Expected: [" + expected + "]");
            System.out.println("   -> Actual:   [" + actual + "]");
        }
        System.out.println();
    }

    private static void printException(int testNo, String description, Exception e) {
        System.out.printf("Test #%d [%s]: FAILED due to Exception\n", testNo, description);
        System.out.println("   -> Exception: " + e.getClass().getName() + " : " + e.getMessage());
        System.out.println();
    }
}