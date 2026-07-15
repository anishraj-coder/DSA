package arrays;

import java.util.Arrays;

public class MissingRepeating {

    /**
     * Problem: Find the repeating number (A) and the missing number (B).
     * Constraint: Do NOT modify the original array.
     * Time Complexity Goal: O(N)
     * Space Complexity Goal: O(1) (excluding result array)
     * * @return int[] {repeating, missing}
     */
    public int[] findNumbers(int[] arr) {
        int n=arr.length;

        long sn=((long)n*(n+1))/2;
        long s2n=((long)n*(n+1)*(2L *n+1))/6;
        long s1=0L,s2=0L;
        for(int a:arr){
            s1+=a;
            s2+=(long)a*a;
        }
        long val1=s1-sn;
        long val2=s2-s2n;
        val2=val2/val1;
        long x=(val1+val2)/2L;
        long y=x-val1;

        return new int[]{(int)x,(int) y};
    }

    public int[] findNumbersXor(int[]arr){
        int n=arr.length;
        int xr=0;
        for(int i=0;i<n;i++){
            xr^=arr[i];
            xr^=(i+1);
        }
        int bitNumber = (xr & ~(xr - 1));;

        int one=0,zero=0;
        for(int i=0;i<n;i++){
            if((arr[i]&bitNumber)!=0){
                one^=arr[i];
            }else zero^=arr[i];
        }
        for(int i=1;i<=n;i++){
            if((i& bitNumber)!=0)one^=i;
            else zero^=i;
        }
        int count=0;
        for(int a:arr){
            if(a==zero)count++;
        }
        if(count!=0)return new int[]{zero,one};

        return new int[]{one,zero};
    }

    public static void main(String[] args) {
        MissingRepeating solver = new MissingRepeating();

        // Test Cases: {Input Array, Expected Output {Repeating, Missing}}
        Object[][] testCases = {
                { // 1. Standard case
                        new int[]{3, 5, 4, 1, 1},
                        new int[]{1, 2}
                },
                { // 2. Missing is the first number
                        new int[]{2, 3, 4, 2},
                        new int[]{2, 1}
                },
                { // 3. Missing is the last number
                        new int[]{1, 2, 3, 3},
                        new int[]{3, 4}
                },
                { // 4. Large gap between repeating and missing
                        new int[]{1, 2, 3, 6, 7, 5, 7},
                        new int[]{7, 4}
                },
                { // 5. Smallest possible array
                        new int[]{1, 1},
                        new int[]{1, 2}
                },
                { // 6. Smallest possible array (reverse)
                        new int[]{2, 2},
                        new int[]{2, 1}
                },
                { // 7. Numbers are unsorted
                        new int[]{4, 3, 6, 2, 1, 1},
                        new int[]{1, 5}
                },
                { // 8. Repeating is larger than missing
                        new int[]{1, 3, 3},
                        new int[]{3, 2}
                },
                { // 9. All numbers but one are correct (large N)
                        new int[]{1, 2, 3, 4, 5, 5},
                        new int[]{5, 6}
                },
                { // 10. Large values (testing for potential overflow)
                        new int[]{5, 4, 3, 2, 2},
                        new int[]{2, 1}
                }
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int[] expected = (int[]) testCases[i][1];

            // Clone input to ensure the original isn't modified by user code accidentally
            int[] inputCopy = input.clone();
            int[] actual = solver.findNumbersXor(inputCopy);

            boolean isMatch = Arrays.equals(actual, expected);
            boolean modified = !Arrays.equals(input, inputCopy);

            if (isMatch && !modified) {
                passed++;
                System.out.println("Test Case " + (i + 1) + ": PASSED");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAILED");
                if (modified) System.out.println("   CRITICAL: Original array was modified!");
            }

            System.out.println("   Input:    " + Arrays.toString(input));
            System.out.println("   Expected: " + Arrays.toString(expected));
            System.out.println("   Actual:   " + Arrays.toString(actual));
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + testCases.length + " cases passed.");
    }
}