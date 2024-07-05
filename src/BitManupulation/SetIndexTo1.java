package BitManupulation;

/*Given a number n and a value k. From the right, set the kth bit in the
binary representation of n. The position of LSB(or last bit) is 0, second last
bit is 1 and so on.

Take the following as input.
1. A number: N
2. a Value: K

let us assume 'res' is desired answer.

For Example:
Input :
N = 20
K = 3

Output :
28

ExplanationL: 20 in binary Number: 10100, 
After turning on the thirst bit it will become 11100 
which is 28 in decimal form.
Input Format

Integer representing N
Integer representing K
Constraints

1 <= N <= 10 ^ 9
Output Format

Print the number after turning on the Kth Bit
Sample Input 0

20
3
Sample Output 0

28*/

/**
 * This class defines a method `setIndexTo1` that sets the kth bit (from the right)
 * to 1 in the binary representation of a given number n.
 */
public class SetIndexTo1 {

    public static int setIndexTo1(int n, int k) {
        // Explanation:
        // 1. Left shift 1 by k positions to get a number with a 1 at the kth bit from the right.
        //    - Example: k = 3, so 1 << 3 gives us 00100 (binary representation).
        // 2. Perform bitwise OR (|) between n and the shifted 1.
        //    - OR operation sets bits to 1 if either operand has a 1 at that position.
        //    - In this case, ORing n with the shifted 1 ensures the kth bit is set to 1
        //      while preserving other bits in n.
        return n | (1 << k);
    }

    public static void main(String[] args) {
        int n = 20; // 10100 in binary
        int k = 3;
        System.out.println(setIndexTo1(n, k)); // Output: 28 (11100 in binary)
    }
}
