package maths;

import java.util.*;

public class DistinctPrimeFactors {

    /**
     * Instructions:
     * Implement the method below to return the number of distinct prime factors
     * in the product of the elements of the nums array.
     * * Constraints: 2 <= nums[i] <= 1000
     */
    public int countDistinctPrimeFactors(int[] arr) {
        long product=1;
        for(int a:arr)product*=a;
        return countPrime(product);
    }

    private int countPrime(long n){
        int count=0;
        for(int i=2;i*i<=n;i++){
            if(n%i==0){
                count++;
                while(n%i==0)n/=i;
            }
        }
        if(n!=1)count++;
        return count;
    }

    public static void main(String[] args) {
        DistinctPrimeFactors solver = new DistinctPrimeFactors();

        // Defining Test Cases
        Object[][] testCases = {
                {new int[]{2, 4, 3, 7, 10, 6}, 4},    // Example 1
                {new int[]{2, 4, 8, 16}, 1},          // Example 2 (Only factor is 2)
                {new int[]{30}, 3},                    // Single number with 3 factors (2,3,5)
                {new int[]{97, 97, 97}, 1},            // Multiple large primes (only 1 distinct)
                {new int[]{2, 3, 5, 7, 11, 13}, 6},    // All distinct primes
                {new int[]{1000, 1000}, 2},            // Largest constraint value (factors: 2, 5)
                {new int[]{77, 91, 143}, 4},           // Composite products (7*11, 7*13, 11*13 -> 7,11,13) - Wait, 77(7,11), 91(7,13), 143(11,13) -> {7,11,13} is 3.
                {new int[]{2, 1024, 512}, 1},          // Large powers of 2
                {new int[]{210, 2}, 4},                // 210 has 2, 3, 5, 7
                {new int[]{997, 2}, 2}                 // Largest prime < 1000 and smallest prime
        };

        // Note: Correcting Case 7: 77=7*11, 91=7*13, 143=11*13. Distinct: {7, 11, 13} -> Count 3.
        testCases[6] = new Object[]{new int[]{77, 91, 143}, 3};

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int[] input = (int[]) testCases[i][0];
            int expected = (int) testCases[i][1];
            int actual = solver.countDistinctPrimeFactors(input);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(input));
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);

            if (actual == expected) {
                System.out.println("Result: PASS");
                passed++;
            } else {
                System.out.println("Result: FAIL");
            }
            System.out.println("---------------------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + testCases.length + " Passed");
    }
}