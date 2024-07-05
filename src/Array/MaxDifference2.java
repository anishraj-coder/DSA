package Array;

import java.util.Scanner;

/*
Problem Statement:
Given an array of integers, find the maximum value of arr[i] - arr[j] + i - j
for any pair of indices i and j in the array, where i > j.

Input:
- First line: The size of the array (n)
- Second line: n space-separated integers representing the array elements

Constraints:
- 2 <= array length <= 10^5
- -10^4 <= array[i] <= 10^4

Output:
The maximum value of arr[i] - arr[j] + i - j possible.

Example:
Input:
5
3 9 8 4 1

Output:
7 (This could be achieved when i = 1 and j = 4, as 9 - 1 + 1 - 4 = 7)
*/

public class MaxDifference2 {
    
    /**
     * Finds the maximum value of arr[i] - arr[j] + i - j for any pair (i, j) in the array.
     * 
     * @param arr The input array of integers
     * @return The maximum value of arr[i] - arr[j] + i - j possible
     */
    public static int maxDifference(int[] arr) {
        // Step 1: Add the index to each element
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] + i;
        }
        
        // Step 2: Find the maximum and minimum in the modified array
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            max = Math.max(i, max);
            min = Math.min(i, min);
        }
        
        // Step 3: Restore the original array (optional, but good practice)
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] - i;
        }
        
        // Step 4: Return the difference between max and min
        return max - min;
    }

    public static void main(String[] args) {
    	Scanner in= new Scanner(System.in);
    	System.out.println("Enter the length of array:");
    	int n= in.nextInt();
    	int []arr= new int[n];
    	for(int i=0;i<n;i++) {
    		arr[i]= in.nextInt();    		
    	}
    	System.out.println("Max Difference:\t"+maxDifference(arr));
    	in.close();
    }
}