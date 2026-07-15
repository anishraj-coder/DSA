package trees;

import java.util.*;


public class BinaryTreeFromInorderPostorder {

    /**
     * Problem: Construct Binary Tree from Inorder and Postorder Traversal
     * Constraints: 1 <= inorder.length <= 3000, Unique values.
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(postorder==null||postorder.length==0)return null;
        if(postorder.length==1)return new TreeNode(postorder[0]);
        HashMap<Integer,Integer>map=new HashMap<>();
        int n=inorder.length;
        for(int i=0;i<n;i++)map.put(inorder[i],i);
        return construct(postorder,0,n-1,0,n-1,map);
    }

    private TreeNode construct(int[]post,int pStart,int pEnd,int iStart,int iEnd,HashMap<Integer,Integer>map){
        if(pStart>pEnd||iStart>iEnd)return null;
        TreeNode root=new TreeNode(post[pEnd]);
        int rootIdx=map.get(root.val);
        int numRight=iEnd-rootIdx;
        root.right=construct(post,pEnd-numRight,pEnd-1,rootIdx+1,iEnd,map);
        root.left=construct(post,pStart,pEnd-numRight-1,iStart,rootIdx-1,map);
        return root;
    }

    public static void main(String[] args) {
        BinaryTreeFromInorderPostorder builder = new BinaryTreeFromInorderPostorder();

        int[][] inorders = {
                {9, 3, 15, 20, 7},                  // Case 1: Standard tree
                {-1},                               // Case 2: Single node
                {1, 2, 3, 4},                       // Case 3: Right-skewed tree
                {4, 3, 2, 1},                       // Case 4: Left-skewed tree
                {2, 1, 3},                          // Case 5: Simple balanced tree
                {10, 20, 30, 40, 50},               // Case 6: Increasing values
                {1, 5, 2, 4, 3},                    // Case 7: Complex structure
                {-3000, 0, 3000},                   // Case 8: Boundary values
                {4, 2, 5, 1, 6, 3, 7},              // Case 9: Full binary tree
                {2, 3, 1}                           // Case 10: Root is not middle
        };

        int[][] postorders = {
                {9, 15, 7, 20, 3},                  // Case 1
                {-1},                               // Case 2
                {4, 3, 2, 1},                       // Case 3
                {4, 3, 2, 1},                       // Case 4
                {2, 3, 1},                          // Case 5
                {50, 40, 30, 20, 10},               // Case 6
                {5, 4, 3, 2, 1},                    // Case 7
                {-3000, 3000, 0},                   // Case 8
                {4, 5, 2, 6, 7, 3, 1},              // Case 9
                {2, 3, 1}                           // Case 10
        };

        String[] expectedOutputs = {
                "[3, 9, 20, null, null, 15, 7]",
                "[-1]",
                "[1, null, 2, null, 3, null, 4]",
                "[1, 2, null, 3, null, 4, null]",
                "[1, 2, 3]",
                "[10, null, 20, null, 30, null, 40, null, 50]",
                "[1, null, 2, 5, 3, null, null, 4]",
                "[0, -3000, 3000]",
                "[1, 2, 3, 4, 5, 6, 7]",
                "[1, 3, null, 2]"
        };

        int passed = 0;
        for (int i = 0; i < inorders.length; i++) {
            TreeNode result = builder.buildTree(inorders[i], postorders[i]);
            String actual = serialize(result);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Inorder:   " + Arrays.toString(inorders[i]));
            System.out.println("Postorder: " + Arrays.toString(postorders[i]));
            System.out.println("Expected:  " + expectedOutputs[i]);
            System.out.println("Actual:    " + actual);

            if (actual.equals(expectedOutputs[i])) {
                System.out.println("Result:    PASSED");
                passed++;
            } else {
                System.out.println("Result:    FAILED");
            }
            System.out.println("-----------------------------------------");
        }
        System.out.println("Final Score: " + passed + "/" + inorders.length);
    }

    /**
     * Helper to convert tree to BFS string representation for comparison
     */
    private static String serialize(TreeNode root) {
        if (root == null) return "[]";
        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                result.add("null");
            } else {
                result.add(String.valueOf(node.val));
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        // Remove trailing nulls to match common representation
        while (result.get(result.size() - 1).equals("null")) {
            result.remove(result.size() - 1);
        }

        return result.toString();
    }
}