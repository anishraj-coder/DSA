package stacks;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem: Asteroid Collision
 * Find out the state of the asteroids after all collisions.
 */
public class AsteroidImpactManager {

    /**
     * Computes the remaining asteroids after all collisions have occurred.
     * * @param asteroids An array of integers representing asteroids.
     * @return The state of asteroids after all collisions.
     */
    public int[] collideAsteroids(int[] arr) {
        int n=arr.length;
        if(n==1)return arr;
        Stack<Integer>st=new Stack<>();
        int i=0;
        while(i<n){
            if(st.isEmpty()||arr[i]>0){
                st.push(arr[i]);
                i++;
            }else{
                if(st.isEmpty()||st.peek()<0){
                    st.push(arr[i]);
                    i++;
                }else if(Math.abs(arr[i])>st.peek()){
                    st.pop();
                }else if(Math.abs(arr[i])<st.peek()){
                    i++;
                }else{
                    st.pop();
                    i++;
                }
            }
        }
        int length=st.size();
        int[]res=new int[length];
        for(int j=length-1;j>=0;j--){
            res[j]=st.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        AsteroidImpactManager manager = new AsteroidImpactManager();
        int passed = 0;
        int total = 10;

        // Test Case 1: Standard collision (right eats left)
        passed += runTest(1, new int[]{5, 10, -5}, new int[]{5, 10}, manager);

        // Test Case 2: Equal size collision (both explode)
        passed += runTest(2, new int[]{8, -8}, new int[]{}, manager);

        // Test Case 3: Chained collision (one negative eats multiple positives)
        passed += runTest(3, new int[]{10, 2, -5}, new int[]{10}, manager);

        // Test Case 4: No collisions (moving away from each other)
        passed += runTest(4, new int[]{-2, -1, 1, 2}, new int[]{-2, -1, 1, 2}, manager);

        // Test Case 5: Complex chain (Example 4 from prompt)
        passed += runTest(5, new int[]{3, 5, -6, 2, -1, 4}, new int[]{-6, 2, 4}, manager);

        // Test Case 6: Edge Case - Single direction (All Right)
        passed += runTest(6, new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}, manager);

        // Test Case 7: Edge Case - Single direction (All Left)
        passed += runTest(7, new int[]{-1, -2, -3, -4, -5}, new int[]{-1, -2, -3, -4, -5}, manager);

        // Test Case 8: Massive negative clears everything to the left
        passed += runTest(8, new int[]{1, 2, 3, 4, -5}, new int[]{-5}, manager);

        // Test Case 9: Multiple identical collisions resulting in empty
        passed += runTest(9, new int[]{5, 5, -5, -5}, new int[]{}, manager);

        // Test Case 10: Late collision after long sequence
        passed += runTest(10, new int[]{-2, -2, 1, -2}, new int[]{-2, -2, -2}, manager);

        System.out.println("\n---------------------------------------");
        System.out.println("Final Results: " + passed + " / " + total + " Passed");
        System.out.println("---------------------------------------");
    }

    private static int runTest(int id, int[] input, int[] expected, AsteroidImpactManager manager) {
        int[] actual = manager.collideAsteroids(input);
        boolean isMatch = Arrays.equals(actual, expected);

        System.out.println("Test Case " + id + ": " + (isMatch ? "PASSED ✅" : "FAILED ❌"));
        System.out.println("   Input:    " + Arrays.toString(input));
        System.out.println("   Expected: " + Arrays.toString(expected));
        System.out.println("   Actual:   " + Arrays.toString(actual));
        System.out.println();

        return isMatch ? 1 : 0;
    }
}