package arrays;

public class RangeMinimumQuery {

	
	private int[][]pre;
	
    // Constructor for any pre-computation (e.g., Sparse Table, Segment Tree)
    public RangeMinimumQuery(int[] arr) {
        int n=arr.length,k=(int)(Math.log(n)/Math.log(2));
        pre=new int[k+1][n];
        for(int i=0;i<n;i++)pre[0][i]=arr[i];
        
        for(int j=1;j<=k;j++) {
        	for(int i=0;i+(1<<j)<=n;i++) pre[j][i]=Math.min(pre[j-1][i], pre[j-1][i+(1<<(j-1))]);
        }
        
    }

    // Method to return the minimum value in range [i, j] (inclusive)
    public int query(int i, int j) {
        int len=j-i+1,k=(int)(Math.log(len)/Math.log(2));
        
        return Math.min(pre[k][i], pre[k][j-(1<<k)+1]);
    }

    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        // Base array for testing
        int[] arr = {7, 2, 3, 0, 5, 10, -3, 2, 8, 0, -3, 15};
        RangeMinimumQuery rmq = new RangeMinimumQuery(arr);

        // Test Cases Setup: {i, j, expectedResult}
        int[][] testCases = {
            // Standard ranges
            {0, 2, 2},    // [7, 2, 3] -> min is 2
            {1, 4, 0},    // [2, 3, 0, 5] -> min is 0
            {4, 7, -3},   // [5, 10, -3, 2] -> min is -3

            // Edge Case 1: Single element range (i == j)
            {0, 0, 7},    // [7] -> min is 7
            {6, 6, -3},   // [-3] -> min is -3

            // Edge Case 2: Full range covering entire array
            {0, 11, -3},  // Entire array -> min is -3

            // Edge Case 3: Duplicate minimums in range
            {6, 10, -3},  // [-3, 2, 8, 0, -3] -> min is -3

            // Edge Case 4: Queries at boundaries (start and end of array)
            {0, 1, 2},    // Start boundary [7, 2] -> min is 2
            {10, 11, -3}, // End boundary [-3, 15] -> min is -3

            // Edge Case 5: Out of order range parameters (i > j)
            {5, 2, 0}     // [10, 5, 0, 3] -> handles range normalized as [2, 5]
        };

        System.out.println("--- Running Test Cases ---\n");

        for (int t = 0; t < testCases.length; t++) {
            int i = testCases[t][0];
            int j = testCases[t][1];
            int expected = testCases[t][2];

            // Normalize range if i > j for robust testing
            int left = Math.min(i, j);
            int right = Math.max(i, j);

            int actual = rmq.query(left, right);

            if (actual == expected) {
                System.out.printf("Test %2d PASSED: Query(%d, %d) => %d%n", t + 1, left, right, actual);
                passed++;
            } else {
                System.out.printf("Test %2d FAILED: Query(%d, %d) => Expected: %d, Got: %d%n", t + 1, left, right, expected, actual);
                failed++;
            }
        }

        System.out.println("\n--------------------------");
        System.out.printf("Total Passed: %d/%d%n", passed, testCases.length);
        System.out.printf("Total Failed: %d/%d%n", failed, testCases.length);
        System.out.println("--------------------------");
    }
}