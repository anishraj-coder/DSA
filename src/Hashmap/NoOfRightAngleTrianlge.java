package Hashmap;

import java.util.HashMap;


/*Given an array arr[] of N distinct integers points on the 2D Plane. 
 * The task is to count the number
 *  of Right-Angled Triangle from N points such that the base
 *   or perpendicular is parallel to the X or Y-axis.
Input Format

The first line contains number of coordinates N.
Next N lines contains pair  "X Y" denoting coordinates
Constraints

1<=n<=10^5
1<=X,Y<=10^5
Output Format

Print number of triangles.
Sample Input 0

3
4 2
2 1
1 3
Sample Output 0

0*/


/**
 * This class finds the number of right-angled triangles formed from points on a 2D plane,
 * considering only triangles where the base or perpendicular is parallel to the X or Y-axis.
 */
public class NoOfRightAngleTrianlge {
		// Solution video: https://youtu.be/219ae5GyKJA

    public static void main(String[] args) {
        // Sample points (replace with user input if needed)
        int[][] points = {{-3, 2}, {-2, 2}, {1, 2}, {3, 2}, {5, 2}, {6, 2},
                {3, 3}, {3, 5}, {3, -1}, {3, -3}, {5, -1}, {5, 4}};

        // HashMaps to store frequencies of X and Y coordinates
        HashMap<Integer, Integer> xCoordinates = new HashMap<>();
        HashMap<Integer, Integer> yCoordinates = new HashMap<>();

        // Build frequency maps for X and Y coordinates
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];

            if (xCoordinates.containsKey(x)) {
                xCoordinates.put(x, xCoordinates.get(x) + 1); // Increment X-coordinate frequency
            } else {
                xCoordinates.put(x, 1); // Add new X-coordinate with frequency 1
            }

            if (yCoordinates.containsKey(y)) {
                yCoordinates.put(y, yCoordinates.get(y) + 1); // Increment Y-coordinate frequency
            } else {
                yCoordinates.put(y, 1); // Add new Y-coordinate with frequency 1
            }
        }

        // Print X-coordinate frequencies for debugging purposes (optional)
        System.out.println("X-coordinate Frequencies:");
        for (int key : xCoordinates.keySet()) {
            System.out.println(key + " -> " + xCoordinates.get(key));
        }

        System.out.println("Y-coordinate Frequencies:");
        for (int key : yCoordinates.keySet()) {
            System.out.println(key + " -> " + yCoordinates.get(key));
        }

        // Count valid triangles (considering the correction - not implemented here)
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];

            // Check if the current point forms a valid triangle with points having the same X or Y coordinate
            // **Correction:** Subtract 1 only from the frequency of the current point's coordinate
            // (because the current point itself cannot form a triangle with itself). (Not implemented here)
            if (xCoordinates.get(x) >= 1 && yCoordinates.get(y) >= 1) {
                // **Correction needed:** Subtract 1 from frequencies before multiplication (not implemented here)
                count += (xCoordinates.get(x) - 1) * (yCoordinates.get(y) - 1);
            }
        }

        System.out.println("Number of right-angled triangles: " + count); // This calculation might be incorrect without the correction
    }
}

