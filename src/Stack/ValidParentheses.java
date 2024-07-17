package Stack;

import java.util.Stack;

/**
 * Valid Parentheses Problem
 * 
 * Question: Given a string s containing just the characters '(', ')', '{', '}',
 * '[' and ']', determine if the input string is valid.
 * 
 * An input string is valid if: 1. Open brackets must be closed by the same type
 * of brackets. 2. Open brackets must be closed in the correct order. 3. Every
 * close bracket has a corresponding open bracket of the same type.
 * 
 * Examples: Input: s = "()" Output: true Input: s = "()[]{}" Output: true
 * Input: s = "(]" Output: false
 * 
 * Ideology and Method: 1. Stack-based Approach: - Use a stack to keep track of
 * opening brackets as we traverse the string. - The stack helps in maintaining
 * the order and matching of brackets.
 * 
 * 2. Bracket Matching: - When an opening bracket is encountered, push it onto
 * the stack. - When a closing bracket is encountered, check if it matches the
 * top of the stack. - If it matches, pop the top element; if not, the string is
 * invalid.
 * 
 * 3. Validation Logic: - If the stack is empty at the end, all brackets were
 * properly closed. - If the stack is not empty, there were unmatched opening
 * brackets.
 * 
 * 4. Error Handling: - Handle cases where a closing bracket is encountered when
 * the stack is empty. - Ensure that each closing bracket matches the most
 * recent opening bracket.
 * 
 * Time Complexity: O(n), where n is the length of the input string. Space
 * Complexity: O(n) in the worst case, where all characters are opening
 * brackets.
 */
public class ValidParentheses {
	public static boolean check(String s) {
		Stack<Character> st = new Stack<>();

		// Iterate through each character in the string
		for (char ch : s.toCharArray()) {
			// If stack is empty or character is an opening bracket, push to stack
			if (st.isEmpty() || ch == '{' || ch == '(' || ch == '[') {
				st.push(ch);
			} else {
				// Handle closing brackets
				if (ch == '}') {
					// Check if the corresponding opening bracket is at the top of the stack
					if (!st.isEmpty() && st.peek() == '{')
						st.pop();
					else
						return false; // Mismatched or no corresponding opening bracket
				} else if (ch == ']') {
					if (!st.isEmpty() && st.peek() == '[')
						st.pop();
					else
						return false;
				} else if (ch == ')') {
					if (!st.isEmpty() && st.peek() == '(')
						st.pop();
					else
						return false;
				}
			}
		}

		// If stack is empty, all brackets were matched and closed properly
		return st.isEmpty();
	}

	public static void main(String[] args) {
		String s = "()[]{}";
		System.out.println(check(s));
	}
}