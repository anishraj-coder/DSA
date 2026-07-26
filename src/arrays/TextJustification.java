package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextJustification {

    public List<String> fullJustify(String[] words, int width) {
    	int n=words.length,i=0;
    	List<String>ans=new ArrayList<>();
    	while(i<n) {
    		int j=i,wordlen=0;
    		while(j<n&& wordlen +words[j].length() +(j-i)<=width) {
    			wordlen+=words[j].length();
    			j++;
    		}
    		
    		StringBuilder sb=new StringBuilder();
    		
    		int numWords=j-i;
    		int numGaps=numWords-1;
    		if(j==n||numWords==1) {
    			for(int k=i;k<j;k++) {
    				sb.append(words[k]);
    				if(k<j-1)sb.append(" ");
    			}
    			while(sb.length()<width)sb.append(" ");
    		}else {
    			int totalGaps=width-wordlen;
    			int base=totalGaps/numGaps;
    			int extra=totalGaps%numGaps;
    			int extraUsed=0;
    			for(int k=i;k<j;k++) {
    				sb.append(words[k]);
    				if(k<j-1) {
    					int space=base;
        				if(extraUsed<extra) {
        					extraUsed++;
        					space++;
        				}
        				for(int s=0;s<space;s++)sb.append(" ");
    				}
    			}
    		}
    		i=j;
    		ans.add(sb.toString());
    	}
        return ans;
    }

    public static void main(String[] args) {
        TextJustification tj = new TextJustification();
        int passed = 0;
        int failed = 0;

        // Test Case 1: Standard Example 1
        String[] words1 = {"This", "is", "an", "example", "of", "text", "justification."};
        int width1 = 16;
        List<String> expected1 = Arrays.asList(
            "This    is    an",
            "example  of text",
            "justification.  "
        );
        if (runTestCase(1, tj.fullJustify(words1, width1), expected1)) passed++; else failed++;

        // Test Case 2: Standard Example 2 (Single-word lines & Last line behavior)
        String[] words2 = {"What", "must", "be", "acknowledgment", "shall", "be"};
        int width2 = 16;
        List<String> expected2 = Arrays.asList(
            "What   must   be",
            "acknowledgment  ",
            "shall be        "
        );
        if (runTestCase(2, tj.fullJustify(words2, width2), expected2)) passed++; else failed++;

        // Test Case 3: Standard Example 3 (Uneven space distribution)
        String[] words3 = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        int width3 = 20;
        List<String> expected3 = Arrays.asList(
            "Science  is  what we",
            "understand      well",
            "enough to explain to",
            "a  computer.  Art is",
            "everything  else  we",
            "do                  "
        );
        if (runTestCase(3, tj.fullJustify(words3, width3), expected3)) passed++; else failed++;

        // Test Case 4: Edge Case - Single word that fits exactly
        String[] words4 = {"Hello"};
        int width4 = 5;
        List<String> expected4 = Arrays.asList(
            "Hello"
        );
        if (runTestCase(4, tj.fullJustify(words4, width4), expected4)) passed++; else failed++;

        // Test Case 5: Edge Case - Single word needing padding
        String[] words5 = {"Hello"};
        int width5 = 10;
        List<String> expected5 = Arrays.asList(
            "Hello     "
        );
        if (runTestCase(5, tj.fullJustify(words5, width5), expected5)) passed++; else failed++;

        // Test Case 6: Edge Case - Multiple single-letter words (Last line check)
        String[] words6 = {"a", "b", "c", "d", "e"};
        int width6 = 3;
        List<String> expected6 = Arrays.asList(
            "a b",
            "c d",
            "e  "
        );
        if (runTestCase(6, tj.fullJustify(words6, width6), expected6)) passed++; else failed++;

        // Test Case 7: Edge Case - Exactly filling width without spaces left over
        String[] words7 = {"The", "quick", "brown", "fox"};
        int width7 = 19;
        List<String> expected7 = Arrays.asList(
            "The   quick   brown",
            "fox                "
        );
        if (runTestCase(7, tj.fullJustify(words7, width7), expected7)) passed++; else failed++;

        // Test Case 8: Edge Case - Each line contains only one word (large width)
        String[] words8 = {"A", "Computer", "Science", "Portal"};
        int width8 = 10;
        List<String> expected8 = Arrays.asList(
            "A         ",
            "Computer  ",
            "Science   ",
            "Portal    "
        );
        if (runTestCase(8, tj.fullJustify(words8, width8), expected8)) passed++; else failed++;

        // Test Case 9: Uneven spaces across slots (Left slots get more spaces)
        String[] words9 = {"Ask", "not", "what", "your", "country", "can", "do", "for", "you"};
        int width9 = 16;
        List<String> expected9 = Arrays.asList(
            "Ask   not   what",
            "your country can",
            "do for you      "
        );
        if (runTestCase(9, tj.fullJustify(words9, width9), expected9)) passed++; else failed++;

        // Test Case 10: Edge Case - Words with punctuation and symbols
        String[] words10 = {"Listen...", "to", "them--children", "of", "the", "night."};
        int width10 = 18;
        List<String> expected10 = Arrays.asList(
            "Listen...       to",
            "them--children  of",
            "the night.        "
        );
        if (runTestCase(10, tj.fullJustify(words10, width10), expected10)) passed++; else failed++;

        System.out.println("\n----------------------------------");
        System.out.println("Summary: " + passed + " PASSED, " + failed + " FAILED");
        System.out.println("----------------------------------");
    }

    private static boolean runTestCase(int testNum, List<String> actual, List<String> expected) {
        boolean matches = expected.equals(actual);
        if (matches) {
            System.out.println("Test Case " + testNum + ": PASS");
        } else {
            System.out.println("Test Case " + testNum + ": FAIL");
            System.out.println("   Expected: " + expected);
            System.out.println("   Actual:   " + actual);
        }
        return matches;
    }
}