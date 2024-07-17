package Stack;

import java.util.Stack;

/*
Asteroid Collision

Problem Explanation:
Imagine a row of asteroids in space. Each asteroid has a size (represented by a positive integer)
and a direction (positive for moving right, negative for moving left). When two asteroids collide:
- The smaller one explodes
- If they're the same size, both explode
- Asteroids moving in the same direction never collide

Our task is to determine which asteroids remain after all possible collisions occur.

Detailed Solution Approach:

1. Visualization:
   Think of the asteroids as a line of objects moving. Right-moving asteroids can only collide
   with left-moving asteroids that come after them.

2. Using a Stack:
   We use a stack to keep track of asteroids that haven't collided yet. This works well because
   we only need to consider collisions with the most recently added asteroid.

3. Processing Asteroids:
   We go through each asteroid from left to right:
   
   a) For right-moving asteroids (positive values):
      - Always add them to the stack. They can't collide with anything to their left.
   
   b) For left-moving asteroids (negative values):
      - Check if it will collide with the asteroid at the top of the stack.
      - Keep comparing and potentially removing asteroids from the stack until:
        * The stack is empty (no more collisions possible)
        * We meet a left-moving asteroid (they won't collide)
        * The current asteroid explodes (stack top is larger)
        * Both asteroids explode (same size)

4. Collision Scenarios:
   - If stack top < |current|: Stack top explodes, continue checking with next in stack
   - If stack top = |current|: Both explode, move to next asteroid
   - If stack top > |current|: Current asteroid explodes, move to next asteroid
   - If no collision: Add current asteroid to stack

5. Final Result:
   After processing all asteroids, the stack contains the surviving asteroids in reverse order.
   We pop them out to get the final array in the correct order.

This approach efficiently simulates the collision process in a single pass through the asteroid array,
handling all possible collision scenarios.
*/

public class AsteroidCollision {
	public static int[] asteroidCollision(int[] asteroids) {
		if (asteroids.length == 0)
			return new int[0];

		int i = 0;
		Stack<Integer> st = new Stack<>();

		while (i < asteroids.length) {
			if (asteroids[i] < 0) { // Left-moving asteroid
				// Handle potential collisions
				while (!st.isEmpty() && st.peek() > 0 && st.peek() < Math.abs(asteroids[i])) {
					st.pop(); // Smaller right-moving asteroids explode
				}

				if (st.isEmpty() || st.peek() < 0) {
					// No collision: stack empty or top moving left
					st.push(asteroids[i]);
					i++;
				} else if (st.peek() == Math.abs(asteroids[i])) {
					// Equal size: both explode
					st.pop();
					i++;
				} else if (st.peek() > Math.abs(asteroids[i])) {
					// Current asteroid explodes
					i++;
				}
				// If st.peek() < Math.abs(asteroids[i]), loop continues to next comparison
			} else {
				// Right-moving asteroid: always add to stack
				st.push(asteroids[i]);
				i++;
			}
		}

		// Convert stack to array (reverse order)
		int[] ans = new int[st.size()];
		for (int j = ans.length - 1; j >= 0; j--) {
			ans[j] = st.pop();
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] ques = { -2, -1, 1, 2 };
		int[] ans = asteroidCollision(ques);

		System.out.println("Input asteroids: ");
		printArray(ques);

		System.out.println("Asteroids after collision: ");
		printArray(ans);

		// Additional test cases
		int[][] testCases = { { 5, 10, -5 }, { 8, -8 }, { 10, 2, -5 }, { -2, -1, 1, 2 }, { -2, -2, 1, -2 } };

		for (int[] testCase : testCases) {
			System.out.println("\nTest case:");
			printArray(testCase);
			int[] result = asteroidCollision(testCase);
			System.out.println("Result:");
			printArray(result);
		}
	}

	// Helper method to print arrays
	private static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i < arr.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}
}