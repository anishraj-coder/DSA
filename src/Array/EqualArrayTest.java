package Array;

import java.util.HashMap;

class EqualArraysTest {

	public static void main(String[] args) {
		// Test case 1
		int[] arr1 = { 1, 2, 5, 4, 0 };
		int[] arr2 = { 2, 4, 5, 0, 1 };
		System.out.println("Test Case 1");
		System.out.println("Array 1: " + arrayToString(arr1));
		System.out.println("Array 2: " + arrayToString(arr2));
		System.out.println("Are arrays equal? " + areArraysEqual(arr1, arr2));
		System.out.println("Expected Output: true\n");

		// Test case 2
		int[] arr1b = { 1, 2, 5 };
		int[] arr2b = { 2, 4, 15 };
		System.out.println("Test Case 2");
		System.out.println("Array 1: " + arrayToString(arr1b));
		System.out.println("Array 2: " + arrayToString(arr2b));
		System.out.println("Are arrays equal? " + areArraysEqual(arr1b, arr2b));
		System.out.println("Expected Output: false\n");

		// Test case 3
		int[] arr1c = { 1, 2, 2, 3 };
		int[] arr2c = { 3, 2, 2, 1 };
		System.out.println("Test Case 3");
		System.out.println("Array 1: " + arrayToString(arr1c));
		System.out.println("Array 2: " + arrayToString(arr2c));
		System.out.println("Are arrays equal? " + areArraysEqual(arr1c, arr2c));
		System.out.println("Expected Output: true\n");

		// Test case 4
		int[] arr1d = { 1, 2 };
		int[] arr2d = { 1, 2, 3 };
		System.out.println("Test Case 4");
		System.out.println("Array 1: " + arrayToString(arr1d));
		System.out.println("Array 2: " + arrayToString(arr2d));
		System.out.println("Are arrays equal? " + areArraysEqual(arr1d, arr2d));
		System.out.println("Expected Output: false\n");

		// Test case 5
		int[] arr1e = {};
		int[] arr2e = {};
		System.out.println("Test Case 5");
		System.out.println("Array 1: " + arrayToString(arr1e));
		System.out.println("Array 2: " + arrayToString(arr2e));
		System.out.println("Are arrays equal? " + areArraysEqual(arr1e, arr2e));
		System.out.println("Expected Output: true\n");

		// Test case 6
		int[] arr1f = { 1, 1, 2, 2 };
		int[] arr2f = { 2, 2, 1, 1 };
		System.out.println("Test Case 6");
		System.out.println("Array 1: " + arrayToString(arr1f));
		System.out.println("Array 2: " + arrayToString(arr2f));
		System.out.println("Are arrays equal? " + areArraysEqual(arr1f, arr2f));
		System.out.println("Expected Output: true\n");

		// Test case 7
		int[] arr1g = { 1, 2, 3 };
		int[] arr2g = { 3, 3, 1 };
		System.out.println("Test Case 7");
		System.out.println("Array 1: " + arrayToString(arr1g));
		System.out.println("Array 2: " + arrayToString(arr2g));
		System.out.println("Are arrays equal? " + areArraysEqual(arr1g, arr2g));
		System.out.println("Expected Output: false\n");

		// Test case 8
		int[] arr1h = { 5, 5, 5, 6 };
		int[] arr2h = { 5, 6, 5, 5 };
		System.out.println("Test Case 8");
		System.out.println("Array 1: " + arrayToString(arr1h));
		System.out.println("Array 2: " + arrayToString(arr2h));
		System.out.println("Are arrays equal? " + areArraysEqual(arr1h, arr2h));
		System.out.println("Expected Output: true\n");

		// Test case 9
		int[] arr1i = { 7, 8, 9 };
		int[] arr2i = { 7, 8, 10 };
		System.out.println("Test Case 9");
		System.out.println("Array 1: " + arrayToString(arr1i));
		System.out.println("Array 2: " + arrayToString(arr2i));
		System.out.println("Are arrays equal? " + areArraysEqual(arr1i, arr2i));
		System.out.println("Expected Output: false\n");

		// Test case 10
		int[] arr1j = { 1000000000, 1000000000, 1 };
		int[] arr2j = { 1, 1000000000, 1000000000 };
		System.out.println("Test Case 10");
		System.out.println("Array 1: " + arrayToString(arr1j));
		System.out.println("Array 2: " + arrayToString(arr2j));
		System.out.println("Are arrays equal? " + areArraysEqual(arr1j, arr2j));
		System.out.println("Expected Output: true\n");
	}

	private static boolean areArraysEqual(int[] arr1, int[] arr2) {
		if (arr1.length != arr2.length) {
			return false;
		}
		HashMap<Integer, Integer> hm = new HashMap<>();
		for (int i = 0; i < arr1.length; i++) {
			if (hm.containsKey(arr1[i])) {
				hm.put(arr1[i], hm.get(arr1[i]) + 1);
			} else {
				hm.put(arr1[i], 1);
			}
		}
		for (int i = 0; i < arr2.length; i++) {
			if (hm.containsKey(arr2[i])) {
				if (hm.get(arr2[i]) == 1) {
					hm.remove(arr2[i]);
				} else {
					hm.put(arr2[i], hm.get(arr2[i]) - 1);
				}
			} else {
				return false;
			}
		}
		return hm.isEmpty();
	}

	// Utility method to convert array to a readable string format
	public static String arrayToString(int[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if (i < array.length - 1) {
				sb.append(", ");
			}
		}
		sb.append(']');
		return sb.toString();
	}

	// Placeholder method for checking if arrays are equal

}
