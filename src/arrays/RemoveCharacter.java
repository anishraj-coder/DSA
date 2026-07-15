package arrays;

public class RemoveCharacter {

    /**
     * Removes all occurrences of character c from string s.
     *
     * @param s the input string
     * @param c the character to be removed
     * @return the resulting string after removal
     */
    public String removeOccurrences(String s, char c) {
        int n=s.length();
        if(n==1 && s.charAt(0)==c)return "";
        StringBuilder sb=new StringBuilder();
        for(char ch:s.toCharArray()){
            if(ch!=c)sb.append(ch);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        RemoveCharacter instance = new RemoveCharacter();

        // Test Case Definitions: {String s, char c, String expected}
        Object[][] testCases = {
                {"geeksforgeeks", 'e', "gksforgks"},      // Example 1
                {"geeksforgeeks", 'g', "eeksforeeks"},    // Example 2
                {"aaaaa", 'a', ""},                       // Edge: All characters are the target
                {"abcde", 'z', "abcde"},                  // Edge: Character not present
                {"a", 'a', ""},                           // Edge: Single char string (match)
                {"a", 'b', "a"},                          // Edge: Single char string (no match)
                {"hello world", ' ', "helloworld"},       // Edge: Removing whitespace
                {"AbcA", 'A', "bc"},                      // Case sensitivity check
                {"123123", '1', "2323"},                  // Numeric characters
                {"mousum", 'u', "mosm"}                   // Regular case
        };

        int passed = 0;

        System.out.println("Running Test Cases for RemoveCharacter...");
        System.out.println("--------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            String s = (String) testCases[i][0];
            char c = (char) testCases[i][1];
            String expected = (String) testCases[i][2];

            String actual = instance.removeOccurrences(s, c);

            // Handle null safety in comparison
            boolean isPassed = (actual != null && actual.equals(expected));
            if (isPassed) passed++;

            System.out.printf("Test Case %d: Input = (\"%s\", '%c') | Expected = \"%s\" | Actual = \"%s\" | Result = %s%n",
                    (i + 1), s, c, expected, actual, isPassed ? "PASSED" : "FAILED");
        }

        System.out.println("--------------------------------------------------");
        System.out.printf("Final Result: %d/%d Passed%n", passed, testCases.length);
    }
}