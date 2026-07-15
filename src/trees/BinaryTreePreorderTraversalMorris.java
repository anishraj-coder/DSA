package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class BinaryTreePreorderTraversalMorris {

    /**
     * Given the root of a binary tree, return the preorder traversal of its nodes' values.
     *
     * Target Technique: Morris Preorder Traversal (O(1) auxiliary space)
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root==null)return ans;
        TreeNode curr=root;
        while(curr!=null){
            if(curr.left==null){
                ans.add(curr.val);
                curr=curr.right;
            }else{
                TreeNode temp=curr.left;

                while(temp.right!=null&&temp.right!=curr){
                    temp=temp.right;
                }

                if(temp.right==null){
                    temp.right=curr;
                    ans.add(curr.val);
                    curr=curr.left;
                }else{
                    temp.right=null;
                    curr=curr.right;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        BinaryTreePreorderTraversalMorris engine = new BinaryTreePreorderTraversalMorris();
        int passed = 0;
        int totalTests = 10;

        System.out.println("--- Running Suite: Binary Tree Preorder Traversal Morris ---\n");

        // Test 1: Empty Tree (Edge Case)
        TreeNode root1 = null;
        List<Integer> expected1 = Arrays.asList();
        passed += verify(1, "Empty Tree", expected1, engine.preorderTraversal(root1));

        // Test 2: Single Node (Edge Case)
        TreeNode root2 = new TreeNode(1);
        List<Integer> expected2 = Arrays.asList(1);
        passed += verify(2, "Single Node Tree", expected2, engine.preorderTraversal(root2));

        // Test 3: Standard Example 1 (Right-skewed zig-zag)
        TreeNode root3 = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        List<Integer> expected3 = Arrays.asList(1, 2, 3);
        passed += verify(3, "Right-leaning Zig-Zag", expected3, engine.preorderTraversal(root3));

        // Test 4: Full Binary Tree (Perfect balance)
        TreeNode root4 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), new TreeNode(7))
        );
        List<Integer> expected4 = Arrays.asList(1, 2, 4, 5, 3, 6, 7);
        passed += verify(4, "Full Balanced Tree", expected4, engine.preorderTraversal(root4));

        // Test 5: Left Skewed Tree (Deep threading check)
        TreeNode root5 = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4), null), null), null);
        List<Integer> expected5 = Arrays.asList(1, 2, 3, 4);
        passed += verify(5, "Strictly Left-Skewed Tree", expected5, engine.preorderTraversal(root5));

        // Test 6: Right Skewed Tree (Deep lineage check)
        TreeNode root6 = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4))));
        List<Integer> expected6 = Arrays.asList(1, 2, 3, 4);
        passed += verify(6, "Strictly Right-Skewed Tree", expected6, engine.preorderTraversal(root6));

        // Test 7: Duplicate Values (Constraint verification)
        TreeNode root7 = new TreeNode(7, new TreeNode(7, null, new TreeNode(7)), new TreeNode(7));
        List<Integer> expected7 = Arrays.asList(7, 7, 7, 7);
        passed += verify(7, "Tree with All Identical Values", expected7, engine.preorderTraversal(root7));

        // Test 8: Extraneous Boundary Values (Boundary Check)
        TreeNode root8 = new TreeNode(-100, new TreeNode(0), new TreeNode(100));
        List<Integer> expected8 = Arrays.asList(-100, 0, 100);
        passed += verify(8, "Constraint Range Boundaries [-100, 100]", expected8, engine.preorderTraversal(root8));

        // Test 9: Complex Asymmetric Tree (Example 2 from problem description)
        TreeNode leftSub = new TreeNode(2,
                new TreeNode(4),
                new TreeNode(5, new TreeNode(6), new TreeNode(7))
        );
        TreeNode rightSub = new TreeNode(3,
                null,
                new TreeNode(8, new TreeNode(9), null)
        );
        TreeNode root9 = new TreeNode(1, leftSub, rightSub);
        List<Integer> expected9 = Arrays.asList(1, 2, 4, 5, 6, 7, 3, 8, 9);
        passed += verify(9, "Complex Asymmetric Tree", expected9, engine.preorderTraversal(root9));

        // Test 10: Linear Zig-Zag "Spine" Tree
        TreeNode root10 = new TreeNode(1,
                new TreeNode(2,
                        null,
                        new TreeNode(3,
                                new TreeNode(4),
                                null
                        )
                ),
                null
        );
        List<Integer> expected10 = Arrays.asList(1, 2, 3, 4);
        passed += verify(10, "Complex Inner Zig-Zag Spine", expected10, engine.preorderTraversal(root10));

        System.out.println("----------------------------------------");
        System.out.printf("Final Performance: %d/%d Tests Passed.\n", passed, totalTests);
    }

    private static int verify(int id, String description, List<Integer> expected, List<Integer> actual) {
        System.out.printf("Test #%d: %s\n", id, description);
        System.out.println("  Expected: " + expected);
        System.out.println("  Actual:   " + actual);

        if (expected.equals(actual)) {
            System.out.println("  Result:   [PASSED]\n");
            return 1;
        } else {
            System.out.println("  Result:   [FAILED]\n");
            return 0;
        }
    }
}