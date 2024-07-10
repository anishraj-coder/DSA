package Recursion;

public class IsPalindrome {
	public static boolean isPalindrome(char[] str, int startIndex, int endIndex) {
		// Base case: If the starting index is greater than or equal to the ending
		// index,
		// it means we've reached the middle or end of the String (considering character
		// positions).
		// A String with zero or one character is considered a palindrome.
		if (startIndex >= endIndex) {
			return true;
		}

		// Check if the characters at the starting and ending positions are the same
		// (case-insensitive)
		if ((str[startIndex]) == (str[endIndex])) {
			// If they are the same, move closer to the middle by recursively checking the
			// remaining part of the String
			return isPalindrome(str, startIndex + 1, endIndex - 1);
		} else {
			// If the characters are not the same, the String is not a palindrome
			return false;
		}
	}

	public static void main(String[] args) {
		String s = "Malayalam";
		s = s.toLowerCase();
		System.out.println(isPalindrome(s.toCharArray(), 0, s.length() - 1));

	}

}
