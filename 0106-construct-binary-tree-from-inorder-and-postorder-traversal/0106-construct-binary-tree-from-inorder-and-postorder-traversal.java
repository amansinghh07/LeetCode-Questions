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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n=inorder.length;
        if(inorder.length!=postorder.length) return null;
        Map<Integer,Integer>inMap=new HashMap<>();
        for(int i=0;i<n;i++){
            inMap.put(inorder[i],i);
        }
        TreeNode root=buildTree(inorder,0,n-1,postorder,0,n-1,inMap,n);
        return root;
    }
    private TreeNode buildTree(int[] inorder,int inStart,int inEnd,int[] postorder,int postStart,int postEnd,Map<Integer,Integer>inMap,int n){
        if(inStart>inEnd || postStart>postEnd) return null;
        TreeNode root=new TreeNode(postorder[postEnd]);
        int inRoot=inMap.get(root.val);
        int numsLeft=inRoot-inStart;
        root.left=buildTree(inorder,inStart,inRoot-1,postorder,postStart,postStart+numsLeft-1,inMap,n);
        root.right=buildTree(inorder,inRoot+1,inEnd,postorder,postStart+numsLeft,postEnd-1,inMap,n);
        return root;
    }
}