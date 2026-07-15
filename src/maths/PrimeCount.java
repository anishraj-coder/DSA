package maths;

import java.util.*;

public class PrimeCount {

    /**
     * Instructions:
     * Return the number of prime numbers strictly less than n.
     * Constraint: 0 <= n <= 5 * 10^6
     * Hint: Use the Sieve of Eratosthenes for O(n log log n) complexity.
     */
    public int countPrimes(int n) {
        boolean[]arr=new boolean[n+1];
        for(int i=2;i*i<=n;i++){
            if(!arr[i]){
                for(int j=i*i;j<=n;j+=i){
                    arr[j]=true;
                }
            }
        }

        int count=0;
        for(int i=2;i<n;i++){
            if(!arr[i])count++;
        }

        return count;
    }

    public static void main(String[] args) {
        PrimeCount solver = new PrimeCount();

        // Defining Test Cases
        int[] inputs = {
                10,         // Example 1: Primes (2, 3, 5, 7)
                0,          // Example 2: Boundary low
                1,          // Example 3: Boundary low
                2,          // Edge: Strictly less than 2 (None)
                3,          // Edge: Strictly less than 3 (Only 2)
                5,          // Edge: Strictly less than 5 (2, 3)
                20,         // Medium range (2, 3, 5, 7, 11, 13, 17, 19)
                100,        // Standard range (25 primes)
                5000,       // Larger range (669 primes)
                5000000     // Max constraint (348,513 primes)
        };

        int[] expected = {
                4,
                0,
                0,
                0,
                1,
                2,
                8,
                25,
                669,
                348513
        };

        int passed = 0;
        for (int i = 0; i < inputs.length; i++) {
            int actual = solver.countPrimes(inputs[i]);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: n = " + inputs[i]);
            System.out.println("Expected: " + expected[i]);
            System.out.println("Actual:   " + actual);

            if (actual == expected[i]) {
                System.out.println("Result: PASS");
                passed++;
            } else {
                System.out.println("Result: FAIL");
            }
            System.out.println("---------------------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + inputs.length + " Passed");
    }
}