/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null){
            return "";
        }
            StringBuilder sb=new StringBuilder();
            Queue<TreeNode>q=new LinkedList<>();
            q.offer(root);
            while(!q.isEmpty()){
                TreeNode currNode=q.poll();
                if(currNode==null){
                    sb.append("#,");
                }else{
                    sb.append(currNode.val).append(",");
                    q.offer(currNode.left);
                    q.offer(currNode.right);
                }
            }
            return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if(data.isEmpty()){
            return null;
        }
        StringBuilder s=new StringBuilder(data);
        String str;
        int commaIndex=s.indexOf(",");
        str=s.substring(0,commaIndex);
        s.delete(0,commaIndex+1);
        TreeNode root=new TreeNode(Integer.parseInt(str));
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode node=q.poll();
            commaIndex=s.indexOf(",");
            str=s.substring(0,commaIndex);
            s.delete(0,commaIndex+1);
            if(!str.equals("#")){
                TreeNode leftNode = new TreeNode(Integer.parseInt(str));
                node.left = leftNode;
                q.offer(leftNode);
            }

            // Read the value of the right child from the serialized data
            commaIndex = s.indexOf(",");
            str = s.substring(0, commaIndex);
            s.delete(0, commaIndex + 1);
            if (!str.equals("#")) {
                TreeNode rightNode = new TreeNode(Integer.parseInt(str));
                node.right = rightNode;
                q.offer(rightNode);
            }
        }

        // Return the reconstructed root of the tree
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));