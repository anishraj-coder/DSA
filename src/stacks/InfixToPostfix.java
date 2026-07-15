package stacks;

import java.util.*;

public class InfixToPostfix {

    /**
     * Converts an infix expression to a postfix expression.
     * Constraints: 1 <= n <= 5000
     * Operators: +, -, *, /, ^, (, )
     * Operands: digits and lower case English letters
     */
    public String convert(String exp) {
        StringBuilder ans=new StringBuilder();
        Stack<Character>st=new Stack<>();
        for(char ch:exp.toCharArray()){
            if((ch>='A'&&ch<='Z')||(ch>='a'&&ch<='z')||(ch>='0'&&ch<='9'))ans.append(ch);
            else if(ch=='(')st.push(ch);
            else if(ch==')'){
                while (!st.isEmpty()&&st.peek()!='('){
                    ans.append(st.peek());
                    st.pop();
                }
                st.pop();
            }else {
                while(!st.isEmpty()&&priority(ch)<=priority(st.peek())){
                    ans.append(st.peek());
                    st.pop();
                }
                st.push(ch);
            }
        }

        while(!st.isEmpty())ans.append(st.pop());
        return ans.toString();
    }

    private int priority(char ch){
        if(ch=='^')return 3;
        else if(ch=='*'||ch=='/')return 2;
        else if(ch=='+'||ch=='-')return 1;
        else return -1;
    }

    public static void main(String[] args) {
        InfixToPostfix converter = new InfixToPostfix();

        // Test Case Definitions: {Infix, Expected Postfix}
        String[][] testCases = {
                {"3+4*8", "348*+"},                          // Standard precedence
                {"3^(1+1)", "311+^"},                        // Parentheses and power
                {"a+b+c+d-e", "ab+c+d+e-"},                  // Left-to-right associativity
                {"a+b*(c^d-e)^(f+g*h)-i", "abcd^e-fgh*+^*+i-"}, // Complex expression
                {"(a+b)*c", "ab+c*"},                        // Parentheses overriding precedence
                {"a^b^c", "abc^^"},                          // Right-to-left associativity of ^
                {"x-y/z", "xyz/-"},                          // Division precedence
                {"a+b*c+(d*e+f)*g", "abc*+de*f+g*+"},        // Mixed nested structures
                {"a+b-c+d", "ab+c-d+"},                      // Same precedence sequential
                {"((a+b)-c*(d/e))+f", "ab+de/c*-f+"},        // Deeply nested parentheses
                {"1+2^3*4", "123^4*+"}                       // Mixed numbers and operators
        };

        int passed = 0;
        System.out.println("Testing Infix to Postfix Conversion...\n");
        System.out.printf("%-25s | %-25s | %-25s | %-8s%n", "Infix", "Expected", "Actual", "Result");
        System.out.println("-".repeat(90));

        for (String[] test : testCases) {
            String input = test[0];
            String expected = test[1];
            String actual = converter.convert(input);
            boolean isMatch = expected.equals(actual);

            if (isMatch) passed++;

            System.out.printf("%-25s | %-25s | %-25s | %-8s%n",
                    input, expected, actual, isMatch ? "PASS" : "FAIL");
        }

        System.out.println("-".repeat(90));
        System.out.printf("Final Score: %d/%d%n", passed, testCases.length);
    }
}