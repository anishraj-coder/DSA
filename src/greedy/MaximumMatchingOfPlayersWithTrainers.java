package greedy;

import java.util.Arrays;

public class MaximumMatchingOfPlayersWithTrainers {

    // Implement your solution here
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int i=0, j=0,count=0;
        while(i<players.length&&j<trainers.length){
            if(players[i]<=trainers[j]){
                count++;
                i++;j++;
            }else{
                j++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MaximumMatchingOfPlayersWithTrainers runner = new MaximumMatchingOfPlayersWithTrainers();

        TestCase[] testCases = new TestCase[] {
                // Test 1: Standard Example 1 from problem statement
                new TestCase(
                        new int[]{4, 7, 9},
                        new int[]{8, 2, 5, 8},
                        2
                ),
                // Test 2: Standard Example 2 from problem statement
                new TestCase(
                        new int[]{1, 1, 1},
                        new int[]{10},
                        1
                ),
                // Test 3: No valid matchings possible
                new TestCase(
                        new int[]{10, 20, 30},
                        new int[]{1, 2, 3},
                        0
                ),
                // Test 4: All players match all trainers perfectly
                new TestCase(
                        new int[]{2, 4, 6},
                        new int[]{2, 4, 6},
                        3
                ),
                // Test 5: Single element arrays (Match)
                new TestCase(
                        new int[]{5},
                        new int[]{5},
                        1
                ),
                // Test 6: Single element arrays (No Match)
                new TestCase(
                        new int[]{10},
                        new int[]{5},
                        0
                ),
                // Test 7: Duplicate values requiring optimal greedy matching
                new TestCase(
                        new int[]{5, 5, 5, 5},
                        new int[]{5, 5, 2, 10},
                        3
                ),
                // Test 8: Large capability gap (large inputs close to 10^9 limit)
                new TestCase(
                        new int[]{1000000000, 500000000},
                        new int[]{1000000000, 100000000},
                        1
                ),
                // Test 9: Unsorted arrays needing full sorting to pair efficiently
                new TestCase(
                        new int[]{9, 1, 4, 7, 3},
                        new int[]{2, 8, 3, 5, 10},
                        4
                ),
                // Test 10: Trainers far outnumber players
                new TestCase(
                        new int[]{3, 6},
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8},
                        2
                )
        };

        int passed = 0;

        System.out.println("==========================================================");
        System.out.println("          RUNNING TEST CASES FOR DSA PROBLEM               ");
        System.out.println("==========================================================\n");

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];

            // Make deep copies so user's implementation doesn't affect raw input inspection if modified
            int[] playersCopy = tc.players.clone();
            int[] trainersCopy = tc.trainers.clone();

            int actual = runner.matchPlayersAndTrainers(playersCopy, trainersCopy);
            boolean isPass = actual == tc.expected;

            if (isPass) {
                passed++;
                System.out.printf("[PASS] Test Case %2d\n", i + 1);
            } else {
                System.out.printf("[FAIL] Test Case %2d\n", i + 1);
                System.out.println("       Inputs   : players = " + Arrays.toString(tc.players) + ", trainers = " + Arrays.toString(tc.trainers));
                System.out.println("       Expected : " + tc.expected);
                System.out.println("       Actual   : " + actual);
            }
            System.out.println("----------------------------------------------------------");
        }

        System.out.printf("\nRESULTS: %d / %d Test Cases Passed.\n", passed, testCases.length);
        System.out.println("==========================================================");
    }

    private static class TestCase {
        int[] players;
        int[] trainers;
        int expected;

        TestCase(int[] players, int[] trainers, int expected) {
            this.players = players;
            this.trainers = trainers;
            this.expected = expected;
        }
    }
}