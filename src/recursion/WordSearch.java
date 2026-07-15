package recursion;

import java.util.*;

/**
 * Problem: Word Search
 * Goal: Determine if a word exists in an m x n grid of characters.
 * The word can be constructed from letters of sequentially adjacent cells (horizontal or vertical).
 * Each cell may be used at most once per word.
 */
public class WordSearch {

    /**
     * Implement your solution here.
     *
     * @param arr The m x n grid of characters.
     * @param s  The target string to find.
     * @return true if the word exists in the grid, false otherwise.
     */
    public boolean exist(char[][] arr, String s) {
        int m=arr.length,n=arr[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(helper(arr,i,j,s,0))return true;
            }
        }
        return false;
    }

    private boolean helper(char[][]arr,int i,int j,String s,int idx){
        if(idx==s.length())return true;
        int m=arr.length,n=arr[0].length;
        if(i<0||j<0||i>=m||j>=n||arr[i][j]!=s.charAt(idx))return false;

        int[][]dir=new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        boolean res=false;
        for(int[]d:dir){
            int i1=d[0]+i,j1=d[1]+j;
            char temp=arr[i][j];
            arr[i][j]='#';
            if(helper(arr,i1,j1,s,idx+1)){
                res=true;
                arr[i][j]=temp;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        WordSearch solver = new WordSearch();

        // Helper to define boards easily
        char[][] board1 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        char[][] board2 = {{'a'}};

        char[][] board3 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        // Test Case Definitions: {Board, Word, Expected Output}
        Object[][] testCases = {
                {board1, "ABCCED", true},         // Standard path
                {board1, "SEE", true},            // Vertical and horizontal
                {board1, "ABCB", false},          // Cannot reuse same cell 'B'
                {board2, "a", true},              // Single cell match
                {board2, "b", false},             // Single cell mismatch
                {board1, "ABCESEEEFS", true},     // Long snaking path
                {board3, "ABCESEEEFSAD", true},   // Path using most of the board
                {board1, "ABCEFS", false},        // Path exists but directionally blocked
                {board1, "ADEE", true},           // Path at the bottom edge
                {board1, "ASAD", false},          // Reusing 'A' at (0,0) and (2,0) is fine, but check connectivity
                {board1, "XYZ", false}            // Characters not in board
        };

        int passed = 0;
        System.out.println("--- Starting Word Search Tests ---\n");

        for (int i = 0; i < testCases.length; i++) {
            char[][] board = (char[][]) testCases[i][0];
            String word = (String) testCases[i][1];
            boolean expected = (boolean) testCases[i][2];

            boolean actual = solver.exist(deepCopy(board), word);

            System.out.println("Test Case " + (i + 1) + ": Word = \"" + word + "\"");
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);

            if (actual == expected) {
                System.out.println("Result:   PASSED");
                passed++;
            } else {
                System.out.println("Result:   FAILED");
            }
            System.out.println("----------------------------------------------");
        }

        System.out.println("\nFinal Results: " + passed + "/" + testCases.length + " tests passed.");
    }

    /**
     * Creates a deep copy of the board to ensure tests don't interfere with each other
     * if the implementation modifies the board.
     */
    private static char[][] deepCopy(char[][] original) {
        if (original == null) return null;
        char[][] result = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = original[i].clone();
        }
        return result;
    }
}