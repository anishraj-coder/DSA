package twoPointer;

public class MaximumPointsFromCards {

    /**
     * Computes the maximum score obtainable by taking exactly k cards
     * from either the beginning or the end of the cardPoints array.
     * * @param cardPoints Array of integers representing points of cards.
     * @param k The number of cards to take.
     * @return The maximum sum of points.
     */
    public int maxScore(int[] arr, int k) {
        int n=arr.length;
        int lsum=0,rsum=0;
        if(k>=n){
            for(int a:arr)lsum+=a;
            return lsum;
        }
        for(int i=0;i<k;i++){
            lsum+=arr[i];
        }
        int i=k-1,j=n-1,max=lsum;
        for(int x=1;x<=k;x++){
            lsum-=arr[i];
            rsum+=arr[j];
            max=Math.max(lsum+rsum,max);
            j--;i--;
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumPointsFromCards solver = new MaximumPointsFromCards();

        Object[][] testCases = {
                // {cardPoints, k, expectedOutput}
                {new int[]{1, 2, 3, 4, 5, 6, 1}, 3, 12},           // Example 1: Taking from the end
                {new int[]{2, 2, 2}, 2, 4},                       // Example 2: Uniform values
                {new int[]{9, 7, 7, 9, 7, 7, 9}, 7, 55},           // Example 3: k equals array length
                {new int[]{1, 1000, 1, 1, 1}, 3, 1002},           // Hard: Greedily picking end fails (1+1+1 < 1+1000+1)
                {new int[]{1, 79, 80, 1, 1, 1, 200, 1}, 3, 202},  // Edge: High value buried deep at one end
                {new int[]{100, 40, 17, 9, 73, 58}, 3, 231},      // Mixed: Best is 100 + 58 + 73
                {new int[]{1, 2, 3, 4, 5, 6, 1}, 1, 1},           // k = 1: Pick max of ends
                {new int[]{5, 5, 5, 5, 5}, 1, 5},                 // k = 1: Identical ends
                {new int[]{11, 49, 100, 20, 86, 29, 72}, 4, 232}, // Hard: Strategy check (11+49 + 72+100)
                {new int[]{1, 2, 3, 4, 5, 6, 1}, 0, 0},           // Edge: k = 0 (Minimal constraint check)
                {new int[]{10, 1, 1, 1, 10}, 2, 20}               // Symmetry: Taking one from each end
        };

        int passed = 0;
        int failed = 0;

        System.out.println("Running Test Cases...\n" + "=".repeat(60));

        for (int i = 0; i < testCases.length; i++) {
            int[] cardPoints = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = solver.maxScore(cardPoints, k);
            boolean isMatch = (actual == expected);

            if (isMatch) {
                passed++;
            } else {
                failed++;
            }

            System.out.printf("Test Case %d:\n", i + 1);
            System.out.printf("  Input: cardPoints = %s, k = %d\n", arrayToString(cardPoints), k);
            System.out.printf("  Expected Output: %d\n", expected);
            System.out.printf("  Actual Output:   %d\n", actual);
            System.out.printf("  Result: %s\n", isMatch ? "PASSED ✅" : "FAILED ❌");
            System.out.println("-".repeat(60));
        }

        System.out.printf("\nFinal Result: %d Passed, %d Failed\n", passed, failed);
    }

    private static String arrayToString(int[] arr) {
        if (arr.length > 10) return "[...] (Length: " + arr.length + ")";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]).append(i == arr.length - 1 ? "" : ", ");
        }
        sb.append("]");
        return sb.toString();
    }
}