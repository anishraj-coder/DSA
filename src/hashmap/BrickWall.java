package hashmap;

import java.util.*;

/**
 * Problem: Brick Wall
 * Objective: Find the vertical line that crosses the minimum number of bricks.
 * A line crosses the fewest bricks if it passes through the maximum number of brick edges.
 */
public class BrickWall {

    public int leastBricks(List<List<Integer>> arr) {
        int n=arr.size(),max=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(List<Integer>a :arr){
            int sum=0;
            for(int i=0;i<a.size()-1;i++){
                int curr=a.get(i);
                sum+=curr;
                map.put(sum,map.getOrDefault(sum,0)+1);
                max=Math.max(max,map.get(sum));
            }
        }
        return n-max;
    }

    public static void main(String[] args) {
        BrickWall solver = new BrickWall();

        // Test Case Definitions
        List<TestCase> testCases = new ArrayList<>();

        // Case 1: Standard Example
        testCases.add(new TestCase(
                Arrays.asList(
                        Arrays.asList(1, 2, 2, 1),
                        Arrays.asList(3, 1, 2),
                        Arrays.asList(1, 3, 2),
                        Arrays.asList(2, 4),
                        Arrays.asList(3, 1, 2),
                        Arrays.asList(1, 3, 1, 1)
                ), 2, "Standard multi-row wall"));

        // Case 2: Single brick per row (Must cross all)
        testCases.add(new TestCase(
                Arrays.asList(
                        Arrays.asList(1),
                        Arrays.asList(1),
                        Arrays.asList(1)
                ), 3, "All rows have single brick"));

        // Case 3: Large widths
        testCases.add(new TestCase(
                Arrays.asList(
                        Arrays.asList(1000000, 1000000),
                        Arrays.asList(2000000)
                ), 1, "Large width values"));

        // Case 4: Perfectly aligned edges
        testCases.add(new TestCase(
                Arrays.asList(
                        Arrays.asList(2, 2),
                        Arrays.asList(2, 2),
                        Arrays.asList(2, 2)
                ), 0, "All edges align perfectly in the middle"));

        // Case 5: No edges align except boundaries
        testCases.add(new TestCase(
                Arrays.asList(
                        Arrays.asList(1, 5),
                        Arrays.asList(2, 4),
                        Arrays.asList(3, 3)
                ), 2, "Staggered bricks with minimum overlap"));

        // Case 6: Single row wall
        testCases.add(new TestCase(
                Arrays.asList(
                        Arrays.asList(1, 1, 1, 1)
                ), 0, "Single row with multiple bricks"));

        // Case 7: Varied row lengths
        testCases.add(new TestCase(
                Arrays.asList(
                        Arrays.asList(1, 1, 1),
                        Arrays.asList(1, 2),
                        Arrays.asList(2, 1)
                ), 1, "Small wall with varied distributions"));

        // Case 8: Only two bricks per row, different split points
        testCases.add(new TestCase(
                Arrays.asList(
                        Arrays.asList(1, 4),
                        Arrays.asList(4, 1),
                        Arrays.asList(1, 4)
                ), 1, "Frequent edge at index 1"));

        // Case 9: All bricks are width 1
        testCases.add(new TestCase(
                Arrays.asList(
                        Arrays.asList(1, 1, 1),
                        Arrays.asList(1, 1, 1),
                        Arrays.asList(1, 1, 1)
                ), 0, "Grid-like wall (all width 1)"));

        // Case 10: Deep wall (many rows), narrow width
        testCases.add(new TestCase(
                Arrays.asList(
                        Arrays.asList(1, 2), Arrays.asList(1, 2), Arrays.asList(1, 2),
                        Arrays.asList(2, 1), Arrays.asList(2, 1)
                ), 2, "High N, narrow total width"));

        // Execution
        int passed = 0;
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-5s | %-15s | %-15s | %-10s%n", "Test", "Expected", "Actual", "Result");
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int actual = solver.leastBricks(tc.input);
            boolean isPassed = actual == tc.expected;
            if (isPassed) passed++;

            System.out.printf("%-5d | %-15d | %-15d | %-10s%n",
                    (i + 1), tc.expected, actual, isPassed ? "PASSED" : "FAILED");
            if (!isPassed) {
                System.out.println("      -> Note: " + tc.description);
            }
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Final Score: " + passed + "/" + testCases.size());
    }

    static class TestCase {
        List<List<Integer>> input;
        int expected;
        String description;

        TestCase(List<List<Integer>> input, int expected, String description) {
            this.input = input;
            this.expected = expected;
            this.description = description;
        }
    }
}