package BitManupulation;

/**
 * This class defines a `main` method that checks if a number is even or odd
 * using bitwise AND operation.
 */
public class OddEven {

    public static void main(String[] args) {
        // Declare an integer variable 'n' and assign a value (e.g., 5).
        int n = 5;

        // Check if the least significant bit (LSB) of 'n' is 0 using bitwise AND (&) with 1.
        // - Even numbers have a 0 in their LSB, while odd numbers have a 1.
        if ((n & 1) == 0) {
            System.out.println("Even");
        } else {
            System.out.println("Odd");
        }
    }
}
