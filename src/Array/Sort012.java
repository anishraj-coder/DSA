package Array;

import java.util.Arrays;
import java.util.Random;

public class Sort012 {
	public static void main(String[] args) {
		// Test case 1: Example from the problem statement
		int[] arr1 = { 0, 2, 1, 2, 0 };
		testSort012(arr1);

		// Test case 2: Example from the problem statement
		int[] arr2 = { 0, 1, 0 };
		testSort012(arr2);

		// Test case 3: Array with all 0s
		int[] arr3 = { 0, 0, 0, 0, 0 };
		testSort012(arr3);

		// Test case 4: Array with all 1s
		int[] arr4 = { 1, 1, 1, 1, 1 };
		testSort012(arr4);

		// Test case 5: Array with all 2s
		int[] arr5 = { 2, 2, 2, 2, 2 };
		testSort012(arr5);

		// Test case 6: Array with mixed values
		int[] arr6 = { 1, 0, 2, 1, 0, 2, 1, 0, 2 };
		testSort012(arr6);

		// Test case 7: Random array with large size
		int[] arr7 = generateRandomArray(1000000);
		testSort012(arr7);

		// Test case 8: Edge case - array with single element
		int[] arr8 = { 1 };
		testSort012(arr8);

		// Test case 9: Edge case - empty array
		int[] arr9 = {};
		testSort012(arr9);
	}

	private static void testSort012(int[] arr) {
		System.out.println("Input: " + Arrays.toString(arr));
		int[] sortedArr = arr.clone();
		sort(arr);
		System.out.println("Expected Output: " + Arrays.toString(sortedArr));
		System.out.println("--------------------");
	}

	public static void sort(int[] arr) {
		int pointer = 0;
		int i = 0;
		while (i < arr.length) {
			if (arr[i] == 0) {
				if (arr[pointer] != 0) {
					int t = arr[pointer];
					arr[pointer] = arr[i];
					arr[i] = t;
				}
				pointer++;
			} else {
				i++;
			}
		}
		i = 0;
		while (i < arr.length) {
			if (arr[i] == 1) {
				if (arr[pointer] != 1) {
					int t = arr[pointer];
					arr[pointer] = arr[i];
					arr[i] = t;
				}
				pointer--;
			} else {
				i++;
			}
		}
		i = 0;
		while (i < arr.length) {
			if (arr[i] == 2) {
				if (arr[pointer] != 2) {
					int t = arr[pointer];
					arr[pointer] = arr[i];
					arr[i] = t;
				}
				pointer--;
			} else {
				i++;
			}
		}
	}

	private static int[] generateRandomArray(int size) {
		int[] arr = new int[size];
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			arr[i] = random.nextInt(3);
		}
		return arr;
	}
}