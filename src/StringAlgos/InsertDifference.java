package StringAlgos;


/*Take as input String.

Write a code that inserts between each pair of characters the difference
between their ascii codes. Print the final string.

Note: You can use adding at the end of the string

Example:

Input:
abfk

output
a1b4f5k

1 = b-a
4 = f-b
5 = k-f
Input Format

String representing S.
Constraints

Size of String may be large.
Output Format

Print the string after inserting the difference of ASCII
Sample Input 0

abfk
Sample Output 0

a1b4f5k*/


public class InsertDifference {

    /**
     * This method takes a String `str` as input and returns a new String
     * with the difference between the ASCII codes of each character pair
     * inserted between the original characters.
     *
     * @param str The input String.
     * @return The modified String with character differences inserted.
     */
    public static String insertDifference(String str) {
        // Create a StringBuilder object to efficiently build the resulting string.
        StringBuilder ans = new StringBuilder();

        // Loop through each character in the input string except the last one.
        for (int i = 0; i < str.length() - 1; i++) {
            // Append the current character to the StringBuilder.
            ans.append(str.charAt(i));

            // Calculate the difference between the ASCII codes of the current and next characters.
            int difference = (int) (str.charAt(i + 1) - str.charAt(i));

            // Append the difference as an integer to the StringBuilder.
            ans.append(difference);
        }

        // Since the last character doesn't have a following pair, simply append it.
        ans.append(str.charAt(str.length() - 1));

        // Convert the StringBuilder object to a String and return it.
        return ans.toString();
    }

    public static void main(String[] args) {
        String str = "abfk";
        System.out.println(insertDifference(str)); // Output: a1b4f5k
    }
}

