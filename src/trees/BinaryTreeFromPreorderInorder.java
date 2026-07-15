package trees;

import java.util.*;


public class BinaryTreeFromPreorderInorder {

    // TO BE IMPLEMENTED BY YOU
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0)return null;
        if(preorder.length==1)return new TreeNode(preorder[0]);

        HashMap<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<preorder.length;i++)map.put(inorder[i],i);
        int n=preorder.length-1;

        return construct(preorder,inorder,map,0,n,0,n);
    }
    private TreeNode construct(int[]pre,int[]in,HashMap<Integer,Integer>map,int ps,int pe,int is,int ie){
        if(ie<is)return null;
        if(pe<ps)return null;
        TreeNode root=new TreeNode(pre[ps]);
        int idx=map.get(root.val);
        int length=idx-is;
        root.left=construct(pre,in,map,ps+1,ps+length,is,idx-1);
        root.right=construct(pre,in,map,ps+length+1,pe,idx+1,ie);
        return root;
    }

    public static void main(String[] args) {
        BinaryTreeFromPreorderInorder builder = new BinaryTreeFromPreorderInorder();

        // Define 10 verified test cases
        int[][][] testCases = {
                // Case 1: Standard Balanced Tree (Sample 1)
                {{1, 2, 4, 5, 3, 6, 7}, {4, 2, 5, 1, 6, 3, 7}},
                // Case 2: Left-Leaning Variant (Sample 2)
                {{5, 6, 2, 3, 9, 10}, {2, 6, 3, 9, 5, 10}},
                // Case 3: Single Element (Edge Case)
                {{42}, {42}},
                // Case 4: Completely Left Skewed (Edge Case)
                {{1, 2, 3, 4}, {4, 3, 2, 1}},
                // Case 5: Completely Right Skewed (Edge Case)
                {{1, 2, 3, 4}, {1, 2, 3, 4}},
                // Case 6: Empty Arrays (Edge Case)
                {{}, {}},
                // Case 7: Perfect Binary Tree
                {{10, 20, 40, 50, 30, 60, 70}, {40, 20, 50, 10, 60, 30, 70}},
                // Case 8: Zig-zag (Left-Right alternative)
                {{1, 2, 3, 4}, {2, 4, 3, 1}},
                // Case 9: Node values contain zeros and negatives
                {{0, -1, -2, -3}, {-3, -2, -1, 0}},
                // Case 10: Mixed tree structure
                {{1, 2, 4, 3, 5, 6}, {4, 2, 1, 5, 3, 6}}
        };

        // Expected Level Order Results (Serialized as strings, excluding trailing nulls)
        String[] expectedOutputs = {
                "[1, 2, 3, 4, 5, 6, 7]",
                "[5, 6, 10, 2, 3, null, null, null, 9]",
                "[42]",
                "[1, 2, null, 3, null, 4]",
                "[1, null, 2, null, 3, null, 4]",
                "[]",
                "[10, 20, 30, 40, 50, 60, 70]",
                "[1, 2, null, null, 3, 4]",
                "[0, -1, null, -2, null, -3]",
                "[1, 2, 3, 4, null, 5, 6]"
        };

        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] preorder = testCases[i][0];
            int[] inorder = testCases[i][1];
            String expected = expectedOutputs[i];

            System.out.println("----------------------------------------");
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Preorder: " + Arrays.toString(preorder));
            System.out.println("Inorder:  " + Arrays.toString(inorder));

            TreeNode root = null;
            try {
                root = builder.buildTree(preorder, inorder);
            } catch (Exception e) {
                System.out.println("Exception thrown during execution: " + e.getMessage());
            }

            String actual = serializeLevelOrder(root);
            System.out.println("Expected Output: " + expected);
            System.out.println("Actual Output:   " + actual);

            if (expected.equals(actual)) {
                System.out.println("Result:          PASSED");
                passed++;
            } else {
                System.out.println("Result:          FAILED");
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("Final score: " + passed + " / " + testCases.length + " passed.");
    }

    // Level-order custom serialization to convert tree structures securely to string format
    private static String serializeLevelOrder(TreeNode root) {
        if (root == null) return "[]";

        List<String> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                list.add("null");
            } else {
                list.add(String.valueOf(curr.val));
                queue.add(curr.left);
                queue.add(curr.right);
            }
        }

        // Trim trailing "null" strings to mimic canonical test framework representations
        int lastNonNull = list.size() - 1;
        while (lastNonNull >= 0 && list.get(lastNonNull).equals("null")) {
            lastNonNull--;
        }

        List<String> trimmedList = new ArrayList<>();
        for (int i = 0; i <= lastNonNull; i++) {
            trimmedList.add(list.get(i));
        }

        return trimmedList.toString();
    }
}