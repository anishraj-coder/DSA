package Recursion;

/*1.Take as input String.

a. Write a recursive function which counts, 
the number of times “aaa”
appears in the string. Print the value returned.

b. Write a recursive function which counts, the number 
of times “aaa”
appears in the string,
 but only such instances of “aaa” should be
considered which do not overlap.
 Print the value returned.

Example(To be used only for expected output):

Input:
aaaaaa

output
4
2
Input Format

String representing S.
Constraints

Size of String may be large.
Output Format

print the Output as explained in Description.
Sample Input 0

aaaaaa
Sample Output 0

4
2*/

public class Countaaa {
	public static int count1(String str, int index) {
		// Base case: If the index reaches the end of the String (or beyond the second
		// last character), return 0 (no more "aaa" to check)
		if (index >= str.length() - 2) {
			return 0;
		}

		// Make a recursive call to continue checking the String from the next character
		int remainingCount = count1(str, index + 1);

		// Check if the current character sequence is "aaa"
		if (str.charAt(index) == 'a' && str.charAt(index + 1) == 'a' && str.charAt(index + 2) == 'a') {
			// If "aaa" is found, increment the count by 1 and return the combined count
			return remainingCount + 1;
		} else {
			// If not "aaa", return the remaining count without incrementing
			return remainingCount;
		}
	}

	public static int count2(String str, int index) {
		// Base case: If the index reaches the end of the String (or beyond the second
		// last character), return 0 (no more "aaa" to check)
		if (index >= str.length() - 2) {
			return 0;
		}

		// Check if the current character sequence is "aaa"
		if (str.charAt(index) == 'a' && str.charAt(index + 1) == 'a' && str.charAt(index + 2) == 'a') {
			// If "aaa" is found, make a recursive call with index advanced by 3 (to skip
			// the counted "aaa")
			return 1 + count2(str, index + 3); // Count this "aaa" and check for more from the skipped position
		} else {
			// If not "aaa", make a recursive call with the next character
			return count2(str, index + 1);
		}
	}

	public static void main(String[] args) {
		String s = "aaaaaa";
		System.out.println(count1(s, 0));
		System.out.println(count2(s, 0));
	}

}
