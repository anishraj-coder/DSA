package strings;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Problem: Reverse Words in a String
 * * Instructions:
 * 1. Implement the 'reverseWords' method.
 * 2. The output should have words in reverse order separated by a single space.
 * 3. No leading or trailing spaces should be present.
 * 4. Multiple spaces between words should be reduced to a single space.
 */
public class ReverseWords {

    public String reverseWords(String s) {
        ArrayList<StringBuilder>list=new ArrayList<>();
        StringBuilder currentWord=new StringBuilder();
        for(char ch:s.toCharArray()){
            if(ch!=' '){
                currentWord.append(ch);
            }else{
                if(!currentWord.isEmpty())list.add(currentWord);
                currentWord=new StringBuilder();
            }
        }
        if(!currentWord.isEmpty())list.add(currentWord);

        StringBuilder res=new StringBuilder();
        for(int i=list.size()-1;i>=0;i--){
            res.append(list.get(i));
            if(i!=0)res.append(" ");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        ReverseWords solver = new ReverseWords();

        // Test Case Definitions
        String[] inputs = {
                "the sky is blue",              // Standard case
                "  hello world  ",              // Leading and trailing spaces
                "a good   example",             // Multiple spaces between words
                "  Bob    Loves  Alice   ",     // Mixed spaces and multiple words
                "Alice",                        // Single word
                "  SingleWord  ",               // Single word with spaces
                "123 456 789",                  // Digits as words
                " a b c d ",                    // Single character words
                "   ",                          // Only spaces (Note: Constraints say at least one word)
                "EPIC   LONG    STRING   TEST"  // All caps with varied spacing
        };

        String[] expected = {
                "blue is sky the",
                "world hello",
                "example good a",
                "Alice Loves Bob",
                "Alice",
                "SingleWord",
                "789 456 123",
                "d c b a",
                "",
                "TEST STRING LONG EPIC"
        };

        int passed = 0;

        System.out.println("Running Tests...\n");
        System.out.printf("%-3s | %-25s | %-20s | %-20s | %-6s\n", "ID", "Input", "Expected", "Actual", "Result");
        System.out.println("---------------------------------------------------------------------------------------");

        for (int i = 0; i < inputs.length; i++) {
            String result = solver.reverseWords(inputs[i]);
            boolean isMatch = Objects.equals(result, expected[i]);

            if (isMatch) passed++;

            System.out.printf("%-3d | %-25s | %-20s | %-20s | %-6s\n",
                    i + 1,
                    "\"" + inputs[i] + "\"",
                    "\"" + expected[i] + "\"",
                    "\"" + result + "\"",
                    isMatch ? "PASS" : "FAIL"
            );
        }

        System.out.println("\n---------------------------------------------------------------------------------------");
        System.out.printf("Final Result: %d/%d Tests Passed\n", passed, inputs.length);
    }
}