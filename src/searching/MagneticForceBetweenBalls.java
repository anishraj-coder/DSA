package searching;

import java.util.Arrays;

public class MagneticForceBetweenBalls {

    /**
     * Problem: Magnetic Force Between Two Balls
     * Distribute m balls into baskets such that the minimum magnetic force
     * between any two balls is maximized.
     * * @param position - Array of basket positions
     * @param m        - Number of balls to place
     * @return         - The maximum possible minimum magnetic force
     */
    public int maxDistance(int[] arr, int m) {
        int n=arr.length;
        Arrays.sort(arr);
        int low=1,hi=arr[n-1]-arr[0];
        while(low<=hi){
            int mid=(hi-low)/2+low;
            int magnets=canPlace(arr,mid);
            if(magnets>=m){
                low=mid+1;
            }else hi=mid-1;
        }
        return hi;
    }

    public int canPlace(int[]arr,int dist){
        int magnet=1,last=arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i]-last>=dist){
                magnet++;
                last=arr[i];
            }
        }
        return magnet;
    }

    public static void main(String[] args) {
        MagneticForceBetweenBalls solver = new MagneticForceBetweenBalls();

        Object[][] testCases = {
                // {position, m, expected_output, description}
                {new int[]{1, 2, 3, 4, 7}, 3, 3, "Standard case from Example 1"},
                {new int[]{5, 4, 3, 2, 1, 1000000000}, 2, 999999999, "Two balls at extreme ends"},
                {new int[]{10, 20, 30, 40}, 4, 10, "Balls placed in every available basket"},
                {new int[]{1, 100, 101, 102, 103}, 2, 102, "Skip internal clusters for max distance"},
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 4, 3, "Uniformly distributed baskets"},
                {new int[]{79, 74, 57, 22}, 4, 5, "Unsorted small input with 4 balls"},
                {new int[]{1, 10, 11, 12, 100}, 3, 11, "k=3 with large gaps and small clusters"},
                {new int[]{22, 57, 74, 79, 100, 111}, 3, 44, "Checking mid-range distribution"},
                {new int[]{1, 5, 6, 7, 12}, 2, 11, "Only two balls, ignore central clusters"},
                {new int[]{1, 4, 8, 13, 20}, 3, 7, "Greedy placement check with k=3"},
                {new int[]{1, 2, 3, 100, 101, 102}, 3, 99, "Bridge the large gap in the middle"}
        };

        int passed = 0;
        System.out.println("Running Tests for MagneticForceBetweenBalls...\n");
        System.out.printf("%-5s | %-15s | %-12s | %-12s | %-30s%n", "No.", "Status", "Expected", "Actual", "Description");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            int[] position = (int[]) testCases[i][0];
            int m = (int) testCases[i][1];
            int expected = (int) testCases[i][2];
            String desc = (String) testCases[i][3];

            // Clone to prevent side effects from sorting in the user's implementation
            int actual = solver.maxDistance(position.clone(), m);
            boolean isCorrect = (actual == expected);

            if (isCorrect) passed++;

            System.out.printf("%-5d | %-15s | %-12d | %-12d | %s%n",
                    (i + 1),
                    isCorrect ? "✅ PASSED" : "❌ FAILED",
                    expected,
                    actual,
                    desc);
        }

        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("Final Result: %d/%d Tests Passed%n", passed, testCases.length);
    }
}