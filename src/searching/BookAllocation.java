package searching;
import java.util.Arrays;

public class BookAllocation {

    /**
     * Finds the minimum possible value of the maximum number of pages assigned to a student.
     * * @param arr Array representing the number of pages in each book.
     * @param k   Number of students.
     * @return    The minimized maximum pages, or -1 if allocation is impossible.
     */
    public int findPages(int[] arr, int k) {
        int n=arr.length;
        if(k>n)return -1;
        int sum=0,max=Integer.MIN_VALUE;
        for(int a:arr){
            sum+=a;
            max=Math.max(a,max);
        }
        int low=max,hi=sum;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            int curr=calculateBooks(arr,mid);
            if(curr<=k){
                hi=mid-1;
            }else low=mid+1;
        }
        return low;
    }

    private int calculateBooks(int[]arr,int limit){
        int students=1;
        long sum=0L;
        for(int a:arr){
            if(sum+a>limit){
                sum=a;
                students++;
            }else sum+=a;
        }
        return students;
    }

    public static void main(String[] args) {
        BookAllocation solver = new BookAllocation();

        // Test Case Definitions: {Array, k, Expected Output}
        Object[][] testCases = {
                {new int[]{12, 34, 67, 90}, 2, 113},        // Standard case
                {new int[]{15, 17, 20}, 5, -1},             // More students than books (Edge)
                {new int[]{22, 23, 67}, 1, 112},            // Single student (Sum of all)
                {new int[]{10, 10, 10, 10}, 2, 20},         // Uniform distribution
                {new int[]{1, 1, 1, 1, 1, 1}, 6, 1},        // Each student gets one book (Minimum possible)
                {new int[]{100}, 1, 100},                   // Single book, single student
                {new int[]{75, 200, 300, 10, 500}, 3, 585}, // Large variance in pages
                {new int[]{10, 20, 30, 40}, 2, 60},         // Tightest split
                {new int[]{10, 10, 10, 10, 10}, 3, 20},     // Uneven split with uniform pages
                {new int[]{1, 2, 3, 4, 100}, 2, 100}        // Max element is the bottleneck
        };

        int passed = 0;

        System.out.println("Running DSA Practice: Allocate Minimum Pages\n");
        System.out.printf("%-5s | %-30s | %-5s | %-10s | %-10s | %-10s%n",
                "No.", "Array Input", "k", "Expected", "Actual", "Result");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            int[] arr = (int[]) testCases[i][0];
            int k = (int) testCases[i][1];
            int expected = (int) testCases[i][2];

            int actual = solver.findPages(arr, k);
            boolean isCorrect = (actual == expected);
            if (isCorrect) passed++;

            String arrayStr = Arrays.toString(arr);
            if (arrayStr.length() > 27) arrayStr = arrayStr.substring(0, 24) + "...";

            System.out.printf("%-5d | %-30s | %-5d | %-10d | %-10d | %-10s%n",
                    (i + 1), arrayStr, k, expected, actual, (isCorrect ? "PASSED" : "FAILED"));
        }

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}