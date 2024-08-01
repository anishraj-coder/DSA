package Array;

import java.util.Arrays;
/*Maximize Distance to Closest Person
Solved
Medium
Topics
Companies
You are given an array representing a row of seats where seats[i] = 1 
represents a person sitting in the ith seat, and seats[i] = 0 represents that the ith seat is empty (0-indexed).

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and
 the closest person to him is maximized. 

Return that maximum distance to the closest person.

 

Example 1:


Input: seats = [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
Example 2:

Input: seats = [1,0,0,0]
Output: 3
Explanation: 
If Alex sits in the last seat (i.e. seats[3]), the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
Example 3:

Input: seats = [0,1]
Output: 1*/
/*
Problem Analysis:
The problem "Maximize Distance to Closest Person" presents us with a row of seats, represented as an array.
Each seat is either occupied (1) or empty (0). The goal is to find the empty seat that maximizes the distance
to the nearest occupied seat. This problem has real-world applications, such as social distancing in public spaces.

Key Considerations:
1. The array always contains at least one empty seat and one occupied seat.
2. The distance is calculated as the number of empty seats between two people, plus one.
3. We need to consider three distinct scenarios:
   a) Empty seats between two occupied seats
   b) Empty seats at the beginning of the row (before the first person)
   c) Empty seats at the end of the row (after the last person)

Intuition and Approach:
1. For seats between two people:
   - We need to find the longest consecutive sequence of empty seats.
   - The optimal seat will be in the middle of this sequence.
   - The distance to the closest person will be half of this sequence length (rounded up).
   - We can keep track of the current sequence of empty seats (k) and update our answer as we go.

2. For seats at the beginning:
   - Count the number of empty seats until we encounter the first person.
   - This count directly represents the distance for the leftmost empty seat.

3. For seats at the end:
   - Count the number of empty seats after the last person.
   - This count directly represents the distance for the rightmost empty seat.

4. The maximum of these three scenarios will be our final answer.

Detailed Solution Approach:
1. Initialize variables:
   - ans: to store the maximum distance
   - k: to count consecutive empty seats

2. First pass through the array:
   - Iterate through each seat.
   - If the seat is occupied (1), reset k to 0.
   - If the seat is empty (0), increment k.
   - Update ans with max(ans, (k+1)/2) for each k.
     (The (k+1)/2 handles both odd and even sequences)

3. Second pass for the beginning of the row:
   - Count empty seats until the first occupied seat.
   - Update ans if this count is larger.

4. Third pass for the end of the row:
   - Count empty seats from the end until the last occupied seat.
   - Update ans if this count is larger.

5. Return the final value of ans.

Time and Space Complexity:
- Time Complexity: O(n), where n is the length of the seats array. We make three passes through the array.
- Space Complexity: O(1), as we only use a constant amount of extra space regardless of input size.

Edge Cases and Considerations:
- Array with only two elements: Handle correctly for [0,1] and [1,0].
- All seats empty except one: Important to check both ends of the array.
- Alternating pattern of occupied and empty seats: Should return 1.
- Large gaps at the beginning or end: These might be the optimal solution.

Key Insights:
- The division by 2 for middle seats is crucial because Alex can sit in the middle of empty sequences.
- Checking the ends separately is necessary because these distances don't need to be divided by 2.
- The solution efficiently handles all cases in a single pass, with two additional checks for the ends.

This approach provides a robust and efficient solution to the problem, covering all possible seating arrangements
and finding the optimal seat for maximizing distance to the closest person.
*/

