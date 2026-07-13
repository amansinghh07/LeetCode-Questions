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
    public boolean findTarget(TreeNode root, int k) {
       List<Integer>sortedEle=new ArrayList<>();
        inorder(root,sortedEle);
        int left=0;
        int right=sortedEle.size()-1;
        while(left<right){
            int sum=sortedEle.get(left)+sortedEle.get(right);
           if(sum==k) return true;
           else if(sum<k) left++;
           else right--;
        }
        return false;
    }
    private void inorder(TreeNode root,List<Integer>sortedEle){
        if(root==null) return;
        inorder(root.left,sortedEle);
        sortedEle.add(root.val);
        inorder(root.right,sortedEle);
    } 
}