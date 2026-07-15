package searching;

public class KthMissingPositive {

    /**
     * Problem: Kth Missing Positive Number
     * Find the kth positive integer that is missing from the strictly increasing array.
     * @param arr - Strictly increasing positive integers
     * @param k   - The index of the missing number to find
     * @return    - The kth missing positive integer
     */
    public int findKthPositive(int[] arr, int k) {
        int n=arr.length,low=0,hi=n-1;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            int missing=arr[mid]-mid -1;
            if(missing<=k)low=mid+1;
            else hi=mid-1;
        }

        return low+k;
    }

    public static void main(String[] args) {
        KthMissingPositive solver = new KthMissingPositive();

        Object[][] testCases = {
                // {arr, k, expected_output, description}
                {new int[]{2, 3, 4, 7, 11}, 5, 9, "Standard case from example 1"},
                {new int[]{1, 2, 3, 4}, 2, 6, "Missing numbers are beyond the array end"},
                {new int[]{5, 6, 7, 8, 9}, 2, 2, "Missing numbers are at the very beginning"},
                {new int[]{1, 10, 20, 30}, 5, 6, "Large gaps between elements"},
                {new int[]{2}, 1, 1, "Single element array, missing is before it"},
                {new int[]{1}, 1, 2, "Single element array, missing is after it"},
                {new int[]{1, 2, 3, 4, 5, 6, 10, 11}, 2, 8, "Consecutive start with a gap later"},
                {new int[]{7, 8, 9, 10}, 10, 14, "k is larger than the gap at the start"},
                {new int[]{2, 3, 5, 9, 10, 11, 12}, 2, 4, "Gap of size 1 and size 3 mixed"},
                {new int[]{4, 7, 9}, 3, 3, "k equals exactly a missing value before index 0"},
                {new int[]{1, 2, 1000}, 997, 999, "Maximum constraint gap near 1000"}
        };

        int passed = 0;
        System.out.println("Running Tests for KthMissingPositive...\n");
        System.out.printf("%-5s | %-15s | %-10s | %-10s | %-10s%n", "No.", "Status", "Expected", "Actual", "Description");
        System.out.println("---------------------------------------------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            int[] arr = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int expected = (int) testCases[i][2];
            String desc = (String) testCases[i][3];

            int actual = solver.findKthPositive(arr, k);
            boolean isCorrect = (actual == expected);

            if (isCorrect) passed++;

            System.out.printf("%-5d | %-15s | %-10d | %-10d | %s%n",
                    (i + 1),
                    isCorrect ? "✅ PASSED" : "❌ FAILED",
                    expected,
                    actual,
                    desc);
        }

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("Final Result: %d/%d Tests Passed%n", passed, testCases.length);
    }
}