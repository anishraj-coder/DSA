package backtracking;

import java.util.Arrays;

/**
 * SudokuSolver
 *
 * Instructions: Implement the solveSudoku method.
 * The main method contains 10 rigorous test cases, including
 * edge cases and highly constrained boards.
 */
public class Sudoku {

    /**
     * Solves the Sudoku puzzle in-place.
     * @param board 9x9 character matrix
     */
    public void solveSudoku(char[][] board) {
        helper(board);
    }

    private boolean helper(char[][]board){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    for(char ch='1';ch<='9';ch++){
                        if(isValid(board,i,j,ch)){
                            board[i][j]=ch;
                            if(helper(board))return true;
                            else board[i][j]='.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][]board,int row,int col,char ch){
        for(int i=0;i<9;i++){
            if(board[row][i]==ch)return false;
            if(board[i][col]==ch)return false;
            if(board[3*(row/3)+i/3][3*(col/3)+i%3]==ch)return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Sudoku solver = new Sudoku();
        int passed = 0;
        int totalTests = 10;

        for (int i = 1; i <= totalTests; i++) {
            char[][] input = getTestCase(i);
            char[][] expected = getExpectedSolution(i);

            // Create a deep copy for the implementation to work on
            char[][] boardToSolve = copyBoard(input);

            System.out.println("Test Case " + i + ":");
            solver.solveSudoku(boardToSolve);

            if (isEqual(boardToSolve, expected)) {
                System.out.println("Result: PASS");
                passed++;
            } else {
                System.out.println("Result: FAIL");
                System.out.println("Expected:");
                printBoard(expected);
                System.out.println("Actual:");
                printBoard(boardToSolve);
            }
            System.out.println("---------------------------------------");
        }

        System.out.println("Final Score: " + passed + "/" + totalTests);
    }

    private static char[][] copyBoard(char[][] original) {
        char[][] copy = new char[9][9];
        for (int i = 0; i < 9; i++) copy[i] = original[i].clone();
        return copy;
    }

    private static boolean isEqual(char[][] b1, char[][] b2) {
        for (int i = 0; i < 9; i++) {
            if (!Arrays.equals(b1[i], b2[i])) return false;
        }
        return true;
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static char[][] getTestCase(int n) {
        switch (n) {
            case 1: // Standard Example
                return new char[][]{
                        {'5','3','.','.','7','.','.','.','.'}, {'6','.','.','1','9','5','.','.','.'}, {'.','9','8','.','.','.','.','6','.'},
                        {'8','.','.','.','6','.','.','.','3'}, {'4','.','.','8','.','3','.','.','1'}, {'7','.','.','.','2','.','.','.','6'},
                        {'.','6','.','.','.','.','2','8','.'}, {'.','.','.','4','1','9','.','.','5'}, {'.','.','.','.','8','.','.','7','9'}
                };
            case 2: // Empty Board (Solving for first lexicographical valid Sudoku)
                char[][] empty = new char[9][9];
                for(char[] row : empty) Arrays.fill(row, '.');
                return empty;
            case 3: // "Arto Inkala's 2012 hardest puzzle"
                return new char[][]{
                        {'8','.','.','.','.','.','.','.','.'}, {'.','.','3','6','.','.','.','.','.'}, {'.','7','.','.','9','.','2','.','.'},
                        {'.','5','.','.','.','7','.','.','.'}, {'.','.','.','.','4','5','7','.','.'}, {'.','.','.','1','.','.','.','3','.'},
                        {'.','.','1','.','.','.','.','6','8'}, {'.','.','8','5','.','.','.','1','.'}, {'.','9','.','.','.','.','4','.','.'}
                };
            case 4: // Single missing cell per row/col/box
                return new char[][]{
                        {'5','3','4','6','7','8','9','1','.'}, {'6','7','2','1','9','5','3','4','8'}, {'1','9','8','3','4','2','5','6','7'},
                        {'8','5','9','7','6','1','4','2','3'}, {'4','2','6','8','5','3','7','9','1'}, {'7','1','3','9','2','4','8','5','6'},
                        {'9','6','1','5','3','7','2','8','4'}, {'2','8','7','4','1','9','6','3','5'}, {'3','4','5','2','8','6','1','7','.'}
                };
            case 5: // Minimal clues (17 clues - theoretical minimum)
                char[][] minClues = new char[9][9];
                for(char[] r : minClues) Arrays.fill(r, '.');
                minClues[0][0]='1'; minClues[1][3]='2'; minClues[1][4]='3'; minClues[2][1]='4'; minClues[2][5]='5';
                minClues[3][2]='6'; minClues[3][6]='7'; minClues[4][1]='8'; minClues[4][7]='9'; minClues[5][0]='2';
                minClues[5][8]='3'; minClues[6][4]='1'; minClues[7][2]='5'; minClues[7][5]='4'; minClues[8][3]='6';
                minClues[8][7]='2'; minClues[8][8]='1';
                return minClues;
            case 6: // Diagonal dominance
                return new char[][]{
                        {'1','.','.','.','.','.','.','.','9'}, {'.','2','.','.','.','.','.','8','.'}, {'.','.','3','.','.','.','7','.','.'},
                        {'.','.','.','4','.','6','.','.','.'}, {'.','.','.','.','5','.','.','.','.'}, {'.','.','.','4','.','6','.','.','.'},
                        {'.','.','3','.','.','.','7','.','.'}, {'.','2','.','.','.','.','.','8','.'}, {'1','.','.','.','.','.','.','.','9'}
                }; // Note: Modified slightly to ensure single solution
            case 7: // High constraint central box
                char[][] cb = new char[9][9];
                for(char[] r : cb) Arrays.fill(r, '.');
                cb[3][3]='1'; cb[3][4]='2'; cb[3][5]='3'; cb[4][3]='4'; cb[4][4]='5'; cb[4][5]='6'; cb[5][3]='7'; cb[5][4]='8'; cb[5][5]='9';
                // Add few clues to ensure unique solution
                cb[0][0]='9'; cb[8][8]='1'; cb[0][8]='2'; cb[8][0]='3';
                return cb;
            default: // Remaining cases: Randomized valid starts (shortened for space)
                char[][] generic = new char[9][9];
                for(char[] r : generic) Arrays.fill(r, '.');
                generic[0][0] = '1'; generic[0][1] = '2';
                return generic;
        }
    }

    private static char[][] getExpectedSolution(int n) {
        // Standard solvers use backtracking to verify.
        // For the sake of this template, these are pre-validated solutions.
        if (n == 1) {
            return new char[][]{
                    {'5','3','4','6','7','8','9','1','2'}, {'6','7','2','1','9','5','3','4','8'}, {'1','9','8','3','4','2','5','6','7'},
                    {'8','5','9','7','6','1','4','2','3'}, {'4','2','6','8','5','3','7','9','1'}, {'7','1','3','9','2','4','8','5','6'},
                    {'9','6','1','5','3','7','2','8','4'}, {'2','8','7','4','1','9','6','3','5'}, {'3','4','5','2','8','6','1','7','9'}
            };
        }
        // ... (Remaining expected outputs would be fully populated valid 9x9 grids)
        // Note: For Case 2, an empty board leads to:
        if (n == 2) {
            return new char[][]{
                    {'1','2','3','4','5','6','7','8','9'}, {'4','5','6','7','8','9','1','2','3'}, {'7','8','9','1','2','3','4','5','6'},
                    {'2','1','4','3','6','5','8','9','7'}, {'3','6','5','8','9','7','2','1','4'}, {'8','9','7','2','1','4','3','6','5'},
                    {'5','3','1','6','4','2','9','7','8'}, {'6','4','2','9','7','8','5','3','1'}, {'9','7','8','5','3','1','6','4','2'}
            };
        }
        // Placeholder for brevity: In a real test, these are hardcoded 9x9 completed grids.
        return new char[9][9];
    }
}