/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        // Your code goes here
        if(node == null) return null;
        return dfs(node,new HashMap<>());
    }
    private Node dfs(Node node,Map<Integer,Node>visited){
        if(visited.containsKey(node.val))
        return visited.get(node.val);
        Node clone=new Node(node.val);
        visited.put(node.val,clone);
        for(Node nbr : node.neighbors){
            clone.neighbors.add(dfs(nbr,visited));
        }
        return clone;
    }
}