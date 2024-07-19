package Queue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*Question:
 * First non-repeating character in a stream
Difficulty: MediumAccuracy: 31.65%Submissions: 203K+Points: 4
Given an input stream A of n characters consisting only of lower case alphabets.
 While reading characters from the stream, you have to tell which character has 
 appeared only once in the stream upto that point. If there are many characters 
 that have appeared only once, you have to tell which one of them was the first one to appear.
  If there is no such character then append '#' to the answer.

NOTE:
1. You need to find the answer for every i (0 <= i < n)
2. In order to find the solution for every i you need to consider the string from starting position till ith position.
 

Example 1:

Input: A = "aabc"
Output: "a#bb"
Explanation: For every ith character we will
consider the string from index 0 till index i first non
repeating character is as follow-
"a" - first non-repeating character is 'a'
"aa" - no non-repeating character so '#'
"aab" - first non-repeating character is 'b'
"aabc" - there are two non repeating characters 'b' and 'c', 
first non-repeating character is 'b' because 'b' comes before
'c' in the stream.
Example 2:

Input: A = "zz"
Output: "z#"
Explanation: For every character first non
repeating character is as follow-
"z" - first non-repeating character is 'z'
"zz" - no non-repeating character so '#'
 

Your Task:
You don't need to read or print anything. Your task is to complete the function 
FirstNonRepeating() which takes A as input parameter and returns a string after processing the input stream.
 

Expected Time Complexity: O(n)
Expected Space Complexity: O(n)*/

/*
First non-repeating character in a stream

Ideology and Method:
This problem requires finding the first non-repeating character in a stream of characters for each position in the input string.
The challenge lies in efficiently tracking characters and their frequencies while maintaining their order of appearance.

The solution employs a clever combination of two data structures:

1. Queue:
   - Purpose: Maintains the order of characters as they appear in the stream.
   - Advantage: Allows quick access to the earliest occurring character.
   - Operation: Characters are added to the rear as they're encountered in the stream.

2. HashMap:
   - Purpose: Keeps a count of each character's occurrences.
   - Advantage: Provides constant-time lookup and update of character frequencies.
   - Operation: Character counts are incremented as they're processed in the stream.

Process Flow:
1. Initialization:
   - Create an empty queue and HashMap.
   - Prepare a char array to store the result for each position.

2. Stream Processing (for each character):
   a. Enqueue the current character.
   b. Update its frequency in the HashMap.
   c. Cleanup: Remove characters from the front of the queue if their count exceeds 1.
      This step ensures that the queue only contains potential non-repeating characters.
   d. Result Determination:
      - If the queue is not empty, the front character is the first non-repeating character.
      - If the queue is empty, no non-repeating character exists at this point, so use '#'.

3. Final Output:
   Convert the result array to a string, representing the first non-repeating character (or '#') for each position.

Key Insights:
- The queue acts as a filter, maintaining only the characters that are currently non-repeating and in order.
- The HashMap allows for quick frequency checks, crucial for the queue cleanup step.
- By processing each character once and using efficient data structures, we achieve O(n) time complexity.
- The space usage is O(n), primarily due to the potential size of the queue and HashMap in worst-case scenarios.

This approach elegantly balances the need for maintaining order (queue) with the need for quick frequency lookups (HashMap),
resulting in an efficient solution to this stream processing problem.
*/

public class FirstNon_RepeatingCharacter {
	public static String firstNonRepeatingCharacter(String A) {
		// Initialize a queue to maintain order of characters
		Queue<Character> q = new LinkedList<>();
		// HashMap to store character frequencies
		HashMap<Character, Integer> count = new HashMap<>();
		// Array to store the result for each position
		char[] ans = new char[A.length()];

		for (int i = 0; i < A.length(); i++) {
			char c = A.charAt(i);

			// Add current character to the queue
			q.add(c);

			// Update character count in HashMap
			count.put(c, count.getOrDefault(c, 0) + 1);

			// Remove characters from queue front if they are repeating
			while (!q.isEmpty() && count.get(q.peek()) > 1) {
				q.remove();
			}

			// Set the answer for current position
			if (!q.isEmpty()) {
				ans[i] = q.peek(); // First non-repeating character
			} else {
				ans[i] = '#'; // No non-repeating character found
			}
		}

		// Convert char array to String and return
		return String.valueOf(ans);
	}

	public static void main(String[] args) {
		// Test cases
		System.out.println(firstNonRepeatingCharacter("aabc"));
		System.out.println(firstNonRepeatingCharacter("zz"));
	}
}