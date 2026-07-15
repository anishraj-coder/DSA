package stacks;

import java.util.*;

public class PrefixToInfix {

    /**
     * Converts a prefix expression to an infix expression.
     * Iterates from right to left using a Stack of Strings.
     * Constraints: 3 <= S.length <= 10^4
     */
    public String preToInfix(String s) {
        Stack<StringBuilder>st=new Stack<>();
        for(int i=s.length()-1;i>=0;i--){
            char ch=s.charAt(i);
            if(Character.isLetterOrDigit(ch))st.push(new StringBuilder(String.valueOf(ch)));
            else {
                StringBuilder first=st.pop();
                StringBuilder second=st.pop();
                StringBuilder temp=new StringBuilder();
                temp.append("(");
                temp.append(first);
                temp.append(String.valueOf(ch));
                temp.append(second);
                temp.append(")");
                st.push(temp);
            }
        }
        return st.peek().toString();
    }

    public static void main(String[] args) {
        PrefixToInfix converter = new PrefixToInfix();

        // Test Case Definitions: {Prefix, Expected Infix}
        String[][] testCases = {
                {"*-A/BC-/AKL", "((A-(B/C))*((A/K)-L))"},      // Example from problem
                {"+ab", "(a+b)"},                               // Simplest case
                {"*+abc", "((a+b)*c)"},                         // Standard precedence
                {"++A*BCD", "(A+((B*C)+D))"},                   // Sequential operators
                {"/*a+bcd", "((a*(b+c))/d)"},                   // Mixed operators
                {"^a^bc", "(a^(b^c))"},                         // Power operator (Right-associative)
                {"+-ab+cd", "((a-b)+(c+d))"},                   // Parallel operations
                {"*+AB-CD", "((A+B)*(C-D))"},                   // Product of sums
                {"-A/B+C^DE", "(A-(B/(C+(D^E))))"},             // Complex nested
                {"/+p-qr*st", "((p+(q-r))/(s*t))"}              // Varied operators
        };

        int passed = 0;
        System.out.println("Testing Prefix to Infix Conversion...\n");
        System.out.printf("%-15s | %-25s | %-25s | %-8s%n", "Prefix", "Expected", "Actual", "Result");
        System.out.println("-".repeat(85));

        for (String[] test : testCases) {
            String input = test[0];
            String expected = test[1];
            String actual = "";

            try {
                actual = converter.preToInfix(input);
            } catch (Exception e) {
                actual = "ERROR";
            }

            // Note: Whitespace and redundant brackets can differ,
            // but for this problem, we follow the (A op B) pattern strictly.
            boolean isMatch = expected.equals(actual);
            if (isMatch) passed++;

            System.out.printf("%-15s | %-25s | %-25s | %-8s%n",
                    input, expected, actual, isMatch ? "PASS" : "FAIL");
        }

        System.out.println("-".repeat(85));
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}