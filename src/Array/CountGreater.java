package Array;

import java.util.Scanner;

/**
 * This class solves the problem of counting elements in an array that have at least one element greater than themselves.
 *
 * Problem Statement:
 * Given an array of integers, count the total number of elements that have at least one other element in the array
 * greater than themselves.
 *
 * Constraints:
 * - 2 <= array length <= 10^5
 * - -10^4 <= array[i] <= 10^4
 *
 * Input Format:
 * - First line: The size of the array (n)
 * - Second line: n space-separated integers representing the array elements
 *
 * Output:
 * A single integer representing the count of elements with at least one greater element in the array.
 */
public class CountGreater {

    /**
     * Counts the number of elements in the array that have at least one element greater than themselves.
     *
     * @param arr The input array of integers
     * @return The count of elements with at least one greater element
     */
    public static int countGreater(int[] arr) {
        int max = Integer.MIN_VALUE;
        int count = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (max == arr[i]) {
                count++;
            } else if (max < arr[i]) {
                count = 1;
                max = arr[i];
            }
        }
        
        return arr.length - count;
    }

    /**
     * Main method to run the program.
     * Reads input from the user and calls the countGreater method.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the array length:");
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();
        System.out.println("Valid Elements: " + countGreater(arr));
        in.close();
    }
}