/*
Visualization of the Solution and Possible Cases:

1. General Case:
   [1, 0, 0, 0, 1, 0, 1]
    ^     ^     ^   ^
    |     |     |   |
    Person|     |   Person
          |     Person
          Optimal Seat (distance = 2)

   k: 0 -> 1 -> 2 -> 3 -> 0 -> 1 -> 0
   ans: 0 -> 1 -> 1 -> 2 -> 2 -> 2 -> 2


Correctly Revised Cases 2 and 3:

2. Empty Seats at the Beginning:
   [0, 0, 0, 1, 0, 1]
    ^        ^   ^
    |        |   |
    |        |   Person
    |        Person
    Optimal Seat (distance = 3)

   Explanation:
   - The first person is at index 3.
   - The optimal seat is at the very beginning (index 0).
   - The distance is 3 (from index 0 to 3).
   - This is handled by the special check for the start of the array.
   
   Algorithm step:
   distance = index of first person = 3
   ans = max(ans, distance) = max(1, 3) = 3

3. Empty Seats at the End:
   [1, 0, 1, 0, 0, 0]
    ^   ^        ^
    |   |        |
    |   Person   Optimal Seat (distance = 3)
    Person       

   Explanation:
   - The last person is at index 2.
   - The optimal seat is at the very end (index 5).
   - The distance is 3 (from index 5 back to 2).
   - This is handled by the special check for the end of the array.
   
   Algorithm step:
   distance = length of array - 1 - index of last person = 5 - 2 = 3
   ans = max(ans, distance) = max(1, 3) = 3

Key Points:
1. For seats at the beginning, we count from the start to the first person.
2. For seats at the end, we count from the last person to the end.
3. These counts are not divided by 2, unlike the gaps between occupied seats.
4. The algorithm needs separate checks for the start and end of the array.
5. The final answer is the maximum of:
   a) The largest gap between occupied seats, divided by 2 (rounded up)
   b) The distance from the start to the first person
   c) The distance from the last person to the end

This corrected explanation accurately represents how the algorithm handles
the special cases of empty seats at the beginning and end of the row.


4. Alternating Pattern:
   [1, 0, 1, 0, 1, 0, 1]
      ^   ^   ^   ^
      |   |   |   |
      All seats equidistant (distance = 1)

   k: 0 -> 1 -> 0 -> 1 -> 0 -> 1 -> 0
   ans: 0 -> 1 -> 1 -> 1 -> 1 -> 1 -> 1

5. Large Gap in the Middle:
   [1, 0, 0, 0, 0, 0, 1]
      ^     ^     ^
      |     |     |
      |  Optimal  |
      |   Seat    |
      Person    Person

   k: 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 0
   ans: 0 -> 1 -> 1 -> 2 -> 2 -> 3 -> 3

6. Single Person at Start:
   [1, 0, 0, 0, 0]
    ^        ^
    |        |
    Person   Optimal Seat (distance = 4)

   Special check for end: count = 4
   ans = max(2, 4) = 4

7. Single Person at End:
   [0, 0, 0, 0, 1]
    ^        ^
    |        |
    Optimal  Person
    Seat (distance = 4)

   Special check for start: count = 4
   ans = max(2, 4) = 4

8. Multiple Equal Gaps:
   [1, 0, 0, 1, 0, 0, 1]
      ^  ^     ^  ^
      |  |     |  |
      |  |     |  Optimal Seats
      |  |     | (distance = 2)
      |  |     Person
      |  Optimal Seat
      Person

   k: 0 -> 1 -> 2 -> 0 -> 1 -> 2 -> 0
   ans: 0 -> 1 -> 1 -> 1 -> 1 -> 2 -> 2

These visualizations cover various scenarios the algorithm needs to handle:
- General case with optimal seat between two people
- Empty seats at the beginning and end of the row
- Alternating pattern of occupied and empty seats
- Large gaps in the middle
- Single person at the start or end
- Multiple equal optimal gaps

The algorithm efficiently handles all these cases by:
1. Tracking the maximum gap between occupied seats (div*/

public class MaximumDistanceToClosestPerson {
	public static void main(String[] args) {
		testCase(new int[] { 1, 0, 0, 0, 1, 0, 1 }, 2, "Basic case with people at ends");
		testCase(new int[] { 1, 0, 0, 0 }, 3, "Person only at the beginning");
		testCase(new int[] { 0, 0, 0, 1 }, 3, "Person only at the end");
		testCase(new int[] { 1, 0, 1, 0, 1, 0, 1 }, 1, "Alternating seats");
		testCase(new int[] { 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 }, 3, "Longer sequence");
		testCase(new int[] { 1, 1, 0, 0, 0, 1, 1 }, 2, "Multiple people at ends");
		testCase(new int[] { 0, 1 }, 1, "Minimum length array");
		testCase(new int[] { 1, 0 }, 1, "Minimum length array, reversed");
		testCase(new int[] { 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, 3, "Equal spaces at start and between");
		testCase(new int[] { 1, 0, 0, 1, 0, 0, 1 }, 1, "Evenly spaced people");
		testCase(new int[] { 0, 0, 0, 0, 0, 1 }, 5, "Single person at end, long empty space");
		testCase(new int[] { 1, 0, 0, 0, 0, 0 }, 5, "Single person at start, long empty space");
		testCase(new int[] { 0, 1, 0, 1, 0, 1, 0 }, 1, "Alternating with empty ends");
		testCase(new int[] { 1, 1, 1, 0, 1, 1, 1 }, 1, "Single empty seat");
		testCase(new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 4, "Two people with large gap");
		testCase(new int[] { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0 }, 3, "Multiple gaps of different sizes");
		testCase(new int[] { 0, 0, 1, 0, 0, 0, 1, 0, 0 }, 2, "People in middle, empty ends");
		testCase(new int[] { 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1 }, 3, "Large group, then gap, then large group");
		testCase(new int[] { 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, 2, "Evenly spaced with empty ends");
		testCase(new int[] { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 }, 2, "Multiple equal max distances");
	}

	private static void testCase(int[] seats, int expected, String description) {
		System.out.println("Test Case: " + description);
		System.out.println("Input: " + Arrays.toString(seats));
		System.out.println("Expected Output: " + expected);
		int result = maxDistToClosest(seats);
		System.out.println("Actual Output: " + result);
		System.out.println("Result: " + (result == expected ? "PASS" : "FAIL"));
		System.out.println();
	}

	// This is a placeholder for the actual method you'll implement
	public static int maxDistToClosest(int[] seats) {

		int k = 0;
		int ans = 0;

		int n = seats.length;
		for (int i = 0; i < n; i++) {
			if (seats[i] == 1) {
				k = 0;

			} else {
				k++;
			}
			if (k > ans) {
				ans = k;

			}
		}
		ans = (ans + 1) / 2;

		int dist = 0;
		for (int i = 0; i < n; i++) {
			if (seats[i] == 1) {
				dist = i;
				break;
			}

		}
		ans = Math.max(ans, dist);
		dist = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (seats[i] == 1) {
				break;
			} else {
				dist++;
			}
		}
		ans = Math.max(ans, dist);
		return ans; // Placeholder return
	}
}