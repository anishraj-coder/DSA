package Array;

import java.util.Scanner;

/*Given n length array "arr" and an integer k, Check if there exists a pair(i,j) such that arr[i]+arr[j] == k and i!=j
Input Format

Each of the test cases have 3 lines.
The First line contains the size of the array n.
The second line contains the n elements of the array.
The third line contains an integer k
Constraints

2 <= array length <= 10^5
-10^4 <= array[i] <= 10^4
Output Format

Print true if there is a valid pair present else print false.
Sample Input 0

7
2 -1 0 3 2 5 7
8
Sample Output 0

true*/

public class TwoSumBrute {

    /**
     * Checks if there exist two numbers in the array that sum up to a given target.
     * 
     * @param arr The input array of integers
     * @param k   The target sum
     * @return true if two numbers in the array sum up to k, false otherwise
     */
    public static boolean sumFound(int[] arr, int k) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == k)
                    return true;
            }
        }
        return false;
    }

    /**
     * Main method to run the program.
     * Reads input from the user and calls the sumFound method.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number:");
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(sumFound(arr, k));
        in.close();
    }
}
