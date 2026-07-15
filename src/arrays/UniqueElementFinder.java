package arrays;

public class UniqueElementFinder {

    /**
     * Finds the single element in an array where every other element appears twice.
     * Constraint: Linear time complexity and constant extra space.
     * * @param arr Array of integers
     * @return The element that appears only once
     */
    public int findSingleNumber(int[] arr) {

        int n=arr.length;
        if(n==1)return arr[0];
        int x=0;
        for(int a:arr){
            x^=a;
        }

        return x;
    }

    public static void main(String[] args) {
        UniqueElementFinder finder = new UniqueElementFinder();

        // Test Case Definitions: {Input Array, Expected Output}
        Object[][] testCases = {
                {new int[]{2, 2, 1}, 1},                                      // Example 1
                {new int[]{4, 1, 2, 1, 2}, 4},                                // Example 2
                {new int[]{1}, 1},                                            // Single element (Edge Case)
                {new int[]{-1, -1, -2}, -2},                                  // Negative numbers
                {new int[]{0, 1, 0}, 1},                                      // Presence of zero
                {new int[]{7, 3, 5, 3, 5, 11, 11}, 7},                        // Multiple pairs, single at start
                {new int[]{10, 20, 10, 20, 30}, 30},                          // Single at end
                {new int[]{-30000, 30000, -30000}, 30000},                    // Constraint boundaries
                {new int[]{1, 5, 9, 1, 5, 9, 42}, 42},                        // Larger set of pairs
                {new int[]{Integer.MAX_VALUE, 0, Integer.MAX_VALUE}, 0},      // Max integer value
                {new int[]{100, 200, 300, 400, 500, 400, 300, 200, 100}, 500} // Peak element in sequence
        };

        int passed = 0;
        int failed = 0;

        System.out.println("Running Test Cases for: UniqueElementFinder\n");
        System.out.printf("%-5s | %-35s | %-10s | %-10s | %-8s%n", "No.", "Input", "Expected", "Actual", "Result");
        System.out.println("---------------------------------------------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int expected = (int) testCases[i][1];
            int actual = finder.findSingleNumber(input);

            String status = (actual == expected) ? "PASSED" : "FAILED";
            if (actual == expected) passed++; else failed++;

            // Format input array for printing
            String inputStr = java.util.Arrays.toString(input);
            if (inputStr.length() > 33) inputStr = inputStr.substring(0, 30) + "...";

            System.out.printf("%-5d | %-35s | %-10d | %-10d | %-8s%n", (i + 1), inputStr, expected, actual, status);
        }

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("FINAL RESULTS:");
        System.out.println("Total Passed: " + passed);
        System.out.println("Total Failed: " + failed);
    }
}
