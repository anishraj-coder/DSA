package trees;

import java.util.*;



public class BinaryTreeCodec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null)return " ";
        Queue<TreeNode>q=new LinkedList<>();
        StringBuilder ans=new StringBuilder();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode rem=q.poll();
            if(rem==null) {
                ans.append("@,");
                continue;
            }
            ans.append(rem.val+",");
            q.offer(rem.left);
            q.offer(rem.right);
        }
        return ans.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String str) {
        if(" ".equals(str))return null;
        String[] nodes=str.split(",");
        TreeNode root=new TreeNode(Integer.parseInt(nodes[0]));

        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        for(int i=1;i<nodes.length;i++){
            TreeNode rem=q.poll();
            if(!nodes[i].equals("@")) {
                rem.left = new TreeNode(Integer.parseInt(nodes[i]));
                q.offer(rem.left);
            }
            if(!nodes[++i].equals("@")) {
                rem.right = new TreeNode(Integer.parseInt(nodes[i]));
                q.offer(rem.right);
            }
        }

        return root;
    }

    // ==========================================
    // TEST RUNNER ENGINE
    // ==========================================
    public static void main(String[] args) {
        BinaryTreeCodec codec = new BinaryTreeCodec();
        int passed = 0;
        int totalTests = 11;

        System.out.println("Starting Binary Tree Codec Verification...\n");

        for (int i = 1; i <= totalTests; i++) {
            TreeNode originalTree = getTestCase(i);
            String testDescription = getTestCaseDescription(i);

            System.out.println("Test Case " + i + ": " + testDescription);

            try {
                // Step 1: Serialize the user's tree
                String serializedString = codec.serialize(originalTree);
                System.out.println("  Your Serialized Output: \"" + serializedString + "\"");

                // Step 2: Deserialize it back
                TreeNode deserializedTree = codec.deserialize(serializedString);

                // Step 3: Compare structures
                boolean match = isSameTree(originalTree, deserializedTree);

                String expectedVisual = treeToLevelOrderListString(originalTree);
                String actualVisual = treeToLevelOrderListString(deserializedTree);

                System.out.println("  Expected Tree Structure: " + expectedVisual);
                System.out.println("  Actual Tree Structure:   " + actualVisual);

                if (match) {
                    System.out.println("  Result: PASSED\n");
                    passed++;
                } else {
                    System.out.println("  Result: FAILED (Tree structure structural mismatch)\n");
                }
            } catch (Exception e) {
                System.out.println("  Result: FAILED with Exception: " + e.getMessage());
                e.printStackTrace();
                System.out.println();
            }
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Verification Completed: " + passed + " / " + totalTests + " Test Cases Passed.");
        System.out.println("--------------------------------------------------");
    }

    // Helper to check if two binary trees are identical in structure and values
    private static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // Helper to serialize tree to standard LeetCode level-order string format purely for displaying test results
    private static String treeToLevelOrderListString(TreeNode root) {
        if (root == null) return "[]";
        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                result.add("null");
            } else {
                result.add(String.valueOf(curr.val));
                queue.add(curr.left);
                queue.add(curr.right);
            }
        }

        // Trim trailing nulls to clean up the printed look
        while (!result.isEmpty() && result.get(result.size() - 1).equals("null")) {
            result.remove(result.size() - 1);
        }
        return result.toString();
    }

    private static String getTestCaseDescription(int id) {
        switch (id) {
            case 1: return "Empty Tree / Null Root (Crucial edge case)";
            case 2: return "Single Node Tree";
            case 3: return "Standard Balanced Binary Tree with mixed leaves";
            case 4: return "Strictly Left-Skewed Tree (Deep recursion/linear check)";
            case 5: return "Strictly Right-Skewed Tree";
            case 6: return "Tree containing extreme Negative, Zero, and Positive values";
            case 7: return "Symmetric / Mirror Image Tree structure";
            case 8: return "Asymmetric sparse tree (Multiple internal null gaps)";
            case 9: return "Full Binary Tree with identical repeating values everywhere";
            case 10: return "Large sequential line with zig-zag left-right paths";
            case 11: return "Nodes hitting max/min constraints (-1000 and 1000)";
            default: return "Unknown Test";
        }
    }

    private static TreeNode getTestCase(int id) {
        switch (id) {
            case 1: // Empty Tree
                return null;

            case 2: // Single Node
                return new TreeNode(1);

            case 3: // Standard tree [1,2,3,null,null,4,5]
                TreeNode r3 = new TreeNode(1);
                r3.left = new TreeNode(2);
                r3.right = new TreeNode(3);
                r3.right.left = new TreeNode(4);
                r3.right.right = new TreeNode(5);
                return r3;

            case 4: // Left-Skewed
                TreeNode r4 = new TreeNode(1);
                r4.left = new TreeNode(2);
                r4.left.left = new TreeNode(3);
                r4.left.left.left = new TreeNode(4);
                return r4;

            case 5: // Right-Skewed
                TreeNode r5 = new TreeNode(1);
                r5.right = new TreeNode(2);
                r5.right.right = new TreeNode(3);
                r5.right.right.right = new TreeNode(4);
                return r5;

            case 6: // Negative, zero, and positive values
                TreeNode r6 = new TreeNode(0);
                r6.left = new TreeNode(-5);
                r6.right = new TreeNode(105);
                r6.left.right = new TreeNode(-20);
                r6.right.left = new TreeNode(50);
                return r6;

            case 7: // Symmetric structure
                TreeNode r7 = new TreeNode(1);
                r7.left = new TreeNode(2);
                r7.right = new TreeNode(2);
                r7.left.left = new TreeNode(3);
                r7.right.right = new TreeNode(3);
                return r7;

            case 8: // Sparse tree with structural gaps
                TreeNode r8 = new TreeNode(1);
                r8.left = new TreeNode(2);
                r8.left.left = new TreeNode(3);
                r8.left.left.left = new TreeNode(4);
                r8.right = new TreeNode(5);
                r8.right.right = new TreeNode(6);
                r8.right.right.right = new TreeNode(7);
                return r8;

            case 9: // Repeating values
                TreeNode r9 = new TreeNode(7);
                r9.left = new TreeNode(7);
                r9.right = new TreeNode(7);
                r9.left.left = new TreeNode(7);
                r9.left.right = new TreeNode(7);
                return r9;

            case 10: // Zig-zag tree
                TreeNode r10 = new TreeNode(1);
                r10.left = new TreeNode(2);
                r10.left.right = new TreeNode(3);
                r10.left.right.left = new TreeNode(4);
                return r10;

            case 11: // Max boundary limits
                TreeNode r11 = new TreeNode(-1000);
                r11.left = new TreeNode(1000);
                r11.right = new TreeNode(-1000);
                r11.left.left = new TreeNode(1000);
                return r11;

            default:
                return null;
        }
    }
}