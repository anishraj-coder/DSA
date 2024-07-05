package Array;

import java.util.Scanner;

/*
Problem Statement:
Given an array of integers, find the maximum difference between any two elements,
where the larger element appears after the smaller element in the array.
In other words, find max(arr[i] - arr[j]) where i > j.

Input:
- First line: The size of the array (n)
- Second line: n space-separated integers representing the array elements

Constraints:
- 2 <= array length <= 10^5
- -10^4 <= array[i] <= 10^4

Output:
The maximum value of arr[i] - arr[j] possible.

Example:
Input:
5
3 9 8 4 1

Output:
8 (because 9 - 1 = 8, which is the maximum difference)
*/

public class MaxDifference1 {
    
    /**
     * Finds the maximum difference between any two elements in the array.
     * 
     * @param arr The input array of integers
     * @return The maximum difference possible
     */
    public static int maxDifference1(int[] arr) {
        // Initialize max to the smallest possible integer
        int max = Integer.MIN_VALUE;
        // Initialize min to the largest possible integer
        int min = Integer.MAX_VALUE;
        
        // Iterate through the array
        for (int i : arr) {
            // Update max if we find a larger element
            max = Math.max(i, max);
            // Update min if we find a smaller element
            min = Math.min(i, min);
        }
        
        // The maximum difference is the difference between the largest and smallest elements
        return max - min;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the no. of elements");
        int n = in.nextInt();
        int[] arr = new int[n];
        
        // Read the array elements
        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();
        
        // Calculate and print the maximum difference
        System.out.println("Max Difference:\t" + maxDifference1(arr));
        in.close();
    }
}