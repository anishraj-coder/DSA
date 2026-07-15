package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalsTriangle {

    public List<Integer> generateRow(int n){
        List<Integer>ans=new ArrayList<>();
        ans.add(1);
        int res=1;
        for(int i=1;i<n;i++){
            res*=n-i;
            res/=i;
            ans.add(res);
        }

        return ans;
    }

    public List<List<Integer>> generate(int n) {
        List<List<Integer>>ans=new ArrayList<>();
        if(n==0)return ans;
        ans.add(List.of(1));

        if(n==1)return ans;

        for(int i=1;i<n;i++){
            ans.add(generateRow(i+1));
        }

        return ans;
    }

    public static void main(String[] args) {
        PascalsTriangle solver = new PascalsTriangle();

        // Test Cases Definitions
        Object[][] testCases = {
                {1, List.of(List.of(1))},
                {2, List.of(List.of(1), List.of(1, 1))},
                {3, List.of(List.of(1), List.of(1, 1), List.of(1, 2, 1))},
                {4, List.of(List.of(1), List.of(1, 1), List.of(1, 2, 1), List.of(1, 3, 3, 1))},
                {5, List.of(List.of(1), List.of(1, 1), List.of(1, 2, 1), List.of(1, 3, 3, 1), List.of(1, 4, 6, 4, 1))},
                {6, List.of(List.of(1), List.of(1, 1), List.of(1, 2, 1), List.of(1, 3, 3, 1), List.of(1, 4, 6, 4, 1), List.of(1, 5, 10, 10, 5, 1))},
                {7, List.of(List.of(1), List.of(1, 1), List.of(1, 2, 1), List.of(1, 3, 3, 1), List.of(1, 4, 6, 4, 1), List.of(1, 5, 10, 10, 5, 1), List.of(1, 6, 15, 20, 15, 6, 1))},
                // Edge cases and larger constraints
                {0, new ArrayList<List<Integer>>()},
                {8, List.of(List.of(1), List.of(1, 1), List.of(1, 2, 1), List.of(1, 3, 3, 1), List.of(1, 4, 6, 4, 1), List.of(1, 5, 10, 10, 5, 1), List.of(1, 6, 15, 20, 15, 6, 1), List.of(1, 7, 21, 35, 35, 21, 7, 1))},
                {10, List.of(
                        List.of(1), List.of(1, 1), List.of(1, 2, 1), List.of(1, 3, 3, 1), List.of(1, 4, 6, 4, 1),
                        List.of(1, 5, 10, 10, 5, 1), List.of(1, 6, 15, 20, 15, 6, 1), List.of(1, 7, 21, 35, 35, 21, 7, 1),
                        List.of(1, 8, 28, 56, 70, 56, 28, 8, 1), List.of(1, 9, 36, 84, 126, 126, 84, 36, 9, 1)
                )}
        };

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            int input = (int) testCases[i][0];
            @SuppressWarnings("unchecked")
            List<List<Integer>> expected = (List<List<Integer>>) testCases[i][1];
            List<List<Integer>> actual = solver.generate(input);

            System.out.println("Test Case " + (i + 1) + ": numRows = " + input);
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);

            if (expected.equals(actual)) {
                System.out.println("Result:   PASSED");
                passed++;
            } else {
                System.out.println("Result:   FAILED");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Final Score: " + passed + "/" + testCases.length);
    }
}