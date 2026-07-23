package DynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Objects;

public class LongestPalindromicSubstring {

    // =========================================================================
    // IMPLEMENT YOUR SOLUTIONS HERE
    // =========================================================================

    /**
     * Approach 1: Top-Down Dynamic Programming (Recursion + Memoization)
     */
    public String longestPalindromeTopDown(String s) {
        int n=s.length();
        int[][]dp=new int[n][n];
        for(int[]a:dp) Arrays.fill(a,-1);
        int max=Integer.MIN_VALUE,start=-1;
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                if(isPalindrome(s,j,i,dp)==1){
                    int curr=i-j+1;
                    if(curr>max){
                        max=curr;
                        start=j;
                    }
                }
            }
        }
        return s.substring(start,max+start);
    }

    /**
     * Approach 2: Bottom-Up Dynamic Programming (Tabulation)
     */
    public String longestPalindromeBottomUp(String s) {
        int n=s.length();
        int[][]dp=new int[n][n];
        int max=1,start=0;
        for(int i=0;i<n;i++)dp[i][i]=1;
        for(int i=0;i<n-1;i++){
            if(s.charAt(i)==s.charAt(i+1)){
                dp[i][i+1]=1;
                max=2;
                start=i;
            }
        }
        for(int len=3;len<=n;len++){
            for(int j=0;j<=n-len;j++){
                int k=j+len-1;
                if(s.charAt(j)==s.charAt(k)&&dp[j+1][k-1]==1){
                    dp[j][k]=1;
                    if(len>=max){
                        max=len;
                        start=j;
                    }
                }
            }
        }
        return s.substring(start,start+max);
    }

    /**
     * Approach 3: Two-Pointer Approach (Expand Around Center)
     */
    public String longestPalindromeTwoPointer(String s) {
        if(s==null|| s.isEmpty())return "";
        if(s.length()==1)return s.substring(0,1);
        int max=1,start=0;
        int n=s.length();
        for(int i=0;i<n-1;i++){
            int odd=expand(s,i,i);
            int even=expand(s,i,i+1);
            if(odd>max){
                max=odd;
                start=i-(odd-1)/2;
            }
            if(even>max){
                max=even;
                start=i-(even-1)/2;
            }
        }
        return s.substring(start,start+max);
    }

    private int expand(String s,int l,int r){
        int n=s.length();
        while(l>=0&&r<n&&s.charAt(l)==s.charAt(r)){
            --l;r++;
        }
        return r-l-1;
    }


    private int isPalindrome(String s,int l,int r,int[][]dp){
        if(l>=r)return 1;

        if(dp[l][r]!=-1)return dp[l][r];
        if(s.charAt(l)==s.charAt(r)){
            dp[l][r]=isPalindrome(s,l+1,r-1,dp);
        }else{
            dp[l][r]=0;
        }

        return dp[l][r];
    }

    // =========================================================================
    // TEST SUITE & HARNESS
    // =========================================================================

    static class TestCase {
        String input;
        String[] validExpectedOutputs; // Multiple valid answers possible (e.g., "bab" or "aba")

        TestCase(String input, String... validExpectedOutputs) {
            this.input = input;
            this.validExpectedOutputs = validExpectedOutputs;
        }
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring instance = new LongestPalindromicSubstring();
        Scanner scanner = new Scanner(System.in);

        TestCase[] testCases = new TestCase[] {
                // 1. Standard Case with odd-length palindrome
                new TestCase("babad", "bab", "aba"),

                // 2. Standard Case with even-length palindrome
                new TestCase("cbbd", "bb"),

                // 3. Edge Case: Single character
                new TestCase("a", "a"),

                // 4. Edge Case: Two identical characters
                new TestCase("bb", "bb"),

                // 5. Edge Case: Two distinct characters
                new TestCase("ac", "a", "c"),

                // 6. Hard Case: Entire string is a palindrome
                new TestCase("racecar", "racecar"),

                // 7. Hard Case: All identical characters
                new TestCase("aaaaa", "aaaaa"),

                // 8. Tricky Case: Overlapping centers / multiple palindromes
                new TestCase("bananas", "anana"),

                // 9. Numeric String & Long sequence with distinct center
                new TestCase("12321456", "12321"),

                // 10. Long Case: Palindrome at the very end
                new TestCase("abacabadabacaba", "abacabadabacaba"),

                // 11. No long palindrome (returns first char)
                new TestCase("abcdefg", "a", "b", "c", "d", "e", "f", "g")
        };

        System.out.println("==============================================");
        System.out.println(" Select Approach to Test:");
        System.out.println(" 1. Top-Down Dynamic Programming");
        System.out.println(" 2. Bottom-Up Dynamic Programming");
        System.out.println(" 3. Two-Pointer (Expand Around Center)");
        System.out.println("==============================================");
        System.out.print("Enter choice (1-3): ");

        int choice = scanner.nextInt();
        System.out.println();

        switch (choice) {
            case 1:
                System.out.println("--- Testing Top-Down DP ---");
                runTests(testCases, instance::longestPalindromeTopDown);
                break;
            case 2:
                System.out.println("--- Testing Bottom-Up DP ---");
                runTests(testCases, instance::longestPalindromeBottomUp);
                break;
            case 3:
                System.out.println("--- Testing Two-Pointer Approach ---");
                runTests(testCases, instance::longestPalindromeTwoPointer);
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
        }

        scanner.close();
    }

    @FunctionalInterface
    interface SolutionFunction {
        String apply(String input);
    }

    private static void runTests(TestCase[] testCases, SolutionFunction solver) {
        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            String actual = solver.apply(tc.input);

            boolean isPass = false;
            for (String validExpected : tc.validExpectedOutputs) {
                if (Objects.equals(actual, validExpected)) {
                    isPass = true;
                    break;
                }
            }

            if (isPass) {
                passed++;
                System.out.printf("Test %2d: [ PASS ] Input: \"%s\" | Output: \"%s\"\n",
                        i + 1, tc.input, actual);
            } else {
                String expectedStr = String.join(" OR ", tc.validExpectedOutputs);
                System.out.printf("Test %2d: [ FAIL ] Input: \"%s\"\n", i + 1, tc.input);
                System.out.printf("          Expected: \"%s\"\n", expectedStr);
                System.out.printf("          Actual:   \"%s\"\n", actual);
            }
        }

        System.out.println("\n----------------------------------------------");
        System.out.printf("Result: %d/%d Test Cases Passed\n", passed, testCases.length);
        System.out.println("----------------------------------------------");
    }
}