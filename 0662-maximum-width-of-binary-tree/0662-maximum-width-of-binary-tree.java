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
    static class Pair<K,V>{
        K key;
        V value;
        public Pair(K key,V value){
            this.key=key;
            this.value=value;
        }
        public K getKey(){
            return key;
        }
        public V getValue(){
            return value;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        if(root==null) return 0;
        Queue<Pair<TreeNode,Integer>>q=new LinkedList<>();
        q.offer(new Pair<>(root,0));
        int ans=0;
        while(!q.isEmpty()){
            int size=q.size();
            int mmin=q.peek().getValue();
            int first=0,last=0;
            for(int i=0;i<size;i++){
                int currId=q.peek().getValue()-mmin;
                TreeNode node=q.peek().getKey();
                q.poll();
                if(i==0){
                    first=currId;
                }
                if(i==size-1){
                    last=currId;
                }
                if(node.left!=null){
                    q.offer(new Pair<>(node.left,currId*2+1));
                }
                if(node.right!=null){
                    q.offer(new Pair<>(node.right,currId*2+2));
                }
            }
            ans=Math.max(ans,last-first+1);
        }
        return ans;
    }
}