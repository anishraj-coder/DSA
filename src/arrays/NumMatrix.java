package arrays;

public class NumMatrix {

    private final int[][]arr, prefix;

    // TODO: Define your instance variables here (e.g., prefix sum matrix)

    public NumMatrix(int[][] matrix) {
        this.arr=matrix;
        int m=arr.length,n=arr[0].length;
        this.prefix =new int[m][n];
        for(int i=0;i<m;i++){
            prefix[i][0]=arr[i][0];
            for(int j=1;j<n;j++)prefix[i][j]=arr[i][j]+prefix[i][j-1];
        }
        for(int i=1;i<m;i++){
            for(int j=0;j<n;j++){
                prefix[i][j]+=prefix[i-1][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        int sum=prefix[row2][col2];
        if(row1>0)sum-=prefix[row1-1][col2];
        if(col1>0)sum-=prefix[row2][col1-1];
        if(row1>0&&col1>0){
            sum+=prefix[row1-1][col1-1];
        }

        return sum;
    }

    // =========================================================================
    // TEST RUNNER AND TEST CASES
    // =========================================================================

    private static class TestCase {
        int id;
        int[][] matrix;
        int[][] queries; // Each query is {row1, col1, row2, col2}
        int[] expected;
        String description;

        TestCase(int id, int[][] matrix, int[][] queries, int[] expected, String description) {
            this.id = id;
            this.matrix = matrix;
            this.queries = queries;
            this.expected = expected;
            this.description = description;
        }
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[] {
                // Test 1: Standard Example from Problem Statement
                new TestCase(
                        1,
                        new int[][] {
                                {3, 0, 1, 4, 2},
                                {5, 6, 3, 2, 1},
                                {1, 2, 0, 1, 5},
                                {4, 1, 0, 1, 7},
                                {1, 0, 3, 0, 5}
                        },
                        new int[][] {
                                {2, 1, 4, 3},
                                {1, 1, 2, 2},
                                {1, 2, 2, 4}
                        },
                        new int[] {8, 11, 12},
                        "Standard 5x5 Matrix with multiple queries"
                ),

                // Test 2: Single Element Matrix (1x1)
                new TestCase(
                        2,
                        new int[][] {
                                {42}
                        },
                        new int[][] {
                                {0, 0, 0, 0}
                        },
                        new int[] {42},
                        "Single Element Matrix (1x1)"
                ),

                // Test 3: Entire Matrix Query
                new TestCase(
                        3,
                        new int[][] {
                                {1, 2},
                                {3, 4}
                        },
                        new int[][] {
                                {0, 0, 1, 1}
                        },
                        new int[] {10},
                        "Query covering the entire matrix"
                ),

                // Test 4: Single Row Matrix (1xN)
                new TestCase(
                        4,
                        new int[][] {
                                {2, 4, 6, 8, 10}
                        },
                        new int[][] {
                                {0, 1, 0, 3},
                                {0, 0, 0, 4}
                        },
                        new int[] {18, 30},
                        "Single Row Matrix (1xN)"
                ),

                // Test 5: Single Column Matrix (Mx1)
                new TestCase(
                        5,
                        new int[][] {
                                {5},
                                {10},
                                {15},
                                {20}
                        },
                        new int[][] {
                                {1, 0, 3, 0},
                                {0, 0, 2, 0}
                        },
                        new int[] {45, 30},
                        "Single Column Matrix (Mx1)"
                ),

                // Test 6: Matrix with Negative Numbers
                new TestCase(
                        6,
                        new int[][] {
                                {-1, -2, -3},
                                {4,  5,  6},
                                {-7,  8, -9}
                        },
                        new int[][] {
                                {0, 0, 2, 2},
                                {0, 1, 1, 2},
                                {1, 0, 2, 1}
                        },
                        new int[] {-1, 8, 10},
                        "Matrix containing negative numbers"
                ),

                // Test 7: All Zeroes
                new TestCase(
                        7,
                        new int[][] {
                                {0, 0, 0},
                                {0, 0, 0},
                                {0, 0, 0}
                        },
                        new int[][] {
                                {0, 0, 2, 2},
                                {1, 1, 2, 2}
                        },
                        new int[] {0, 0},
                        "Matrix with all zeroes"
                ),

                // Test 8: Single Cell Query inside a Larger Matrix
                new TestCase(
                        8,
                        new int[][] {
                                {1, 2, 3},
                                {4, 9, 6},
                                {7, 8, 5}
                        },
                        new int[][] {
                                {1, 1, 1, 1},
                                {2, 2, 2, 2}
                        },
                        new int[] {9, 5},
                        "Single cell query inside a multi-element matrix"
                ),

                // Test 9: Top-Left Origin Queries
                new TestCase(
                        9,
                        new int[][] {
                                {10, 20, 30},
                                {40, 50, 60},
                                {70, 80, 90}
                        },
                        new int[][] {
                                {0, 0, 0, 0},
                                {0, 0, 1, 1},
                                {0, 0, 2, 2}
                        },
                        new int[] {10, 120, 450},
                        "Queries originating at top-left corner (0,0)"
                ),

                // Test 10: Larger Grid with Mixed Large Numbers
                new TestCase(
                        10,
                        new int[][] {
                                {100, -50, 200, 300},
                                {-100, 400, -200, 500},
                                {50,   60,  70,   80}
                        },
                        new int[][] {
                                {0, 1, 2, 2},
                                {1, 0, 2, 3},
                                {0, 0, 1, 3}
                        },
                        new int[] {580, 560, 1150},
                        "Larger grid with mixed positive and negative values"
                )
        };

        int passedCount = 0;

        for (TestCase test : testCases) {
            System.out.println("==================================================");
            System.out.printf("Test %d: %s%n", test.id, test.description);
            System.out.println("==================================================");

            NumMatrix numMatrix = new NumMatrix(test.matrix);
            boolean allQueriesPassed = true;

            for (int q = 0; q < test.queries.length; q++) {
                int[] query = test.queries[q];
                int expected = test.expected[q];
                int actual = numMatrix.sumRegion(query[0], query[1], query[2], query[3]);

                boolean match = (expected == actual);
                if (!match) {
                    allQueriesPassed = false;
                }

                System.out.printf("  Query [%d, %d, %d, %d] -> Expected: %d | Actual: %d | Status: %s%n",
                        query[0], query[1], query[2], query[3],
                        expected, actual,
                        match ? "PASS" : "FAIL");
            }

            if (allQueriesPassed) {
                System.out.println("Result: PASS\n");
                passedCount++;
            } else {
                System.out.println("Result: FAIL\n");
            }
        }

        System.out.println("--------------------------------------------------");
        System.out.printf("Summary: %d / %d Tests Passed%n", passedCount, testCases.length);
        System.out.println("--------------------------------------------------");
    }
}