package Array;

public class view2DArray {

    /**
     * Prints the elements of a 2D integer array row-wise.
     *
     * @param arr The 2D integer array to be printed.
     */
    public static void printRowWise(int[][] arr) {
        // Outer loop iterates through each row of the array
        for (int i = 0; i < arr.length; i++) {
            // Inner loop iterates through each element in the current row
            for (int j = 0; j < arr[0].length; j++) {
                // Print the current element followed by a space
                System.out.print(arr[i][j] + " ");
            }
            // Move to the next line after printing all elements in a row
            System.out.println();
        }
    }

    /**
     * Prints the elements of a 2D integer array column-wise.
     *
     * @param arr The 2D integer array to be printed.
     */
    public static void printColWise(int[][] arr) {
        // Outer loop iterates through each column of the array
        for (int i = 0; i < arr[0].length; i++) {
            // Inner loop iterates through each element in the current column
            for (int j = 0; j < arr.length; j++) {
                // Print the element at the current column and row index followed by a space
                System.out.print(arr[j][i] + " ");
            }
            // Move to the next line after printing all elements in a column
            System.out.println();
        }
    }

    /**
     * Prints the elements of a 2D integer array in a zig-zag pattern.
     *
     * @param arr The 2D integer array to be printed.
     */
    public static void printZigZag(int[][] arr) {
        // Outer loop iterates through each row of the array
        for (int i = 0; i < arr.length; i++) {
            // Check if the current row is even-numbered
            if (i % 2 == 0) {
                // Print elements in normal order for even rows
                for (int j = 0; j < arr[0].length; j++) {
                    System.out.print(arr[i][j] + " ");
                }
            } else {
                // Print elements in reverse order for odd rows
                for (int j = arr.length - 1; j >= 0; j--) {
                    System.out.print(arr[i][j] + " ");
                }
            }
            // Move to the next line after printing all elements in a row
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Row Wise");
        printRowWise(arr);

        System.out.println("Col Wise:");
        printColWise(arr);

        System.out.println("Zig Zag:");
        printZigZag(arr);
    }
}
