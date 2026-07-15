package backtracking;

import java.util.*;

public class NQueens {


    private int[]dig,ant;
    private int[]col;

    /**
     * Problem: The n-queens puzzle is the problem of placing n queens on an n x n
     * chessboard such that no two queens attack each other.
     * Constraints: 1 <= n <= 9
     */
    public List<List<String>> solve(int n) {
        if(n<=0)return new ArrayList<>();
        if(n==2)return new ArrayList<>();
        this.col=new int[n];
        this.ant=new int[2*n-1];
        this.dig=new int[2*n-1];
        int[][]arr=new int[n][n];
        List<List<String>>ans=new ArrayList<>();
        helper(arr,0,ans);
        return ans;
    }

    private void helper(int[][]arr,int i,List<List<String>>ans){
        int n=arr.length;
        if(i==n){
            addAns(arr,ans);
            return;
        }

        for(int j=0;j<n;j++){
            if(!canPlace(arr,i,j))continue;
            col[j]=dig[i-j+(n-1)]=ant[i+j]=1;
            arr[i][j]=1;
            helper(arr,i+1,ans);
            arr[i][j]=0;
            col[j]=dig[i-j+(n-1)]=ant[i+j]=0;
        }
    }

    private boolean canPlace(int[][]arr,int i,int j){
        int n=arr.length;
        return  arr[i][j]==0&& col[j]==0&&dig[i-j+(n-1)]==0&&ant[i+j]==0;
    }

    private void addAns(int[][]arr,List<List<String>>ans){
        List<String>temp=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<arr.length;j++){
                sb.append((arr[i][j]==1)?'Q':'.');
            }
            temp.add(sb.toString());
        }
        ans.add(temp);
    }

    public static void main(String[] args) {
        NQueens solver = new NQueens();

        // Defining Test Cases
        Object[][] testCases = {
                {1, List.of(List.of("Q"))},
                {2, Collections.emptyList()},
                {3, Collections.emptyList()},
                {4, List.of(
                        List.of(".Q..", "...Q", "Q...", "..Q."),
                        List.of("..Q.", "Q...", "...Q", ".Q..")
                )},
                {5, List.of(
                        List.of("Q....", "..Q..", "....Q", ".Q...", "...Q."),
                        List.of("Q....", "...Q.", ".Q...", "....Q", "..Q.."),
                        List.of(".Q...", "...Q.", "Q....", "..Q..", "....Q"),
                        List.of(".Q...", "....Q", "..Q..", "Q....", "...Q."),
                        List.of("..Q..", "Q....", "...Q.", ".Q...", "....Q"),
                        List.of("..Q..", "....Q", ".Q...", "...Q.", "Q...."),
                        List.of("...Q.", "Q....", "..Q..", "....Q", ".Q..."),
                        List.of("...Q.", ".Q...", "....Q", "..Q..", "Q...."),
                        List.of("....Q", ".Q...", "...Q.", "Q....", "..Q.."),
                        List.of("....Q", "..Q..", "Q....", "...Q.", ".Q...")
                )},
                {6, 4}, // Storing size for larger outputs to avoid cluttering main
                {7, 40},
                {8, 92},
                {9, 352},
                {0, 0} // Boundary check (though constraint is n >= 1)
        };

        System.out.println("N-Queens Test Suite Results:\n");
        System.out.printf("%-5s | %-15s | %-15s | %-10s%n", "N", "Expected Size", "Actual Size", "Result");
        System.out.println("---------------------------------------------------------------");

        for (Object[] testCase : testCases) {
            int n = (int) testCase[0];
            List<List<String>> actual = solver.solve(n);

            boolean passed;
            String expectedDisplay;

            if (testCase[1] instanceof List) {
                List<List<String>> expected = (List<List<String>>) testCase[1];
                passed = compareResults(expected, actual);
                expectedDisplay = String.valueOf(expected.size());
            } else {
                int expectedSize = (int) testCase[1];
                passed = (actual != null && actual.size() == expectedSize);
                expectedDisplay = String.valueOf(expectedSize);
            }

            System.out.printf("%-5d | %-15s | %-15s | %-10s%n",
                    n, expectedDisplay, (actual == null ? "null" : actual.size()), (passed ? "PASSED" : "FAILED"));
        }
    }

    /**
     * Helper to compare two nested lists regardless of order
     */
    private static boolean compareResults(List<List<String>> expected, List<List<String>> actual) {
        if (actual == null) return expected == null;
        if (expected.size() != actual.size()) return false;

        HashSet<List<String>> set1 = new HashSet<>(expected);
        HashSet<List<String>> set2 = new HashSet<>(actual);
        return set1.equals(set2);
    }
}