package maths;

import java.util.*;

public class PrimeFactorization {

    /**
     * Instructions:
     * Implement the method below to return the prime factorization of each number
     * in the queries array in sorted order.
     * * Example: query = 12, Output = [2, 2, 3]
     */
    public List<List<Integer>> getPrimeFactorization(int[] arr) {
        List<List<Integer>>ans=new ArrayList<>();
        for(int a:arr){
            if(a==0){
                ans.add(new ArrayList<>());
                continue;
            }
            List<Integer>temp=getPrimeFactor(a);
            ans.add(temp);
        }
        return ans;
    }

    private List<Integer> getPrimeFactor(int n){
        List<Integer>ans=new ArrayList<>();
        for(int i=2;i*i<=n;i++){
            if(n%i==0){
                while(n%i==0) {
                    ans.add(i);
                    n /= i;
                }
            }
        }

        if(n!=1)ans.add(n);
        return ans;
    }

    public static void main(String[] args) {
        PrimeFactorization solver = new PrimeFactorization();

        // Defining Test Cases
        int[][] inputs = {
                {2, 3, 4, 5, 6},                  // Basic sequence
                {7, 12, 18},                      // Mix of prime and composite
                {1},                              // Edge case: 1 (no prime factors)
                {100},                            // Perfect square with multiple factors
                {97},                             // Large prime number
                {1024},                           // Power of 2
                {0},                              // Edge case: 0 (usually no factors)
                {2, 2, 2},                        // Duplicate queries
                {99991},                          // Large Prime near boundary
                {45, 60, 1}                       // Mixed large composites and 1
        };

        Integer[][][] expected = {
                {{2}, {3}, {2, 2}, {5}, {2, 3}},
                {{7}, {2, 2, 3}, {2, 3, 3}},
                {{}},
                {{2, 2, 5, 5}},
                {{97}},
                {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}},
                {{}},
                {{2}, {2}, {2}},
                {{99991}},
                {{3, 3, 5}, {2, 2, 3, 5}, {}}
        };

        int passed = 0;
        for (int i = 0; i < inputs.length; i++) {
            List<List<Integer>> actual = solver.getPrimeFactorization(inputs[i]);
            String expectedStr = formatResult(expected[i]);
            String actualStr = actual == null ? "null" : formatActual(actual);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(inputs[i]));
            System.out.println("Expected: " + expectedStr);
            System.out.println("Actual:   " + actualStr);

            if (expectedStr.equals(actualStr)) {
                System.out.println("Result: PASS");
                passed++;
            } else {
                System.out.println("Result: FAIL");
            }
            System.out.println("---------------------------------------");
        }

        System.out.println("Final Result: " + passed + "/" + inputs.length + " Passed");
    }

    private static String formatResult(Integer[][] res) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < res.length; i++) {
            sb.append(Arrays.toString(res[i]));
            if (i < res.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private static String formatActual(List<List<Integer>> res) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i).toString());
            if (i < res.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}