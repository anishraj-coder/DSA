package searching;

import java.util.Objects;

/**
 * Class name derived from the problem: Finding the Nth Root.
 * Implementation left to the user.
 */
public class NthRoot {

    /**
     * Finds the Nth root of M.
     * Returns the integer root if it exists, otherwise returns -1.
     * @param p The root degree.
     * @param n The number to find the root of.
     * @return The integer Nth root or -1.
     */
    public int findNthRoot(int p, int n) {
        int low=1,hi=n;
        while(low<=hi){
            int mid=(hi-low)/2+low;
            long res=power(mid,p);
            if(res==n)return mid;
            else if(res<n)low=mid+1;
            else hi=mid-1;
        }
        return -1;
    }

    private long power(int num,int p){
        int i=p;
        long res=1,n=num;
        while(i>0){
            if((i&1)==1){
                res*=n;
            }
            n*=n;
            i/=2;
        }
        return res;
    }

    public static void main(String[] args) {
        NthRoot engine = new NthRoot();

        // Test Data: {N, M, Expected Output}
        long[][] testCases = {
                {3, 27, 3},          // Perfect cube
                {4, 69, -1},         // Non-perfect root
                {1, 100, 100},       // 1st root of M is M
                {10, 1, 1},          // Any root of 1 is 1
                {2, 1000000, 1000},  // Large perfect square
                {5, 243, 3},         // 3^5 = 243
                {9, 512, 2},         // 2^9 = 512
                {2, 2, -1},          // Small non-perfect square
                {30, 1073741824, 2}, // 2^30 = 1073741824 (Boundary of Integer)
                {3, 1000000000, 1000}, // 1000^3
                {20, 1000000, -1}    // Power exceeds M quickly
        };

        int passed = 0;

        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-5s | %-12s | %-10s | %-10s | %-10s%n", "N", "M", "Expected", "Actual", "Result");
        System.out.println("------------------------------------------------------------------");

        for (long[] test : testCases) {
            int n = (int) test[0];
            int m = (int) test[1];
            int expected = (int) test[2];
            int actual = engine.findNthRoot(n, m);

            boolean isMatch = (actual == expected);
            if (isMatch) passed++;

            System.out.printf("%-5d | %-12d | %-10d | %-10d | %-10s%n",
                    n, m, expected, actual, isMatch ? "PASS" : "FAIL");
        }

        System.out.println("------------------------------------------------------------------");
        System.out.printf("Final Score: %d/%d cases passed.%n", passed, testCases.length);
        System.out.println("------------------------------------------------------------------");
    }
}