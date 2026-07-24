package greedy;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class ValidParenthesisString {

    // ==========================================
    // 1. Top-Down Approach (Memoization)
    // ==========================================
    public boolean checkValidStringMemoization(String s) {
        int n=s.length();
        int[][]dp=new int [n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        return helper(s,0,0,dp);
    }

    private boolean helper(String s,int i,int oc,int[][]dp){
        int n=s.length();
        if(i==n)return oc==0;
        if(dp[i][oc]!=-1)return dp[i][oc]==1;
        boolean isValid=false;
        char ch=s.charAt(i);
        if(ch=='*'){
            isValid=isValid||helper(s,i+1,oc,dp);
            if(oc>0)isValid=isValid||helper(s,i+1,oc-1,dp);
            isValid=isValid||helper(s,i+1,oc+1,dp);
        }else if(ch=='('){
            isValid=isValid||helper(s,i+1,oc+1,dp);

        }else {
            if(oc>0)isValid=isValid||helper(s,i+1,oc-1,dp);
        }
        dp[i][oc]=isValid?1:0;
        return isValid;

    }

    // ==========================================
    // 2. Stack Approach
    // ==========================================
    public boolean checkValidStringStack(String s) {
        int n=s.length();
        Stack<Integer> bracket=new Stack<>();
        Stack<Integer> star=new Stack<>();
        for(int i=0;i<n;i++){
            char ch=s.charAt(i);
            if(ch=='(')bracket.push(i);
            else if(ch=='*')star.push(i);
            else if(ch==')'){
                if(!bracket.isEmpty())bracket.pop();
                else if(!star.isEmpty())star.pop();
                else return false;
            }
        }
        while(!star.isEmpty()&&!bracket.isEmpty()){
            if(bracket.peek()>star.peek())return false;
            bracket.pop();star.pop();
        }
        return bracket.isEmpty();
    }

    // ==========================================
    // 3. Two-Pointer / Greedy Approach
    // ==========================================
    public boolean checkValidStringTwoPointer(String s) {
        int min=0,max=0;
        for(char ch:s.toCharArray()){
            if(ch=='('){
                min++;max++;
            }else if(ch==')'){
                min--;max--;
            }else{
                min--;max++;
            }
            if(min<0)min=0;
            if(max<0)return false;
        }
        return min==0;
    }

    // ==========================================
    // Test Case Suite & Interactive Runner
    // ==========================================
    static class TestCase {
        String input;
        boolean expected;

        TestCase(String input, boolean expected) {
            this.input = input;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        ValidParenthesisString checker = new ValidParenthesisString();
        Scanner scanner = new Scanner(System.in);

        TestCase[] testCases = new TestCase[] {
                // 1. Standard simple valid strings
                new TestCase("()", true),
                new TestCase("(*)", true),
                new TestCase("(*))", true),

                // 2. Simple invalid strings
                new TestCase(")(", false),
                new TestCase("(", false),
                new TestCase(")", false),

                // 3. Wildcard edge cases (* as empty, '(', or ')')
                new TestCase("***", true),
                new TestCase("(((***", true),
                new TestCase("(((******", true),

                // 4. Complex / Nested valid cases
                new TestCase("((*)())*", true),
                new TestCase("((*)(*))", true),

                // 5. Hard / Tricky invalid cases
                new TestCase("*(*)", true),
                new TestCase("(((((*)))**", false),
                new TestCase("()*)*()", false),
                new TestCase("(*()", false),
                new TestCase("*(", false)
        };

        while (true) {
            System.out.println("\n=================================");
            System.out.println("  Valid Parenthesis String Runner ");
            System.out.println("=================================");
            System.out.println("1. Test Memoization (Top-Down)");
            System.out.println("2. Test Stack Approach");
            System.out.println("3. Test Two-Pointer Approach");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");

            if (!scanner.hasNextInt()) {
                break;
            }

            int choice = scanner.nextInt();
            if (choice == 4) {
                System.out.println("Exiting test runner.");
                break;
            }

            if (choice < 1 || choice > 3) {
                System.out.println("Invalid option. Please choose between 1 and 4.");
                continue;
            }

            runTestSuite(checker, testCases, choice);
        }

        scanner.close();
    }

    private static void runTestSuite(ValidParenthesisString checker, TestCase[] testCases, int choice) {
        String methodName = switch (choice) {
            case 1 -> "Memoization (Top-Down)";
            case 2 -> "Stack";
            case 3 -> "Two-Pointer";
            default -> "";
        };

        System.out.println("\n--- Running Tests for: " + methodName + " ---");
        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            boolean actual = false;

            switch (choice) {
                case 1 -> actual = checker.checkValidStringMemoization(tc.input);
                case 2 -> actual = checker.checkValidStringStack(tc.input);
                case 3 -> actual = checker.checkValidStringTwoPointer(tc.input);
            }

            boolean isPass = (actual == tc.expected);
            if (isPass) {
                passed++;
            }

            String status = isPass ? "PASS" : "FAIL";
            System.out.printf("[%s] Test %2d | Input: %-15s | Expected: %-5b | Actual: %-5b\n",
                    status, (i + 1), "\"" + tc.input + "\"", tc.expected, actual);
        }

        System.out.println("--------------------------------------------------");
        System.out.printf("Results: %d/%d test cases passed.\n", passed, testCases.length);
    }
}