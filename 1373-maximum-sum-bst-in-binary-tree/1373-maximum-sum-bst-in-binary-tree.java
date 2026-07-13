/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    int ans = 0;

    class NodeInfo {

        boolean isBST;
        int min;
        int max;
        int sum;

        NodeInfo(boolean isBST, int min, int max, int sum) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.sum = sum;
        }
    }

    public int maxSumBST(TreeNode root) {
        dfs(root);
        return ans;
    }

    private NodeInfo dfs(TreeNode root) {

        if (root == null) {
            return new NodeInfo(true, Integer.MAX_VALUE,
                    Integer.MIN_VALUE, 0);
        }

        NodeInfo left = dfs(root.left);
        NodeInfo right = dfs(root.right);

        if (left.isBST && right.isBST &&
                left.max < root.val &&
                root.val < right.min) {

            int sum = left.sum + right.sum + root.val;

            ans = Math.max(ans, sum);

            int min = Math.min(left.min, root.val);
            int max = Math.max(right.max, root.val);

            return new NodeInfo(true, min, max, sum);
        }

        return new NodeInfo(false, 0, 0, 0);
    }
}