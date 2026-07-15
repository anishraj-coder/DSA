package trees;

import java.util.*;



public class BinaryTreeMaxWidth {


    public int widthOfBinaryTree(TreeNode root) {
        if(root==null)return 0;
        if(root.left==null&&root.right==null)return 0;
        int maxWidth=0;
        Queue<Pair>q=new LinkedList<>();
        q.offer(new Pair(root,0));
        while(!q.isEmpty()){
            int n=q.size(),min=q.peek().pos,first=0,last=0;

            for(int i=0;i<n;i++){
                Pair rem=q.poll();
                int curr=(rem.pos-min);
                if(i==0)first=curr;
                if(n-1==i)last=curr;
                if(rem.node.left!=null){
                    q.offer(new Pair(rem.node.left,2*curr+1));
                }
                if(rem.node.right!=null){
                    q.offer(new Pair(rem.node.right,2*curr+2));
                }
            }

            maxWidth=Math.max(last-first+1,maxWidth);
        }
        return maxWidth;
    }

    private class Pair{
        int pos;
        TreeNode node;
        Pair(TreeNode node,int pos){
            this.pos=pos;
            this.node=node;
        }
    }

    // --- Test Utility Methods ---

    public static void main(String[] args) {
        BinaryTreeMaxWidth calculator = new BinaryTreeMaxWidth();
        int passed = 0;
        int totalTests = 10;

        // Test Cases
        Object[][] testCases = {
                { "Example 1: Standard Tree", new Integer[]{1, 3, 2, 5, 3, null, 9}, 4 },
                { "Example 2: Wide Gap (Level 4)", new Integer[]{1, 3, 2, 5, null, null, 9, 6, null, null, null, null, null, null, 7}, 8 },
                { "Example 3: Simple Balanced", new Integer[]{1, 3, 2, 5}, 2 },
                { "Single Node", new Integer[]{1}, 1 },
                { "Full Left Skewed", new Integer[]{1, 2, null, 3, null, 4}, 1 },
                { "Full Right Skewed", new Integer[]{1, null, 2, null, 3, null, 4}, 1 },
                { "V-Shape (Leaves only at ends)", new Integer[]{1, 2, 3, 4, null, null, 5}, 4 },
                { "Power of 2 level (Level 3)", new Integer[]{1, 1, 1, 1, 1, 1, 1}, 4 },
                { "Zig-Zag tree", new Integer[]{1, 2, null, null, 3, 4, null}, 2 },
                { "Large gaps in deep tree", new Integer[]{1, 2, 3, 4, null, null, 5, 6, null, null, 7}, 8 }
        };

        for (int i = 0; i < testCases.length; i++) {
            String description = (String) testCases[i][0];
            Integer[] treeData = (Integer[]) testCases[i][1];
            int expected = (int) testCases[i][2];

            TreeNode root = buildTree(treeData);
            int actual = calculator.widthOfBinaryTree(root);

            System.out.println("Test " + (i + 1) + ": " + description);
            System.out.println("Expected Width: " + expected);
            System.out.println("Actual Width:   " + actual);

            if (actual == expected) {
                System.out.println("Result:         PASSED\n");
                passed++;
            } else {
                System.out.println("Result:         FAILED\n");
            }
        }

        System.out.println("Final Score: " + passed + "/" + totalTests);
    }

    private static TreeNode buildTree(Integer[] vals) {
        if (vals == null || vals.length == 0) return null;
        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < vals.length) {
            TreeNode curr = queue.poll();
            if (i < vals.length && vals[i] != null) {
                curr.left = new TreeNode(vals[i]);
                queue.add(curr.left);
            }
            i++;
            if (i < vals.length && vals[i] != null) {
                curr.right = new TreeNode(vals[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}