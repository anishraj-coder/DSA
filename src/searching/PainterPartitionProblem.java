package searching;

import java.util.Arrays;

public class PainterPartitionProblem {

    /**
     * Finds the minimum time required to paint all boards.
     * * @param arr Array representing the length of each board.
     * @param k   Number of painters available.
     * @return    The minimized maximum time taken by any painter.
     */
    public long minTime(int[] arr, int k) {
        long low=Long.MIN_VALUE,hi=0;
        for(int a:arr){
            hi+=a;
            low=Math.max(low,a);
        }

        while(low<=hi){
            long mid=(hi-low)/2+low;
            long painters=calculatePainters(arr,mid);
            if(painters>k){
                low=mid+1;
            }else hi=mid-1;
        }
        return low;
    }
    private long calculatePainters(int[]arr,long limit){
        long sum=0,painters=1;
        for(int a:arr){
            if(a+sum>limit){
                painters++;
                sum=a;
            }else sum+=a;
        }
        return painters;
    }

    public static void main(String[] args) {
        PainterPartitionProblem solver = new PainterPartitionProblem();

        // Test Case Definitions: {arr, k, expected}
        // Note: Using long for expected because sum can exceed Integer.MAX_VALUE
        Object[][] testCases = {
                {new int[]{5, 10, 30, 20, 15}, 3, 35L},      // Standard case
                {new int[]{10, 20, 30, 40}, 2, 60L},        // Sequential split
                {new int[]{100, 200, 300, 400}, 1, 1000L},  // Single painter
                {new int[]{10, 10, 10, 10}, 4, 10L},        // Painters = Boards
                {new int[]{48, 12, 60, 24}, 5, 60L},        // More painters than boards
                {new int[]{1, 1, 1, 1, 1, 1, 1, 1}, 2, 4L}, // Equal distribution
                {new int[]{10000, 10000, 10000}, 2, 20000L},// Large board lengths
                {new int[]{5, 5, 5, 5}, 3, 10L},            // K not a divisor of length
                {new int[]{10, 20, 30, 40, 50}, 2, 90L},    // Pivot near the end
                {new int[]{10, 5, 2, 7, 8}, 4, 10L}         // Small boards, many painters
        };

        int passed = 0;

        System.out.println("Running DSA Practice: Painter's Partition Problem-II\n");
        System.out.printf("%-5s | %-25s | %-5s | %-10s | %-10s | %-10s%n",
                "No.", "Board Lengths", "k", "Expected", "Actual", "Result");
        System.out.println("---------------------------------------------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            int[] arr = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            long expected = (long) testCases[i][2];

            long actual = solver.minTime(arr, k);
            boolean isCorrect = (actual == expected);
            if (isCorrect) passed++;

            String arrayStr = Arrays.toString(arr);
            if (arrayStr.length() > 22) arrayStr = arrayStr.substring(0, 19) + "...";

            System.out.printf("%-5d | %-25s | %-5d | %-10d | %-10d | %-10s%n",
                    (i + 1), arrayStr, k, expected, actual, (isCorrect ? "PASSED" : "FAILED"));
        }

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}