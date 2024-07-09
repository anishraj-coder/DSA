package Hashmap;

import java.util.HashMap;

/*Given N array elements and Q queries. 
 * For every query find frequency of element in array.
Input Format

First line contains 2 integers N,
 M denoting size of the array and the number of queries.
Second line contains the elements of the array.
Third line contains M elements representin queries. 
Constraints

1 <= arr.length <= 10^5
1 <= arr[i], value <= 10^4
0 <= left <= right < arr.length
Output Format

Output M integers in different lines denoting the frequency of each query.
Sample Input 0

9 2
2 8 6 9 8 6 8 2 11
2 8
Sample Output 0

2
3*/

public class FrequencyCount {

	public static void main(String[] args) {

		// Sample array (replace with user input if needed)
		int[] arr = { 2, 8, 3, 6, 9, 8, 6, 8, 2, 11 };

		// Sample queries (replace with user input if needed)
		int[] query = { 2, 8 };

		// HashMap to store element frequencies
		HashMap<Integer, Integer> count = new HashMap<>();

		// Build the frequency map for elements in the array
		for (int element : arr) {
			if (count.containsKey(element)) {
				// If the element already exists in the map, increment its frequency count
				count.put(element, count.get(element) + 1);
			} else {
				// If the element is not found, add it to the map with a frequency of 1
				count.put(element, 1);
			}
		}

		// Process each query and print the frequency of the queried element
		for (int element : query) {
			if (count.containsKey(element)) {
				System.out.println(element + " -> " + count.get(element));
			} else {
				System.out.println(element + " -> 0"); // Print 0 if the element is not found in the array
			}
		}

	}

}
