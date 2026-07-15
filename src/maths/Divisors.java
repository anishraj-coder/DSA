package maths;

import java.util.*;

public class Divisors {

    /**
     * Instructions:
     * Implement the method below to find all divisors of n.
     * The result must be returned in sorted order.
     * * Optimization Hint: Use the property that if i divides n,
     * then (n / i) also divides n. You only need to loop up to sqrt(n).
     */
    public List<Integer> getAllDivisors(int n) {
        List<Integer>ans=new ArrayList<>();
        ans.add(1);
        if(n==1)return ans;
        for(int i=2;i*i<=n;i++){
            if(n%i==0){
                ans.add(i);
                if(n/i!=i)ans.add(n/i);
            }
        }
        ans.add(n);
        return ans;
    }

    public static void main(String[] args) {
        Divisors solver = new Divisors();

        // Defining Test Cases
        int[] inputs = {
                6,          // Example 1
                8,          // Example 2
                1,          // Edge case: smallest positive integer
                2,          // Smallest prime
                25,         // Perfect square (avoid duplicate 5)
                36,         // Perfect square with many factors
                97,         // Large prime number
                100,        // Composite number
                48,         // Highly composite number
                1000        // Larger boundary
        };

        Integer[][] expected = {
                {1, 2, 3, 6},
                {1, 2, 4, 8},
                {1},
                {1, 2},
                {1, 5, 25},
                {1, 2, 3, 4, 6, 9, 12, 18, 36},
                {1, 97},
                {1, 2, 4, 5, 10, 20, 25, 50, 100},
                {1, 2, 3, 4, 6, 8, 12, 16, 24, 48},
                {1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 125, 200, 250, 500, 1000}
        };

        int passed = 0;
        for (int i = 0; i < inputs.length; i++) {
            List<Integer> actual = solver.getAllDivisors(inputs[i]);
            List<Integer> expectedList = Arrays.asList(expected[i]);
            actual.sort(Comparator.naturalOrder());

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: n = " + inputs[i]);
            System.out.println("Expected: " + expectedList);
            System.out.println("Actual:   " + (actual == null ? "null" : actual));

            if (expectedList.equals(actual)) {
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