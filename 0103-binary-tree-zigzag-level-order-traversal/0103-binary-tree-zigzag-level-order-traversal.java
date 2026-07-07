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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
         List<List<Integer>>ans=new ArrayList<>();
        if(root==null) return ans;
        Queue<TreeNode>q=new LinkedList<>();
        q.add(root);
        int flag=0;
        while(!q.isEmpty()){
            int size=q.size();
            List<Integer>levels=new ArrayList<>(Collections.nCopies(size,0));
            for(int i=0;i<size;i++){
                TreeNode node=q.poll();
                int index=(flag==0)?i:(size-1-i);
                levels.set(index,node.val);
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
            }
            ans.add(levels);
            flag=1-flag;  
        }
        return ans;
    }
}