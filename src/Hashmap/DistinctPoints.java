package Hashmap;

import java.util.HashSet;

/*Given N 2D Points, Calculate no. of distinct points among them.

Ex: x[5] = {2, 1, 3, 2, 2}
    y[5] = {3, 1, 2, 3, 4}
    
The first array represents the x co-ordinates,
 the second array represents the y co-ordinate.
  Acoording to above examples the points are 
    (x[0],y[0])->(2,3)
    (x[1],y[1])->(1,1)
    (x[2],y[2])->(3,2)
    (x[3],y[3])->(2,3)
    (x[4],y[4])->(2,4)
    
Total number of distinct points are 4.
Input Format

The first line contains an Integer n denoting number of points.
Second line contains n integer denoting the x-coordinates.
Third line contains another n integer denoting the y-coordinates.
Constraints

1<=n<=10^5
Output Format

Output the distinct points count
Sample Input 0

5
2 1 3 2 2
3 1 2 3 4
Sample Output 0

4*/

public class DistinctPoints {

	public static void main(String[] args) {
		// Sample number of points (replace with user input if needed)
		int numPoints = 5;

		// Sample X-coordinates (replace with user input if needed)
		int[] xCoordinates = { 2, 1, 3, 2, 2 };

		// Sample Y-coordinates (replace with user input if needed)
		int[] yCoordinates = { 3, 1, 2, 3, 4 };

		// HashSet to store unique combinations of (X, Y) coordinates representing
		// points
		HashSet<String> distinctPoints = new HashSet<>();

		// Build a set of unique string representations of points
		for (int i = 0; i < numPoints; i++) {
			int x = xCoordinates[i];
			int y = yCoordinates[i];

			// Combine X and Y coordinates into a single String for the HashSet
			String point = x + " " + y;

			// Add the point representation to the HashSet.
			// If the point already exists, the add() method returns false and the set
			// remains unchanged.
			distinctPoints.add(point);
		}

		// The size of the HashSet containing unique points represents the number of
		// distinct points
		System.out.println("Number of distinct points: " + distinctPoints.size());
	}
}
