package searching;

import java.util.Arrays;

public class AggressiveCows {

    /**
     * Problem: Aggressive Cows
     * Assign k cows to stalls such that the minimum distance between any two cows is maximized.
     * @param arr - Positions of the stalls
     * @param k    - Number of aggressive cows
     * @return     - The maximum possible minimum distance
     */
    public int solve(int[] arr, int k) {
        int n=arr.length;
        Arrays.sort(arr);
        int low=1,hi=arr[n-1]-arr[0];
        while(low<=hi){
            int mid=(hi-low)/2+low;
            int cows=canPlace(arr,mid);
            if(cows>=k){
                low=mid+1;
            }else hi=mid-1;
        }
        return hi;
    }

    private int canPlace(int[]arr,int dist){
        int cows=1,last=arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i]-last>=dist) {
                cows++;
                last=arr[i];
            }
        }
        return cows;
    }

    public static void main(String[] args) {
        AggressiveCows solver = new AggressiveCows();

        Object[][] testCases = {
                // {nums, k, expected_output, description}
                {new int[]{0, 3, 4, 7, 10, 9}, 4, 3, "Standard case: intermediate distance"},
                {new int[]{4, 2, 1, 3, 6}, 2, 5, "Two cows: max distance is between ends"},
                {new int[]{1, 2, 8, 4, 9}, 3, 3, "Classic case with sorted gaps"},
                {new int[]{1, 10, 100, 1000}, 2, 999, "Large distance with few cows"},
                {new int[]{5, 5, 5, 5}, 2, 0, "All stalls at the same position"},
                {new int[]{1, 2, 3, 4, 5}, 5, 1, "Cows equal to number of stalls"},
                {new int[]{10, 1, 2, 7, 5}, 3, 4, "Unsorted input with specific gap"},
                {new int[]{1, 1000000000}, 2, 999999999, "Large coordinate constraints"},
                {new int[]{2, 12, 11, 3, 26, 7}, 5, 1, "Tight fit for many cows"},
                {new int[]{1, 5, 10, 15, 20, 25}, 2, 24, "Max spread on large array"},
                {new int[]{6, 1, 9, 11, 15}, 4, 2, "Check for optimal placement with 4 cows"}
        };

        int passed = 0;
        System.out.println("Running Tests for AggressiveCows...\n");
        System.out.printf("%-5s | %-15s | %-10s | %-10s | %-30s%n", "No.", "Status", "Expected", "Actual", "Description");
        System.out.println("-------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int expected = (int) testCases[i][2];
            String desc = (String) testCases[i][3];

            // Note: We clone to ensure the original test case isn't permanently mutated if your code sorts it.
            int actual = solver.solve(nums.clone(), k);
            boolean isCorrect = (actual == expected);

            if (isCorrect) passed++;

            System.out.printf("%-5d | %-15s | %-10d | %-10d | %s%n",
                    (i + 1),
                    isCorrect ? "✅ PASSED" : "❌ FAILED",
                    expected,
                    actual,
                    desc);
        }

        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.printf("Final Result: %d/%d Tests Passed%n", passed, testCases.length);
    }
}