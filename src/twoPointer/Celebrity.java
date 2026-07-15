package twoPointer;

public class Celebrity {

    /**
     * Function to find the celebrity.
     * @param mat n*n matrix representing the party relationships
     * @return index of the celebrity if found, else -1
     */
    public int findCelebrity(int[][] mat) {
        int n = mat.length;
        int top=0,down=n-1;
        while(top<down){
            if(mat[top][down]==1){
                top++;
            }else if(mat[down][top]==1)down--;
            else{
                top++;
                down--;
            }
        }
        if(top>down)return -1;
        for(int i=0;i<n;i++){
            if(top!=i){
                if(mat[top][i]==1||mat[i][top]==0)return -1;
            }
        }
        return top; // Placeholder
    }

    public static void main(String[] args) {
        Celebrity solver = new Celebrity();

        // Test Cases Definitions
        int[][][] inputs = {
                {{0, 1, 0}, {0, 0, 0}, {0, 1, 0}},                       // Case 1: Standard celebrity at index 1
                {{0, 1}, {1, 0}},                                         // Case 2: No celebrity (both know each other)
                {{0}},                                                    // Case 3: Single person (technically a celebrity)
                {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},                       // Case 4: Multiple potential (invalidates celebrity rule)
                {{0, 1, 1}, {0, 0, 1}, {1, 1, 0}},                       // Case 5: No celebrity (circular/complex)
                {{0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}, {0, 0, 1, 0}}, // Case 6: Celebrity at index 2
                {{0, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 0, 1}, {1, 1, 1, 0}}, // Case 7: Everyone knows everyone (no celebrity)
                {{0, 0, 0, 0}, {1, 0, 1, 1}, {1, 1, 0, 1}, {1, 1, 1, 0}}, // Case 8: Celebrity at index 0
                {{0, 1, 0, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {0, 1, 0, 0}}, // Case 9: Celebrity at index 1 (large party)
                {{0, 0, 0}, {1, 0, 0}, {1, 0, 0}}                         // Case 10: Index 0 known by all, but knows no one
        };

        int[] expectedOutputs = {1, -1, 0, -1, -1, 2, -1, 0, 1, 0};

        int passed = 0;

        System.out.println("--- Starting Test Suite ---");
        for (int i = 0; i < inputs.length; i++) {
            int actual = solver.findCelebrity(inputs[i]);
            boolean isMatch = actual == expectedOutputs[i];

            if (isMatch) passed++;

            System.out.printf("Test Case %d: %s\n", i + 1, isMatch ? "PASSED" : "FAILED");
            System.out.println("   Input: " + java.util.Arrays.deepToString(inputs[i]));
            System.out.println("   Expected: " + expectedOutputs[i]);
            System.out.println("   Actual:   " + actual);
            System.out.println("---------------------------");
        }

        System.out.printf("Final Result: %d/%d Passed\n", passed, inputs.length);
    }
}