package stacks;

import java.util.Stack;

public class InfixToPrefix {

    /**
     * Converts an infix expression to a prefix expression.
     * Precedence: ^ (Highest, R-to-L), * / (Med, L-to-R), + - (Low, L-to-R)
     * Constraints: 3 <= s.length <= 5000
     */
    public String convert(String s) {
        Stack<Character> st=new Stack<>();
        StringBuilder ans=new StringBuilder();

        for(int i=s.length()-1;i>=0;i--){
            char ch=s.charAt(i);
            if((ch>='A'&&ch<='Z')||(ch>='a'&&ch<='z')||(ch>='0'&&ch<='9'))ans.append(ch);
            else if (ch==')')st.push(ch);
            else if(ch=='('){
                while(!st.isEmpty()&&st.peek()!=')')ans.append(st.pop());
                if(!st.isEmpty())st.pop();
            }else {
                if(ch=='^'){
                    while(!st.isEmpty())ans.append(st.pop());
                    st.push(ch);
                }else{
                    while(!st.isEmpty()&&priority(ch)<priority(st.peek()))ans.append(st.pop());
                    st.push(ch);
                }
            }
        }
        while(!st.isEmpty())ans.append(st.pop());
        ans.reverse();
        return ans.toString();
    }

    private int priority(char ch){
        if(ch=='^')return 3;
        else if(ch=='*'||ch=='/')return 2;
        else if(ch=='+'||ch=='-')return 1;
        else return -1;
    }

    public static void main(String[] args) {
        InfixToPrefix converter = new InfixToPrefix();

        // Test Case Definitions: {Infix, Expected Prefix}
        String[][] testCases = {
                {"a*(b+c)/d", "/*a+bcd"},                   // Standard precedence
                {"(a-b/c)*(a/k-l)", "*-a/bc-/akl"},         // Nested parentheses
                {"x+y*z", "+x*yz"},                         // Basic precedence
                {"(a+b)*c", "*+abc"},                       // Parentheses override
                {"a^b^c", "^a^bc"},                         // Right-to-left power
                {"a+b-c", "-+abc"},                         // Sequential same precedence
                {"(A+B)*(C-D)", "*+AB-CD"},                 // Simple multiplication of sums
                {"A+B*C+D", "++A*BCD"},                     // Mixed without parentheses
                {"a+b*(c^d-e)^(f+g*h)-i", "-+a*b^-^cdef+g*hi"}, // Extremely complex
                {"((a+b)-c*(d/e))+f", "+--ab*c/def"},       // Deeply nested
        };

        int passed = 0;
        System.out.println("Testing Infix to Prefix Conversion...\n");
        System.out.printf("%-25s | %-25s | %-25s | %-8s%n", "Infix", "Expected", "Actual", "Result");
        System.out.println("-".repeat(95));

        for (String[] test : testCases) {
            String input = test[0];
            String expected = test[1];
            String actual = converter.convert(input);
            boolean isMatch = expected.equals(actual);

            if (isMatch) passed++;

            System.out.printf("%-25s | %-25s | %-25s | %-8s%n",
                    input, expected, actual, isMatch ? "PASS" : "FAIL");
        }

        System.out.println("-".repeat(95));
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}