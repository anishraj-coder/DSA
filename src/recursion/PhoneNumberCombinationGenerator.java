package recursion;

import java.util.*;

public class PhoneNumberCombinationGenerator {

    /**
     * Returns all possible letter combinations that the digits could represent.
     * @param digits A string containing digits from 2-9 inclusive.
     * @return A list of strings representing the letter combinations.
     */
    public List<String> letterCombinations(String digits) {
        if(digits==null||digits.isBlank())return new ArrayList<>();
        List<String>ans=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        helper(digits,0,sb,ans);
        return ans;
    }

    private void helper(String str,int i,StringBuilder curr,List<String>ans){
        int n=str.length();
        if(i==n){
            ans.add(curr.toString());
            return;
        }

        char[][]choice={{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','0'},{'p','q','r','s'}
                ,{'t','u','v'},{'w','x','y','z'}};
        int idx=(str.charAt(i)-'0')-2;
        for(char ch: choice[idx]){
            curr.append(ch);
            helper(str,i+1,curr,ans);
            curr.deleteCharAt(curr.length()-1);
        }
    }

    public static void main(String[] args) {
        PhoneNumberCombinationGenerator generator = new PhoneNumberCombinationGenerator();

        // Test Mapping
        Map<String, List<String>> testCases = new LinkedHashMap<>();

        testCases.put("2", Arrays.asList("a", "b", "c"));
        testCases.put("3", Arrays.asList("d", "e", "f"));
        testCases.put("23", Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
        testCases.put("7", Arrays.asList("p", "q", "r", "s"));
        testCases.put("9", Arrays.asList("w", "x", "y", "z"));
        testCases.put("22", Arrays.asList("aa", "ab", "ac", "ba", "bb", "bc", "ca", "cb", "cc"));

        // Complex Case: Mixed lengths (3 vs 4 letters)
        testCases.put("27", Arrays.asList("ap", "aq", "ar", "as", "bp", "bq", "br", "bs", "cp", "cq", "cr", "cs"));

        // Maximum Constraint: Length 4
        testCases.put("2345", Arrays.asList(
                "adgj", "adgk", "adgl", "adhj", "adhk", "adhl", "adij", "adik", "adil",
                "aegj", "aegk", "aegl", "aehj", "aehk", "aehl", "aeij", "aeik", "aeil",
                "afgj", "afgk", "afgl", "afhj", "afhk", "afhl", "afij", "afik", "afil",
                "bdgj", "bdgk", "bdgl", "bdhj", "bdhk", "bdhl", "bdij", "bdik", "bdil",
                "begj", "begk", "begl", "behj", "behk", "behl", "beij", "beik", "beil",
                "bfgj", "bfgk", "bfgl", "bfhj", "bfhk", "bfhl", "bfij", "bfik", "bfil",
                "cdgj", "cdgk", "cdgl", "cdhj", "cdhk", "cdhl", "cdij", "cdik", "cdil",
                "cegj", "cegk", "cegl", "cehj", "cehk", "cehl", "ceij", "ceik", "ceil",
                "cfgj", "cfgk", "cfgl", "cfhj", "cfhk", "cfhl", "cfij", "cfik", "cfil"
        ));

        // Edge Case: Empty String
        testCases.put("", Collections.emptyList());

        // Edge Case: Repeating same digit with 4 letters
        testCases.put("77", Arrays.asList(
                "pp", "pq", "pr", "ps", "qp", "qq", "qr", "qs",
                "rp", "rq", "rr", "rs", "sp", "sq", "sr", "ss"
        ));

        int passed = 0;
        int total = 0;

        for (Map.Entry<String, List<String>> entry : testCases.entrySet()) {
            String input = entry.getKey();
            List<String> expected = new ArrayList<>(entry.getValue());
            List<String> actual = generator.letterCombinations(input);

            Collections.sort(expected);
            if (actual != null) Collections.sort(actual);

            boolean isMatch = expected.equals(actual);
            total++;
            if (isMatch) passed++;

            System.out.println("Test Case " + total + ": digits = \"" + input + "\"");
            System.out.println("Expected Size: " + expected.size());
            System.out.println("Actual Size:   " + (actual == null ? 0 : actual.size()));
            System.out.println("Result:        " + (isMatch ? "PASSED ✅" : "FAILED ❌"));
            if (!isMatch) {
                System.out.println("Expected: " + expected);
                System.out.println("Actual:   " + actual);
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("\nFinal Score: " + passed + "/" + total);
    }
}