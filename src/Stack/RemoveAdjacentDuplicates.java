package Stack;

import java.util.Stack;

/**
 * Problem: Remove Adjacent Duplicates
 * 
 * Question: Given a string S, remove equal pairs of adjacent characters and
 * return the final string.
 * 
 * Input: A single line containing the string Constraints: 1 <= S.length() <=
 * 10^5 Output: Print the String after removing all the adjacent same characters
 * 
 * Sample Input: abbbbd Sample Output: ad
 * 
 * Ideology and Method: 1. Stack-based Approach: - Use a stack to keep track of
 * characters as we traverse the string. - The stack will help in efficiently
 * handling the removal of adjacent duplicates.
 * 
 * 2. Iterative Processing: - Iterate through each character of the input
 * string. - For each character, compare it with the top of the stack (if the
 * stack is not empty).
 * 
 * 3. Duplicate Removal Logic: - If the current character matches the top of the
 * stack, it forms an adjacent duplicate pair. - In this case, pop the top
 * character from the stack (effectively removing the pair). - If it doesn't
 * match, push the current character onto the stack.
 * 
 * 4. Result Construction: - After processing all characters, the stack contains
 * the characters of the final string, but in reverse order. - Pop all
 * characters from the stack and construct the final string.
 * 
 * Time Complexity: O(n), where n is the length of the input string. Space
 * Complexity: O(n) in the worst case, where no duplicates are removed.
 */
public class RemoveAdjacentDuplicates {
	public static void main(String[] args) {
		String s = "acbbbbced";
		System.out.println(removeAdjacentDuplicates(s));
	}

	private static String removeAdjacentDuplicates(String s) {
		// Create a stack to store characters
		Stack<Character> st = new Stack<>();

		// Iterate through each character in the input string
		for (char ch : s.toCharArray()) {
			if (st.isEmpty() || st.peek() != ch) {
				// If stack is empty or current character is different from the top of the
				// stack,
				// push the current character onto the stack
				st.push(ch);
			} else if (st.peek() == ch) {
				// If current character matches the top of the stack,
				// pop the top character (removing the adjacent duplicate)
				st.pop();
			}
		}

		// Construct the final string from the stack
		char[] ch = new char[st.size()];
		for (int i = st.size() - 1; i >= 0; i--) {
			// Pop characters from the stack and place them in reverse order
			ch[i] = st.pop();
		}

		// Convert the character array to a string and return
		return String.valueOf(ch);
	}
}