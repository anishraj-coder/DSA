package searching;

public class RowWithMaxOnes {

    /**
     * Problem: Find the 0-based index of the first row with the maximum number of 1's.
     * Each row is sorted in non-decreasing order.
     * Return -1 if no '1' is present in the entire matrix.
     */
    public int findMaxOnesRow(int[][] arr, int n, int m) {
        int max=0,maxIdx=-1;
        for(int i=0;i<n;i++){
            int low=lowBound(arr[i],1);
            if(arr[i][low]!=1)continue;
            int count=m-low;
            if(count>max){
                maxIdx=i;
                max=count;
            }
        }
        return maxIdx; // Placeholder
    }

    public int lowBound(int[]arr,int target){
        int n=arr.length;
        int low=0,hi=n-1;
        while(low<hi){
            int mid=(hi-low)/2+low;
            if(arr[mid]>=target)hi=mid;
            else low=mid+1;
        }
        return low;
    }

    public static void main(String[] args) {
        RowWithMaxOnes solver = new RowWithMaxOnes();
        int testsPassed = 0;
        int totalTests = 10;

        // Test Case 1: Standard case - Row 0 has most 1s
        int[][] m1 = {{1, 1, 1}, {0, 0, 1}, {0, 0, 0}};
        testsPassed += runTest(1, solver.findMaxOnesRow(m1, 3, 3), 0);

        // Test Case 2: Tie-break - Row 0 and Row 1 both have two 1s
        int[][] m2 = {{1, 1}, {1, 1}};
        testsPassed += runTest(2, solver.findMaxOnesRow(m2, 2, 2), 0);

        // Test Case 3: No 1s present at all
        int[][] m3 = {{0}, {0}};
        testsPassed += runTest(3, solver.findMaxOnesRow(m3, 2, 1), -1);

        // Test Case 4: Only the last row has 1s
        int[][] m4 = {{0, 0}, {0, 0}, {0, 1}};
        testsPassed += runTest(4, solver.findMaxOnesRow(m4, 3, 2), 2);

        // Test Case 5: Single element matrix (is 1)
        int[][] m5 = {{1}};
        testsPassed += runTest(5, solver.findMaxOnesRow(m5, 1, 1), 0);

        // Test Case 6: Single element matrix (is 0)
        int[][] m6 = {{0}};
        testsPassed += runTest(6, solver.findMaxOnesRow(m6, 1, 1), -1);

        // Test Case 7: All elements are 1
        int[][] m7 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        testsPassed += runTest(7, solver.findMaxOnesRow(m7, 3, 3), 0);

        // Test Case 8: Only one 1 in the whole matrix (middle)
        int[][] m8 = {{0, 0, 0}, {0, 1, 1}, {0, 0, 0}};
        testsPassed += runTest(8, solver.findMaxOnesRow(m8, 3, 3), 1);

        // Test Case 9: Jagged logic (Sorted rows, varying starts)
        int[][] m9 = {
                {0, 0, 1, 1},
                {0, 1, 1, 1},
                {0, 0, 0, 1}
        };
        testsPassed += runTest(9, solver.findMaxOnesRow(m9, 3, 4), 1);

        // Test Case 10: Large empty rows before a row with a single 1
        int[][] m10 = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 1}
        };
        testsPassed += runTest(10, solver.findMaxOnesRow(m10, 3, 4), 2);

        System.out.println("\n---------------------------------------");
        System.out.println("Final Result: " + testsPassed + "/" + totalTests + " Tests Passed");
        System.out.println("---------------------------------------");
    }

    private static int runTest(int id, int actual, int expected) {
        System.out.print("Test Case " + id + ": ");
        if (actual == expected) {
            System.out.println("PASSED (Expected: " + expected + ", Actual: " + actual + ")");
            return 1;
        } else {
            System.out.println("FAILED (Expected: " + expected + ", Actual: " + actual + ")");
            return 0;
        }
    }
}